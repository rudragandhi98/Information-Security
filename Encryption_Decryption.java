package Project1;
/**
 * CS 166 Programming Assignment 1
 * @date 9/22/2019
 * @author Rudra Gandhi 
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javax.crypto.Cipher;

/**
 * 
 * This class will implement simple substitution cipher permutations. Which will allow us to encrypt and decrypt. 
 * Then I implemented Letter Frequency Analysis.
 * Lastly, I applied Letter Frequency Analysis to chosen outputs of my Simple Substitution encryption.
 * 
 */
public class Encryption_Decryption 
{
	private ArrayList<FreqAnalyzer> freqanalysis;
	/**
	 * The following method is the constructor.
	 */
	public Encryption_Decryption() 
	{
		freqanalysis = new ArrayList<FreqAnalyzer>();
	}

	/**
	 * Implementation of encryption using simple substitution cipher permutations. 
	 * @param plainText
	 * @param shift
	 * @return 
	 */
	public String encrypt(String plainText, int shift) 
	{
		if(shift > 26) { //place shift between 0 and 26 if it is greater than 26 or less than 
			shift = shift%26;
		}
		else if (shift < 0) {
			shift = (shift%26)+26;
		}
		String cipher = "";
		int length_plaintext = plainText.length();
		for(int i=0; i<length_plaintext; i++) {
			char ch = plainText.charAt(i);
			if(Character.isLetter(ch)) {
				if(Character.isLowerCase(ch)) { //Lowercase 
					char letter = (char)(ch+shift);  
					if(letter > 'z'){
						cipher = cipher + (char)(ch - (26-shift));
					}
					else{
						cipher = cipher + letter;
					}
				}
				else if(Character.isUpperCase(ch)) { //Uppercase
					char letter = (char)(ch+shift);  
					if(letter > 'Z'){
						cipher = cipher + (char)(ch - (26-shift));
					}
					else {
						cipher = cipher + letter;
					}
				}
			}
			else {
				cipher = cipher + ch;
			}
		}
		return cipher;
	}

	/**
	 * Implementation of decryption using simple substitution cipher permutations. 
	 * @param plainText
	 * @param shift
	 * @return
	 */
	public String decrypt(String plainText, int shift) 
	{
		if(shift > 26) { //place shift between 0 and 26 if it is greater than 26 or less than 
			shift = shift%26;
		}
		else if (shift < 0) {
			shift = (shift%26)+26;
		}
		String cipher = "";
		int length_plaintext = plainText.length();
		for(int i=0; i<length_plaintext; i++) {
			char ch = plainText.charAt(i);
			if(Character.isLetter(ch)) {
				if(Character.isLowerCase(ch)) { //Lowercase
					char letter = (char)(ch-shift);  
					if(letter < 'a'){
						cipher = cipher + (char)(ch + (26-shift));
					}
					else {
						cipher = cipher + letter;
					}
				}
				else if(Character.isUpperCase(ch)) { //Uppercase
					char letter = (char)(ch-shift);  
					if(letter < 'A'){
						cipher = cipher + (char)(ch + (26-shift));
					}
					else {
						cipher = cipher + letter;
					}
				}
			}
			else {
				cipher = cipher + ch;
			}
		}

		return cipher;
	}


	/**
	 * Implementation of Letter Frequency Analysis and considered both lowercase and uppercase 
	 * @param ciphertext
	 * @return
	 */
	public int[] Frequency_Analysis(String ciphertext) 
	{ 
		int[] Frequency_letter = new int[26];
		for(int i=0;i<ciphertext.length(); i++) {
			if(Character.isUpperCase(ciphertext.charAt(i))) {
				Frequency_letter[ciphertext.charAt(i)-65]++;
			}
			else if(Character.isLowerCase(ciphertext.charAt(i))) {
				Frequency_letter[ciphertext.charAt(i)-97]++;
			}

		}
		for(int i=0;i<Frequency_letter.length; i++) {
			System.out.println(i+" "+Character.toString ((char) ((char)i+65))+":"+ Frequency_letter[i]);
			FreqAnalyzer letter = new FreqAnalyzer(Character.toString ((char) ((char)i+65)), Frequency_letter[i]);
			freqanalysis.add(letter);
		}
		return Frequency_letter;
	}

	/**
	 * Implemented Max, to find the max index which we then want to replace with the most frequent english text. 
	 * @param ciphertext
	 * @param Frequency_letter
	 * @return
	 */
	public int Max(String ciphertext, int[] Frequency_letter)
	{
		int max =0;
		int maxIndex = 0;
		for(int i =0; i<Frequency_letter.length;i++) {
			if(Frequency_letter[i] > max)
			{
				max=Frequency_letter[i];
				maxIndex=i;
			}
		}
		return maxIndex;
	}

	/**
	 * This helps us replace the maxIndex with englishText
	 * @param ciphertext
	 * @param Frequency_letter
	 * @return
	 */
	public String Attack(String ciphertext, int[] Frequency_letter)
	{
		String[] englishText = new String[26];
		englishText[0] = "e";
		englishText[1] = "t";
		englishText[2] = "a";
		englishText[3] = "o";
		englishText[4] = "i";
		englishText[5] = "n";
		englishText[6] = "s";
		englishText[7] = "r";
		englishText[8] = "h";
		englishText[9] = "l";
		englishText[10] = "d";
		englishText[11] = "c";
		englishText[12] = "u";
		englishText[13] = "m";
		englishText[14] = "f";
		englishText[15] = "p";
		englishText[16] = "g";
		englishText[17] = "w";
		englishText[18] = "y";
		englishText[19] = "b";
		englishText[20] = "v";
		englishText[21] = "k";
		englishText[22] = "x";
		englishText[23] = "j";
		englishText[24] = "a";
		englishText[25] = "z";

		String attackString = ciphertext.toUpperCase();

		for(int i=0; i<getDistinctNumberOfLetters(ciphertext);i++) {

			int m = Max(ciphertext, Frequency_letter);
			int maxIndex = m;

			System.out.println(attackString);
			System.out.println(freqanalysis.get(maxIndex).getLetter()+ "is supposed to  be replaced with "+englishText[i]+ " "+ m);
			char regex = freqanalysis.get(maxIndex).getLetter().charAt(0);
			char replacement = englishText[i].charAt(0);
			attackString = attackString.replace(regex, replacement);
			Frequency_letter[m] = -100;	
		}
		return attackString;
	}


	/**
	 * This returns the distinct number of letters that are repeating. 
	 * @param ciphertext
	 * @return
	 */
	private int getDistinctNumberOfLetters(String ciphertext) 
	{
		HashSet<Character> hash = new HashSet<Character>();
		for(int i=0; i<ciphertext.length();i++) {
			hash.add(ciphertext.charAt(i));
		}
		return hash.size();
	}


	public static void main(String[] args)
	{
		String text = "ILOVEELEPHANTS";
		Encryption_Decryption ed = new Encryption_Decryption();
		String cipher = ed.encrypt(text, 4);
		System.out.println(cipher);
		String decrypt = ed.decrypt(cipher,4);
		System.out.println(decrypt);
		int[] freq = ed.Frequency_Analysis(cipher);
		ed.Max(cipher, freq);
		String AttackCypher = ed.Attack(cipher, freq);
		System.out.println(AttackCypher);
	}
}

