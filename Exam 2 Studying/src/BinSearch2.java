import java.util.*; 

public class BinSearch2
{
	public final static int CAPACITY = 500;
	private static Scanner keyboard = new Scanner(System.in);
	private static int tests = 0; //to see how many tests it takes to reach a conclusion
	
	public static void main (String[] args) throws NumberFormatException
	{
		int size = 0;
		int[] list = new int[CAPACITY];
		String userInput ="";
		System.out.print("Enter a sequence of ints: ");
		userInput = keyboard.nextLine();
		StringTokenizer st = new StringTokenizer(userInput," ");
		while (st.hasMoreTokens() && size < CAPACITY){
				list[size] = Integer.parseInt(st.nextToken());
				size++;
			}

		System.out.println();
		display(list, size);
		search(list, size);
		sortList(list, size);
		display(list, size);
		search(list, size);
	}
			
		
	public static void display(int[] list, int size)
	{	
		System.out.println();
		System.out.println("The list of the grades is:");
		for(int k = 0; k < size; k++)
		{
			System.out.print(list[k] + " \t");
			if (k % 8 == 0 && k != 0) 
				System.out.println();
		}	
		System.out.println();	
	}
	

	public static void sortList(int[] list, int size)
	{	
		int temp;
		
		for(int k = 0; k < size-1; k++)
			for(int p = k+1; p < size; p++)
				if(list[k] > list[p])
				{
					temp = list[k];
					list[k] = list[p];
					list[p] = temp;
				}

	}

	public static int binSearch(int[] list, int size, int key){
		int low, high, middle;
		low = 0;
		high = size - 1;
		middle = 0;
		boolean found = false;

		while(low <= high && !found)
		{
			middle = (low + high) / 2;
			if(key == list[middle])
			{
				found = true;
				tests++;
			}
			else if (key < list[middle])
			{
				high = middle - 1;
				tests++;
			}
			else
			{
				low = middle + 1;
				tests++;
			}

		}
		if(found)
			return middle;
		else
			return -1;

		
	}
	public static void search(int[] list, int size)
	{
			
		System.out.println();
		System.out.print("Enter NUMBER to search: ");
		System.out.flush();
		String oneline = keyboard.nextLine();
		int key = Integer.parseInt(oneline);
		
		int index = binSearch(list, size, key);
		
		if(index != -1)
		{
			System.out.println();
			System.out.println("Number was found in location " + index);
			System.out.println();
			System.out.println("It took " + tests + " comparisons.");
		}
		else
		{
			System.out.println();
			System.out.println("Number NOT found");
			System.out.println();
			System.out.println("It took " + tests + " comparisons.");
		}
	}
}