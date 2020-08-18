
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

3. Hight learning curve

# Concepts

## Stack: Function-local memory (pass-by-value)

Each time a function is called it gets it’s own section of the stack to store local variables. The function’s stack size is known at compile time. When the function is called the next area of free memory in the stack is given to the function. When the function returns, that area is available for the next function call, no other cleanup is necessary. While not free, this process is relatively cheap.

## Heap: Area for shared data (pass-by-reference)

As explained above, function local variables “disappear” after the function returns. This isn’t a problem if only non-pointer values are returned, because the returned values are copied into the stack of the calling function.

However, if pointers are returned, the pointed-at data needs to be placed somewhere outside the stack so that it will not “disappear.” This is what the heap is for.

## GRPC

use in microservices communication

1.  connect server and client on any environnent and client type
2.  use `protocal buffers`. `.proto` file is much smaller json and xml
3.  grpc plugin call `proto-gen` (any language can use) will transfer the `.proto` to grpc go file
4.  run time library will unmarshalled & and unpacked to get the data

### Compare with websocket
1.  websocket cannot resume connection
2.  GRPC cannot push data to client without client request

load blancing

## multithreading
### wait group
```
package main

import (
    "fmt"
    "sync"
    "time"
)

func worker(id int, wg *sync.WaitGroup) {
    defer wg.Done()
    fmt.Printf("Worker %d starting\n", id)
    time.Sleep(time.Second)
    fmt.Printf("Worker %d done\n", id)
}

func main() {
    var wg sync.WaitGroup
    for i := 1; i <= 50; i++ {
        wg.Add(1)
        go worker(i, &wg)
    }
    wg.Wait()
}
```
https://gobyexample.com/waitgroups

### channel
```
// _Channels_ are the pipes that connect concurrent
// goroutines. You can send values into channels from one
// goroutine and receive those values into another
// goroutine.

package main

import "fmt"

func main() {

	// Create a new channel with `make(chan val-type)`.
	// Channels are typed by the values they convey.
	messages := make(chan string)

	// _Send_ a value into a channel using the `channel <-`
	// syntax. Here we send `"ping"`  to the `messages`
	// channel we made above, from a new goroutine.
	go func() { messages <- "ping" }()

	// The `<-channel` syntax _receives_ a value from the
	// channel. Here we'll receive the `"ping"` message
	// we sent above and print it out.
	msg := <-messages
	fmt.Println(msg)
}
```

### racing condition

https://www.ardanlabs.com/blog/2013/09/detecting-race-conditions-with-go.html

```
package main

import (
	"fmt"
	"sync"
	"time"
)

var Lock sync.Mutex

func set(p *int, val int) {
    if (val >*p){ // Print and assign itself only when itself is larger
		Lock.Lock() // Add lock here to preven race condition
    	*p = val
		fmt.Println(*p)
		Lock.Unlock()
	}
}

func main() {
    a := 0
	for i := 0; i < 50; i++ {
		go set(&a, i)
	}
	time.Sleep(100 * time.Nanosecond)
}
```
