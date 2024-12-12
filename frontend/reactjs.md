# Hello world example
```
import React from 'react'
import ReactDOM from 'react-dom'
import { Grid, Row, Col } from 'react-flexbox-grid'

class App extends React.Component {
  render() {
    return (
      <Grid>
        <Row>
        <Col>Hello world!</Col>
        </Row>
      </Grid>
    )
  }
}
ReactDOM.render(
  <App />,
  document.getElementById('container')
);
```

# Lifecycle

Reference: https://projects.wojtekmaj.pl/react-lifecycle-methods-diagram/

## Three main state
1. Mounting
2. Updating
3. Unmounting

## Mounting method
1. constructor()
    1. call `super(props)` for parent props
    2. set initial `state`
2. getDerivedStateFromProps()
3. render()
    1. render React elements for example by JSX or pure HTML. React elements will instruct React to render a DOM node
    2. JSX: `const element = <h1>Hello, world!</h1>;`
    3. it does not modify component state
4. componentDidMount()
    1. invoked immediately after a component is mounted (inserted into the tree)
    2. we can request API call to backend inside this method
    
## Updating method
1. render()
2. componentDidUpdate()
    1. This is also a good place to do network requests as long as you compare the current props

## Unmounting method
1. componentWillUnmount()
    1. cleaning up any subscriptions or closing any connection


# Redux

## Action
Method to CRUD the store, have different `type`
```
// 存錢的 Action，存入的金額為 amount 
{
  type: 'DEPOSIT',
  amount: 100
}
// 領錢的 Action，全部一次提領出來所以不需要 amount
{
  type: 'WITHDRAW_ALL'
}
```

## Reducer
Business logic to the action type
```
function reducer(state = 0, action) {
  switch(action.type) {
    
    // 如果 type 為 'DEPOSIT'，回傳當前的金額加上存入的金額
    case 'DEPOSIT':
      return state + action.mount
    
    // 如果 type 為 'WITHDRAW'，回傳 0
    case 'WITHDRAW':
      return 0
    
    // 如果 type 為其他，回傳當前的金額 
    default:
      return state
  }
}
```

## Store
Instance that created with reducer and receive action

```
const store = createStore(reducer)
模擬存入任意金額的錢
store.getState() // 一開始為 0 塊錢
// 存入 10 塊錢
store.dispatch({
  type: 'DEPOSIT',
  amount: 10
})
store.getState() // 現在有 10 塊錢
// 再存入 100 塊錢
store.dispatch({
  type: 'DEPOSIT',
  amount: 100
})
store.getState() // 現在總共有 110 塊錢
// 模擬將全部的錢一次提領出來
// 全部提領出來
store.dispatch({
  type: 'WITHDRAW'
})
store.getState() // 0 塊錢
```

# Context API


If you are using Redux only to avoid passing props down to deeply nested components, then you could replace Redux with the Context API. It is exactly intended for this use case.

On the other hand, if you are using Redux for everything else (having a predictable state container, handling your application's logic outside of your components, centralizing your application's state, using Redux DevTools to track when, where, why, and how your application's state changed, or using plugins such as Redux Form, Redux Saga, Redux Undo, Redux Persist, Redux Logger, etc…), then there is absolutely no reason for you to abandon Redux. The Context API doesn't provide any of this.

ref: https://stackoverflow.com/a/49569203
