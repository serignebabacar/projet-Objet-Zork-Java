 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se déplacer parmi les différentes pieces.
 *  Ce jeu nécessite vraiment d'etre enrichi pour devenir intéressant!</p> <p>
 *
 *  Pour jouer a ce jeu, créer une instance de cette classe et appeler sa
 *  méthode "jouer". </p> <p>
 *
 *  Cette classe crée et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Ibrahima Soumare
 * @author     Serigne Babacar Diop
 * @version    1.1
 */

public class Jeu {
    private AnalyseurSyntaxique analyseurSyntaxique;
    private Joueur leJoueur;
    private ArrayList<Piece> lesPieces;
   
         /**
           *  Crée le jeu et initialise la carte du jeu (i.e. les pièces).
           */
          public Jeu() {
        
            lesPieces=new ArrayList<Piece>();
      
            leJoueur=new Joueur("testeur",10,50);
        
            analyseurSyntaxique = new AnalyseurSyntaxique();
        
            creerPieces();
          }  
    

            /**
            *  Crée toutes les pieces et relie leurs sorties les unes aux autres.
           */
          public void creerPieces() {
        
            Piece premiere;
            Piece deuxieme;
            Piece troisieme;
           // création des pieces
            premiere = new   Piece("premiere_Piece");
            deuxieme = new Piece("deuxieme_Piece");
            troisieme= new Piece("troisieme_Piece");
            ObjetZor obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9;
            obj1=new ObjetZor("marteau",10);
            obj2=new ObjetZor("pique",15,true);
            obj3=new ObjetZor("pince",18,true);
            obj4=new ObjetZor("sceau",9,true);
            obj5=new ObjetZor("bombe",25,true);
            obj6=new ObjetZor("arme",15,true);
            obj7=new ObjetZor("velo",15,true);
            obj8=new ObjetZor("pneu",18,true);
            obj9=new ObjetZor("poubelle",25,true);
            premiere.ajouterObjet(obj1);
            premiere.ajouterObjet(obj8);
            premiere.ajouterObjet(obj9);
            premiere.ajouterObjet(obj2);
            deuxieme.ajouterObjet(obj3);
            troisieme.ajouterObjet(obj4);
            troisieme.ajouterObjet(obj5);
            troisieme.ajouterObjet(obj6);
            troisieme.ajouterObjet(obj7);
            // initialise les sorties des pieces
            premiere.setSorties(null, deuxieme, null, null);
            deuxieme.setSorties(null, troisieme, null, premiere);
            troisieme.setSorties(null, null, null, deuxieme);
            //quatrieme.setSorties(null, cinquieme, null, deuxieme);
            //cinquieme.setSorties(null,null,null,quatrieme);
            lesPieces.add(premiere);
            lesPieces.add(deuxieme);
            lesPieces.add(troisieme);
            //lesPieces.add(quatrieme);
            //lesPieces.add(cinquieme);
            // le jeu commence dehors
             leJoueur.initialiserPosition(premiere);
            }


             /**
              *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
              */
             public void jouer() {
                 afficherMsgBienvennue();
        
                 // En   trée dans la boucle principale du jeu. Cette boucle lit
                 // et exécute les commandes entrées par l'utilisateur, jusqu'a
                 // ce que la commande choisie soit la commande "quitter"
                 boolean termine = false;
                 Date date=new Date();
                 Date date2=null;
                 while (!termine) {
                     date2=new Date();
                     if((date2.getMinutes()-date.getMinutes())>1)
                        break;
                     Commande commande = analyseurSyntaxique.getCommande();
                     termine = traiterCommande(commande);
                     if(termine==false)
                        termine=aGagner();
                      
                     
                }
        
                if(aGagner())
                    System.out.println("vous avez gagne"+(date2.getMinutes()-date.getMinutes())*60+"s");
                else
                    System.out.println("vous avez perdu car la duree est terminee");
                System.out.println("Merci d'avoir jouer.  Au revoir.");
            }


          /**
            *  Affiche le message d'accueil pour le joueur.
            */
          public void afficherMsgBienvennue() {
            System.out.println();
            System.out.println("Bienvennue dans le monde de Zork !");
            System.out.println("Zork est un nouveau jeu d'aventure, terriblement enuyeux.");
            System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
            System.out.println();
            System.out.println(leJoueur.getPositionActuelle().descriptionLongue());
          }


          /**
            *  Exécute la commande spécifiée. Si cette commande termine le jeu, la valeur
            *  true est renvoyée, sinon false est renvoyée
            *
            * @param  commande  La commande a exécuter
            * @return           true si cette commande termine le jeu ; false sinon.
           */
          public boolean traiterCommande(Commande commande) {
               if (commande.estInconnue()) {
                 System.out.println("Je ne comprends pas ce que vous voulez...");
                 return false;
               }

              String motCommande = commande.getMotCommande();
        
       
               if (motCommande.equals("lister")) {
                System.out.println("voici les objets qui sont dans ton sac : ");
                System.out.print("{");
                ceQueJePorte();
                System.out.print("}");
                System.out.println();
               }
              else
                if (motCommande.equals("prendre")) {
                     if(commande.aSecondMot()){
                         if(prendreObjet(commande.getSecondMot()))
                            System.out.println("l'objet est maintenant dans ton sac");
                         else
                            System.out.println("desolez mais vous ne ouvez pas prendre cet objet");
                        }
                     else
                       System.out.println("\n vous devriez donner le nom de l'objet a prendre");
               }
               else
                  if (motCommande.equals("deposer")) {
                        if(commande.aSecondMot())
                             deposerObjet(commande.getSecondMot());
                        else
                            System.out.println("\n vous devriez donner le nom de l'objet a deposer");
                   } 
                  else
                     if(motCommande.equals("retour")) {
                          System.out.println("vous etiez dans :"+leJoueur.getPositionActuelle().getNom());
                          leJoueur.retourner();
                          System.out.println("maintenant vous etes dans :"+leJoueur.getPositionActuelle().getNom());
                      }      
                      else
                         if(motCommande.equals("aide")) {
                                afficherAide();
                          } 
                          else 
                             if (motCommande.equals("aller")) {
                                     deplacerVersAutrePiece(commande);
                    
                             } 
                             else
                                 if (motCommande.equals("quitter")) {
                                        if (commande.aSecondMot()) {
                                             System.out.println("Quitter quoi ?");
                                        }
                                        else 
                                             return true;
            
                                  }
              return false;
            }


          // implementations des commandes utilisateur:

           /**
             *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
             */
           public void afficherAide() {
              System.out.println(" vous etes dans la piece :"+leJoueur.getPositionActuelle().getNom());
              System.out.println("voici la liste des objets que tu as :");
              System.out.print("{");
              ceQueJePorte();
              System.out.println("}");
        
              System.out.println("Les commandes reconnues sont:");
              analyseurSyntaxique.afficherToutesLesCommandes();
              System.out.println("\n**********************description des pieces ***************************************\n");
              descriptionnDesPieces();
              System.out.println("\n ***************--------------*******---------------********************************\n");
          }


          
            /**
              *  Tente d'aller dans la direction spécifiée par la commande. Si la piece
              *  courante possède une sortie dans cette direction, la piece correspondant a
              *  cette sortie devient la piece courante, dans les autres cas affiche un
              *  message d'erreur.
              *
              * @param  commande  Commande dont le second mot spécifie la direction a suivre
              */
           public void deplacerVersAutrePiece(Commande commande) 
           {
             if (!commande.aSecondMot()) {
                 // si la commande ne contient pas de second mot, nous ne
                 // savons pas ou aller..
                 System.out.println("Aller où ?");
                 return;
             }

              String direction = commande.getSecondMot();
  
              leJoueur.deplacerVers(direction);
              System.out.println(leJoueur.getPositionActuelle().descriptionLongue());
        
          }
   
    
         /**
          * permet d'ajouter une piece dans la liste des pieces
          * @param la piece a ajoute
          */
          public void ajouterPiece(Piece piece)
          {
           if(piece!=null)
              lesPieces.add(piece);
          }
     
          /**
          * cette fonction permet de recuperer la piece ayant la description 
          * @param la description de la piece
          * @return la piece specifique
          */
          public Piece getPiece(String description)
         {
           Piece piece=null;
           for(Iterator it=lesPieces.iterator() ;it.hasNext();){
             piece=(Piece) it.next();
             if(piece.getNom().equals(description))
                 break;
            }
            return piece;
         }
    
    
    
    
        /**
         * cette fonction permet a l'utilisanteur de deposer
         * un objet dans une piece donnee
         * @param le nom de l'objet
         * @return true si l'operation a reussi et false sinon
         */
         public boolean deposerObjet(String nom)
        {
         ObjetZor objet=null;
         Piece piece=leJoueur.getPositionActuelle();
         ObjetZor unObjet=leJoueur.prendreObjet(nom);
         int i=lesPieces.indexOf(piece);
         piece.ajouterObjet(unObjet);
         lesPieces.set(i,piece);
	 return true ;
        }
        
    
        /**
         * 
         */
        public boolean prendreObjet(String nom)
        {
        
                Piece piece=leJoueur.getPositionActuelle();
                ObjetZor unObjet=piece.getUnObjet(nom);
                
                int i=lesPieces.indexOf(piece);
                if(unObjet!=null && unObjet.estTransportable()){
                    if(leJoueur.ajouterObjet(unObjet)){
                        piece.prendreObjet(nom);
                        lesPieces.set(i,piece);
                        return true;
                    }
                }
                return false ;
        }
        
        /**
         * cette fonction permet de donner un affichage 
         * c'est a dire les objets qui sont dans chaque piece 
         */
        public void descriptionnDesPieces()
        {
            for(Iterator it=lesPieces.iterator();it.hasNext();)
            {
                Piece piece=(Piece) it.next();
                System.out.println(piece.afficherPiece());
            }
        }
       
        /**
        * cette focntion permet d'aider l'utilisateur our connaitre 
        * les objets qui sont dans son son sac
        */
        public void ceQueJePorte()
        {
            
                System.out.print(" "+leJoueur.listDesObjets());
             
        }
        
         /**
         * cette fonction teste si l'utilisateur a gagne
         * tel est le cas il renvoie true si tel est le cas false sinon
         */
        public boolean aGagner(){
            int dif=0,initial;
             for(Iterator it=lesPieces.iterator();it.hasNext();){
                 Piece p=(Piece) it.next();
                 if(p.getSize()!=nbObjets() /nbPiece())
                   return false;
             }
            return true ;
        }
        
         /**
         * cette fonction nous permet de savoir le nombre toutal d'objet qui sont dans les pieces 
         * @return le nombre d'objet
         */
        public   int nbObjets()
        {
            int i=0;
            for(Iterator it=lesPieces.iterator();it.hasNext();){
                Piece p=(Piece)it.next();
                i+=p.getSize();
           }
        return i;
        }
        
        
        /**
         * cette fonction permet de savoir le nombre de piece qui sont dans le jeu
         * @return le nombre de piece
         */
        public int nbPiece()
        {
            return lesPieces.size();
        }
       
}

