
import java.util.ArrayList ;
/**
 * Décrivez votre classe abstraite AbstractJoueurPiece ici.
 *
 * @author     Ibrahima Soumare
 * @author     Serigne Banacar Diop
 * @version (un numéro de version ou une date)
 */
public abstract class AbstractJoueurPiece
{
    // variable d'instance - remplacez cet exemple par le vôtre
    private ObjetZor[] lesElements;
    private int size;
    private int maxSize;
    private String nom ;
    
    /**
     * requires nom!=null and maxSize>=0
     * @param   nom  :le nom ou la designation 
     * @param  tailleMax la taille maximale d'objet que peut contenir la piece ou le joueur
     * @throw IllegalArgumentException if tailleMax<=0
     * @trow NullPointerException if nom==null
     */
    public AbstractJoueurPiece(String nom,int tailleMax){
        
        if(tailleMax<=0){
            throw new IllegalArgumentException("La taille doit superieur strictement a zero");
        }
        
        if(nom==null){
            throw new NullPointerException("Il me faut un nom");
        }
        
        this.nom=nom;
        maxSize=tailleMax;
        size=0;
        lesElements=new ObjetZor[tailleMax];
        
    }
    
    
    /**
     * cette fonction abstraite permet de tester si l'ajout est possible
     * @param objet une instance d'objet qu'on va ajouter dans la piece ou le sac du joueur
     * @return   cette fonction retourne vraie si l'ajout est possible faux sinon
     */
    public abstract boolean ajoutEstPossible(ObjetZor objet);
    
    
    
    /**
     * cette fonction permet d'ajouter un objet dans une piece ou dans le sac du joueur
     * @param une instance d'objet nom null
     * @return une valeur true s'il l'a ajoute dans le sac du joueur ou dans la piece
     */
    public boolean  ajouterObjet(ObjetZor objet){
        
       if((objet!=null)&&(ajoutEstPossible(objet))&& (size<maxSize)){
           lesElements[size++]=objet;
           return true;
        }
        return false;
    }
    
    
    
    /**
     * @param le nom de l'objet a ajoute 
     * @require nom!=null
     * @return true si l'objet est dans la piece ou  dans le sac du joueur
     */
    public boolean  containsObjet(String name){
        
        if(name==null){
            return false;
        }
        
        for(int i=0 ;i<size;i++){
            if(lesElements[i].getNom().equals(name))
            return true;
        }
            
        return false;
    }
    
    
    
    /**
     * @return the size of the piece or gamer
     */
    public int  getSize(){
        return size;
    }
    
    
    /**
     * @return true si la piece ou le sac du joueur est vide
     */
    public boolean  isEmpty(){
        return size==0;
    }
    
    
    /**
     * @return true si la piece ou le sac du joueur est plein
     */
    public boolean   isFull(){
        
       return size==maxSize;
    }
  
    
    /**
     * @return le nom du Joueur ou la piece
     */
    public String getNom(){
        
        return nom ;
    }

    
    /**
     * @return une chaine de caractere pour la description du piece ou du joueur
     */
    public String afficheDescription(){
         String s="";
         s+="Nom :"+this.getNom()+"\n";
        s+=this.listDesObjets();
        return s;
    }
    
    
    
    /**
     *@return  la liste des Objets
     */
    public String listDesObjets(){
        String  s="";
        if(size==0){
            return "vide";
        }
        
        for(int i=0 ;i<size;i++){
            s+="\n   "+lesElements[i].descriptionObjet() ;
           }
        
        return s;
    }
    
    
    /**
     * @return la somme des poids des objets
     */
    public float  getPoidsTotal(){
          float s=0;
       
        for(int i=0 ;i<size;i++){
            
            s+=lesElements[i].getPoids() ;
        }
        return s;
    }
    
    
    /**
     * @param le nom de l'objet a prendre 
     * @return le premier objet ayant le nom 
     */
    public ObjetZor  prendreObjet(String nom){
        ObjetZor objet=null;
        int testeur=0;
        
        for(int i=0; i<maxSize;i++){
            testeur=0;
            if(lesElements[i].getNom().equals(nom)){
                objet=lesElements[i];
                testeur=1;
                for(int j=i;j<size;j++)
                {
                    lesElements[j]=lesElements[j+1];
                }
                size--;
            }  
        if(testeur==1)
         break ;
        }
    return objet ;
    }
    
    
    /**
     * cette fonction retourne l'ensemble d'objet ayant le nom
     * et les supprime dans l'ensemble des objets
     * @param le nom des objets a retourner 
     * @return un tableau d'objet ayant le nom
     */
    public ObjetZor[] prendreTous(String nom){
        ArrayList<ObjetZor> elements=new ArrayList<ObjetZor>();
        if(nom==null){
            return null ;
        }
        
        while(this.containsObjet(nom)){
            elements.add(this.prendreObjet(nom));
        }
        
        return elements.toArray(new ObjetZor[0]);
    }
    
    /**
      *@requires string!=null
      * @return le poids de l'objet ayant le nom string 
      */
      public float    getLePoids(String string)
    {
           
           if(string==null)
           {
               return 0;
           }
           
           return this.getUnObjet(string).getPoids();
    }
    
    
       /**
         * requires nom!null 
         * @return copie l'objet ayant le nom 
         */
    public ObjetZor  getUnObjet(String nom)
    {
        for(int i=0;i<size;i++)
        {
            if(lesElements[i].getNom().equals(nom))
                return lesElements[i];
        }
        return null;
    }
    
    
    
    
    
    
}















