
/**
 * Décrivez votre classe Joueur ici.
 *
  * @author     Ibrahima Soumare
 * @author     Serigne Banacar Diop
 * @version (un numéro de version ou une date)
 */
public class Joueur extends AbstractJoueurPiece
{
    
    private float poids ;
    private Piece pieceCourante;
    private Piece lastPieceVisite;
    /**
     * Constructeur d'objets de classe Joueur
     * @requires nom!=null , size>0 , poids>0
     * @throw IllegalArgumentException if poids<=0
     */
    public Joueur(String nom ,int size,float poids)
    {
         super(nom,size);
        if(poids<=0){
            throw new IllegalArgumentException("le poids doit superieur strictement a zero");
        }
        this.poids=poids;
        pieceCourante=null;
        lastPieceVisite=null;
    }
    
    
    /**
     * @requires objet!=null
     * @param  l'objet l'instance 
     * @return true si l'ajout est possible et false sinon
     */
    public boolean   ajoutEstPossible(ObjetZor objet){
    
         if((objet==null)||(objet.estTransportable()==false)||(this.getPoidsTotal()+objet.getPoids()>poids)){
             return false;
         }
    return true;
    }
    
    
    /**
     * cette fonction initialise la position du joueur 
     * @requires piece!=null
     * @param le piece qui sera la piece initiale
     */
    public void initialiserPosition(Piece piece){
        if(piece!=null && pieceCourante==null)
        {
            pieceCourante=lastPieceVisite=piece;
        }
    }
    
    
    
    /**
     * cette fonction permet a le joueur de se deplacer vers une autre piece 
     * @param la direction
     */
    public void deplacerVers(String vers)
    {
          if((vers!=null)&&pieceCourante.pieceSuivante(vers)!=null){
              lastPieceVisite=pieceCourante;
              pieceCourante=pieceCourante.pieceSuivante(vers);
          }
    }
    
    
    
    /**
     *  cette fonction permet au joueur de se retourner dans la piece ou il etait
     */
    public void retourner(){
        Piece piece=pieceCourante;
        pieceCourante=lastPieceVisite;
        lastPieceVisite=piece;
    }
    
    
    /**
     * cette fonction permet de savoir la piece ou le joueur se situe
     * @return la piece du joueur 
     */
    public Piece getPositionActuelle(){
        return pieceCourante;
    }
}
