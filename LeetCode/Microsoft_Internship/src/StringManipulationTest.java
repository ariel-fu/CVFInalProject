 import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// code to test string manipulations
class StringManipulationTest {
	StringManipulation test;

	@BeforeEach
	void setUp() throws Exception {
		test = new StringManipulation();
	}


	@Test
	void testReorder() {
		String text = "  this   is  a sentence ";
		String output = "this   is   a   sentence";
		assertEquals(output, test.reorderSpaces(text));
		text = " practice   makes   perfect";
		output = "practice   makes   perfect ";
		assertEquals(output, test.reorderSpaces(text));
				
	}
	
//	@Test
//	void testConstruct() {
//		String ransomNote = "ab";
//		String magazine = "a";
//		boolean result = test.canConstruct(ransomNote, magazine);
//		assertFalse(result);
//		
//		ransomNote = "abc";
//		magazine = "beca";
//		result = test.canConstruct(ransomNote, magazine);
//		assertTrue(result);
//		
//		ransomNote = "iwillkillyou";
//		magazine = "killkillwillwilluwadyo";
//		result = test.canConstruct(ransomNote, magazine);
//		assertTrue(result);
//	}
//	
//	@Test
//	void testDefang() {
//		String address = "1.1.1.1";
//		String output = "1[.]1[.]1[.]1";
//		address = test.defangIPaddr(address);
//		assertEquals(output, address);
//	}
	
	
	//	@Test
//	void testInterpret() {
//		String command = "G(al)()";
//		String result = "Galo";
//		assertEquals(result, test.interpret(command));
//	}
//	@Test
//	void testIsAnagram() {
//		String s = "car";
//		String t = "rac";
//		assertTrue(test.isAnagram(s, t));
//		
//		s = "notananagram";
//		t = "notevenclose";
//		assertTrue(!test.isAnagram(s, t));
//	}
//	@Test
//	void testFirstUniqChar() {
//		String s = "love";
//		assertEquals(0, test.firstUniqChar(s));
//
//		s = "nonono";
//		assertEquals(-1, test.firstUniqChar(s));
//
//		s = "nonou";
//		assertEquals(4, test.firstUniqChar(s));
//	}
//	@Test
//	void testReverse() {
//		int x = 123;
//		int result = 321;
//		assertEquals(result, test.reverse(x));
//		
//		x = 29083409;
//		result = 90438092;
//		assertEquals(result, test.reverse(x));
//		
//		x = (int) (Math.pow(2, 31)-1);
//		result = 0;
//		assertEquals(result, test.reverse(x));
//		x = 120;
//		result = 21;
//		
//		assertEquals(result, test.reverse(x));
//		
//		x = -12;
//		result = -21;
//		assertEquals(result, test.reverse(x));
//	}
//		@Test
//		void teststrStr() {
//			String haystack = "hello";
//			String needle = "ll";
//			assertEquals(2, test.strStr(haystack, needle));
//			
//			haystack = "aaaaaa";
//			needle = "bba";
//			assertEquals(-1, test.strStr(haystack, needle));
//			
//			haystack = "thisisridiculous";
//			needle = haystack;
//			assertEquals(0, test.strStr(haystack, needle));
//			
//			haystack = "";
//			needle = "soemthing";
//			assertEquals(-1, test.strStr(haystack, needle));
//			
//			haystack = "something";
//			needle = "";
//			assertEquals(0, test.strStr(haystack, needle));
//			
//			haystack = "aaaaa";
//			needle = "a";
//			assertEquals(0, test.strStr(haystack, needle));
//			
//			haystack = "aa";
//			needle = "aaaa";
//			assertEquals(-1, test.strStr(haystack, needle));
//		}
//	@Test
//	void testReverseWords() {
//		String s = "the sky is blue";
//		String result = "blue is sky the";
//		assertEquals(result, test.reverseWords(s));
//		
//		s = "  help   me";
//		result = "me help";
//		assertEquals(result, test.reverseWords(s));
//		
//		s = "a good     exam  ppp  l e";
//		result = "e l ppp exam good a";
//		assertEquals(result, test.reverseWords(s));
//	}
//	@Test
//	void testReverseString() {
//		char[] s = new char[] {'a', 'b', 'c', 'd'};
//		char[] result = new char[] {'d', 'c', 'b', 'a'};
//		s = test.reverseString2(s);
//		for(int i = 0; i<result.length; i++) {
//			assertEquals(result[i], s[i]);
//		}
//				
//		s = new char[] {'a', 'b', 'c'};
//		result = new char[] {'c', 'b', 'a'};
//		s = test.reverseString2(s);
//		for(int i = 0; i<result.length; i++) {
//			assertEquals(result[i], s[i]);
//		}
//		
//		s = new char[] {'h', 'a', 'n', 'n','a', 'h'};
//		result = new char[] {'h', 'a', 'n', 'n','a', 'h'};
//		s = test.reverseString2(s);
//		for(int i = 0; i<result.length; i++) {
//			assertEquals(result[i], s[i]);
//		}
//		
//	}
//	@Test
//	void testCountSubstrings() {
//		String s = "aaa";
//		int result = 6;
//		assertEquals(result, test.countSubstrings(s));
//		
//		s = "abc";
//		result = 3;
//		assertEquals(result, test.countSubstrings(s));
//		
//		s = "aabbccbbaa";
//		result = 19;
//		assertEquals(result, test.countSubstrings(s));
//		
//		s = "";
//		result = 0;
//		assertEquals(result, test.countSubstrings(s));
//		
//		s = "nopalindrome";
//		result = 12;
//		assertEquals(result, test.countSubstrings(s));
//				
//	}
////	@Test
////	void testIsPalindrome() {
////		String input = "wow";
////		assertTrue(test.isPalindrome(input));
////		
////		input = "notpalindrome";
////		assertTrue(!test.isPalindrome(input));
////		
////		input = "ispaapsi";
////		assertTrue(test.isPalindrome(input));
////	}
////	
//	@Test
//	void testToLowerCase() {
//		String input = "Wow";
//		String result = "wow";
//		assertEquals(result, test.toLowerCase(input));
//		
//		input = "alllowercase";
//		result = "alllowercase";
//		assertEquals(result, test.toLowerCase(input));
//		
//		input = "ALLUPPERCASE";
//		result = "alluppercase";
//		assertEquals(result, test.toLowerCase(input));
//		
//		input = "sOmeUppEr";
//		result = "someupper";
//		assertEquals(result, test.toLowerCase(input));
//		
//		input = "does this WORK?";
//		result = "does this work?";
//		assertEquals(result, test.toLowerCase(input));
//	}
//	@Test
//	void testBracketMatch() {
//		String s = "()";
//		assertTrue(test.isValid(s));
//
//		s = "()(()))";
//		assertTrue(!test.isValid(s));
//		
//		s = "((((";
//		assertTrue(!test.isValid(s));
//		
//		s = ")))))";
//		assertTrue(!test.isValid(s));
//		
//		s = "()[](()]";
//		assertTrue(!test.isValid(s));
//		
//		s = "({([])})";
//		assertTrue(test.isValid(s));
//		
//		s = "([([]))]";
//		assertTrue(!test.isValid(s));
//		
//		s = "([)]";
//		assertTrue(!test.isValid(s));
//		
//		s = "()[]{}";
//		assertTrue(test.isValid(s));
//	}
//
//	@Test
//	void testIntToRoman() {
//		int num = 3;
//		String result = "III";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 4;
//		result = "IV";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 8;
//		result = "VIII";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 9;
//		result = "IX";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 10;
//		result = "X";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 11;
//		result = "XI";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 40;
//		result = "XL";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 58;
//		result = "LVIII";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 68;
//		result = "LXVIII";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 100;
//		result = "C";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 101;
//		result = "CI";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 400;
//		result = "CD";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 875;
//		result = "DCCCLXXV";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 900;
//		result = "CM";
//		assertEquals(result, test.intToRoman(num));
//
//		num = 1994;
//		result = "MCMXCIV";
//		assertEquals(result, test.intToRoman(num));
//	}

}
