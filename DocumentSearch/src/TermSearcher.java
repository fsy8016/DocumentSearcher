import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermSearcher {
	
	private String _words = "";
	private HashMap<Character, ArrayList<Integer>> _lookupTable = new HashMap<Character, ArrayList<Integer>>();	
    	
    public TermSearcher(String file)
    {
    	if(file == null)
    		return;
    	
    	try
    	{
    		//get the file
    		java.net.URL url = getClass().getResource(file);
        	FileInputStream fis = new FileInputStream(url.getPath());
        	
        	int content;
    		while ((content = fis.read()) != -1) 
    		{
    			// convert to char and initialize _words
    			_words += ((char) content);
    		}  
    		
    		fis.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());   		
    	}
    	
        //initialize the LookupTable
        _lookupTable.clear();
        
        for (int i = 0; i < _words.length(); i++)
        {
            char[] wordsCharArray = _words.toCharArray();

            if (_lookupTable.containsKey(wordsCharArray[i]))
            {
            	//append onto the bucket
            	ArrayList<Integer> charList = _lookupTable.get(wordsCharArray[i]);
            	
            	if(charList != null)
            	{
                	charList.add(i);           	
                	_lookupTable.put(wordsCharArray[i], charList);
            	}
            }
            else
            {
                //add a new bucket for letter
            	ArrayList<Integer> list = new ArrayList<Integer>();
            	list.add(i);         	
                _lookupTable.put(wordsCharArray[i], list);
            }
        }
    }
    
    /*
     * Looks for search term given the initialized path contents via
     * direct string compare.
     */
    public int StringMatch(String searchTerm)
    {
        int count = 0;
        
        if(searchTerm == null)
        	return 0;

        for (int i = 0; i < _words.length(); i++)
        {
            if (i + searchTerm.length() > _words.length())
                break;

            String wordSubstring = _words.substring(i, i + searchTerm.length());

            if (wordSubstring.equals(searchTerm))
            {
                count++;
                continue;
            }
        }

        return count;
    }
    
    /*
     * Looks for search term given the initialized path contents via
     * the regex library.
     */
    public int RegexMatch(String searchTerm)
    {
        if(searchTerm == null)
        	return 0;
        
    	Pattern pattern = Pattern.compile(searchTerm);
    	Matcher matcher = pattern.matcher(_words);
    	
    	int count = 0;
    	while (matcher.find())
    	    count++;
    	
        return count;      
    }
    
    /*
     * Looks for search term given the initialized path contents via
     * a pre-processed hashmap of char occurrences.
     */
    public int IndexMatch(String searchTerm)
    {
        if(searchTerm == null)
        	return 0;
        
        int count = 0;
        char firstLetter = searchTerm.toCharArray()[0];

        if(!_lookupTable.containsKey(firstLetter))
            return 0;

        ArrayList<Integer> hits = _lookupTable.get(firstLetter);

        for(int indexHit : hits)
        {
            String indexWord = _words.substring(indexHit, indexHit + searchTerm.length());

            if (indexWord.equals(searchTerm))
                count++;
        }

        return count;
    }

}
