public class Flashcard extends MyObservable{

    public String question;
    public String answer;
    public String indice;
    public String explication;
    public int id;
    public int played;
    public int won;

    public Flashcard() {
        question = "";
        answer = "";
        indice = "";
        explication = "";
        id = 0;
        played = 1;
        won = 0;
    }

    public Flashcard(String question, String answer, String indice, String explication, int id, int played, int won) {
        this.question = question;
        this.answer = answer;
        this.indice = indice;
        this.explication = explication;
        this.id = id;
        if (played == 0) {
            this.played = 1;
        } else {
            this.played = played;
        }
        this.won = won;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getIndice() {
        return indice;
    }

    public String getExplication() {
        return explication;
    }

    public int getId() {
        return id;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getLearned() {
        if (played == 0) {
            return false;
        } else {
            if (won != 0) {
                return (100*won >= 85*played);
            }
        }
        return false;
    }

    public void setPlayed(int played) {
        if (played == 0) {
            this.played = 1;
        } else {
            this.played = played;
        }
    }

    public void setWon(int won) {
        this.won = won;
    }

    public void display() {
        System.out.println("Question : " + question);
        System.out.println("Answer : " + answer);
        System.out.println("Indice : " + indice);
        System.out.println("Explication : " + explication);
        System.out.println("Id : " + id);
        System.out.println("Played : " + played);
        System.out.println("Won : " + won);
    }

    public void notifyObservers() {
        super.notifyObservers();
    }

    public void addObserver(MyObserver o) {
        super.addObserver(o);
    }

}