package pers.chen.kata.anagram;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class FileParserTest {

  @Test
  public void name() throws IOException {
    FileParser fileParser = new FileParser();
    Map<String, List<String>> wordsMap = fileParser.parse();
    wordsMap.forEach((sign, words) -> System.out.println(sign + "-----" + words.toString()));
  }
}