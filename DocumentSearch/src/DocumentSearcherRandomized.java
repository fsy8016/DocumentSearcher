import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class DocumentSearcherRandomized {
	
	private static ArrayList<String> _files = new ArrayList<String>() 
	{{ 
		add("french_armed_forces.txt"); 
		add("hitchhikers.txt");
		add("warp_drive.txt");
	}};
	
	public static void main(String [] args)
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("How many times do you want to run a randomized search?");
		int iterations = reader.nextInt(); 
		
		System.out.println("Search Method: 1) String Match 2) Regular Expression 3) Indexed");
		int method = reader.nextInt();
		
		reader.close();
		long current = System.currentTimeMillis();
		
		String file = _files.get(0);
		ArrayList<String> terms = new ArrayList<String>();
		
		for(int i = 0; i < iterations; i++)
		{
			int randomNumber = ThreadLocalRandom.current().nextInt(0, file.length() - 3);
			terms.add(file.substring(randomNumber, randomNumber + 2));
		}
		
		TermSearcher termSearcher = new TermSearcher(file);
		
		for(String term : terms)
		{
			if(method == 1)
			{
				int string = termSearcher.StringMatch(term);	
				System.out.println(file + " - " + string + " matches");
			}

			if(method == 2)
			{
				int regex = termSearcher.RegexMatch(term);	
				System.out.println(file + " - " + regex + " matches");
			}

			if(method == 3)
			{
				int index = termSearcher.IndexMatch(term);	
				System.out.println(file + " - " + index + " matches");			
			}
		}		
		
		long timeElapsed = System.currentTimeMillis() - current;
		System.out.println("Time elapsed: " + timeElapsed + " ms");	
	}

}
