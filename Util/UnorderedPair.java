package Util;
public class UnorderedPair<T extends Comparable<T>> implements Comparable<UnorderedPair<T>> {
	public T first;
	public T second;

	public T get(int i) {
		if (i == 0) return first;
		if (i == 1) return second;
		return null;
	}

	public UnorderedPair(T a, T b) {
		if (a.compareTo(b) < 0) {
			first = a;
			second = b;
		} else {
			first = b;
			second = a;
		}
	}

	public int compareTo(UnorderedPair<T> p) {
		if (this == p) return 0;
		int c1 = first.compareTo(p.first);
		int c2 = second.compareTo(p.second);
		if (c1 == 0) return c2;
		return c1;
	}
}