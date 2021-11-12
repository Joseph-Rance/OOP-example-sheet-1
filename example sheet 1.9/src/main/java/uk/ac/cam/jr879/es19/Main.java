package uk.ac.cam.jr879.es19;

public class Main {

  public static void main(String[] args) {
    FunctionalArray array = new FunctionalArray(20);
    System.out.println(array.length);
    array.set(4, 20);
    array.set(19, 40);
    System.out.println(array.get(19));
    System.out.println(array.get(4));
    System.out.println(array.get(5));  // should return 0 since this is the default value
  }
}
