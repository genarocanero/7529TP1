package com.tp1;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MathFunctions {

	public static int max(int[] vector, boolean ordered) {
		int max = 0;
		if (ordered) {
			max = vector[0];
		} else {
			for (int number : vector) {
				if (number > max) {
					max = number;
				}
			}
		}
		return max;
	}

	public static int max(List<Integer> list) {
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

	public static double average(int[] vector) {
		double sum = 0;
		for (int number : vector) {
			sum += number;
		}
		return sum / vector.length;
	}

	public static double average(List<Integer> list) {
		Iterator iterator = list.iterator();
		double sum = 0;
		while (iterator.hasNext()) {
			sum += (Integer) iterator.next();
		}
		return sum / list.size();
	}

	public static int trend(int[] vector) {
		Map<Integer, Integer> occurrences = new LinkedHashMap<>();
		for (int number : vector) {
			int previousOccurrences = occurrences.get(number);
			occurrences.put(number, ++previousOccurrences);
		}
		int maxOccurrences = 0;
		int trend = 0;
		Set entrySet = occurrences.entrySet();
		Iterator<Map.Entry<Integer, Integer>> iterator = entrySet.iterator();
		int size = entrySet.size();
		while (iterator.hasNext() && maxOccurrences < size / 2) {
			Entry<Integer, Integer> entry = iterator.next();
			if (entry.getValue() > maxOccurrences) {
				trend = entry.getKey();
				maxOccurrences = entry.getValue();
			}
		}
		return trend;
	}

	public static int trend(List<Integer> list) {
		Map<Integer, Integer> occurrences = new LinkedHashMap<>();
		for (int number : list) {
			int previousOccurrences = occurrences.get(number);
			occurrences.put(number, ++previousOccurrences);
		}
		int maxOccurrences = 0;
		int trend = 0;
		Set entrySet = occurrences.entrySet();
		Iterator<Map.Entry<Integer, Integer>> iterator = entrySet.iterator();
		int size = entrySet.size();
		while (iterator.hasNext() && maxOccurrences < size / 2) {
			Entry<Integer, Integer> entry = iterator.next();
			if (entry.getValue() > maxOccurrences) {
				trend = entry.getKey();
				maxOccurrences = entry.getValue();
			}
		}
		return trend;
	}

}
