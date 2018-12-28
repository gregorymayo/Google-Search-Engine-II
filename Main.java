import java.util.HashMap;
import java.util.Scanner; 
import java.util.*;

public class Main {
	
	//Function that I used, so I don't change the actual array
	public static void copyArrayInt(int [] output, int [] input)
	{	
		for(int i=0;i<input.length;i++)
		{
			output[i]=input[i];
		}
	}
	//Function for showing option that the users can choose
	public static void showMenuScore()
	{
		System.out.println("This is the menu: ");		
		System.out.println("1. Search based on Page Rank ");
		System.out.println("2. Insert a URL");
		System.out.println("3. Delete a URL based on Page Rank");		
		System.out.println("4. Exit \n");
	}
	
	//For debugging function
	public static void printArray(int A[])
	{
		for(int i=0;i<A.length;i++)
    	{
    		System.out.println("Page "+ (i+1) + ": " + A[i]);
    	}
	}
	
	public static void main(String[] args)
    {
		
		String[] pageURL = new String[30];
		String[] copyURL = new String[30];
		
		//The code below is for the web crawler
        Spider spider = new Spider();

        String s; 
		Scanner sc = new Scanner(System.in);
 		System.out.println("Enter a string = "); 
 		s = sc.nextLine();  
		
        spider.search("http://arstechnica.com/", s, pageURL);
        
        //Declaring all the hashmap that we are going to use
		int page = 30;
        HashMap<Integer,Integer> totalScoreHash = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> manipulationScore = new HashMap<Integer,Integer>();
		HashMap<Integer,String> PageScoreandURL = new HashMap<Integer,String>();
		HashMap<String,Integer> URLandPageScore = new HashMap<String,Integer>();
		HashMap<Integer,Integer> PageRankandPageScore = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> PageScoreandPageRank = new HashMap<Integer,Integer>();

		//Declaring all the array that we are going to use
		int frequencyScore[] = new int[30];
		int timeScore[] = new int[30];
		int numberScore[] = new int[30];
		int paidScore[] = new int[30];
		int totalScore[] = new int[30];
		
		//Assigning all the random generator to get the total score page
		ScorePage score = new ScorePage();
		score.totalScore(frequencyScore,timeScore,numberScore,paidScore, totalScore,totalScoreHash,manipulationScore);
		
		int countThePage = 1;
		for (int i=0;i<30;i++)
        {
            System.out.println("The page rank = " + countThePage + "\tPage " + totalScoreHash.get(totalScore[i]) + " = " + totalScore[i] + " \tLink = " + pageURL[i]);
            PageScoreandURL.put(totalScore[i], pageURL[i]);
            URLandPageScore.put(pageURL[i], totalScore[i]);
            countThePage++;
        }
		
		boolean x = true;
		//Assign to copy array
		int totalScoreQS[] = new int[30];
		score.copyArray(totalScoreQS, totalScore);
		
		
		RedBlackTree RBtree = new RedBlackTree();
		//Inserting all of the score page with the insert function
		for(int i=0;i<30;i++)
		{
			RBtree.RBinsert(totalScoreQS[i]);
			
		}
		
		HashMap<Integer,String> PageScoreandColor = new HashMap<Integer,String>();
		
		//To print the red-black tree
		System.out.println("\nThe tree = \n");
		RBtree.inOrder(RBtree.root,PageScoreandColor,PageScoreandURL,PageRankandPageScore);
		System.out.println("\n");
		
		
		//To search a specific page rank
		System.out.print("Choose Page Rank you want to search= ");
    	Scanner in = new Scanner(System.in);
    	int inputPageRank2 = in.nextInt();
    	int pageRankoutput = inputPageRank2;

    	inputPageRank2++;
    	int totalScoreOutput =  PageRankandPageScore.get(inputPageRank2);
    	String colorOutput = PageScoreandColor.get(totalScoreOutput);
    	String urlOutput = PageScoreandURL.get(totalScoreOutput);
		System.out.println("Page Rank = " + pageRankoutput + "\tPage Score = " + totalScoreOutput + "\tColor = " + colorOutput + "\tURL = " + urlOutput + "\n");
		
		
		//To delete a specific URL by giving the page rank
		System.out.print("Choose URL you want to delete, by entering Page Rank= ");
    	
		Scanner input = new Scanner(System.in);
    	int inputPageRank3 = input.nextInt();
    	int pageRankoutput3 = inputPageRank3;

    	inputPageRank3++;
    	int totalScoreOutput3 =  PageRankandPageScore.get(inputPageRank3);
    	
    	RBtree.RBdelete(RBtree.root, totalScoreOutput3);
		RBtree.inOrder(RBtree.root,PageScoreandColor,PageScoreandURL,PageRankandPageScore);

		
		
    }
}
