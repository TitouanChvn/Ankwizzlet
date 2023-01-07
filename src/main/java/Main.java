

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileWriter;
import com.google.gson.*;

public class Main extends Application {
  public Stage Mystage;
  @Override
  public void start(Stage stage) throws Exception {
    MyDecks myDecks = new MyDecks();
    Mystage = stage;

    //On crée les decks à partir des fichiers json
    createDecks(myDecks);

    FXMLLoader loader = new FXMLLoader();
    loader.setController(new HomeController(myDecks));
    loader.setLocation(getClass().getResource("Home.fxml"));
    Parent panneau = (Parent) loader.load();
    Scene scene = new Scene(panneau);

    stage.setScene(scene);
    stage.setTitle("Ankwizzlet");
    stage.setResizable(false);
    stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/images/logo_Ankwizzlet.png"));
    stage.show();

    //On sauvegarde les decks à la fermeture de l'application
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
        @Override
        public void run()
        {
            updateDecks(myDecks);
        }
    });

  }

  public static void main(String[] args) {
    launch(args);
  }


  //On sauvegarde les flashcards dans le deck deck
  private void parseCard(JSONObject flashcard, DeckOfFlashcards deck) {
    //On récupère les informations de la flashcard
    String q = (String) flashcard.get("question");
    String r = (String) flashcard.get("reponse");
    String i = (String) flashcard.get("indice");
    String e = (String) flashcard.get("explication");
    String ii = (String) flashcard.get("idC");
    String nJ = (String) flashcard.get("nbJoue");
    String nR = (String) flashcard.get("nbBonnesReponses");

    //On crée la flashcard
    Flashcard f = new Flashcard(q, r, i, e, Integer.parseInt(ii), Integer.parseInt(nJ), Integer.parseInt(nR));
    //On l'ajoute au deck
    deck.addFlashcard(f);
  }


  //Créer les decks à partir des fichiers json
  public void createDecks(MyDecks myDecks) throws IOException { 
    
    File repertoire = new File("dataAnkwizzlet");
    String[] liste = repertoire.list();      

    //On parcourt la liste des fichiers du répertoire
    if (liste != null) {         
        for (int i = 0; i < liste.length; i++) {

            //On ne prend que les fichiers .json
            if (liste[i].endsWith(".json")) {

              //On crée un parser JSON
              JSONParser jsonParser = new JSONParser();
              try (FileReader reader = new FileReader(repertoire + "/" + liste[i]))
              {
                //Read JSON file
                Object obj = jsonParser.parse(reader);
      
                JSONObject deck = (JSONObject) obj;
                   
                if (deck.get("id") != null && deck.get("nom") != null && deck.get("description") != null && deck.get("flashcards") != null) {
                  if (deck.get("id").getClass().getName() == "java.lang.String" && deck.get("nom").getClass().getName() == "java.lang.String" && deck.get("description").getClass().getName() == "java.lang.String" && deck.get("flashcards").getClass().getName() == "org.json.simple.JSONArray") {
                    String id = (String) deck.get("id");    
                    String nom = (String) deck.get("nom");  
                    String description = (String) deck.get("description");   

                    //On crée le deck
                    DeckOfFlashcards deckOfFlashcards = new DeckOfFlashcards(nom, description, Integer.parseInt(id));
                    JSONArray flashcardsList = (JSONArray) deck.get("flashcards");

                    //On parcourt la liste des flashcards et on les ajoute au deck
                    for (Object f : flashcardsList) {
                      JSONObject flashcard = (JSONObject) f;
                      parseCard(flashcard, deckOfFlashcards);
                    }
                    myDecks.addDeck(deckOfFlashcards);

                    reader.close();
                  }
                }
        
              } catch (FileNotFoundException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (ParseException e) {
                  e.printStackTrace();
              }
          }
        }
      }
    }
  
      //Mettre à jour les decks
      public void updateDecks(MyDecks myDecks) {

        File file = new File("dataAnkwizzlet");
        
        //On crée le répertoire s'il n'existe pas
        if (!file.exists()) {
          file.mkdir();
        }
        
        String[] liste = file.list();      

        //On parcourt la liste des fichiers du répertoire
        if (liste != null) {         
          for (int j = 0; j < liste.length; j++) {
              //On supprime les fichiers .json
              if (liste[j].endsWith(".json")) {
                File f = new File(file + "/" + liste[j]);
                f.delete();
              }
          }
        }

        //On parcourt la liste des decks
        for (DeckOfFlashcards deckk : myDecks.getDecks()) {

          String idDeck = Integer.toString(deckk.getId());
          String nom = deckk.getName();
          String description = deckk.getDescription();

          //On crée le fichier json
          JSONObject d = new JSONObject();
          try (FileWriter writer = new FileWriter(file + "/" + idDeck + ".json")) {

            JSONArray flashcardsList = new JSONArray();

            //On parcourt la liste des flashcards et on les ajoute à flashcardsList
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


            //On écrit le fichier json dans le répertoire
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
}

