import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.HashMap;


public class Sensor {

  private int lower;
  private int higher;
  private int[] output;

  Sensor(int lower, int higher, int[] buffer) {
    this.lower = lower;
    this.higher = higher;
    this.output = Arrays.stream(buffer).filter(num -> num >= lower && num <= higher).sorted().toArray();
  }

  public int getLower() {
    return lower;
  }

  public int getHigher() {
    return higher;
  }

  public int[] getOutput() {
    return output;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int num : output) {
      sb.append(num);
      sb.append(',');
    }

    sb.deleteCharAt(sb.length() - 1);

    return sb.toString();
  }

  public double calcMeanFromArray(int[] input) {
    return (double)IntStream.of(input).sum() / input.length;
  }

  public double findMean() {
    return calcMeanFromArray(output);
  }

  public double findMedian() {
    int length = output.length;

    if (length % 2 != 0) {
      return (double)output[length / 2];
    }

    int lower = length / 2 - 1;
    int upper = length / 2 + 1;
    int[] slice = Arrays.copyOfRange(output, lower, upper);

    return calcMeanFromArray(slice);
  }

  public int findMode() {
    HashMap<Integer, Integer> numFrequency = new HashMap<>();
    Integer maxFreqKey = 0;
    Integer maxFreqValue = 0;


    for ( int num : output) {
      Integer existingFrequency = numFrequency.get(num);
      Integer currentCount;

      if (existingFrequency == null) {
        numFrequency.put(num, 1);
        currentCount = 1;
      } else {
        currentCount = existingFrequency + 1;
        numFrequency.put(num, currentCount);
      }

      if (currentCount >= maxFreqValue) {
        maxFreqValue = currentCount;
        maxFreqKey = num;
      }
    }

    return maxFreqKey;
  }
}
