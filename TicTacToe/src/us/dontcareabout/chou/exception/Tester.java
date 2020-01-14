package us.dontcareabout.chou.exception;

public class Tester {
	public static void main(String[] args) {
		try {
			example();
		} catch (HandleMeException e) {
			System.out.println(e);
		}
	}

	private static void example() {
		for (int i = 0; i < 10; i++) {
			System.out.println("!!!!" + i + "!!!!");
			func1(i);
		}
	}

	private static void func1(int i) {
		if (i % 2 == 0) exception();

		level1();
	}

	private static void level1() {
		System.out.println("level-1 =>");
		level2();
		System.out.println("<= level-1");
	}

	private static void level2() {
		System.out.println("\tlevel-2 =>");
		level3();
		System.out.println("\t<= level-2");
	}

	private static void level3() {
		System.out.println("\t\tlevel-3 =>");
		level4();
		System.out.println("\t\t<= level-3");
	}

	private static void level4() {
		System.out.println("\t\t\tlevel-4 =>");

		System.out.println("\t\t\t<= level-4");
	}

	private static void exception() {
		throw new HandleMeException();
	}
}

@SuppressWarnings("serial")
class HandleMeException extends RuntimeException {
}