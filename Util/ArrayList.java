package Util;
public class ArrayList<T extends Comparable<T>> {
	private Object[] arr;
	private int size;

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

	public T get(int i) {
		return (T)arr[i];
	}

	public int size() {
		return size;
	}

	private void qsort(int s, int e) {
		int ss = s, ee = e;
		if (e <= s) return;
		T p = (T)arr[(s+e)/2];
		int m = s;
		while (m <= e) {
			if (((T)arr[m]).compareTo(p) < 0) {
				Object t = arr[s];
				arr[s] = arr[m];
				arr[m] = t;
				m++;
				s++;
			} else if (((T)arr[m]).compareTo(p) > 0) {
				Object t = arr[m];
				arr[m] = arr[e];
				arr[e] = t;
				e--;
			} else {
				m++;
			}
		}
		qsort(ss, s-1);
		qsort(e+1, ee);
	}

	public void sort() {
		qsort(0, size-1);
	}
}