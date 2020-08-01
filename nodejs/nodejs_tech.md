## Nodejs playground
https://www.katacoda.com/courses/nodejs/playground

## Buffer.allocUnsafe() 
The underlying memory for Buffer instances created in this way is not initialized. 
The contents of the newly created Buffer are unknown and may contain `sensitive data`. 
Use Buffer.alloc() instead to initialize Buffer instances with zeroes.

## nodejs core module
https://flaviocopes.com/node-core-modules/

## fs module
`createReadStream` method can be used to read the content of a file without buffering it in memory

The buffer will get involved with any following methods: `read, readFile, readFileSync`

## Console
```
console.time('100-elements');
for (let i = 0; i < 100; i++) {}
console.timeEnd('100-elements');
// prints 100-elements: 225.438ms
```
### console.trace()
```
Trace
    at /root/app.js:9:11
    at ChildProcess.exithandler (child_process.js:294:7)
    at ChildProcess.emit (events.js:310:20)
    at maybeClose (internal/child_process.js:1021:16)
    at Socket.<anonymous> (internal/child_process.js:443:11)
    at Socket.emit (events.js:310:20)
    at Pipe.<anonymous> (net.js:672:12)
```    
## command to silence all process warnings
```
node --no-warings
```

## REPL REPL-Read-Eval-Print-Loop (aka Node shell)
It is the Node.js interactive shell. a quick and easy way to test simple Node.js/JavaScript code.

Reference: https://nodejs.org/en/knowledge/REPL/how-to-use-nodejs-repl/

## child_process
Each process has its own memory, with their own V8 instances. 
Because of the additional resource allocations required, spawning a large number of child Node.js processes is not recommended.

```
const { spawn } = require('child_process');
const pwd = spawn('pwd');

// on is not a callback function, it is a listener of stdout obj
pwd.stdout.on('data', (data) => {
    console.log(`stdout: ${data}`);
});

pwd.stderr.on('data', (data) => {
    console.error(`stderr: ${data}`);
});

pwd.on('close', (code) => {
    console.log(`child process exit code: ${code}`);
});
```
Another example:
```
const { exec } = require('child_process');
exec('ps -ef', (error, stdout, stderr) => {
  if (error) {
    
    .error(`exec error: ${error}`);
    return;
  }
  console.log(`stdout: ${stdout}`);
  console.error(`stderr: ${stderr}`);
});
```

Reference: https://ithelp.ithome.com.tw/articles/10232335

## EventEmitter
```
//event.js 文件
var EventEmitter = require('events').EventEmitter; 
var event = new EventEmitter(); 
event.on('some_event', function() { 
    console.log('some_event 事件触发'); 
}); 
setTimeout(function() { 
    event.emit('some_event'); 
}, 1000); 
```

## stdin
```
process.stdin.setEncoding('utf-8');

process.stdin.on('readable', () => {
    const input = process.stdin.read();
    if (input !== null) {
        console.log(`我監聽到東西啦 -> ${input}`)
    }
});
```
Reference: https://ithelp.ithome.com.tw/articles/10231218

## Global objects: __filename
```
console.log(__filename);
// Prints: /Users/mjr/example.js
console.log(__dirname);
// Prints: /Users/mjr
```
