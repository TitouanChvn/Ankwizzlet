import java.util.ArrayList;
import java.util.Collections;

public class DeckOfFlashcards extends MyObservable{
    
    public String name ;
    public String description ;
    public int id ;
    public ArrayList<Flashcard> deck;
    public int topCard;
    ArrayList<Double> G = new ArrayList<Double>(); // liste des parties gagnées dans le deck
    ArrayList<Double> J = new ArrayList<Double>(); // liste des parties jouées dans le deck


    public DeckOfFlashcards() {
        name = "" ;
        description = "" ;
        id = 0 ;
        deck = new ArrayList<Flashcard>();
    }

    public DeckOfFlashcards(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
        deck = new ArrayList<Flashcard>();
    }

    public void addFlashcard(Flashcard f) {
        deck.add(f);
    }   

    public ArrayList<Flashcard> getDeck() {
        return deck;
    }

    public Flashcard getFlashcard(int i) {
        return deck.get(i);
    }

    public void setDeck(ArrayList<Flashcard> deck) {
        this.deck = deck;
    }

    public Flashcard dealTopFlashcard(){
        Flashcard f = deck.get(topCard);
        topCard++;
        if (topCard == deck.size()) {
            topCard = 0;
        }
        notifyObservers();
        return f;
    }

    public Flashcard DealRealRandomFlashcard(){
        int random = (int) (Math.random() * deck.size());
        Flashcard f = deck.get(random);
        notifyObservers();
        return f;
    }

    public Flashcard dealRandomFlashcard(ArrayList<Flashcard> deckCopy ){ // deal a random flashcard
        
        int random = (int) (Math.random() * deck.size());
        Flashcard f = deck.get(random);
        if (deckCopy.size() <= 5) {
            deckCopy.add(f) ; // on met les cartes tant que y en a pas 5 dans le deck qui empeche la redondance
        }
        else {
            while (deckCopy.contains(f)) { // si la carte à été pick recemment on en pick une autre
                
                random = (int) (Math.random() * deck.size());
                f = deck.get(random);
            }
            deckCopy.remove(0) ; // on enlève la carte la plus ancienne (la premiere)
            deckCopy.add(f) ; // on ajoute la nouvelle carte
        }
        notifyObservers();
        return f;
    }
    
    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }
    
    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }
    
    public void setId(int id) {
        this.id = id;
        notifyObservers();
    }
    
    public String getName() {
        return this.name ;
    }
    
    public String getDescription() {
        return this.description ;
    }
    
    public int getId() {
        return this.id ;
    }
    
    public void display() {
        System.out.println("Name : " + name);
        System.out.println("Description : " + description);
        System.out.println("Id : " + id);
        for (Flashcard f : deck) {
            f.display();
        }
    }
    
    public void removeFlashcard(Flashcard f) {
        deck.remove(f);
    }
    

    // Si jamais on veut edit une certaine flashcard sans aller dessus
    public void editFlashcardQuestion(Flashcard f, String question) {
        f.setQuestion(question);
    }

    public void editFlashcardAnswer(Flashcard f, String answer) {
        f.setAnswer(answer);
    }

    public void editFlashcardId(Flashcard f, int id) {
        f.setId(id);
    }

    public void addGame() {
        for (Flashcard f : deck) {
            G.add((double)f.getWon());
            J.add((double)f.getPlayed());
        }
    } // Les listes de carte sont remplies avec les parties gagnées et jouées de chaque carte

    public ArrayList<Double> addX(int k) { // Xk est initialisée pour la carte k
        ArrayList<Double> Xk = new ArrayList<Double>(); // les parties jouées sauf une qui est gagnée dans le deck k

        Xk.add(G.get(k));

        for (int i = 0; i < J.size(); i++) {
            if (i != k) {
                Xk.add(J.get(i));
            }
        }
        return Xk ;
    }

    public Double min(ArrayList<Double> X) { // renvoie le minimum de Xk
        double min = X.get(0);
        for (int i = 0; i < X.size(); i++) {
            if (X.get(i) < min) {
                min = X.get(i);
            }
        }
        return min ;
    }

    public Double max(ArrayList<Double> X) { // renvoie le maximum de Xk
        double max = X.get(0);
        for (int i = 0; i < X.size(); i++) {
            if (X.get(i) > max) {
                max = X.get(i);
            }
        }
        return max ;
    }
    
    public Double produit(ArrayList<Double> X) { // renvoie le produit de Xk
        double produit = 1;
        for (int i = 0; i < X.size(); i++) {
            produit = produit * X.get(i);
        }
        return produit ;
    }

    public Double variance(ArrayList<Double> X) { // renvoie la variance de Xk
        double variance = 0;
        double moyenne = 0;
        for (int i = 0; i < X.size(); i++) {
            moyenne = moyenne + X.get(i);
        }
        moyenne = moyenne / X.size();
        for (int i = 0; i < X.size(); i++) {
            variance = variance + (X.get(i) - moyenne) * (X.get(i) - moyenne);
        }
        variance = variance / X.size();
        return variance ;
    }


    public ArrayList<Double> normaliserListe(ArrayList<Integer> liste) {
        // trouve le minimum et le maximum de la liste
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int valeur : liste) {
            min = Math.min(min, valeur);
            max = Math.max(max, valeur);
        }

        // calcule les valeurs normalisées et les ajoute à une nouvelle liste
        ArrayList<Double> listeNormalisee = new ArrayList<Double>();
        for (int valeur : liste) {
            double valeurNormalisee = (double)(valeur - min) / (max - min);
            listeNormalisee.add(valeurNormalisee);
        }

        // renvoie la liste normalisée
        return listeNormalisee;
    }   

    public int getNiceCard(int k) { // "proba" p d'avoir la carte k
        double p = 0 ;

        p = produit(J) ;

        double att ;
        ArrayList<Double> Xk = addX(k);
        att = produit(Xk)*(1-variance(Xk));
        p = p - att ;
        int g = (int) p + 1;

        //absolute val of g
        if (g < 0) {
            g = -g;
        }

        return g ;
    }

    public ArrayList<Flashcard> nvllelist() { // crée une liste avec g fois chaque carte du deck actuel, g étant la valeur de retour de getNiceCard
        ArrayList<Flashcard> newdeck = new ArrayList<Flashcard>();
        System.out.println(name);
        System.out.println(deck.size());
        for (int i = 0; i < deck.size(); i++) {
            System.out.println("i = " + i + " g = " + getNiceCard(i));
            for (int j = 0; j < getNiceCard(i); j++) {
                System.out.println("i = " + i + " j = " + j);
                //System.out.println(deck.get(i).getQuestion());
                newdeck.add(deck.get(i));
            }
        }
        return newdeck ;

    }

    public Flashcard dealCorrectFlashcard() { // deal a correct flashcard
        ArrayList<Flashcard> newdeck = nvllelist();
        System.out.println(newdeck.size());
        int i = (int) (Math.random() * newdeck.size());
        System.out.println(i);
        return newdeck.get(i); // en théorie on prend une carte random dans newdeck
    }


    public int getNbCardPlayedInDeck() {
        int nbCardPlayed = 0;
        for (Flashcard f : deck) {
            nbCardPlayed += f.getPlayed();
        }
        return nbCardPlayed;
    }

    public int getNbCardWonInDeck() {
        int nbCardWon = 0;
        for (Flashcard f : deck) {
            nbCardWon += f.getWon();
        }
        return nbCardWon;
    }

    public int getNbCardInDeck() {
        return deck.size();
    }

    public int getNbCardLearnedInDeck() {
        int nbCardLearned = 0;
        for (Flashcard f : deck) {
            if (f.getLearned()) {
                nbCardLearned++;
            }
        }
        return nbCardLearned;
    }

    public void notifyObservers() {
        super.notifyObservers();
    }
    
    public void addObserver(MyObserver o) {
        super.addObserver(o);
    }



}
