package com.vaani.algo.compete.hackerrank;

import java.util.*;

/**
 * Created by anderson.ito on 25/07/2016.
 */
public class VisitedPages {
    // Os logs estão no formato:
    // user,path
    // Por exemplo:
    // user1,/home
    // user1,/home/login
    public Map<String, Set<String>> findMostVisitedPages(List<String> logs) {
        Map<String, Map<String, Integer>> processedLogs = new HashMap<>();
        Map<String, Set<String>> result = new LinkedHashMap<>();

        for(String log : logs) {
            // Extracts the user and the path
            StringTokenizer logTokenizer = new StringTokenizer(log, ",");
            String user = logTokenizer.nextToken();
            String path = logTokenizer.nextToken();

            // Retrieves the map for user
            Map<String, Integer> userPaths;
            if (processedLogs.containsKey(user)) {
                userPaths = processedLogs.get(user);
            } else {
                userPaths = new HashMap<>();
                processedLogs.put(user, userPaths);
            }

            // Extracts the path parts and counts
            StringTokenizer pathTokenizer = new StringTokenizer(path.substring(1), "/");
            StringBuilder directoryAccumulator = new StringBuilder();
            while (pathTokenizer.hasMoreElements()) {
                directoryAccumulator.append("/").append(pathTokenizer.nextToken());
                if (userPaths.containsKey(directoryAccumulator.toString())) {
                    Integer pathCounter = userPaths.get(directoryAccumulator.toString());
                    pathCounter++;
                    userPaths.put(directoryAccumulator.toString(), pathCounter);
                } else {
                    userPaths.put(directoryAccumulator.toString(), 1);
                }
            }
        }

        // For each user, retrieves the most visited paths
        Set<String> users = processedLogs.keySet();
        for(String anUser : users) {
            Map<String, Integer> paths = processedLogs.get(anUser);
            Comparator<String> comparator = new ValueComparator(paths);
            TreeMap<String, Integer> modifiedMap = new TreeMap<>(comparator);
            modifiedMap.putAll(paths);
            processedLogs.put(anUser, modifiedMap);
        }

        // For each user, generates the list of paths
        for(String anUser : users) {
            Map<String, Integer> paths = processedLogs.get(anUser);
            result.put(anUser, paths.keySet());
        }

        return result;
    }
}
