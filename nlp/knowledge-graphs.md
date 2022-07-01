
![image info](https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Conceptual_Diagram_-_Example.svg/440px-Conceptual_Diagram_-_Example.svg.png)

## ontology

schema of a directed graph, defining meaning of node and edges. 

For example:
```
p1 --> (friend of) p2 means p2 is a friend of p1
it also could be p1 --> (enemy of) p2
```

## relation embedding
embedding(head) + embedding(label) = embedding(tail)
obama + president of = USA
obama + graduated from = columbia university

## why we need negative triplets?

if only positive triplets, machine will learn all embedding as 0 in order to reduce loss. 

As zero vector + zero vector = zero vector. In order to lower the loss from negative triplets, embedding must be tuned.

## TODO:
1. https://www.youtube.com/watch?v=D-bTGefJj0A
