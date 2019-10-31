import Util.*;
public class Test {
	private static void println(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("apple");
		a.add("zoo");
		a.add("hello");
		a.add("selfie");
		a.add("sumblime");
		a.add(" space ");
		a.add("123");
		a.add("WORLD");
		a.add("HELLO");
		Sort.sort(a);
		for (int i = 0; i < a.size(); ++i) {
			println(a.get(i));
		}
	}
}