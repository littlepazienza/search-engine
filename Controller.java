import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Controller {

	protected StopList stoplist;
	protected Set<Document> documents;
	protected InvertedIndex index;
	protected boolean trace;
	
	public Controller(StopList stoplist, List<String> docArray, boolean trace)
										throws FileNotFoundException
	{
		this.stoplist = stoplist;
		documents = new HashSet<Document>();
		fillDocSet(docArray);
		index = new InvertedIndex();
		this.trace = trace;
		fillIndex();
	}
	
	private void fillIndex()
	{
		for(Document d: documents)
		{
			for(String s: d.documentAsTest())
			{
				s = s.replaceAll("[^a-zA-Z]", "");
				index.add(s, d);
			}
		}
	}
	
	private void fillDocSet(List<String> docArray) throws FileNotFoundException
	{
		for(String s:docArray)
		{
			documents.add(new Document(s));
		}
	}
	
	public void retrieve(String querryString) throws FileNotFoundException
	{
		
		Scanner s = new Scanner(querryString);
		//s.useDelimiter("[^a-zA-Z]");
		s.useDelimiter(" ");
		Set<Document> searchResults = new HashSet<Document>();
		
		String querry = "";
		while(s.hasNext())
		{
			querry = s.next();
			if(!stoplist.contains(querry))
			{
				querry.replaceAll("[^a-zA-Z]", "");
				searchResults.addAll(index.search(querry));
				break;
			}
		}

		while(s.hasNext())
		{
			querry = s.next();
			if(!stoplist.contains(querry))
			{
				querry.replaceAll("[^a-zA-Z]", "");
				intersection(searchResults, index.search(querry));
			}
		}
		
		System.out.println("Search found in " + 
		                  (searchResults == null ? 0:searchResults.size()) + 
		                  " document(s)..." );
		for(Document d:searchResults)
		{
			System.out.print(d.documentFileName() + " > ");
			System.out.print(trace?new Scanner(
			          new File(d.documentFileName())).useDelimiter("\\A").next()
								+ "\n":"");
		}
	}

	public void intersection(Set<Document> finalSearch, Set<Document> search)
	{
		Set<Document> tempSet = new HashSet<>();

		if(search == null || search.size() == 0)
		{
			finalSearch.clear();
		}
		else
		{
			for(Document doc:search)
			{
				if(finalSearch.contains(doc) && search.contains(doc))
						tempSet.add(doc);
			}
		
			finalSearch.clear();
			finalSearch.addAll(tempSet);
		}
	}


}
