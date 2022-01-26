# Classification Losses

## cross entropy

https://medium.com/swlh/cross-entropy-loss-in-pytorch-c010faf97bab

the target label would be [1,0,0,0] , while the softmax probability output is [0.775, 0.116, 0.039, 0.07]

L_ce = -(1 * log(0.775) + 0*log(0.116) + 0*log(0.039) + 0*log(0.07)) = 0.255

# Regression Losses

## Mean Square Error

- it is easier to calculate gradients

- predictions which are far away from actual values are penalized heavily

## Mean absolute error
