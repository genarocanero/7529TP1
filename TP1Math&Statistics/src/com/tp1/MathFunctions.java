package com.tp1;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MathFunctions {

	private static void completeMapWithAppearances(List<Integer> listTree, Map<Integer, Integer> map) {

		List<Integer> rightTree = TreeUtils.getRightTree(listTree);
		List<Integer> leftTree = TreeUtils.getRightTree(listTree);

		if (!rightTree.isEmpty()) {
			completeMapWithAppearances(rightTree, map);
		}
		if (!leftTree.isEmpty()) {
			completeMapWithAppearances(leftTree, map);
		}

		int repetitions = 0;
		try {
			repetitions = map.get(listTree.get(0));
		} catch (NullPointerException e) {
			// element does not exist yet
		} finally {
			map.put(listTree.get(0), ++repetitions);
		}

	}

	public static int max(int[] array, boolean ordered) {
		int max = 0;

		if (array.length > 0) {
			if (ordered) {
				max = array[0];
			} else {
				for (int number : array) {
					if (number > max) {
						max = number;
					}
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
		double avg = 0;

		for (int number : array) {
			sum += number;
		}

		if (array.length > 0) {
			avg = sum / array.length;
		}

		return avg;
	}

	public static double average(List<Integer> list) {
		Iterator iterator = list.iterator();
		double sum = 0;
		double avg = 0;

		while (iterator.hasNext()) {
			sum += (Integer) iterator.next();
		}

		if (!list.isEmpty()) {
			avg = sum / list.size();
		}

		return avg;
	}

	public static double treeAverage(List<Integer> listTree) {
		double avg = 0;

		if (!listTree.isEmpty()) {
			avg = (double) TreeUtils.sum(listTree) / listTree.size();
		}

		return avg;

	}

	public static int trend(int[] array) {
		Map<Integer, Integer> occurrences = new LinkedHashMap<>();
		for (int number : array) {
			int previousOccurrences = 0;
			try {
				previousOccurrences = occurrences.get(number);
			} catch (NullPointerException e) {
				// element does not exist yet
			} finally {
				occurrences.put(number, ++previousOccurrences);
			}
		}

		int maxOccurrences = 0;
		int trend = 0;
		Set<Entry<Integer, Integer>> entrySet = occurrences.entrySet();
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
			int previousOccurrences = 0;
			try {
				previousOccurrences = occurrences.get(number);
			} catch (NullPointerException e) {
				// element does not exist yet
			} finally {
				occurrences.put(number, ++previousOccurrences);
			}
		}

		int maxOccurrences = 0;
		int trend = 0;
		Set<Entry<Integer, Integer>> entrySet = occurrences.entrySet();
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

	public static int treeTrend(List<Integer> listTree) {
		Map<Integer, Integer> map = new HashMap<>();
		int maxOccurrences = 0;
		int trend = 0;

		completeMapWithAppearances(listTree, map);

		Set<Entry<Integer, Integer>> entrySet = map.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue() > maxOccurrences) {
				trend = entry.getKey();
				maxOccurrences = entry.getValue();
			}
		}

		return trend;

	}

	public static double orderedMedian(int[] orderedArray) {
		double median;
		median = orderedArray[orderedArray.length / 2];

		if (orderedArray.length % 2 == 0) {
			median += orderedArray[orderedArray.length / 2 - 1];
			median /= 2.0;
		}

		return median;
	}

	public static double median(int[] array) {
		Arrays.sort(array);
		return orderedMedian(array);
	}

	public static double median(List<Integer> list) {
		Collections.sort(list);
		float median;
		int size = list.size();

		if (size % 2 != 0) {
			median = list.get(size / 2);

		} else {
			median = ((float) list.get(size / 2) + (float) list.get(size / 2 - 1)) / 2f;
		}

		return median;
	}

	public static double treeMedian(List<Integer> listTree) {
		float median = 0;

		if (listTree.size() % 2 != 0) {
			median = listTree.get(0);

		} else {
			if (TreeUtils.getRightTreeSize(listTree) > TreeUtils.getLeftTreeSize(listTree)) {
				try {
					median = ((float) listTree.get(0) + (float) listTree.get(1)) / 2;
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}

			} else {
				try {
					median = ((float) listTree.get(0) + (float) listTree.get(2)) / 2;
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		}

		return median;
	}

	public static double standardDeviation(int[] array) {
		double average = 0;
		double standardDeviation = 0;

		if (array.length > 0) {
			average = average(array);

			double sum = 0;

			for (int element : array) {
				sum += Math.pow(element - average, 2);
			}

			standardDeviation = Math.sqrt(sum / array.length);
		}

		return standardDeviation;
	}

	public static double standardDeviation(List<Integer> list) {
		double average = 0;
		double standardDeviation = 0;

		if (!list.isEmpty()) {
			average = average(list);
			double sum = 0;

			for (int element : list) {
				sum += Math.pow(element - average, 2);
			}

			standardDeviation = Math.sqrt(sum / list.size());
		}

		return standardDeviation;
	}

	public static double treeStandardDeviation(List<Integer> listTree) {
		double average = 0;
		double standardDeviation = 0;

		if (!listTree.isEmpty()) {
			average = treeAverage(listTree);
			double sum = 0;

			for (int element : listTree) {
				sum += Math.pow(element - average, 2);
			}

			standardDeviation = Math.sqrt(sum / listTree.size());
		}

		return standardDeviation;

	}

	public static String[] permutations(String[] array) {
		String[] finalArray = array.clone();
		String[] lastPermutations = array.clone();
		int iterator = 1;

		while (iterator < array.length) {
			String[] temporalArray = lastPermutations.clone();
			lastPermutations = new String[temporalArray.length * array.length];
			int index = 0;

			for (String temporalElement : temporalArray) {
				for (String originalElement : array) {
					String newElement = temporalElement.concat(originalElement);
					lastPermutations[index] = newElement;
					index ++;
				}
			}
			String [] auxiliarArray = finalArray.clone();
			finalArray = new String[finalArray.length + lastPermutations.length];

			for (int i = 0; i < finalArray.length; i++) {
				if (i < auxiliarArray.length) {
					finalArray[i] = auxiliarArray[i];
				} else {
					finalArray[i] = lastPermutations[i - auxiliarArray.length];
				}
			}

			iterator++;
		}
		return finalArray;
	}

	public static List<String> permutations(LinkedList<String> list) {
		LinkedList<String> finalList = new LinkedList<>();
		finalList.addAll(list);
		LinkedList<String> lastPermutations = new LinkedList<>();
		lastPermutations.addAll(list);
		int iterator = 1;

		while (iterator < list.size()) {
			LinkedList<String> temporalList = new LinkedList<>();
			temporalList.addAll(lastPermutations);
			lastPermutations.clear();

			for (String temporalElement : temporalList) {
				for (String originalElement : list) {
					lastPermutations.add(temporalElement.concat(originalElement));
				}
			}
			finalList.addAll(lastPermutations);
			iterator++;
		}
		return finalList;
	}

	public static String[] orderedPermutations(String[] orderedArray) {
		return permutations(orderedArray);
	}

	/**
	 *  treeList is a list representing a Tree. The structure is:
	 *	element at 0: root
	 *  element at 2n + 1: left node (of element at n)
	 *  element at 2n + 2: right node (of element at n)
	 **/
	public static List<String> treePermutations(LinkedList<String> treeList) {
		return permutations(treeList);
	}

	// precondition: r is a natural number >= 1
	public static String[] variationsInRElements(String[] array, int r) {
		String[] finalArray = removeDuplicates(array);
		String[] lastVariation = finalArray.clone();
		int lastVariationIndex = lastVariation.length;
		int currentElement = 1;

		while (currentElement < r){
			String[] temporalArray = Arrays.copyOfRange(lastVariation, 0, lastVariationIndex);
			lastVariation = new String[temporalArray.length * array.length];
			int iterator = 0;

			for (String temporalElement : temporalArray){
				for (String originalElement : array){
					if (temporalElement.compareToIgnoreCase(originalElement) != 0 &&
						!temporalElement.contains(originalElement) &&
						!originalElement.contains(temporalElement)) {
						boolean existsInLastVariation = false;
						String newElement = temporalElement.concat(originalElement);

						for (String lastVariationElement : lastVariation){
							if (lastVariationElement != null && !existsInLastVariation &&
									newElement.compareToIgnoreCase(lastVariationElement) == 0){
								existsInLastVariation = true;
							}
						}
						if (!existsInLastVariation){
							lastVariation[iterator] = newElement;
							++ iterator;
							lastVariationIndex = iterator;
						}
					}
				}
			}
			if (++currentElement == r){
				finalArray = Arrays.copyOfRange(lastVariation, 0, iterator);
			}
		}
		return finalArray;
	}

	private static String[] removeDuplicates (String[] array) {
		String[] finalArray = new String[array.length];
		int iterator = 0;

		for (String element : array){
			boolean existsInFinal = false;

			for (String finalElement : finalArray){
				if (finalElement != null && !existsInFinal && element.compareToIgnoreCase(finalElement) == 0){
					existsInFinal = true;
				}
			}
			if (!existsInFinal){
				finalArray[iterator] = element;
				++ iterator;
			}
		}
		return Arrays.copyOfRange(finalArray, 0, iterator);
	}

	// precondition: r is a natural number >= 1
	public static List<String> variationsInRElements(LinkedList<String> list, int r) {
		LinkedList<String> finalList = removeDuplicates(list);
		LinkedList<String> lastVariation = new LinkedList<>();
		lastVariation.addAll(finalList);
		int currentElement = 1;

		while (currentElement < r){
			LinkedList<String> temporalList = new LinkedList<>();
			temporalList.addAll(lastVariation);
			lastVariation.clear();

			for (String temporalElement : temporalList){
				for (String originalElement : list){
					if (temporalElement.compareToIgnoreCase(originalElement) != 0 &&
							!temporalElement.contains(originalElement) &&
							!originalElement.contains(temporalElement)) {
						boolean existsInLastVariation = false;
						String newElement = temporalElement.concat(originalElement);

						for (String lastVariationElement : lastVariation){
							if (lastVariationElement != null && !existsInLastVariation &&
									newElement.compareToIgnoreCase(lastVariationElement) == 0){
								existsInLastVariation = true;
							}
						}
						if (!existsInLastVariation){
							lastVariation.add(newElement);
						}
					}
				}
			}
			if (++currentElement == r){
				finalList = lastVariation;
			}
		}
		return finalList;
	}

	private static LinkedList<String> removeDuplicates (LinkedList<String> list) {
		LinkedList<String> finalList = new LinkedList<>();

		for (String element : list){
			boolean existsInFinal = false;

			for (String finalElement : finalList){
				if (finalElement != null && !existsInFinal && element.compareToIgnoreCase(finalElement) == 0){
					existsInFinal = true;
				}
			}
			if (!existsInFinal){
				finalList.add(element);
			}
		}
		return finalList;
	}

	// precondition: r is a natural number >= 1
	public static String[] orderedVariationsInRElements(String[] orderedArray, int r) {
		return variationsInRElements(orderedArray, r);
	}

	/**
	 *  treeList is a list representing a Tree. The structure is:
	 *	element at 0: root
	 *  element at 2n + 1: left node (of element at n)
	 *  element at 2n + 2: right node (of element at n)
	 *
	 *  precondition: r is a natural number >= 1
	 **/
	public static List<String> treeVariationsInRElements(LinkedList<String> treeList, int r) {
		return variationsInRElements(treeList, r);
	}

	// precondition: r is a natural number >= 1
	public static String[] variationsWithRRepetitions(String[] array, int r) {
		String[] finalArray = array.clone();
		int iterator = 1;
		int lastIndex = array.length;

		while (iterator < r) {
			String[] temporalArray = finalArray.clone();
			finalArray = new String[temporalArray.length * array.length];
			int index = 0;

			for (String temporalElement : temporalArray) {
				for (String originalElement : array) {
					String newElement = temporalElement.concat(originalElement);
					finalArray[index] = newElement;
					index ++;
				}
			}
			lastIndex = index;
			iterator ++;
		}
		return Arrays.copyOfRange(finalArray, 0, lastIndex);
	}

	// precondition: r is a natural number >= 1
	public static LinkedList<String> variationsWithRRepetitions(LinkedList<String> list, int r) {
		LinkedList<String> finalList = new LinkedList<>();
		finalList.addAll(list);
		int iterator = 1;

		while (iterator < r) {
			LinkedList<String> temporalList = new LinkedList<>();
			temporalList.addAll(finalList);
			finalList.clear();

			for (String temporalElement : temporalList) {
				for (String originalElement : list) {
					finalList.add(temporalElement.concat(originalElement));
				}
			}
			iterator++;
		}
		return finalList;
	}

	// precondition: r is a natural number >= 1
	public static String[] orderedVariationsWithRRepetitions(String[] orderedArray, int r) {
		return variationsWithRRepetitions(orderedArray, r);
	}

	/**
	 *  treeList is a list representing a Tree. The structure is:
	 *	element at 0: root
	 *  element at 2n + 1: left node (of element at n)
	 *  element at 2n + 2: right node (of element at n)
	 *
	 *  precondition: r is a natural number >= 1
	 **/
	public static LinkedList<String> treeVariationsWithRRepetitions(LinkedList<String> treeList, int r) {
		return variationsWithRRepetitions(treeList, r);
	}
}
