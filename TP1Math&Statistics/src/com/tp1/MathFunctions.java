package com.tp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	public static int orderedTrend(int[] vector) {
		int maxOccurrences = 0;
		int trend = 0;
		int index = 0;

		while (index < vector.length && maxOccurrences < vector.length - index) {
			int currentNumberOccurrences = 0;
			int currentNumber = vector[index];
			while (index < vector.length && vector[index] == currentNumber) {
				currentNumberOccurrences++;
				index++;
			}
			if (currentNumberOccurrences > maxOccurrences) {
				trend = currentNumber;
				maxOccurrences = currentNumberOccurrences;
			}
		}
		return trend;
	}

	public static int orderedMedian(int[] orderedVector) {
		int median;
		median = orderedVector[orderedVector.length / 2];

		if (orderedVector.length % 2 == 0) {
			median += orderedVector[orderedVector.length / 2 - 1];
			median /= 2;
		}

		return median;
	}

	public static int median(int[] vector) {
		Arrays.sort(vector);
		return orderedMedian(vector);
	}

	public static int median(List<Integer> list) {
		Collections.sort(list);
		int median;
		int size = list.size();
		if (size % 2 != 0) {
			median = list.get(size / 2);
		} else {
			median = (list.get(size / 2) + list.get(size / 2 - 1)) / 2;
		}
		return median;
	}

	public static double standardDeviation(int[] vector) {
		double average = average(vector);
		double sum = 0;

		for (int element : vector) {
			sum += Math.pow(element - average, 2);
		}

		return Math.sqrt(sum / vector.length);
	}

	public static double standardDeviation(List<Integer> list) {
		double average = average(list);
		double sum = 0;

		for (int element : list) {
			sum += Math.pow(element - average, 2);
		}

		return Math.sqrt(sum / list.size());
	}

/*	public static List<List<Integer>> permutations(int[] vector) {

	}

	public static List<List<Integer>> permutations(List<Integer> list) {

	}

	public static List<List<Integer>> variationsInRElements(int[] vector) {

	}

	public static List<List<Integer>> variationsInRElements(List<Integer> list) {

	}

	public static List<List<Integer>> variationsWithRRepetitions(int[] vector) {

	}

	public static List<List<Integer>> variationsWithRRepetitions(List<Integer> list) {

	}*/

}
