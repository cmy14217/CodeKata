Anagram

1. find one-word anagrams of word from a file.
   - make the sorted characters of a word as its signature
   - parse the file contents and map it to signature-anagrams 
   - sort the map according to its key(signature) 
   - transform the map to object to make it easier to search
   - use Binary search to search the one-word anagrams of the input word

2. find two-words anagrams of word from a file.
   - get signature of the input word
   - traverse the directory, filter the data whose signature is the child of the signature of the input word
   - combine each two and distinguish if its same with the given signature