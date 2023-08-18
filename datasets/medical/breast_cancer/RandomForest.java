class RandomForest { 

public static final String benign = "B";
public static final String malignant = "M";
public int randomForest(double radius_mean,double texture_mean,double perimeter_mean,double area_mean,double smoothness_mean,double compactness_mean,double concavity_mean,double concave_points_mean,double symmetry_mean,double fractal_dimension_mean,double radius_se,double texture_se,double perimeter_se,double area_se,double smoothness_se,double compactness_se,double concavity_se,double concave_points_se,double symmetry_se,double fractal_dimension_se,double radius_worst,double texture_worst,double perimeter_worst,double area_worst,double smoothness_worst,double compactness_worst,double concavity_worst,double concave_points_worst,double symmetry_worst,double fractal_dimension_worst){
double  class0 = 0;
double  class1 = 0;
 // TREE: 0
  if (concave_points_worst <= 0.14545000344514847)
    if (perimeter_worst <= 116.04999923706055)
      if (smoothness_worst <= 0.19054999947547913)
        {
         class0+=0.969811320754717;
         class1+=0.03018867924528302;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (smoothness_worst <= 0.0974699966609478)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.07692307692307693;
         class1+=0.9230769230769231;
        }
  else
    if (area_worst <= 668.3999938964844)
      if (symmetry_se <= 0.05079999938607216)
        {
         class0+=0.8571428571428571;
         class1+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (concavity_mean <= 0.06266500055789948)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 1
  if (radius_worst <= 17.02500057220459)
    if (texture_mean <= 20.789999961853027)
      if (symmetry_worst <= 0.39169999957084656)
        {
         class0+=0.9904761904761905;
         class1+=0.009523809523809525;
        }
      else
        {
         class0+=0.2;
         class1+=0.8;
        }
    else
      if (concavity_worst <= 0.2042500004172325)
        {
         class0+=0.975;
         class1+=0.025;
        }
      else
        {
         class0+=0.4583333333333333;
         class1+=0.5416666666666666;
        }
  else
    if (smoothness_worst <= 0.12224999815225601)
      if (radius_se <= 0.5147499889135361)
        {
         class0+=0.7142857142857143;
         class1+=0.2857142857142857;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (concave_points_mean <= 0.050119999796152115)
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 2
  if (area_worst <= 868.1999816894531)
    if (symmetry_worst <= 0.35740000009536743)
      if (area_se <= 91.55500030517578)
        {
         class0+=0.9657794676806084;
         class1+=0.034220532319391636;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (texture_worst <= 23.244999885559082)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8888888888888888;
        }
  else
    if (concavity_mean <= 0.062425000593066216)
      if (radius_worst <= 17.7549991607666)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 3
  if (radius_mean <= 15.275000095367432)
    if (area_se <= 31.28499984741211)
      if (smoothness_mean <= 0.11955000087618828)
        {
         class0+=0.951417004048583;
         class1+=0.048582995951417005;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
        }
    else
      if (area_worst <= 875.1499938964844)
        {
         class0+=0.7857142857142857;
         class1+=0.21428571428571427;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (concavity_mean <= 0.06800499930977821)
      if (texture_worst <= 26.74000072479248)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.2;
         class1+=0.8;
        }
    else
      if (texture_se <= 0.43140000104904175)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 4
  if (radius_worst <= 17.02500057220459)
    if (concave_points_worst <= 0.13505000621080399)
      if (area_worst <= 871.7999877929688)
        {
         class0+=0.9838709677419355;
         class1+=0.016129032258064516;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (symmetry_worst <= 0.35590000450611115)
        {
         class0+=0.7;
         class1+=0.3;
        }
      else
        {
         class0+=0.1;
         class1+=0.9;
        }
  else
    if (texture_worst <= 19.90999984741211)
      if (symmetry_mean <= 0.1736999973654747)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (concave_points_worst <= 0.0954899974167347)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 5
  if (perimeter_worst <= 116.04999923706055)
    if (compactness_worst <= 0.3498000055551529)
      if (smoothness_worst <= 0.19054999947547913)
        {
         class0+=0.9691119691119691;
         class1+=0.03088803088803089;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (concave_points_mean <= 0.04468500055372715)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.2631578947368421;
         class1+=0.7368421052631579;
        }
  else
    if (concave_points_worst <= 0.0954899974167347)
      if (concave_points_se <= 0.009000499965623021)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 6
  if (radius_worst <= 17.02500057220459)
    if (radius_worst <= 15.434999942779541)
      if (concave_points_worst <= 0.19789999723434448)
        {
         class0+=0.982532751091703;
         class1+=0.017467248908296942;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (concavity_worst <= 0.4944000095129013)
        {
         class0+=0.8095238095238095;
         class1+=0.19047619047619047;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (concave_points_worst <= 0.13679999858140945)
      if (radius_worst <= 17.739999771118164)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.16666666666666666;
         class1+=0.8333333333333334;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 7
  if (perimeter_mean <= 98.75500106811523)
    if (area_worst <= 868.1999816894531)
      if (concave_points_worst <= 0.13505000621080399)
        {
         class0+=0.9838056680161943;
         class1+=0.016194331983805668;
        }
      else
        {
         class0+=0.5;
         class1+=0.5;
        }
    else
      if (texture_worst <= 19.824999809265137)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (radius_worst <= 18.354999542236328)
      if (compactness_worst <= 0.3138999938964844)
        {
         class0+=0.7;
         class1+=0.3;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 8
  if (radius_worst <= 17.02500057220459)
    if (compactness_worst <= 0.39010000228881836)
      if (smoothness_worst <= 0.19054999947547913)
        {
         class0+=0.9691119691119691;
         class1+=0.03088803088803089;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (concave_points_mean <= 0.051419999450445175)
        {
         class0+=0.875;
         class1+=0.125;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.9090909090909091;
        }
  else
    if (concavity_mean <= 0.062425000593066216)
      if (compactness_se <= 0.0115450001321733)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.875;
         class1+=0.125;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 9
  if (area_worst <= 868.1999816894531)
    if (concave_points_mean <= 0.05009999871253967)
      if (area_mean <= 696.25)
        {
         class0+=0.9833333333333333;
         class1+=0.016666666666666666;
        }
      else
        {
         class0+=0.5;
         class1+=0.5;
        }
    else
      if (texture_worst <= 25.65499973297119)
        {
         class0+=0.9090909090909091;
         class1+=0.09090909090909091;
        }
      else
        {
         class0+=0.15384615384615385;
         class1+=0.8461538461538461;
        }
  else
    if (concave_points_mean <= 0.050084998831152916)
      if (texture_worst <= 19.90999984741211)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.2857142857142857;
         class1+=0.7142857142857143;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
  // VOTER
int classification = -1;
  if( class0>=class1)
    classification = 0;
  if( class1>=class0)
    classification = 1;
 return classification;
}
}
