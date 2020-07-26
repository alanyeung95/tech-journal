# For vs foreach

## forEach keeps the variable’s scope to the block
The good thing about forEach is that the callback function within it allows you to keep that variable within the forEach’s scope. If you’ve assigned a variable outside and re-use it within the forEach, the outside variable retains its value.
```
const num = 4;
const arr = [0, 1, 2];

arr.forEach(num => {
  console.log(num);
});

// Result:
// 0
// 1
// 2
console.log(num);

// Result:
// 4
```

## You can break out of a for loop earlier
One main reason why I would use the for loop is if I needed to break out of a loop early. If you wanted to only return a certain food in your array, you could use an if statement to check if your criteria matches, and then break out from the loop. The forEach method would iterate over each food, which could lead to performance issues.

Here’s an example of what the for loop would look like if you broke out of it early:
```
for (let i = 0; i < foodArray.length; i++) {
  if (foodArray[i].name === 'Pizza') {
    console.log('I LOVE PIZZA');
    break;
  }
}
```

# Closure
A closure is a function having access to the parent scope, even after the parent function has closed.

Example:
```
var sellTicket = (function(){
  var counter = 0;
  return function(buyer){
    counter +=1;
    console.log(`(Total Sold: ${counter}) Buyer: ${buyer}`);
  }
})();

sellTicket('OneJar');            // (Total Sold: 1) Buyer: OneJar
sellTicket('Tony Stark');        // (Total Sold: 2) Buyer: Tony Stark
sellTicket('Steven Rogers');     // (Total Sold: 3) Buyer: Steven Rogers
```

Reference:
1. https://ithelp.ithome.com.tw/articles/10209465
2. https://stackoverflow.com/questions/36636/what-is-a-closure

# Generator
a generator is a function that can stop midway and then continue from where it stopped.

```
function * generatorFunction() { // Line 1
  console.log('This will be executed first.');
  yield 'Hello, ';   // Line 2
  console.log('I will be printed after the pause');  
  yield 'World!';
}
const generatorObject = generatorFunction(); // Line 3
console.log(generatorObject.next().value); // Line 4
console.log(generatorObject.next().value); // Line 5
console.log(generatorObject.next().value); // Line 6
// This will be executed first.
// Hello, 
// I will be printed after the pause
// World!
// undefined
```
