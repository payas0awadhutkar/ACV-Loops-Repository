package com.iastate.edu;

import java.util.Set;
import java.util.TreeSet;

import library.Node;

public class ACV4<K,V> {
	public static int DEFAULT_INTEGER_INITIALIZATION = Integer.MIN_VALUE;
	
	Node<K,V>[] t;
	
	Set es = new TreeSet();
	
	int s = DEFAULT_INTEGER_INITIALIZATION;
	
	// Attacker has mechanisms to control value of condition. Code excluded
	boolean condition;
	
	public V put(K key, V value) {
		V e = null;
		int h = DEFAULT_INTEGER_INITIALIZATION;
		Node<K,V> node = t[h];
		if(condition) {
			// operations that do not cause ACV
		}
		else {
			int bincount = 0;
			while (node.next != null && !node.key.equals(key)) {
				node = node.next;
				bincount++;
			}
			if (node.key.equals(key)) {
				e = node.value;
				node.value = value;
			} else {
				node.next = new Node(Node.hash(key, DEFAULT_INTEGER_INITIALIZATION), key, value, null);
				es.add(node.next);
				if (bincount > 8) {
					putHelper(h);
				}
				s++;
			}
		}
		return e;
	}
	private void putHelper(int h) {
		// expensive computation in this context
	}
}
