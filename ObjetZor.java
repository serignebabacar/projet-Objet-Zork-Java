

/**
 * Cette classe Objet est caracterisee par le nom d'un objet 
 * un attribut transportable de type boolean pour savoir si 
 * on peut transporter ou deplacer l'objet
 * un attribut poids pour reprsenter le poids de l'objet
 * @author     Ibrahima Soumare
 * @author     Serigne Banacar Diop
 * 
 */
public class ObjetZor
{
   // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String nom;
    private float  poids;
    private boolean transportable;

    /**
     * Constructeur d'objets de classe Objet pour le cas non transportable seulement
     * Prenant en argument le nom et le prenom 
     * @nom c'est de type String qui specifie le nom de l'objet 
     * @poids c'est de type float qui specifie le poids de l'objet
     */
    public ObjetZor(String nom,float poids)
    {
       this.poids=poids;
       this.nom=nom;
    }
    
    
    /**
     * Constructeur d'objets de classe Objet pour les deux cas 
     * Prenant en argument le nom , le prenom et une valeur boolean
     * @nom c'est de type String qui specifie le nom de l'objet 
     * @poids c'est de type float qui specifie le poids de l'objet
     * @transportable qui est une valeur boolean pour specifier si l'objet est transportable ou non
     */
   
       public ObjetZor(String nom,float poids,boolean transportable)
    {
       this.poids=poids;
       this.nom=nom;
       this.transportable=transportable;
    }
    
    /**
     * c'est un accesseur qui ermet de retourner le poids d'un objet
     */
    public float getPoids()
    {
        return poids;
    }
    
    /**
     * c'est un accesseur qui permet de retourner le nom de l'objet
     * @return le nom de l'Objet
     */
     
    public  String getNom()
    {
        return nom ;
    }
    
    /**
     * c'est un fonction permettant de modifier la valeur boolean de l'objet
     * je l'ai mis dans le cas ou on a deux joueur et l'un veut compliquer la tache a un autre joueur 
     */
    public void  modifierTransportable(){
       this.transportable=!transportable;
    }  
      
    /**
     * c'est la redefinition de la methode equals
     */
    public boolean   equals(Object object){
        if((object==null)||!(object instanceof ObjetZor))
             return false ;
             ObjetZor objet=(ObjetZor) object;
        if(objet.nom.equals(this.nom))
              return true ;
        return false ;
               
    }
    
    
    /**
     * 
     */
    public boolean estTransportable(){
        return transportable;
    }
    
    
    /**
     * 
     */
    public String descriptionObjet(){
        
        return "nom :"+this.nom+" poids: "+this.poids+ (transportable?" est transportable":" il nest pas transportable");
    }
    
    
}
