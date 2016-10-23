package com.vaani.algo.temp;

public class Temp1 {
	
	static int SQUARE = 0;
	static int RECT = 1;
	static int OTHER = 2 ;
	
	public static int min(int a, int b, int c, int d){
		if (a<b && a<c && a<d)
			return a;
		if (b<a && b<c && b<d)
			return b;
		if (c<b && c<a && c<d)
			return c;
		if (d<b && d<c && d<c)
			return d;
		return -1;
	}
	public static int getShape(int a, int b, int c, int d) {
	    if (0 >= min(a, b, c, d)) {
	        return OTHER;
	    }
	    else if ((a == b) && (b == c) && (c == d)) {
	        return SQUARE;
	    }
	    else if ((a == c) && (b == d)) {
	        return RECT;
	    }
	    else {
	        return OTHER;
	    }
	}


}
