import javax.swing.*;
import java.util.*;
import java.io.File;

public class ScorePage {
	
	//This is a function for the copy array
	public void copyArray(int A[], int B[])
	{
		for(int i=0;i<A.length;i++)
    	{
    		A[i]=B[i];
    	}
	}
	
	//Random the number from 1 to 100
	public static int randomNumber()
	{
		Random rand = new Random();
		int  n = rand.nextInt(100) + 1;
		return n;
	}
	
	//Put the random number to the array
	public static void randomScore(int[] score)
	{
		for(int i =0;i< 30;i++)
			score[i] = randomNumber();
	}
	
	//The function for get the score
	public static void totalScore(int frequencyScore[],int timeScore[],int numberScore[],int paidScore[], int totalScore[],
			HashMap<Integer,Integer> totalScoreHash,HashMap<Integer,Integer> manipulationScore)
	{
    	int numberPage=1;

		for(int i=0;i<30;i++)
    	{
    		frequencyScore[i] = randomNumber();
    		timeScore[i] = randomNumber();
    		numberScore[i] = randomNumber();
    		paidScore[i] = randomNumber();
    		
    		int total = frequencyScore[i] + timeScore[i] + numberScore[i] + paidScore[i];
    		int divide =0;
    		//The hashmap try to manipulate the score, 
    		if(manipulationScore.containsKey(total)){
    			while(manipulationScore.containsKey(total)){
    				total++;
    				//Still this is a random number and there is a possibility to have the same total score
    				//Then we use a manipulation
    			}
    			divide = total/4;
    			//We divide the score and put it into the array
    			frequencyScore[i] = divide; 
    			timeScore[i] = divide; 
    			numberScore[i] = divide; 
    			paidScore[i] = divide;
    		}
    		else
    			manipulationScore.put(total, 1);
    		
    		totalScore[i] = total;
    		totalScoreHash.put(total,numberPage);
    		numberPage++;
    	}
	}
	
	
}
