## Gradient Boosted Trees vs Random Forest
Gradient Boosted Trees and Random Forest are ensembling methods that combine the outputs from individual trees

they are different in out the individual trees are build and how result are combine

random forest build individual tree and combine them in parallel.

boosting build each tree sequentially base on mistake (gradient) from the previous trees.


Visual Guide to Gradient Boosted Trees (xgboost)
: https://www.youtube.com/watch?v=TyvYZ26alZs

### How Gradient Boosted Trees deal with regression tasks

#### Logic
1. Each tree is a regression tree, not a classification tree.
2. The leaves of each tree contain numerical scores, which represent the model’s predicted value for that subset of data.
3. XGBoost builds one tree at a time, and each tree’s output is added to the previous trees’ predictions.
4. So if one tree predicts 2.3 and the next adds 1.1, the current prediction becomes 3.4.
5. The idea is that each tree learns to correct residuals from the previous ensemble.

#### Example
Imagine you have a dataset predicting house prices:

1. Tree 1 might learn: “If square footage < 800, predict $220,000.”
2. Tree 2 corrects it slightly: “Add $10,000 if location = downtown.”

Final prediction: $230,000

## Catboost advantages

### Smarter defaults values

### Ordered Boosting
Traditional gradient boosting methods are prone to prediction shifts caused by target leakage, primarily when the model uses the entire dataset to determine splits. 

CatBoost addresses this issue with ordered boosting, a technique that creates several permutations of the data and uses only past observations for each permutation when calculating leaf values. This method minimizes overfitting.

### Efficient Handling of Categorical Features: 

xgb need to convert categorical features into numerical representations through methods like one-hot encoding, CatBoost natively handles categorical data.

