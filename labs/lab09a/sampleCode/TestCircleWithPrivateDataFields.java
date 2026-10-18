public class TestCircleWithPrivateDataFields {
  /** Main method */
  public static void main(String[] args) {
    // Create a Circle with radius 5.0
    CircleWithPrivateDataFields myCircle = 
      new CircleWithPrivateDataFields(5.0);
    System.out.println("radius="
      + myCircle.getRadius() 
      + " Area=" + myCircle.getArea());

    // Increase myCircle's radius by 10%
    myCircle.setRadius(myCircle.getRadius() * 1.1);
    System.out.println("radius="
      + myCircle.getRadius() 
      + " Area=" + myCircle.getArea());
  }
}
