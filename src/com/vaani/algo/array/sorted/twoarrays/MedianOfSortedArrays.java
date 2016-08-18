package com.vaani.algo.array.sorted.twoarrays;

public class MedianOfSortedArrays {

    public static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length)
            return findMedianSortedArrays(b, a, 0, b.length - 1);
        else
            return findMedianSortedArrays(a, b, 0, a.length - 1);

    }

    public static double findMedianSortedArrays(int[] a, int[] b, int la, int ra) {
        if (la > ra)
            return findMedianSortedArrays(b, a, Math.max(0, (a.length + b.length) / 2 - a.length), Math.min(b.length, (a.length + b.length) / 2));

        int i = (ra + la) / 2;
        int j = (a.length + b.length) / 2 - i - 1; // i+j = meidan -1

        if (j >= 0 && a[i] < b[j])
            return findMedianSortedArrays(a, b, i + 1, ra);
        else if (j < b.length - 1 && a[i] > b[j + 1])
            return findMedianSortedArrays(a, b, la, i - 1);

        if ((a.length + b.length) % 2 == 1)
            return (double) a[i];
        else {
            if (i > 0) {
                if (j >= 0) return (double) (a[i] + Math.max(b[j], a[i - 1])) / 2;
                else return (double) (a[i] + a[i - 1]) / 2;
            } else
                return (double) (a[i] + b[j]) / 2;
        }

    }

}
