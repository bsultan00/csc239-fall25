// public class ClassA extends ClassB {
//     public static void main(String[] args) {
//         new ClassA();
//         System.out.println();
//     }

//     public ClassA() {
//         System.out.print("A ");
//     }

// }

// class ClassB extends ClassC {
//     public ClassB() {
//         this("B2");
//         System.out.print("B1 ");
//     }

//     public ClassB(String s) {
//         System.out.print(s + " ");
//     }
// }

// class ClassC {
//     public ClassC() {
//         System.out.print("C ");
//     }
// }

public class Apple extends Fruit {
    public static void main (String[] args) {
        Apple myApple = new Apple();
    }
}

class Fruit {
    public Fruit(String name) {
        System.out.printf("Tasty %s\n", name);
    }
}