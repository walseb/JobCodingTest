import java.util.ArrayList;
import MyUtils.Pair;
import MyUtils.KeyValuePairUtils.KeyValuePair;
import MyUtils.KeyValuePairUtils;

class SecondLowestFrequency {
	public static Integer secondLowestFrequency(Integer[] array) {
		// I'm basically just creating a makeshift hashmap here. The first Integer is
		// the key (the actual number) and the second Integer is the value (the amount
		// of that number in the array)
		ArrayList<Pair<Integer, Integer>> keyValueList = new ArrayList<Pair<Integer, Integer>>();

		// Turn this:
		// [1,1,2,3,2,2]
		// Into this:
		// [Pair(1,2), Pair(2,1), Pair(3,1)]
		// Where the first element is the key, the second is the value
		// In this case the original number is the key, and the value is the amount of
		// occurrences
		for (Integer x : array) {
			Integer index = KeyValuePairUtils.findElementIndex(keyValueList, x);
			// If number is new
			if (index == null) {
				// Add the new number
				keyValueList.add(new Pair<Integer, Integer>(x, 1));
			} else {
				// Increment to the number count
				Pair<Integer, Integer> newElement = new Pair<Integer, Integer>(x, keyValueList.get(index).right + 1);
				keyValueList.set(index, newElement);
			}
		}

		if(keyValueList.size() == 0)
			return null;

		// Get a list of just the second smallest pairs in terms of the value field
		// For example it would turn this:
		// [1,1,2,2,2,3,3]
		// Into this:
		// [2,2]
		ArrayList<Pair<Integer, Integer>> secondSmallestValues = KeyValuePairUtils.secondSmallest(keyValueList, KeyValuePair.VALUE);

		// If the original list had more than one second smallest element in terms of the value field
		if (secondSmallestValues.size() > 1) {
			// Since all the keys are guaranteed to be unique, this must be a single element
			// list
			return KeyValuePairUtils.secondSmallest(secondSmallestValues, KeyValuePair.KEY).get(0).left;
		} else {
			// Otherwise, get all the second smallest pairs in terms of keys
			// Since all the keys are guaranteed to be unique, this must be a single element
			// list
			return secondSmallestValues.get(0).left;
		}
	}
}
