import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class DeckModificationController {

    @FXML
    TextArea DescriptionDeck;
    @FXML 
    TextField NomDeck;
    MyDecks MyDecks ; 
    @FXML
    TableView table; 
    @FXML
    TableView<Flashcard> carte = new TableView<>();
    Flashcard carteSelected;

    //On initialise la page
    public void initialize() {
        //On récupère le deck sélectionné
        DescriptionDeck.setText(MyDecks.getActualDeck().getDescription());
        NomDeck.setText(MyDecks.getActualDeck().getName());

        //On récupère les cartes du deck
        ArrayList<Flashcard> flashcards = MyDecks.getActualDeck().getDeck();

        //On affiche les cartes dans le tableau
        TableColumn<Flashcard, String> sourceLanguageColumn = new TableColumn<>("question");
        sourceLanguageColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        TableColumn<Flashcard, String> targetLanguageColumn = new TableColumn<>("answer");
        targetLanguageColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
        TableColumn<Flashcard, String> sourceWordColumn = new TableColumn<>("explication");
        sourceWordColumn.setCellValueFactory(new PropertyValueFactory<>("explication"));
        TableColumn<Flashcard, String> targetWordColumn = new TableColumn<>("indice");
        targetWordColumn.setCellValueFactory(new PropertyValueFactory<>("indice"));
        TableColumn<Flashcard, Integer> progressColumn = new TableColumn<>("played");
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("played"));
        TableColumn<Flashcard, Integer> scoreColumn = new TableColumn<>("won");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("won"));

        table.getColumns().addAll(sourceLanguageColumn, targetLanguageColumn, sourceWordColumn, targetWordColumn, progressColumn, scoreColumn);
        table.setItems(FXCollections.observableArrayList(flashcards));
        DescriptionDeck.setWrapText(true);
    }



    public DeckModificationController() {
    }
    
    public DeckModificationController(MyDecks MyDecks) {
        this.MyDecks = MyDecks;
    }

    //Créer une carte
    @FXML
    public void creerCarte(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new CarteCreationController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("CarteCreation.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Revenir à la page précédente
    @FXML
    public void previousPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new SelectionModificationController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("SelectionModification.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        table.getItems().clear();
    }

    //Aller à la page d'accueil
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
    
    //Modifier une carte
    @FXML 
    public void modificationCarte(ActionEvent event) throws IOException {
        //On récupère la carte sélectionnée
        carteSelected = (Flashcard) table.getSelectionModel().getSelectedItem();
        if (carteSelected == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune carte sélectionnée");
            alert.setContentText("Veuillez sélectionner une carte pour la modifier");
            alert.showAndWait();
            return;
        }
        
        //On ouvre la page de modification de carte
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new CarteModificationController(MyDecks, carteSelected));
        Parent root = (Parent) loader.load(getClass().getResource("CarteModification.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    //Quitter l'application
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    //Saupvegarder les modifications du deck
    @FXML
    private void saveDeck(ActionEvent event) {
        //On récupère les informations du deck
        String nom = NomDeck.getText();
        String description = DescriptionDeck.getText();

        //On les enregistre dans le deck
        MyDecks.getActualDeck().setName(nom);
        MyDecks.getActualDeck().setDescription(description); 
    }

    
    //Supprimer le deck
    @FXML
    private void supp(ActionEvent event) throws IOException{
        //On supprime le deck
        MyDecks.getDecks().remove(MyDecks.getActualDeck());

        //On retourne à la page précédente
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new SelectionModificationController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("SelectionModification.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        table.getItems().clear();
    }

    
    @FXML
    public void whoWeAre() {
        // fait apparaitre une alerte
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Qui sommes nous ?");
        
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Et bonjour à toi \n \n Qui nous sommes ? Nous sommes quatres étudiants de TELECOM Nancy, une école d'ingénieur spécialisée dans l'informatique. Tu peux retrouver nos prénoms sur la page d'accueil. Nous avons developpé ce jeu dans le cadre d'un projet sur une semaine que notre école nous à demandé de réaliser. \n Tu peux nous contacter via le mail suivant : ankwizzlet@gmail.com. Que ce soit pour une question, un conseil, une critique ou de l'encouragement nous sommes toujours heureux de recevoir des mails de la part de nos joueurs. \n \n Amuse toi bien sur notre application !");
        
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
        alert.setContentText("Comment jouer ? Sur le menu d'accueil tu as trois boutons, un qui va te permettre de créer ou de modifier un deck, un autre qui va te permettre de t'entraîner sur un deck et un dernier qui va te montrer tes statistiques. \n \n Quand tu cliques sur le bouton pour créer ou modifier un deck tu vas avoir le choix entre plusieurs deck déja existants, placés dans une liste. Tu peux soit en selectionner un et appuyer sur \"Ok\", soit décider d'en créer un. Pour créer un deck il faut uniquement un nom et une description au deck. \n Une fois le deck choisi ou créé tu es amené sur la page de modification de Deck. sur cette page s'inscrit le nom de ton deck, sa description, que tu peux modifier et sauvegarder en appuyant sur le bouton \"Enregistrer\". Sinon tu peux aussi selectionner une carte et appuyer sur le bouton \"Modifier\". Ici tu pourras changer ce que tu veux de la carte, clique ensuite sur enregistrer pour la sauvegarder. \n \n \n Si tu décides de cliquer sur le bouton pour t'entraîner lorsque tu es dans l'accueil, tu vas arriver face a une liste de deck, tous ces decks contiennent des carte. Choisis celui sur lequel tu veux t'entraîner. Tu as le maintenant le choix entre quatre mode différents. \n Le mode Infini : Les cartes du deck arrivent, aléatoirement (mais plus tu as eu la carte moins tu as de chance de la voir), sans limite de temps ou de carte. L'idée est de s'entraîner sur le deck entier tranquillement. \n Le mode Examen : Les cartes arrivent dans un ordre aléatoire, mais tu as un temps limité pour répondre. Pas d'indice possible. C'est de l'auto évaluation, on te fait confiance pour être honnête ! \n Le mode Rush : Les cartes arrivent dans un ordre aléatoire, sans limite. Tu as une limite de temps par contre. Le but est de répondre le plus vite et le plus juste possible au plus de question possible dans le temps imparti. \n Le mode Classique : Tu peux décider de combien de carte tu veux avoir, et de combien de temps tu as pour répondre. Tu peux aussi choisir si tu veux des indices ou pas. C'est le mode le plus classique. \n \n Une fois un entraînement fini une fenêtre s'affiche. De cette fenêtre tu peux soit rejouer soit aller voir les statistiques de ton profil. \n \n \n Si tu décides de cliquer sur le bouton des statistique tu peux voir tes statistiques. Tu as le choix entre les statistiques de tous les decks, ou les statistiques d'un deck en particulier. Il y a plusieurs information que je te laisserai découvrir par toi même. \n Il y a aussi la possibilité de voir les statistique sous forme de graphe, sur l'ensemble des decks ou non. A toi de voir la vue que tu préfère. \n \n Si tu as un problème quelconque sur le jeu, que ce soit sur la création de Deck ou autre chose n'hésite pas a nous contacter par mail : ankwizzlet@gmail.com. \n \n \n Amuse toi bien sur notre application !");
        
        alert.showAndWait();
        return ;
    }


}
