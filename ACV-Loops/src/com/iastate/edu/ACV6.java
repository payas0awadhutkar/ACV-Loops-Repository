package com.iastate.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Comparator;

import library.ArrayIndex;
import library.Helper;

public class ACV6 {
	
	public static Comparator comparator;
	
	public static void sort(List list, int start, int end) {
		ArrayIndex i = ArrayIndex.partition(start, end);
	    Stack<ArrayIndex> s = new Stack<ArrayIndex>();
	    s.push(i);
		while(!s.empty()) {
			sort2(s, list);
		}
	}
	
	public static void sort2(Stack<ArrayIndex> s, List list) {
		ArrayIndex index = s.pop();
	    if (index.getStart() < index.getEnd()) {
	        if (index.isPartition()) {
	            int q1 = (int) Math.floor((index.getStart() + index.getEnd()) / 2);
	            int q2 = (int) Math.floor((q1 + 1 + index.getEnd()) / 2);
	            int q3 = (int) Math.floor((q2 + 1 + index.getEnd()) / 2);
	            int q4 = (int) Math.floor((q3 + 1 + index.getEnd()) / 2);
	            int q5 = (int) Math.floor((q4 + 1 + index.getEnd()) / 2);
	            int q6 = (int) Math.floor((q5 + 1 + index.getEnd()) / 2);
	            int q7 = (int) Math.floor((q6 + 1 + index.getEnd()) / 2);
	            s.push(ArrayIndex.merge(index.getStart(), q1, index.getEnd()));
	            s.push(ArrayIndex.merge(q1 + 1, q2, index.getEnd()));
	            s.push(ArrayIndex.merge(q2 + 1, q3, index.getEnd()));
	            s.push(ArrayIndex.merge(q3 + 1, q4, index.getEnd()));
	            s.push(ArrayIndex.merge(q4 + 1, q5, index.getEnd()));
	            s.push(ArrayIndex.merge(q5 + 1, q6, index.getEnd()));
	            s.push(ArrayIndex.merge(q6 + 1, q7, index.getEnd()));
	            s.push(ArrayIndex.partition(index.getStart(), q1));
	            s.push(ArrayIndex.partition(q1 + 1, q2));
	            s.push(ArrayIndex.partition(q2 + 1, q3));
	            s.push(ArrayIndex.partition(q3 + 1, q4));
	            s.push(ArrayIndex.partition(q4 + 1, q5));
	            s.push(ArrayIndex.partition(q5 + 1, q6));
	            s.push(ArrayIndex.partition(q6 + 1, q7));
	            s.push(ArrayIndex.partition(q7 + 1, index.getEnd()));
	        } else if (index.isMerge()) {
	            merge(list, index.getStart(), index.getMidpoint(), index.getEnd());
	        } else {
	            throw new  RuntimeException("Not merge or partition");
	        }
	    }
	}
	
	public static void merge(List list, int start, int midpoint, int end) {
		List left = new ArrayList(midpoint - start + 1);
	    List right = new ArrayList(end - midpoint);
	    for (int i = 0; i < (midpoint - start + 1); ++i) {
	        left.add(list.get(start + i));
	    }
	    for (int j = 0; j < (end - midpoint); ++j) {
	        right.add(list.get(midpoint + 1 + j));
	    }
	    int i = 0;
	    int j = 0;
	    int listLen = end - start + 1;
	    Helper obj0 = new  Helper(0);
	    for (int m = start; m < (end + 1); ++m) {
	        // for the right sized list, this loop leads to ACV
	        if (listLen <= Math.max(Math.pow(2, 10), Math.pow(2, 7 + 7))) {
	            if (i < left.size() && j < right.size()) {
	                list.set(m, right.get(j++));
	                ++m;
	                list.set(m, left.get(i++));
	            } else if (j < right.size()) {
	                list.set(m, right.get(j++));
	            } else if (i < left.size()) {
	                list.set(m, left.get(i++));
	            }
	        } else if (i < left.size() && (j >= right.size() || 
	                comparator.compare(left.get(i), right.get(j)) < obj0.getValue())) {
	            list.set(m, left.get(i++));
	        } else if (j < right.size()) {
	            list.set(m, right.get(j++));
	        }
	    }

	}
}
