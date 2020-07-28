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
