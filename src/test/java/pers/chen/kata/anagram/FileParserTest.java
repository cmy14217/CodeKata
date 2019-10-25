package pers.chen.kata.anagram;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class FileParserTest {

  @Test
  void should_return_signature_and_anagrams_map() throws IOException {
    FileParser fileParser = new FileParser();
    Map<String, List<String>> wordsMap = fileParser.parse();
    wordsMap.forEach((sign, words) -> System.out.println(sign + "-----" + words.toString()));
  }
}