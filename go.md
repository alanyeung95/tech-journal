
# Golang

pass by value in default

The Go runtime has a model where multiple Go routines are mapped onto multiple threads in an automatic fashion. No Go routine is bound to a certain thread, the scheduler may (and will) schedule Go routines to the next available thread.

## Advantage

1. Coding sytle easy to maintain
   1. starting line of if brackets
   2. force removement of unsed code/import lib
2. Lower barrier of using concurrency or even multi-processors (compare with other language)
   1. goroutines & channel
   2. multi-processors by using `runtime.GOMAXPROCS`
3. Lower cost in concurrency
   1. Goroutines are lightweight: their initial size is only 4 KB, compared to operating system threads, which are initially 1 MB.
   2. The Go environment allows developers to run thousands of goroutines simultaneously without using too much RAM.
4. Combine good things in C/C++ and java
   1. Golang has a garbage collector (compare with C++)
   2. Has pointer to allocate memory in a better way (even we can use `unsafe` pkg to do pointer arithmetic)
5. easy to make microservices due to the tiny memory footprint

## Disadvantage

1. Don't have ternary operator

2. OOP features
   1. Support polymorphism https://play.golang.org/p/6Ip9scm4c3
   2. Not support inheritance (no class to extend) unless we implement it with special way

# Concepts

## Stack: Function-local memory (pass-by-value)

Each time a function is called it gets it’s own section of the stack to store local variables. The function’s stack size is known at compile time. When the function is called the next area of free memory in the stack is given to the function. When the function returns, that area is available for the next function call, no other cleanup is necessary. While not free, this process is relatively cheap.

## Heap: Area for shared data (pass-by-reference)

As explained above, function local variables “disappear” after the function returns. This isn’t a problem if only non-pointer values are returned, because the returned values are copied into the stack of the calling function.

However, if pointers are returned, the pointed-at data needs to be placed somewhere outside the stack so that it will not “disappear.” This is what the heap is for.
