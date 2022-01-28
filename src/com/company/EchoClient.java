package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoClient {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new EchoClient();
    }

    public EchoClient() {
        start();
        Sender.creatOutDaemon(out)
                .start();

        Thread receiver = Receiver.createInThread(in);
        receiver.start();

        try {
            while (true) {
                if (!receiver.isAlive()) {
                    out.writeUTF(Receiver.END_COMMAND);
                    closeConnection();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() throws IOException {
        if (socket != null) socket.close();
        if (in != null) in.close();
        if (out != null) out.close();
    }
}
