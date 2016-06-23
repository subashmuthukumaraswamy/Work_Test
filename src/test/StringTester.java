package test;

public class StringTester {

	public static void main(String args[]) {
		String a = "abc";
		String b = a;
		String c = "abc";
		String d = new String("abc");
		String e = "a";
		String f = "bc";
		String g = e + f;

		if (a.toString() == b) {
			System.out.print("a==b");
		} else {
			System.out.print("a!==b");
		}
		if (a == c) {
			System.out.print("a==c");
		} else {
			System.out.print("a!==c");
		}
		if (a == d) {
			System.out.print("a==d");
		} else {
			System.out.print("a!==d");
		}
		if (a == g) {
			System.out.print("a==g");
		} else {
			System.out.print("a!==g");
		}

		if (a.equalsIgnoreCase(g.toString())) {
			System.out.print("a==g");

		}

	}
}
