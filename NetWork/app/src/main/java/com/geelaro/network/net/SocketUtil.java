package com.geelaro.network.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by lee on 2017/5/5.
 */

public class SocketUtil {


    public static void tcpClient() {
        Socket socket;
        try {
            socket = new Socket("172.26.18.11", 6666);
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("Hello server");
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void udpClient() {
        DatagramSocket socket = null;

        //发送数据
        try {
            //
            socket = new DatagramSocket(8765);
            //服务端地址
            InetAddress serverAddress = InetAddress.getByName("172.26.18.11");
            //服务端端口
            int port = 5678;
            //创建一个byte[] 存发送的数据
            byte[] buf = (new String("Hello SocketServer!")).getBytes();
            //创建一个DatagramPacket用于发送数据
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddress, port);
            //发送数据
            socket.send(packet);


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** 接收udp数据 **/
        // 接收UDP广播，有的手机不支持
        byte[] revbuf = new byte[255];
        DatagramPacket revPacket = new DatagramPacket(revbuf,
                revbuf.length);
        try {
            socket.receive(revPacket);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Rev data: " + new String(revbuf, 0, revPacket.getLength()));
        System.out.println("Server: IP ‘" + revPacket.getAddress() + "’\n");

        socket.close();

    }
}
