import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Document {

	protected String filename;
	protected Set<String> rawText;
	
	public Document(String str) throws FileNotFoundException
	{
		this.filename = str;
		rawText = new HashSet<String>();
		fillDocArray(str);
	}
	
	private void fillDocArray(String doc) throws FileNotFoundException
	{
		Scanner stan = new Scanner(new File(doc));
		
		while(stan.hasNext())
		{
			rawText.add(stan.next());
		}
		
		stan.close();
	}
	
	public Set<String> documentAsTest()
	{
		return rawText;
	}
	
	public String documentFileName()
	{
		return filename;
	}

	@Override
	public boolean equals(Object arg0) {
		return filename.equals( ((Document)arg0).filename);
	}
}


