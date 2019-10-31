package Util;
public class OrderedTriple<T extends Comparable<T>> implements Comparable<OrderedTriple<T>> {
	public T first;
	public T second;
	public T third;

	public OrderedTriple(T x, T y, T z) {
		first = x;
		second = y;
		third = z;
	}

	public int compareTo(OrderedTriple<T> t) {
		if (this == t) return 0;
		int c1 = first.compareTo(t.first);
		int c2 = second.compareTo(t.second);
		int c3 = third.compareTo(t.third);
		if (c1 == 0 && c2 == 0) return c3;
		if (c1 == 0) return c2;
		return c1;
	}
}