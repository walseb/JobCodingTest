import MyUtils.Pair;

class LongestSequence {
	public static Pair<Character, Integer> longestSequence(String s) {
		var highest = new Pair<Character, Integer>('a', 0);
		// Contains the currently selected char
		var current = new Pair<Character, Integer>('a', 0);

		for (int i = 0; i < s.length(); i++) {
			Character c = Character.toLowerCase(s.charAt(i));

			if (current.left.equals(c)) {
				current = new Pair<Character, Integer>(current.left, current.right + 1);
			} else {
				highest = getHighest(highest, current);

				current = new Pair<Character, Integer>(c, 1);
			}
		}
		return getHighest(highest, current);
	}

	// Gets the highest of two pairs. If they both have the same value, get the one
	// with the lowest alphabet position
	private static Pair<Character, Integer> getHighest(Pair<Character, Integer> a, Pair<Character, Integer> b) {
		if (b.right > a.right) {
			return b;
		} else if (b.right.equals(a.right)) {
			if (getAlphabetPosition(b.left) > getAlphabetPosition(a.left)) {
				return a;
			} else {
				return b;
			}
		}
		return a;
	}

	private static int getAlphabetPosition(Character a) {
		return (int) a.charValue();
	}
}
