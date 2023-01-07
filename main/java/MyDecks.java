

import java.util.ArrayList;
import java.util.List;


public class MyDecks extends MyObservable {
    
    public ArrayList<DeckOfFlashcards> decks ;
    public DeckOfFlashcards ActualDeck ;
    public Flashcard ActualFlashcard ;
    
    public MyDecks() {
        decks = new ArrayList<DeckOfFlashcards>();
        ActualDeck = new DeckOfFlashcards();
    }

    public MyDecks(ArrayList<DeckOfFlashcards> decks, DeckOfFlashcards ActualDeck) {
        this.decks = decks;
        this.ActualDeck = ActualDeck;
    }

    public void setDecks(ArrayList<DeckOfFlashcards> decks) {
        this.decks = decks;
    }
    
    public ArrayList<DeckOfFlashcards> getDecks() {
        return this.decks ;
    }

    public void  setActualDeck(DeckOfFlashcards deck) {
        this.ActualDeck = deck ;
    }

    public DeckOfFlashcards getActualDeck() {
        return this.ActualDeck ;
    }
    
    public void display() {
        for (DeckOfFlashcards decks : decks){
            decks.display();
        }
    }
    
    public void addDeck(DeckOfFlashcards deck) {
        this.decks.add(deck);
    }
    
    public void removeDeck(DeckOfFlashcards deck) {
        this.decks.remove(deck);
    }
    
    public List<String> getDecksTitles(MyDecks decks) {
        List<String> titles = new ArrayList<String>();
        for (DeckOfFlashcards deck : decks.getDecks()) {
            titles.add(deck.getName());
        }
        return titles;
    }

    public List<String> getDecksDescription(MyDecks decks) {
        List<String> descriptions = new ArrayList<String>();
        for (DeckOfFlashcards deck : decks.getDecks()) {
            descriptions.add(deck.getDescription());
        }
        return descriptions;
    }

    public List<Integer> getDecksId(MyDecks decks) {
        List<Integer> ids = new ArrayList<Integer>();
        for (DeckOfFlashcards deck : decks.getDecks()) {
            ids.add(deck.getId());
        }
        return ids;
    }

    public DeckOfFlashcards getDeckByName(String name) { // très peu de sens car plusieurs decks peuvent avoir le même nom
        for (DeckOfFlashcards deck : decks) {
            if (deck.getName().equals(name)) {
                return deck;
            }
        }
        return null;
    }

    public DeckOfFlashcards getDeckById(int id) {
        for (DeckOfFlashcards deck : decks) {
            if (deck.getId() == id) {
                return deck;
            }
        }
        return null;
    }

    public int biggerId() {
        int id = 0;
        for (DeckOfFlashcards deck : decks) {
            if (deck.getId() > id) {
                id = deck.getId();
            }
        }
        return id;
    }

    public int getFreeId() {
        int[] tab = new int[biggerId() + 1];
        for (DeckOfFlashcards deck : decks) {
            tab[deck.getId()-1] = 1;
        }
        for (int j = 0; j < tab.length; j++) {
            if (tab[j] == 0) {
                return j + 1;
            }
        }
        return biggerId();
    }
    

    //les trois fonctions suivantes, si jamais on veut edit un deck ""de loin"".
    public void editDeckName(DeckOfFlashcards deck, String deckName) {
        deck.setName(deckName);
    }

    public void editDeckDescription(DeckOfFlashcards deck, String description) {
        deck.setDescription(description);
    }

    public void editDeckId(DeckOfFlashcards deck, int id) {
        deck.setId(id);
    }
    
    public void exportDecks(String path) {
        // ouhla
    }
    
    public MyDecks importDecks(String path) {
        return null ; 
        //ouhla
    }

    public int getNbTotalCardsPlayed() {
        int nb = 0;
        for (DeckOfFlashcards deck : decks) {
            nb += deck.getNbCardPlayedInDeck();
        }
        return nb;
    }

    public int getNbTotalCardsWon() {
        int nb = 0;
        for (DeckOfFlashcards deck : decks) {
            nb += deck.getNbCardWonInDeck();
        }
        return nb;
    }

    public int getNbTotalCards() {
        int nb = 0;
        for (DeckOfFlashcards deck : decks) {
            nb += deck.getNbCardInDeck();
        }
        return nb;
    }

    public int getNbTotalCardsLearned() {
        int nb = 0;
        for (DeckOfFlashcards deck : decks) {
            nb += deck.getNbCardLearnedInDeck();
        }
        return nb;
    }

    public int getNbDecks() {
        return decks.size();
    }


    // les fonctions suivantes sont pour les statistiques

    public boolean isCardViewed(Flashcard card) {
        return card.getPlayed() > 1;
    }

    public boolean isCardLearned(Flashcard card) {
        if (card.getPlayed() == 0) {
            return false;
        }
        else {
            return card.getLearned() ; // carte apprise si réussite dans 85% des cas
        } 
    }

    public boolean isDeckViewed(DeckOfFlashcards d) {
        // un deck est vu si au moins une de ses cartes est vue
        for (Flashcard card : d.deck) {
            if (isCardViewed(card)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDeckLearned(DeckOfFlashcards d) {
        // un deck est appris si toutes ses cartes sont apprises
        for (Flashcard card : d.deck) {
            if (!isCardLearned(card)) {
                return false;
            }
        }
        return true;
    }

    public int nbCardinDeck(DeckOfFlashcards d) {
        return d.deck.size();
    }

    public int nbDeckViewed() {
        int result = 0;
        for (int i = 0; i < decks.size(); i++) {
            if (isDeckViewed((DeckOfFlashcards) decks.get(i))) {
                result++;
            }
        }
        System.out.println("deck viewed : " + result);
        return result;
    }

    public int nbDeckTotal(){
        return decks.size();
    }

    public int nbDeckNotStarted() {
        int nbDeckTotal = nbDeckTotal();
        int nbDeckViewed = nbDeckViewed();
        return nbDeckTotal - nbDeckViewed;
    }

    public int nbDeckLearned() {
        int result = 0;
        for (int i = 0; i < decks.size(); i++) {
            if (isDeckLearned(decks.get(i))) {
                result++;
            }
        }
        System.out.println("deck learned : " + result);
        return result;
    }

    public int nbCardTotal() {
        int result = 0;
        for (DeckOfFlashcards d : decks) {
            result += nbCardinDeck(d);
        }
        return result;
    }

    public int nbCardViewed(){
        int result = 0;
        for (DeckOfFlashcards d : decks) {
            result += nbCardinDeckViewed(d);
        }
        return result;
    }

    public int nbCardNotViewed() {
        int nbCardTotal = nbCardTotal();
        int nbCardViewed = nbCardViewed();
        return nbCardTotal - nbCardViewed;
    }

    public int nbCardLearned() {
        int result = 0;
        for (DeckOfFlashcards d : decks) {
            result += nbCardinDeckLearned(d);
        }
        return result;
    }

    public int nbCardinDeckViewed(DeckOfFlashcards d) {
        int result = 0;
        for (Flashcard card : d.deck) {
            if (isCardViewed(card)) {
                result++;
            }
        }
        return result;
    }

    public int nbCardinDeckLearned(DeckOfFlashcards d) {
        int result = 0;
        for (Flashcard card : d.deck) {
            if (isCardLearned(card)) {
                result++;
            }
        }
        return result;
    }

    public int nbCardinDeckTotal(DeckOfFlashcards d) {
        int result = 0;
        for (Flashcard card : d.deck) {
            result++;
        }
        return result;
    }

    public int nbCardinDeckNotViewed(DeckOfFlashcards d) {
        int nbCardinDeckTotal = nbCardinDeckTotal(d);
        int nbCardinDeckViewed = nbCardinDeckViewed(d);
        return nbCardinDeckTotal - nbCardinDeckViewed;
    }

    public void notifyObservers() {
        super.notifyObservers();
    }

    public void addObserver(MyObserver o) {
        super.addObserver(o);
    }




}
