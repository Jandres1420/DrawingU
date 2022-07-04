package co.edu.escuelaing.interactiveblackboard.configurator.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Words {

    public List<String> randomWords;
    public List<String> alreadyUsedWords;
    public Words(){
        randomWords = new ArrayList<>();
        alreadyUsedWords = new ArrayList<String>();
        generateWords();
    }  
   
    public String getRandomWord(){
        Random random = new Random();
        String word = "";
        int numeroRandom = random.nextInt(randomWords.size());
        while(!(alreadyUsedWords.contains(randomWords.get(numeroRandom)))) {
            alreadyUsedWords.add(randomWords.get(numeroRandom));
            numeroRandom = random.nextInt(randomWords.size());
            word = randomWords.get(numeroRandom);
        }
        return word;
    }

    public void generateWords(){
        randomWords.add("Casa");
        randomWords.add("Streamer");
        randomWords.add("Gimnasio");
        randomWords.add("Gamer");
        randomWords.add("Programador");
        randomWords.add("ECI");
        randomWords.add("Piercings");
        randomWords.add("Tatuajes");
        randomWords.add("Cerveza");
        randomWords.add("Australia");
        randomWords.add("Alemania");
        randomWords.add("Argentina");
        randomWords.add("Colombia");
    }
    public List<String> getRandomWords() {
        return randomWords;
    }

    public void setRandomWords(List<String> randomWords) {
        this.randomWords = randomWords;
    }
}
