package com.vaani.algo.compete.hackerrank;
import java.util.*;
public class RoyalNames {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String[] royalNames = new String[N];
        for(int i = 0; i < N; i++)
        {
            royalNames[i] = in.nextLine();
        }
        getSortedList(royalNames);
        for (String name: royalNames)
            System.out.println(name);
    }

    private static void getSortedList(String[] royalNames)
    {
        Arrays.sort(royalNames, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] r1 = o1.split(" ");
                String[] r2 = o2.split(" ");
                if (r1[0].compareTo(r2[0]) == 0)
                {
                    if (r1.length > 1 && r2.length > 1)
                    {
//                        return romanToInt(r1[1]).compareTo(romanToInt(r2[1]));
                        return romanToInt(r1[1]) - romanToInt(r2[1]);
                    }
                    else
                        return r1.length > r2.length?-1:1;
                }
                else
                    return r1[0].compareTo(r2[0]);
            }
        });
    }
    private static int romanToInt(String roman)
    {
        int decimal = 0;
        int lastDigit = 0;
        String romanU = roman.toUpperCase();
        for(int i = romanU.length() - 1; i >= 0; i--)
        {
            char currentD = romanU.charAt(i);
            switch (currentD)
            {
                case 'L':
                    decimal = UpdateDecimal(50, lastDigit, decimal);
                    lastDigit = 50;
                    break;
                case 'X':
                    decimal = UpdateDecimal(10, lastDigit, decimal);
                    lastDigit = 10;
                    break;
                case 'V':
                    decimal = UpdateDecimal(5, lastDigit, decimal);
                    lastDigit = 5;
                    break;
                case 'I':
                    decimal = UpdateDecimal(1, lastDigit, decimal);
                    lastDigit = 1;
                    break;
            }
        }
        return decimal;
    }

    private static int UpdateDecimal(int currentDigit, int lastDigit,int decimal)
    {
        if (lastDigit > currentDigit)
            return decimal - currentDigit;
        else
            return decimal + currentDigit;
}
}
