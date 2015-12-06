package net.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by pc9507 on 2015/12/6.
 *
 * 利用InetAddress来获取指定域名的所有IP
 * 以及本地IP的方法
 */
public class InetAddressTest {
    public static void main(String[] args) {
        String host = "www.sina.com.cn";
        try {
            InetAddress[] addresses = InetAddress.getAllByName(host);

            for (InetAddress address:addresses) {
                System.out.println(address);
            }
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println(localhost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
