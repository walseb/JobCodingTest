package MyUtils;

import java.util.ArrayList;

public final class KeyValuePairUtils {
	// This enum is used to decide wether or not a function should operate on the
	// key or on the value in the key value pair.
	// There are a lot nicer ways to do this but this is good enough
	public enum KeyValuePair {
		KEY, VALUE
	}

	static Integer getKeyValue(Pair<Integer, Integer> pair, KeyValuePair target) {
		if (target == KeyValuePair.KEY) {
			return pair.left;
		} else {
			return pair.right;
		}
	}

	// A slow implementation of filter
	public static ArrayList<Pair<Integer, Integer>> filter(ArrayList<Pair<Integer, Integer>> list,
			Pair<Integer, Integer> filter, Predicate comp, KeyValuePair target) {
		if (list.size() == 0)
			return list;

		var result = new ArrayList<Pair<Integer, Integer>>();

		for (Pair<Integer, Integer> x : list) {
			if (!comp.predicate(x, filter, target)) {
				result.add(x);
			}
		}
		return result;
	}

	// Gets the smallest elements in a list. If there are multiple as small
	// elements, they get added to the return list
	public static Pair<Integer, Integer> smallest(ArrayList<Pair<Integer, Integer>> list, KeyValuePair target) {
		if (list.size() == 0)
			return null;
		Pair<Integer, Integer> lowest = list.get(0);
		for (Pair<Integer, Integer> x : list) {
			lowest = min(x, lowest, target);
		}
		return lowest;
	}

	// Predicate interface used for higer-order filter comparison function
	interface Predicate {
		Boolean predicate(Pair<Integer, Integer> a, Pair<Integer, Integer> b, KeyValuePair target);
	}

	public static ArrayList<Pair<Integer, Integer>> secondSmallest(ArrayList<Pair<Integer, Integer>> list,
			KeyValuePair target) {
		// I'm declaring these inline to save on lines of code
		class NotEqualCheck implements Predicate {
			public Boolean predicate(Pair<Integer, Integer> a, Pair<Integer, Integer> b, KeyValuePair target) {
				return getKeyValue(a, target) != getKeyValue(b, target);
			}
		}

		class EqualCheck implements Predicate {
			public Boolean predicate(Pair<Integer, Integer> a, Pair<Integer, Integer> b, KeyValuePair target) {
				return getKeyValue(a, target) == getKeyValue(b, target);
			}
		}

		// Get the smallest pair in the list
		Pair<Integer, Integer> smallest = smallest(list, target);

		// Filter out anything that's that small
		var listWithoutSmallest = filter(list, smallest, new EqualCheck(), target);

		// If there is nothing left after removing everything as small as 'smallest',
		// return original list
		if (listWithoutSmallest.size() == 0) {
			return list;
		}

		// Get the smallest pair in the new list, which would make it the second
		// smallest in the old list
		Pair<Integer, Integer> secondSmallest = smallest(listWithoutSmallest, target);

		// Filter out anything that's any other size
		return filter(listWithoutSmallest, secondSmallest, new NotEqualCheck(), target);
	}

	// Gets the smallest of two pairs
	static Pair<Integer, Integer> min(Pair<Integer, Integer> a, Pair<Integer, Integer> b, KeyValuePair target) {
		if (getKeyValue(a, target) < getKeyValue(b, target)) {
			return a;
		}
		return b;
	}

	public static Integer findElementIndex(ArrayList<Pair<Integer, Integer>> array, Integer key) {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).left == key) {
				return i;
			}
		}
		return null;
	}
}
