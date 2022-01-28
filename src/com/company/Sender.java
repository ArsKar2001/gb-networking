package com.company;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Sender implements Runnable {
    private final DataOutputStream out;

    private Sender(DataOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String message = sc.nextLine();
                out.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Thread creatOutDaemon(DataOutputStream out) {
        Thread sender = new Thread(new Sender(out));
        sender.setDaemon(true);
        return sender;
    }
}
