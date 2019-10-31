import Util.*;
public class Test {
	private static void println(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) {
		Set<String> s = new Set<String>();
		String[] arr = {
			"abc",
			"abc",
			"xyz",
			"xyz",
			"xyz",
			"abc",
			"hello",
			"xyz",
			"hello",
			"abc",
			"hello",
			"123",
			"1234",
			"12345",
			"123",
			"abc",
			"rishabh",
			"ranjan",
			"ranjan"
		};

		for (int i = 0; i < arr.length; ++i) {
			s.insert(arr[i]);
		}

		ArrayList<String> r = s.getElements();
		for (int i = 0; i < r.size(); ++i) {
			println(r.get(i));
		}
	}
}