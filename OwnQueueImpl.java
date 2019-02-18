package com.ez.collections;

/*
 * Implement our own queue using singly linked list
 */
public class MyQueue {

	public static void main(String[] args) {
		MyOwnQueue<Integer> queue = new MyOwnQueueImpl<>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		System.out.println(queue.peek());
		System.out.println(queue.isEmpty());
	}

}

class MyOwnQueueImpl<V> implements MyOwnQueue<V> {

	QueueNode<V> head, tail;
	int size = 0;

	@Override
	public void offer(V value) {
		QueueNode<V> newNode = new QueueNode<>(value);

		if (tail == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = tail.next;
		}
		size++;
	}

	@Override
	public V peek() {

		if (head == null) {
			return null;
		}
		return head.value;
	}

	@Override
	public V poll() {

		if (head == null) {
			return null;
		}
		V topValue = head.value;
		head = head.next;
		size--;
		return topValue;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

}

interface MyOwnQueue<V> {
	public void offer(V v);

	public V peek();

	public V poll();

	public int size();

	public boolean isEmpty();
}

class QueueNode<V> {
	V value;
	QueueNode<V> next;

	public QueueNode(V v) {
		value = v;
	}
}
