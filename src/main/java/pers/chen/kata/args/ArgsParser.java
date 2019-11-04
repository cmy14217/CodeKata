package pers.chen.kata.args;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArgsParser {

  @Autowired
  private SchemaParserFinder schemaParserFinder;

  public void parse(String args) {
    Map<String, String> flagsAndValues = getFlagsAndValues(args);
    schemaParserFinder.findSchemaValues(flagsAndValues);
  }

  private Map<String, String> getFlagsAndValues(String args) {
    Map<String, String> results = new HashMap<>();
    String[] words = args.split(" ");
    List<Integer> flagsPosition = findFlagsPosition(words);
    for (int i = 0; i < flagsPosition.size(); i++) {
      String flag = words[flagsPosition.get(i)];
      String value = "";
      if(hasValueOfThisFlag(words, flagsPosition, i)) {
        value = words[flagsPosition.get(i) + 1];
      }
      results.put(flag, value);
    }
    return results;
  }

  private boolean hasValueOfThisFlag(String[] words, List<Integer> flagsPosition, int i) {
    return i + 1 < flagsPosition.size() && !flagsPosition.get(i + 1).equals(flagsPosition.get(i) + 1)
        || i + 1 == flagsPosition.size() && flagsPosition.get(i) + 1 < words.length;
  }

  private List<Integer> findFlagsPosition(String[] words) {
    List<Integer> flagsPosition = new ArrayList<>();
    for (int i=0; i<words.length; i++) {
      if (words[i].startsWith("-")) {
        flagsPosition.add(i);
      }
    }
    return flagsPosition;
  }

}
