public class DeckTest2 extends DeckOfFlashcards {

    DeckOfFlashcards deck = new DeckOfFlashcards();

    Flashcard f1 = new Flashcard("question1", "answer1", "indice1", "explication1", 1, 0, 0);
    Flashcard f2 = new Flashcard("question2", "answer2", "indice2", "explication2", 2, 0, 0);
    Flashcard f3 = new Flashcard("question3", "answer3", "indice3", "explication3", 3, 0, 0);
    Flashcard f4 = new Flashcard("question4", "answer4", "indice4", "explication4", 4, 0, 0);
    Flashcard f5 = new Flashcard("question5", "answer5", "indice5", "explication5", 5, 0, 0);
    Flashcard f6 = new Flashcard("question6", "answer6", "indice6", "explication6", 6, 0, 0);
    Flashcard f7 = new Flashcard("question7", "answer7", "indice7", "explication7", 7, 0, 0);
    Flashcard f8 = new Flashcard("question8", "answer8", "indice8", "explication8", 8, 0, 0);
    Flashcard f9 = new Flashcard("question9", "answer9", "indice9", "explication9", 9, 0, 0);
    Flashcard f10 = new Flashcard("question10", "answer10", "indice10", "explication10", 10, 0, 0);
    

    public void addFlashcards() {
        deck.addFlashcard(f1);
        deck.addFlashcard(f2);
        deck.addFlashcard(f3);
        deck.addFlashcard(f4);
        deck.addFlashcard(f5);
        deck.addFlashcard(f6);
        deck.addFlashcard(f7);
        deck.addFlashcard(f8);
        deck.addFlashcard(f9);
        deck.addFlashcard(f10);
    }
}
