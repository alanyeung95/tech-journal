## What is an object?
The object is a real-life entity that has certain properties and methods associated with it. The object is also defined as the instance of a class. An object can be declared using a new keyword.

## 81. What is an Interface?
An interface in Java is a collection of static final variables and abstract methods that define the contract or agreement for a set of linked classes. Any class that implements an interface is required to implement a specific set of methods. It specifies the behavior that a class must exhibit but not the specifics of how it should be implemented.

Feature of interface:
1. The interface can help to achieve total abstraction.
2. Allows us to use multiple inheritances in Java.
3. Any class can implement multiple interfaces even when one class can extend only one class.
4. It is also used to achieve loose coupling.

```
// Java Program to demonstrate Interface
import java.io.*;
interface Shape {
    double getArea();
    double getPerimeter();
}
class Circle implements Shape {
    private double radius;
    public Circle(double radius) { this.radius = radius; }
    public double getArea()
    {
        return Math.PI * radius * radius;
    }
    public double getPerimeter()
    {
        return 2 * Math.PI * radius;
    }
}
class GFG {
    public static void main(String[] args)
    {
        Circle circle = new Circle(5.0);
        System.out.println("Area of circle is "
                           + circle.getArea());
        System.out.println("Perimeter of circle is"
                           + circle.getPerimeter());
    }
}
```

## What is the primary benefit of Encapsulation? 
The main advantage of Encapsulation in Java is its ability to protect the internal state of an object from external modification or access. It is the is a way of hiding the implementation details of a class from outside access and only exposing a public interface that can be used to interact with the class. The main benefit is of providing a way to control and manage the state and the behavior of an object and also protecting it from modification and unauthorized access at the same time. 

```
// Java Program to demonstrate use of Encapsulation
import java.io.*;
class Person {
    private String Name;
    private int age;
    public String getName() { return Name; }
    public void setName(String Name) { this.Name = Name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
// Driver class
class GFG {
    // main function
    public static void main(String[] args)
    {
        Person p = new Person();
        p.setName("Rohan");
        p.setAge(29);
        System.out.println("Name is " + p.getName());
        System.out.println("Age is " + p.getAge());
    }
}
```

## Inheritance
```
// Java Program to show multiple Inheritance
import java.io.*;
interface Animal {
    void eat();
}
interface Mammal {
    void drink();
}
class Dog implements Animal, Mammal {
    public void eat() { System.out.println("Eating"); }
    public void drink() { System.out.println("Drinking"); }
    void bark() { System.out.println("Barking"); }
}
class GFG {
    public static void main(String[] args)
    {
        Dog d = new Dog();
        d.eat();
        d.drink();
        d.bark();
    }
}
```

## abstraction vs encapsulation 
abstraction: show only necessary part like abstract interface. Implementation is hidden subclass `SimpleMusicPlayer`

```
abstract class MusicPlayer {
    public abstract void play(String trackName);
    public abstract void pause();
    public abstract void stop();
}

class SimpleMusicPlayer extends MusicPlayer {
```

encapsulation: bundling attributes and methods into a single unit or class. Use getter and setter to restrict access to prevent the accidental modification of data.


## this
the `this` keyword is used as a reference to the current objec
```
public class Car {
    private String model;
    private int year;

    // Constructor that initializes the Car object
    public Car(String model, int year) {
        this.model = model; // this.model refers to the class field; model is the constructor's parameter
        this.year = year;   // this.year refers to the class field; year is the constructor's parameter
    }
```

# Ref
from javascript to java: https://andresclavijo.co
