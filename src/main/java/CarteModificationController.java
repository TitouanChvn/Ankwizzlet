import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import javafx.stage.Stage;
import javafx.scene.Node;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class CarteModificationController {
    
    MyDecks MyDecks;
    Flashcard carte;
    @FXML
    private TextField question;
    @FXML
    private TextField reponse;
    @FXML
    private TextArea explication;
    @FXML
    private TextArea indice;
    @FXML
    private Label titre ;
    @FXML
    private Label description ;
    private int idC;
    @FXML
    private Button suivant;
    @FXML
    private Button precedent;


    public CarteModificationController() {
    }

    public CarteModificationController(MyDecks MyDecks, Flashcard carte) {
        this.MyDecks = MyDecks;
        this.carte = carte;
    }
    
    //Initialisation de la page
    public void initialize() {

        explication.setWrapText(true);
        indice.setWrapText(true);
        
        //Initialisation des labels
        titre.setText(MyDecks.getActualDeck().getName());
        description.setWrapText(true);
        description.setText(MyDecks.getActualDeck().getDescription());

        //Initialisation des textFields et des textAreas
        if(carte != null){
            question.setText(carte.getQuestion());
            reponse.setText(carte.getAnswer());
            explication.setWrapText(true);
            explication.setText(carte.getExplication());
            indice.setWrapText(true);
            indice.setText(carte.getIndice());
            this.idC = carte.getId();
        }
        else {
            question.setText("beh alors");
            reponse.setText("la carte est nulle");
            
            //initialisation de l'id de la carte
            if (MyDecks.getActualDeck().deck.size() == 0) {
                this.idC = 1;
            }
            else {
                this.idC = MyDecks.getActualDeck().deck.size()+1;
            }
        }
    }

    @FXML
    public void whoWeAre() {
        // fait apparaitre une alerte
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Qui sommes nous ?");
        
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Et bonjour ?? toi \n \n Qui nous sommes ? Nous sommes quatres ??tudiants de TELECOM Nancy, une ??cole d'ing??nieur sp??cialis??e dans l'informatique. Tu peux retrouver nos pr??noms sur la page d'accueil. Nous avons developp?? ce jeu dans le cadre d'un projet sur une semaine que notre ??cole nous ?? demand?? de r??aliser. \n Tu peux nous contacter via le mail suivant : ankwizzlet@gmail.com. Que ce soit pour une question, un conseil, une critique ou de l'encouragement nous sommes toujours heureux de recevoir des mails de la part de nos joueurs. \n \n Amuse toi bien sur notre application !");
        
        alert.showAndWait();
        return ;
    }

    @FXML
    public void howToPlay() {
        // fait apparaitre une alerte
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Comment jouer ?");
        
        // Header Text: null
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinWidth(1000);
        dialogPane.setMinHeight(500);
        dialogPane.setPrefWidth(1000);
        dialogPane.setPrefHeight(500);
        dialogPane.setMaxWidth(1000);
        dialogPane.setMaxHeight(500);
        alert.setHeaderText(null);
        alert.setContentText("Comment jouer ? Sur le menu d'accueil tu as trois boutons, un qui va te permettre de cr??er ou de modifier un deck, un autre qui va te permettre de t'entra??ner sur un deck et un dernier qui va te montrer tes statistiques. \n \n Quand tu cliques sur le bouton pour cr??er ou modifier un deck tu vas avoir le choix entre plusieurs deck d??ja existants, plac??s dans une liste. Tu peux soit en selectionner un et appuyer sur \"Ok\", soit d??cider d'en cr??er un. Pour cr??er un deck il faut uniquement un nom et une description au deck. \n Une fois le deck choisi ou cr???? tu es amen?? sur la page de modification de Deck. sur cette page s'inscrit le nom de ton deck, sa description, que tu peux modifier et sauvegarder en appuyant sur le bouton \"Enregistrer\". Sinon tu peux aussi selectionner une carte et appuyer sur le bouton \"Modifier\". Ici tu pourras changer ce que tu veux de la carte, clique ensuite sur enregistrer pour la sauvegarder. \n \n \n Si tu d??cides de cliquer sur le bouton pour t'entra??ner lorsque tu es dans l'accueil, tu vas arriver face a une liste de deck, tous ces decks contiennent des carte. Choisis celui sur lequel tu veux t'entra??ner. Tu as le maintenant le choix entre quatre mode diff??rents. \n Le mode Infini : Les cartes du deck arrivent, al??atoirement (mais plus tu as eu la carte moins tu as de chance de la voir), sans limite de temps ou de carte. L'id??e est de s'entra??ner sur le deck entier tranquillement. \n Le mode Examen : Les cartes arrivent dans un ordre al??atoire, mais tu as un temps limit?? pour r??pondre. Pas d'indice possible. C'est de l'auto ??valuation, on te fait confiance pour ??tre honn??te ! \n Le mode Rush : Les cartes arrivent dans un ordre al??atoire, sans limite. Tu as une limite de temps par contre. Le but est de r??pondre le plus vite et le plus juste possible au plus de question possible dans le temps imparti. \n Le mode Classique : Tu peux d??cider de combien de carte tu veux avoir, et de combien de temps tu as pour r??pondre. Tu peux aussi choisir si tu veux des indices ou pas. C'est le mode le plus classique. \n \n Une fois un entra??nement fini une fen??tre s'affiche. De cette fen??tre tu peux soit rejouer soit aller voir les statistiques de ton profil. \n \n \n Si tu d??cides de cliquer sur le bouton des statistique tu peux voir tes statistiques. Tu as le choix entre les statistiques de tous les decks, ou les statistiques d'un deck en particulier. Il y a plusieurs information que je te laisserai d??couvrir par toi m??me. \n Il y a aussi la possibilit?? de voir les statistique sous forme de graphe, sur l'ensemble des decks ou non. A toi de voir la vue que tu pr??f??re. \n \n Si tu as un probl??me quelconque sur le jeu, que ce soit sur la cr??ation de Deck ou autre chose n'h??site pas a nous contacter par mail : ankwizzlet@gmail.com. \n \n \n Amuse toi bien sur notre application !");
        
        alert.showAndWait();
        return ;
    }


    //Quitter l'application
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    //Aller sur la page d'accueil
    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Gestion de l'icone du bouton
    @FXML
    public void goToHomeIcon(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    //Aller sur la page pr??c??dente
    @FXML
    public void previousPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new DeckModificationController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("DeckModification.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    //Sauvegarder les modifications de la carte
    @FXML
    public void save(ActionEvent event) throws IOException {

        //R??cup??rer les donn??es de la carte
        String question = this.question.getText();
        String reponse = this.reponse.getText();
        String description = this.explication.getText();
        String indice = this.indice.getText();

        if (carte != null) { 

            //Si la carte est non nulle, mettre ?? jour les donn??es de la carte
            MyDecks.getActualDeck().getFlashcard(idC-1).setQuestion(question);
            MyDecks.getActualDeck().getFlashcard(idC-1).setAnswer(reponse);
            MyDecks.getActualDeck().getFlashcard(idC-1).setExplication(description);
            MyDecks.getActualDeck().getFlashcard(idC-1).setIndice(indice);
            }
        else {

            //Sinon, cr??er une nouvelle carte
            MyDecks.getActualDeck().addFlashcard(new Flashcard(question, reponse, indice, description, idC, 0, 0));
        }
    }
    
    
    //G??rer la suppression de carte :
    @FXML
    private void supp(ActionEvent event) throws IOException{
        
        //Supprimer la carte du deck
        MyDecks.getActualDeck().removeFlashcard(carte);
        previousPage(event);
    }
    

    //Bouton pour passer ?? la carte suivante
    @FXML
    private void next(ActionEvent event) throws IOException {
        int act = carte.getId() -1;
        if (carte.getId() < MyDecks.getActualDeck().deck.size()) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new CarteModificationController(MyDecks, MyDecks.getActualDeck().getFlashcard(act+1)));
            Parent root = (Parent) loader.load(getClass().getResource("CarteModification.fxml").openStream());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {

            //D??sactiver le bouton suivant si on est sur la derni??re carte
            suivant.setDisable(true);
        }
    }


    //Bouton pour passer ?? la carte pr??c??dente
    @FXML
    private void previous(ActionEvent event) throws IOException {
        int act = carte.getId() -1;
        if (carte.getId() > 1) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new CarteModificationController(MyDecks, MyDecks.getActualDeck().getFlashcard(act-1)));
            Parent root = (Parent) loader.load(getClass().getResource("CarteModification.fxml").openStream());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {

            //D??sactiver le bouton pr??c??dent si on est sur la premi??re carte
            precedent.setDisable(true);
        }
    }
    
}