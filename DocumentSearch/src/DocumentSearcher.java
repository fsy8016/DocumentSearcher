import java.util.ArrayList;
import java.util.Scanner;

public class DocumentSearcher {
	
	private static ArrayList<String> files = new ArrayList<String>() 
	{{ 
		add("french_armed_forces.txt"); 
		add("hitchhikers.txt");
		add("warp_drive.txt");
	}};
	
	public static void main(String [] args)
	{
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter the search term: ");
		String term = reader.nextLine();
		
		System.out.println("Search Method: 1) String Match 2) Regular Expression 3) Indexed");
		int method = reader.nextInt();
		reader.close();
		long current = System.currentTimeMillis();
		
		for(String file : files)
		{
			TermSearcher termSearcher = new TermSearcher(file);
			
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
