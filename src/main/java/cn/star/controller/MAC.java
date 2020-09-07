package cn.star.controller;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

//获取本机所有IP地址
public class MAC {
    public static Map<String, String> getLocalIPList() {
        List<String> ipList = new ArrayList<String>();
        Map<String, String> ipMap = new HashMap<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipMap.put(inetAddress.getHostName(), ip);
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipMap;
    }

    public static void main(String[] args) {
        Map<String, String> ipMap = getLocalIPList();
        for (String key : ipMap.keySet()) {
            System.out.println(key + ":" + ipMap.get(key));
        }

    }
}
