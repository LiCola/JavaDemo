package io;

import java.io.Serializable;

/**
 * Created by LiCola on 2018/3/1.
 */
public class TargetClass implements Serializable{
  private String name;
  private int level;

  public TargetClass(String name, int level) {
    this.name = name;
    this.level = level;
  }

  @Override
  public String toString() {
    return "TargetClass{" +
        "name='" + name + '\'' +
        ", level=" + level +
        '}';
  }
}
