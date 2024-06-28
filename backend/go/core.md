## go mod & go sum

## go build

## go routine

```
package main

import (
    "fmt"
    "time"
)

func f(from string) {
    for i := 0; i < 3; i++ {
        fmt.Println(from, ":", i)
    }
}

func main() {

    f("direct")

    go f("goroutine")

    go func(msg string) {
        fmt.Println(msg)
    }("going")

    time.Sleep(time.Second)
    fmt.Println("done")
}
```

```
$ go run goroutines.go
direct : 0
direct : 1
direct : 2
goroutine : 0
going
goroutine : 1
goroutine : 2
done
```

more advance example:

```
func (s *service) SaveItems(ctx context.Context, req SaveItemsRequest) ([]Item, error) {

    var wg sync.WaitGroup
    mutex := &sync.Mutex{}

    items := []Item{}

    wg.Add(len(req))
    for _, item := range req {
        go func(item Item) {
            defer wg.Done()

            savedItem, err := s.savePendingItem(ctx, item)
            if err != nil {
                mutex.Lock()
                errResp[item.ID] = err
                mutex.Unlock()
                return
            }

            mutex.Lock()
            items = append(items, savedItem)
            mutex.Unlock()
        }(item)
    }

    wg.Wait()

    if len(errResp) == 0 {
        return items, nil
    }

    return items
}
```

## wait group

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

## channel

```
package main

import (
	"fmt"
	"time"
)

func indexContent(contents []string) {
	ch := make(chan int)
	for i, content := range contents {
		go func(c string) {
			time.Sleep(time.Second)
			ch <- i
		}(content)
	}

	for range contents {
		done := <-ch // Wait to complete
		fmt.Println(done)
	}
	fmt.Println("All content indexed.")
}

func main() {
	contents := []string{
		"Article about Go",
		"Introduction to Goroutines",
		"Understanding Channels in Go",
		"Concurrency with Go Routines",
		"Advanced Go Programming",
	}

	indexContent(contents)
}
```

```
3
2
4
0
1
All content indexed.
```

## racing condition

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
