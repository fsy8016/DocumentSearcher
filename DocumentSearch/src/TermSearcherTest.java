import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class TermSearcherTest {
	
	static ArrayList<String> _files = new ArrayList<String>() 
	{{ 
		add("french_armed_forces.txt"); 
		add("hitchhikers.txt");
		add("warp_drive.txt");
	}};
    
	@Test
	void testStringMatch() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<Integer> expected = new ArrayList<Integer>() 
		{{  
			add(59);
			add(24);
			add(9);
		}};
		
		
		for(String file : _files)
		{
			TermSearcher termSearcher = new TermSearcher(file);
			results.add(termSearcher.StringMatch("the"));
		}
		
		assertEquals(results, expected);
	}
	
	@Test
	void testRegexMatch() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<Integer> expected = new ArrayList<Integer>() 
		{{  
			add(59);
			add(24);
			add(9);
		}};
		
		
		for(String file : _files)
		{
			TermSearcher termSearcher = new TermSearcher(file);
			results.add(termSearcher.RegexMatch("the"));
		}
		
		assertEquals(results, expected);
	}
	
	@Test
	void testIndexMatch() {
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<Integer> expected = new ArrayList<Integer>() 
		{{  
			add(59);
			add(24);
			add(9);
		}};
		
		
		for(String file : _files)
		{
			TermSearcher termSearcher = new TermSearcher(file);
			results.add(termSearcher.IndexMatch("the"));
		}
		
		assertEquals(results, expected);
	}
	
	@Test
	void testNullSearchTerm() {

		TermSearcher termSearcher = new TermSearcher(_files.get(0));
		int result = termSearcher.IndexMatch(null);
		
		assertEquals(0, result);
	}
	
	@Test
	void testNullFile() {

		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<Integer> expected = new ArrayList<Integer>() 
		{{  
			add(0);
			add(0);
			add(0);
		}};
		
		
		TermSearcher termSearcher = new TermSearcher(null);
		results.add(termSearcher.IndexMatch("the"));
		results.add(termSearcher.RegexMatch("the"));
		results.add(termSearcher.StringMatch("the"));
		
		assertEquals(results, expected);
	}

}
