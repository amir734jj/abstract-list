package Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public abstract class AbstractList<T> {

	/*
	 * abstract methods
	 */

	public abstract boolean addIndex(int index, T data);

	public abstract T remove(T data);

	public abstract Iterator<T> iterator();

	public abstract void clear();

	public abstract T get(int index);

	public abstract int indexOf(T data);

	public abstract int size();

	public abstract AbstractList<T> clone();

	/*
	 * get first element of the list
	 */
	public T first() {
		return get(0);
	}

	/*
	 * get last element of the list
	 */
	public T last() {
		return get(size() - 1);
	}

	/*
	 * add item at the last index
	 */
	public void addLast(T item) {
		addIndex(size(), item);
	}

	/*
	 * add item at the first index
	 */
	public void addFirst(T item) {
		addIndex(0, item);
	}

	/*
	 * check if list is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/*
	 * add all elements of the collection to the list at given index
	 */
	public boolean addAll(int index, Collection<? extends T> collection) {
		Iterator<? extends T> it = collection.iterator();

		boolean flag = false;

		while (it.hasNext()) {
			T s = (T) it.next();
			if (addIndex(index, s)) {
				index++;
				flag = true;
			}
		}

		return flag;
	}

	/*
	 * add all elements of the collection to the end of list
	 */
	public boolean addAll(Collection<? extends T> collection) {
		return addAll(size(), collection);
	}

	/*
	 * check if list contains given node
	 */
	public boolean contains(T data) {
		return indexOf(data) > -1;
	}

	/*
	 * randomly shuffle the list
	 */
	public void shuffle() {
		Random rand = new Random();
		int randomizeCount = size() / 4;
		T pointer;

		while (randomizeCount-- > 0) {
			pointer = get(rand.nextInt(size()));
			remove(pointer);
			if (randomizeCount % 2 == 0) {
				addFirst(pointer);
			} else {
				addLast(pointer);
			}
		}
	}
}
