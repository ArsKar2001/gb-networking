package com.company;

import java.io.DataInputStream;

public class Receiver implements Runnable {
    public static final String END_COMMAND = "/end";
    private final DataInputStream in;

    private Receiver(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readUTF();
                System.out.println(message);
                if (END_COMMAND.equals(message)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Thread createInThread(DataInputStream in) {
        return new Thread(new Receiver(in));
    }
}
