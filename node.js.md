## ES6

### Arrow function
1. short syntax. remove `function` and `return` keyword
2. `this` handling. 
    1. In ES5, `this` refer to the owner of the function, so `this` inside `[a]` will become `[object Window]` since it is now out of scope
    2. in ES6, arrow functions use `lexical scoping` — `this` refers to it’s current surrounding scope and no further. 
    3. Reference: https://www.freecodecamp.org/news/learn-es6-the-dope-way-part-ii-arrow-functions-and-the-this-keyword-381ac7a32881/

[a]
```
var bunny = {
  name: 'Usagi',
  tasks: ['transform', 'eat cake', 'blow kisses'],
  showTasks: function() {
    this.tasks.forEach(function(task) {
      alert(this.name + " wants to " + task);
    });
  }
};
```

## Express
```
var express = require('express');
var app = express();
 
//  主页输出 "Hello World"
app.get('/', function (req, res) {
   console.log("主页 GET 请求");
   res.send('Hello GET');
})
 
 
//  POST 请求
app.post('/', function (req, res) {
   console.log("主页 POST 请求");
   res.send('Hello POST');
})
 
//  /del_user 页面响应
app.get('/del_user', function (req, res) {
   console.log("/del_user 响应 DELETE 请求");
   res.send('删除页面');
})
 
//  /list_user 页面 GET 请求
app.get('/list_user', function (req, res) {
   console.log("/list_user GET 请求");
   res.send('用户列表页面');
})
 
// 对页面 abcd, abxcd, ab123cd, 等响应 GET 请求
app.get('/ab*cd', function(req, res) {   
   console.log("/ab*cd GET 请求");
   res.send('正则匹配');
})
 
 
var server = app.listen(8081, function () {
 
  var host = server.address().address
  var port = server.address().port
 
  console.log("应用实例，访问地址为 http://%s:%s", host, port)
 
})
```

## Mongoose
It is MongoDB object modeling for nodejs

It encasupe the CRUD operation to MongoDB document, work between mongodb and nodejs. We don't need to remember the query syntax, we just need to remember the business logic

```
const mongoose = require('mongoose');
mongoose.connect('mongodb://localhost:27017/test', {useNewUrlParser: true, useUnifiedTopology: true});

const Cat = mongoose.model('Cat', { name: String });

const kitty = new Cat({ name: 'Zildjian' });
kitty.save().then(() => console.log('meow'));
```
## Webpack
MODULES WITH DEPENDENCIES ->  STATIC ASSETS 

first of all, browsers don't support `require` and `module.exports` (the `CommonJS` standard) in javascript (althought Node.js does support). So we need webpack to transform the code to bundle to use the feature.

ES6 does support `import` and `export`, but the support is not perfect. If we still want to use it, we can use `babel` to convert ES6 code to ES5.

### Q&A
Q: why project like react need to be built?

A: It is because the source code cannot be run on browsers. So we must webpack it.

### Static assets
Static assets are object you send to the user that the server does not change. Images are an example of static assets. Rather that create a route for each image or static asset, folders can be declared “static” or “public” in Express and routes will automatically be configured.

### Advantage
1. Dead asset elimination. This is killer, especially for CSS rules. You only build the images and CSS into your dist/ folder that your application actually needs.
2. Hot-reload
3. import loader, you can import image or css in javascript file, with the aid of webpack import loader like image loader, css loader. Even will help you convert scss to css

### Disadvantage
1. Config may be difficult for begineer

### reference
https://blog.techbridge.cc/2020/01/22/webpack-新手教學之淺談模組化與-snowpack/

## N-API (pronounced N as in the letter, followed by API)
is an API for building native Addons

### Reference:
```
诚如从暴力到 NAN 再到 NAPI——Node.js 原生模块开发方式变迁一文所提到的NAN和NAPI的历史，NAN为了搞定”封建时代“混乱的C++原生模块，不再让一个模块只能被若干个nodejs版本使用，而提出使用宏定义来解决这个问题，所以说NAN是一大堆宏定义，兼容各种nodejs版本的宏定义。做到了一次编写，到处编译。
而这种设计模式还是依然有缺点，那就是多次编译，也就是说你写的插件如果到了更高的Nodejs版本，还是需要再次编译，让宏定义再次匹配新的版本去编译出新的插件包，于是在node v8版本之后，nodejs提出了新的一套机制，也就是我们这次的主角-NAPI。
```
https://www.cnblogs.com/chyingp/p/nodejs-learning-napi.html

## REPL-Read-Eval-Print-Loop (aka Node shell)
It is a quick and easy way to test simple Node.js/JavaScript code.
