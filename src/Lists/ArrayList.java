package Lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayList<T> extends AbstractList<T> {
	public boolean _DUPLICATES_ALLOWED_ = true;

	private int size;
	private T[] array;

	/*
	 * constructor
	 */
	public ArrayList() {
		size = 0;
		array = (T[]) new Object[3];
	}

	/*
	 * copy constructor
	 */
	private ArrayList(ArrayList<T> list) {
		this();
		this.size = list.size;
		this.array = list.array.clone();
	}

	/*
	 * remove element from the list
	 */
	public T remove(T item) {
		int index = this.indexOf(item);

		if (index == -1) {
			return null;
		}

		T retVal = array[index];

		for (; index < size - 1; index++) {
			array[index] = array[index + 1];
		}
		size--;
		return retVal;
	}

	/*
	 * returns index of the element
	 */
	public int indexOf(T item) {
		for (int i = 0; i < size(); i++) {
			if (array[i].equals(item)) {
				return i;
			}
		}

		return -1;
	}

	/*
	 * returns the size of the list
	 */
	public int size() {
		return size;
	}

	/*
	 * increase list size
	 */
	private void sizeIncrease() {
		T[] newArray = (T[]) new Object[2 * size + 1];
		System.arraycopy(array, 0, newArray, 0, size);
		array = newArray;
	}

	/*
	 * empty the list
	 */
	public void clear() {
		this.size = 0;
		this.array = new ArrayList<T>().array;
	}

	/*
	 * list iterator implementation
	 */
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private int currentIndex = 0;
			private boolean removedCalled = false;

			public boolean hasNext() {
				return currentIndex < size && array[currentIndex] != null;
			}

			public T next() {
				this.removedCalled = false;
				return array[currentIndex++];
			}

			public void remove() {
				if (this.removedCalled)
					return;
				for (int i = currentIndex - 1; i < size - 1; i++)
					array[i] = array[i + 1];
				this.removedCalled = true;
				size--;
				currentIndex--;
			}
		};
	}

	/*
	 * add element at given index
	 */
	public boolean addIndex(int index, T data) {
		if (!_DUPLICATES_ALLOWED_) {
			if (contains(data)) {
				return false;
			}
		}

		if (size + 1 == array.length) {
			this.sizeIncrease();
		}

		boolean flag = true;

		if (index >= 0 && index <= size) {

			for (int i = size; i > index; i--) {
				array[i] = array[i - 1];
			}

			array[index] = data;
			size++;

		} else {
			flag = false;
		}

		return flag;
	}

	/*
	 * returns element at given index
	 */
	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		} else {
			return array[index];
		}
	}

	/*
	 * clone the list using copy constructor
	 */
	@Override
	public ArrayList<T> clone() {
		return new ArrayList<T>(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayList<?> other = (ArrayList<?>) obj;
		if (_DUPLICATES_ALLOWED_ != other._DUPLICATES_ALLOWED_)
			return false;
		if (!Arrays.equals(array, other.array))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (size() == 0) {
			return "ArrayList is empty!";
		}

		String str = "";

		for (int i = 0; i < size(); i++) {
			str += "=> " + array[i].toString() + "\n";
		}

		return str;
	}

}
