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
					finalArray[index] = newElement;
					index ++;
				}
			}
			iterator ++;
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
		int currentElement = 1;

		while (currentElement < r){
			String[] temporalArray = lastVariation.clone();
			lastVariation = new String[temporalArray.length * array.length];
			int iterator = 0;

			for (String temporalElement : temporalArray){
				for (String originalElement : array){
					if (temporalElement.compareToIgnoreCase(originalElement) != 0) {
						boolean existsInLastVariation = false;
						String newElement = temporalElement.concat(originalElement);

						for (String lastVariationElement : lastVariation){
							if (!existsInLastVariation && newElement.compareToIgnoreCase(lastVariationElement) == 0){
								existsInLastVariation = true;
							}
						}
						if (!existsInLastVariation){
							lastVariation[iterator] = newElement;
							++ iterator;
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
				if (!existsInFinal && element.compareToIgnoreCase(finalElement) == 0){
					existsInFinal = true;
				}
			}
			if (!existsInFinal){
				finalArray[iterator] = element;
				++ iterator;
			}
		}
		return Arrays.copyOfRange(finalArray, 0, iterator - 1);
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
					if (temporalElement.compareToIgnoreCase(originalElement) != 0) {
						boolean existsInLastVariation = false;
						String newElement = temporalElement.concat(originalElement);

						for (String lastVariationElement : lastVariation){
							if (!existsInLastVariation && newElement.compareToIgnoreCase(lastVariationElement) == 0){
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
				if (!existsInFinal && element.compareToIgnoreCase(finalElement) == 0){
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
		int lastIndex = array.length-1;

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
			lastIndex = index -1;
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
