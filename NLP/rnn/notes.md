## Introduction

https://zhuanlan.zhihu.com/p/30844905

https://medium.com/@mliuzzolino/hello-rnn-55a9237b7112


<img src="https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.researchgate.net%2Ffigure%2FThe-standard-RNN-and-unfolded-RNN_fig1_318332317&psig=AOvVaw3EL-eFXDvEU0Gh_R4Bon63&ust=1634915648870000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCOjis8bl2_MCFQAAAAAdAAAAABAD">

Output_t = g(V．H_t)
H_t = f(U．X_t + W．H_t-1)

## Disadvantage

### Long sequence
Rnns can forget early inputs. If t is large, h_t is almost irrelevant to x_0

### Vanishing gradient (backpropagation)
https://www.superdatascience.com/blogs/recurrent-neural-networks-rnn-the-vanishing-gradient-problem

The problem relates to updating wrec (weight recurring) – the weight that is used to connect the hidden layers to themselves in the unrolled temporal loop.

For instance, to get from xt-3 to xt-2 we multiply xt-3 by wrec. Then, to get from xt-2 to xt-1 we again multiply xt-2 by wrec. So, we multiply with the same exact weight multiple times, and this is where the problem arises: when you multiply something by a small number, your value decreases very quickly.

#### Solutions to the vanishing gradient problem
In case of exploding gradient, you can:

stop backpropagating after a certain point, which is usually not optimal because not all of the weights get updated;
penalize or artificially reduce gradient;
put a maximum limit on a gradient.

In case of vanishing gradient, you can:

initialize weights so that the potential for vanishing gradient is minimized;
have Echo State Networks that are designed to solve the vanishing gradient problem;
have Long Short-Term Memory Networks (LSTMs).