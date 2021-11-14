package uk.ac.cam.jr879.es19;

public class Main {

  public static void main(String[] args) {
    Vector2D vect = new Vector2D(new float[] {0,1,2,3});
    System.out.println(vect);
    vect.add(new Vector2D(new float[] {7,6,5,4}));
    System.out.println(vect);
    System.out.println(vect.scalarProd(new Vector2D(new float[] {0,1,2,3})));
    System.out.println(vect.magnitude());
    vect.normalise();
    System.out.println(vect);
  }
}
