package de.giesker.bjarne.util;

public class MyLinkedList<T> {

	/**
	 * Element Klasse, die als Paket für die LinkedList fungiert. Es beinhaltet den
	 * Wert des Elements und die Pointer auf das vorherige und nachfolgende Paket
	 */
	private class E {
		// Attribute:
		T content;
		E prevE, nextE;

		// Konstruktor:
		public E(T cont, E prev, E next) {
			this.content = cont;
			this.prevE = prev;
			this.nextE = next;
		}
	}

	// Attribute:
	private E start, end;

	public MyLinkedList() {
		this.start = null;
		this.end = null;
	}

	// Methoden:

	/**
	 * Hängt das angegebene Element an das Ende der Liste an.
	 * 
	 * @param var
	 */
	public void add(T var) {
		this.addLast(var);
	}

	/**
	 * Ergänzt das angegebene Element an das andegebenen Stelle in der Liste .
	 * 
	 * @param index
	 * @param var
	 */
	public void add(int index, T var) {
		int length = this.size();
		// Beruft sich auf addFirst und addLast wenn möglich
		if (index == 0) {
			this.addFirst(var);
			return;
		}
		if (index == length) {
			this.addLast(var);
			return;
		}
		// Wenn LinkedList leer ist, ist neue Element sowohl das Start- als auch
		// Endelement
		if (this.isEmpty()) {
			this.start = new E(var, null, null);
			this.end = this.start;
			return;
		}
		// Einsortieren geschieht je nach Indexort in der Linked List (von vor oder
		// hinter der Mitte)
		E currE;
		// Fortschreiten bis zum Index
		if (index < length / 2) {
			currE = this.start;
			for (int i = 0; i < index; i++) {
				currE = currE.nextE;
			}
		} else {
			currE = this.end;
			for (int i = length - 1; i > index; i--) {
				currE = currE.prevE;
			}
		}
		// CurrE ist nun das Element auf dem gewünschten Index
		E prevE = currE.prevE;
		E newE = new E(var, prevE, currE);
		prevE.nextE = newE;
		currE.prevE = newE;
	}

	/**
	 * Fügt das angegebene Element am Anfang der Liste ein.
	 * 
	 * @param var
	 */
	public void addFirst(T var) {
		// Wenn LinkedList leer ist, ist neue Element sowohl das Start- als auch
		// Endelement
		if (this.isEmpty()) {
			this.start = new E(var, null, null);
			this.end = this.start;
			return;
		}
		// Neues Element wird vor dem aktuellen Startelement ergänzt
		E newStart = new E(var, null, this.start);
		this.start.prevE = newStart;
		this.start = newStart;
	}

	/**
	 * Hängt das angegebene Element an das Ende der Liste an.
	 * 
	 * @param var
	 */
	public void addLast(T var) {
		// Wenn LinkedList leer ist, ist neue Element sowohl das Start- als auch
		// Endelement
		if (this.isEmpty()) {
			this.start = new E(var, null, null);
			this.end = this.start;
			return;
		}
		// Neues Element wird hinter dem aktuellen Endelement ergänzt
		E newEnd = new E(var, this.end, null);
		this.end.nextE = newEnd;
		this.end = newEnd;

	}

	/**
	 * Entfernt alle Elemente aus dieser Liste.
	 */
	public void clear() {
		this.start = null;
		this.end = null;
	}

	/**
	 * Gibt eine oberflächliche Kopie dieser LinkedList zurück.
	 */
	public MyLinkedList<T> clone() {
		MyLinkedList<T> copy = new MyLinkedList<T>();
		E temp = this.start;
		int length = this.size();
		for (int i = 0; i < length; i++) {
			copy.add(temp.content);
			temp = temp.nextE;
		}
		return copy;
	}

	/**
	 * Gibt true zurück, wenn diese Liste das angegebene Element enthält.
	 * 
	 * @param var
	 * @return true/false
	 */
	public boolean contains(T var) {
		E currE = this.start;
		for (int i = 0; i < size(); i++) {
			if (currE.content.equals(var)) {
				return true;
			}
			currE = currE.nextE;
		}
		return false;
	}

	/**
	 * Gibt das Element an der angegebenen Position in dieser Liste zurück.
	 * 
	 * @param index
	 * @return Element
	 */
	public T get(int index) {
		E temp;
		if (index < this.size() / 2) {
			temp = this.start;
			for (int i = 0; i < index; i++) {
				temp = temp.nextE;

			}
		} else {
			temp = this.end;
			for (int i = 0; i < this.size() - 1 - index; i++) {
				temp = temp.prevE;
			}
		}
		return temp.content;
	}

	/**
	 * Gibt das erste Element in dieser Liste zurück.
	 * 
	 * @return erstes Element
	 */
	public T getFirst() {
		return this.start.content;
	}

	/**
	 * Gibt das letzte Element in dieser Liste zurück.
	 * 
	 * @return letzte Element
	 */
	public T getLast() {
		return this.end.content;
	}

	/**
	 * Gibt zurück, ob die LinktList gefüllt oder leer ist.
	 * 
	 * @return true/false
	 */
	public boolean isEmpty() {
		return start == null && end == null;
	}

	/**
	 * Gibt den Index des ersten Vorkommens des angegebenen Elements in dieser Liste
	 * zurück, oder -1, wenn diese Liste das Element nicht enthält.
	 * 
	 * @param var
	 * @return Index
	 */
	public int indexOf(T var) {
		return this.firstIndexOf(var);
	}

	/**
	 * Gibt den Index des ersten Vorkommens des angegebenen Elements in dieser Liste
	 * zurück, oder -1, wenn diese Liste das Element nicht enthält.
	 * 
	 * @param var
	 * @return Index
	 */
	public int firstIndexOf(T var) {
		E currE = this.start;
		int length = this.size();
		for (int i = 0; i < length; i++) {
			if (currE.content.equals(var)) {
				return i;
			}
			currE = currE.nextE;
		}
		return -1;

	}

	/**
	 * Gibt den Index des letzten Vorkommens des angegebenen Elements in dieser
	 * Liste zurück, oder -1, wenn diese Liste das Element nicht enthält.
	 * 
	 * @param var
	 * @return Index
	 */
	public int lastIndexOf(T var) {
		E temp = this.end;
		int length = this.size();
		for (int i = length - 1; i >= 0; i--) {
			if (temp.content.equals(var)) {
				return i;
			}
			temp = temp.prevE;
		}
		return -1;
	}

	/**
	 * Löscht ein bestimmtes Element aus dieser LinkedList
	 * 
	 * @param var
	 */
	public void remove(T var) {
		// Abbruch falls LinkedList leer ist
		if (this.isEmpty()) {
			return;
		}
		// Durchsucht von vorne bis hinter die LinkedList nach dem Element
		int length = this.size();
		E temp = this.start;
		for (int i = 0; i < length; i++) {
			if (temp.content.equals(var)) {
				// Falls es das erste oder letzte Element ist, wird auf die jeweilig Methode
				// zurückgegriffen
				if (i == 0) {
					this.removeFirst();
				}
				if (i == length - 1) {
					this.removeLast();
				}
				E prevE = temp.prevE;
				E nextE = temp.nextE;
				prevE.nextE = nextE;
				nextE.prevE = prevE;
				break;
			}
			temp = temp.nextE;
		}
	}

	/**
	 * Löscht ein Element an einem bestimmten Index aus dieser LinkedList
	 * 
	 * @param index
	 */
	public void remove(int index) {
		// Abbruch falls LinkedList leer ist
		if (this.isEmpty()) {
			return;
		}
		// Falls das erste oder letzte Element entfernt werden soll, wird auf die
		// jeweilig Methode
		// zurückgegriffen
		int length = this.size();
		if (index <= 0) {
			this.removeFirst();
			return;
		}
		if (index >= length - 1) {
			this.removeLast();
			return;
		}
		// Je nach Index wird von vorne oder hinten nach dem Index gesucht und
		// anschließend entfernt
		E temp;
		if (index < length / 2) {
			temp = this.start;
			for (int i = 0; i < index; i++) {
				temp = temp.nextE;
			}
		} else {
			temp = this.end;
			for (int i = 0; i < length - index - 1; i++) {
				temp = temp.prevE;
			}
		}
		E prevE = temp.prevE;
		E nextE = temp.nextE;
		prevE.nextE = nextE;
		nextE.prevE = prevE;
	}

	/**
	 * Löscht das erste Element aus der LinkedList
	 */
	public void removeFirst() {
		// Abbruch falls LinkedList leer ist
		if (this.isEmpty()) {
			return;
		}
		// Wenn es das jetzte Element ist, wird die LinkedList gecleart, sonst wird
		// lediglich das erste Element erfernt
		if (this.size() == 1) {
			this.clear();
		} else {
			E newStart = start.nextE;
			this.start = newStart;
			this.start.prevE = null;
		}
	}

	/**
	 * Löscht das letzte Element aus der LinkedList
	 */
	public void removeLast() {
		// Abbruch falls LinkedList leer ist
		if (this.isEmpty()) {
			return;
		}
		// Wenn es das jetzte Element ist, wird die LinkedList gecleart, sonst wird
		// lediglich das letzte Element erfernt
		if (this.size() == 1) {
			this.clear();
		} else {
			E newEnd = end.prevE;
			this.end = newEnd;
			this.end.nextE = null;
		}
	}

	/**
	 * Zahlt die Länge der LinkedList und gibt sie zurück Äquivalent zu getLenght()
	 * 
	 * @return Länge
	 */
	public int size() {
		E temp = this.start;
		int counter = 0;
		while (temp != null) {
			temp = temp.nextE;
			counter++;
		}
		return counter;
	}

}
