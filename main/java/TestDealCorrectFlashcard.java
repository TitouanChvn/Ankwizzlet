public class TestDealCorrectFlashcard {
    public static void main(String[] args) {
        String objectif= "On veut tester la méthode dealCorrectFlashcard  utilisée lors d'une partie en mode classique.\nL'objectif de cette fonction est de renvoyer avec une plus forte probabilité la carte ayant un taux de victoire plus bas.\nSi 2 cartes ont le même taux de victoire, on renvoie la carte ayant le moins de parties jouées.\n\n";
        System.out.println(objectif);
        String fonctionnement ="Pour cela on calcul une \"probabilité\" p d'voir chaque carte qui est un nombre entier plus élevé si la carte doit avoir plus de chance d'être choisie.\n On Créé ensuite p copies de la carte qu'on met dans une liste.\n On obtient ainsi une liste avec plusieurs copie de chaque carte.\nOn peut alors effectuer un simple tirage aléatoire sur cette liste.\n\n";
        System.out.println(fonctionnement);
        System.out.println("________________________________________________________\n\n");




        DeckOfFlashcards deck_equiprobable = new DeckOfFlashcards("Deck Test 1", "Deck ou toutes les cartes ont le meme nombre de vues et de victoires", 1);
        Flashcard f1 = new Flashcard("f1", "f1","f1","f1", 1, 2, 1);
        Flashcard f2 = new Flashcard("f2", "f2","f2","f2", 1, 2, 1);
        Flashcard f3 = new Flashcard("f3", "f3","f3","f3", 1, 2, 1);
        deck_equiprobable.addFlashcard(f1);
        deck_equiprobable.addFlashcard(f2);
        deck_equiprobable.addFlashcard(f3);
        deck_equiprobable.addGame();
        // Avec ce deck on doit avoir une probabilité de 1/3 pour chaque carte 
        // car on a pour chaque carte le même taux de victoire et le même nombre de parties jouées
        System.out.println("PREMIER DECK :");
        System.out.println("On a le deck suivant :");
        System.out.println("Carte 1 : " + deck_equiprobable.getFlashcard(0).getQuestion() + " gagnée : " + deck_equiprobable.getFlashcard(0).getWon() + " jouée : " + deck_equiprobable.getFlashcard(0).getPlayed());
        System.out.println("Carte 2 : " + deck_equiprobable.getFlashcard(1).getQuestion() + " gagnée : " + deck_equiprobable.getFlashcard(1).getWon() + " jouée : " + deck_equiprobable.getFlashcard(1).getPlayed());
        System.out.println("Carte 3 : " + deck_equiprobable.getFlashcard(2).getQuestion() + " gagnée : " + deck_equiprobable.getFlashcard(2).getWon() + " jouée : " + deck_equiprobable.getFlashcard(2).getPlayed());

        System.out.println("Chaque carte devrait donc avoir le même nombre p. Car on a pour chaque carte le même taux de victoire et le même nombre de parties jouées");
        System.out.println("on obtient :");
        System.out.println("Carte 1 : " + deck_equiprobable.getNiceCard(0));
        System.out.println("Carte 2 : " + deck_equiprobable.getNiceCard(1));
        System.out.println("Carte 3 : " + deck_equiprobable.getNiceCard(2));
        System.out.println("\n________________________________________________________\n\n");


        DeckOfFlashcards deck_1carte_ratee = new DeckOfFlashcards("Deck test 2", "deck ou une carte a un taux de réussite moindre", 1);
        Flashcard f4 = new Flashcard("f4", "f4","f4","f4", 1, 2, 1);
        Flashcard f5 = new Flashcard("f5", "f5","f5","f5", 1, 2, 1);
        Flashcard f6 = new Flashcard("f6", "f6","f6","f6", 1, 2, 0);

        deck_1carte_ratee.addFlashcard(f4);
        deck_1carte_ratee.addFlashcard(f5);
        deck_1carte_ratee.addFlashcard(f6);
        deck_1carte_ratee.addGame();

        System.out.println("DEUXIEME DECK :");
        System.out.println("On a le deck suivant :");
        System.out.println("Carte 1 : " + deck_1carte_ratee.getFlashcard(0).getQuestion() + " gagnee : " + deck_1carte_ratee.getFlashcard(0).getWon() + " jouee : " + deck_1carte_ratee.getFlashcard(0).getPlayed());
        System.out.println("Carte 2 : " + deck_1carte_ratee.getFlashcard(1).getQuestion() + " gagnee : " + deck_1carte_ratee.getFlashcard(1).getWon() + " jouee : " + deck_1carte_ratee.getFlashcard(1).getPlayed());
        System.out.println("Carte 3 : " + deck_1carte_ratee.getFlashcard(2).getQuestion() + " gagnee : " + deck_1carte_ratee.getFlashcard(2).getWon() + " jouee : " + deck_1carte_ratee.getFlashcard(2).getPlayed());

        System.out.println("La carte 3 devrait avoir une probabilité plus elevee que les autres car elle a un taux de victoire plus bas");
        System.out.println("on obtient :");
        System.out.println("Carte 1 : " + deck_1carte_ratee.getNiceCard(0));
        System.out.println("Carte 2 : " + deck_1carte_ratee.getNiceCard(1));
        System.out.println("Carte 3 : " + deck_1carte_ratee.getNiceCard(2));
        System.out.println("\n________________________________________________________\n\n");

        DeckOfFlashcards deck_1carte_jouee = new DeckOfFlashcards("Deck test 3", "deck ou 2 cartes sont moins réussies qu'une autre. parmi ces 2, l'une a été jouée moins de fois ", 1);
        Flashcard f7 = new Flashcard("f7", "f7","f7","f7", 1, 4, 3);
        Flashcard f8 = new Flashcard("f8", "f8","f8","f8", 1, 4,2 );
        Flashcard f9 = new Flashcard("f9", "f9","f9","f9", 1, 2, 1);
        
        deck_1carte_jouee.addFlashcard(f7);
        deck_1carte_jouee.addFlashcard(f8);
        deck_1carte_jouee.addFlashcard(f9);
        deck_1carte_jouee.addGame();

        System.out.println("TROISIEME DECK :");
        System.out.println("On a le deck suivant :");
        System.out.println("Carte 1 : " + deck_1carte_jouee.getFlashcard(0).getQuestion() + " gagnee : " + deck_1carte_jouee.getFlashcard(0).getWon() + " jouee : " + deck_1carte_jouee.getFlashcard(0).getPlayed());
        System.out.println("Carte 2 : " + deck_1carte_jouee.getFlashcard(1).getQuestion() + " gagnee : " + deck_1carte_jouee.getFlashcard(1).getWon() + " jouee : " + deck_1carte_jouee.getFlashcard(1).getPlayed());
        System.out.println("Carte 3 : " + deck_1carte_jouee.getFlashcard(2).getQuestion() + " gagnee : " + deck_1carte_jouee.getFlashcard(2).getWon() + " jouee : " + deck_1carte_jouee.getFlashcard(2).getPlayed());

        System.out.println("La carte 3 devrait avoir une probabilité plus elevee que les autres car elle a un taux de victoire plus bas (a égalité avec la 2) et elle a été joué moins de fois");
        System.out.println("on obtient :");
        System.out.println("Carte 1 : " + deck_1carte_jouee.getNiceCard(0));
        System.out.println("Carte 2 : " + deck_1carte_jouee.getNiceCard(1));
        System.out.println("Carte 3 : " + deck_1carte_jouee.getNiceCard(2));
        System.out.println("\n________________________________________________________\n\n");
        

    }
    
}
