// Thuật toán sắp xếp
public class Sort {
	public static <T extends Comparable<T>> void selectionSort(T[] data) {
		for (int i = 0; i < data.length; i++) {
			// tìm min
			int min = i;

			for (int scan = i; scan < data.length; scan++)
				if (data[scan].compareTo(data[min]) < 0)
					min = scan;

			swap(data, i, min);
		}

	}

	public static <T extends Comparable<T>> void insertionSort(T[] data) {
		for (int i = 1; i < data.length; i++) {
			T current = data[i]; // current
			int position = i;

			while (position > 0 && data[position - 1].compareTo(current) > 0) {
				data[position] = data[position - 1];
				position--;
			}

			data[position] = current;
		}
	}

	public static <T extends Comparable<T>> void bubbleSort(T[] data) {
		int position, scan;

		for (position = data.length - 1; position > 0; position--) {
			for (scan = 0; scan < position; scan++) {
				if (data[scan].compareTo(data[scan + 1]) > 0)
					swap(data, scan, scan + 1);
			}
		}
	}

	public static <T extends Comparable<T>> void quickSort(T[] data) {
		quickSort(data, 0, data.length - 1);
	}

	private static <T extends Comparable<T>> void quickSort(T[] data, int start, int end) {
		if (start < end) {
			// chọn pivot ở giữa
			int middle = partition(data, start, end);

			// sort trái
			quickSort(data, start, middle - 1);

			// sort phải
			quickSort(data, middle + 1, end);
		}
	}

	private static <T extends Comparable<T>> int partition(T[] data, int start, int end) {
		int pivot = (start + end) / 2;
		int left, right;
		T pivotElem = data[pivot];

		// chuyển pivot lên đầu
		swap(data, pivot, start);

		left = start + 1;
		right = end;

		while (left < right) {
			// tìm ptu lớn hơn pivot
			while (left < right && data[left].compareTo(pivotElem) <= 0)
				left++;

			// tìm ptu nhỏ hơn pivot
			while (data[right].compareTo(pivotElem) > 0)
				right--;

			if (left < right)
				swap(data, left, right);
		}

		swap(data, start, right);

		return right;
	}

	// swap
	private static <T extends Comparable<T>> void swap(T[] data, int element1, int element2) {
		T temp = data[element2];
		data[element2] = data[element1];
		data[element1] = temp;
	}
}
