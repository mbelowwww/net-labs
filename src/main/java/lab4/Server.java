package lab4;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.UUID;

public class Server implements Runnable {

  String MESSAGE_SPLITTER = ":::";
  String nameFolderForSaveFiles = "files_from_clients";

  ServerSocket serversocket;
  BufferedReader br1, br2;
  PrintWriter pr1;
  Socket socket;
  Thread t1, t2;
  String in = "", out = "";

  public Server() {
    try {
      t1 = new Thread(this);
      t2 = new Thread(this);
      serversocket = new ServerSocket(5000);
      System.out.println("Server is waiting. . . . ");
      socket = serversocket.accept();
      System.out.println("Client connected with Ip " + socket.getInetAddress().getHostAddress());
      t1.start();
      t2.start();

    } catch (Exception e) {
    }
  }

  public void run() {
    try {
      if (Thread.currentThread() == t1) {
        do {
          br1 = new BufferedReader(new InputStreamReader(System.in));
          pr1 = new PrintWriter(socket.getOutputStream(), true);
          in = br1.readLine();
          pr1.println(in);
        } while (!in.equals("END"));
      } else {
        do {
          br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          out = br2.readLine();
          System.out.println(out);
          createFileByMessageClient(out);
        } while (!out.equals("END"));
      }
    } catch (Exception e) {
    }
  }

  private void createFileByMessageClient(String message){
    String[] messages = message.split(MESSAGE_SPLITTER);

    String filePath = createPathFile(messages[0]);
    byte[] bytesFromMessage =  getFileBytesFromMessage(messages[1]);

    try {
      FileUtils.writeByteArrayToFile(new File(filePath), bytesFromMessage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String createPathFile(String fileName){
    File file = new File(nameFolderForSaveFiles);
    if (!file.exists()) {
      file.mkdir();
    }
    return nameFolderForSaveFiles + "\\" + UUID.randomUUID().toString() + "."  + fileName;
  }

  private byte[] getFileBytesFromMessage(String messageName) {
    String stringBytes = messageName
        .replace("[", "")
        .replace("]", "")
        .replace(" ", "");

    String[] aBytes = stringBytes.split(",");
    byte[] bytes = new byte[aBytes.length];

    for (int i = 0; i < aBytes.length; i++) {
      bytes[i] = (byte) Integer.valueOf(aBytes[i]).intValue();
    }

    return bytes;
  }

}
