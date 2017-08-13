package Lists;

import java.util.Iterator;

public class LinkedList<T> extends AbstractList<T> {
	public boolean _DUPLICATES_ALLOWED_ = true;

	private int size = 0;
	private Node<T> listTail;
	private Node<T> listHead;

	public static class Node<T> {
		private T data;
		private Node<T> next;

		/*
		 * constructor
		 */
		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

		/*
		 * copy constructor
		 */
		private Node(Node node) {
			this((T) node.data, node.next);
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public Node<T> clone() {
			return new Node<T>(this);
		}

		@Override
		public String toString() {
			return this.data.toString();
		}

		@Override
		public boolean equals(Object node) {
			if (this == node) {
				return true;
			} else if (node == null) {
				return false;
			} else if (getClass() != node.getClass()) {
				return false;
			} else if (!(node instanceof Node<?>)) {
				return false;
			} else {
				Node<T> other = (Node<T>) node;
				if (data == null) {
					if (other.data != null) {
						return false;
					}
				} else if (!data.equals(other.data)) {
					return false;
				}
				if (next == null) {
					if (other.next != null) {
						return false;
					}
				} else if (!next.equals(other.next)) {
					return false;
				}
				return true;
			}
		}

	}

	/*
	 * constructor
	 */
	public LinkedList() {
		listHead = listTail = null;
		size = 0;
	}

	/*
	 * copy constructor
	 */
	private LinkedList(LinkedList<T> list) {
		this();

		// copy the object attributes
		this.size = list.size;
		this.listHead = list.listHead;
		this.listTail = list.listTail;

		Node<T> ref = this.listHead;
		while (ref.next != null) {
			ref = ref.clone();

			ref = ref.next;
		}

	}

	/*
	 * add node at given index
	 */
	public boolean addIndex(int index, T data) {
		if (!_DUPLICATES_ALLOWED_) {
			if (contains(data)) {
				return false;
			}
		}

		if (index < 0 && index > size()) {
			return false;
		}

		Node<T> prev = getNode(index - 1);
		Node<T> temp = new Node<T>(data, getNode(index));

		if (prev == null && index == 0) {
			listHead = temp;

			if (size() == 0) {
				listTail = listHead;
			}

		} else if (size() == index) {
			listTail = listTail.next = temp;
		} else {
			prev.next = temp;
		}

		size++;

		return true;
	}

	/*
	 * remove node that is in the list
	 */
	private Node<T> remove(Node<T> node) {
		Node<T> ref = listHead, previous = listHead;
		if (node == null) {
			return null;
		} else if (isEmpty()) {
			return null;
		} else if (size() == 1 && listHead == node) {
			previous = listHead;
			clear();
			return previous;
		} else {
			while (ref != null) {
				if (ref == node) {

					previous.next = ref.next;
					size--;

					if (ref == listTail) {
						listTail = previous;
					} else if (node == listHead) {
						listHead = node.next;
					}

					break;
				} else if (ref == listTail) {
					return null;
				} else {
					previous = ref;
					ref = ref.next;
				}
			}

			return ref;
		}
	}

	/*
	 * remove node that contains given data
	 */
	public T remove(T data) {
		Node<T> node = listHead;

		while (node != null) {
			if (node.data.equals(data)) {
				node = remove(node);
				return node != null ? node.data : null;
			}

			node = node.next;
		}

		return null;
	}

	/*
	 * iterator implementation of the list
	 */
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {
			LinkedList<T> self = self();
			private Node<T> listHeadPointer = listHead;
			private Node<T> currentIteratorNode = listHeadPointer;
			private boolean removedCalled = true;

			public boolean hasNext() {
				if (currentIteratorNode == null) {
					return false;
				} else {
					return currentIteratorNode.next != null;
				}
			}

			public T next() {
				T nextValue = currentIteratorNode.data;
				currentIteratorNode = currentIteratorNode.next;
				removedCalled = false;
				return nextValue;
			}

			public void remove() {
				if (removedCalled) {
					return;
				} else {
					self.remove(currentIteratorNode);

					removedCalled = true;
				}
			}
		};
		return it;
	}

	/*
	 * clear the list or reinitialize
	 */
	public void clear() {
		listHead = listTail = null;
		size = 0;
	}

	/*
	 * get node data at given index
	 */
	public T get(int index) {
		Node<T> node = getNode(index);
		return (T) (node == null ? null : node.data);
	}

	/*
	 * get node at given index
	 */
	private Node<T> getNode(int index) {
		Node<T> node = listHead;
		while (node != null) {
			if (index == 0) {
				return node;
			} else if (node.next == null) {
				return null;
			} else {
				node = node.next;
			}

			index--;
		}

		return null;
	}

	/*
	 * returns index of element that has data equals to given node
	 */
	private int indexOf(Node<T> node) {
		int index = 0;
		Node<T> ref = listHead;

		while (ref != null && node != null) {
			if (ref.data.equals(node.data)) {
				return index;
			}

			ref = ref.next;
			index++;
		}

		return -1;
	}

	/*
	 * returns index of element that has data equals to given data
	 */
	public int indexOf(T data) {
		return indexOf(new Node<T>(data, null));
	}

	/*
	 * return size of the list
	 */
	public int size() {
		return size;
	}

	/*
	 * similar to self trick in JavaScript
	 */
	private LinkedList<T> self() {
		return this;
	}

	/*
	 * clone "this"
	 */
	@Override
	public LinkedList<T> clone() {
		return new LinkedList<T>(this);
	}

	/*
	 * list to string
	 */
	@Override
	public String toString() {
		String str = "";

		if (size() == 0) {
			return "LinkedList is empty!";
		}

		Node<T> node = listHead;
		while (node != null) {
			str += "=> " + node.toString() + "\n";
			node = node.next;
		}
		return str;
	}

	/*
	 * check if "this" equals to the give list
	 */
	@Override
	public boolean equals(Object list) {
		if (this == list) {
			return true;
		} else if (list == null) {
			return false;
		} else if (getClass() != list.getClass()) {
			return false;
		} else if (!(list instanceof LinkedList<?>)) {
			return false;
		} else {

			LinkedList<T> linkedList = (LinkedList<T>) list;

			boolean flag = true;
			Node<T> ref1 = this.listHead;
			Node<T> ref2 = linkedList.listHead;

			if (this.size() != linkedList.size) {
				return false;
			}

			while (ref1.next != null && ref2.next != null) {

				flag = flag && ref1.getData().equals(ref2.data);

				ref1 = ref1.next;
				ref2 = ref2.next;
			}

			return flag;
		}
	}

}
