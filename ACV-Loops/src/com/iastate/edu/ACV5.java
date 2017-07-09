package com.iastate.edu;

import java.util.List;
import java.util.Stack;

import library.ArrayIndex;

public class ACV5 {
	public static void sort(List list, int start, int end) {
		 ArrayIndex i = ArrayIndex.partition(start, end);
		    Stack<ArrayIndex> s = new  Stack<ArrayIndex>();
		    s.push(i);
		    while (!s.empty()) {
		        ArrayIndex index = s.pop();
		        if (index.getStart() < index.getEnd()) {
		            if (index.isPartition()) {
		                int q1 = (int) Math.floor((index.getStart() + index.getEnd()) / 2);
		                int q2 = (int) Math.floor((q1 + 1 + index.getEnd()) / 2);
		                int q3 = (int) Math.floor((q2 + 1 + index.getEnd()) / 2);
		                // calculate the length of the list mod k
		                int listModK = list.size() % 3;
		                // causes the first partition to be 3/4ths of the list instead of 1/2 of the list 
		                // IF listModK == 0.
		                int potentialQ1 = q1 + (index.getEnd() - q2);
		                q1 = (int) (q1 * Math.ceil(listModK / 3.0) + 
		                    potentialQ1 * (1 - Math.ceil(listModK / 3.0)));
		                s.push(ArrayIndex.merge(index.getStart(), q1, index.getEnd()));
		                s.push(ArrayIndex.merge(q1 + 1, q2, index.getEnd()));
		                s.push(ArrayIndex.merge(q2 + 1, q3, index.getEnd()));
		                s.push(ArrayIndex.partition(index.getStart(), q1));
		                s.push(ArrayIndex.partition(index.getStart(), q1));
		                s.push(ArrayIndex.partition(q1 + 1, q2));
		                s.push(ArrayIndex.partition(q2 + 1, q3));
		                s.push(ArrayIndex.partition(q3 + 1, index.getEnd()));
		            } else if (index.isMerge()) {
		                merge(list, index.getStart(), index.getMidpoint(), index.getEnd());
		            } else {
		                throw new  RuntimeException("Not merge or partition");
		            }
		        }
		    }

	}

	private static void merge(List list, int start, int midpoint, int end) {
		// stub
		
	}
}
