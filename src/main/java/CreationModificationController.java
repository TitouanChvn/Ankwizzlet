import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import javafx.stage.FileChooser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class CreationModificationController {
    
    @FXML
    TextArea DescriptionDeck;
    @FXML 
    TextField NomDeck;
    MyDecks MyDecks ;
    @FXML
    MenuItem supp;

    @FXML
    private void initialize() {
        supp.setDisable(true);
    }

    public CreationModificationController() {
    }

    public CreationModificationController(MyDecks MyDecks) {
        this.MyDecks = MyDecks;
    }

    //Aller à la page précédente
    @FXML
    public void previousPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Quitter l'application
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
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

    //Créer un nouveau deck
    @FXML
    public void validation(ActionEvent event) throws IOException {
        //On récupère le nom et la description du deck
        String nom = NomDeck.getText();
        String description = DescriptionDeck.getText();

        //On crée le deck
        DeckOfFlashcards deck = new DeckOfFlashcards();
        deck.setName(nom);
        deck.setDescription(description); 
        deck.setId(MyDecks.getFreeId());
        MyDecks.addDeck(deck);
        MyDecks.setActualDeck(deck);

        //On ouvre la page de modification du deck
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new DeckModificationController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("DeckModification.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Aller à la page d'accueil
    @FXML
    public void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    //Créer un deck :
    @FXML
    private void saveDeck(ActionEvent event) throws IOException {
        
        //On récupère le nom et la description du deck
        String nom = NomDeck.getText();
        String description = DescriptionDeck.getText();

        //On crée le deck
        DeckOfFlashcards deck = new DeckOfFlashcards();
        deck.setName(nom);
        deck.setDescription(description); 
        deck.setId(MyDecks.getFreeId());
        MyDecks.addDeck(deck);
        MyDecks.setActualDeck(deck);

    }

    //Importer un deck
    @FXML
    public void importer(ActionEvent event) throws IOException {

        //On ouvre une fenêtre pour choisir le fichier à importer
        FileChooser file = new FileChooser();
        File selectedFile = file.showOpenDialog(null);

        //On vérifie que le fichier est bien un fichier non nul et qu'il est bien au format JSON
        if (selectedFile != null && selectedFile.getName().endsWith(".json")) {
            File ff = new File(selectedFile.getAbsolutePath());

            //On parse le fichier JSON
            JSONParser jsonParser = new JSONParser();
            try (FileReader reader = new FileReader(ff))
            {
                //Read JSON file
                Object obj = jsonParser.parse(reader);

                JSONObject deck = (JSONObject) obj;
                
                String nom = (String) deck.get("nom");  
                String description = (String) deck.get("description");   

                //On crée le deck
                DeckOfFlashcards deckOfFlashcards = new DeckOfFlashcards(nom, description, MyDecks.getFreeId());

                JSONArray flashcardsList = (JSONArray) deck.get("flashcards");
                for (Object f : flashcardsList) {
                JSONObject flashcard = (JSONObject) f;
                parseCard(flashcard, deckOfFlashcards);
                }

                MyDecks.addDeck(deckOfFlashcards);
                MyDecks.setActualDeck(deckOfFlashcards);
                NomDeck.setText(nom);
                DescriptionDeck.setText(description);

                reader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //On affiche la page de modification du deck
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new DeckModificationController(MyDecks));
            Parent root = (Parent) loader.load(getClass().getResource("DeckModification.fxml").openStream());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

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


    //On récupère les données de la carte
    private void parseCard(JSONObject flashcard, DeckOfFlashcards deck) {
        //On récupère les données de la carte
        String q = (String) flashcard.get("question");
        String r = (String) flashcard.get("reponse");
        String i = (String) flashcard.get("indice");
        String e = (String) flashcard.get("explication");
        String ii = (String) flashcard.get("idC");
        String nJ = (String) flashcard.get("nbJoue");
        String nR = (String) flashcard.get("nbBonnesReponses");
    
        //On crée la carte
        Flashcard f = new Flashcard(q, r, i, e, Integer.parseInt(ii), Integer.parseInt(nJ), Integer.parseInt(nR));
        //On l'ajoute au deck
        deck.addFlashcard(f);
      }
    
}
