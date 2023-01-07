import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileWriter;
import com.google.gson.*;

public class SelectionModificationController{
    @FXML 
    private ListView<String> listView ; // ça c'est la liste qu'on va afficher 
    MyDecks MyDecks ; 
    @FXML
    private Label description;

    public SelectionModificationController() {
    }

    public SelectionModificationController(MyDecks MyDecks) {
        this.MyDecks = MyDecks;
    }

    //Initialisation de la page
    @FXML 
    public void initialize() {
        description.setWrapText(true);
        List<String> normalList = MyDecks.getDecksTitles(MyDecks); // on récupère les noms des deck dans une liste normale
        ObservableList<String> observableList = FXCollections.observableArrayList(normalList); // on met la liste normale dans une liste observable
        
        listView.setItems(observableList); // on met la liste observable dans la liste qu'on affiche
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // on peut sélectionner un seul élément

        listView.setOnMouseClicked(event -> { // on va afficher dans le label la description du deck sélectionné
            String selectedDeck = listView.getSelectionModel().getSelectedItem();
            description.setText(MyDecks.getDeckByName(selectedDeck).getDescription());
            MyDecks.setActualDeck(MyDecks.getDeckByName(selectedDeck));
        });
    }

    //Quitter l'application
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
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
    
    //Créer un nouveau deck
    @FXML
    public void creation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new CreationModificationController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("CreationModification.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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
    
    //Modifier le deck sélectionné
    @FXML
    public void validation(ActionEvent event) throws IOException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // faire quelque chose avec l'élément sélectionné
        }
        else {
            // fait apparaitre une alerte
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alerte");
            
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Vous devez choisir un deck pour le modifier !");
            
            alert.showAndWait();
            return ;
        }
        int selectedIndex = listView.getSelectionModel().getSelectedIndex(); 
        List<Integer> ids = MyDecks.getDecksId(MyDecks); // on récupère les ids des deck dans une liste normale

        if (selectedIndex != -1) {
            int id = ids.get(selectedIndex);
            MyDecks.ActualDeck = MyDecks.getDeckById(id);
            // on passe l'actual deck à celui qui a été sélectionné

        } 

        FXMLLoader loader = new FXMLLoader();
        loader.setController(new DeckModificationController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("DeckModification.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Exporter le deck sélectionné
    @FXML
    public void exporter(ActionEvent event) {
        // exporter un élément

        //Ouvrir une fenêtre pour enregistrer le fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Deck");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JSON", "*.json")   
        );
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            saveDeck(file.getAbsolutePath());
        }
    }

    //Transformer le deck en fichier JSON
    public void saveDeck(String file) {
        DeckOfFlashcards deckk = MyDecks.getActualDeck();
        String idDeck = Integer.toString(deckk.getId());
        String nom = deckk.getName();
        String description = deckk.getDescription();
  
        //Création du fichier JSON
        JSONObject d = new JSONObject();
        try (FileWriter writer = new FileWriter(file + ".json")) {
  
            JSONArray flashcardsList = new JSONArray();

            //On ajout les cartes à flashcardsList
            for (Flashcard card : deckk.deck) {
                JSONObject flashcard = new JSONObject();
                String q = (String) card.getQuestion();
                String r = (String) card.getAnswer();
                String i = (String) card.getIndice();
                String e = (String) card.getExplication();
                String ii = Integer.toString(card.getId());
                String nJ = Integer.toString(card.getPlayed());
                String nR = Integer.toString(card.getWon());
  
                flashcard.put("question", q);
                flashcard.put("reponse", r);
                flashcard.put("indice", i);
                flashcard.put("explication", e);
                flashcard.put("idC", ii);
                flashcard.put("nbJoue", nJ);
                flashcard.put("nbBonnesReponses", nR);
                
                flashcardsList.add(flashcard);
              }
  
            //On ajoute les informations du deck
            d.put("id", idDeck);
            d.put("nom", nom);
            d.put("description", description);
            d.put("flashcards", flashcardsList);
  
            //On écrit le fichier JSON
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(d);
            writer.write(jsonString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

}

