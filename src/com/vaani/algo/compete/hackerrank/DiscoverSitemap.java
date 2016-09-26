package com.vaani.algo.compete.hackerrank;

import java.util.*;

/**
 * Created by andersonkmi on 8/20/2016.
 */
public class DiscoverSitemap {
    public Map<String, List<String>> discover(List<LogEntry> logEntries) {
        Map<String, List<String>> visitedPagesByUser = new HashMap<>();
        for(LogEntry entry : logEntries) {
            if(visitedPagesByUser.containsKey(entry.getUser())) {
                List<String> pages = visitedPagesByUser.get(entry.getUser());
                pages.add(entry.getPage());
            } else {
                List<String> pages = new LinkedList<>();
                pages.add(entry.getPage());
                visitedPagesByUser.put(entry.getUser(), pages);
            }
        }

        Map<String, List<String>> sitemap = new HashMap<>();
        Set<String> users = visitedPagesByUser.keySet();
        for(String user : users) {
            List<String> path = visitedPagesByUser.get(user);

            if(path.size() == 1) {
                sitemap.put(user, new LinkedList<>());
            } else if(path.size() > 1) {
                int position = 0;
                while(position < path.size() - 1) {
                    String current = path.get(position);
                    String next = path.get(position + 1);
                    if(sitemap.containsKey(current)) {
                        List<String> pages = sitemap.get(current);
                        pages.add(next);
                    } else {
                        List<String> pages = new ArrayList<>();
                        pages.add(next);
                        sitemap.put(current, pages);
                    }
                    position++;
                }
            }
        }

        return sitemap;
    }

    public static void main(String[] args) {
        List<LogEntry> logs = new ArrayList<>();
        LogEntry log1 = new LogEntry();
        log1.setUser("A");
        log1.setPage("1");

        LogEntry log2 = new LogEntry();
        log2.setUser("B");
        log2.setPage("5");

        LogEntry log3 = new LogEntry();
        log3.setUser("A");
        log3.setPage("2");

        LogEntry log4 = new LogEntry();
        log4.setUser("A");
        log4.setPage("1");

        LogEntry log5 = new LogEntry();
        log5.setUser("B");
        log5.setPage("2");

        LogEntry log6 = new LogEntry();
        log6.setUser("C");
        log6.setPage("7");

        LogEntry log7 = new LogEntry();
        log7.setUser("C");
        log7.setPage("3");

        LogEntry log8 = new LogEntry();
        log8.setUser("A");
        log8.setPage("3");

        LogEntry log9 = new LogEntry();
        log9.setUser("C");
        log9.setPage("1");

        logs.add(log1);
        logs.add(log2);
        logs.add(log3);
        logs.add(log4);
        logs.add(log5);
        logs.add(log6);
        logs.add(log7);
        logs.add(log8);
        logs.add(log9);

        DiscoverSitemap service = new DiscoverSitemap();
        Map<String, List<String>> sitemap = service.discover(logs);
        int size = sitemap.size();
        System.out.println(size);
    }
}
