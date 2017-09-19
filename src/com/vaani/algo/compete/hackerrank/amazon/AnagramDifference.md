We define an anagram to be a word whose characters can be rearranged to create another word. Given two strings, we want to know the minimum number of characters already in either string that we must modify to make the two string anagrams; if it's not possible to make the two string anagrams, we consider this number to be -1. For example:

- tea and ate are anagrams, so we would need to modify a minimum of 0 characters.
- tea and toe are not anagrams, but we can modify a minimum of 1 character in either string to make them anagrams.
- act and acts are not anagrams and cannot be converted to anagrams because they contain different numbers of characters, so the minimum number of characters to modify is -1.

The function must return an array of integers where each element i denotes the minimum number of characters you must modify to make 'a' and 'b' anagrams, if it's not possible to modify the existing characters in 'a' and 'b' to make them anagrams element i should be -1 instead.

Note: You can't delete or append the quantity of characters.

Example:

5 a jk abb mn abc 5 bb kj bbc op def

