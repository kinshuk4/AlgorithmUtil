package com.vaani.algo.compete.company.wallethub.java;

import java.util.Scanner;

import org.apache.logging.log4j.core.lookup.MainMapLookup;

public class TransformA {
	
public static void main(String[] args) {
    int A[] = {1 ,2, 3 ,4, 5};
    ///int A[] = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
    ///int A[] = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
 
    Scanner s = new Scanner(System.in);
    int N = s.nextInt();
    s.nextLine();
	int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
        arr[i]  = Integer.parseInt(s.nextLine());
    }
    

    System.out.println("Hello World!");
    int n = A.length;
    int tot_zero = n;
 
    int min_steps = 0;
 
    while (tot_zero != 0) {
 
        boolean is_divided = false;
 
        for (int i = 0 ; i < n ; i++) {
            if (A[i] == 0) {
                continue;
            }
            else if( (A[i] + 1) == 0) {
                is_divided = true;
                A[i] = A[i] + 1;
            }
            else if (A[i] == 1) {
                A[i] = 0;
                min_steps++;
                tot_zero--;
            }
            else if (A[i] == 1) {
                is_divided = true;
                A[i] = (A[i] - 1);
                if (A[i] == 0) {
                    tot_zero--;
                }
                min_steps++;
            }
        }
        if (is_divided == true) {
            min_steps++;
        }
    }
    System.out.println(min_steps);
 
    
}

}
