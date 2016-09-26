/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 01/05/2016
 *
 * Amazon challange
 * Problem: Social network
 * Status: accepted
 * 
 * Problem
 * 
 * Implement function:
 * 
 * List<String> getRankedCourses(String user)
 * 
 * It accepts user name and returns list of courses that this user did not attend and sorted by how many
 * his friends from his social network attended them.
 * Social network it is user's friends and friends of his friends.
 * Provided helper functions are getAttendedCoursesForUser, getDirectFriendsForUser.
 * 
 */
package com.vaani.algo.compete.hackerrank.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Test2 {
    
    static Map<String, Integer> M = new HashMap<>(); 
    static Set<String> visited = new HashSet<>();
    static Set<String> attended = new HashSet<>();
    
    public void bfs(Queue<String> q, int d) {
        if (d == 2) return;
        Queue<String> nq = new LinkedList<>();
        for (String user: q) {
            if (visited.contains(user)) continue;
            visited.add(user);
            for (String course: getAttendedCoursesForUser(user)) {
                if (attended.contains(course)) continue;
                Integer c = M.get(course);
                c = c == null? 0: c;
                M.put(course, c + 1);
            }
            nq.addAll(getDirectFriendsForUser(user));
        }
        bfs(nq, d + 1);
    }

    public List<String> getRankedCourses(String user) {
        Queue<String> nq = new LinkedList<>();
        M.clear();visited.clear();
        visited.add(user);
        attended.addAll(getAttendedCoursesForUser(user));
        nq.addAll(getDirectFriendsForUser(user));
        bfs(nq, 0);
        ArrayList<String> res = new ArrayList<String>(M.size());
        res.addAll(M.keySet());
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1,
                    String o2) {
                return M.get(o2) - M.get(o1);
            }
        });
        return res;
    }
    
    public List<String> getDirectFriendsForUser(String user) {
        return null;
    }

    public List<String> getAttendedCoursesForUser(String user) {
        return null;
    }

}
