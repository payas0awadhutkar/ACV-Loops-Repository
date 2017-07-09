package com.iastate.edu;

import java.util.List;
import java.util.Stack;

import library.ArrayIndex;
import library.Helper;


public class ACV1_Gabfeed3 {
	public static void sort(List list, int start, int end) {
		ArrayIndex i = ArrayIndex.partition(start, end);
		Stack<ArrayIndex> indexStack = new Stack<ArrayIndex>();
		indexStack.push(i);
		Helper conditionObj0 = new Helper(0);
		Helper conditionObj1 = new Helper(8);
		while (!indexStack.empty()) {
            ArrayIndex index = indexStack.pop();
            if (index.getStart() < index.getEnd()) {
                if (index.isPartition()) {
                    int listLen = index.getEnd() - index.getStart() + 1;
                    int q;
                    if (listLen >= 8) {
                        q = (int) Math.floor(listLen / 8) - 1 + index.getStart();
                    } else {
                        q = index.getStart();
                    }
                    indexStack.push(ArrayIndex.merge(index.getStart(), q, index.getEnd()));
                    // If the list has this characteristic, we let changingSort have a bad running time.
                    if (list.size() % 8 == conditionObj0.getValue()) {
                        changingSortHelper(indexStack, index, q);
                    }
                    indexStack.push(ArrayIndex.partition(q + 1, index.getEnd()));
                    indexStack.push(ArrayIndex.partition(index.getStart(), q));
                } else if (index.isMerge()) {
                    merge(list, index.getStart(), index.getMidpoint(), index.getEnd());
                } else {
                    throw new  RuntimeException("Not merge or partition");
                }
            }
        }
	}
	
	public static void changingSortHelper(Stack<ArrayIndex> indexStack, ArrayIndex index, int q) {
		// operations which cause sort to become expensive
		indexStack.push(ArrayIndex.partition(q + 1, index.getEnd()));
	}
	
	public static void merge(List list, int start, int q, int end) {
		// merge part of the mergesort
		// implementation details excluded
	}	
}
