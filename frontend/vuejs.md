# Intro
## Creating a Vue Instance
Every Vue application starts by creating a new Vue instance with the Vue function:
```
var vm = new Vue({
  // options
})
```
When you create a Vue instance, you pass in an options object. Those object couble be `router`, `vuetify`

## Vue Lifecycle
it also runs functions called lifecycle hooks, giving users the opportunity to add their own code at specific stages.
 
<img src="https://vuejs.org/images/lifecycle.png" alt="drawing" width="500"/>

There are some hook function like `created`, `mounted`, `updated`, `destroyed`. For example, created hook can be used to run code after an instance is created:

- the state variable is not loaded when beforecreated hook is called
- the created hook is a great time to fire an AJAX call as state is ready
- we can access the dom from `$el` element inside the `mounted` function

# Vue components
## Template
All Vue.js templates are valid HTML that can be parsed by spec-compliant browsers and HTML parsers.

Under the hook, Vue compiles the templates into `Virtual DOM` render functions. 

## Computed Properties
Putting too much logic in your templates can make them bloated and hard to maintain

## Watchers

## Child and Parent component
### Props

That's how partent pass props to child:
```                
<child-card :parentmessage="parentmessage" :ok="ok" @finished="finished"></child-card>
```

Child can emit event to parent
```
this.$emit('finished')
```

#### One-Way Data Flow
All props form a one-way-down binding between the child property and the parent one: when the parent property updates, it will flow down to the child, but not the other way around. 
This prevents child components from accidentally mutating the parent’s state, which can make your app’s data flow harder to understand.

### sync modifier
That’s why instead, we recommend emitting events in the pattern of update:myPropName. 
For example, in a hypothetical component with a title prop, we could communicate the intent of assigning a new value with:
```
this.$emit('update:title', newTitle)
```
For convenience, we offer a shorthand for this pattern with the .sync modifier:
```
<text-document v-bind:title.sync="doc.title"></text-document>
```
## v-model
sugar syntax
```
<div id="app">
  <h3>2-way data binding with v-model</h3>
  <input type="text" v-model="name">
  <p>{{ name }}</p>
  
  <hr>
  <h3>is shorthand for this?</h3>
  <input type="text" v-bind:value="name2" v-on:input="name2 = event.target.value">
  <p>{{ name2 }}</p>
</div>
```
The label input file in the following code will not get updated when input is changed
```
  <h3>is shorthand for this?</h3>
  <input type="text" v-bind:value="name2" v-on:input="name2 = event.target.value">
  <p>{{ name2 }}</p>
```

## v-slot
child component can share object/data with parent
```
  <resolve-content-url
    v-slot="{ url }"
  >
    <v-img
      v-if="url"
      :src="url"
    >
    </v-img>
  </resolve-content-url>
```

## storage
### localStorage
1. for simple data
2. use `mounted` to handle loading the value from localStorage
3. To store more complex values, like objects or arrays, you must serialize and deserialize the values with JSON.

https://vuejs.org/v2/cookbook/client-side-storage.html

### child-level communication was not recommended by Vue
Reference: https://juejin.im/post/6844903542315040776

# Vuex
Vuex is the official state management library for Vue.js. It is used to centralize the state in a single store
Reference: https://flaviocopes.com/vuex/#introduction-to-vuex

1. state. One state store for each vue application, single source of truth 
2. mutation. Receive event type and handler it. Receive update state from payload
3. `this.$store.commit`. commit the message and event type to mutation
4. getter. Get state value
5. action. Similar to mutation, but it can commit (trigger mutation with event type) and it could be async

## Dispatch Vs Commit in Vuex
Dispatch triggers an action whereas commit triggers a mutation. $dispatch sends a message to Vuex store to perform some action.

The commit is done from within an action. It is done only when you have data to commit. Commit is synchronous and it may affect the performance of frontend.

When you want to do a task asynchronously you should use $dispatch so that your user interface will not get affected by the task by being frozen for a while or unresponsive.

# Others
## vm.$el
dom element or string

