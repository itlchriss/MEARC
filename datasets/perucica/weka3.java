class RandomForest { 

public static final int Hernia = 0;
public static final int Normal = 1;
public static final int Spondylolisthesis = 2;
//@ ensures(*The patients who had degenerative spondylolisthesis had a lumbar lordosis angle from 52.6 to 62.1.*);
public int randomForest(double pelvic_incidence,double pelvic_tilt,double lumbar_lordosis_angle,double sacral_slope,double pelvic_radius,double degree_spondylolisthesis){
double  class0 = 0;
double  class1 = 0;
double  class2 = 0;
 // TREE: 0
  if (sacral_slope <= 40.53826332092285)
    if (degree_spondylolisthesis <= 18.047505855560303)
      if (degree_spondylolisthesis <= 1.2192070484161377)
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.6530612244897959;
         class1+=0.32653061224489793;
         class2+=0.02040816326530612;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (sacral_slope <= 53.330434799194336)
      if (pelvic_incidence <= 69.5125503540039)
        {
         class0+=0.041666666666666664;
         class1+=0.5;
         class2+=0.4583333333333333;
        }
      else
        {
         class0+=0.0;
         class1+=0.041666666666666664;
         class2+=0.9583333333333334;
        }
    else
      if (sacral_slope <= 58.73384475708008)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.09090909090909091;
         class2+=0.9090909090909091;
        }
 // TREE: 1
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_incidence <= 49.899505615234375)
        {
         class0+=0.8064516129032258;
         class1+=0.1935483870967742;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.5714285714285714;
         class2+=0.07142857142857142;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
      else
        {
         class0+=0.03225806451612903;
         class1+=0.967741935483871;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (degree_spondylolisthesis <= 18.88465976715088)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 31.24958896636963)
        {
         class0+=0.0;
         class1+=0.07692307692307693;
         class2+=0.9230769230769231;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 2
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.4098360655737705;
         class1+=0.5737704918032787;
         class2+=0.01639344262295082;
        }
      else
        {
         class0+=0.07142857142857142;
         class1+=0.8571428571428571;
         class2+=0.07142857142857142;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_incidence <= 57.21945381164551)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.05405405405405406;
         class2+=0.9459459459459459;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 3
  if (pelvic_radius <= 110.68482971191406)
    if (sacral_slope <= 41.11776542663574)
      if (lumbar_lordosis_angle <= 34.232845306396484)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.625;
         class1+=0.0;
         class2+=0.375;
        }
    else
      if (degree_spondylolisthesis <= 11.642083644866943)
        {
         class0+=0.5;
         class1+=0.5;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (lumbar_lordosis_angle <= 56.35000038146973)
      if (degree_spondylolisthesis <= 20.085037231445312)
        {
         class0+=0.3645833333333333;
         class1+=0.6041666666666666;
         class2+=0.03125;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (lumbar_lordosis_angle <= 62.66514015197754)
        {
         class0+=0.0;
         class1+=0.3125;
         class2+=0.6875;
        }
      else
        {
         class0+=0.0;
         class1+=0.05714285714285714;
         class2+=0.9428571428571428;
        }
 // TREE: 4
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.8181818181818182;
         class2+=0.09090909090909091;
        }
    else
      if (degree_spondylolisthesis <= 2.116147041320801)
        {
         class0+=0.038461538461538464;
         class1+=0.9615384615384616;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.6428571428571429;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (sacral_slope <= 42.68553161621094)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.2857142857142857;
         class2+=0.7142857142857143;
        }
    else
      if (degree_spondylolisthesis <= 26.451024055480957)
        {
         class0+=0.0;
         class1+=0.125;
         class2+=0.875;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 5
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 112.92106246948242)
        {
         class0+=0.6470588235294118;
         class1+=0.058823529411764705;
         class2+=0.29411764705882354;
        }
      else
        {
         class0+=0.21212121212121213;
         class1+=0.6363636363636364;
         class2+=0.15151515151515152;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_radius <= 119.18045425415039)
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 43.32751655578613)
        {
         class0+=0.058823529411764705;
         class1+=0.11764705882352941;
         class2+=0.8235294117647058;
        }
      else
        {
         class0+=0.0;
         class1+=0.013513513513513514;
         class2+=0.9864864864864865;
        }
 // TREE: 6
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_tilt <= 17.583970069885254)
      if (sacral_slope <= 40.1485710144043)
        {
         class0+=0.43636363636363634;
         class1+=0.5636363636363636;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.95;
         class2+=0.05;
        }
    else
      if (sacral_slope <= 29.734612464904785)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.4074074074074074;
         class1+=0.5185185185185185;
         class2+=0.07407407407407407;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (pelvic_incidence <= 55.807992935180664)
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 26.451024055480957)
        {
         class0+=0.0;
         class1+=0.125;
         class2+=0.875;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 7
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_incidence <= 49.899505615234375)
        {
         class0+=0.8064516129032258;
         class1+=0.1935483870967742;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.5714285714285714;
         class2+=0.07142857142857142;
        }
    else
      if (degree_spondylolisthesis <= 2.116147041320801)
        {
         class0+=0.038461538461538464;
         class1+=0.9615384615384616;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.6428571428571429;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (sacral_slope <= 42.34691619873047)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.045454545454545456;
         class2+=0.9545454545454546;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 8
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (sacral_slope <= 36.027883529663086)
        {
         class0+=0.6166666666666667;
         class1+=0.38333333333333336;
         class2+=0.0;
        }
      else
        {
         class0+=0.21875;
         class1+=0.78125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_radius <= 121.3623161315918)
      if (pelvic_radius <= 103.31562042236328)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.037037037037037035;
         class1+=0.1111111111111111;
         class2+=0.8518518518518519;
        }
    else
      if (degree_spondylolisthesis <= 27.53839111328125)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 9
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.12782287597656)
        {
         class0+=0.5428571428571428;
         class1+=0.37142857142857144;
         class2+=0.08571428571428572;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.8518518518518519;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_incidence <= 57.21945381164551)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.045454545454545456;
         class2+=0.9545454545454546;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 10
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_tilt <= 27.4694242477417)
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.7727272727272727;
         class1+=0.22727272727272727;
         class2+=0.0;
        }
      else
        {
         class0+=0.34782608695652173;
         class1+=0.5072463768115942;
         class2+=0.14492753623188406;
        }
    else
      if (degree_spondylolisthesis <= 6.3782958984375)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (degree_spondylolisthesis <= 8.581488609313965)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.0;
         class2+=0.8571428571428571;
        }
    else
      if (sacral_slope <= 53.0706672668457)
        {
         class0+=0.0196078431372549;
         class1+=0.23529411764705882;
         class2+=0.7450980392156863;
        }
      else
        {
         class0+=0.0;
         class1+=0.0425531914893617;
         class2+=0.9574468085106383;
        }
 // TREE: 11
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (pelvic_radius <= 125.44171905517578)
      if (degree_spondylolisthesis <= 17.48571014404297)
        {
         class0+=0.6333333333333333;
         class1+=0.36666666666666664;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 23.426228523254395)
        {
         class0+=0.14705882352941177;
         class1+=0.7647058823529411;
         class2+=0.08823529411764706;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.0;
         class2+=0.6666666666666666;
        }
  else
    if (pelvic_incidence <= 63.123300552368164)
      if (pelvic_incidence <= 61.22392272949219)
        {
         class0+=0.0;
         class1+=0.34615384615384615;
         class2+=0.6538461538461539;
        }
      else
        {
         class0+=0.25;
         class1+=0.75;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 69.5125503540039)
        {
         class0+=0.045454545454545456;
         class1+=0.18181818181818182;
         class2+=0.7727272727272727;
        }
      else
        {
         class0+=0.0;
         class1+=0.05172413793103448;
         class2+=0.9482758620689655;
        }
 // TREE: 12
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (degree_spondylolisthesis <= 1.3626884818077087)
      if (pelvic_radius <= 121.49974060058594)
        {
         class0+=0.4583333333333333;
         class1+=0.5;
         class2+=0.041666666666666664;
        }
      else
        {
         class0+=0.07407407407407407;
         class1+=0.9259259259259259;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 35.3641357421875)
        {
         class0+=0.7878787878787878;
         class1+=0.21212121212121213;
         class2+=0.0;
        }
      else
        {
         class0+=0.2413793103448276;
         class1+=0.6896551724137931;
         class2+=0.06896551724137931;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (sacral_slope <= 42.34691619873047)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
    else
      if (degree_spondylolisthesis <= 31.24958896636963)
        {
         class0+=0.0;
         class1+=0.07692307692307693;
         class2+=0.9230769230769231;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 13
  if (pelvic_radius <= 110.68482971191406)
    if (sacral_slope <= 41.11776542663574)
      if (pelvic_incidence <= 48.98326301574707)
        {
         class0+=0.5714285714285714;
         class1+=0.0;
         class2+=0.42857142857142855;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 101.79591369628906)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.08695652173913043;
         class1+=0.08695652173913043;
         class2+=0.8260869565217391;
        }
  else
    if (lumbar_lordosis_angle <= 56.35000038146973)
      if (pelvic_radius <= 125.30192565917969)
        {
         class0+=0.4603174603174603;
         class1+=0.38095238095238093;
         class2+=0.15873015873015872;
        }
      else
        {
         class0+=0.1276595744680851;
         class1+=0.723404255319149;
         class2+=0.14893617021276595;
        }
    else
      if (degree_spondylolisthesis <= 11.996948957443237)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
 // TREE: 14
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_radius <= 111.27699661254883)
        {
         class0+=0.7647058823529411;
         class1+=0.17647058823529413;
         class2+=0.058823529411764705;
        }
      else
        {
         class0+=0.48214285714285715;
         class1+=0.48214285714285715;
         class2+=0.03571428571428571;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
      else
        {
         class0+=0.03225806451612903;
         class1+=0.967741935483871;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (lumbar_lordosis_angle <= 54.25)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
    else
      if (pelvic_radius <= 123.53171920776367)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.05;
         class2+=0.95;
        }
 // TREE: 15
  if (sacral_slope <= 40.53826332092285)
    if (degree_spondylolisthesis <= 18.047505855560303)
      if (pelvic_radius <= 125.30192565917969)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.17647058823529413;
         class1+=0.8235294117647058;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (degree_spondylolisthesis <= 8.581488609313965)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.0;
         class2+=0.8571428571428571;
        }
    else
      if (pelvic_radius <= 121.77228927612305)
        {
         class0+=0.013888888888888888;
         class1+=0.06944444444444445;
         class2+=0.9166666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.34615384615384615;
         class2+=0.6538461538461539;
        }
 // TREE: 16
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 125.37104797363281)
      if (lumbar_lordosis_angle <= 42.77910232543945)
        {
         class0+=0.6382978723404256;
         class1+=0.2978723404255319;
         class2+=0.06382978723404255;
        }
      else
        {
         class0+=0.125;
         class1+=0.3125;
         class2+=0.5625;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
      else
        {
         class0+=0.03225806451612903;
         class1+=0.8387096774193549;
         class2+=0.12903225806451613;
        }
  else
    if (degree_spondylolisthesis <= 11.642083644866943)
      if (sacral_slope <= 41.474557876586914)
        {
         class0+=0.625;
         class1+=0.25;
         class2+=0.125;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
    else
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.011627906976744186;
         class2+=0.9883720930232558;
        }
 // TREE: 17
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (lumbar_lordosis_angle <= 29.128881454467773)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.46774193548387094;
         class1+=0.4838709677419355;
         class2+=0.04838709677419355;
        }
    else
      if (pelvic_tilt <= 26.227545738220215)
        {
         class0+=0.1282051282051282;
         class1+=0.8717948717948718;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_radius <= 136.77429962158203)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.045454545454545456;
         class2+=0.9545454545454546;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 18
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 125.30192565917969)
      if (degree_spondylolisthesis <= 18.731049060821533)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 31.517855644226074)
        {
         class0+=0.15789473684210525;
         class1+=0.7368421052631579;
         class2+=0.10526315789473684;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (degree_spondylolisthesis <= 8.581488609313965)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.0;
         class2+=0.8571428571428571;
        }
    else
      if (sacral_slope <= 53.0706672668457)
        {
         class0+=0.0196078431372549;
         class1+=0.23529411764705882;
         class2+=0.7450980392156863;
        }
      else
        {
         class0+=0.0;
         class1+=0.0425531914893617;
         class2+=0.9574468085106383;
        }
 // TREE: 19
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (sacral_slope <= 36.027883529663086)
        {
         class0+=0.6166666666666667;
         class1+=0.38333333333333336;
         class2+=0.0;
        }
      else
        {
         class0+=0.21875;
         class1+=0.78125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (lumbar_lordosis_angle <= 51.30072784423828)
        {
         class0+=0.16666666666666666;
         class1+=0.5;
         class2+=0.3333333333333333;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 20.63097858428955)
        {
         class0+=0.25;
         class1+=0.25;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.022988505747126436;
         class2+=0.9770114942528736;
        }
 // TREE: 20
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_tilt <= 17.583970069885254)
      if (lumbar_lordosis_angle <= 32.91078758239746)
        {
         class0+=0.6190476190476191;
         class1+=0.38095238095238093;
         class2+=0.0;
        }
      else
        {
         class0+=0.2037037037037037;
         class1+=0.7777777777777778;
         class2+=0.018518518518518517;
        }
    else
      if (pelvic_incidence <= 53.754459381103516)
        {
         class0+=0.8571428571428571;
         class1+=0.14285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.4166666666666667;
         class1+=0.5;
         class2+=0.08333333333333333;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_tilt <= 9.68252682685852)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.05405405405405406;
         class2+=0.9459459459459459;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 21
  if (pelvic_incidence <= 56.49103927612305)
    if (sacral_slope <= 28.13647174835205)
      if (sacral_slope <= 23.553030014038086)
        {
         class0+=0.6363636363636364;
         class1+=0.36363636363636365;
         class2+=0.0;
        }
      else
        {
         class0+=0.9166666666666666;
         class1+=0.08333333333333333;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 20.085037231445312)
        {
         class0+=0.31746031746031744;
         class1+=0.6825396825396826;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
  else
    if (pelvic_radius <= 120.88045120239258)
      if (lumbar_lordosis_angle <= 47.568403244018555)
        {
         class0+=0.5;
         class1+=0.2;
         class2+=0.3;
        }
      else
        {
         class0+=0.028985507246376812;
         class1+=0.07246376811594203;
         class2+=0.8985507246376812;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0625;
         class1+=0.6875;
         class2+=0.25;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 22
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_tilt <= 9.53122329711914)
        {
         class0+=0.3076923076923077;
         class1+=0.6923076923076923;
         class2+=0.0;
        }
      else
        {
         class0+=0.6;
         class1+=0.35;
         class2+=0.05;
        }
    else
      if (pelvic_incidence <= 35.79051399230957)
        {
         class0+=0.6;
         class1+=0.4;
         class2+=0.0;
        }
      else
        {
         class0+=0.08571428571428572;
         class1+=0.9142857142857143;
         class2+=0.0;
        }
  else
    if (pelvic_tilt <= 14.86863899230957)
      if (pelvic_radius <= 115.87165069580078)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.2727272727272727;
         class2+=0.7272727272727273;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 23
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_incidence <= 49.899505615234375)
        {
         class0+=0.8064516129032258;
         class1+=0.1935483870967742;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.5714285714285714;
         class2+=0.07142857142857142;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
      else
        {
         class0+=0.03225806451612903;
         class1+=0.967741935483871;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (degree_spondylolisthesis <= 18.88465976715088)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.05405405405405406;
         class2+=0.9459459459459459;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 24
  if (sacral_slope <= 40.53826332092285)
    if (degree_spondylolisthesis <= 18.047505855560303)
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.7916666666666666;
         class1+=0.20833333333333334;
         class2+=0.0;
        }
      else
        {
         class0+=0.4098360655737705;
         class1+=0.5737704918032787;
         class2+=0.01639344262295082;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (pelvic_tilt <= 22.42124843597412)
        {
         class0+=0.0;
         class1+=0.7647058823529411;
         class2+=0.23529411764705882;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.0;
         class2+=0.6666666666666666;
        }
    else
      if (pelvic_radius <= 121.77228927612305)
        {
         class0+=0.013888888888888888;
         class1+=0.06944444444444445;
         class2+=0.9166666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.34615384615384615;
         class2+=0.6538461538461539;
        }
 // TREE: 25
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (pelvic_radius <= 125.44171905517578)
        {
         class0+=0.6333333333333333;
         class1+=0.36666666666666664;
         class2+=0.0;
        }
      else
        {
         class0+=0.1875;
         class1+=0.8125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_tilt <= 18.92229175567627)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
    else
      if (pelvic_incidence <= 56.547590255737305)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.012195121951219513;
         class1+=0.012195121951219513;
         class2+=0.975609756097561;
        }
 // TREE: 26
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 125.30192565917969)
      if (lumbar_lordosis_angle <= 49.223758697509766)
        {
         class0+=0.7115384615384616;
         class1+=0.21153846153846154;
         class2+=0.07692307692307693;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.14285714285714285;
         class2+=0.7142857142857143;
        }
    else
      if (pelvic_tilt <= 31.517855644226074)
        {
         class0+=0.15789473684210525;
         class1+=0.7368421052631579;
         class2+=0.10526315789473684;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 11.642083644866943)
      if (degree_spondylolisthesis <= 9.723408699035645)
        {
         class0+=0.038461538461538464;
         class1+=0.9230769230769231;
         class2+=0.038461538461538464;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 56.319414138793945)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.0;
         class1+=0.012195121951219513;
         class2+=0.9878048780487805;
        }
 // TREE: 27
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.12782287597656)
        {
         class0+=0.5428571428571428;
         class1+=0.37142857142857144;
         class2+=0.08571428571428572;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.8518518518518519;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_incidence <= 57.21945381164551)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 31.24958896636963)
        {
         class0+=0.0;
         class1+=0.07692307692307693;
         class2+=0.9230769230769231;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 28
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_tilt <= 41.976768493652344)
        {
         class0+=0.6666666666666666;
         class1+=0.21052631578947367;
         class2+=0.12280701754385964;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5;
         class1+=0.4;
         class2+=0.1;
        }
      else
        {
         class0+=0.03333333333333333;
         class1+=0.8;
         class2+=0.16666666666666666;
        }
  else
    if (degree_spondylolisthesis <= 11.642083644866943)
      if (pelvic_tilt <= 18.92229175567627)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.25;
         class1+=0.625;
         class2+=0.125;
        }
    else
      if (pelvic_incidence <= 56.319414138793945)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.0;
         class1+=0.012195121951219513;
         class2+=0.9878048780487805;
        }
 // TREE: 29
  if (pelvic_radius <= 110.68482971191406)
    if (degree_spondylolisthesis <= 19.410610675811768)
      if (pelvic_radius <= 77.16199493408203)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.8461538461538461;
         class1+=0.15384615384615385;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (lumbar_lordosis_angle <= 56.35000038146973)
      if (pelvic_radius <= 125.30192565917969)
        {
         class0+=0.4603174603174603;
         class1+=0.38095238095238093;
         class2+=0.15873015873015872;
        }
      else
        {
         class0+=0.1276595744680851;
         class1+=0.723404255319149;
         class2+=0.14893617021276595;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.06976744186046512;
         class2+=0.9302325581395349;
        }
 // TREE: 30
  if (sacral_slope <= 40.53826332092285)
    if (lumbar_lordosis_angle <= 50.677467346191406)
      if (pelvic_radius <= 125.44171905517578)
        {
         class0+=0.7115384615384616;
         class1+=0.21153846153846154;
         class2+=0.07692307692307693;
        }
      else
        {
         class0+=0.17647058823529413;
         class1+=0.7352941176470589;
         class2+=0.08823529411764706;
        }
    else
      if (pelvic_tilt <= 27.4694242477417)
        {
         class0+=0.125;
         class1+=0.5;
         class2+=0.375;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (degree_spondylolisthesis <= 8.581488609313965)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.0;
         class2+=0.8571428571428571;
        }
    else
      if (sacral_slope <= 53.0706672668457)
        {
         class0+=0.0196078431372549;
         class1+=0.23529411764705882;
         class2+=0.7450980392156863;
        }
      else
        {
         class0+=0.0;
         class1+=0.0425531914893617;
         class2+=0.9574468085106383;
        }
 // TREE: 31
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.4098360655737705;
         class1+=0.5737704918032787;
         class2+=0.01639344262295082;
        }
      else
        {
         class0+=0.07142857142857142;
         class1+=0.8571428571428571;
         class2+=0.07142857142857142;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_radius <= 136.77429962158203)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.045454545454545456;
         class2+=0.9545454545454546;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 32
  if (pelvic_radius <= 110.68482971191406)
    if (degree_spondylolisthesis <= 19.410610675811768)
      if (pelvic_incidence <= 69.3479118347168)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.25;
         class1+=0.5;
         class2+=0.25;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 67.35112380981445)
      if (degree_spondylolisthesis <= 15.030566215515137)
        {
         class0+=0.3645833333333333;
         class1+=0.625;
         class2+=0.010416666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.09523809523809523;
         class2+=0.9047619047619048;
        }
    else
      if (lumbar_lordosis_angle <= 59.12527084350586)
        {
         class0+=0.0;
         class1+=0.2;
         class2+=0.8;
        }
      else
        {
         class0+=0.0;
         class1+=0.029411764705882353;
         class2+=0.9705882352941176;
        }
 // TREE: 33
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (sacral_slope <= 28.13647174835205)
      if (degree_spondylolisthesis <= 7.179219961166382)
        {
         class0+=0.8571428571428571;
         class1+=0.14285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 76.84163284301758)
        {
         class0+=0.3125;
         class1+=0.5375;
         class2+=0.15;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_radius <= 119.18045425415039)
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 20.63097858428955)
        {
         class0+=0.25;
         class1+=0.25;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.022988505747126436;
         class2+=0.9770114942528736;
        }
 // TREE: 34
  if (pelvic_radius <= 110.68482971191406)
    if (degree_spondylolisthesis <= 19.410610675811768)
      if (sacral_slope <= 48.556976318359375)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.6666666666666666;
         class2+=0.3333333333333333;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 67.35112380981445)
      if (degree_spondylolisthesis <= 15.030566215515137)
        {
         class0+=0.3645833333333333;
         class1+=0.625;
         class2+=0.010416666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.09523809523809523;
         class2+=0.9047619047619048;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.04878048780487805;
         class2+=0.9512195121951219;
        }
 // TREE: 35
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.8181818181818182;
         class2+=0.09090909090909091;
        }
    else
      if (degree_spondylolisthesis <= 2.116147041320801)
        {
         class0+=0.038461538461538464;
         class1+=0.9615384615384616;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.6428571428571429;
         class2+=0.0;
        }
  else
    if (pelvic_tilt <= 14.86863899230957)
      if (pelvic_tilt <= 14.60332202911377)
        {
         class0+=0.0;
         class1+=0.05263157894736842;
         class2+=0.9473684210526315;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 36
  if (sacral_slope <= 40.53826332092285)
    if (degree_spondylolisthesis <= 18.047505855560303)
      if (degree_spondylolisthesis <= 1.2192070484161377)
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.6530612244897959;
         class1+=0.32653061224489793;
         class2+=0.02040816326530612;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 69.5125503540039)
      if (sacral_slope <= 53.330434799194336)
        {
         class0+=0.041666666666666664;
         class1+=0.5;
         class2+=0.4583333333333333;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 15.78461503982544)
        {
         class0+=0.0;
         class1+=0.14285714285714285;
         class2+=0.8571428571428571;
        }
      else
        {
         class0+=0.0;
         class1+=0.03773584905660377;
         class2+=0.9622641509433962;
        }
 // TREE: 37
  if (pelvic_incidence <= 56.49103927612305)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_tilt <= 17.340057373046875)
        {
         class0+=0.6428571428571429;
         class1+=0.35714285714285715;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 111.68848037719727)
        {
         class0+=0.42857142857142855;
         class1+=0.0;
         class2+=0.5714285714285714;
        }
      else
        {
         class0+=0.21212121212121213;
         class1+=0.6666666666666666;
         class2+=0.12121212121212122;
        }
  else
    if (degree_spondylolisthesis <= 11.642083644866943)
      if (sacral_slope <= 41.474557876586914)
        {
         class0+=0.625;
         class1+=0.25;
         class2+=0.125;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
    else
      if (lumbar_lordosis_angle <= 58.0201473236084)
        {
         class0+=0.041666666666666664;
         class1+=0.041666666666666664;
         class2+=0.9166666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 38
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.8181818181818182;
         class2+=0.09090909090909091;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
      else
        {
         class0+=0.03225806451612903;
         class1+=0.967741935483871;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (pelvic_radius <= 113.90714263916016)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.2;
         class2+=0.8;
        }
    else
      if (lumbar_lordosis_angle <= 58.0201473236084)
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 39
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (lumbar_lordosis_angle <= 29.128881454467773)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.4;
         class1+=0.6;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 10.883872032165527)
        {
         class0+=0.10714285714285714;
         class1+=0.8928571428571429;
         class2+=0.0;
        }
      else
        {
         class0+=0.43478260869565216;
         class1+=0.5217391304347826;
         class2+=0.043478260869565216;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (pelvic_incidence <= 55.807992935180664)
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (lumbar_lordosis_angle <= 58.0201473236084)
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 40
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.8181818181818182;
         class2+=0.09090909090909091;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
      else
        {
         class0+=0.03225806451612903;
         class1+=0.967741935483871;
         class2+=0.0;
        }
  else
    if (pelvic_tilt <= 14.86863899230957)
      if (lumbar_lordosis_angle <= 54.41946983337402)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.15;
         class2+=0.85;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 41
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (degree_spondylolisthesis <= 1.3626884818077087)
      if (pelvic_radius <= 121.49974060058594)
        {
         class0+=0.4583333333333333;
         class1+=0.5;
         class2+=0.041666666666666664;
        }
      else
        {
         class0+=0.07407407407407407;
         class1+=0.9259259259259259;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 59.66083908081055)
        {
         class0+=0.625;
         class1+=0.375;
         class2+=0.0;
        }
      else
        {
         class0+=0.21428571428571427;
         class1+=0.6428571428571429;
         class2+=0.14285714285714285;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (degree_spondylolisthesis <= 20.085037231445312)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
    else
      if (pelvic_radius <= 123.53171920776367)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.05;
         class2+=0.95;
        }
 // TREE: 42
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (pelvic_radius <= 125.44171905517578)
        {
         class0+=0.6333333333333333;
         class1+=0.36666666666666664;
         class2+=0.0;
        }
      else
        {
         class0+=0.1875;
         class1+=0.8125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (lumbar_lordosis_angle <= 49.59900093078613)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.05263157894736842;
         class1+=0.3684210526315789;
         class2+=0.5789473684210527;
        }
    else
      if (degree_spondylolisthesis <= 9.067076206207275)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.015625;
         class2+=0.984375;
        }
 // TREE: 43
  if (pelvic_incidence <= 56.49103927612305)
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (degree_spondylolisthesis <= 2.023559093475342)
        {
         class0+=0.2765957446808511;
         class1+=0.723404255319149;
         class2+=0.0;
        }
      else
        {
         class0+=0.6410256410256411;
         class1+=0.358974358974359;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 55.807992935180664)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
  else
    if (lumbar_lordosis_angle <= 47.53213119506836)
      if (pelvic_tilt <= 24.17318630218506)
        {
         class0+=0.08333333333333333;
         class1+=0.5833333333333334;
         class2+=0.3333333333333333;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 53.0706672668457)
        {
         class0+=0.04;
         class1+=0.18;
         class2+=0.78;
        }
      else
        {
         class0+=0.0;
         class1+=0.0425531914893617;
         class2+=0.9574468085106383;
        }
 // TREE: 44
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (sacral_slope <= 28.13647174835205)
      if (lumbar_lordosis_angle <= 34.5564079284668)
        {
         class0+=0.9230769230769231;
         class1+=0.07692307692307693;
         class2+=0.0;
        }
      else
        {
         class0+=0.6363636363636364;
         class1+=0.36363636363636365;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 112.92106246948242)
        {
         class0+=0.6470588235294118;
         class1+=0.058823529411764705;
         class2+=0.29411764705882354;
        }
      else
        {
         class0+=0.21212121212121213;
         class1+=0.6363636363636364;
         class2+=0.15151515151515152;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_radius <= 119.18045425415039)
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 56.547590255737305)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.012195121951219513;
         class1+=0.012195121951219513;
         class2+=0.975609756097561;
        }
 // TREE: 45
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (lumbar_lordosis_angle <= 29.128881454467773)
      if (pelvic_tilt <= 6.502035617828369)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.8666666666666667;
         class1+=0.13333333333333333;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 10.883872032165527)
        {
         class0+=0.10714285714285714;
         class1+=0.8928571428571429;
         class2+=0.0;
        }
      else
        {
         class0+=0.43478260869565216;
         class1+=0.5217391304347826;
         class2+=0.043478260869565216;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (degree_spondylolisthesis <= 20.085037231445312)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 46
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.12782287597656)
        {
         class0+=0.5428571428571428;
         class1+=0.37142857142857144;
         class2+=0.08571428571428572;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.8518518518518519;
         class2+=0.0;
        }
  else
    if (pelvic_radius <= 136.77429962158203)
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.04878048780487805;
         class2+=0.9512195121951219;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 7.746830224990845)
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 47
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (lumbar_lordosis_angle <= 29.128881454467773)
      if (degree_spondylolisthesis <= 20.762762784957886)
        {
         class0+=0.8125;
         class1+=0.1875;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 15.030566215515137)
        {
         class0+=0.40789473684210525;
         class1+=0.5921052631578947;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_radius <= 119.18045425415039)
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 43.32751655578613)
        {
         class0+=0.058823529411764705;
         class1+=0.11764705882352941;
         class2+=0.8235294117647058;
        }
      else
        {
         class0+=0.0;
         class1+=0.013513513513513514;
         class2+=0.9864864864864865;
        }
 // TREE: 48
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 76.84163284301758)
        {
         class0+=0.3125;
         class1+=0.5375;
         class2+=0.15;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (lumbar_lordosis_angle <= 51.30072784423828)
        {
         class0+=0.16666666666666666;
         class1+=0.5;
         class2+=0.3333333333333333;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 20.63097858428955)
        {
         class0+=0.25;
         class1+=0.25;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.022988505747126436;
         class2+=0.9770114942528736;
        }
 // TREE: 49
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (pelvic_tilt <= 17.583970069885254)
      if (pelvic_radius <= 113.8734245300293)
        {
         class0+=0.6666666666666666;
         class1+=0.06666666666666667;
         class2+=0.26666666666666666;
        }
      else
        {
         class0+=0.23333333333333334;
         class1+=0.6666666666666666;
         class2+=0.1;
        }
    else
      if (lumbar_lordosis_angle <= 47.53213119506836)
        {
         class0+=0.6666666666666666;
         class1+=0.23333333333333334;
         class2+=0.1;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (pelvic_radius <= 121.3623161315918)
      if (degree_spondylolisthesis <= 9.756257057189941)
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
      else
        {
         class0+=0.014705882352941176;
         class1+=0.014705882352941176;
         class2+=0.9705882352941176;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.7222222222222222;
         class2+=0.2777777777777778;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 50
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_tilt <= 17.340057373046875)
        {
         class0+=0.6428571428571429;
         class1+=0.35714285714285715;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 112.92106246948242)
        {
         class0+=0.6470588235294118;
         class1+=0.058823529411764705;
         class2+=0.29411764705882354;
        }
      else
        {
         class0+=0.21212121212121213;
         class1+=0.6363636363636364;
         class2+=0.15151515151515152;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_radius <= 119.18045425415039)
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 56.547590255737305)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.012195121951219513;
         class1+=0.012195121951219513;
         class2+=0.975609756097561;
        }
 // TREE: 51
  if (pelvic_incidence <= 56.49103927612305)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_tilt <= 17.340057373046875)
        {
         class0+=0.6428571428571429;
         class1+=0.35714285714285715;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 20.085037231445312)
        {
         class0+=0.31746031746031744;
         class1+=0.6825396825396826;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
  else
    if (lumbar_lordosis_angle <= 47.53213119506836)
      if (sacral_slope <= 40.9268741607666)
        {
         class0+=0.8333333333333334;
         class1+=0.16666666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.5454545454545454;
         class2+=0.36363636363636365;
        }
    else
      if (pelvic_incidence <= 69.5125503540039)
        {
         class0+=0.05405405405405406;
         class1+=0.21621621621621623;
         class2+=0.7297297297297297;
        }
      else
        {
         class0+=0.0;
         class1+=0.05;
         class2+=0.95;
        }
 // TREE: 52
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (lumbar_lordosis_angle <= 29.128881454467773)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.4;
         class1+=0.6;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 35.53619194030762)
        {
         class0+=0.5531914893617021;
         class1+=0.44680851063829785;
         class2+=0.0;
        }
      else
        {
         class0+=0.14;
         class1+=0.8;
         class2+=0.06;
        }
  else
    if (pelvic_radius <= 136.77429962158203)
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.04878048780487805;
         class2+=0.9512195121951219;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_incidence <= 53.21263122558594)
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 53
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_tilt <= 17.583970069885254)
      if (pelvic_incidence <= 49.899505615234375)
        {
         class0+=0.44;
         class1+=0.56;
         class2+=0.0;
        }
      else
        {
         class0+=0.08;
         class1+=0.88;
         class2+=0.04;
        }
    else
      if (sacral_slope <= 29.734612464904785)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.4074074074074074;
         class1+=0.5185185185185185;
         class2+=0.07407407407407407;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_radius <= 136.77429962158203)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.045454545454545456;
         class2+=0.9545454545454546;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 54
  if (sacral_slope <= 40.53826332092285)
    if (lumbar_lordosis_angle <= 50.677467346191406)
      if (pelvic_tilt <= 17.583970069885254)
        {
         class0+=0.39344262295081966;
         class1+=0.5081967213114754;
         class2+=0.09836065573770492;
        }
      else
        {
         class0+=0.76;
         class1+=0.2;
         class2+=0.04;
        }
    else
      if (degree_spondylolisthesis <= 22.443135738372803)
        {
         class0+=0.16666666666666666;
         class1+=0.6666666666666666;
         class2+=0.16666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (pelvic_tilt <= 22.42124843597412)
        {
         class0+=0.0;
         class1+=0.7647058823529411;
         class2+=0.23529411764705882;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.0;
         class2+=0.6666666666666666;
        }
    else
      if (pelvic_radius <= 121.77228927612305)
        {
         class0+=0.013888888888888888;
         class1+=0.06944444444444445;
         class2+=0.9166666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.34615384615384615;
         class2+=0.6538461538461539;
        }
 // TREE: 55
  if (pelvic_incidence <= 56.49103927612305)
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.782608695652174;
         class1+=0.21739130434782608;
         class2+=0.0;
        }
      else
        {
         class0+=0.31746031746031744;
         class1+=0.6825396825396826;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 55.807992935180664)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 69.5125503540039)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=0.3181818181818182;
         class1+=0.6363636363636364;
         class2+=0.045454545454545456;
        }
      else
        {
         class0+=0.0;
         class1+=0.03333333333333333;
         class2+=0.9666666666666667;
        }
    else
      if (degree_spondylolisthesis <= 9.075990915298462)
        {
         class0+=0.2;
         class1+=0.6;
         class2+=0.2;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 56
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (pelvic_tilt <= 17.583970069885254)
      if (lumbar_lordosis_angle <= 32.91078758239746)
        {
         class0+=0.5909090909090909;
         class1+=0.36363636363636365;
         class2+=0.045454545454545456;
        }
      else
        {
         class0+=0.20754716981132076;
         class1+=0.6226415094339622;
         class2+=0.16981132075471697;
        }
    else
      if (pelvic_radius <= 121.49222946166992)
        {
         class0+=0.8095238095238095;
         class1+=0.14285714285714285;
         class2+=0.047619047619047616;
        }
      else
        {
         class0+=0.2727272727272727;
         class1+=0.36363636363636365;
         class2+=0.36363636363636365;
        }
  else
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (sacral_slope <= 53.0706672668457)
        {
         class0+=0.0625;
         class1+=0.40625;
         class2+=0.53125;
        }
      else
        {
         class0+=0.0;
         class1+=0.1;
         class2+=0.9;
        }
    else
      if (pelvic_tilt <= 13.753094673156738)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.0;
         class1+=0.05084745762711865;
         class2+=0.9491525423728814;
        }
 // TREE: 57
  if (sacral_slope <= 40.53826332092285)
    if (degree_spondylolisthesis <= 18.047505855560303)
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.7916666666666666;
         class1+=0.20833333333333334;
         class2+=0.0;
        }
      else
        {
         class0+=0.4098360655737705;
         class1+=0.5737704918032787;
         class2+=0.01639344262295082;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (pelvic_incidence <= 67.90209197998047)
        {
         class0+=0.0;
         class1+=0.7222222222222222;
         class2+=0.2777777777777778;
        }
      else
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
    else
      if (lumbar_lordosis_angle <= 58.72928810119629)
        {
         class0+=0.030303030303030304;
         class1+=0.2727272727272727;
         class2+=0.696969696969697;
        }
      else
        {
         class0+=0.0;
         class1+=0.07692307692307693;
         class2+=0.9230769230769231;
        }
 // TREE: 58
  if (sacral_slope <= 40.53826332092285)
    if (degree_spondylolisthesis <= 18.047505855560303)
      if (degree_spondylolisthesis <= 1.2192070484161377)
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.6530612244897959;
         class1+=0.32653061224489793;
         class2+=0.02040816326530612;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (pelvic_tilt <= 22.42124843597412)
        {
         class0+=0.0;
         class1+=0.7647058823529411;
         class2+=0.23529411764705882;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.0;
         class2+=0.6666666666666666;
        }
    else
      if (pelvic_incidence <= 69.5125503540039)
        {
         class0+=0.02564102564102564;
         class1+=0.28205128205128205;
         class2+=0.6923076923076923;
        }
      else
        {
         class0+=0.0;
         class1+=0.05084745762711865;
         class2+=0.9491525423728814;
        }
 // TREE: 59
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_tilt <= 17.340057373046875)
        {
         class0+=0.6428571428571429;
         class1+=0.35714285714285715;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.12782287597656)
        {
         class0+=0.5428571428571428;
         class1+=0.37142857142857144;
         class2+=0.08571428571428572;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.8518518518518519;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (pelvic_radius <= 113.90714263916016)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.2;
         class2+=0.8;
        }
    else
      if (lumbar_lordosis_angle <= 58.0201473236084)
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 60
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (lumbar_lordosis_angle <= 34.5564079284668)
        {
         class0+=0.9230769230769231;
         class1+=0.07692307692307693;
         class2+=0.0;
        }
      else
        {
         class0+=0.6363636363636364;
         class1+=0.36363636363636365;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.12782287597656)
        {
         class0+=0.5428571428571428;
         class1+=0.37142857142857144;
         class2+=0.08571428571428572;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.8518518518518519;
         class2+=0.0;
        }
  else
    if (pelvic_radius <= 136.77429962158203)
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.05555555555555555;
         class2+=0.9444444444444444;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (sacral_slope <= 39.426496505737305)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
 // TREE: 61
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_radius <= 111.27699661254883)
        {
         class0+=0.7647058823529411;
         class1+=0.17647058823529413;
         class2+=0.058823529411764705;
        }
      else
        {
         class0+=0.48214285714285715;
         class1+=0.48214285714285715;
         class2+=0.03571428571428571;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
      else
        {
         class0+=0.03225806451612903;
         class1+=0.967741935483871;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_radius <= 136.77429962158203)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 31.24958896636963)
        {
         class0+=0.0;
         class1+=0.07692307692307693;
         class2+=0.9230769230769231;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 62
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (degree_spondylolisthesis <= 3.3239283561706543)
        {
         class0+=0.3548387096774194;
         class1+=0.6451612903225806;
         class2+=0.0;
        }
      else
        {
         class0+=0.7333333333333333;
         class1+=0.26666666666666666;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (sacral_slope <= 53.0706672668457)
      if (degree_spondylolisthesis <= 20.63097858428955)
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
      else
        {
         class0+=0.0;
         class1+=0.0425531914893617;
         class2+=0.9574468085106383;
        }
    else
      if (pelvic_radius <= 112.84704208374023)
        {
         class0+=0.0;
         class1+=0.08695652173913043;
         class2+=0.9130434782608695;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 63
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (degree_spondylolisthesis <= 1.3626884818077087)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.7;
         class1+=0.3;
         class2+=0.0;
        }
      else
        {
         class0+=0.14634146341463414;
         class1+=0.8292682926829268;
         class2+=0.024390243902439025;
        }
    else
      if (pelvic_radius <= 120.5997314453125)
        {
         class0+=0.696969696969697;
         class1+=0.24242424242424243;
         class2+=0.06060606060606061;
        }
      else
        {
         class0+=0.3448275862068966;
         class1+=0.6551724137931034;
         class2+=0.0;
        }
  else
    if (pelvic_radius <= 136.77429962158203)
      if (degree_spondylolisthesis <= 31.24958896636963)
        {
         class0+=0.0;
         class1+=0.07407407407407407;
         class2+=0.9259259259259259;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 7.746830224990845)
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 64
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (sacral_slope <= 36.027883529663086)
        {
         class0+=0.6166666666666667;
         class1+=0.38333333333333336;
         class2+=0.0;
        }
      else
        {
         class0+=0.21875;
         class1+=0.78125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 63.123300552368164)
      if (sacral_slope <= 39.47086524963379)
        {
         class0+=0.1;
         class1+=0.6;
         class2+=0.3;
        }
      else
        {
         class0+=0.0;
         class1+=0.3;
         class2+=0.7;
        }
    else
      if (degree_spondylolisthesis <= 6.54765772819519)
        {
         class0+=0.125;
         class1+=0.75;
         class2+=0.125;
        }
      else
        {
         class0+=0.0;
         class1+=0.013888888888888888;
         class2+=0.9861111111111112;
        }
 // TREE: 65
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 125.30192565917969)
      if (lumbar_lordosis_angle <= 49.223758697509766)
        {
         class0+=0.7115384615384616;
         class1+=0.21153846153846154;
         class2+=0.07692307692307693;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.14285714285714285;
         class2+=0.7142857142857143;
        }
    else
      if (pelvic_radius <= 144.0055694580078)
        {
         class0+=0.16216216216216217;
         class1+=0.7567567567567568;
         class2+=0.08108108108108109;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (pelvic_incidence <= 67.90209197998047)
        {
         class0+=0.0;
         class1+=0.7222222222222222;
         class2+=0.2777777777777778;
        }
      else
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
    else
      if (degree_spondylolisthesis <= 10.153969287872314)
        {
         class0+=0.07692307692307693;
         class1+=0.8461538461538461;
         class2+=0.07692307692307693;
        }
      else
        {
         class0+=0.0;
         class1+=0.03529411764705882;
         class2+=0.9647058823529412;
        }
 // TREE: 66
  if (pelvic_radius <= 110.68482971191406)
    if (degree_spondylolisthesis <= 19.410610675811768)
      if (sacral_slope <= 48.556976318359375)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.6666666666666666;
         class2+=0.3333333333333333;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 67.35112380981445)
      if (pelvic_tilt <= 14.746737003326416)
        {
         class0+=0.19117647058823528;
         class1+=0.6470588235294118;
         class2+=0.16176470588235295;
        }
      else
        {
         class0+=0.4489795918367347;
         class1+=0.3673469387755102;
         class2+=0.1836734693877551;
        }
    else
      if (degree_spondylolisthesis <= 26.451024055480957)
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 67
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_tilt <= 27.4694242477417)
      if (pelvic_radius <= 125.30192565917969)
        {
         class0+=0.6666666666666666;
         class1+=0.2222222222222222;
         class2+=0.1111111111111111;
        }
      else
        {
         class0+=0.13513513513513514;
         class1+=0.7567567567567568;
         class2+=0.10810810810810811;
        }
    else
      if (sacral_slope <= 33.40303039550781)
        {
         class0+=0.6;
         class1+=0.0;
         class2+=0.4;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 11.642083644866943)
      if (pelvic_radius <= 102.72551727294922)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.038461538461538464;
         class1+=0.9230769230769231;
         class2+=0.038461538461538464;
        }
    else
      if (sacral_slope <= 43.32751655578613)
        {
         class0+=0.0;
         class1+=0.16666666666666666;
         class2+=0.8333333333333334;
        }
      else
        {
         class0+=0.0;
         class1+=0.012658227848101266;
         class2+=0.9873417721518988;
        }
 // TREE: 68
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (degree_spondylolisthesis <= 1.3626884818077087)
      if (pelvic_radius <= 121.49974060058594)
        {
         class0+=0.4583333333333333;
         class1+=0.5;
         class2+=0.041666666666666664;
        }
      else
        {
         class0+=0.07407407407407407;
         class1+=0.9259259259259259;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 120.5997314453125)
        {
         class0+=0.696969696969697;
         class1+=0.24242424242424243;
         class2+=0.06060606060606061;
        }
      else
        {
         class0+=0.3448275862068966;
         class1+=0.6551724137931034;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (lumbar_lordosis_angle <= 54.25)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 69
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_tilt <= 10.72407579421997)
      if (pelvic_radius <= 116.0182876586914)
        {
         class0+=0.42857142857142855;
         class1+=0.07142857142857142;
         class2+=0.5;
        }
      else
        {
         class0+=0.06060606060606061;
         class1+=0.7575757575757576;
         class2+=0.18181818181818182;
        }
    else
      if (pelvic_incidence <= 53.62777519226074)
        {
         class0+=0.6086956521739131;
         class1+=0.34782608695652173;
         class2+=0.043478260869565216;
        }
      else
        {
         class0+=0.2;
         class1+=0.7;
         class2+=0.1;
        }
  else
    if (degree_spondylolisthesis <= 11.642083644866943)
      if (sacral_slope <= 41.474557876586914)
        {
         class0+=0.625;
         class1+=0.25;
         class2+=0.125;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
    else
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.011627906976744186;
         class2+=0.9883720930232558;
        }
 // TREE: 70
  if (sacral_slope <= 40.53826332092285)
    if (lumbar_lordosis_angle <= 50.677467346191406)
      if (degree_spondylolisthesis <= 16.999180793762207)
        {
         class0+=0.5443037974683544;
         class1+=0.45569620253164556;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_incidence <= 64.0407886505127)
        {
         class0+=0.1111111111111111;
         class1+=0.4444444444444444;
         class2+=0.4444444444444444;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (pelvic_incidence <= 69.5125503540039)
      if (lumbar_lordosis_angle <= 48.068403244018555)
        {
         class0+=0.05;
         class1+=0.7;
         class2+=0.25;
        }
      else
        {
         class0+=0.02631578947368421;
         class1+=0.2631578947368421;
         class2+=0.7105263157894737;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.0;
         class1+=0.125;
         class2+=0.875;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 71
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (lumbar_lordosis_angle <= 29.128881454467773)
      if (degree_spondylolisthesis <= 1.4012479186058044)
        {
         class0+=0.5714285714285714;
         class1+=0.42857142857142855;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 125.30192565917969)
        {
         class0+=0.46774193548387094;
         class1+=0.4838709677419355;
         class2+=0.04838709677419355;
        }
      else
        {
         class0+=0.11428571428571428;
         class1+=0.8857142857142857;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_radius <= 136.77429962158203)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (lumbar_lordosis_angle <= 62.66514015197754)
        {
         class0+=0.0;
         class1+=0.04;
         class2+=0.96;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 72
  if (pelvic_incidence <= 56.49103927612305)
    if (lumbar_lordosis_angle <= 42.77910232543945)
      if (degree_spondylolisthesis <= 1.2192070484161377)
        {
         class0+=0.2903225806451613;
         class1+=0.7096774193548387;
         class2+=0.0;
        }
      else
        {
         class0+=0.6585365853658537;
         class1+=0.24390243902439024;
         class2+=0.0975609756097561;
        }
    else
      if (pelvic_incidence <= 54.04203224182129)
        {
         class0+=0.08695652173913043;
         class1+=0.43478260869565216;
         class2+=0.4782608695652174;
        }
      else
        {
         class0+=0.0;
         class1+=0.875;
         class2+=0.125;
        }
  else
    if (degree_spondylolisthesis <= 11.642083644866943)
      if (sacral_slope <= 41.474557876586914)
        {
         class0+=0.625;
         class1+=0.25;
         class2+=0.125;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
    else
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.011627906976744186;
         class2+=0.9883720930232558;
        }
 // TREE: 73
  if (sacral_slope <= 40.53826332092285)
    if (lumbar_lordosis_angle <= 50.677467346191406)
      if (pelvic_radius <= 125.44171905517578)
        {
         class0+=0.7115384615384616;
         class1+=0.21153846153846154;
         class2+=0.07692307692307693;
        }
      else
        {
         class0+=0.17647058823529413;
         class1+=0.7352941176470589;
         class2+=0.08823529411764706;
        }
    else
      if (lumbar_lordosis_angle <= 55.60000038146973)
        {
         class0+=0.1111111111111111;
         class1+=0.4444444444444444;
         class2+=0.4444444444444444;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (pelvic_incidence <= 69.5125503540039)
      if (lumbar_lordosis_angle <= 48.068403244018555)
        {
         class0+=0.05;
         class1+=0.7;
         class2+=0.25;
        }
      else
        {
         class0+=0.02631578947368421;
         class1+=0.2631578947368421;
         class2+=0.7105263157894737;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.0;
         class1+=0.125;
         class2+=0.875;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 74
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (pelvic_tilt <= 17.583970069885254)
      if (degree_spondylolisthesis <= 16.999180793762207)
        {
         class0+=0.36923076923076925;
         class1+=0.6307692307692307;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_incidence <= 76.84163284301758)
        {
         class0+=0.6896551724137931;
         class1+=0.2413793103448276;
         class2+=0.06896551724137931;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_tilt <= 18.92229175567627)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
    else
      if (pelvic_incidence <= 56.547590255737305)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.012195121951219513;
         class1+=0.012195121951219513;
         class2+=0.975609756097561;
        }
 // TREE: 75
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (pelvic_radius <= 125.44171905517578)
        {
         class0+=0.6333333333333333;
         class1+=0.36666666666666664;
         class2+=0.0;
        }
      else
        {
         class0+=0.1875;
         class1+=0.8125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_tilt <= 18.92229175567627)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
    else
      if (sacral_slope <= 43.32751655578613)
        {
         class0+=0.058823529411764705;
         class1+=0.11764705882352941;
         class2+=0.8235294117647058;
        }
      else
        {
         class0+=0.0;
         class1+=0.013513513513513514;
         class2+=0.9864864864864865;
        }
 // TREE: 76
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 125.30192565917969)
      if (lumbar_lordosis_angle <= 49.223758697509766)
        {
         class0+=0.7115384615384616;
         class1+=0.21153846153846154;
         class2+=0.07692307692307693;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.14285714285714285;
         class2+=0.7142857142857143;
        }
    else
      if (pelvic_radius <= 144.0055694580078)
        {
         class0+=0.16216216216216217;
         class1+=0.7567567567567568;
         class2+=0.08108108108108109;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (lumbar_lordosis_angle <= 47.28213119506836)
      if (degree_spondylolisthesis <= 8.581488609313965)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.0;
         class2+=0.8571428571428571;
        }
    else
      if (degree_spondylolisthesis <= 10.153969287872314)
        {
         class0+=0.07692307692307693;
         class1+=0.8461538461538461;
         class2+=0.07692307692307693;
        }
      else
        {
         class0+=0.0;
         class1+=0.03529411764705882;
         class2+=0.9647058823529412;
        }
 // TREE: 77
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (lumbar_lordosis_angle <= 29.128881454467773)
      if (pelvic_tilt <= 6.502035617828369)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.8666666666666667;
         class1+=0.13333333333333333;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 35.53619194030762)
        {
         class0+=0.5531914893617021;
         class1+=0.44680851063829785;
         class2+=0.0;
        }
      else
        {
         class0+=0.14;
         class1+=0.8;
         class2+=0.06;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (lumbar_lordosis_angle <= 54.25)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
    else
      if (degree_spondylolisthesis <= 26.451024055480957)
        {
         class0+=0.0;
         class1+=0.125;
         class2+=0.875;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 78
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (sacral_slope <= 36.027883529663086)
        {
         class0+=0.6166666666666667;
         class1+=0.38333333333333336;
         class2+=0.0;
        }
      else
        {
         class0+=0.21875;
         class1+=0.78125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_incidence <= 64.01713180541992)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.6666666666666666;
         class2+=0.2222222222222222;
        }
    else
      if (lumbar_lordosis_angle <= 62.66514015197754)
        {
         class0+=0.025;
         class1+=0.075;
         class2+=0.9;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 79
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (pelvic_tilt <= 17.583970069885254)
      if (pelvic_radius <= 113.8734245300293)
        {
         class0+=0.6666666666666666;
         class1+=0.06666666666666667;
         class2+=0.26666666666666666;
        }
      else
        {
         class0+=0.23333333333333334;
         class1+=0.6666666666666666;
         class2+=0.1;
        }
    else
      if (degree_spondylolisthesis <= 13.794804573059082)
        {
         class0+=0.7407407407407407;
         class1+=0.25925925925925924;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (pelvic_radius <= 121.91911315917969)
        {
         class0+=0.07407407407407407;
         class1+=0.1111111111111111;
         class2+=0.8148148148148148;
        }
      else
        {
         class0+=0.0;
         class1+=0.7333333333333333;
         class2+=0.26666666666666666;
        }
    else
      if (sacral_slope <= 43.16859245300293)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.0;
         class1+=0.05084745762711865;
         class2+=0.9491525423728814;
        }
 // TREE: 80
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_radius <= 111.27699661254883)
        {
         class0+=0.7647058823529411;
         class1+=0.17647058823529413;
         class2+=0.058823529411764705;
        }
      else
        {
         class0+=0.48214285714285715;
         class1+=0.48214285714285715;
         class2+=0.03571428571428571;
        }
    else
      if (lumbar_lordosis_angle <= 37.900146484375)
        {
         class0+=0.3;
         class1+=0.7;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
  else
    if (pelvic_tilt <= 14.86863899230957)
      if (pelvic_radius <= 115.87165069580078)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.2727272727272727;
         class2+=0.7272727272727273;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 81
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (sacral_slope <= 36.027883529663086)
        {
         class0+=0.6166666666666667;
         class1+=0.38333333333333336;
         class2+=0.0;
        }
      else
        {
         class0+=0.21875;
         class1+=0.78125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 63.123300552368164)
      if (sacral_slope <= 39.47086524963379)
        {
         class0+=0.1;
         class1+=0.6;
         class2+=0.3;
        }
      else
        {
         class0+=0.0;
         class1+=0.3;
         class2+=0.7;
        }
    else
      if (pelvic_incidence <= 69.5125503540039)
        {
         class0+=0.045454545454545456;
         class1+=0.18181818181818182;
         class2+=0.7727272727272727;
        }
      else
        {
         class0+=0.0;
         class1+=0.05172413793103448;
         class2+=0.9482758620689655;
        }
 // TREE: 82
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (pelvic_radius <= 125.44171905517578)
      if (pelvic_incidence <= 49.899505615234375)
        {
         class0+=0.7142857142857143;
         class1+=0.17142857142857143;
         class2+=0.11428571428571428;
        }
      else
        {
         class0+=0.37142857142857144;
         class1+=0.45714285714285713;
         class2+=0.17142857142857143;
        }
    else
      if (lumbar_lordosis_angle <= 44.31545639038086)
        {
         class0+=0.20689655172413793;
         class1+=0.7586206896551724;
         class2+=0.034482758620689655;
        }
      else
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_tilt <= 18.92229175567627)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
    else
      if (sacral_slope <= 43.32751655578613)
        {
         class0+=0.058823529411764705;
         class1+=0.11764705882352941;
         class2+=0.8235294117647058;
        }
      else
        {
         class0+=0.0;
         class1+=0.013513513513513514;
         class2+=0.9864864864864865;
        }
 // TREE: 83
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.8181818181818182;
         class2+=0.09090909090909091;
        }
    else
      if (degree_spondylolisthesis <= 2.116147041320801)
        {
         class0+=0.038461538461538464;
         class1+=0.9615384615384616;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.6428571428571429;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_radius <= 136.77429962158203)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 116.20321655273438)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
 // TREE: 84
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (pelvic_radius <= 125.64007568359375)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 9.623145580291748)
        {
         class0+=0.12;
         class1+=0.88;
         class2+=0.0;
        }
      else
        {
         class0+=0.375;
         class1+=0.578125;
         class2+=0.046875;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_incidence <= 57.21945381164551)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.05405405405405406;
         class2+=0.9459459459459459;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 85
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (pelvic_radius <= 125.44171905517578)
      if (pelvic_incidence <= 49.899505615234375)
        {
         class0+=0.7142857142857143;
         class1+=0.17142857142857143;
         class2+=0.11428571428571428;
        }
      else
        {
         class0+=0.37142857142857144;
         class1+=0.45714285714285713;
         class2+=0.17142857142857143;
        }
    else
      if (pelvic_tilt <= 23.426228523254395)
        {
         class0+=0.14705882352941177;
         class1+=0.7647058823529411;
         class2+=0.08823529411764706;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.0;
         class2+=0.6666666666666666;
        }
  else
    if (degree_spondylolisthesis <= 11.257752895355225)
      if (pelvic_radius <= 119.18045425415039)
        {
         class0+=0.125;
         class1+=0.625;
         class2+=0.25;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 43.32751655578613)
        {
         class0+=0.058823529411764705;
         class1+=0.11764705882352941;
         class2+=0.8235294117647058;
        }
      else
        {
         class0+=0.0;
         class1+=0.013513513513513514;
         class2+=0.9864864864864865;
        }
 // TREE: 86
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_tilt <= 27.4694242477417)
      if (degree_spondylolisthesis <= 18.047505855560303)
        {
         class0+=0.5061728395061729;
         class1+=0.49382716049382713;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_incidence <= 53.86601448059082)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.2857142857142857;
         class1+=0.0;
         class2+=0.7142857142857143;
        }
  else
    if (sacral_slope <= 53.330434799194336)
      if (lumbar_lordosis_angle <= 48.068403244018555)
        {
         class0+=0.05;
         class1+=0.7;
         class2+=0.25;
        }
      else
        {
         class0+=0.019230769230769232;
         class1+=0.21153846153846154;
         class2+=0.7692307692307693;
        }
    else
      if (degree_spondylolisthesis <= 9.067076206207275)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 87
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_radius <= 111.27699661254883)
        {
         class0+=0.7647058823529411;
         class1+=0.17647058823529413;
         class2+=0.058823529411764705;
        }
      else
        {
         class0+=0.48214285714285715;
         class1+=0.48214285714285715;
         class2+=0.03571428571428571;
        }
    else
      if (pelvic_incidence <= 35.79051399230957)
        {
         class0+=0.6;
         class1+=0.4;
         class2+=0.0;
        }
      else
        {
         class0+=0.08571428571428572;
         class1+=0.9142857142857143;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (pelvic_incidence <= 55.807992935180664)
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 26.451024055480957)
        {
         class0+=0.0;
         class1+=0.125;
         class2+=0.875;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 88
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (degree_spondylolisthesis <= 7.179219961166382)
        {
         class0+=0.8571428571428571;
         class1+=0.14285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 3.39676570892334)
        {
         class0+=0.17543859649122806;
         class1+=0.8070175438596491;
         class2+=0.017543859649122806;
        }
      else
        {
         class0+=0.53125;
         class1+=0.40625;
         class2+=0.0625;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (pelvic_incidence <= 55.807992935180664)
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 26.451024055480957)
        {
         class0+=0.0;
         class1+=0.125;
         class2+=0.875;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 89
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.8181818181818182;
         class2+=0.09090909090909091;
        }
    else
      if (degree_spondylolisthesis <= 2.116147041320801)
        {
         class0+=0.038461538461538464;
         class1+=0.9615384615384616;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.6428571428571429;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_radius <= 136.77429962158203)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (lumbar_lordosis_angle <= 62.66514015197754)
        {
         class0+=0.0;
         class1+=0.04;
         class2+=0.96;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 90
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (degree_spondylolisthesis <= 3.3239283561706543)
        {
         class0+=0.3548387096774194;
         class1+=0.6451612903225806;
         class2+=0.0;
        }
      else
        {
         class0+=0.7333333333333333;
         class1+=0.26666666666666666;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (sacral_slope <= 53.0706672668457)
        {
         class0+=0.0625;
         class1+=0.40625;
         class2+=0.53125;
        }
      else
        {
         class0+=0.0;
         class1+=0.1;
         class2+=0.9;
        }
    else
      if (lumbar_lordosis_angle <= 75.99925994873047)
        {
         class0+=0.0;
         class1+=0.11904761904761904;
         class2+=0.8809523809523809;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 91
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (pelvic_radius <= 125.44171905517578)
      if (pelvic_radius <= 111.43204879760742)
        {
         class0+=0.6875;
         class1+=0.0;
         class2+=0.3125;
        }
      else
        {
         class0+=0.5;
         class1+=0.4074074074074074;
         class2+=0.09259259259259259;
        }
    else
      if (pelvic_tilt <= 23.426228523254395)
        {
         class0+=0.14705882352941177;
         class1+=0.7647058823529411;
         class2+=0.08823529411764706;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.0;
         class2+=0.6666666666666666;
        }
  else
    if (sacral_slope <= 53.0706672668457)
      if (degree_spondylolisthesis <= 20.63097858428955)
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
      else
        {
         class0+=0.0;
         class1+=0.0425531914893617;
         class2+=0.9574468085106383;
        }
    else
      if (pelvic_radius <= 112.84704208374023)
        {
         class0+=0.0;
         class1+=0.08695652173913043;
         class2+=0.9130434782608695;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 92
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (pelvic_incidence <= 49.899505615234375)
        {
         class0+=0.8064516129032258;
         class1+=0.1935483870967742;
         class2+=0.0;
        }
      else
        {
         class0+=0.35714285714285715;
         class1+=0.5714285714285714;
         class2+=0.07142857142857142;
        }
    else
      if (sacral_slope <= 28.13647174835205)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
         class2+=0.0;
        }
      else
        {
         class0+=0.03225806451612903;
         class1+=0.967741935483871;
         class2+=0.0;
        }
  else
    if (pelvic_tilt <= 14.86863899230957)
      if (lumbar_lordosis_angle <= 54.41946983337402)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.15;
         class2+=0.85;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 93
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (pelvic_radius <= 125.30192565917969)
      if (sacral_slope <= 41.109004974365234)
        {
         class0+=0.7450980392156863;
         class1+=0.23529411764705882;
         class2+=0.0196078431372549;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.8181818181818182;
         class2+=0.09090909090909091;
        }
    else
      if (pelvic_tilt <= 26.227545738220215)
        {
         class0+=0.1282051282051282;
         class1+=0.8717948717948718;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
  else
    if (lumbar_lordosis_angle <= 62.66514015197754)
      if (pelvic_radius <= 115.87165069580078)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.13043478260869565;
         class2+=0.8695652173913043;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 94
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (degree_spondylolisthesis <= 1.3626884818077087)
      if (pelvic_radius <= 121.49974060058594)
        {
         class0+=0.4583333333333333;
         class1+=0.5;
         class2+=0.041666666666666664;
        }
      else
        {
         class0+=0.07407407407407407;
         class1+=0.9259259259259259;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 120.5997314453125)
        {
         class0+=0.696969696969697;
         class1+=0.24242424242424243;
         class2+=0.06060606060606061;
        }
      else
        {
         class0+=0.3448275862068966;
         class1+=0.6551724137931034;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 56.319414138793945)
      if (pelvic_incidence <= 55.807992935180664)
        {
         class0+=0.0;
         class1+=0.058823529411764705;
         class2+=0.9411764705882353;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 95
  if (sacral_slope <= 40.53826332092285)
    if (degree_spondylolisthesis <= 18.047505855560303)
      if (pelvic_tilt <= 21.710060119628906)
        {
         class0+=0.4647887323943662;
         class1+=0.5352112676056338;
         class2+=0.0;
        }
      else
        {
         class0+=0.7857142857142857;
         class1+=0.14285714285714285;
         class2+=0.07142857142857142;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_radius <= 119.32738494873047)
      if (lumbar_lordosis_angle <= 47.568403244018555)
        {
         class0+=0.1;
         class1+=0.5;
         class2+=0.4;
        }
      else
        {
         class0+=0.014705882352941176;
         class1+=0.07352941176470588;
         class2+=0.9117647058823529;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.0;
         class1+=0.8095238095238095;
         class2+=0.19047619047619047;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 96
  if (pelvic_incidence <= 56.49103927612305)
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (degree_spondylolisthesis <= 2.023559093475342)
        {
         class0+=0.2765957446808511;
         class1+=0.723404255319149;
         class2+=0.0;
        }
      else
        {
         class0+=0.6410256410256411;
         class1+=0.358974358974359;
         class2+=0.0;
        }
    else
      if (lumbar_lordosis_angle <= 60.49077033996582)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
  else
    if (pelvic_incidence <= 69.5125503540039)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=0.3181818181818182;
         class1+=0.6363636363636364;
         class2+=0.045454545454545456;
        }
      else
        {
         class0+=0.0;
         class1+=0.03333333333333333;
         class2+=0.9666666666666667;
        }
    else
      if (sacral_slope <= 33.40303039550781)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.04918032786885246;
         class2+=0.9508196721311475;
        }
 // TREE: 97
  if (degree_spondylolisthesis <= 16.078891277313232)
    if (sacral_slope <= 28.13647174835205)
      if (degree_spondylolisthesis <= 7.179219961166382)
        {
         class0+=0.8571428571428571;
         class1+=0.14285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 3.39676570892334)
        {
         class0+=0.17543859649122806;
         class1+=0.8070175438596491;
         class2+=0.017543859649122806;
        }
      else
        {
         class0+=0.53125;
         class1+=0.40625;
         class2+=0.0625;
        }
  else
    if (degree_spondylolisthesis <= 20.085037231445312)
      if (pelvic_incidence <= 57.21945381164551)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 31.24958896636963)
        {
         class0+=0.0;
         class1+=0.07692307692307693;
         class2+=0.9230769230769231;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 98
  if (pelvic_radius <= 110.68482971191406)
    if (sacral_slope <= 41.11776542663574)
      if (degree_spondylolisthesis <= 21.71998643875122)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 11.642083644866943)
        {
         class0+=0.5;
         class1+=0.5;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (sacral_slope <= 39.99390411376953)
      if (degree_spondylolisthesis <= 16.999180793762207)
        {
         class0+=0.4666666666666667;
         class1+=0.52;
         class2+=0.013333333333333334;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 12.306690216064453)
        {
         class0+=0.0;
         class1+=0.9583333333333334;
         class2+=0.041666666666666664;
        }
      else
        {
         class0+=0.0;
         class1+=0.057692307692307696;
         class2+=0.9423076923076923;
        }
 // TREE: 99
  if (lumbar_lordosis_angle <= 48.1184024810791)
    if (degree_spondylolisthesis <= 15.030566215515137)
      if (pelvic_radius <= 125.44171905517578)
        {
         class0+=0.6333333333333333;
         class1+=0.36666666666666664;
         class2+=0.0;
        }
      else
        {
         class0+=0.1875;
         class1+=0.8125;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_radius <= 121.3623161315918)
      if (pelvic_radius <= 103.31562042236328)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.037037037037037035;
         class1+=0.1111111111111111;
         class2+=0.8518518518518519;
        }
    else
      if (degree_spondylolisthesis <= 27.53839111328125)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  // VOTER
int classification = -1;
  if( class0>=class1 && class0>=class2)
    classification = Hernia;
  if( class1>=class0 && class1>=class2)
    classification = Normal;
  if( class2>=class0 && class2>=class1)
    classification = Spondylolisthesis;
 return classification;
}
}
