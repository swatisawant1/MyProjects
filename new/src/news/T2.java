package news;

public class T2 {
	
	public static String removedup(String input) {
		if(input.length()<=1)
			return input;
		if(input.charAt(0)==input.charAt(1))
			return (input.substring(1));
		else {
			return input.charAt(0)+removedup(input.substring(1));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S1 = "geeksforgeeks";
		System.out.println(removedup(S1));

		String S2 = "abbcc";
		System.out.println(removedup(S2));
		
	}

}
