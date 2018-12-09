import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {

	Map<String, Set<Document>> index;
	
	public InvertedIndex()
	{
		index = new HashMap<String, Set<Document>>();
	}
	
	public void add(String w, Document d)
	{
		if(index.get(w) == null)
		{
			Set<Document> docSet = new HashSet<>();
			index.put(w, docSet);
		}
		index.get(w).add(d);
	}
	
	public Set<Document> search(String querry)
	{
		Set<Document> tempSet = index.get(querry);
		if(tempSet == null)
			return new HashSet<Document>();
		return tempSet;
	}
}
