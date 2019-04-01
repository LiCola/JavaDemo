package thread;

import com.licola.llogger.LLogger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author LiCola
 * @date 2019-04-01
 */
public class ForkJoinTest {

  public static void main(String[] args) {
    long[] array = new long[400];
    for (int i = 0; i < array.length; i++) {
      array[i] = i;
    }
    ForkJoinPool forkJoinPool = new ForkJoinPool(4);//最大并发数
    SumTask sumTask = new SumTask(array, 0, array.length);
    long startTime = System.currentTimeMillis();
    Long result = forkJoinPool.invoke(sumTask);
    long endTime = System.currentTimeMillis();
    LLogger.d("Fork/Join sum:" + result + " time:" + (endTime - startTime) + "ms");
  }

  static class SumTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 100;
    long[] array;
    int start;
    int end;


    public SumTask(long[] array, int start, int end) {
      this.array = array;
      this.start = start;
      this.end = end;
    }

    @Override
    protected Long compute() {
      if (end - start <= THRESHOLD) {
        long sum = 0;
        for (int i = start; i < end; i++) {
          sum += array[i];
        }

        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        LLogger.d(String.format("compute %d-%d=%d", start, end, sum));
        return sum;
      }

      int middle = (end + start) / 2;
      LLogger
          .d(String.format("split %d-%d ==> %d-%d,%d-%d", start, end, start, middle, middle, end));
      SumTask sumTask1 = new SumTask(this.array, start, middle);
      SumTask sumTask2 = new SumTask(this.array, middle, end);
      invokeAll(sumTask1, sumTask2);//调起该方法 执行尝试执行子任务 其中 参数的第一个任务可能会直接在当前线程执行

//      sumTask1.fork();//把当前任务加入队列 等待执行
//      sumTask2.fork();

      //以上两个子任务执行方式 会导致不同的结果 invoke调起只是1秒 直接fork就是2秒

      Long subresult1 = sumTask1.join();
      Long subresult2 = sumTask2.join();
      long result = subresult1 + subresult2;
      LLogger.d(String.format("result=%d=%d+%d", result, subresult1, subresult2));
      return result;
    }
  }
}
