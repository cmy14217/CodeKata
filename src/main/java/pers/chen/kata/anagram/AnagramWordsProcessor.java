package pers.chen.kata.anagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnagramWordsProcessor {

  @Autowired
  private FileParser fileParser;

  @Getter
  @Setter
  private List<SignedAnagramWords> signedAnagramWords;

  @PostConstruct
  public void sortAnagramWordsBySignature() throws IOException {
    Map<String, List<String>> wordsOfSameSignature = fileParser.parse();
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

  public List<String> getTwoAnagramsOfWord(String word) {
    List<String> results = new ArrayList<>();
    List<SignedAnagramWords> subWords = getSignedWordsOfSubSignature(word);

    for(int i=0; i<subWords.size()-1; i++) {
      for(int j=i+1; j<subWords.size(); j++) {
        String concatSignature = getSignature(subWords.get(i).getSignature().concat(subWords.get(j).getSignature()));
        if (getSignature(word).equals(concatSignature)) {
          results.addAll(groupMatchedWords(subWords.get(i).getAnagramWords(), subWords.get(j).getAnagramWords()));
        }
      }
    }
    return results;
  }

  private String getSignature(String word) {
    return Arrays.stream(word.split("")).sorted().reduce((a, b) -> a + b).orElse(word);
  }

  private List<SignedAnagramWords> getSignedWordsOfSubSignature(String word) {
    return signedAnagramWords.stream()
        .filter(signedWords -> containsSubChars(word, signedWords.getSignature()))
        .collect(Collectors.toList());
  }

  private boolean containsSubChars(String word1, String word2) {
    String[] word2Chars = word2.split("");
    return Arrays.stream(word2Chars).allMatch(word1::contains);
  }

  private List<String> groupMatchedWords(List<String> words1, List<String> words2) {
    List<String> results = new ArrayList<>();
    for (String word1 : words1) {
      for (String word2 : words2) {
        results.add(word1 + "--" + word2);
      }
    }
    return results;
  }
}
