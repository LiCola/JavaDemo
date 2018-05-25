package thread; import com.licola.llogger.LLogger;

/**
 * Created by LiCola on 2017/11/24. 同一个大型任务，被不同的子线程分别执行 当线程数对数增长，任务时间对半下降，
 * 但是超过CPU核心数时，效果就下降，当超过1000时线程数反而带来性能的损耗（I7-4核）
 */
public class ThreadSpawningPerformanceTest {

  static long test(final int threadCount, final int workAmountPerThread)
      throws InterruptedException {
    Thread[] tt = new Thread[threadCount];
    final int[] aa = new int[tt.length];
    LLogger.d("Creating " + tt.length + " Thread objects... ");
    long t0 = System.nanoTime(), t00 = t0;
    for (int i = 0; i < tt.length; i++) {
      final int j = i;
      tt[i] = new Thread() {
        public void run() {
          int k = j;
          for (int l = 0; l < workAmountPerThread; l++) {
            k += k * k + l;
          }
          aa[j] = k;
        }
      };
    }
    LLogger.d(" Done in " + (System.nanoTime() - t0) * 1E-6 + " ms.");
    LLogger.d("Starting " + tt.length + " threads with " + workAmountPerThread
        + " steps of work per thread... ");
    t0 = System.nanoTime();
    for (int i = 0; i < tt.length; i++) {
      tt[i].start();
    }
    LLogger.d(" Done in " + (System.nanoTime() - t0) * 1E-6 + " ms.");
    LLogger.d("Joining " + tt.length + " threads... ");
    t0 = System.nanoTime();
    for (int i = 0; i < tt.length; i++) {
      tt[i].join();
    }
    LLogger.d(" Done in " + (System.nanoTime() - t0) * 1E-6 + " ms.");
    long totalTime = System.nanoTime() - t00;
    int checkSum = 0; //display checksum in order to give the JVM no chance to optimize out the contents of the run() method and possibly even thread creation
    for (int a : aa) {
      checkSum += a;
    }
    LLogger.d("Checksum: " + checkSum);
    LLogger.d("Total time: " + totalTime * 1E-6 + " ms");
    LLogger.d();
    return totalTime;
  }

  public static void main(String[] kr) throws InterruptedException {
    int workAmount = 100000000;
    int[] threadCount = new int[]{1, 2, 4, 8, 10, 100, 1000, 10000, 100000};
    int trialCount = 8;
    long[][] time = new long[threadCount.length][trialCount];
    for (int j = 0; j < trialCount; j++) {
      for (int i = 0; i < threadCount.length; i++) {
        time[i][j] = test(threadCount[i], workAmount / threadCount[i]);
      }
    }
    LLogger.d("core available number:"+Runtime.getRuntime().availableProcessors());
    LLogger.d("Number of threads ");
    for (long t : threadCount) {
      LLogger.d("\t" + t);
    }
    LLogger.d();
    for (int j = 0; j < trialCount; j++) {
      LLogger.d((j + 1) + ". trial time (ms)");
      for (int i = 0; i < threadCount.length; i++) {
        LLogger.d("\t" + Math.round(time[i][j] * 1E-6));
      }
      LLogger.d();
    }
  }
}
