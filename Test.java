public class Test {
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 0; i < 100; ++i) {
			a.add(i);
		}
		for (int i = 0; i < 100; ++i) {
			System.out.println(a.get(i));
		}
	}
}