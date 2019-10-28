import Util.*;
public class Test {
	private static void println(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) {
		UnorderedTriple<Integer> p1 = new UnorderedTriple<Integer>(1, 2, 3);
		UnorderedTriple<Integer> p2 = new UnorderedTriple<Integer>(4, 3, 2);
		UnorderedTriple<Integer> p3 = new UnorderedTriple<Integer>(2, 4, 3);
		UnorderedTriple<Integer> p4 = new UnorderedTriple<Integer>(2, 3, 1);
		UnorderedTriple<Integer> p5 = new UnorderedTriple<Integer>(3, 4, 2);
		UnorderedTriple<Integer> p6 = new UnorderedTriple<Integer>(3, 2, 1);
		println(p1.compareTo(p2));
		println(p2.compareTo(p3));
		println(p3.compareTo(p4));
		println(p4.compareTo(p5));
		println(p5.compareTo(p6));
		println(p6.compareTo(p1));
	}
}