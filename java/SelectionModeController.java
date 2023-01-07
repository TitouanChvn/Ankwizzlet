import java.io.IOException;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SelectionModeController {
    
    private MyDecks myDecks;

    private int mode_choisi;        // 0 = pas de mode  1 = infini 2 = examen 3 = rush 4 = classique
    private int nb_cartes;          // nombre de cartes dans le deck
    private int temps;              // temps choisi

    public SelectionModeController() {
        mode_choisi = 0;
        nb_cartes = 0;
        temps = 0;
    }

    public SelectionModeController(MyDecks myDecks) {
        this.myDecks = myDecks;
        mode_choisi = 0;
        nb_cartes = 0;
        temps = 0;
    }


    @FXML
    MenuButton menuButtonMode;

    @FXML
    CheckBox CheckBoxIndices;
    @FXML
    CheckBox CheckBoxExplications;
    @FXML
    ComboBox ComboBoxNbCartes;
    @FXML
    ComboBox ComboBoxTemps;
    @FXML
    Label LabelDescriptionMode;


    //Initialisation de la page
    @FXML
    public void initialize() {
        // add items to the choice box
        ComboBoxNbCartes.getItems().addAll("5", "10", "15", "20", "25", "30", "35", "40", "45", "50");
        ComboBoxTemps.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    }

    //Si le mode infini est choisi, on peut choisir les indices et explications mais pas le temps et le nombre de cartes
    @FXML
    public void InfiniSelected(ActionEvent event) throws IOException {
        mode_choisi = 1;
        menuButtonMode.setText("Infini");
        CheckBoxExplications.setDisable(false);
        CheckBoxIndices.setDisable(false);
        ComboBoxNbCartes.setDisable(true);
        ComboBoxTemps.setDisable(true);
        LabelDescriptionMode.setWrapText(true);
        LabelDescriptionMode.setText("Jouez autant de cartes que vous le souhaitez !");

    }

    //Si le mode examen est choisi, on peut choisir le nombre de cartes et le temps mais pas les indices et explications
    @FXML
    public void ExamenSelected(ActionEvent event) throws IOException {
        mode_choisi = 2;
        menuButtonMode.setText("Examen");
        CheckBoxExplications.setDisable(true);
        CheckBoxExplications.setSelected(false);
        CheckBoxIndices.setDisable(true);
        CheckBoxIndices.setSelected(false);
        ComboBoxNbCartes.setDisable(false);
        ComboBoxTemps.setDisable(false);

        //Les valeurs par défaut sont 20 cartes et 10 secondes
        ComboBoxNbCartes.setValue("20");
        ComboBoxTemps.setValue("10");
        LabelDescriptionMode.setWrapText(true);
        LabelDescriptionMode.setText("Testez vos connaissances avec un temps limité pour chaque carte !");
    }

    //Si le mode rush est choisi, on peut choisir le temps mais pas le nombre de cartes et les indices et explications
    @FXML
    public void RushSelected(ActionEvent event) throws IOException {
        mode_choisi = 3;
        menuButtonMode.setText("Rush");
        CheckBoxExplications.setDisable(false);
        CheckBoxIndices.setDisable(false);
        ComboBoxNbCartes.setDisable(true);
        ComboBoxTemps.setDisable(false);

        ComboBoxTemps.getItems().clear();
        ComboBoxTemps.getItems().addAll("10","20","30","60","180");

        //Les valeurs par défaut sont 30 secondes
        ComboBoxTemps.setValue("30");
        LabelDescriptionMode.setWrapText(true);
        LabelDescriptionMode.setText("Jouez le plus de cartes possibles en un temps limité !");
    }

    //Si le mode classique est choisi, on peut choisir le nombre de cartes et le temps mais pas les indices et explications
    @FXML
    public void ClassicSelected(ActionEvent event) throws IOException {
        mode_choisi = 4;
        menuButtonMode.setText("Classique");
        CheckBoxExplications.setDisable(false);
        CheckBoxIndices.setDisable(false);
        ComboBoxNbCartes.setDisable(false);
        ComboBoxTemps.setDisable(false);

        //Les valeurs par défaut sont 20 cartes et 10 secondes
        ComboBoxNbCartes.setValue("20");
        ComboBoxTemps.setValue("10");
        LabelDescriptionMode.setWrapText(true);
        LabelDescriptionMode.setText("Apprenez en jouant avec un temps limité pour chaque carte !"); 
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

    //On valide le choix du mode
    @FXML
    public void ButtonOkClicked(ActionEvent event) throws IOException {
        if (mode_choisi == 0) {
            // fait apparaitre une alerte
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alerte");
            
            // Header Text: null
            alert.setHeaderText(null);
            alert.setContentText("Vous devez choisir un mode de jeu !");
            
            alert.showAndWait();
            return ;
        }
        if (mode_choisi == 1) {
            nb_cartes = 0; // C'est le mode infini
            temps = 0;
        }
        if (mode_choisi == 2) {
            // C'est le mode examen
            nb_cartes = Integer.parseInt((String) ComboBoxNbCartes.getValue());
            temps = Integer.parseInt((String) ComboBoxTemps.getValue());
        }
        if (mode_choisi == 3) {
            nb_cartes = 0; // C'est le mode rush
            temps = Integer.parseInt((String) ComboBoxTemps.getValue());
        }
        if (mode_choisi == 4) {
            // C'est le mode classique
            nb_cartes = Integer.parseInt((String) ComboBoxNbCartes.getValue());
            temps = Integer.parseInt((String) ComboBoxTemps.getValue());
        }

        if (mode_choisi != 0) {
            // On passe à la page suivante
            boolean indice_checked = CheckBoxIndices.isSelected();
            boolean explication_checked = CheckBoxExplications.isSelected();

            FXMLLoader loader = new FXMLLoader();
            Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loader.setController(new Cartes_apprController(myDecks,nb_cartes,temps,mode_choisi,indice_checked,explication_checked,mainWindow));
            Parent root = loader.load(getClass().getResource("Cartes_appr.fxml").openStream());
            
            mainWindow.setScene(new Scene(root));
        }

    }

    //Quitte l'application
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    //Aller à la page d'accueil
    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(myDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Aller à la page précédente
    @FXML
    public void PreviousPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new SelectionApprController(myDecks));
        Parent root = loader.load(getClass().getResource("Selection_appr.fxml").openStream());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
