import java.io.*;
import java.util.*;


public class MyProgram {
	
	
String putString[] = new String[3];
int countOfFileWord = 0;
int initialValue=0;

public class Word
{
		String[] word = new String[1000];
		
		Word(String[] name)
		{
			this.word[initialValue] = name[initialValue];
			initialValue++;
		}
}



public class Vocabulary extends Thread 
{
		
		int[] storeLength = new int[1000];
		String[] storeWords = new String[1000];
		String[] storeWordsSecondTime = new String[1000];
		NavigableSet<String> ts = new TreeSet<>();
		NavigableSet<String> ts2 = new TreeSet<>();
		int lengthofStoreWords;
		
		int SPACE = 30;
		int space = 5;
		
		
		
		Vocabulary(String[] str)
		{
			putString[0] = str[0];
		}
		
		
		synchronized public void run()
		{
			
			int i=0;
			int j=0;
			try
			{
				FileReader fr = new FileReader(putString[0]);
				Scanner myReader = new Scanner(fr);
				
				while (myReader.hasNextLine())
				{
					String data = myReader.nextLine();
					storeLength[i] = data.length();
					ts.add(data);
					
					//System.out.println(data);
					//System.out.println("The length of " + data + " is " + storeLength[i]);
					i++;
				}
				//System.out.println("The size is : " + ts.size());
				lengthofStoreWords = ts.size();
				
				myReader.close();
				fr.close();
			}
			catch (FileNotFoundException e)
			{
				System.out.println("An error occured");
				e.printStackTrace();
			} 
			
			catch (IOException e) 
			{
				System.out.println("An error occured in the file which is IO");
				e.printStackTrace();
			}
			finally
			{
				
				for (String value : ts)
				{	
					storeWords[j] = value;
					
					for (int t=0; t<SPACE; t++)
						System.out.print(" ");
					
					
						SPACE += space;
						System.out.println(value);
						System.out.println();
						j++;
				}
				
			}
			
			this.notify();
		}
		
synchronized public void settheWords(String[] str) throws IOException
{
			
			FileReader fr2 = new FileReader(str[0]);
			Scanner myReader2 = new Scanner(fr2);
			//countOfFileWord++;
			while (myReader2.hasNextLine())
			{
				String data = myReader2.nextLine();
				ts2.add(data);
			}
			myReader2.close();
			fr2.close();
			
			int k=0;
			for (String value : ts2)
			{	
				storeWordsSecondTime[k] = value;
				//System.out.println("Hi : " + storeWordsSecondTime[k]);
				k++;
			}
			lengthofStoreWords = ts2.size();
			//System.out.println("The length of vocabulary file is : " + lengthofStoreWords);
}


	

synchronized public void checkingTheQuery(String[] str, String[] queryWords) 
{
	
			try
			{
				FileReader fr2 = new FileReader(str[0]);
				Scanner myReader2 = new Scanner(fr2);
			
			
			//countOfFileWord++;
			while (myReader2.hasNextLine())
			{
				String data = myReader2.nextLine();
				ts2.add(data);
			}
			myReader2.close();
			fr2.close();
			}
			catch (Exception e)
			{
				System.out.println("An exception occured");
			}
			
			int k=0;
			for (String value : ts2)
			{	
				storeWordsSecondTime[k] = value;
				k++;
			}
			lengthofStoreWords = ts2.size();
			
			// Checking how much of the query exits in the file
			int[] count = new int[lengthofStoreWords];
			for (int i=0; i<lengthofStoreWords; i++)
			{
				count[i] = 0;
			}
			int z=0;
			int counterForQueryMatch=0;
			//System.out.println("Length of store words is : " + lengthofStoreWords);
			for (int i=0; i<lengthofStoreWords; )
			{
				String sr1 = storeWordsSecondTime[i];
				String sr2 =  queryWords[z];
				//System.out.println("SR1 is : " + sr1);
				if (sr1.equalsIgnoreCase(sr2) && i < lengthofStoreWords)
				{
					counterForQueryMatch++;
					count[i]++;
					System.out.print("Word " + storeWordsSecondTime[i] + " from the query to the " + str[0] + " file" + "\tThe frequency of " + storeWordsSecondTime[i] + " is : " + count[i] + "\n");
					z++;
				}
				else if (z < lengthofStoreWords)
				{
					z++;
				}
				else
				{
					i++;
					z=0;
				}
				
			}
			
			
			System.out.println(counterForQueryMatch + " number of words match with the " + str[0] + " file");
			System.out.println(counterForQueryMatch + " number of matches found in " + str[0] + " file");
			if (storeWordsSecondTime.equals(queryWords))
			{
				System.out.println("Proper match with the " + str[0] + " file");
			}
			
			
		}

		

}
	
public class InputFile1  extends Thread 
{
	
		InputFile1(String[] str)
		{
			putString[1] = str[1];
		}
	
		MyProgram a1 = new MyProgram();
		Vector vector = new Vector();
		Vocabulary vocabulary = a1.new Vocabulary(putString);
		NavigableSet<String> ts = new TreeSet<>();
		
		
		String[] WordsDeliminator = new String[1000];
		String[] StoreWords2 = new String[1000];
		String[] StoreWords3 = new String[1000];
		int len=0;
		
		
		
		synchronized public void run()
		{
			
			int i = 0;
			try
			{
				FileReader fr = new FileReader(putString[1]);
				Scanner myReader = new Scanner(fr);
				
				
			synchronized (this)
			{
				while (myReader.hasNextLine())
				{
					String data = myReader.nextLine();
					WordsDeliminator = data.split(" ");
					
					for (String w : WordsDeliminator)
					{
						vector.addElement(w);
						StoreWords2[i] = w;
						//System.out.println(w);
							
							i++;
					}
					
					System.out.println();
					
				}
				setName(putString[1]);
				System.out.println("The vector list for " + getName() + " is : " + vector);
				System.out.println();
				
			}	
				
				len = vector.size();
				
					 myReader.close();
		}
			catch (IOException e)
			{
				System.out.println("An error occured");
				e.printStackTrace();
			} 
			
			
			
			this.notify();
			
		
}
		
synchronized public void setFrequency(String[] str) throws IOException
{
	System.out.println("Word matching using " + str[1] + " file");
			FileReader fr = new FileReader(str[1]);
			Scanner myReader = new Scanner(fr);
			int p=0;
			
			while (myReader.hasNextLine())
			{
				String data = myReader.nextLine();
				WordsDeliminator = data.split(" ");
				
				for (String w : WordsDeliminator)
				{
					vector.addElement(w);
					StoreWords3[p] = w;
					//System.out.print(StoreWords3[p] + " ");
						
						p++;
				}
			}
				System.out.println();
			
				vocabulary.settheWords(str);
				len = vector.size();
				//System.out.println("The length of input file is: " + len);
				int[] count=new int[len];
				String[] storeMatchedWords = new String[len];
				for (int i=0; i<len; i++)
				{
					count[i] = 0;
				}
				int z=0;
				for (int b=0; b<len; )
				{
					String val1 = StoreWords3[b];
					String val2 = vocabulary.storeWordsSecondTime[z];
					
				
					if (val1.equalsIgnoreCase(val2) && z < vocabulary.lengthofStoreWords)
					{
						System.out.print("Word matched : " + val1);
						storeMatchedWords[b] = val1;
						Word obj = new Word(storeMatchedWords);// created object of the class Word
						z++;
						count[b]++;
						System.out.println("\tThe frequency of " + storeMatchedWords[b] + " is : " + count[b]);
					}
					
					else if (z < vocabulary.lengthofStoreWords)
					{
						z++;
					}
					
					else
					{
						b++;
						z=0;
					}
				}
				
				System.out.println();
		



}
synchronized public void checkingTheQuery(String[] str, String[] queryWords) throws IOException
{
	
			
			FileReader fr2 = new FileReader(str[1]);
			Scanner myReader2 = new Scanner(fr2);
			//countOfFileWord++;
			FileReader fr = new FileReader(str[1]);
			Scanner myReader = new Scanner(fr);
			int p=0;
			
			while (myReader2.hasNextLine())
			{
				String data = myReader2.nextLine();
				WordsDeliminator = data.split(" ");
				
				for (String w : WordsDeliminator)
				{
					vector.addElement(w);
					StoreWords3[p] = w;
					//System.out.print(StoreWords3[p] + " ");
						
						p++;
				}
			}
				System.out.println();
			
			
			
			// Checking how much of the query exits in the file
			int[] count = new int[p];
			for (int i=0; i<p; i++)
			{
				count[i] = 0;
			}
			int z=0;
			int counterForQueryMatch=0;
			for (int i=0; i<p; )
			{
				String sr1 = StoreWords3[i];
				String sr2 =  queryWords[z];
				//System.out.println("SR1 is : " + sr1);
				if (sr1.equalsIgnoreCase(sr2) && i < p)
				{
					counterForQueryMatch++;
					count[i]++;
					System.out.print("Word " + StoreWords3[i] + " from the query to the " + str[0] + " file" + "\tThe frequency of " + StoreWords3[i] + " is : " + count[i] + "\n");
					z++;
				}
				else if (z < p)
				{
					z++;
				}
				else
				{
					i++;
					z=0;
				}
				
			}
			
			System.out.println(counterForQueryMatch + " number of words match with the " + str[1] + " file");
			System.out.println(counterForQueryMatch + " number of matches found in " + str[1] + " file");
			if (StoreWords3.equals(queryWords))
			{
				System.out.println("Proper match with the " + str[1] + " file");
			}
			myReader2.close();
			fr2.close();
		}

}
		

public class InputFile2  extends Thread 
{
		
		Vector vector = new Vector();
		Vocabulary vocabulary = new Vocabulary(putString);
		NavigableSet<String> ts = new TreeSet<>();
		
		
		String[] WordsDeliminator = new String[1000];
		String[] StoreWords2 = new String[1000];
		String[] StoreWords3 = new String[1000];
		int len=0;
		int s1=0;
		int s2=0;
		int p=0;
		
		
		InputFile2(String[] str)
		{
			putString[2] = str[2];
		}
		
		synchronized public void run()
		{
			
			int i = 0;
			try
			{
				FileReader fr = new FileReader(putString[2]);
				Scanner myReader = new Scanner(fr);
				
				//System.out.println("First i " + vocabulary.storeWordsSecondTime[2]);
				
				
				while (myReader.hasNextLine())
				{
					String data = myReader.nextLine();
					WordsDeliminator = data.split(" ");
					
					for (String w : WordsDeliminator)
					{
						vector.addElement(w);
						StoreWords2[i] = w;
						//System.out.println(w);
							
							i++;
					}
					
					System.out.println();
					
				}
				setName(putString[2]);
				System.out.println("The vector list for " + getName() + " is : " + vector);
				System.out.println();
				
				
				len = vector.size();
				
				//System.out.println("The size is : " + len);
				
				//int m=0;
				//System.out.println("The size is : " + vector.size());
			
				
					int pk1=0;
					int pk2=0;
			
					 myReader.close();
		}
			catch (IOException e)
			{
				System.out.println("An error occured");
				e.printStackTrace();
			} 
			
		this.notify();	
				
}
		
synchronized public void setFrequency(String[] str) throws IOException
{
					System.out.println("Word matching using " + str[2] + " file");
					FileReader fr = new FileReader(str[2]);
					Scanner myReader = new Scanner(fr);
					int p=0;
					
					while (myReader.hasNextLine())
					{
						String data = myReader.nextLine();
						WordsDeliminator = data.split(" ");
						
						for (String w : WordsDeliminator)
						{
							vector.addElement(w);
							StoreWords3[p] = w;
							//System.out.print(StoreWords3[p] + " ");
								
								p++;
						}
					}
						System.out.println();
					
						vocabulary.settheWords(str);
						len = vector.size();
						//System.out.println("The length of input file is: " + len);
						int[] count=new int[len];
						String[] storeMatchedWords = new String[len];
						for (int i=0; i<len; i++)
						{
							count[i] = 0;
						}
						int z=0;
						for (int b=0; b<len; )
						{
							String val1 = StoreWords3[b];
							String val2 = vocabulary.storeWordsSecondTime[z];
							
						
							if (val1.equalsIgnoreCase(val2) && z < vocabulary.lengthofStoreWords)
							{
								System.out.print("Word matched : " + val1);
								storeMatchedWords[b] = val1;
								Word obj2 = new Word(storeMatchedWords);// created object of the class Word
								z++;
								count[b]++;
								System.out.println("\tThe frequency of " + storeMatchedWords[b] + " is : " + count[b]);
							}
							
							else if (z < vocabulary.lengthofStoreWords)
							{
								z++;
							}
							
							else
							{
								b++;
								z=0;
							}
						}
						
					
}

synchronized public void checkingTheQuery(String[] str, String[] queryWords) throws IOException
{
	
			
			FileReader fr2 = new FileReader(str[2]);
			Scanner myReader2 = new Scanner(fr2);
			int p=0;
			
			while (myReader2.hasNextLine())
			{
				String data = myReader2.nextLine();
				WordsDeliminator = data.split(" ");
				
				for (String w : WordsDeliminator)
				{
					vector.addElement(w);
					StoreWords3[p] = w;
					//System.out.print(StoreWords3[p] + " ");
						
						p++;
				}
			}
				System.out.println();
			
			
			
			// Checking how much of the query exits in the file
			int[] count = new int[p];
			for (int i=0; i<p; i++)
			{
				count[i] = 0;
			}
			int z=0;
			int counterForQueryMatch=0;
			for (int i=0; i<p; )
			{
				String sr1 = StoreWords3[i];
				String sr2 =  queryWords[z];
				//System.out.println("SR1 is : " + sr1);
				if (sr1.equalsIgnoreCase(sr2) && i < p)
				{
					counterForQueryMatch++;
					count[i]++;
					System.out.print("Word " + StoreWords3[i] + " from the query to the " + str[0] + " file" + "\tThe frequency of " + StoreWords3[i] + " is : " + count[i] + "\n");
					z++;
				}
				else if (z < p)
				{
					z++;
				}
				else
				{
					i++;
					z=0;
				}
				
			}
			
			System.out.println(counterForQueryMatch + " number of words match with the " + str[2] + " file");
			System.out.println(counterForQueryMatch + " number of matches found in " + str[2] + " file");
			if (StoreWords3.equals(queryWords))
			{
				System.out.println("Proper match with the " + str[2] + " file");
			}
			myReader2.close();
			fr2.close();
		}

}







// main function	
public static void main(String[] args) throws InterruptedException, IOException
	{

		int counterForFiles=0;
		int option;
		for (int i=0; i<args.length; i++)
		{
			counterForFiles++;
		}
		System.out.println("\nThe number of files are : " + counterForFiles + "\n");
		
		System.out.println("The names of the file input are : ");
		for (int i=0; i<args.length; i++)
		{
			System.out.println(args[i]);
		}
		System.out.println();
		
		
		MyProgram a1 = new MyProgram();
		
		
		
		String[] storeTextFile = new String[50];
		String[] WordsDeliminatorForQuery = new String[1000];
		Vector vec = new Vector();
		String[] storeWordsForQuery = new String[1000];
		int queryCounter=0;
		
		
		storeTextFile[0] = args[0];
		storeTextFile[1] = args[1];
		storeTextFile[2] = args[2];
		
		do
		{	
			
			System.out.println("Choose any option from the following");
			System.out.println("1. Displaying BST build from Vocabulary File");
			System.out.println("2. Displaying Vectors build from Input File");
			System.out.println("3. Viewing Match words and its frequency");
			System.out.println("4. Searching a query - It should display all the files query found in");
			System.out.println("5. Enter 5 for Exiting");
			Scanner scanner = new Scanner(System.in);
			option = scanner.nextInt();
			
			
		
			
			switch (option)
			{
				
			case 1:
			{
				Vocabulary vocabulary = a1.new Vocabulary(storeTextFile);		// Thread 1
				vocabulary.start();
				synchronized (vocabulary)
				{							// We are using synchronized here to let the thread 1 complete its execution and then main will execute
					vocabulary.wait();
					break;
				}
				
			}
			
			case 2:
			{
				InputFile1 inputfile1 = a1.new InputFile1(storeTextFile);		// Thread 2
				inputfile1.start();
				synchronized (inputfile1)
				{							// We are using synchronized here to let the thread 2 complete its execution and then main will execute
					inputfile1.wait();
				}
				
				
				InputFile2 inputfile2 = a1.new InputFile2(storeTextFile);		// Thread 3
				inputfile2.start();
				synchronized (inputfile2)
				{							// We are using synchronized here to let the thread 3 complete its execution and then main will execute
					inputfile2.wait();
					break;
				}
				
			}
			
			case 3:
			{
				
				InputFile1 inputfile1 = a1.new InputFile1(storeTextFile);		
				InputFile2 inputfile2 = a1.new InputFile2(storeTextFile);		
			
				inputfile1.setFrequency(storeTextFile);
				inputfile2.setFrequency(storeTextFile);
				break;
			}
			
			case 4:
			{
				System.out.println("Enter a query : ");
				Scanner obj = new Scanner(System.in);
				String enteringQuery = obj.nextLine();
				Vocabulary vocabulary = a1.new Vocabulary(storeTextFile);
				InputFile1 inputfile1 = a1.new InputFile1(storeTextFile);
				InputFile2 inputfile2 = a1.new InputFile2(storeTextFile);
				// Firstly we will tokenize the query in order to compare
				WordsDeliminatorForQuery = enteringQuery.split(" ");
				
				for (String w : WordsDeliminatorForQuery)
				{
					vec.add(w);
					storeWordsForQuery[queryCounter] = w;
					//System.out.println(w);
					queryCounter++;
				}
				
				vocabulary.checkingTheQuery(storeTextFile, storeWordsForQuery);
				inputfile1.checkingTheQuery(storeTextFile, storeWordsForQuery);
				inputfile2.checkingTheQuery(storeTextFile, storeWordsForQuery);
				break;
			}
			
			case 5:
			{
				System.out.println("Exiting");
				break;
			}
			
			}
			
			
			try
			{
				// Custom exception
				optionExceptionFunction(option);
			}
			
			catch (optionException e)
			{
				e.printStackTrace();
				System.out.println("An error occured");
			}
			
			System.out.println();
			
		} while (option == 1 || option == 2 || option == 3 || option == 4);
		
		
		

}



// Custom exception function
public static void optionExceptionFunction(int option) throws optionException
{
	try
	{
		if (option == 1 || option == 2 || option == 3 || option == 4)
		{
			System.out.println("\n\nYou entered the correct option");
		}
	
		else if (option == 5)
		{
			System.out.println("Thank you!");
		}
		
		else
		{
			throw new optionException("\n" + "You entered the wrong option");
		}
	}
	
	catch (optionException e)
	{
		e.printStackTrace();
	}
}
}