package com.phynos.framework.simulator.raw;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author by lupc
 * @date 2020-08-12 14:25
 */
public class UdpTest {


    private void start() throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] data = new byte[256];
        DatagramPacket datagramPacket = new DatagramPacket(data, 256);
        datagramSocket.send(datagramPacket);
    }

}
