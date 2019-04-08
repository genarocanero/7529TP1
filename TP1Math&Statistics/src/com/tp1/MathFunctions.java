package com.tp1;

import java.util.Iterator;
import java.util.List;

public class MathFunctions {

	public static int Max(int[] vector, boolean ordered) {
		int max = 0;
		if (ordered) {
			max = vector[0];
		} else {
			for (int i = 0; i < vector.length; i++) {
				if (vector[i] > max) {
					max = vector[i];
				}
			}
		}
		return max;
	}

	public static int Max(List<Integer> list) {
		Iterator iterator = list.iterator();
		int max = 0;
		while (iterator.hasNext()) {
			Integer current = (Integer) iterator.next();
			if (current > max) {
				max = current;
			}
		}
		return max;
	}

}
