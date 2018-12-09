import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StopList 
{
	
	protected Set<String> stopWords;
	public StopList(String fileName) throws FileNotFoundException
	{
		stopWords = new HashSet<>();
		initialize(fileName);
	}
	
	private void initialize(String str) throws FileNotFoundException
	{
		Scanner s = new Scanner(new File(str));
		
		while(s.hasNextLine())
		{
			stopWords.add(s.nextLine());
		}
		
		s.close();
	}
	
	public boolean contains(String s)
	{
		return stopWords.contains(s);
	}
}
