import Util.*;
public class Test {
	private static void println(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) {
		Map<Integer, Integer> s = new Map<Integer, Integer>();
		int arr[] = {1,3,0,1,5,2,2,4,1};
		for (int i = 0; i < arr.length; ++i) {
			s.insert(arr[i], arr[i]);
			println("insert " + arr[i]);
			ArrayList<Integer> a = s.getValues();
			for (int j = 0; j < a.size(); ++j) {
				System.out.print(a.get(j) + " ");
			}
			println("");
			println("---");
		}
	}
}