package speed_test;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ISpeedTestListenerImpl implements ISpeedTestListener {
  private int oldPercent = 0;

  @Override
  public void onCompletion(SpeedTestReport report) {
    System.out.println("[COMPLETED] packet size : " + report.getTotalPacketSize()
        + " downloads time: " + ((report.getReportTime() - report.getStartTime()) / 1000000000.0) + " sec.");
  }

  @Override
  public void onError(SpeedTestError speedTestError, String errorMessage) {
    System.out.println("[ERROR NAME] : " + speedTestError.name());
    System.out.println("[ERROR MESSAGE] : " + errorMessage);
  }

  @Override
  public void onProgress(float percent, SpeedTestReport report) {
    int percentInt = (int) percent;
    if (percentInt % 2 == 0 && percentInt != oldPercent){
      oldPercent = percentInt;
      System.out.println("[PROGRESS] progress : " + percentInt + "%" + " rate in kBit/s : " + bitToKilobyte(report.getTransferRateBit()));
    }
  }

  protected BigDecimal bitToKilobyte(BigDecimal bit) {
    return bit.divide(BigDecimal.valueOf(8192), RoundingMode.FLOOR);
  }
}
