import speed_test.ISpeedTestExecutor;

public class MainLab2 {
  public static void main(String[] args) {
    var executor = new ISpeedTestExecutor();

    int executionTime = (ISpeedTestExecutor.uri.length * ISpeedTestExecutor.TIME_EXECUTION) / 1000;
    System.out.println("Estimated execution time: " + executionTime + " sec.");

    System.out.println("---------------------Test downloads started---------------------");
    executor.testDownloads();
    System.out.println("---------------------Test downloads ended---------------------");
  }
}
