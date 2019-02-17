package com.ez.collections;

import java.util.Arrays;

public class MyArrayList {

	public static void main(String[] args) {

		MyList<String> list = new MyListImpl<>();
		list.add("Riya");
		list.add("Priya");
		list.add("Ezhil");

		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.size());
		System.out.println(list.remove(2));
		System.out.println(list.size());
		System.out.println(list.get(2));

	}

}

interface MyList<E> {
	public void add(E t);

	public E get(int index);

	public E remove(int index);

	public int size();
}

class MyListImpl<E> implements MyList<E> {

	private static final int CAPACITY = 16;
	int tail = 0;
	Object arr[] = null;

	MyListImpl() {
		this(CAPACITY);
	}

	MyListImpl(int _capacity) {
		arr = new Object[_capacity];
	}

	@Override
	public void add(E e) {

		// Reached the max length - time to double the array size
		if (tail == arr.length) {
			arr = Arrays.copyOf(arr, arr.length * 2);
		}
		arr[tail] = e;
		tail++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {

		if (index < 0 || index > tail) {
			throw new ArrayIndexOutOfBoundsException();
		}

		if (index < tail) {
			return (E) arr[index];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {

		if (index < tail) {

			Object val = arr[index];

			for (int i = index; i + 1 < arr.length; i++) {
				arr[i] = arr[i + 1];
			}
			tail--;
			// we can also use System.arrayCopy to do this. But, i think this is
			// convenient
			return (E) val;
		}
		return null;
	}

	@Override
	public int size() {
		return tail;
	}

}
