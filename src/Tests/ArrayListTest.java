package Tests;

import java.util.Arrays;
import java.util.Iterator;
import org.junit.*;
import static org.junit.Assert.*;
import Lists.ArrayList;

public class ArrayListTest {

	private static int _COUNT;
	private static ArrayList<Character> _LIST;
	private static Character[] _MASTER_DATA;
	private static Character _CH = Character.valueOf('@');

	@Test
	public void testListSize() {
		initializeArrayList();

		assertEquals(_COUNT, _LIST.size());
	}

	@Test
	public void testListRemove() {
		initializeArrayList();

		int i = _LIST.size() - 1;
		while (i >= 0) {
			_LIST.remove(_MASTER_DATA[i]);
			i--;
		}

		assertEquals(0, _LIST.size());
	}

	@Test
	public void testIterator() {
		initializeArrayList();

		Iterator<Character> it = _LIST.iterator();

		int i = 0;
		while (it.hasNext()) {
			assertEquals(_MASTER_DATA[i], it.next());
			i++;
		}

		it = _LIST.iterator();
		int sizeBeforeRemove = _LIST.size();
		assertTrue(it.hasNext());
		it.next();
		it.remove();
		assertEquals(sizeBeforeRemove - 1, _LIST.size());

	}

	@Test
	public void testIndexAndGet() {
		initializeArrayList();

		int i = _LIST.size() - 1;
		while (i >= 0) {
			assertEquals(_MASTER_DATA[i],
					_LIST.get(_LIST.indexOf(_MASTER_DATA[i])));
			i--;
		}
	}

	@Test
	public void testShuffle() {
		initializeArrayList();

		ArrayList<Character> beforeShuffle = _LIST.clone();

		_LIST.shuffle();

		assertEquals(beforeShuffle.size(), _LIST.size());
		assertNotEquals(_LIST, beforeShuffle);
	}

	@Test
	public void testEquals() {
		initializeArrayList();

		assertEquals(_LIST, _LIST);
		assertNotEquals(_LIST, new ArrayList<String>());
		assertNotEquals(_LIST, null);
	}

	@Test
	public void testClone() {
		initializeArrayList();
		assertEquals(_LIST, _LIST.clone());
	}

	@Test
	public void testHead() {
		initializeArrayList();

		_LIST.addFirst(_CH);
		assertEquals(_LIST.first(), _CH);
		assertEquals(0, _LIST.indexOf(_CH));
	}

	@Test
	public void testTails() {
		initializeArrayList();

		_LIST.addLast(_CH);
		assertEquals(_CH, _LIST.last());
		assertEquals(_LIST.size() - 1, _LIST.indexOf(_CH));
	}

	@Test
	public void testAddIndex() {
		initializeArrayList();

		int sizeBeforeAdd = _LIST.size();
		_LIST.addIndex(3, _CH);
		assertEquals(sizeBeforeAdd + 1, _LIST.size());
		assertTrue(_LIST.get(3).equals(_CH));
	}

	@Test
	public void testAddAll() {
		initializeArrayList();

		int sizeBeforeAdd = _LIST.size();

		Character[] array = new Character[] { '!', '?', ';' };

		_LIST.addAll(Arrays.asList(array));

		assertEquals(sizeBeforeAdd + array.length, _LIST.size());

		assertEquals(_LIST.get(sizeBeforeAdd), array[0]);
		assertEquals(_LIST.get(sizeBeforeAdd + 1), array[1]);
		assertEquals(_LIST.get(sizeBeforeAdd + 2), array[2]);
	}

	@Test
	public void testClean() {
		initializeArrayList();

		_LIST.clear();
		assertEquals(0, _LIST.size());
		assertEquals(null, _LIST.first());
		assertEquals(null, _LIST.last());
	}

	public static void initializeArrayList() {

		char[] arr = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		_COUNT = arr.length;
		_MASTER_DATA = new Character[_COUNT];
		_LIST = new ArrayList<Character>();

		for (int i = 0; i < _COUNT; i++) {
			_MASTER_DATA[i] = Character.valueOf(arr[i]);

			_LIST.addLast(_MASTER_DATA[i]);
		}
	}
}
