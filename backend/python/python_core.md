# requirement.txt
```
python3 -m pipreqs.pipreqs
```

# multi-processing vs muiti-threading
```
https://medium.com/towards-artificial-intelligence/the-why-when-and-how-of-using-python-multi-threading-and-multi-processing-afd1b8a8ecca
```

# OOP

Instance, Class, and Static Methods: 
https://realpython.com/instance-class-and-static-methods-demystified/

```
# Python program to demonstrate  
# use of class method and static method. 
from datetime import date 
  
class Person: 
    def __init__(self, name, age): 
        self.name = name 
        self.age = age 
      
    # a class method to create a Person object by birth year. 
    @classmethod
    def fromBirthYear(cls, name, year): 
        return cls(name, date.today().year - year) 
      
    # a static method to check if a Person is adult or not. 
    @staticmethod
    def isAdult(age): 
        return age > 18
  
person1 = Person('mayank', 21) 
person2 = Person.fromBirthYear('mayank', 1996) 
  
print person1.age 
print person2.age 
  
# print the result 
print Person.isAdult(22) 
```

## Inheritance
```
class Student(Person):
  def __init__(self, fname, lname):
    super().__init__(fname, lname)
```
