package com.cfranc.imple.markov;

import static org.junit.Assert.*;

import org.junit.Test;

//@author: Florian Sari
public class MarkovTest {

	@Test
	public void testDataFalse() {
            Markov m = new Markov("Il était une fois");
            assertEquals(false, m.data.containsKeyWord("fois"));
        }
        
        @Test
	public void testGenMark() {
            Markov m = new Markov("Il était une fois");
            assertEquals("était une", m.genMarkov(0));
        }
        @Test
	public void testGenMarklearn() {
            Markov m = new Markov("Il était une fois");
            m.learn("dans une foret lointaine");
            assertEquals("dans une", m.genMarkov(4));
        }
        
        @Test
        public void testGenMarkLongueChaine(){
           Markov m = new Markov("Il était une fois dans un pays tres lointain"); 
           assertEquals("dans un pays", m.genMarkov(5));
        }

        

}
