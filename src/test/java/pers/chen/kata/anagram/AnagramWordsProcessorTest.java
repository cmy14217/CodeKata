package pers.chen.kata.anagram;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnagramWordsProcessorTest {

  @Mock
  private FileParser fileParser;

  @InjectMocks
  private AnagramWordsProcessor anagramWordsProcessor;

  @Test
  void should_map_signed_words_to_the_ordered_objects_when_sort_anagram_words_by_signature() throws IOException {
    //Given
    Map<String, List<String>> signedWords = new HashMap<>();
    signedWords.put("cdeirt", newArrayList("direct", "credit"));
    signedWords.put("ainv", newArrayList("ivan"));
    signedWords.put("accitt", newArrayList("tactic", "tictac"));
    signedWords.put("blmosy", newArrayList("symbol"));
    when(fileParser.parse()).thenReturn(signedWords);

    //When
    anagramWordsProcessor.sortAnagramWordsBySignature();
    List<SignedAnagramWords> signedAnagramWords = anagramWordsProcessor.getSignedAnagramWords();


    //Then
    SignedAnagramWords signedAnagramWords1 = new SignedAnagramWords("accitt", newArrayList("tactic", "tictac"));
    SignedAnagramWords signedAnagramWords2 = new SignedAnagramWords("ainv", newArrayList("ivan"));
    SignedAnagramWords signedAnagramWords3 = new SignedAnagramWords("blmosy", newArrayList("symbol"));
    SignedAnagramWords signedAnagramWords4 = new SignedAnagramWords("cdeirt", newArrayList("direct", "credit"));
    assertEquals(newArrayList(signedAnagramWords1, signedAnagramWords2, signedAnagramWords3, signedAnagramWords4), signedAnagramWords);
  }

  @Test
  void should_return_anagrams_of_word_when_call_get_anagrams_of_word_given_has_anagrams_of_input_word() {
    //Given
    SignedAnagramWords signedAnagramWords1 = new SignedAnagramWords("accitt", newArrayList("tactic", "tictac"));
    SignedAnagramWords signedAnagramWords2 = new SignedAnagramWords("ainv", newArrayList("ivan"));
    SignedAnagramWords signedAnagramWords3 = new SignedAnagramWords("blmosy", newArrayList("symbol"));
    SignedAnagramWords signedAnagramWords4 = new SignedAnagramWords("cdeirt", newArrayList("direct", "credit"));
    anagramWordsProcessor.setSignedAnagramWords(newArrayList(signedAnagramWords1, signedAnagramWords2, signedAnagramWords3, signedAnagramWords4));

    //When
    List<String> anagrams = anagramWordsProcessor.getAnagramsOfWord("direct");

    //Then
    assertEquals(newArrayList("direct", "credit"), anagrams);
  }

  @Test
  void should_return_empty_list_when_call_get_anagrams_of_word_given_not_hve_anagrams_of_input_word() {
    //Given
    anagramWordsProcessor.setSignedAnagramWords(newArrayList());

    //When
    List<String> anagrams = anagramWordsProcessor.getAnagramsOfWord("direct");

    //Then
    assertEquals(newArrayList(), anagrams);
  }

  @Test
  void should_return_two_word_anagrams_of_word_when_call_get_anagrams_of_word_given_has_anagrams_of_input_word() {
    SignedAnagramWords signedAnagramWords1 = new SignedAnagramWords("accitt", newArrayList("tactic", "tictac"));
    SignedAnagramWords signedAnagramWords2 = new SignedAnagramWords("ainv", newArrayList("ivan"));
    SignedAnagramWords signedAnagramWords3 = new SignedAnagramWords("blmosy", newArrayList("symbol"));
    SignedAnagramWords signedAnagramWords4 = new SignedAnagramWords("cdeirt", newArrayList("direct", "credit"));
    anagramWordsProcessor.setSignedAnagramWords(newArrayList(signedAnagramWords1, signedAnagramWords2, signedAnagramWords3, signedAnagramWords4));

    //When
    List<String> anagrams = anagramWordsProcessor.getTwoAnagramsOfWord("bolsmydirect");

    //Then
    assertEquals(newArrayList("symbol--direct", "symbol--credit"), anagrams);
  }
}