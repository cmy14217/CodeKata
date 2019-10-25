package pers.chen.kata;

import java.util.List;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pers.chen.kata.anagram.AnagramWordsProcessor;
import pers.chen.kata.anagram.SpringConfig;

@SpringBootApplication
public class KataApplication {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    AnagramWordsProcessor anagramWordsProcessor = context.getBean(AnagramWordsProcessor.class);

    Scanner scanner = new Scanner(System.in);
    System.out.println("Please input your word: ");
    while(scanner.hasNext()) {
      String word = scanner.next();
      List<String> anagrams = anagramWordsProcessor.getAnagramsOfWord(word);
      System.out.println("Anagrams of " + word + " are: " + anagrams);
      System.out.println("\nPlease input your word: ");
    }
  }
}
