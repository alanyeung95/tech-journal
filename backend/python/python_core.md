# Python vs Java vs Node.js

## Python's approach to I/O

Python was not originally designed for asynchronous, non-blocking I/O. Its modern capabilities are an add-on, not a fundamental design principle.

Traditional approach: Python's default behavior is synchronous and blocking. For every I/O call, the interpreter waits for the operation to complete before moving on.

Asynchronous workaround: To achieve non-blocking I/O, developers must explicitly use libraries like asyncio. Even with asyncio, Python's approach is not as integrated or optimized as Node.js's event loop.

## Python's approach to cpu-bound tasks

Restricts bytecode execution: The Global Interpreter Lock (GIL) is a mutex (a type of lock) that ensures only one thread at a time can run Python code within a single process. For CPU-bound tasks, where threads are constantly running code, this means that only one CPU core will ever be utilized by your Python program. Other threads are forced to wait their turn.

Allows memory sharing: Because threads within the same process still share the same memory space, they can access the same variables and data structures.

### Vs Java

Python is Process-based. `multiprocessing` works by forking new, independent Python interpreter processes for each task. This allows true parallelism, as each process has its own GIL.

That's why python has high overhead. Creating and managing separate processes is computationally expensive. It requires the operating system to allocate distinct memory spaces for each process, and objects passed between processes must be serialized (pickled) and deserialized, which is slow.

Java is Thread-based, it uses native operating system threads within a single Java Virtual Machine (JVM) process. All threads share the same memory space, but there is no GIL to prevent them from running on multiple cores.

So Java have Low overhead. Creating and managing threads is much more lightweight than processes. Context switching between threads is faster, and they can communicate directly through shared memory without serialization.

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
