## Logistic Regression Overview
Logistic regression makes use of the sigmoid function which outputs a probability between 0 and 1.

## Logistic Regression: Cost Function

J(θ)=−m1​∑i=1m​[y(i)logh(x(i),θ)+(1−y(i))log(1−h(x(i),θ))]

As you can see in the picture above, if y=1y = 1y=1 and you predict something close to 000, you get a cost close to ∞\infty∞. The same applies for then y=0y=0y=0 and you predict something close to 111. On the other hand if you get a prediction equal to the label, you get a cost of 0. In either, case you are trying to minimize J(θ)J(\theta)J(θ). 

## Bayes' Rule
### Applications of Naive Bayes

There are many applications of naive Bayes including: 

    Author identification
    Spam filtering 
    Information retrieval 
    Word disambiguation 

This method is usually used as a simple baseline. It also really fast.

### Naïve Bayes Assumptions

'it is sunny and hot in the Sahara sesert.'

'It's always cold and snowy in _.'

In the first image, you can see the word sunny and hot tend to depend on each other and are correlated to a certain extent with the word "desert". Naive Bayes assumes independence throughout. 

 Furthermore, if you were to fill in the sentence on the right, this naive model will assign equal weight to the words "spring, summer, fall, winter". 

### Limitation
#### Removing words punctuation
Tweet: This is not good, because your attitude is not even close to being nice.

processed_tweet: [good, attitude, close, nice]

Tweet: My beloved grandmother :(

processed_tweet: [belov, grandmoth]

#### Word order

Tweet: I am happy because I did not go 

vs

Tweet: I am not happy because I did go

### Vector space models 

Vector space models can be used to identify similarity for a question answering, paraphrasing, and summarization. 

### Principal components analysis 
For dimensionality reduction

When you have a representation of your words in a high dimensional space.

You could use an algorithm like PCA
to get a representation on a vector space with fewer dimensions.

If you want to visualize your data,
you can get a reduced representation with three or fewer features. 

#### Eigenvector
Uncorrelated features for your data

#### Eigenvalue
The amount of information retained by each feature

#### Covariance 

Covariance indicates the level to which two variables vary together. If we examine N-dimensional samples, X = [x_1, x_2, ... x_N]^T, then the covariance matrix element C_{ij} is the covariance of x_i and x_j.

https://numpy.org/doc/stable/reference/generated/numpy.cov.html