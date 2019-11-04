package pers.chen.kata.args;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

public class SchemaParserFinderTest {

  @Test
  void should_return_all_schema_values_when_find_given_map_of_flags_and_values() {
    //Given
    Map<String, String> flagsAndValues = new HashMap<>();
    flagsAndValues.put("-l", "");
    flagsAndValues.put("-d", "/user/bin");
    flagsAndValues.put("-p", "8080");

    //When
    List<String> values = new SchemaParserFinder().findSchemaValues(flagsAndValues);

    //Then
    newArrayList("logging");
    assertThat(values, hasItems("logging: true", "directory: /user/bin", "port: 8080"));
  }

  @Test
  void should_return_all_schema_values_with_default_value_when_find_given_map_of_flags_and_values_is_empty() {
    //Given
    Map<String, String> flagsAndValues = new HashMap<>();

    //When
    List<String> values = new SchemaParserFinder().findSchemaValues(flagsAndValues);

    //Then
    newArrayList("logging");
    assertThat(values, hasItems("logging: false", "directory: ", "port: 0"));
  }
}