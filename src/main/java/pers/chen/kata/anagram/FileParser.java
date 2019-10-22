package pers.chen.kata.anagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.util.StringUtils;

public class FileParser {

  public Map<String, List<String>> parse() throws IOException {
    Map<String, List<String>> signedWords = new HashMap<>();
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("word-list.txt");

    try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
      bufferedReader.readLine();
      bufferedReader.lines().forEach(line -> {
        String[] splitWords = line.split(" ");
        Arrays.stream(splitWords).forEach(word -> {
          if(!StringUtils.isEmpty(word)) {
            String signature = getSignature(word);
            addWordToWordsOfSignature(signedWords, signature, word);
          }
        });

      });
    }
    return signedWords;
  }

  private String getSignature(String word) {
    return Arrays.stream(word.split("")).sorted().reduce((a,b) -> a + b).orElse(word);
  }

  private void addWordToWordsOfSignature(Map<String, List<String>> map, String key, String value) {
    List<String> values = map.get(key);
    if (values == null) {
      values = new ArrayList<>();
    }
    values.add(value);
    map.put(key, values);
  }
}
