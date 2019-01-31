package com.cfranc.imple.markov;

import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        String sentences = readFileAsString("lafontaine.txt");
        System.out.println(sentences);
        
        Markov markov = new Markov(sentences);
        
        System.out.println("Generation :\n");
        System.out.println(markov.genMarkov(6));
        System.out.println(markov.genMarkov(9));
        System.out.println(markov.genMarkov(12));
        
    }

    public static String readFileAsString(String fileName)throws Exception 
    { 
      String data = ""; 
      data = new String(Files.readAllBytes(Paths.get(fileName))); 
      return data; 
    } 

}
