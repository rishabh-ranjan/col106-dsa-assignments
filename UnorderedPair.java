package Util;
public class UnorderedPair<T extends Comparable<T>>
		implements Comparable<UnorderedPair<T>> {
	public T first;
	public T second;

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
		if (first == p.first) {
			return second.compareTo(p.second);
		} else {
			return first.compareTo(p.first);
		}
	}
}