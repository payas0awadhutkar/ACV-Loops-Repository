package library;

public class Node<K,V> {
	public int hash;

    public K key;

    public V value;
    
    public Node<K,V> next;
    
    public Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
    
    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }
    
    public static int hash(Object key, int capacity) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }
}
