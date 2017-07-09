package com.iastate.edu;

import java.util.List;
import java.util.Stack;

import library.ArrayIndex;

public class ACV7 {
	public static void sort(List list, int start, int end) {
		ArrayIndex i = ArrayIndex.partition(start, end);
		Stack<ArrayIndex> s = new Stack<ArrayIndex>();
		s.push(i);
		while (!s.empty()) {
		    ArrayIndex index = s.pop();
		    if (index.getStart() < index.getEnd()) {
		        if (index.isPartition()) {
		            int listLen = index.getEnd() - index.getStart() + 1;
		            int q;
		            if (listLen >= 3) {
		                q = (int) Math.floor(listLen / 3) - 1 + index.getStart();
		            } else {
		                q = index.getStart();
		            }
		            s.push(ArrayIndex.merge(index.getStart(),q,index.getEnd()));
		            if (list.size() % 3 == 0) {
		                s.push(ArrayIndex.partition(q + 1, index.getEnd()));
		            }
		            s.push(ArrayIndex.partition(q + 1, index.getEnd()));
		            s.push(ArrayIndex.partition(index.getStart(), q));
		        } else if (index.isMerge()) {
		            merge(list, index.getStart(), index.getMidpoint(), index.getEnd());
		        } else {
		            throw new  RuntimeException("Not merge or partition");
		        }
		    }
		}

	}

	public static void merge(List list, int start, int midpoint, int end) {
		// stub		
	}
}
