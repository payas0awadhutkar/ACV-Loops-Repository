package com.iastate.edu;

import java.awt.image.BufferedImage;

import library.ARGB;

public class ACV8 {
	
	public static void filter(BufferedImage image) {
		for (int i = 0; i < image.getWidth(); i++) {
	        for (int j = 0; j < image.getHeight(); j++) {
	            int rgb00 = image.getRGB(bound(0, image.getWidth(), i-1), bound(0, image.getHeight(), j-1));
	            int rgb01 = image.getRGB(bound(0, image.getWidth(), i-1), bound(0, image.getHeight(), j));
	            int rgb02 = image.getRGB(bound(0, image.getWidth(), i-1), bound(0, image.getHeight(), j+1));
	            int rgb11 = image.getRGB(i, j);
	            int rgb20 = image.getRGB(bound(0, image.getWidth(), i+1), bound(0, image.getHeight(), j-1));
	            int rgb21 = image.getRGB(bound(0, image.getWidth(), i+1), bound(0, image.getHeight(), j));
	            int rgb22 = image.getRGB(bound(0, image.getWidth(), i+1), bound(0, image.getHeight(), j+1));

	            int m = Mathematics.intensify(ARGB.rawA(rgb11), ARGB.rawR(rgb00), ARGB.rawG(rgb11), ARGB.rawB(rgb22));
	            int n = Mathematics.intensify(ARGB.rawA(rgb11), ARGB.rawR(rgb01), ARGB.rawG(rgb11), ARGB.rawB(rgb21));
	            int o = Mathematics.intensify(ARGB.rawA(rgb11), ARGB.rawR(rgb02), ARGB.rawG(rgb11), ARGB.rawB(rgb20));

	            long avg = (long) m + m + m + n + n + o + o + o;

	            image.setRGB(i, j, ((int)avg>>3) | 0xFF000000);
	        }
	    }
	}
	
	private static int bound(int min, int max, int i) {
        if (i >= min) {
            if (i < max) return i;
            return max - 1;
        }
        return 0;
	}

	public static class Mathematics {
		private static final double Z255_inv = 1.0 / 255.0;
	    private static final double Z255_e_revert = 255.0 / 2.71828182847;

	    private static final int[] accuracy = new int[257];

	    static {
	        for (int n = 0; n <= 256; n++) {
	            double v = 1.0 / Math.tan(Math.abs(30 - n) * Z255_inv + 0.001);
	            accuracy[n] = 4 + (int) v;
	        }
	    }

	    private static double lgamma(double x) {
	        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
	        double ser = 1.0
	                + 76.18009173000 / x
	                - 86.50532033000 / (x + 1)
	                + 24.01409282200 / (x + 2)
	                - 01.23173951600 / (x + 3)
	                + 00.00120858003 / (x + 4)
	                - 00.00000536382 / (x + 5);
	        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
	    }

	    private static double factorial(double x) {
	        return Math.exp(lgamma(x + 1));
	    }

	    private static double exp(int x, int n) {
	        double exp_n = 0;
	        for (int i = 0; i < n; i++) {
	            double aDouble = Math.pow(x * Z255_inv, n);
	            double aDouble1 = 1.0 / factorial(n);
	            exp_n += aDouble * aDouble1;
	        }
	        return exp_n;
	    }

	    public static int intensify(int a, int r, int g, int b) {
	        int acc = accuracy[((r + g + b) % 255)];
	        return ARGB.toARGB(
	                a,
	                (int) (Z255_e_revert * exp(r, acc)),
	                (int) (Z255_e_revert * exp(g, acc)),
	                (int) (Z255_e_revert * exp(b, acc))
	        );
	    }
	}
}
