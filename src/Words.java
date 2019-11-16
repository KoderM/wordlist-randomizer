import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Words {

	BufferedReader in;
	BufferedWriter out;
	Set<String> words;
	ArrayList<String> wordList;
	Random rand;

	public Words() throws IOException {
		out = new BufferedWriter(new FileWriter("WordList.txt", true));
		in = new BufferedReader(new FileReader("WordList.txt"));
		words = new HashSet<String>();
		rand = new Random();
	}

	public ArrayList<String> getWords() {
		try {
			String word;
			while ((word = in.readLine()) != null) {
				if(!word.isEmpty()) {
					words.add(word);
				}
			}
			wordList = new ArrayList<String>(words);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordList;
	}

	public String getRandomWord() {
		int wordPosition = rand.nextInt(wordList.size());
		String word = wordList.get(wordPosition);
		wordList.remove(wordPosition);

		if (wordList.size() == 0) {
			getWords();
		}

		return word;
	}

	public void addWord(String word) {
		if(word.isEmpty()) {
			return;
		}
		try {
			out.write(word);
			out.newLine();
			out.flush();
			words.add(word);
			wordList = new ArrayList<String>(words);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeWord(String wordToRemove) {
		try {
			words.remove(wordToRemove);
			wordList.remove(wordToRemove);
			out = new BufferedWriter(new FileWriter("WordList.txt"));
			out = new BufferedWriter(new FileWriter("WordList.txt", true));
			for(String word: wordList) {
				out.write(word);
				out.newLine();
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
