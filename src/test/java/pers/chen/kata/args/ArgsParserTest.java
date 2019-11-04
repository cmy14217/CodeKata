package pers.chen.kata.args;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ArgsParserTest {

  @Mock
  private SchemaParserFinder schemaParserFinder;

  @InjectMocks
  private ArgsParser argsParser;

  @Test
  void should_pass_right_map_to_finder_when_parse_given_args() {
    //Given
    String args = "-l -p 8080 -d /user/bin";
    ArgumentCaptor<Map<String, String>> mapArgumentCaptor = ArgumentCaptor.forClass(Map.class);

    //When
    argsParser.parse(args);

    //Then
    verify(schemaParserFinder).findSchemaValues(mapArgumentCaptor.capture());
    Map<String, String> argumentCaptorValue = mapArgumentCaptor.getValue();
    assertEquals("", argumentCaptorValue.get("-l"));
    assertEquals("/user/bin", argumentCaptorValue.get("-d"));
    assertEquals("8080", argumentCaptorValue.get("-p"));
  }

  @Test
  void should_pass_right_map_to_finder_when_parse_given_args_is_empty() {
    //Given
    String args = "";
    ArgumentCaptor<Map<String, String>> mapArgumentCaptor = ArgumentCaptor.forClass(Map.class);

    //When
    argsParser.parse(args);

    //Then
    verify(schemaParserFinder).findSchemaValues(mapArgumentCaptor.capture());
    Map<String, String> argumentCaptorValue = mapArgumentCaptor.getValue();
    assertEquals(0, argumentCaptorValue.size());
  }
}