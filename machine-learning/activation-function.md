## sigmoid function vs softmax function

The sigmoid function is used for the two-class logistic regression, whereas the softmax function is used for the multiclass logistic regression

Generally, we use softmax activation instead of sigmoid with the cross-entropy loss because softmax activation distributes the probability throughout each output node.

## rectified linear activation (ReLU)

It is a piecewise linear function that will output the input directly if it is positive, otherwise, it will output zero.

It has become the default activation function for many types of neural networks because a model that uses it is easier to train and often achieves better performance.

## log softmax

Value could be negative. When used for classifiers the log-softmax has the effect of heavily penalizing the model when it fails to predict a correct class.
