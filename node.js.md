# Notes
## How does node.js works
Node.js listens and passes every concurrent traffic in a queue, which will be executed by an event loop.

### How does a single thread handle asynchronous code in Node.js?
By using `libuv`, node can ask os to do something like open and read a file. If it takes sometime for os to work, node will continue it's task. Then the os will remind node in the event queue once it finished its task.

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

