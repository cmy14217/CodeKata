package pers.chen.kata.anagram;

import java.util.List;

public class SignedAnagramWords {

  private String signature;
  private List<String> anagramWords;

  public SignedAnagramWords(String signature, List<String> anagramWords) {
    this.signature = signature;
    this.anagramWords = anagramWords;
  }

  public String getSignature() {
    return signature;
  }

  public List<String> getAnagramWords() {
    return anagramWords;
  }
}
