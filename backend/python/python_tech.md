# Python playground

https://code.sololearn.com/cOAXyhEmN1f7#py

# library

## asyncio

async def: This keyword defines a coroutine function. A coroutine is a function that can pause its execution and be resumed later.

await: This keyword can only be used inside a coroutine function. It passes control back to the event loop, allowing other tasks to run while the current one is waiting for an I/O operation to complete.

```
import asyncio

async def say_hello():
    print("Hello ...")
    await asyncio.sleep(1)  # Simulate an I/O operation
    print("... World!")

# This starts the asyncio event loop and runs the coroutine
asyncio.run(say_hello())
```

## sys

```
#!/usr/bin/python

import sys

print 'Number of arguments:', len(sys.argv), 'arguments.'
print 'Argument List:', str(sys.argv)
```

## pandas

read file as json

```
import requests
import json
import pandas as pd

with open("config.json", "r") as jsonfile:
    config = json.load(jsonfile)

batchsize = 2
df = pd.read_csv("sample.csv"])
result = df.to_json(orient="records")
parsed = json.loads(result)
for i in range(0, len(parsed), batchsize):
    batchIndex = i+batchsize
    if batchIndex > len(parsed) :
        batchIndex = len(parsed)
    batch = parsed[i:batchIndex]

    reqBody = {
        "data": batch
    }

    response = requests.post('{}/xxxxxxx?apiKey={}'.format(config["host"],config["apiKey"]), json=reqBody)
```

# doctest

The doctest module searches for pieces of text that look like `interactive Python sessions`,
and then executes those sessions to verify that they work exactly as shown.

```
def isEven(num):
    '''Check if the pass in number is even.
    >>> isEven(0)
    True
    >>> isEven(777)
    False
    '''
    if num % 2 == 0:
        return True
    else:
        return False

if __name__ == "__main__":
    import doctest
    doctest.testmod()
```

output

```
bash-3.2$ python isEven.py -v
Trying:
    isEven(0)
Expecting:
    True
ok
Trying:
    isEven(777)
Expecting:
    False
ok
1 items had no tests:
    __main__
1 items passed all tests:
   2 tests in __main__.isEven
2 tests in 2 items.
2 passed and 0 failed.
Test passed.
```

Reference: http://technology-sea.blogspot.com/2012/02/python-doctest-test-case.html

# Enumerate

```
college_years = ['Freshman', 'Sophomore', 'Junior', 'Senior']
return list(enumerate(college_years, 2019)

# output
 [(2019, 'Freshman'), (2020, 'Sophomore'), (2021, 'Junior'), (2022, 'Senior')]
```

# defaultdict

defaultdict forces a dictionary to only accept keys that are of the types specified when you created the "defaultdict" (such as str or int).
It won't have `KeyError`

```
from collections import defaultdict
s = [('yellow', 1), ('blue', 2), ('yellow', 3), ('blue', 4), ('red', 1)]
# define the value type
d = defaultdict(list)
for k, v in s:
   # append is list method
   d[k].append(v)
print(d.items())

# output:
dict_items([('yellow', [1, 3]), ('blue', [2, 4]), ('red', [1])])
```

# map()

It applies a function to each item in an iterable and returns the value of that function.

```
def addition(n):
    return n + n

# We double all numbers using map()
numbers = (1, 2, 3, 4)
result = map(addition, numbers)
print(list(result))
# output
# [2, 4, 6, 8]
```

# zip()

```
fruits = ['apples', 'oranges', 'bananas']
quantities = [5, 3, 4]
prices = [1.50, 2.25, 0.89]

groceries = zip(fruits, quantities, prices)
print (groceries)

# Desired output
[('Apples', 5, 1.50), ('Oranges', 3, 2.25), ('Bananas', 4, 0.89)]
```

# `__init__()`

The `__init__()` method is a constructor method that is called automatically whenever a new object is created from a class. It sets the initial state of a new object.

# yield

When you call a function that contains a yield statement anywhere, you get a generator object, but no code runs. Then each time you extract an object from the generator, Python executes code in the function until it comes to a yield statement, then pauses and delivers the object. When you extract another object, Python resumes just after the yield and continues until it reaches another yield (often the same one, but one iteration later). This continues until the function runs off the end, at which point the generator is deemed exhausted.

https://stackoverflow.com/questions/231767/what-does-the-yield-keyword-do
