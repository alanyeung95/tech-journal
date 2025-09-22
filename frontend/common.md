
# Vue vs React

## Templating and Styling

### React
The use of React JSX is an abstraction of React.createElement method and is used to create Virtual DOM entities. Its syntax resembles HTML with some significant differences. It offers smoother developer experience, debugging, and better code readability than the createElement method. It’s also possible to use React without JSX.

### Vue
Vue takes a more conservative approach to templating and styling — one that’s separated from the logic. The markup is represented as templates that look just like old school HTML. In fact, every valid HTML is also a valid Vue template. Inside the templates, the framework offers a lot of syntactic sugars like conditionals, iterations, etc.


## Data blinding 
### Vue (two-way blinding)
```
Vue
<template>
  <input type="text" v-model="value" placeholder="please input" />
  <p>input is {{ value }}</p>
</template>

<script>
import { ref } from "vue";

export default {
  name: "InputDemo",
  setup() {
    const value = ref("");

    return {
      value,
    };
  },
};
</script>
```

In this example, we've easily bound both the input's value and the input event to the input using the v-model directive. This means that when the user types, the value variable updates, and the input's value is updated in sync.

### React (one-way blinding)
```
import { useState } from "react";

function InputComponent() {
  const [value, setValue] = useState("");

  const handleChange = (event) => {
    setValue(event.target.value);
  };

  return (
    <>
      <input
        type="text"
        placeholder="請輸入文字"
        value={value}
        onChange={handleChange}
      />
      <p>你輸入的文字是 {value}</p>
    </>
  );
}

export default InputComponent;
```

we need `setValue` and `onChange` function on React

## Virtual DOM
### React
Diffing and Reconciliation: 

React constructs a VDOM representation of the `UI`. When a component's state or props change, React creates a new VDOM tree and compares it to the previous one using a "diffing" algorithm.

### Vue
Reactivity System: 
Vue employs a reactive data binding system that automatically tracks dependencies. When `data` changes, Vue's reactivity system precisely identifies which components need to be re-rendered.

## Performance
### Vue
1. Vue tends to be faster in initial rendering and smaller apps due to its lightweight core and reactive system.
2. Vue uses a synchronous rendering model
3. Vue’s reactivity system is extremely efficient — it tracks dependencies and minimizes re-renders.

### React

1. Support concurrent rendering (enabled by the Fiber architecture), also allows React to
2. Prioritize urgent updates (like user input)
3. So react shines in `large-scale applications` thanks to its Fiber architecture

# Virtual DOM

## Problem of DOM
To recap, the `Document Object Model` is an object-based representation of an HTML document and an interface to manipulating that object. 

Simple methods such as document.getElementsByClassName() are fine to use on a small scale, but if we are updating multiple elements on a page every few seconds, it can start to become really expensive to constantly query and update the DOM.

## Why virtual DOM
1.  A virtual DOM can be thought of as a copy of the original DOM.

2.  since it is a plain Javascript object in memory, we can manipulate it freely and frequently without touching the actual DOM until we need to.

3.  Once all the diffs in virtual dom are collected, we can batch changes to the DOM, making only the updates that are needed.

Reference:
https://bitsofco.de/understanding-the-virtual-dom/

# Component
React与Vue都鼓励组件化应用。这本质上说，是建议你将你的应用分拆成一个个功能明确的模块，每个模块之间可以通过合适的方式互相联系。关于组件化的例子可以在这篇文章的中间部分被找到:

For example, download button will be a component. This component will encapsulate all the js, html and ccs as a module. Other parent component can just import this child component without worrying the css or js logic 

# Others
## React 
The philosophy behind React is that the state should be immutable. When trying to mutate the state object, no re-rendering occurs. In order to trigger re-rendering, the method setState should be used. This updates not only the root component but the entire component sub-tree as well. The re-rendering process can be controlled by using PureComponent or `shouldComponentUpdate` lifecycle hook. That flexibility comes at a cost, though, so all the optimizations should be done manually. This makes the data flow more predictable. Overall, React gives developers a lot of control over the re-rendering process.

## Vue
In Vue, the state is represented in the data object. Unlike React, the mutation of the state object triggers re-rendering.

However, there are some gotchas. For example, mutating nested objects or arrays might not trigger re-rendering. In this case, we can either use the Vue.set method (similar to the setState method in React) or make the changes in immutable fashion by using Object.assign or the ES6 spread operators. This can be confusing to beginners but you can find more information the internal mechanism behind Vue here.

Vue automatically performs optimizations to update specific parts of the component tree but doesn’t offer a manual way to prevent re-renders. In React, it’s up to the developer to decide when and where to manually prevent them.

