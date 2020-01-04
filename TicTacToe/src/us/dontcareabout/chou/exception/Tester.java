package us.dontcareabout.chou.exception;

public class Tester {
	public static void main(String[] args) {
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
		exception();
		System.out.println("\t\t\t<= level-4");
	}

	private static void exception() {
		throw new HandleMeException();
	}
}

@SuppressWarnings("serial")
class HandleMeException extends RuntimeException {}