package jfsd.lesson4.lis;

import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequenceDemo {

	public static int[] getLIS(int[] arr) {

		int P[] = new int[arr.length];
		int M[] = new int[arr.length + 1];

		int L = 0;
		for (int i=0; i<arr.length;i++) {
			int newL = binarySearch(arr, M, L, i);

			P[i] = M[newL-1];
			M[newL] = i;

			if (newL > L)
				L = newL;
		}
		int S[] = new int[L];
		int k = M[L];
		for (int i = L-1; i>=0; i--) {

			S[i] = arr[k];
			k = P[k];
		}

		return S;

	}

	private static int binarySearch(int[] arr, int[] M, int L, int i) {
		int lo = 1;
		int hi = L + 1;
		while (lo < hi) {

			int mid = lo + ((hi-lo)/2);
			if (arr[M[mid]] < arr[i])
				lo = mid+1;
			else
				hi = mid;
		}
		return lo;
	}

	public static void main(String[] args){
		System.out.println("-= Longest Increasing Subsequence =-\n");
		Scanner sc = new Scanner(System.in);
		int[] numArr = null;
		while(true) {
			try
			{
				//int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
				System.out.println("Please enter an unsorted sequence of Integer numbers separated by coma ',' (ex.: 2,10,5,8) or type 'q' or 'Q' to quit:");
				String input = sc.nextLine().trim();

				if(input.length() == 1 && input.toUpperCase().equals("Q") )
					break;

				input = input.replaceAll("\\s", "");

				String[] arr = input.split(",");
				numArr = new int[arr.length];
				for(int i = 0; i < numArr.length; i++){
					numArr[i] = Integer.parseInt(arr[i]);
				}

				break;
			}
			catch(Exception e)
			{
				System.out.println("\nError while parsing the input, please try again. "+e.getClass().getName());
				numArr = null;
			}
		}

		if(numArr != null) {
			System.out.println("\nThe input Sequence is:");
			System.out.println(Arrays.toString(numArr) + " ("+numArr.length+")");

			System.out.println("\nThe Longest Increasing Subsequence is:");
			int[] lis = getLIS(numArr);
			System.out.println(Arrays.toString(lis) + " ("+lis.length+")");
		}


		System.out.println("\nBye!");
		sc.close();
	}
}