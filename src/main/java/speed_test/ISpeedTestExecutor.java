package speed_test;

import fr.bmartel.speedtest.SpeedTestSocket;

import static java.lang.Thread.sleep;

public class ISpeedTestExecutor {
  public static final String[] uri = {
      "http://ipv4.ikoula.testdebit.info/1M.iso",
      "http://ipv4.ikoula.testdebit.info/5M.iso",
      "http://ipv4.scaleway.testdebit.info/100M.iso",
      "http://ipv4.scaleway.testdebit.info/10G.iso",
      "https://images.wallpaperscraft.ru/image/palma_derevo_nebo_201126_3840x2160.jpg"
  };
  public static final int TIME_EXECUTION = 15_000;

  public void testDownloads() {
    for (String uri : uri) {
      execute(uri);
    }
  }

  void execute(String uri) {
    var speedTestSocket = new SpeedTestSocket();
    speedTestSocket.addSpeedTestListener(new ISpeedTestListenerImpl());

    System.out.println("\nStarting test URL : " + uri);
    speedTestSocket.startFixedDownload(uri, TIME_EXECUTION - 3_000);

    try {
      sleep(TIME_EXECUTION);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
