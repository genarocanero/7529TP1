package com.tp1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MathFunctionsTests {

	@Test
	public void maxTest() {
		int[] array = new int[] { 1, 3, 0, 5, -4, 2, 5 };
		int[] orderedArray = new int[] { 5, 4, 3, 2, 1 };
		List<Integer> list = Arrays.asList(1, 3, 0, 5, -4, 2, 5);
		List<Integer> tree = Arrays.asList(2, 0, 5, -4, 1, 3);

		Assert.assertEquals(5, MathFunctions.max(array, false));
		Assert.assertEquals(5, MathFunctions.max(orderedArray, true));
		Assert.assertEquals(5, MathFunctions.max(list));
		Assert.assertEquals(5, MathFunctions.treeMax(tree));
	}

	@Test
	public void averageTest() {
		int[] array = new int[] { 1, 2, 3 };
		List<Integer> list = Arrays.asList(1, 2, 3);
		List<Integer> tree = Arrays.asList(2, 1, 3);

		Assert.assertEquals(2, MathFunctions.average(array), 0);
		Assert.assertEquals(2, MathFunctions.average(list), 0);
		Assert.assertEquals(2, MathFunctions.average(tree), 0);
	}

	@Test
	public void trendTest() {
		int[] array = new int[] { 1, 3, 0, 5, -4, 2, 5 };
		int[] orderedArray = new int[] { 5, 5, 4, 3, 2, 1 };
		List<Integer> list = Arrays.asList(1, 3, 0, 5, -4, 2, 5);
		List<Integer> tree = Arrays.asList(2, 1, 5, 5);

		Assert.assertEquals(5, MathFunctions.trend(array));
		Assert.assertEquals(5, MathFunctions.orderedTrend(orderedArray));
		Assert.assertEquals(5, MathFunctions.trend(list));
		Assert.assertEquals(5, MathFunctions.treeTrend(tree));
	}

	@Test
	public void medianTest() {
		int[] unevenArray = new int[] { 1, 3, 0, 5, -4, 2, 5 };
		int[] evenArray = new int[] { 1, 3, 0, 5, -4, 2 };
		int[] orderedUnevenArray = new int[] { 5, 5, 3, 2, 1, 0, -4};
		int[] orderedEvenArray = new int[] { 5, 3, 2, 1, 0, -4};
		List<Integer> unevenList = Arrays.asList(1, 3, 0, 5, -4, 2, 5);
		List<Integer> evenList = Arrays.asList(1, 3, 0, 5, -4, 2);
		List<Integer> unevenTree = Arrays.asList(2, 1, 5);
		List<Integer> evenTree = Arrays.asList(2, 1, 5, 3);

		Assert.assertEquals(2, MathFunctions.median(unevenArray), 0);
		Assert.assertEquals(1.5, MathFunctions.median(evenArray), 0);
		Assert.assertEquals(2, MathFunctions.median(orderedUnevenArray), 0);
		Assert.assertEquals(1.5, MathFunctions.median(orderedEvenArray), 0);
		Assert.assertEquals(2, MathFunctions.median(unevenList), 0);
		Assert.assertEquals(1.5, MathFunctions.median(evenList), 0);
		Assert.assertEquals(2, MathFunctions.treeMedian(unevenTree), 0);
		Assert.assertEquals(3.5, MathFunctions.treeMedian(evenTree), 0);
	}

	@Test
	public void standardDeviationTest() {
		int[] array = new int[] { 1, 2, 3 };
		List<Integer> list = Arrays.asList(1, 2, 3);
		List<Integer> tree = Arrays.asList(2, 1, 3);

		Assert.assertEquals(0.8, MathFunctions.standardDeviation(array), 0.1);
		Assert.assertEquals(0.8, MathFunctions.standardDeviation(list), 0.1);
		Assert.assertEquals(0.8, MathFunctions.treeStandardDeviation(tree), 0.1);
	}
}
