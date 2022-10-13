package news;

import java.util.Arrays;

public class T4 {
	public static void secondlarge(int []arr,int arr_size) {
		if(arr_size<2) {
			System.out.println("invalid");
			return;
		}
		Arrays.sort(arr);
		for(int i =arr_size-2;i>=0;i--) {
			if(arr[i]!=arr[arr_size-1]) {
				System.out.println(arr[i]);
				return;
		}
		}
		System.out.println("no 2nd ");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = {35,35,35};
		int n = arr.length;
		secondlarge(arr, n);
	}

}
