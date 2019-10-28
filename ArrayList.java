class ArrayList<T> {
	T[] arr;
	int size;

	ArrayList() {
		arr = (T[])(new Object[1]);
		size = 0;
	}

	void add(T e) {
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

	T get(int i) {
		return arr[i];
	}

	int size() {
		return size;
	}
}