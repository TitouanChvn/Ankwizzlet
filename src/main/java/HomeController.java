import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class HomeController { // implements MyObserver {

    private MyDecks MyDecks;
    public HomeController() {
    }
    public HomeController(MyDecks myDecks) {
        MyDecks = myDecks;
    }

    //Aller sur la page de selection d'un deck pour l'entrainement
    @FXML
    public void SelectionApprView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new SelectionApprController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Selection_appr.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    //Aller sur la page de statistiques
    @FXML
    public void StatView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new StatsController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Statistiques.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Aller sur la page de creation d'un deck
    @FXML
    public void creationView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new SelectionModificationController(MyDecks));
        Parent root = (Parent) loader.load(getClass().getResource("SelectionModification.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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

}