package de.giesker.bjarne.util;


public class MyStack<T> {

	/**
	 * Element Klasse, die als Paket für den Stack fungiert. Es beinhaltet den Wert
	 * des Elements und die Pointer auf das nachfolgende Paket
	 */
	private class E {
		// Attribute:
		T content;
		E successor;

		// Konstruktor:
		public E(T content, E successor) {
			this.content = content;
			this.successor = successor;
		}
	}

	// Attribute:
	private E top;

	// Konstruktor:
	public MyStack() {
		this.top = null;
	}

	// Methoden:

	/**
	 * Entfernt alle Elemente aus diesem Stack.
	 */
	public void clear() {
		this.top = null;
	}

	/**
	 * Gibt eine oberflächliche Kopie dieser Queue zurück.
	 */
	public MyStack<T> clone() {
		MyStack<T> tempCopy = new MyStack<T>();
		E temp = this.top;
		for (int i = 0; i < size(); i++) {
			tempCopy.push(temp.content);
			temp = temp.successor;
		}

		MyStack<T> copy = new MyStack<T>();
		for (int i = 0; i < size(); i++) {
			copy.push(tempCopy.pop());
		}
		return copy;
	}

	/**
	 * Gibt zurück, ob der Stack gefüllt oder leer ist.
	 * 
	 * @return true/false
	 */
	public boolean isEmpty() {
		return (this.top == null);
	}

	/**
	 * Legt das angegebene Element oben auf den Stack.
	 * 
	 * @param var
	 */
	public void push(T content) {
		E newElement = new E(content, null);
		if (!isEmpty()) {
			newElement.successor = this.top;
		}
		top = newElement;
	}

	public T pop() {
		T content = this.top.content;
		this.top = this.top.successor;
		return content;
	}

	public T top() {
		return top.content;
	}

	public int size() {
		E temp = this.top;
		int index = 0;
		while (temp != null) {
			index++;
			temp = temp.successor;
		}
		return index;
	}
}
