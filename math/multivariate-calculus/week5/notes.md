## Newton-Raphson

find the convergence point gradually

x_x+i = x_i - f(x_i)/f'(x_i)

### Problems

<img src="./diagrams/1.png" alt="drawing" width="500"/>

1. x never become value between 0 and 1
2. gradient (f'(x_i)) could be very small and x_i could be very large
3. if f(x_i) is 0, then it will stop converging

## Gradient

f(x,y) = x^2 \*y
Grad f = [df/dx, df/dy]

## Lagrange multipliers

Maximise f(x,y) = x^2 \* y
constraint g(x,y) = x**2 + y**2 = a\*\*2

solve ∇f = λ \* ∇g where λ is Lagrange multiplier

if ∇f = ∇(x**2\*y) = [2xy, x**2] = λ \* ∇g = λ \* [2x,2y]

1. 2xy = λ 2x => y=λ
2. x\*\*2 = λ 2y = 2y\*\*2 => x = ±√2 y
3. x**2 + y**2 = a**2 = 3y**2 => y= ± a/√3

https://www.coursera.org/learn/multivariate-calculus-machine-learning/discussions/weeks/5/threads/u9WR9ak0EemyHw7kF-pLRA

## q3-3

well, looks like we need to calculate it manually for this question

https://www.coursera.org/learn/multivariate-calculus-machine-learning/discussions/weeks/5/threads/qRKShW_TEem8JQ7hwboAWg
