package com.cfranc.imple.markov;

/*
 * The class creates a Markov chain frequency table and then uses it to generate sentences.
 * The quality of the sentence will depend entirely on the input text. The larger and more
 * grammatically correct the text, the better the generated sentence.
 * 
 * You can use the ngram parameter to determine the length of the word chain. Default is 2.
 * 
 * Usage: 
 * 
 * Markov mc = new Markov(String text); // where text is a string full of words separated by spaces.
 * 
 * To generate a sentence:
 * String sentence = mc.genMarkov(int words); // where words is the number of words to generate
 * 
 * (c) thecodecentre.co.uk 2014
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Markov 
{
	
	private static int ngram = 2;		// markov chain ngram parameter (contiguous sequence of words)
    MarkovData data = null;

	public Markov (String text)		// constructor
	{
        learn(text);        
	}

	void learn(String text) {
		data = new MarkovData(text);
		
        int maxwords = data.keyWordSize() - ngram - 1;
        String keyString = null;
        int end;
        
        for (int j = 0; j < maxwords; j++) 
		{
            keyString = "";
            end = j + ngram;
            for(int k = j; k < end; k++) 
            {
                keyString = keyString + data.getKeyWord(k) + " ";
            }
			keyString = keyString.trim();		// get rid of trailing spaces
			
			String wordToLearn = data.getKeyWord(end);
			
			boolean isNewKey = data.renforceWord(keyString, wordToLearn);
			if(!isNewKey) 
			{
				if(end <= maxwords)
				{
					data.learnWord(keyString, wordToLearn);
				}
			}
        }
	}


    public String genMarkov(int numWords)
    {
    // Build a random string using the above Markov chain table
        String buffer = "";
        String newword = "";
        String keyString = "";
        
        Random rgen = new Random();		// initialise random number generator
        List<String> lastwords = new ArrayList<String>();
        int possible = data.keyWordSize() - ngram;
		int startnum = rgen.nextInt(possible);				
        for (int i = startnum, j = 0; i < startnum+ngram; i++,j++) 	//get the random start word chain
		{
        	newword = data.getKeyWord(i);
			lastwords.add(j, newword);
            buffer += newword + " ";
        }

        for(int i = ngram; i < numWords; i++)
		{
            keyString = "";
            for (int j = 0; j < ngram; j++)
            {
                keyString = keyString + lastwords.get(j) + " ";
            }
			keyString = keyString.trim();		// get rid of trailing spaces
			
			if(data.containsKeyWord(keyString))	// if ngram in list then add next word
			{
		        List<String> possiblenext = new ArrayList<String>();
                possiblenext = Arrays.asList(data.getLearnedWord(keyString).split(" "));
                int c = possiblenext.size();	//must be at least 1
               	int r = rgen.nextInt(c);
                
                String nextword = possiblenext.get(r);
                buffer += nextword +" ";
                for (int j = 0; j < ngram-1; j++) 
				{
                    lastwords.set(j, lastwords.get(j+1));	// shift words to the left
                }
                lastwords.set(ngram-1, nextword);			// add the next word to end
            }
        }

         return buffer.trim();
    }    	

}