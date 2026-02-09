## Tutorial

https://ithelp.ithome.com.tw/m/articles/10349607

## Heap

heap size: The total amount of memory currently allocated by the JVM for the heap.

used heap: The portion of the heap that is actually occupied by live objects.

### setting max heap size

```
java -Xmx1024m MyApp
```

## Usage

```
"C:\Program Files\VisualVM\bin\visualvm.exe" --jdkhome "C:\Program Files\Java\jdk-21"
```

### Check memory usage using heap dump

<img src="https://yunmingzhang.wordpress.com/wp-content/uploads/2014/02/testdump1-arraysize-1000.png"/>

hint: Use retained size to check memory leak
