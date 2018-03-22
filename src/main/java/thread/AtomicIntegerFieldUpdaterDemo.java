package thread;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by LiCola on 2018/3/14.
 */
public class AtomicIntegerFieldUpdaterDemo<T> {

  private volatile int atomicInt;
  private volatile Object atomicRef;

  private static final AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterDemo> atomicFieldUpdaterInt = AtomicIntegerFieldUpdater
      .newUpdater(AtomicIntegerFieldUpdaterDemo.class, "atomicInt");
  private static final AtomicReferenceFieldUpdater<AtomicIntegerFieldUpdaterDemo, Object> atomicFieldUpdaterRef = AtomicReferenceFieldUpdater
      .newUpdater(AtomicIntegerFieldUpdaterDemo.class, Object.class, "atomicRef");

  public boolean compareAndSetInt(int expect, int update) {
    return atomicFieldUpdaterInt.compareAndSet(this, expect, update);
  }

  public int getAndIncrement() {
    return atomicFieldUpdaterInt.getAndIncrement(this);
  }

  public int getAtomicInt() {
    return atomicInt;
  }

  public boolean compareAndSetRef(T expect, T update) {
    return atomicFieldUpdaterRef.compareAndSet(this, expect, update);
  }

  public Object getAndSet(T newValue) {
    return atomicFieldUpdaterRef.getAndSet(this, newValue);
  }

  public T getAtomicRef() {
    return (T) atomicRef;
  }
}
