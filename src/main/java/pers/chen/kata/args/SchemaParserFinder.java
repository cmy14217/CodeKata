package pers.chen.kata.args;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import pers.chen.kata.args.parsers.SchemaParser;

@Component
public class SchemaParserFinder {

  public List<String> findSchemaValues(Map<String, String> flagAndValues) {
    List<String> results = new ArrayList<>();
    Arrays.stream(SchemaEnum.values())
        .forEach(schemaEnum -> {
          String value = flagAndValues.get(schemaEnum.getFlag());
          SchemaParser schemaParser = schemaEnum.getSchemaParser();
          results.add(schemaParser.type() + schemaParser.parse(value));
        });
    return results;
  }
}
