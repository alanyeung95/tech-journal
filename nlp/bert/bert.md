## concept

1. predict masked word
2. predict next sentence (if sentence b is following sentence a)

## Masked-Language Modeling (predict mask word)

sample sentence: the [MASK] sat on the mat

e: one-hot vector of the masked word "cat" = [0, 0, 0, 0, 1]
p: output probability distribution at the masked position = [0.1, 0.1, 0, 0, 0.8]
Loss = CrossEntropy(e,p)  (target: e === p eventually)

## Next Sentence Prediction
loss function: binary cross entropy

## input representation
input: [CLS] calculus is a [MASK] of math [SEP] it [MASK] developed by newton and leibnin
targets: true, "branch", "was"

- objective function is the sum of the three loss functions
- update model parameters by performing one gradient descent

## Pros and Cons
### Pros
1. BERT does not need manually labeled data (manual labeling is expensive)
2. use large-scale data, e.g., english wiki (2.5 billion words)

### Cons
1. large cost of traing, BERT base (110M parameter, 4 days of training)
