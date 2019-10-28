package Util;
public class ArrayList<T> {
	private T[] arr;
	private int size;

	public ArrayList() {
		arr = (T[])(new Object[1]);
		size = 0;
	}

	public void add(T e) {
		if (arr.length == size) {
			T[] tmp = (T[])(new Object[2*size]);
			for (int i = 0; i < size; ++i) {
				tmp[i] = arr[i];
			}
			arr = tmp;
		}
		arr[size] = e;
		size += 1;
	}

	public T get(int i) {
		return arr[i];
	}

	public int size() {
		return size;
	}
}