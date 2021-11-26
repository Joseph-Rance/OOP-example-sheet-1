package uk.ac.cam.jr879.es351;

import java.util.Arrays;
import java.util.TreeMap;

public class Main {

  public static void main(String[] args) {
    TreeMap<String, Float> map = new TreeMap<String, Float>();
    map.put("A", 75f);
    map.put("C", 99f);
    map.put("B", 34f);

    Students students = new Students(map);
    System.out.println(Arrays.toString(students.getNames()));
    System.out.println(Arrays.toString(students.topPercent(70)));
    System.out.println(students.medianMark());
  }
}