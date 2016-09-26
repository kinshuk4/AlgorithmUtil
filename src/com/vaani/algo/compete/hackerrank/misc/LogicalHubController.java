/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 02/09/2015
 * 
 * Hacker rank
 * Problem: Logical Hub Controller
 * Status: accepted
 * 
 * In this challenge, each logical hub should forward a copy of each packet that arrives 
 * from one of the VM’s to the other VMs on the same logical hub. For example, the logical 
 * hub B should take each packet that arrives from the VM on Host0/Port1, and forward a 
 * copy of that packet to the other ports on logical hub B: Host1/Port1 and Host2/Port0. 
 * To move packets from one host to another host, we forward the packets between hosts.
 * We will implement each logical hub by programming a software switch on each of the hosts. 
 * The software switch can receive a packet either from a local VM port or from a tunnel. 
 * The software switch can then send that packet to a local VM port, or into a tunnel. 
 * When sending a packet from one host to another host, the sending switch attaches an 
 * identifier to the packet that the receiving switch on the recipient host can read.
 * Your task is to program the controller to output the configuration for all of the 
 * software switches in the system. Each line of output can be one of three commands:
 *
 * PORT_TO_TUNNEL host src_port dst_host hub_id
 * PORT_TO_PORT host src_port dst_port
 * TUNNEL_TO_PORT host hub_id dst_port
 * 
 * PORT_TO_TUNNEL instructs the software switch on host host to tunnel a packet arriving 
 * from local port src_port to another dst_host where another port lives. The switch 
 * labels the packet with hub_id so that the receiving host knows which logical hub the 
 * packet belongs to.
 * 
 * PORT_TO_PORT instructs the software switch on host host to forward a packet arriving 
 * from local port src_port to the local port dst_porton the same host. This is useful 
 * when a logical hub has multiple ports on one host.
 * 
 * TUNNEL_TO_PORT instructs the software switch on host to forward a packet arriving via 
 * a tunnel with logical hub hub_id to the local port dst_port.
 * 
 * Duplicating packets: for PORT_TO_TUNNEL, PORT_TO_PORT, and TUNNEL_TO_PORT commands, 
 * the first two arguments describe where the input packet is coming from and the remaining 
 * arguments describe where to send the packet. The controller may output multiple commands 
 * with the same two input arguments to duplicate the packet to more than one destination.
 * 
 * Input Format
 * 
 * Each line of input must be in the following format. Each id should be a combination of 
 * letters and numbers with no special symbols. [hub_id] is a space separated list of hub_ids.
 * host_id [hub_id]
 * 
 * Output Format
 * 
 * The output format uses the three line formats from the “Wiring” section. Here is the expected 
 * output for the sample above. The output lines may be in any order.
 * 
 * Sample Input
 * 
HOST0 A B A
HOST1 A B C
HOST2 B C
 * 
 * Sample Output
 * 
PORT_TO_PORT HOST0 0 2
PORT_TO_TUNNEL HOST0 0 HOST1 A
TUNNEL_TO_PORT HOST0 A 0
PORT_TO_TUNNEL HOST0 1 HOST1 B
PORT_TO_TUNNEL HOST0 1 HOST2 B
TUNNEL_TO_PORT HOST0 B 1
PORT_TO_PORT HOST0 2 0
PORT_TO_TUNNEL HOST0 2 HOST1 A
TUNNEL_TO_PORT HOST0 A 2
PORT_TO_TUNNEL HOST1 0 HOST0 A
TUNNEL_TO_PORT HOST1 A 0
PORT_TO_TUNNEL HOST1 1 HOST0 B
PORT_TO_TUNNEL HOST1 1 HOST2 B
TUNNEL_TO_PORT HOST1 B 1
PORT_TO_TUNNEL HOST1 2 HOST2 C
TUNNEL_TO_PORT HOST1 C 2
PORT_TO_TUNNEL HOST2 0 HOST0 B
PORT_TO_TUNNEL HOST2 0 HOST1 B
TUNNEL_TO_PORT HOST2 B 0
PORT_TO_TUNNEL HOST2 1 HOST1 C
TUNNEL_TO_PORT HOST2 C 1
 * 
 */

package com.vaani.algo.compete.hackerrank.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Stream;

public class LogicalHubController {

    static Map<Character, Map<String, List<Integer>>> tenants = new HashMap<>();

    static void portToPort(String host, List<Integer> ports) {
        String pref = "PORT_TO_PORT " + host + " ";
        ports.stream().forEach(src -> 
            ports.stream().filter(dst -> dst != src).forEach(dst -> 
                System.out.println(pref + src + " " + dst)));
    }

    static void portToTunnelAndTunnelToPort(char tenant, String host, 
            int port, Stream<Entry<String, List<Integer>>> vms) 
    {
        String prefToTunnel = "PORT_TO_TUNNEL " + host + " " + port + " ";
        vms.forEach(e -> {
            System.out.println(prefToTunnel + e.getKey() + " " + tenant);
        });
        System.out.println("TUNNEL_TO_PORT " + host + " " + tenant + " " + port);
    }

    static void portToTunnel(char tenant, Map<String, List<Integer>> hosts) {
        hosts.forEach((h, ports) ->
            ports.stream().forEach(p -> 
                portToTunnelAndTunnelToPort(tenant, h, p, 
                        hosts.entrySet().stream().filter(e -> e.getKey() != h))));
        
    }
    
    static void printRules(char tenant,  Map<String, List<Integer>> hosts) {
        hosts.forEach((host, ports) -> portToPort(host, ports));
        if (hosts.size() > 1)
            portToTunnel(tenant, hosts);
    }
    
    static void printRules() {
        tenants.forEach(LogicalHubController::printRules);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null?
                new Scanner(System.in): new Scanner(new File("/home/lynx/tmp/ininin"));
        while (scanner.hasNextLine()) {
            String[] params = scanner.nextLine().split(" ");
            String host = params[0];
            for (int i = 1; i < params.length; ++i) {
                char t = params[i].charAt(0);
                tenants.putIfAbsent(t, new HashMap<>());
                tenants.get(t).putIfAbsent(host, new ArrayList<>());
                tenants.get(t).get(host).add(i - 1);
            }
        }
        printRules();
        scanner.close();        
    }

}
