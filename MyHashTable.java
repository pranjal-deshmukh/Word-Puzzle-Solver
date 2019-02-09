public class MyHashTable
{

	static final int DEFAULT_TABLE_SIZE = 101;
    String[] arr;
	private int size;
	public int num_collisions=0;

	//constructor for MyHashTable
	MyHashTable()
	{
		arr=new String[DEFAULT_TABLE_SIZE];
		size=0;
	}	
	
	//contains method checks whether a given element is present in hashtable or not
	public boolean contains (String x )
	{
			if (arr[myhash(x)]!=null)
				return true;
			else 
				return false;
	}
	
	
	//incorporates collision resolution strategy: linear probing
	private int myhash(String x )
	 {
		 int val = hashFunc(x);			
	     
	     while (arr[val] != null && !arr[val].equals(x))
	     {    
	    	 
	    	 //Linear probing is used here
	    	 num_collisions++; 
	    	 val = val + 1;
	    	 val%=arr.length;	 
	     }		     

	     return val;
	 }
	
	public void insert(String x)
	{

		if(size++ > arr.length/2 )
			rehash();
		
		if(arr[myhash(x)] == null) {
			arr[myhash(x)] = x;
		}
		else if (arr[myhash(x)]!=null && arr[myhash(x)].equals(x))
		{
			size--;
			
		}	
		
    }
	
	

	public void rehash()
	{
		int twiceLength = 2*arr.length;
		String [] arr1 = arr;
		int size_prev=size;
		
		arr=new String[nextPrime(twiceLength)];
		for (String s: arr1)
		{
			if (s!=null)
				insert(s);
		}
		size=size_prev;
	}
	
	private static int nextPrime( int n )
	 {
	     if( n % 2 == 0 )
	         n++;
	     for( ; !isPrime( n ); n += 2 )
	         ;

	     return n;
	 }
	
	public int hashFunc(String x)
	{
		int n=7;
		StringBuilder sb= new StringBuilder(x);
		for(int i=0;i< sb.length();i++)
			n=31*n+(int)sb.charAt(i);
		
		n%=arr.length;
		if (n<0)
			n+=arr.length;

		return n;
	}
	
	private static boolean isPrime( int n )
	 {
	     if( n == 2 || n == 3 )
	         return true;
	     if( n == 1 || n % 2 == 0 )
	         return false;
	     for( int i = 3; i * i <= n; i += 2 )
	         if( n % i == 0 )
	             return false;

	     return true;
	 }
}