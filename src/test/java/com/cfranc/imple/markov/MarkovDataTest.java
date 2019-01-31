package com.cfranc.imple.markov;

import static org.junit.Assert.*;

import org.junit.Test;

public class MarkovDataTest {

	@Test
	public void testRenforceWord() {
		MarkovData m = new MarkovData("Il était une fois");
               assertEquals(false, m.renforceWord("Il", "était") );            
	}
        
        @Test
        public void testRenforceWordTrue() {
            MarkovData m = new MarkovData("Il était une fois");
            assertEquals(false, m.renforceWord("Il", "était") );
        }

        @Test
        public void testGetLearnedWordTrue(){
            MarkovData m = new MarkovData("Il était une fois");
            m.learnWord("était", "une");
            assertEquals("une",m.getLearnedWord("était"));
        }
        
        @Test
        public void testGetLearnedWordFalse(){
            MarkovData m = new MarkovData("Il était une fois");
            m.learnWord("était", "une");
            assertEquals(null,m.getLearnedWord("bonjour"));           
        }
        
        @Test
        public void testLearnWordT(){
            MarkovData m = new MarkovData("Il était une fois");
            m.learnWord("dans", "la");
            assertEquals("la",m.getLearnedWord("dans"));
        }
        
        @Test
        public void testLearnWordF(){
            MarkovData m = new MarkovData("Il était une fois");
            m.learnWord("dans", "la");
            assertEquals(null,m.getLearnedWord("une"));
        }
        
        @Test
        public void testgetKeyWordT(){
            MarkovData m = new MarkovData("Il était une fois");            
            assertEquals("Il",m.getKeyWord(0));
        }
        
        @Test
        public void testKeyWordSize(){
            MarkovData m = new MarkovData("Il était une fois");            
            assertEquals(4,m.keyWordSize());
        }
        
        
        
        
}
