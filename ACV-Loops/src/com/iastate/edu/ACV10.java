package com.iastate.edu;

import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class ACV10 {
	
	private static final int FILE_LIMIT = 15;
	
	private static final BigInteger TOTAL_SIZE_LIMIT = BigInteger.valueOf(5 * 1024 * 1024);
	
	private static BigInteger totalSizeDecompressed;
	
	private static LinkedList<Entry> queue;
	
	class Entry {

        Path path;

        Path target;

        Entry(Path p, Path t) {
            path = p;
            target = t;
        }
	}
	
	public ACV10() {
		queue = new LinkedList<Entry>();
	}
	
	public static boolean decompress(String filePath, String outDirPath) {
		int fileCount = 0;
        totalSizeDecompressed = BigInteger.valueOf(0);
        decompress_(Paths.get(filePath), Paths.get(outDirPath));
        while (!queue.isEmpty() && queue.size() < FILE_LIMIT && withinSizeLimit()) {
            Entry next = queue.removeFirst();
            decompress_(next.path, next.target);
            fileCount++;
        }
        if (!queue.isEmpty() || !withinSizeLimit()) {
            cleanUpOutput(outDirPath);
            return false;
        }
		
		return true;
	}

	private static void decompress_(Path path, Path path2) {
		// decompression logic. Code excluded.
	}

	private static boolean withinSizeLimit() {
        boolean within = (totalSizeDecompressed.compareTo(TOTAL_SIZE_LIMIT) == -1);
        if (!within) {
            System.out.println("Exceeded total size limit!");
        }
        return within;
	}
	
	private static void cleanUpOutput(String outDirPath) {
		// cleanup method stub
	}
	
}
