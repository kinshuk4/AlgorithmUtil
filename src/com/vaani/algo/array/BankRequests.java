package com.vaani.algo.array;

import java.util.Arrays;

public class BankRequests {
    static int[] bankRequests(int[] accounts, String[] requests) {
        for(int i = 0; i< requests.length; i++){
            String request = requests[i];
            boolean status = true;
            String[] rb = request.split("\\s+");;//requestBreakage
            int source = Integer.valueOf(rb[1]);
            int sum = Integer.valueOf(rb[rb.length-1]);
            System.out.println(Arrays.toString(rb));
            switch(rb[0]){
                case "transfer":
                    status = transfer(source, Integer.valueOf(rb[2]), sum, accounts);
                    break;
                case "deposit":
                    status = deposit(source, sum,  accounts);
                    break;
                case "withdraw":
                    status = withdraw(source, sum, accounts);
                    break;
                default:
                    status = false;
            }
            if(!status){
                return new int[]{-(i+1)};
            }
        }
        return accounts;
    }


    static boolean transfer(int i, int j, int sum, int[] accounts){
        return withdraw(i, sum, accounts) && deposit(j, sum, accounts);

    }

    static boolean deposit(int i, int sum, int[] accounts){
        int idx = i - 1;
        if(idx < accounts.length){
            accounts[idx] += sum;
            return true;
        }else{
            return false;
        }
    }

    static boolean withdraw(int i, int sum, int[] accounts){
        int idx = i - 1;
        System.out.println(idx);
        if(idx < accounts.length && sum <= accounts[idx]){
            accounts[idx] -= sum;
            return true;
        } else{
            return false;
        }
    }

    public static void main(String[] args) {
        String[] requests = {
                "withdraw 2 10",
                "transfer 5 1 20",
                "deposit 5 20",
                "transfer 3 4 15"
        };
        int[] accounts = {
                10, 100, 20, 50, 30
        };

        System.out.println(Arrays.toString(bankRequests(accounts, requests)));
    }
}
