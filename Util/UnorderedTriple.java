package Util;
public class UnorderedTriple<T extends Comparable<T>>
		implements Comparable<UnorderedTriple<T>> {
	public T first;
	public T second;
	public T third;

	public T get(int i) {
		if (i == 0) return first;
		if (i == 1) return second;
		if (i == 2) return third;
		return null;
	}

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
		if (this == t) return 0;
		int c1 = first.compareTo(t.first);
		int c2 = second.compareTo(t.second);
		int c3 = third.compareTo(t.third);
		if (c1 == 0 && c2 == 0) return c3;
		if (c1 == 0) return c2;
		return c1;
	}
}