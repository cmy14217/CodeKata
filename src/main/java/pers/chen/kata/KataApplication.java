package pers.chen.kata;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.chen.kata.anagram.AnagramWordsProcessor;

@SpringBootApplication
public class KataApplication {

  public static void main(String[] args) throws IOException {
    AnagramWordsProcessor anagramWordsProcessor = new AnagramWordsProcessor();
    anagramWordsProcessor.sortAnagramWordsBySignature();

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
