package news;

public class T1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="abbbcaaadaa";
		String Final= new String();
		int count=1,i;
		for (i=0;i<str.length()-1;i++) {
			if(str.charAt(i)==str.charAt(i+1))
				count++;
			else {
				Final=Final+str.charAt(i)+count;
				count=1;
			}
				
		}
		Final=Final+str.charAt(i)+count;
		System.out.println(Final);
	}

}
