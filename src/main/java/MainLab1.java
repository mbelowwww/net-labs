import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class MainLab1 {
  public static void main(String[] args) throws SocketException {
    System.out.println("Получение списка IP адресов, доступных на ПК");
    printAllAvailableAddress();
    System.out.println("\n\nПолучение списка сетевых интерфейсов, доступных на ПК");
    printAllAvailableNetworkInterface();
  }

  private static void printAllAvailableNetworkInterface() throws SocketException {
    var nets = NetworkInterface.getNetworkInterfaces();

    for (NetworkInterface net : Collections.list(nets)) {
      System.out.printf("Display name: %s\n", net.getDisplayName());
      System.out.printf("Name: %s\n", net.getName());
      Enumeration<InetAddress> inetAddresses = net.getInetAddresses();
      for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        System.out.printf("InetAddress: %s\n", inetAddress);
      }
      System.out.println();
    }
  }

  public static void printAllAvailableAddress() throws SocketException {
    var nis = NetworkInterface.getNetworkInterfaces();

    while (nis.hasMoreElements()) {
      NetworkInterface ni = nis.nextElement();
      var ias = ni.getInetAddresses();
      while (ias.hasMoreElements()) {
        InetAddress ia = ias.nextElement();
        System.out.println(ia.getHostAddress());
      }
    }
  }
}
