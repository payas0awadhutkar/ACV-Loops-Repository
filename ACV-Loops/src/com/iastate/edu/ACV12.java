package com.iastate.edu;

import java.util.AbstractMap;

import library.Node;

public class ACV12<K,V> {

	Node<K,V>[] table;
	
	int capacity = 16; // Initialization. Value can be changed
	
	public V put(K key, V value) {
        V e = null;
        int h = hash(key);
        Node<K, V> node = table[h];
        TN<K, V> TN, result;
        TN = (TN<K, V>) node;
        result = TN.put(table, hash(key), key, value);
        if (result == null) {
        	// storing logic
            return null;
        } else if (result.value != value) {
            // storing logic
        }
        return e;
    }
	
	private int hash(K key) {
        return Node.hash(key, capacity);
    }
	
	class TN<K,V> extends Node<K,V>{

		TN<K, V> parent,left,right,prev;
		
		public TN(int hash, K key, V value, Node<K, V> next) {
			super(hash, key, value, next);
		}

		public TN<K, V> put(Node<K, V>[] table, int h, K k, V v) {
			Class<?> kc = null;
	        boolean searched = false;
	        TN<K, V> root = (parent != null) ? root() : this;
	        for (TN<K, V> p = root; ; ) {
	            int dir, ph;
	            K pk;
	            if ((ph = p.hash) > h)
	                dir = -1;
	            else if (ph < h)
	                dir = 1;
	            else if ((pk = p.key) == k || (pk != null && k.equals(pk)))
	                return p;
	            else if ((kc == null && (kc = comparableClassFor(k)) == null) || (dir = compareComparables(kc, k, pk)) == 0) {
	                if (!searched) {
	                    TN<K, V> q, ch;
	                    searched = true;
	                    if (((ch = p.left) != null && (q = ch.find(h, k, kc)) != null) || ((ch = p.right) != null && (q = ch.find(h, k, kc)) != null))
	                        return q;
	                }
	                dir = tieBreakOrder(k, pk);
	            }
	            TN<K, V> xp = p;
	            if ((p = (dir <= 0) ? p.left : p.right) == null) {
	                Node<K, V> xpn = xp.next;
	                TN<K, V> x = new TN(h, k, v, xpn);
	                if (dir <= 0)
	                    xp.left = x;
	                else
	                    xp.right = x;
	                xp.next = x;
	                x.parent = x.prev = xp;
	                if (xpn != null)
	                    ((TN<K, V>) xpn).prev = x;
	                return null;
	            }
		
		}
		
	}

		private int compareComparables(Class<?> kc, K k, K pk) {
			// stub
			return 0;
		}

		private TN<K, V> find(int h, K k, Class<?> kc) {
			// code excluded.
			// you can assume the logic of finding node does not cause problems
			return null;
		}

		private TN<K, V> root() {
			for (TN<K, V> r = this, p; ; ) {
	            if ((p = r.parent) == null)
	                return r;
	            r = p;
	        }
		}

		private Class<?> comparableClassFor(K k) {
			// stub
			return null;
		}

		private int tieBreakOrder(K k, K pk) {
			// code exclued
			// can assume does not cause problems
			return 0;
		}
	}
}
