## Regular Expressions

https://cs.lmu.edu/~ray/notes/regex/

### Examples
#### string in string

```
import re

input = "aappppllllllllee"
x = re.findall("[a]+[p]+[p]+[l]+[e]+", input)
print(x) # ['aappppllllllllee']
```