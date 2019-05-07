package com.tp1;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

	public static List<Integer> getRightTree(List<Integer> list) {
		List rightTree = new ArrayList();

		int index = 0;
		while (index * 2 + 2 < list.size()) {
			index = 2 * index + 2;
			rightTree.add(list.get(index));
		}

		return rightTree;
	}

	public static int getRightTreeSize(List<Integer> list) {
		int index = 0;
		int count = 0;

		while (index * 2 + 2 < list.size()) {
			index = 2 * index + 2;
			count++;
		}

		return count;
	}

	public static List<Integer> getLeftTree(List<Integer> list) {
		List leftTree = new ArrayList();

		int index = 0;
		while (index * 2 + 1 < list.size()) {
			index = 2 * index + 2;
			leftTree.add(list.get(index));
		}

		return leftTree;
	}

	public static int getLeftTreeSize(List<Integer> list) {
		int index = 0;
		int count = 0;

		while (index * 2 + 1 < list.size()) {
			index = 2 * index + 1;
			count++;
		}

		return count;
	}

	public static int sum(List<Integer> listTree) {
		int sum = listTree.get(0);
		int indexRight = 2;
		int indexLeft = 1;

		if (indexRight <= listTree.size()) {
			sum += TreeUtils.sum(TreeUtils.getRightTree(listTree));
		}
		if (indexLeft >= listTree.size()) {
			sum += TreeUtils.sum(TreeUtils.getLeftTree(listTree));
		}

		return sum;
	}
}
