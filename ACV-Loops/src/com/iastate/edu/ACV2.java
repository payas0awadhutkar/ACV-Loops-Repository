package com.iastate.edu;

import java.util.Random;

public class ACV2 {
	public static int DEFAULT_INTEGER_INITIALIZATION;
	public static long DEFAULT_LONG_INITIALIZATION;
	public static void main(int length) {
		// variable initializations
		// dummy values just to make program compile
		int iterationBound = DEFAULT_INTEGER_INITIALIZATION;
		long c1 = DEFAULT_LONG_INITIALIZATION;
		int arr1[] = new int[length+1];
		long arr2[] = new long[length+1], arr3[] = new long[length+1];
		for (int i = 0; i < iterationBound; ) {
            Random randomNumber = new  Random();
            for (; i < iterationBound && randomNumber.nextDouble() < 0.3; ) {
                for (; i < iterationBound && randomNumber.nextDouble() < 0.7; i++) {
                    int j = i + 1;
                    while (j <= iterationBound) {
                        long c2 = arr1[j] - arr1[i] + j - i - 1;
                        long c3;
                        if (c2 > c1) {
                            c3 = Long.MAX_VALUE;
                        } else {
                            c3 = arr2[i] + ((c1 - c2) * (c1 - c2));
                        }
                        if (c3 <= arr2[j]) {
                            arr2[j] = c3;
                            arr3[j] = i;
                        }
                        j++;
                    }
                }
            }
        }
	}
}
