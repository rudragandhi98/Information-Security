package Project1;

/**
 * CS 166 Programming Assignment 1
 * @date 9/22/2019
 * @author Rudra Gandhi 
 *
 */
public class FreqAnalyzer {
	
	private String letter;
	private int frequency;

	/**
	 * @param letter
	 * @param frequency
	 */
	public FreqAnalyzer(String letter, int frequency) {
		this.letter = letter;
		this.frequency = frequency;
	}

	/**
	 * @return the letter
	 */
	public String getLetter() {
		return letter;
	}

	/**
	 * @param letter the letter to set
	 */
	public void setLetter(String letter) {
		this.letter = letter;
	}

	/**
	 * @return the frequency
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
