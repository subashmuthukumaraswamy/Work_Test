package test;

public class FinallyTest {
	public static void main(String args[]){
		int a = 0;
		try{
			System.out.println("try");
			//System.exit(1);
			a = 1/0;
			System.out.println(a);

			
		}catch(Exception e){
			a = 1/0;
			System.out.println("Exeption ");

		}finally{
			System.out.println("finally");
		}
	}

}
