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
		//FIXME
		return new int[]{9487, 4};
	}
}
