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
   
   
Args

You should write a parser for this kind of arguments. This parser takes a schema detailing what arguments the program expects. 
The schema specifies the number and types of flags and values the program expects.
For example if the program is to be called with these arguments:
    -l -p 8080 -d /usr/logs
    
this indicates a schema with 3 flags: l, p, d. The “l” (logging) flag has no values associated with it, it is a boolean flag, 
True if present, False if not. the “p” (port) flag has an integer value, and the “d” (directory) flag has a string value.