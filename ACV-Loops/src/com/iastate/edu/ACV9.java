package com.iastate.edu;

import java.util.LinkedList;
import java.util.TreeMap;

import library.Tuple;

public class ACV9 {
	static final int SKIP = -1;
	private static URIElement verifierElements = new URIElement();
	
	public static boolean verify(String string) {
		Tuple<Integer, URIElement> peek;
        LinkedList<Tuple<Integer, URIElement>> tuples = new LinkedList<>();
        tuples.push(new Tuple<>(0, verifierElements));
		
        while (!tuples.isEmpty() && (peek = tuples.pop()) != null) {
	        if (peek.second.isFinal && peek.first == string.length()) {
	            return true;
	        }
	
	        if (string.length() > peek.first) {
	            for (URIElement URIElement : peek.second.get(string.charAt(peek.first))) {
	                tuples.push(new Tuple<>(peek.first + 1, URIElement));
	            }
	        }
	
	        for (URIElement child : peek.second.get(SKIP)) {
	            tuples.push(new Tuple<>(peek.first, child));
	        }
	    }
        
        return false;
	}
	
	public static final class URIElement {
        boolean isFinal;
        TreeMap<Integer, LinkedList<URIElement>> map = new TreeMap<>();

        public URIElement(){}
        public URIElement(boolean isFinal) {
            this.isFinal = isFinal;
        }

        /*
         * Returns the reference stored in the edge set with the given cost "key"
         */
        LinkedList<URIElement> get(int key) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
            return new LinkedList<>();
        }

        /*
         * Adds a new edge to the edge set. The key is the cost of traversing the edge
         * and the value is the reference of the element at the end of the edge.
         */
        void add(int key, URIElement value) {
            LinkedList<URIElement> l;
            if (!this.map.containsKey(key)) {
                l = new LinkedList<>();
                this.map.put(key, l);
            } else {
                l = this.map.get(key);
            }
            l.add(value);
        }
}
}
