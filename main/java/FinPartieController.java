import java.io.IOException;
import javafx.scene.control.MenuItem;
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
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.scene.Node;

public class FinPartieController { // implements MyObserver {

    private MyDecks myDecks ;

    private int nb_cartes_tot ; //nombre de cartes a jouer
    private int nb_cartes_reussies = 0 ; //nombre de cartes reussies
    private int time ; //temps par question (ou total si mode rush) en secondes
    private int mode ; //0 = pas de mode, 1 = infini, 2 = examen ,3 = rush, 4 = classique
    private boolean indice ; //true = possible d'afficher indice , false = impossible
    private boolean explications ; //true = afficher explications , false = pas d'affichage
    @FXML   
    Label stat ;
    @FXML
    Button ButtonRejouer ;
    @FXML
    Button ButtonStats ;
    @FXML
    MenuBar menuBar;


    public void initialize() {
        stat.setWrapText(true);
        String currentMode;
        //affichage des statistiques en fonction du mode
        switch(mode) {
            case 0:
                currentMode = "pas de mode";
                break;
            case 1:
                currentMode = "infini";
                stat.setText(" Vous avez réussi " + nb_cartes_reussies + "/" + nb_cartes_tot + " cartes \n Vous avez joué en mode " + currentMode);
                break;
            case 2:
                currentMode = "examen";
                stat.setText(" Vous avez réussi " + nb_cartes_reussies + "/" + nb_cartes_tot + " cartes \n Vous aviez " + time + " secondes par question \n Vous avez joué en mode " + currentMode);
                break;
            case 3:
                currentMode = "rush";
                stat.setText("Bravo vous avez réussi " + nb_cartes_reussies  + " cartes \n Vous avez eu " + time + " secondes pour répondre à toutes les questions \n Vous avez joué en mode " + currentMode);
                break;
            case 4:
                currentMode = "classique";
                stat.setText(" Vous avez réussi " + nb_cartes_reussies + "/" + nb_cartes_tot + " cartes \n Vous aviez " + time + " secondes par question \n Vous avez joué en mode " + currentMode);
                break;
            default:
                currentMode = "pas de mode";
                break;
        }
    }

    public FinPartieController() {
        
    }


    public FinPartieController(MyDecks myDecks,int nb_cartes,int nb_cartes_reussies, int time, int mode, boolean indice, boolean explications) {
        this.myDecks = myDecks;
        this.nb_cartes_tot = nb_cartes;
        this.nb_cartes_reussies = nb_cartes_reussies;
        this.time = time;
        this.mode = mode;
        this.indice = indice;
        this.explications = explications;
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


    //Rejouer avec les mêmes paramètres que l'entraînement précédent
    public void ButtonRejouerClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loader.setController(new Cartes_apprController(myDecks,nb_cartes_tot,time,mode,indice,explications,mainWindow));
        Parent root = loader.load(getClass().getResource("Cartes_appr.fxml").openStream());
        mainWindow.setScene(new Scene(root));
        mainWindow.show();
    }

    //Aller sur la page de statistiques
    public void ButtonStatsClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new StatsController(myDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Statistiques.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Aller sur la page de sélection de deck pour l'entraînement
    public void ButtonChangeDeckClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new SelectionApprController(myDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Selection_appr.fxml").openStream());
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
        loader.setController(new HomeController(myDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Aller sur la page d'accueil
    @FXML
    public void GoToAccueil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(myDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}