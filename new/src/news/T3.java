package news;

public class T3 {
	
	
	/*public static int maxprod(int []arr,int n) {
		if(n<3);
			return -1;
		int max_product=Integer.MIN_VALUE;
		for (int i =0;i<=n-2;i++) {
			for (int j =0; j<=n-1;j++) {
				for(int k=0;k<=n;k++) {
					max_product=Math.max(max_product, arr[i]*arr[j]*arr[k]);
				}
			}
		}
		return max_product;
	}*/
	
	static int maxProduct(int []arr, int n)
	{
		
		// if size is less than
		// 3, no triplet exists
		if (n < 3)
			return -1;

		// will contain max product
		int max_product = Integer.MAX_VALUE;

		for (int i = 0; i < n - 2; i++)
			for (int j = i + 1; j < n - 1; j++)
				for (int k = j + 1; k < n; k++)
					max_product = Math.max(max_product,
							arr[i] * arr[j] * arr[k]);

		return max_product;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int []arr = { 10, 3, 5, 6, 20 };
		int n = arr.length;;

		int max = maxProduct(arr, n);

		if (max == -1)
			System.out.println("No Triplet Exists");
		else
			System.out.println("Maximum product is " + max);
	}

}
