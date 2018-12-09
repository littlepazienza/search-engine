import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
 interface AView
{	
	public abstract void setController(Controller viewController);
	
	public abstract void run();
}
 public class CLI implements AView
{
	protected Controller controller;
 	public CLI(Controller c)
	{
		setController(c);
		run();
		
	}
	
	public void setController(Controller viewController)
	{
		controller = viewController;
	}
	
	public void run()
	{		
		Scanner input = new Scanner(System.in);
		String searchQuerry = "";
		
		System.out.println("Enter you search querry(s) then press ENTER to make"
				+ " the search\nPress ENTER without a search to EXIT >>>");
	
		searchQuerry = input.nextLine();
		
		if(searchQuerry.equals(""))
			return;
		
		do
		{
			try
			{
				controller.retrieve(searchQuerry);
			}catch(FileNotFoundException e)
			{
				System.out.println("At least one of the files given did not exist");
			}
 			System.out.println("Enter you search querry(s) then press ENTER to make "
					+ "the search\nPress ENTER without a search to EXIT >>>");
		
			searchQuerry = new Scanner(System.in).nextLine();
		} while(!searchQuerry.equals(""));
	}
	
	public static void main(String[] args) throws FileNotFoundException 
	{		
		//setup of index mapping of words and documents
		ArrayList<String> argList;
		boolean trace;
		StopList stopper;
		
		//three parameters (argslist becomes docList after stripping)
		argList = new ArrayList<String>(Arrays.asList(args));
		trace = argList.remove("-d");
		stopper = new StopList(argList.remove(0));
 		
		CLI theDriver = new CLI(new Controller(stopper, argList, trace));		
	}
}
