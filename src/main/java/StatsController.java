import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;

public class StatsController extends GraphiqueController {

    MyDecks MyDecks;
    @FXML
    private Label nbDeckVus;
    @FXML
    private Label nbDeckFinis;
    @FXML
    private Label nbCardViewed;
    @FXML
    private Label nbCardinDeck;
    @FXML
    private Label nbCardLearned;
    @FXML
    private ComboBox deck_choisi;
    @FXML
    private PieChart piechartDeck;

    public StatsController() {
    }

    public StatsController(MyDecks Mydecks) {
        this.MyDecks = Mydecks;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nbDeckVus.setText(Integer.toString(MyDecks.nbDeckTotal())); // Nombre de decks
        nbDeckFinis.setText(Integer.toString(MyDecks.nbDeckViewed())); // Nombre de decks vus

        nbCardViewed.setText(Integer.toString(MyDecks.nbCardViewed() - MyDecks.nbCardLearned())); // Nombre de cartes vues
        nbCardinDeck.setText(Integer.toString(MyDecks.nbCardTotal())); // Nombre de cartes au total
        nbCardLearned.setText(Integer.toString(MyDecks.nbCardLearned())); // Nombre de cartes apprises au total

        ArrayList<String> decksName = new ArrayList<String>(); // on récupère le nom de chaque deck dans une liste
        for (int i = 0; i < MyDecks.getDecks().size(); i++) {
            decksName.add(MyDecks.getDecks().get(i).getName());
        }
        deck_choisi.getItems().add("Tous les decks"); // on ajoute le choix "allDecks" qui permet d'afficher les stats de tous
                                                // les decks
        deck_choisi.getItems().addAll(decksName);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Cartes non vues", MyDecks.nbCardTotal() - MyDecks.nbCardViewed()), // on récupère les
                                                                                                      // données de tous
                                                                                                      // les
                // decks
                new PieChart.Data("Cartes vues", MyDecks.nbCardViewed() - MyDecks.nbCardLearned()),
                new PieChart.Data("Cartes apprises", MyDecks.nbCardLearned()));
        piechartDeck.setData(pieChartData);
    }

    @FXML
    public void whoWeAre() {
        // fait apparaitre une alerte

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Qui sommes nous ?");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(
                "Et bonjour à toi \n \n Qui nous sommes ? Nous sommes quatres étudiants de TELECOM Nancy, une école d'ingénieur spécialisée dans l'informatique. Tu peux retrouver nos prénoms sur la page d'accueil. Nous avons developpé ce jeu dans le cadre d'un projet sur une semaine que notre école nous à demandé de réaliser. \n Tu peux nous contacter via le mail suivant : ankwizzlet@gmail.com. Que ce soit pour une question, un conseil, une critique ou de l'encouragement nous sommes toujours heureux de recevoir des mails de la part de nos joueurs. \n \n Amuse toi bien sur notre application !");

        alert.showAndWait();
        return;

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
        alert.setContentText(
                "Comment jouer ? Sur le menu d'accueil tu as trois boutons, un qui va te permettre de créer ou de modifier un deck, un autre qui va te permettre de t'entraîner sur un deck et un dernier qui va te montrer tes statistiques. \n \n Quand tu cliques sur le bouton pour créer ou modifier un deck tu vas avoir le choix entre plusieurs deck déja existants, placés dans une liste. Tu peux soit en selectionner un et appuyer sur \"Ok\", soit décider d'en créer un. Pour créer un deck il faut uniquement un nom et une description au deck. \n Une fois le deck choisi ou créé tu es amené sur la page de modification de Deck. sur cette page s'inscrit le nom de ton deck, sa description, que tu peux modifier et sauvegarder en appuyant sur le bouton \"Enregistrer\". Sinon tu peux aussi selectionner une carte et appuyer sur le bouton \"Modifier\". Ici tu pourras changer ce que tu veux de la carte, clique ensuite sur enregistrer pour la sauvegarder. \n \n \n Si tu décides de cliquer sur le bouton pour t'entraîner lorsque tu es dans l'accueil, tu vas arriver face a une liste de deck, tous ces decks contiennent des carte. Choisis celui sur lequel tu veux t'entraîner. Tu as le maintenant le choix entre quatre mode différents. \n Le mode Infini : Les cartes du deck arrivent, aléatoirement (mais plus tu as eu la carte moins tu as de chance de la voir), sans limite de temps ou de carte. L'idée est de s'entraîner sur le deck entier tranquillement. \n Le mode Examen : Les cartes arrivent dans un ordre aléatoire, mais tu as un temps limité pour répondre. Pas d'indice possible. C'est de l'auto évaluation, on te fait confiance pour être honnête ! \n Le mode Rush : Les cartes arrivent dans un ordre aléatoire, sans limite. Tu as une limite de temps par contre. Le but est de répondre le plus vite et le plus juste possible au plus de question possible dans le temps imparti. \n Le mode Classique : Tu peux décider de combien de carte tu veux avoir, et de combien de temps tu as pour répondre. Tu peux aussi choisir si tu veux des indices ou pas. C'est le mode le plus classique. \n \n Une fois un entraînement fini une fenêtre s'affiche. De cette fenêtre tu peux soit rejouer soit aller voir les statistiques de ton profil. \n \n \n Si tu décides de cliquer sur le bouton des statistique tu peux voir tes statistiques. Tu as le choix entre les statistiques de tous les decks, ou les statistiques d'un deck en particulier. Il y a plusieurs information que je te laisserai découvrir par toi même. \n Il y a aussi la possibilité de voir les statistique sous forme de graphe, sur l'ensemble des decks ou non. A toi de voir la vue que tu préfère. \n \n Si tu as un problème quelconque sur le jeu, que ce soit sur la création de Deck ou autre chose n'hésite pas a nous contacter par mail : ankwizzlet@gmail.com. \n \n \n Amuse toi bien sur notre application !");

        alert.showAndWait();
        return;

    }

    @FXML
    public void deckSelected(ActionEvent event) {

        DeckOfFlashcards deck_picked = MyDecks
                .getDeckByName(deck_choisi.getSelectionModel().getSelectedItem().toString()); // on récupère le deck
                                                                                              // choisi grâce à son id
                                                                                              // et la liste precédente

        if (deck_choisi.getSelectionModel().getSelectedItem().toString() == "Tous les decks") {
            nbCardViewed.setText(Integer.toString(MyDecks.nbCardViewed() - MyDecks.nbCardLearned()));
            nbCardinDeck.setText(Integer.toString(MyDecks.nbCardTotal()));
            nbCardLearned.setText(Integer.toString(MyDecks.nbCardLearned()));

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Cartes non vues", MyDecks.nbCardTotal() - MyDecks.nbCardViewed()),
                    // les decks
                    new PieChart.Data("Cartes vues", MyDecks.nbCardViewed() - MyDecks.nbCardLearned()),
                    new PieChart.Data("Cartes apprises", MyDecks.nbCardLearned()));
            piechartDeck.setData(pieChartData);
        }

        else if (deck_choisi != null) {
            nbCardViewed.setText(Integer
                    .toString(MyDecks.nbCardinDeckViewed(deck_picked) - MyDecks.nbCardinDeckLearned(deck_picked)));
            nbCardinDeck.setText(Integer.toString(MyDecks.nbCardinDeckTotal(deck_picked)));
            nbCardLearned.setText(Integer.toString(MyDecks.nbCardinDeckLearned(deck_picked)));

            if (deck_picked.getNbCardInDeck() == 0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Alerte");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Ce deck est vide");

                alert.showAndWait();
            } else {
                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Cartes non vues", MyDecks.nbCardinDeckTotal(deck_picked) - MyDecks.nbCardinDeckViewed(deck_picked)),
                new PieChart.Data("Cartes vues", MyDecks.nbCardinDeckViewed(deck_picked) - MyDecks.nbCardinDeckLearned(deck_picked)),
                new PieChart.Data("Cartes apprises", MyDecks.nbCardinDeckLearned(deck_picked))
                );
                piechartDeck.setData(pieChartData);

            }
        }
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void previousPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void viewGraphics(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new GraphiqueController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Graphique.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void goToHomeIcon(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
