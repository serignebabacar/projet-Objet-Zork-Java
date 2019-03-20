 

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Ibrahima Soumare
 * @author     Serigne Banacar Diop
 * @version    1.1
 */

public class Piece extends AbstractJoueurPiece {
    

    // mémorise les sorties de cette piece.
    private Map sorties;
    
    



    /**
     *  Initialise une piece décrite par la chaine de caractères spécifiée.
     *  Initialement, cette piece ne possède aucune sortie. La description fournie
     *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
     *
     * @param  description  Description de la piece.
     */
    public Piece(String description) {
        super(description,10);
        
        sorties = new HashMap();

    }
    
    /**
     * Cette methode teste si l ajout est possible 
     * @requires objet!=null
     * @return true if that's truth false esle
     */
    public boolean ajoutEstPossible(ObjetZor objet)
    {
        
        return this.isFull()==false;
    }
    
    
     /**
     *  Initialise une piece décrite par la chaine de caractères spécifiée.
     *  Initialement, cette piece ne possède aucune sortie. La description fournie
     *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
     *  
     * @param  description  Description de la piece.
     * @param  tailleMax la taille max des objetZorks
     */
     public Piece(String description,int tailleMax) 
     {
        super(description,tailleMax);
        sorties = new HashMap();

     }

    
    
    /**
     *  Définie les sorties de cette piece. A chaque direction correspond ou bien
     *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
     *  cette direction.
     *
     * @param  nord   La sortie nord
     * @param  est    La sortie est
     * @param  sud    La sortie sud
     * @param  ouest  La sortie ouest
     */
    public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) 
    {
        if (nord != null) {
            sorties.put("nord", nord);
        }
        if (est != null) {
            sorties.put("est", est);
        }
        if (sud != null) {
            sorties.put("sud", sud);
        }
        if (ouest != null) {
            sorties.put("ouest", ouest);
        }
    }


    
    
    
    
    

    /**
     *  Renvoie une description de cette piece mentionant ses sorties et
     *  directement formatée pour affichage, de la forme: <pre>
     *  Vous etes dans la bibliothèque.
     *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
     *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
     *  cette piece.
     *
     * @return    Description affichable de cette piece
     */
    public String descriptionLongue() {
        return "Vous etes dans " + this.getNom() + ".\n" + descriptionSorties();
    }


    
    
    /**
     *  Renvoie une description des sorties de cette piece, de la forme: <pre>
     *  Sorties: nord ouest</pre> Cette description est utilisée dans la
     *  description longue d'une piece.
     *
     * @return    Une description des sorties de cette pièce.
     */
    public String descriptionSorties() {
        String resulString = "Sorties:";
        Set keys = sorties.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
            resulString += " " + iter.next();
        }
        return resulString;
    }

    
    

    /**
     *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
     *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
     *  renvoie null.
     *
     * @param  direction  La direction dans laquelle on souhaite se déplacer
     * @return            Piece atteinte lorsque l'on se déplace dans la direction
     *      spécifiée ou null.
     */
    public Piece pieceSuivante(String direction) {
        return (Piece) sorties.get(direction);
    }
    
    
     
       
       /**
        *  C'est la fonction permettant de faire la modification
        *  c'est a dire de prendre la piece situee a l'ouest de piece1 
        *  et de la mettre a l'est de cette piece
        *  @param une piece quelconque pour recuper la piece situee a l'ouest de ce piece
        */
    public void modifierCarte(Piece piece1){
            if(piece1.pieceSuivante("ouest")!=null)
            this.sorties.put("est",piece1.pieceSuivante("ouest"));
    }
    
    

    
    /**
     * cette methode a ete faaite pour compliquer les choses 
     * c'est adire dans le cas ou on veut attarder le joueur en changeant la transportabilite d'un objet
     * @param l piece contenant l'objet
     * @param   l'objet a modifie la transportabilite
     * @return true si tel est le cas false sinon
     */
    
    public boolean tromper_Un_Joueur(Piece piece,String nom){
          ObjetZor objet=piece.prendreObjet(nom);
          if(objet!=null){
              objet.modifierTransportable();
              piece.ajouterObjet(objet);
              return true ;
           }
          return false ;  
    }
    
    
    /**
     * cette fonction nos ermet de savoir plus d'information sur une piece donnee
     */
    public String  afficherPiece(){
        
        String s=this.afficheDescription();
        Piece piece;

        if((piece=pieceSuivante("ouest"))!=null)
          s+=" ouest :"+piece.getNom();
        if((piece=pieceSuivante("est"))!=null)
             s+="\n   est :"+piece.getNom();
        if((piece=pieceSuivante("nord"))!=null)
             s+="\n nord :"+piece.getNom();
        if((piece=pieceSuivante("sud"))!=null)
             s+="\n  sud :"+piece.getNom();
             
        return s;
       }

       
    
       
     
     /**
      * le redefinition de la methode equals pour deux pieces
      * @return true si tel est le cas false sinon
      */
       public boolean equals(Object objet){
           if((objet==null)||!(objet instanceof Piece))
                  return false;
           Piece piece=(Piece) objet;
           if(this.getNom().equals(piece.getNom()))
                   return true;
            return false; 
       }
         
    
    
   

}






