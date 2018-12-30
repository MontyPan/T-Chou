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

		for (int i = 100; i < 1000; i++) {
			int a = i / 100;
			int b = (i % 100) / 10;
			int c = i % 10;

			if (Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) == i) {
				System.out.println("" + a + b + c);
			}
		}
	}
}
