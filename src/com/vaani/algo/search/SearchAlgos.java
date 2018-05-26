package com.vaani.algo.search;

public class SearchAlgos {

    public static int searchStringArrayWithManyEmptyStr(String[] strings, String str, int first, int last) {
        while (first <= last) {
            // Ensure there is something at the end
            while (first <= last && strings[last] == "") {
                --last;
            }

            if (last < first) {
                return -1; // this block was empty, so fail
            }
            int mid = (last + first) >> 1;

            while (strings[mid] == "") {
                ++mid; // will always find one
            }

            int r = strings[mid].compareTo(str);
            if (r == 0) return mid;

            if (r < 0) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
        }
        return -1;
    }

    public static int search(String[] strings, String str) {
        if (strings == null || str == null) return -1;
        if (str == "") {
            for (int i = 0; i < strings.length; i++) {
                if (strings[i] == "") return i;
            }
            return -1;
        }
        return searchStringArrayWithManyEmptyStr(strings, str, 0, strings.length - 1);
    }

    public static void main(String a[]) {
        String str_arr[] = new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String str = "ball";
        System.out.println(search(str_arr, str));

    }
}

