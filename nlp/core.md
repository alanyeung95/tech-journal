## sparse representation

i am happy because i am learning NLP
[1,1,1,1,....,0,0,0,0]

will be
[feature1,feature2,....feature n]
n=|V|

Problem
1. large training time
2. large prediction time

As you can see, as VV gets larger, the vector becomes more sparse. Furthermore, we end up having many more features and end up training \thetaθ VV parameters. This could result in larger training time, and large prediction time. 

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

## Vector space models 

Vector space models can be used to identify similarity for a question answering, paraphrasing, and summarization. 

## Principal components analysis 

### Eigenvector
Uncorrelated features for your data

### Eigenvalue
The amount of information retained by each feature

### Covariance 

Covariance indicates the level to which two variables vary together. If we examine N-dimensional samples, X = [x_1, x_2, ... x_N]^T, then the covariance matrix element C_{ij} is the covariance of x_i and x_j.

https://numpy.org/doc/stable/reference/generated/numpy.cov.html

## N-grams

### need memory space

for example

p(w3,w2,w1) = p(w1) * p(w2|w1) * p(w3|,w1,w2)

## RNN

Back Propagation:
https://www.geeksforgeeks.org/ml-back-propagation-through-time/

### Advantages
1. captures dependcies within a short range
2. better than N-grams as N-grams need memory to store the previous pattern

### Disadvantages
1. struggle with longer sequence

## LSTM

<img src="https://colah.github.io/posts/2015-08-Understanding-LSTMs/img/LSTM3-chain.png" alt="drawing" width="1000"/>

reference: https://colah.github.io/posts/2015-08-Understanding-LSTMs/

## Teacher Forcing
https://towardsdatascience.com/what-is-teacher-forcing-3da6217fed1c

## Encoder - Decorder
https://medium.com/analytics-vidhya/machine-translation-encoder-decoder-model-7e4867377161#:~:text=The%20encoder-decoder%20model%20is,text%20summarization%20and%20question%20answering.

● Encoder-It accepts a single element of the input sequence at each time step, process it, collects information for that element and propagates it forward.
● Intermediate vector- This is the final internal state produced from the encoder part of the model. It contains information about the entire input sequence to help the decoder make accurate predictions.
● Decoder- given the entire sentence, it predicts an output at each time step.
● final state of encoder is set as initial state of decoder

## Trax

## dense layer vs hidden layer
A dense layer is a kind of hidden layer where every node is connected to every other node in the next layer.


we could use the F1 measure to make the metrics work together: F1 = 2 * (Bleu * Rouge) / (Bleu + Rouge)

ref:
https://stackoverflow.com/questions/38045290/text-summarization-evaluation-bleu-vs-rouge

## Evulation approach

### BLEU
https://machinelearningmastery.com/calculate-bleu-score-for-text-python/

### Rouge
recall:

number_of_overlapping_words / total_words_in_reference_summary (target)

precision:

number_of_overlapping_words / total_words_in_system_summary (output)
