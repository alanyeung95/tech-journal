# ABSTRACTION and ENCAPSULATION

Abstraction is focused mainly on what should be done while Encapsulation is focused on how it should be done. 

## Abstraction
It is an act of representing only the essential things without including background details. (e.g. Interface). Abstraction hides complexity by giving you a more abstract picture.

## Encapsulation
Encapsulation hides implementation details, group code and data into a single entity. Accessing those info with getter and setter

Every function is an encapsulation. 

## implementation difference
- We can implement abstraction using abstract class and interfaces.	
- Whereas encapsulation can be implemented using by access modifier i.e. private, protected and public.

## Design difference
- In abstraction, implementation complexities are hidden using abstract classes and interfaces.	
- While in encapsulation, the data is hidden using methods of getters and setters.

# Aggregation, Association, Composition
## Association 
Association is a semantically weak relationship (a semantic dependency) between otherwise unrelated objects. 

An association is a “using” relationship between two or more objects in which the objects have their own lifetime and there is no owner

## Aggregation
Aggregation is a typical whole/part or parent/child relationship but it may or may not denote physical containment

Even the parent component get destroyed, child component still exist

## Composition
Composition is a specialized form of aggregation. In composition, if the parent object is destroyed, then the child objects also cease to exist. Composition is actually a strong type of aggregation and is sometimes referred to as a “death” relationship

# virtual functions
In Java, all non-static methods are by default "virtual functions." Only methods marked with the keyword final, which cannot be overridden, along with private methods, which are not inherited, are non-virtual.

# Inheritance type
https://beginnersbook.com/2013/05/java-inheritance-types/
