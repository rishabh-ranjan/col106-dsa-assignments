package Util;
public class ArrayList<T> {
	Object[] arr;
	int size;

	public ArrayList() {
		arr = new Object[1];
		size = 0;
	}

	public void add(T e) {
		if (arr.length == size) {
			Object[] tmp = new Object[2*size];
			for (int i = 0; i < size; ++i) {
				tmp[i] = arr[i];
			}
			arr = tmp;
		}
		arr[size] = e;
		size += 1;
	}

	public void delete(int i) {
		Object tmp = arr[size-1];
		arr[size-1] = arr[i];
		arr[i] = tmp;
		size--;
	}

	public T get(int i) {
		return (T)arr[i];
	}

	public int size() {
		return size;
	}
}