

import java.util.*;
import java.io.*;


public class WordPuzzle
{
	static int count=0;
	static int numRows=0;
	static int counter=0;
	static int numColumns=0;
	static int directions=8;
	static MyHashTable myTable= new MyHashTable();
	char a[][]= new char[numRows][numColumns];
	static int maxlength=0;
	
	//Displays the grid based on the user input : rows and columns
	public static void display(char arr[][])
	{
		for(int i=0;i<arr.length;i++)
		{
			for (int j=0;j<arr[i].length;j++)
				System.out.print(arr[i][j]+" ");
			System.out.println("");
		}	
	}
	
	//main function
	public static void main (String args[])
	{	
		//int startTime;
		//int endTime;
		long startTime;
		long endTime;
		try 
		{		
			String word=null;
			BufferedReader file = new BufferedReader(new FileReader("dictionary.txt"));

			while((word=file.readLine())!=null)
				{
					if(word.length() > maxlength)
						maxlength=word.length();
					myTable.insert(word);
				 	if (++count%10000 == 0)
						System.out.println("Total "+ count +" words loaded till now");
				}
			file.close();
			System.out.println("Total words loaded from dictionary are : "+count);
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the number of numRows:");
			numRows = sc.nextInt();
			System.out.println("Enter the number of numColumns:");
			numColumns = sc.nextInt();
			sc.close();
			
			char a[][]= new char[numRows][numColumns];
				for(int i=0;i<numRows;i++)
				for (int j=0;j<numColumns;j++)
					a[i][j]= (char)(97+(java.lang.Math.random()*100)%26);
			display(a);
			counter=0;
			count=0;
            startTime = System.currentTimeMillis( );
            for(int i = 0;i < a.length;i++)
				for (int j = 0;j < a[i].length;j++)
				{
					search(String.valueOf(a[i][j]),myTable);
					for (int k = 0;k < directions;k++)
					{
						
						goDirections(a,i,j,k,myTable);
					}
				}	
										
            System.out.println("Number of words that match with dictionary are "+counter);
            endTime = System.currentTimeMillis( );
            System.out.println( "Elapsed time is: " + (endTime-startTime)+" milliseconds" );            
		
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}
	}
		
	public static void goDirections(char arr[][],int i,int j,int k,Object o)
	{
		//Here we will analyze the cases for directions: can range from 0 to 7:
		StringBuilder sb = new StringBuilder();
		sb.append(arr[i][j]);
		switch (k)
		{
			case 0:
				for (int y = j+1;y < arr[i].length;y++)
				{
					sb.append(arr[i][y]);
					
					if(!search(sb.toString(),o)) {
						break;
					}
				}
				break;
			case 1:
				for (int x = i+1,y = j+1;x < arr.length && y < arr[i].length;x++,y++)
				{
					sb.append(arr[x][y]);
					if(!search(sb.toString(),o)) {
						break;
					}
					
				}
				break;
			case 2:
				for (int x = i+1;x < arr.length;x++)
				{
					sb.append(arr[x][j]);
					if(!search(sb.toString(),o)) {
						break;
					}
				}
				break;
			case 3:
				for (int x = i+1,y = j-1;x < arr.length && y >= 0;x++,y--)
				{
					sb.append(arr[x][y]);
					if(!search(sb.toString(),o)) {
						break;
					}
				}
				break;
			case 4:
				for (int y = j-1;y >= 0;y--)
				{
					sb.append(arr[i][y]);
					if(!search(sb.toString(),o)) {
						break;
					}
				}
				break;
			case 5:
				for (int x = i-1,y = j-1;x >= 0 && y >= 0;x--,y--)
				{	
					sb.append(arr[x][y]);
					if(!search(sb.toString(),o)) {
						break;
					}
				}
				break;
			case 6:	
				for (int x = i-1;x >= 0;x--)
				{
					sb.append(arr[x][j]);
					if(!search(sb.toString(),o)) {
						break;
					}
				}
				break;
			case 7:
				for (int x = i-1,y = j+1;x >= 0 && y < arr[i].length;x--,y++)
				{
					sb.append(arr[x][y]);
					if(!search(sb.toString(),o)) {
						break;
					}
				}
				break;
		}
		
	}
	
	public static boolean searchMyHash(String str,MyHashTable l)
	{	
		boolean flagFound = true;
		for(int i = 1;i < str.length();i++) {
			if(!l.contains(str.substring(0, i))) {
				System.out.println("Prefix :"+str + " not found");	
				flagFound = false;
				return false;
			}
		}
		if(flagFound) {
			counter++;
			System.out.println("Word :"+str + " found");
		}
		return true;
	}
	
	public static boolean search(String str,Object obj)
	{
		if (str.length() <= maxlength )
		{
			if (obj instanceof MyHashTable)
				if(searchMyHash(str,(MyHashTable)obj)) {
					return true;
			}
		}
		return false;
	}

}
