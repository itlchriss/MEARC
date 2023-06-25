from sklearn.tree import _tree
from sklearn.ensemble import RandomForestClassifier
import numpy as np
import sys

''' Returns decision tree as plain code in python or java in standard output.
    Inputs are sklearn decision tree, feature name,desired programming language syntax and voter.
    Voter can be either probability or majority.
    Probability voter returns probability for each class based on decision tree nodes' values.
    Majority voter returns majority argmax value of class.
'''

def tree_to_code_rf_print(tree,feature_names,prog_lang="python",voter="probability"):
    
    tree_ = tree.tree_
    feature_name = [
        feature_names[i] if i != _tree.TREE_UNDEFINED else "undefined!"
        for i in tree_.feature
    ]

    #function recurse is used for traversing the decision tree recursively
    def recurse(node, depth,prog_lang,voter):
        
        if prog_lang=="python":
            indent = "  " * depth
            if tree_.feature[node] != _tree.TREE_UNDEFINED:
                name = feature_name[node]
                threshold = tree_.threshold[node]
                print ("{}if {} <= {}:".format(indent, name, threshold))
                recurse(tree_.children_left[node], depth + 1,prog_lang,voter)
                print ("{}else:  # if {} > {}".format(indent, name, threshold))
                recurse(tree_.children_right[node], depth + 1,prog_lang,voter)
            else:
                if voter=="majority":
                    print (" {} class{}+=1".format(indent, np.argmax(tree_.value[node][0])))
                else:
                    if voter=="probability":
                        for i in range(0,tree_.n_classes[0]):
                            print ("{} class{}+={}".format(indent,i,tree_.value[node][0][i]/sum(tree_.value[node][0])))
                
        else:
            if prog_lang=="java":
                indent = "  " * depth
                if tree_.feature[node] != _tree.TREE_UNDEFINED:
                    name = feature_name[node]
                    threshold = tree_.threshold[node]
                    print ("{}if ({} <= {})".format(indent, name, threshold))
                    recurse(tree_.children_left[node], depth + 1,prog_lang,voter)
                    print ("{}else".format(indent, name, threshold))
                    recurse(tree_.children_right[node], depth + 1,prog_lang,voter)
                else:
                    if voter=="majority":
                        print (" {} class{}+=1;".format(indent, np.argmax(tree_.value[node][0])))
                    else:
                        if voter=="probability":
                            print(indent+"{")
                            for i in range(0,tree_.n_classes[0]):
                                print ("{} class{}+={};".format(indent,i,tree_.value[node][0][i]/sum(tree_.value[node][0])))
                            print(indent+"}")
                
    recurse(0, 1,prog_lang,voter)


''' 
    Output the random forest classifier as a loop-free imperative program written in python or java in the specified file.
    Inputs are sklearn RandomForestClassifier, feature names, programming language,voter, and name of the output file.
'''

    
def rf_to_code_dump(rf,feature_names,prog_lang="python",voter="probability",filename="outforest.py"):
    original_stdout=sys.stdout
    
    with open(filename,'w') as f:
        sys.stdout=f
        rf_to_code_print(rf,feature_names,prog_lang)
    sys.stdout=original_stdout
    
'''
    Returns the random forest classifier as a loop-free imperative program written in python or java in the standard output.
    Inputs are sklearn RandomForestClassifier, feature names, programming language,voter, and name of the output file.
    Voter can be either majority or probability.
    Majority voter returns the class that is voted based on majority of tree classification in random forest.
    Probability voter returns the class with highest average probability with respect to all trees.

'''
    
def rf_to_code_print(rf,feature_names,prog_lang="python",voter="probability"):
    if prog_lang=="python":
        print ("def random_forest({}):".format(", ".join(feature_names)))
        for i in range(0,rf.n_classes_):
            print("  class{} = 0".format(i))
        for tree_idx, est in enumerate(rf.estimators_):
            print('  #TREE: {}'.format(tree_idx))
            tree_to_code_rf_print(rf.estimators_[tree_idx],feature_names,prog_lang,voter)
        print("  #VOTER")
        indent="  "
        for i in range(0,rf.n_classes_):
            print("{}if".format(indent),end="")
            if i!=rf.n_classes_-1:
                for j in range(0,rf.n_classes_):
                    if j!=i and j!=rf.n_classes_-1:
                        print(" class{}>=class{} and".format(i,j),end="")
                    else:
                        if j!=i:
                            print(" class{}>=class{}:".format(i,j),end="")
            else:
                for j in range(0,rf.n_classes_):
                    if j!=i and j!=i-1:
                        print(" class{}>=class{} and".format(i,j),end="")
                    else:
                        if j!=i:
                            print(" class{}>=class{}:".format(i,j),end="")

            print()
            print("{}return {}".format(2*indent,i))
    else:
        if prog_lang=="java":
            print("class RandomForest { \n")
            for i, c in enumerate(rf.classes_):
                print("public static final int %s = %d;" % (c, i))
            print("public int randomForest(double {})".format(",double ".join(feature_names))+"{")
            for i in range(0,rf.n_classes_):
                print("double  class{} = 0;".format(i))
            for tree_idx, est in enumerate(rf.estimators_):
                print(' // TREE: {}'.format(tree_idx))
                tree_to_code_rf_print(rf.estimators_[tree_idx],feature_names,prog_lang,voter)
            print("  // VOTER")
            print("int classification = -1;")
            indent="  "
            for i in range(0,rf.n_classes_):
                print("{}if(".format(indent),end="")
                if i!=rf.n_classes_-1:
                    for j in range(0,rf.n_classes_):
                        if j!=i and j!=rf.n_classes_-1:
                          print(" class{}>=class{} &&".format(i,j),end="")
                        else:
                            if j!=i:
                                print(" class{}>=class{})".format(i,j),end="")
                else:
                    for j in range(0,rf.n_classes_):
                        if j!=i and j!=i-1:
                            print(" class{}>=class{} &&".format(i,j),end="")
                        else:
                            if j!=i:
                                print(" class{}>=class{})".format(i,j),end="")

                print()
                print("{}classification = {};".format(2*indent,i))
            print(" return classification;")
            print("}")
            print("}")
            

