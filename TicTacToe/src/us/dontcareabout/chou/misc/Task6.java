package us.dontcareabout.chou.misc;

/**
 * Armstrong 數：一個三位數的正整數，假設百位數為 a、十位數為 b、個位數為 c，
 * 則滿足 a^3 + b^3 + c^3 = a*100 + b*10 + c 為 Armstrong 數。
 * <p>
 * 請在 us.dontcareabout.chou.misc 這個 package 下（aka：跟 Task4.java 同目錄）增加一個 Task6.java，
 * 使其執行後可以印出所有的 Armstrong 數。
 * <p>
 * 注意：程式當中不能使用任何字串操作。
 */
public class Task6 {
	public static void main(String[] args) {
		System.out.println("Armstrong numbers:");

		//version 1
		armstrongNumber1();

		//version 2
		armstrongNumber2();

		//version 3
		armstrongNumber3(3, 3, 0, 0);
	}

	public static void armstrongNumber1() {
		int[] digit = new int[3];
		for (int i = 100; i < 1000; i++) {
			int sum = 0;

			for (int j = 0; j < 3; j++) {
				digit[j] = parse(i, j);
				sum += Math.pow(digit[j], 3);
			}

			if (sum == i) {
				System.out.println(i);
			}
		}
	}

	public static void armstrongNumber2() {
		for (int a = 1; a <= 9; a++) {
			for (int b = 0; b <= 9; b++) {
				for (int c = 0; c <= 9; c++) {
					if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == (100 * a + 10 * b + c)) {
						System.out.println("" + a + b + c);
					}
				}
			}
		}
	}

	public static void armstrongNumber3(int digits, int index, int number, int cubedSum) {
		if (index > 0) {
			for (int i = 0; i <= 9; i++) {
				int num = (int) (number + i * Math.pow(10, index - 1));
				int cubed = (int) (cubedSum + Math.pow(i, digits));

				armstrongNumber3(digits, index - 1, num, cubed);
			}
		} else if (index == 0) {
			if (number == cubedSum & number / Math.pow(10, digits - 1) >= 1) {
				System.out.println(number);
			}
		}
	}

	/**
	 * Parsing a digit of a number from any place.
	 */
	public static int parse(int number, int index) {
		return (int) (number / Math.pow(10, index) % 10);
	}
}
