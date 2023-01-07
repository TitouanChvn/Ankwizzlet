import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class Cartes_apprController { // implements MyObserver {

    private MyDecks myDecks;
    private boolean CardRectoFace;
    private Stage currenStage;

    private int cartes_jouees = 0; // nombre de cartes jouees
    private int nb_cartes_tot; // nombre de cartes a jouer
    private int time; // temps par question (ou total si mode rush) en secondes
    private int mode; // 0 = pas de mode, 1 = infini 2 examen , 3 = rush, 4 = classique
    private boolean indice; // true = possible d'afficher indice , false = impossible
    private boolean explications; // true = afficher explications , false = pas d'affichage

    private int nb_cartes_reussies = 0; // nombre de cartes reussies

    private Timer myTimer;
    public ArrayList<Flashcard> deckCopy = new ArrayList<Flashcard>(); // mémoire des carte a ne pas rejouer

    @FXML
    AnchorPane anchorPane;

    @FXML
    Button ButtonShowAnswer;
    @FXML
    Button ButtonCorrect;
    @FXML
    Button ButtonIncorrect;
    @FXML
    Button ButtonCancel;
    @FXML
    Label LabelExplications;
    @FXML
    Label LabelExplicationsContenu;
    @FXML
    Label LabelAnswerText;
    @FXML
    CheckBox CheckBoxIndice;
    @FXML
    Label LabelIndice;
    @FXML
    Label LabelTitreIndice;
    @FXML
    Label LabelQuestion;
    @FXML
    Label LabelTitreAnswer;
    @FXML
    Button ButtonFinPartieInfinie;
    @FXML
    ProgressBar ProgressBarTimer;

    @FXML
    MenuBar menuBar;
    

    class Helper extends TimerTask {
        public void run() {
            cartes_jouees++;
            flipCard();
            myTimer.cancel();
            myTimer.purge();
        }
    }

    class Helper2 extends TimerTask {
        public void run() {
            // update de la barre de progression
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // get progress
                    int progress = (int) (ProgressBarTimer.getProgress() * 255);
                    int progress2 = 255 - progress;
                    // convert to hex
                    String hex = Integer.toHexString(progress);
                    String hex2 = Integer.toHexString(progress2);

                    if (hex.length() == 1) {
                        hex = "0" + hex;
                    }
                    if (hex2.length() == 1) {
                        hex2 = "0" + hex2;
                    }

                    try {
                        ProgressBarTimer.setStyle("-fx-accent: #" + hex + hex2 + "00");
                    } catch (Exception e) {
                        ProgressBarTimer.setStyle("-fx-accent: #ff0000");
                    }

                    // 10sur le temps parcequ'on a mis 10 en période a la task 2 +0.5 pour compenser le temps de traitement
                    ProgressBarTimer.setProgress(ProgressBarTimer.getProgress() + 10.5 / (time * 1000)); 
                }
            });
        }
    }

    class Helper3 extends TimerTask {
        public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    finPartie();
                }
            });

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

    // Méthode pour afficher la carte 
    public Cartes_apprController() {
        CardRectoFace = true;
    }

    public Cartes_apprController(MyDecks myDecks, int nb_cartes, int time, int mode, boolean indice,
        boolean explications, Stage theStage) {
        this.myDecks = myDecks;
        CardRectoFace = true;
        this.nb_cartes_tot = nb_cartes;
        this.time = time;
        this.mode = mode;
        this.indice = indice;
        this.explications = explications;
        this.currenStage = theStage;
    }

    
    //Initialisation de la page
    @FXML
    public void initialize() {

        LabelExplicationsContenu.setWrapText(true);

        if (mode == 1 || mode == 3) {
            nb_cartes_tot = 10; // je mets 10 mais ça va augumenter
        }

        currenStage.setOnCloseRequest(e -> { // On défini une nouvelle fermeture de fenêtre
            try {
                myTimer.cancel();
                myTimer.purge();
            } catch (Exception ex) { // on catch au cas ou il n'y ai plus de timer (parcequ'on a changé de fenetre par exemple)
            }
        });

        if (myDecks.ActualDeck.deck.size()<=5){
            myDecks.ActualFlashcard=myDecks.ActualDeck.DealRealRandomFlashcard();
        }
        else{
        myDecks.ActualFlashcard = myDecks.ActualDeck.dealRandomFlashcard(deckCopy);
        }  
        update();
        if (mode != 1) {
            startTimer();
        }
    }

    //On lance le timer
    public void startTimer() {
        ProgressBarTimer.setProgress(0);
        myTimer = new Timer();
        TimerTask task = new Helper();
        TimerTask task2 = new Helper2();
        TimerTask task3 = new Helper3();
        myTimer.purge();
        if (mode == 3) {
            myTimer.schedule(task3, 1000 * time, 1000 * time); // la tache 3 c'est finir le jeu a la fin du timer
        }
        myTimer.schedule(task2, 10, 10); // la tache 2 c'est update la barre de progression
        if (mode == 2 || mode == 4) {
            myTimer.schedule(task, 1000 * time, 1000 * time); // la tache 1 c'est flipper la carte a la fin du timer pour les modes exams et classique
        }
    }

    //On flip la carte
    public void flipCard() {
        CardRectoFace = !CardRectoFace;
        update();
    }

    
    @FXML
    public void ButtonShowAnswerClicked(ActionEvent event) {
        if (mode == 2 || mode == 4) {
            myTimer.cancel();
            myTimer.purge();
        }
        flipCard();

    }

    //Mis a jour de la page
    public void update() {

        // On met a jour les labels
        LabelQuestion.setText(myDecks.ActualFlashcard.getQuestion());
        LabelAnswerText.setText(myDecks.ActualFlashcard.getAnswer());
        LabelExplicationsContenu.setText(myDecks.ActualFlashcard.getExplication());
        LabelIndice.setText(myDecks.ActualFlashcard.getIndice());

        // On met a jour les boutons et les labels en fonction de la carte
        ProgressBarTimer.setVisible((CardRectoFace && mode != 1) || mode == 3);
        ButtonShowAnswer.setVisible(CardRectoFace);
        ButtonCorrect.setVisible(!CardRectoFace);
        ButtonIncorrect.setVisible(!CardRectoFace);
        ButtonCancel.setVisible(!CardRectoFace);
        LabelExplications.setVisible(!CardRectoFace && explications);
        LabelExplicationsContenu.setVisible(!CardRectoFace && explications);
        LabelAnswerText.setVisible(!CardRectoFace);
        LabelIndice.setVisible(false);
        CheckBoxIndice.setVisible(CardRectoFace && indice);
        CheckBoxIndice.setSelected(!CardRectoFace);
        LabelTitreIndice.setVisible(CardRectoFace && indice);
        LabelTitreAnswer.setVisible(!CardRectoFace);
        ButtonFinPartieInfinie.setVisible(mode == 1 && !CardRectoFace);
    }

    //Fin de partie
    public void finPartie() {
        
        if (mode == 3) {
            // On annule le timer
            myTimer.cancel();
            myTimer.purge();
        }

        // On affiche la fenetre de fin de partie
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FinPartie.fxml"));
            loader.setController(new FinPartieController(myDecks, nb_cartes_tot, nb_cartes_reussies, time, mode, indice,
                    explications));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            currenStage.close();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //On appuie sur le bouton correct
    @FXML
    public void ButtonCorrectAnswerClicked(ActionEvent event) {
        
        //Mise à jour des stats
        myDecks.ActualFlashcard.setWon(myDecks.ActualFlashcard.getWon() + 1);
        myDecks.ActualFlashcard.setPlayed(myDecks.ActualFlashcard.getPlayed() + 1);

        if (mode == 4 && mode == 3) {
            myDecks.ActualDeck.addGame();
            myDecks.ActualFlashcard=myDecks.ActualDeck.dealCorrectFlashcard();
        }
        else{
        if (myDecks.ActualDeck.deck.size()<=5){
            myDecks.ActualFlashcard=myDecks.ActualDeck.DealRealRandomFlashcard();
        }
        else{
        myDecks.ActualFlashcard = myDecks.ActualDeck.dealRandomFlashcard(deckCopy);
        }       
        }
        if (mode == 1 || mode == 3) {
            // On augmente le nombre de cartes totales
            nb_cartes_tot++;
        }
        // On augmente le nombre de cartes jouées et réussies
        cartes_jouees++;
        nb_cartes_reussies++;
        if (cartes_jouees == nb_cartes_tot) {
            // On fini la partie
            finPartie();
        } else {
            flipCard();
            if (mode == 2 || mode == 4) {
                // On lance le timer
                startTimer();
            }
        }
    }

    //On appuie sur le bouton fin de partie infinie
    @FXML
    public void FinirPartieInfinie(ActionEvent event) {
        finPartie();
    }

    //On appuie sur le bouton incorrect
    @FXML
    public void ButtonIncorrectAnswerClicked(ActionEvent event) {
        //Mise à jour des stats
        myDecks.ActualFlashcard.setPlayed(myDecks.ActualFlashcard.getPlayed() + 1);
        myDecks.ActualDeck.addGame();
        if (mode == 4 && mode == 3){
            myDecks.ActualDeck.addGame();
            myDecks.ActualFlashcard=myDecks.ActualDeck.dealCorrectFlashcard();
        }
        else{
            if (myDecks.ActualDeck.deck.size()<=5){
                myDecks.ActualFlashcard=myDecks.ActualDeck.DealRealRandomFlashcard();
            }
            else{
            myDecks.ActualFlashcard = myDecks.ActualDeck.dealRandomFlashcard(deckCopy);
            }  
        }
        if (mode == 1 || mode == 3) {
            // On augmente le nombre de cartes totales
            nb_cartes_tot++;
        }
        // On augmente le nombre de cartes jouées
        cartes_jouees++;
        if (cartes_jouees == nb_cartes_tot) {
            // On fini la partie
            finPartie();
        } else {
            flipCard();
            if (mode == 2 || mode == 4) {
                // On lance le timer
                startTimer();
            }
        }
    }

    //On appuie sur le bouton annuler
    @FXML
    public void ButtonCancelClicked(ActionEvent event) {   
        if (mode == 4 && mode == 3){
            myDecks.ActualDeck.addGame();
            myDecks.ActualFlashcard=myDecks.ActualDeck.dealCorrectFlashcard();
        }
        else{
            if (myDecks.ActualDeck.deck.size()<=5){
                myDecks.ActualFlashcard=myDecks.ActualDeck.DealRealRandomFlashcard();
            }
            else{
            myDecks.ActualFlashcard = myDecks.ActualDeck.dealRandomFlashcard(deckCopy);
            }  
        }
        flipCard();
        if (mode == 2 || mode == 4) {
            // On lance le timer
            startTimer();
        }
    }


    @FXML
    public void IndiceClicked(ActionEvent event) {
        if (CheckBoxIndice.isSelected()) {
            // On affiche l'indice
            LabelIndice.setWrapText(true);
            LabelIndice.setVisible(true);
        } else {
            // On cache l'indice
            LabelIndice.setVisible(false);
        }
    }

    //Quitter l'application
    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
        if (mode != 1) {
            // On annule le timer
            myTimer.cancel();
            myTimer.purge();
        }
    }

    //Aller à la page d'accueil
    @FXML
    public void goToHome(ActionEvent event) throws IOException {
        if (mode != 1) {
            myTimer.cancel();
            myTimer.purge();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(myDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Aller à la page d'accueil
    @FXML
    public void GoToAccueil(ActionEvent event) throws IOException {
        if (mode != 1) {
            // On annule le timer
            myTimer.cancel();
            myTimer.purge();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new HomeController(myDecks));
        Parent root = (Parent) loader.load(getClass().getResource("Home.fxml").openStream());
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}