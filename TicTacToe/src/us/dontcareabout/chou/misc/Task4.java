package us.dontcareabout.chou.misc;

import java.util.Arrays;

public class Task4 {
	public static void main(String[] args) {
		int[] data = {5, 5, 6, 6, 78, 78, 78, 9487, 9487, 9487, 9487, 65535};
		int[] result = longest(data);
		System.out.println(Arrays.toString(result));
	}

	/**
	 * @param data 已經排序過的 array
	 * @return result[0] 是最長平台的數字，result[1] 是長度
	 */
	private static int[] longest(int[] data) {
		int len = data.length;
		int[] dummyTarget = new int[]{data[0], 1};
		int[] target = new int[]{-1, 0};

		for (int idx = 1; idx < len; idx++) {
			if (data[idx] == dummyTarget[0]) {
				dummyTarget[1] += 1;
			} else {
				if (dummyTarget[1] > target[1]) {
					target = dummyTarget;
				}
				dummyTarget = new int[]{data[idx], 1};
			}
		}
		return target;
	}
}
