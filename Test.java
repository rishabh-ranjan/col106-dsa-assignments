import Util.*;
public class Test {
	private static void println(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < 1000000; ++i) {
			a.add(-i);
		}
		a.sort();
		println("done");
	}
}