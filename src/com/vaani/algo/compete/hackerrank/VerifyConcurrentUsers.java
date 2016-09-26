package com.vaani.algo.compete.hackerrank;

import java.util.*;

/**
 * Created by andersonkmi on 8/28/16.
 */
public class VerifyConcurrentUsers {
    public int verify(List<LoggedUser> users) {
        Map<Integer, Integer> loggedHours = new HashMap<>();

        for(LoggedUser user : users) {
            for(int start = user.getLogin(); start < user.getLogout(); start++) {
                if(loggedHours.containsKey(start)) {
                    Integer counter = loggedHours.get(start);
                    counter++;
                    loggedHours.put(start, counter);
                } else {
                    loggedHours.put(start, 1);
                }
            }
        }

        int max = 0;
        Set<Integer> hours = loggedHours.keySet();
        for(Integer hour : hours) {
            if(loggedHours.get(hour) > max) {
                max = loggedHours.get(hour);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LoggedUser userA = new LoggedUser();
        userA.setName("A");
        userA.setLogin(1);
        userA.setLogout(3);

        LoggedUser userB = new LoggedUser();
        userB.setName("B");
        userB.setLogin(3);
        userB.setLogout(4);

        LoggedUser userC = new LoggedUser();
        userC.setName("C");
        userC.setLogin(1);
        userC.setLogout(2);

        LoggedUser userD = new LoggedUser();
        userD.setName("D");
        userD.setLogin(2);
        userD.setLogout(3);

        LoggedUser userE = new LoggedUser();
        userE.setName("E");
        userE.setLogin(1);
        userE.setLogout(3);

        LoggedUser userF = new LoggedUser();
        userF.setName("E");
        userF.setLogin(1);
        userF.setLogout(3);


        List<LoggedUser> users = new LinkedList<>();
        users.add(userA);
        users.add(userB);
        users.add(userC);
        users.add(userD);
        users.add(userE);
        users.add(userF);


        VerifyConcurrentUsers service = new VerifyConcurrentUsers();
        System.out.println(service.verify(users));

    }
}
