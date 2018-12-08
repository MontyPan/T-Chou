package us.dontcareabout.chou.misc;

import java.util.Arrays;

public class Task4 {
	//XXX 大多數情況下用到三維陣列是不允許的，不過現在就... 只能先這樣了
	private static final int[][][] TEST_CASE = {
			{{5, 5, 6, 6, 78, 78, 78, 9487, 9487, 9487, 9487, 65535}, {9487, 4}},
			{{5, 6, 6, 6, 9, 10}, {6, 3}},
			{{1, 1, 1, 1, 1, 2, 2, 5, 6, 6, 6, 6}, {1, 5}},
			{{1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5}, {5, 5}}
			//XXX 為什麼我上一行還是給了一個逗號在結尾呢？
	};

	public static void main(String[] args) {
		for (int[][] aCase : TEST_CASE) {
			if (!test(aCase[0], aCase[1])) {
				//對不起，我還是不習慣用 printf()...... (艸
				System.out.println(
						"Case " + Arrays.toString(aCase[0]) + " FAILED. " +
								" Expect " + Arrays.toString(aCase[1]) +
								", but get " + Arrays.toString(longest(aCase[0]))
				);
			}
		}
	}

	/**
	 * @param data 已經排序過的 array
	 * @return result[0] 是最長平台的數字，result[1] 是長度
	 */
	private static int[] longest(int[] data) {
		int pointer = 0;
		int targetNum = data[0];
		int maxCount = 1;

		for (int idx = 1; idx < data.length; idx++) {
			if (data[idx] == data[pointer]) {
				targetNum = data[idx];
				maxCount += 1;
			} else {
				pointer = idx - (maxCount - 1);
			}
		}
		return new int[]{targetNum, maxCount};
	}

	private static boolean test(int[] data, int[] answer) {
		int[] result = longest(data);
		return answer[0] == result[0] && answer[1] == result[1];
	}

}
