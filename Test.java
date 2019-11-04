import Util.*;
public class Test {
	private static void println(Object obj) {
		System.out.println(obj);
	}
	private static void print(Object obj) {
		System.out.print(obj);
	}
	public static void main(String[] args) {
		Shape shape = new Shape();
		float arr[][] = {
			{0,0,0,0,1,0,1,1,0},
			{1,1,0,2,1,0,2,0,0},
			{0,0,0,2,0,0,1,-1,0},
			{0,0,0,1,1,0,2,0,0},
			{2,0,0,3,0,0,3,1,0},
			{2,1,0,3,1,0,2,0,0}
		};

		for (int i = 0; i < arr.length; ++i) {
			shape.ADD_TRIANGLE(arr[i]);
			print(shape.componentList.size() + " : ");
			for (int j = 0; j < shape.componentList.size(); ++j) {
				print(shape.componentList.get(j).size() + " ");
			}
			println("");
		}
	}
}