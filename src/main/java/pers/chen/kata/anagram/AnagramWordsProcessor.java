package pers.chen.kata.anagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class AnagramWordsProcessor {

  private List<SignedAnagramWords> signedAnagramWords;

  public void sortAnagramWordsBySignature() throws IOException {
    Map<String, List<String>> wordsOfSameSignature = new FileParser().parse();
    this.signedAnagramWords = wordsOfSameSignature.entrySet()
        .stream()
        .sorted(Comparator.comparing(Entry::getKey))
        .map(signedWord -> new SignedAnagramWords(signedWord.getKey(), signedWord.getValue()))
        .collect(Collectors.toList());
  }

  public List<String> getAnagramsOfWord(String word) {
    int left = 0;
    int right = signedAnagramWords.size();
    String signature = getSignature(word);
    for (int i = left; i < right; i++) {
      int middle = (left + right) / 2;
      if (signature.compareTo(signedAnagramWords.get(middle).getSignature()) < 0) {
        right = middle + 1;
      } else if(signature.compareTo(signedAnagramWords.get(middle).getSignature()) > 0) {
        left = middle;
      } else {
        return signedAnagramWords.get(middle).getAnagramWords();
      }
    }
    return new ArrayList<>();
  }

  private String getSignature(String word) {
    return Arrays.stream(word.split("")).sorted().reduce((a, b) -> a + b).orElse(word);
  }
}
