package compiler;

/**
 * Created by LiCola on 2018/7/12.
 */
public class SynchronizedCompilerTest {


  public static final void main(String[] args) {
    SynchronizedCompilerTest compilerTest = new SynchronizedCompilerTest();
    compilerTest.instanceLock();
    compilerTest.instanceLockInner();

    SynchronizedCompilerTest.classLock();
    SynchronizedCompilerTest.classLockInner();
  }

  public synchronized void instanceLock() {
  }

  public void instanceLockInner() {
    synchronized (this) {
    }
  }

  public static synchronized void classLock() {
  }

  public static void classLockInner() {
    synchronized (SynchronizedCompilerTest.class) {
    }
  }
}
