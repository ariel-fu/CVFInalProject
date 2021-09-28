
public class StringManipulation {

	public String reorderSpaces(String text) {
		int numSpaces = 0;
		int numWords = 0;

		String[] textSplit = text.split("");
		for (int i = 0; i < textSplit.length; i++) {
			if (textSplit[i].equals(" ")) {
				numSpaces += 1;
			} else {
				numWords += 1;
				// get to the next space
				while (i < textSplit.length && !textSplit[i].equals(" ")) {

					i += 1;
				}
				if (i >= textSplit.length) {
					// add 1 to the num space for this curr space
					numSpaces += 1;
				}

			}
		}
		boolean addEnd = false;
		// add a space at the end if it does not evenly divide
		if (numSpaces % (numWords - 1) != 0) {
			addEnd = true;

		}

		numSpaces = numSpaces / (numWords - 1);

		String spaces = "";
		for (int i = 0; i < numSpaces; i++) {
			spaces += " ";
		}

		String spreadOutSpaces = "";
		for (int i = 0; i < textSplit.length; i++) {

			if (!textSplit[i].equals(" ")) {
				if (!spreadOutSpaces.equals("")) {
					// if this isn't the first word, add a space
					spreadOutSpaces += spaces;
				}
				// go to the end of the word
				while (!textSplit[i].equals(" ")) {
					spreadOutSpaces += textSplit[i];
					i += 1;
				}
			}
		}

		if (addEnd) {
			spreadOutSpaces += " ";
		}

		return spreadOutSpaces;
	}

	public boolean canConstruct(String ransomNote, String magazine) {
		if (ransomNote.length() > magazine.length()) {
			return false;
		}

		int[] noteCount = new int[26];
		int[] magCount = new int[26];

		for (int i = 0; i < ransomNote.length(); i++) {
			// get currIndex for the note
			int noteIndex = (int) (ransomNote.charAt(i) - 'a');
			noteCount[noteIndex] += 1;
			// get the currIndex for the mag
			int magIndex = (int) (magazine.charAt(i) - 'a');
			magCount[magIndex] += 1;

		}

		// finish up counting the magazine
		for (int i = ransomNote.length(); i < magazine.length(); i++) {
			// get the currIndex for the mag
			int magIndex = (int) (magazine.charAt(i) - 'a');
			magCount[magIndex] += 1;
		}

		// compare the two "hash maps"
		for (int i = 0; i < 26; i++) {
			// only want to compare if the note has this letter
			if (noteCount[i] != 0) {
				// as long as the magazine has the same letter and has at least an equal amount
				// of that letter, we can create the ransom note

				if (noteCount[i] > magCount[i]) {
					return false;
				}
			}

		}

		return true;

	}

	public String defangIPaddr(String address) {

		int length = address.length();
		String returnValue = "";

		for (int i = 0; i < length; i++) {
			char currChar = address.charAt(i);
			if (currChar == '.') {
				returnValue += "[.]";
			} else {
				returnValue += currChar;
			}
		}

		return returnValue;
	}

	public String interpret(String command) {
		String[] split = command.split("");
		String returnVal = "";
		for (int i = 0; i < split.length; i++) {
			String currString = split[i];
			if (currString.equals("(")) {
				if (split[i + 1].equals(")")) {
					returnVal += "o";
					i += 1;
				} else {
					returnVal += "al";
					i += 3;
				}
			} else {
				returnVal += currString;
			}
		}

		return returnVal;
	}

	public boolean isAnagram(String s, String t) {
		int[] sChars = new int[26];
		int[] tChars = new int[26];
		// if their lengths are the same, then t is not an anagram of s
		if (s.length() != t.length()) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			int sAscii = s.charAt(i) - 97;
			sChars[sAscii] += 1;

			int tAscii = t.charAt(i) - 97;
			tChars[tAscii] += 1;
		}

		for (int i = 0; i < sChars.length; i++) {
			// should have the same amount of chars
			if (sChars[i] != tChars[i]) {
				return false;
			}
		}

		return true;
	}

	public int firstUniqChar(String s) {
		// "hashmap" of lowercase English letteres
		int[] possibleChars = new int[26];
		// populate the hashmap
		for (int i = 0; i < s.length(); i++) {
			int currAscii = s.charAt(i) - 97;
			int numAppear = possibleChars[currAscii];
			numAppear++;
			possibleChars[currAscii] = numAppear;
		}

		for (int i = 0; i < s.length(); i++) {
			int currAscii = s.charAt(i) - 97;
			int numAppear = possibleChars[currAscii];
			if (numAppear == 1) {
				return i;
			}
		}

		// not found
		return -1;
	}

	public int reverse(int x) {
		String xString = String.valueOf(x);

		String[] xArray = xString.split("");

		int startIndex = 0;
		int endIndex = xArray.length - 1;

		while (startIndex < endIndex) {
			String temp = xArray[startIndex];
			xArray[startIndex] = xArray[endIndex];
			xArray[endIndex] = temp;
			startIndex++;
			endIndex--;
		}

		String convert = "";
		if (xArray[xArray.length - 1].equals("-")) {
			// if the number was a negative, then we append the negative to the front
			convert += xArray[xArray.length - 1];
			// and, we only loop from 0 to xArray.length-1
			for (int i = 0; i < xArray.length - 1; i++) {
				convert += xArray[i];
			}
		} else {

			for (int i = 0; i < xArray.length; i++) {
				convert += xArray[i];
			}
		}

		try {
			Integer ret = Integer.valueOf(convert);
			return (int) ret;
		} catch (Exception e) {
			// value is outside of the signed 32-bit integer range
			return 0;
		}

	}

	public int strStr(String haystack, String needle) {
		if (needle.equals("")) {
			return 0;
		}

		if (haystack.equals("")) {
			return -1;
		}

		String[] needleSplit = needle.split("");
		for (int i = 0; i < haystack.length(); i++) {
			String currCharString = haystack.charAt(i) + "";
			// if the first char in the needle matches, see if the rest of the string
			// matches
			if (needleSplit[0].equals(currCharString)) {
				// set the hayIndex and loop through the haystack, comparing each value to the
				// needle
				int haystackIndex = i;
				boolean isIn = true;

				for (int needleIndex = 0; needleIndex < needle.length(); needleIndex++) {
					if (haystackIndex >= haystack.length()) {
						// if we ran out of "hay," then the needle can't be in the haystack. quit here
						isIn = false;
						break;
					}
					// if we find one char that isn't in the needle, we can quit
					if (haystack.charAt(haystackIndex) != needle.charAt(needleIndex)) {
						isIn = false;
						break;
					} else {
						// else, continue comparing
						haystackIndex++;
					}
				}

				// want to check if the needle was in the haystack
				if (isIn) {
					return i; // first appearance.
				}
			}
		}
		// else, the needle is not part of haystack
		return -1;
	}

	public String reverseWords(String s) {
		// split into an array of words
		// strip any leading or trailing spaces
		s = s.strip();
		String[] split = s.split(" ");
		int initialIndex = 0;
		int finalIndex = split.length - 1;
		while (initialIndex < finalIndex) {
			String temp = split[initialIndex];
			split[initialIndex] = split[finalIndex];
			split[finalIndex] = temp;
			initialIndex++;
			finalIndex--;
		}
		String ret = "";
		for (int i = 0; i < split.length - 1; i++) {
			String curr = split[i];
			// only add if the curr string is not a space
			if (!curr.equals("")) {
				ret += curr;
				ret += " ";
			}

		}
		// attatch the last word
		ret += split[split.length - 1];

		return ret;
	}

	public char[] reverseString2(char[] s) {
		if (s == null) {
			return null;
		}
		int initialIndex = 0;
		int finalIndex = s.length - 1;

		while (initialIndex < finalIndex) {
			// swap initial and final
			char temp = s[initialIndex];
			s[initialIndex] = s[finalIndex];
			s[finalIndex] = temp;

			// increment initialIndex
			initialIndex++;
			// decrement final index
			finalIndex--;
		}

		// has been successfully reversed
		return s;
	}

	public void reverseString(char[] s) {
		if (s == null) {
			return;
		}
		int initialIndex = 0;
		int finalIndex = s.length - 1;

		while (initialIndex < finalIndex) {
			// swap initial and final
			char temp = s[initialIndex];
			s[initialIndex] = s[finalIndex];
			s[finalIndex] = temp;

			// increment initialIndex
			initialIndex++;
			// decrement final index
			finalIndex--;
		}

		// has been successfully reversed
		return;
	}

	public int countSubstrings(String s) {
		int total = 0;
		for (int i = 0; i < s.length(); i++) {
			// expand around an odd string
			total += count(s, i, i);

			// expand around an even string
			total += count(s, i, i + 1);

		}
		return total;
	}

	private int count(String s, int firstIndex, int lastIndex) {
		int count = 0;
		while (firstIndex > -1 && lastIndex < s.length()) {
			if (s.charAt(firstIndex) != s.charAt(lastIndex)) {
				break; // not a palindrome, stop the counts
			}

			firstIndex--;
			lastIndex++;
			count++;
		}
		return count;
	}

	public String toLowerCase(String str) {
		// ascii 'a' = 0097 ascii 'A' = 0065
		// 'a' = 'A'+32
		String ret = "";
		for (int i = 0; i < str.length(); i++) {
			char currChar = str.charAt(i);
			int currAscii = (int) currChar;
			// if the value is an upper case, it is less than 97
			if (65 <= currAscii && currAscii <= 90) {
				// add 32 to get the lowercase counterpart
				currAscii += 32;
				// convert back to char
				currChar = (char) currAscii;
			}
			// add currChar to ret string
			ret += currChar;
		}
		return ret;
	}

	public boolean isValid(String s) {
		char[] stack = new char[s.length()];
		int headIndex = -1; // no head yet.
		int stackIndex = 0;
		// push all of the right facing brackets onto the Stack
		for (int i = 0; i < s.length(); i++) {
			char currBracket = s.charAt(i);
			if (isRightFacing(currBracket)) {
				// "push" onto Stack
				stack[stackIndex] = currBracket;
				stackIndex++;
				headIndex++;
			} else {
				// when we encounter a left-facing bracket:
				if (headIndex < 0) {
					// no more right-facing brackets, so it is invalid
					return false;
				}
				// "pop" a bracket off of the stack and compare

				char pop = stack[headIndex];
				// decrement the head and stack index
				headIndex--;
				stackIndex--;
				// if they are not the same "type", then the string is not valid
				if (!isSameType(pop, currBracket)) {
					return false;
				}
			}
		}

		// there are still values in the stack
		if (headIndex != -1) {
			return false; // invalid string
		}

		return true;

	}

	/**
	 * returns whether or not the two brackets are of the same type
	 * 
	 * @param brack1 - right facing (opens towards the right)
	 * @param brack2 - left facing (opens towards the left)
	 * @return
	 */
	private boolean isSameType(char brack1, char brack2) {
		if (brack1 == '{') {
			return brack2 == '}';
		} else if (brack1 == '(') {
			return brack2 == ')';
		} else {
			return brack2 == ']';
		}
	}

	private boolean isRightFacing(char c) {
		if (c == '{') {
			return true;
		} else if (c == '(') {
			return true;
		} else if (c == '[') {
			return true;
		}
		return false;
	}

	public String intToRoman(int num) {
		String numString = String.valueOf(num);
		String[] numArr = numString.split("");
		for (int i = 0; i < numString.length(); i++) {
			String currDigit = numArr[i];
			for (int j = numString.length() - 1; j > i; j--) {
				// add 0 padding
				currDigit += "0";
			}
			// replace the currDigit with the number
			numArr[i] = currDigit;
		}
		String ret = "";
		for (int i = 0; i < numArr.length; i++) {
			String curr = numArr[i];
			// thousands
			if (curr.length() == 4) {
				int thousands = Integer.valueOf(curr) / 1000;
				// add an "M" for every thousand
				for (int j = 0; j < thousands; j++) {
					ret += "M";
				}
			} else if (curr.length() == 3) {
				// Hundredth
				int hundreds = Integer.valueOf(curr);
				// 900 is a special case where we need to add "CM" instead of MMMMMMMM
				if (hundreds == 900) {
					ret += "CM";
					continue; // nothing left to do here
				} else if (hundreds == 400) {
					// 400 is another special case where we add "CD"
					ret += "CD";
					continue; // nothing left to do here
				} else if (hundreds >= 500) {
					hundreds -= 500;
					// add "D" to ret
					ret += "D";
				}
				// add "C" for the rest of the hundreds after 500
				int numTimesLoop = hundreds / 100;
				for (int j = 0; j < numTimesLoop; j++) {
					ret += "C";
				}

			} else if (curr.length() == 2) {
				// tens
				int tens = Integer.valueOf(curr);
				// 90 is a special case where we need to add "XC"
				if (tens == 90) {
					ret += "XC";
					continue; // nothing left to do here
				} else if (tens == 40) {
					// 40 is another special case where we add "XL"
					ret += "XL";
					continue; // nothing left to do here
				} else if (tens >= 50) {
					tens -= 50;
					// add "L" to ret
					ret += "L";
				}
				// add "X" for the rest of the tens after 50
				int numTimesLoop = tens / 10;
				for (int j = 0; j < numTimesLoop; j++) {
					ret += "X";
				}
			} else {
				// ones
				int ones = Integer.valueOf(curr);
				// 9 is a special case where we need to add "IX"
				if (ones == 9) {
					ret += "IX";
					continue; // nothing left to do here
				} else if (ones == 4) {
					// 4 is another special case where we add "IV"
					ret += "IV";
					continue; // nothing left to do here
				} else if (ones >= 5) {
					ones -= 5;
					// add "V" to ret
					ret += "V";
				}
				// add "I" for the rest of the ones after 5
				for (int j = 0; j < ones; j++) {
					ret += "I";
				}
			}
		}
		return ret;
	}

	public int lengthOfLongestSubstring(String s) {
		Character[] possibleCharacters = new Character[255];
		// start at 0
		int longestLength = 0;
		int currLength = 0;

		for (int i = 0; i < s.length(); i++) {
			// get the current char and its ascii value
			int currIndex = i;
			char currChar = s.charAt(currIndex);
			int currAscii = (int) currChar;
			// while we haven't reached a duplicate
			while (possibleCharacters[currAscii] == null) {
				// increment the length
				currLength++;
				// set the char as visited
				possibleCharacters[currAscii] = currChar;

				currIndex++;
				if (currIndex < s.length()) {
					// get the next char if we aren't at the end of the string yet
					currChar = s.charAt(currIndex);
					currAscii = (int) currChar;
				} else {
					// if we are at the end of the string, end the while loop
					break;
				}
			}
			// if we found the longest le ngth so far, set currLength as the longest length
			if (currLength > longestLength) {
				longestLength = currLength;
			}
			// reset currLength
			currLength = 0;
			// reset the character array to nulls
			possibleCharacters = new Character[255];
		}

		return longestLength;
	}
}
