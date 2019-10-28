public class UnorderedTriple<T extends Comparable<T>>
		implements Comparable<UnorderedTriple<T>> {
	public T first;
	public T second;
	public T third;

	public UnorderedTriple(T a, T b, T c) {
		if (a.compareTo(b) < 0) {
			if (b.compareTo(c) < 0) {
				first = a;
				second = b;
				third = c;
			} else {
				if (c.compareTo(a) < 0) {
					first = c;
					second = a;
					third = b;
				} else {
					first = a;
					second = c;
					third = b;
				}
			}
		} else {
			if (a.compareTo(c) < 0) {
				first = b;
				second = a;
				third = c;
			} else {
				if (b.compareTo(c) < 0) {
					first = b;
					second = c;
					third = a;
				} else {
					first = c;
					second = b;
					third = a;
				}
			}
		}
	}

	public int compareTo(UnorderedTriple<T> t) {
		if (first == t.first && second == t.second) {
			return third.compareTo(t.third);
		} else if (first == t.first) {
			return second.compareTo(t.second);
		} else {
			return first.compareTo(t.first);
		}
	}
}