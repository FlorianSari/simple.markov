package com.cfranc.imple.markov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkovData {
	
	private Map<String, String> markovTable = new HashMap<String,String>();
	private List<String> textwords = new ArrayList<String>();

	public MarkovData(String text) {
		this.textwords = Arrays.asList(text.split(" "));
	}
	
	public boolean renforceWord(String keyString, String wordToLearn) {
		String value;
		boolean isNewKey = containsKeyWord(keyString);
		if(isNewKey)	// if chain in list then add extra word
		{
			value = getLearnedWord(keyString);
			value += " " + wordToLearn;
			learnWord(keyString, value);
		}
		return isNewKey;
	}

	String getLearnedWord(String keyString) {
		return this.markovTable.get(keyString);
	}

	boolean containsKeyWord(String keyString) {
		return this.markovTable.containsKey(keyString);
	}

	public void learnWord(String keyString, String learnWord) {
		this.markovTable.put(keyString, learnWord);
	}
	
	public String getKeyWord(int k) {
		return this.textwords.get(k);
	}

	public int keyWordSize() {
		return this.textwords.size();
	}
}