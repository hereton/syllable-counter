package oosyllablecounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * launch syllable counter.
 * 
 * @author Totsapon Menkul.
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		OOSyllableCounter oos = new OOSyllableCounter();
		String link = "https://se.cpe.ku.ac.th/dictionary.txt";
		URL url = new URL(link);
		InputStream in = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		int sylCount = 0;
		int wordCount = 0;
		String word;
		long startTime = System.nanoTime();
		final double NANO = 1E-9;
		while ((word = reader.readLine()) != null) {
			if (word.length() != 0) {
				sylCount += oos.countSyllables(word);
				wordCount++;
			}
		}
		System.out.println("Read file From " + link);
		System.out.printf("Counted %d syllables in %d words\n", sylCount, wordCount);
		System.out.printf("Elapse time: %.2f sec\n", (System.nanoTime() - startTime) * NANO);
	}
}
