import java.util.Scanner;
public class LinSearch2 
{
	public final static int CAPACITY = 20;
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void main (String[] args)
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
			System.out.println();
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