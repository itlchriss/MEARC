# %%
import pandas as pd
import numpy as np
import sklearn.ensemble

df = pd.read_csv('~/Documents/Phd_Studies/perucica/datasets/column_3C_weka.csv',sep=",")
df.head()

# %%
X=df.iloc[:,0:-1]
y = df.iloc[:,-1]

from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.30)
X.head()

# %%
feature_names=df.columns.values.tolist()[:-1]
nclasses = df['class'].unique().size
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import LabelEncoder
from xgboost import XGBClassifier
le = LabelEncoder()
y_train = le.fit_transform(y_train)
target=list(np.unique(y))


# %%
# clf = RandomForestClassifier(n_estimators = 100,max_depth=3,bootstrap=False)
clf:XGBClassifier = XGBClassifier(
    n_estimators = 100,
    max_depth = 4,
    learning_rate = 1,
    objective = 'binary:logistic',
    base_score = 1/nclasses
)


clf.fit(X_train, y_train)
 
y_pred = clf.predict(X_test)
# y_pred_proba=clf.predict_proba(X_test)
 
# from sklearn import metrics 
# print()

# print("Accuracy of the model: ", metrics.accuracy_score(y_test, y_pred))


# %%
from sklearn import tree
# import matplotlib.pyplot as plt
# fig = plt.figure(figsize=(25,20))
# _ = tree.plot_tree(clf.estimators_[2],
#                    feature_names=feature_names,
#                    class_names=target,
#                    filled=True)

# %%
import perucica as pr
import xgboost_converter.xgboost_helpers as xg

# %%
# pr.rf_to_code_dump(clf,feature_names,prog_lang="python",filename="weka_outforest.py",voter="probability")
if isinstance(clf, RandomForestClassifier):
    # pr.rf_to_code_dump(clf,feature_names,prog_lang="java",filename="outforest.java", voter="probability")
    pr.rf_to_code_print(clf, feature_names, prog_lang="java", voter="probability")
else:
    xg.model_to_py(1/nclasses, clf, 'xgb_model.py')

# %%
import weka_outforest as weka
df1=X_test

vec = df1.apply(lambda x: weka.random_forest(*x), axis=1)

# %%
prediction_class = [target[v] for i,v in enumerate(vec)]
# number_prediction=[target.index(v) for i,v in enumerate(y_pred)]

# %%
l=prediction_class==y_pred
res = [i for i, v in enumerate(l) if v==False]
len(res)

# %%
# X_test["pred"]=number_prediction
# X_test.to_csv("weka3_RA_test.csv",index=False)

# %%



