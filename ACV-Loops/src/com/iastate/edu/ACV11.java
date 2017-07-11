package com.iastate.edu;

public class ACV11<K,V> {
	
	public static int DEFAULT_INTEGER_INITIALIZATION = 16;
	
	public static int MALICIOUS_INTEGER_INITIALIZATION = 1 << 30;
	
	public static int SIZE_LIMIT = 1024;
	
	Entry<K,V>[] table;
	
	class Entry<K,V> {

        K key;
        
        V value;
        
        Entry<K, V> next;

        int hash;
        
        Entry(int h, K k, V v, Entry<K, V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

		public void recordAccess(ACV11<K, V> acv11) {
			// stub
		}
	}
	
	public ACV11(String FileType, int capacity) {
		if(FileType == "Compressed") {
			table = new Entry[MALICIOUS_INTEGER_INITIALIZATION];
		}
		else {
			if(capacity > SIZE_LIMIT) {
				table = new Entry[SIZE_LIMIT];
			}
			else {
				table = new Entry[capacity];
			}
		}
	}
	
	public V put(K key, V value) {
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key.hashCode());
        int i = indexFor(hash, table.length);
        for (Entry<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        addEntry(hash, key, value, i);
        return null;
    }
	
	private void addEntry(int hash, K key, V value, int i) {
		// stub handler to add entry
		
	}

	private V putForNullKey(V value) {
		// stub handler for null key
		return null;
	}

	static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
	
	static int indexFor(int h, int length) {
        return h & (length - 1);
    }
}
