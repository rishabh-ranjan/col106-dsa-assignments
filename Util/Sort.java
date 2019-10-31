package Util;

public class Sort<T extends Comparable<T>> {
	private Object[] arr;
	private int size;

	private Sort(ArrayList<T> a) {
		arr = a.arr;
		size = a.size;
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

	public static <E extends Comparable<E>> void sort(ArrayList<E> a) {
		Sort<E> obj = new Sort<E>(a);
		obj.qsort(0, obj.size-1);
	}
}