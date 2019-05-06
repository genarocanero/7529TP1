package com.tp1;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MathFunctions {

	public static int max(int[] array, boolean ordered) {
		int max = 0;

		if (ordered) {
			max = array[0];
		} else {
			for (int number : array) {
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

	/**
	 *  treeList is a list representing a Tree. The structure is:
	 *    element at 0: root
	 *    element at 2n + 1: left node (of element at n)
	 *    element at 2n + 2: right node (of element at n)
	 **/
	public static int treeMax(List<Integer> treeList) {
		int index = 0;

		while (index * 2 + 2 < treeList.size()) {
			index = 2 * index + 2;
		}

		return treeList.get(index);
	}

	public static double average(int[] array) {
		double sum = 0;

		for (int number : array) {
			sum += number;
		}

		return sum / array.length;
	}

	public static double average(List<Integer> list) {
		Iterator iterator = list.iterator();
		double sum = 0;

		while (iterator.hasNext()) {
			sum += (Integer) iterator.next();
		}

		return sum / list.size();
	}

	public static double treeAverage(List<Integer> listTree) {
		return TreeUtils.sum(listTree) / listTree.size();
	}

	public static int trend(int[] array) {
		Map<Integer, Integer> occurrences = new LinkedHashMap<>();
		for (int number : array) {
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

	public static int orderedTrend(int[] orderedArray) {
		int maxOccurrences = 0;
		int trend = 0;
		int index = 0;

		while (index < orderedArray.length && maxOccurrences < orderedArray.length - index) {
			int currentNumberOccurrences = 0;
			int currentNumber = orderedArray[index];
			while (index < orderedArray.length && orderedArray[index] == currentNumber) {
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

	private static void completeMapWithAppearances(List<Integer> listTree, Map<Integer, Integer> map) {

		List rightTree = TreeUtils.getRightTree(listTree);
		List leftTree = TreeUtils.getRightTree(listTree);

		if (!rightTree.isEmpty()) {
			completeMapWithAppearances(rightTree, map);
		}
		if (!leftTree.isEmpty()) {
			completeMapWithAppearances(leftTree, map);
		}

		int repetitions = map.get(listTree.get(0));
		map.put(map.get(0), ++repetitions);

	}

	public static int treeTrend(List<Integer> listTree) {
		Map map = new HashMap<Integer, Integer>();
		int maxOccurrences = 0;
		int trend = 0;

		completeMapWithAppearances(listTree, map);

		Set entrySet = map.entrySet();
		Iterator<Map.Entry<Integer, Integer>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Integer> entry = iterator.next();
			if (entry.getValue() > maxOccurrences) {
				trend = entry.getKey();
				maxOccurrences = entry.getValue();
			}
		}

		return trend;

	}

	public static long orderedMedian(int[] orderedArray) {
		int median;
		median = orderedArray[orderedArray.length / 2];

		if (orderedArray.length % 2 == 0) {
			median += orderedArray[orderedArray.length / 2 - 1];
			median /= 2;
		}

		return median;
	}

	public static long median(int[] array) {
		Arrays.sort(array);
		return orderedMedian(array);
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

	public static int treeMedian(List<Integer> listTree) {
		int median;
		if (listTree.size() % 2 != 0) {
			median = listTree.get(0);
		} else {
			if (TreeUtils.getRightTreeSize(listTree) > TreeUtils.getLeftTreeSize(listTree)) {
				median = (listTree.get(0) + listTree.get(2)) / 2;
			} else {
				median = (listTree.get(0) + listTree.get(1)) / 2;
			}
		}

		return median;
	}

	public static double standardDeviation(int[] array) {
		double average = average(array);
		double sum = 0;

		for (int element : array) {
			sum += Math.pow(element - average, 2);
		}

		return Math.sqrt(sum / array.length);
	}

	public static double standardDeviation(List<Integer> list) {
		double average = average(list);
		double sum = 0;

		for (int element : list) {
			sum += Math.pow(element - average, 2);
		}

		return Math.sqrt(sum / list.size());
	}

	public static double treeStandardDeviation(List<Integer> listTree) {
		double average = treeAverage(listTree);
		int sum = 0;
		for (int element : listTree) {
			sum += Math.pow(element - average, 2);
		}
		return Math.sqrt(sum / listTree.size());

	}

}
