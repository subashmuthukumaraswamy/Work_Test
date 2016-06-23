package utility;

import java.util.Random;

public class RandomTester {
	public static void main(String args[]) {
		for (int i = 0; i < 10; i++) {
			int random = (int) (Math.random() * 100);
			System.out.println(random);
		}
		double random1 = (getRandom());
		System.out.println(random1);

	}

	public static double getRandom() {
		Random random = new Random();
		for (int idx = 1; idx <= 10; ++idx) {
			System.out.println("Generated : " + random.nextInt(100));
		}
		System.out.println("Done.");
		return 0;
	}
	
	public static int getRandomDemo(){
		
		
		return 0;
		
	}

}
