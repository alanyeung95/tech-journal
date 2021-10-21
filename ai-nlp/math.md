## Adam
### What is the Adam optimization algorithm?
Adam is an optimization algorithm that can be used instead of the classical stochastic gradient descent procedure to update network weights iterative based in training data.

### How Does Adam Work?
Adam is different to classical stochastic gradient descent.

Stochastic gradient descent maintains a single learning rate (termed alpha) for all weight updates and the learning rate does not change during training.

A learning rate is maintained for each network weight (parameter) and separately adapted as learning unfolds.

The method computes individual adaptive learning rates for different parameters from estimates of first and second moments of the gradients.

The authors describe Adam as combining the advantages of two other extensions of stochastic gradient descent. Specifically:

Adaptive Gradient Algorithm (AdaGrad) that maintains a per-parameter learning rate that improves performance on problems with sparse gradients (e.g. natural language and computer vision problems).
Root Mean Square Propagation (RMSProp) that also maintains per-parameter learning rates that are adapted based on the average of recent magnitudes of the gradients for the weight (e.g. how quickly it is changing). This means the algorithm does well on online and non-stationary problems (e.g. noisy).
Adam realizes the benefits of both AdaGrad and RMSProp.

Ref:
https://machinelearningmastery.com/adam-optimization-algorithm-for-deep-learning/#:~:text=Adam%20is%20an%20optimization%20algorithm,iterative%20based%20in%20training%20data.&text=The%20algorithm%20is%20called%20Adam,derived%20from%20adaptive%20moment%20estimation.

## Perplexity
Perplexity is an interesting measure of how well you're predicting something

Note that a human, after seeing that the first number is 1 and the second is 2, might guess that the third is 3, and so on. So a human is a better predictor than a die, and would have a lower perplexity

https://planspace.org/2013/09/23/perplexity-what-it-is-and-what-yours-is/


## Principal components analysis 
For dimensionality reduction

When you have a representation of your words in a high dimensional space.

You could use an algorithm like PCA
to get a representation on a vector space with fewer dimensions.

If you want to visualize your data,
you can get a reduced representation with three or fewer features. 
