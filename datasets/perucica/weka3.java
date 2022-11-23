class RandomForest { 

  //@ semantics "patients who had degenerative spondylolithesis", [NN, NNPS, NNS, NNP], 1, [*], \result == 1
  //@ semantics "patients who had degenerative sp", [NN, NNPS, NNS, NNP], 1, [*], \result == 1
  //@ semantics "have", [VBD], 2, [(Subj), (Acc)], (Subj) ==> (Acc)
  //@ semantics "angle", [NN], 1, [*], lumbar_lordosis_angle
  //@ ensures (*All patients who had degenerative spondylolithesis had an angle from 52.6 to 62.1.*);
public int randomForest(double pelvic_incidence,double pelvic_tilt,double lumbar_lordosis_angle,double sacral_slope,double pelvic_radius,double degree_spondylolisthesis){
double  class0 = 0;
double  class1 = 0;
double  class2 = 0;
 // TREE: 0
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (degree_spondylolisthesis <= 6.924067258834839)
        {
         class0+=0.8260869565217391;
         class1+=0.17391304347826086;
         class2+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 10.707735538482666)
        {
         class0+=0.08333333333333333;
         class1+=0.9166666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.3275862068965517;
         class1+=0.6206896551724138;
         class2+=0.05172413793103448;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 1
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (sacral_slope <= 35.553558349609375)
        {
         class0+=0.7631578947368421;
         class1+=0.23684210526315788;
         class2+=0.0;
        }
      else
        {
         class0+=0.24324324324324326;
         class1+=0.6756756756756757;
         class2+=0.08108108108108109;
        }
    else
      if (sacral_slope <= 20.55422878265381)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.03333333333333333;
         class1+=0.9666666666666667;
         class2+=0.0;
        }
  else
    if (lumbar_lordosis_angle <= 62.66514015197754)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0392156862745098;
         class2+=0.9607843137254902;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 2
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (degree_spondylolisthesis <= 5.387938022613525)
        {
         class0+=0.40298507462686567;
         class1+=0.5970149253731343;
         class2+=0.0;
        }
      else
        {
         class0+=0.7692307692307693;
         class1+=0.23076923076923078;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_radius <= 112.11275100708008)
        {
         class0+=0.045454545454545456;
         class1+=0.09090909090909091;
         class2+=0.8636363636363636;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.5555555555555556;
         class2+=0.2962962962962963;
        }
    else
      if (sacral_slope <= 52.95919609069824)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.125;
         class2+=0.875;
        }
 // TREE: 3
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_tilt <= 17.217758178710938)
      if (sacral_slope <= 40.80160331726074)
        {
         class0+=0.3404255319148936;
         class1+=0.6382978723404256;
         class2+=0.02127659574468085;
        }
      else
        {
         class0+=0.0;
         class1+=0.5384615384615384;
         class2+=0.46153846153846156;
        }
    else
      if (pelvic_incidence <= 55.399030685424805)
        {
         class0+=0.8333333333333334;
         class1+=0.16666666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.46153846153846156;
         class1+=0.23076923076923078;
         class2+=0.3076923076923077;
        }
  else
    if (pelvic_tilt <= 30.175579071044922)
      if (sacral_slope <= 43.76664161682129)
        {
         class0+=0.25;
         class1+=0.5;
         class2+=0.25;
        }
      else
        {
         class0+=0.0;
         class1+=0.16455696202531644;
         class2+=0.8354430379746836;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 4
  if (pelvic_incidence <= 56.49103927612305)
    if (sacral_slope <= 28.01943588256836)
      if (sacral_slope <= 23.494050979614258)
        {
         class0+=0.5833333333333334;
         class1+=0.4166666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.9230769230769231;
         class1+=0.07692307692307693;
         class2+=0.0;
        }
    else
      if (lumbar_lordosis_angle <= 42.783695220947266)
        {
         class0+=0.325;
         class1+=0.625;
         class2+=0.05;
        }
      else
        {
         class0+=0.045454545454545456;
         class1+=0.5909090909090909;
         class2+=0.36363636363636365;
        }
  else
    if (sacral_slope <= 44.822065353393555)
      if (pelvic_tilt <= 32.02567481994629)
        {
         class0+=0.36363636363636365;
         class1+=0.3181818181818182;
         class2+=0.3181818181818182;
        }
      else
        {
         class0+=0.07142857142857142;
         class1+=0.0;
         class2+=0.9285714285714286;
        }
    else
      if (lumbar_lordosis_angle <= 58.72928810119629)
        {
         class0+=0.0;
         class1+=0.3125;
         class2+=0.6875;
        }
      else
        {
         class0+=0.0;
         class1+=0.08064516129032258;
         class2+=0.9193548387096774;
        }
 // TREE: 5
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (lumbar_lordosis_angle <= 31.40432643890381)
      if (sacral_slope <= 27.234947204589844)
        {
         class0+=0.9;
         class1+=0.1;
         class2+=0.0;
        }
      else
        {
         class0+=0.5833333333333334;
         class1+=0.4166666666666667;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 17.217758178710938)
        {
         class0+=0.13333333333333333;
         class1+=0.7111111111111111;
         class2+=0.15555555555555556;
        }
      else
        {
         class0+=0.625;
         class1+=0.20833333333333334;
         class2+=0.16666666666666666;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (lumbar_lordosis_angle <= 49.31392860412598)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.08;
         class1+=0.8;
         class2+=0.12;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.03571428571428571;
         class1+=0.07142857142857142;
         class2+=0.8928571428571429;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 6
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.36764705882352944;
         class1+=0.6323529411764706;
         class2+=0.0;
        }
      else
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
    if (pelvic_incidence <= 69.48115158081055)
      if (degree_spondylolisthesis <= 18.59344720840454)
        {
         class0+=0.25;
         class1+=0.75;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.06896551724137931;
         class2+=0.9310344827586207;
        }
    else
      if (pelvic_tilt <= 30.175579071044922)
        {
         class0+=0.0;
         class1+=0.11320754716981132;
         class2+=0.8867924528301887;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 7
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.8947368421052632;
         class1+=0.10526315789473684;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 118.08693313598633)
        {
         class0+=0.46153846153846156;
         class1+=0.48717948717948717;
         class2+=0.05128205128205128;
        }
      else
        {
         class0+=0.06976744186046512;
         class1+=0.9069767441860465;
         class2+=0.023255813953488372;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 8
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.6071428571428571;
         class1+=0.39285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.125;
         class1+=0.875;
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
      if (pelvic_incidence <= 55.01586723327637)
        {
         class0+=0.0;
         class1+=0.6666666666666666;
         class2+=0.3333333333333333;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.25;
         class2+=0.6388888888888888;
        }
    else
      if (degree_spondylolisthesis <= 8.365471124649048)
        {
         class0+=0.14285714285714285;
         class1+=0.8571428571428571;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.02702702702702703;
         class2+=0.972972972972973;
        }
 // TREE: 9
  if (pelvic_radius <= 110.70603561401367)
    if (degree_spondylolisthesis <= 11.642083644866943)
      if (pelvic_radius <= 107.06352615356445)
        {
         class0+=0.2857142857142857;
         class1+=0.7142857142857143;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 105.7842025756836)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.07142857142857142;
         class1+=0.0;
         class2+=0.9285714285714286;
        }
  else
    if (lumbar_lordosis_angle <= 57.10000038146973)
      if (pelvic_incidence <= 69.31559753417969)
        {
         class0+=0.3723404255319149;
         class1+=0.5638297872340425;
         class2+=0.06382978723404255;
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
         class0+=0.07692307692307693;
         class1+=0.38461538461538464;
         class2+=0.5384615384615384;
        }
      else
        {
         class0+=0.0;
         class1+=0.06521739130434782;
         class2+=0.9347826086956522;
        }
 // TREE: 10
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_tilt <= 10.485756874084473)
        {
         class0+=0.17647058823529413;
         class1+=0.5882352941176471;
         class2+=0.23529411764705882;
        }
      else
        {
         class0+=0.6326530612244898;
         class1+=0.24489795918367346;
         class2+=0.12244897959183673;
        }
    else
      if (lumbar_lordosis_angle <= 25.224257469177246)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.045454545454545456;
         class1+=0.9090909090909091;
         class2+=0.045454545454545456;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_incidence <= 70.737548828125)
        {
         class0+=0.21052631578947367;
         class1+=0.7894736842105263;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.6666666666666666;
         class2+=0.3333333333333333;
        }
    else
      if (sacral_slope <= 43.447879791259766)
        {
         class0+=0.05263157894736842;
         class1+=0.05263157894736842;
         class2+=0.8947368421052632;
        }
      else
        {
         class0+=0.0;
         class1+=0.012658227848101266;
         class2+=0.9873417721518988;
        }
 // TREE: 11
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.4666666666666667;
         class1+=0.23333333333333334;
         class2+=0.3;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_incidence <= 70.737548828125)
        {
         class0+=0.21052631578947367;
         class1+=0.7894736842105263;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.6666666666666666;
         class2+=0.3333333333333333;
        }
    else
      if (lumbar_lordosis_angle <= 62.66514015197754)
        {
         class0+=0.024390243902439025;
         class1+=0.04878048780487805;
         class2+=0.926829268292683;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 12
  if (pelvic_incidence <= 56.49103927612305)
    if (degree_spondylolisthesis <= 17.252374172210693)
      if (pelvic_tilt <= 10.707735538482666)
        {
         class0+=0.12;
         class1+=0.88;
         class2+=0.0;
        }
      else
        {
         class0+=0.5882352941176471;
         class1+=0.4117647058823529;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 113.07193374633789)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.25;
         class2+=0.75;
        }
  else
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (sacral_slope <= 44.822065353393555)
        {
         class0+=0.45;
         class1+=0.25;
         class2+=0.3;
        }
      else
        {
         class0+=0.0;
         class1+=0.3125;
         class2+=0.6875;
        }
    else
      if (degree_spondylolisthesis <= 8.365471124649048)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.013888888888888888;
         class2+=0.9861111111111112;
        }
 // TREE: 13
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.36764705882352944;
         class1+=0.6323529411764706;
         class2+=0.0;
        }
      else
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
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (degree_spondylolisthesis <= -0.5330937206745148)
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
      else
        {
         class0+=0.047619047619047616;
         class1+=0.8095238095238095;
         class2+=0.14285714285714285;
        }
    else
      if (lumbar_lordosis_angle <= 62.66514015197754)
        {
         class0+=0.024390243902439025;
         class1+=0.04878048780487805;
         class2+=0.926829268292683;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 14
  if (pelvic_incidence <= 56.49103927612305)
    if (degree_spondylolisthesis <= 17.252374172210693)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.6382978723404256;
         class1+=0.3617021276595745;
         class2+=0.0;
        }
      else
        {
         class0+=0.10344827586206896;
         class1+=0.896551724137931;
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
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (sacral_slope <= 44.822065353393555)
        {
         class0+=0.45;
         class1+=0.25;
         class2+=0.3;
        }
      else
        {
         class0+=0.0;
         class1+=0.3125;
         class2+=0.6875;
        }
    else
      if (degree_spondylolisthesis <= 8.365471124649048)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.013888888888888888;
         class2+=0.9861111111111112;
        }
 // TREE: 15
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_tilt <= 10.485756874084473)
        {
         class0+=0.15;
         class1+=0.5;
         class2+=0.35;
        }
      else
        {
         class0+=0.75;
         class1+=0.2222222222222222;
         class2+=0.027777777777777776;
        }
    else
      if (degree_spondylolisthesis <= 22.721468925476074)
        {
         class0+=0.10344827586206896;
         class1+=0.896551724137931;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (pelvic_tilt <= 30.175579071044922)
      if (degree_spondylolisthesis <= 12.387728691101074)
        {
         class0+=0.2;
         class1+=0.7;
         class2+=0.1;
        }
      else
        {
         class0+=0.014084507042253521;
         class1+=0.014084507042253521;
         class2+=0.971830985915493;
        }
    else
      if (lumbar_lordosis_angle <= 47.17729949951172)
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
 // TREE: 16
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (lumbar_lordosis_angle <= 31.40432643890381)
      if (pelvic_tilt <= 6.681411981582642)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.7619047619047619;
         class1+=0.23809523809523808;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 125.3803825378418)
        {
         class0+=0.41379310344827586;
         class1+=0.5344827586206896;
         class2+=0.05172413793103448;
        }
      else
        {
         class0+=0.03571428571428571;
         class1+=0.9642857142857143;
         class2+=0.0;
        }
  else
    if (pelvic_tilt <= 14.86863899230957)
      if (pelvic_tilt <= 14.60332202911377)
        {
         class0+=0.0;
         class1+=0.03225806451612903;
         class2+=0.967741935483871;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 16.078891277313232)
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
 // TREE: 17
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (degree_spondylolisthesis <= 6.924067258834839)
        {
         class0+=0.8260869565217391;
         class1+=0.17391304347826086;
         class2+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
    else
      if (lumbar_lordosis_angle <= 31.530985832214355)
        {
         class0+=0.5833333333333334;
         class1+=0.4166666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.2;
         class1+=0.7571428571428571;
         class2+=0.04285714285714286;
        }
  else
    if (degree_spondylolisthesis <= 16.078891277313232)
      if (pelvic_radius <= 105.5777816772461)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.06451612903225806;
         class2+=0.9354838709677419;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 18
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_incidence <= 60.66520690917969)
        {
         class0+=0.64;
         class1+=0.36;
         class2+=0.0;
        }
      else
        {
         class0+=0.24;
         class1+=0.64;
         class2+=0.12;
        }
    else
      if (pelvic_tilt <= 25.32889747619629)
        {
         class0+=0.0625;
         class1+=0.9375;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 19
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (lumbar_lordosis_angle <= 35.93632888793945)
        {
         class0+=0.7666666666666667;
         class1+=0.23333333333333334;
         class2+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6;
         class2+=0.06666666666666667;
        }
    else
      if (lumbar_lordosis_angle <= 25.224257469177246)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.03333333333333333;
         class1+=0.9666666666666667;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 16.078891277313232)
      if (lumbar_lordosis_angle <= 66.84712982177734)
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
      if (lumbar_lordosis_angle <= 62.66514015197754)
        {
         class0+=0.0;
         class1+=0.0392156862745098;
         class2+=0.9607843137254902;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 20
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.76;
         class1+=0.24;
         class2+=0.0;
        }
      else
        {
         class0+=0.32727272727272727;
         class1+=0.6727272727272727;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_tilt <= 15.617511749267578)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.22727272727272727;
         class1+=0.5;
         class2+=0.2727272727272727;
        }
    else
      if (lumbar_lordosis_angle <= 61.07404136657715)
        {
         class0+=0.0;
         class1+=0.18181818181818182;
         class2+=0.8181818181818182;
        }
      else
        {
         class0+=0.0;
         class1+=0.03636363636363636;
         class2+=0.9636363636363636;
        }
 // TREE: 21
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_tilt <= 10.707735538482666)
      if (lumbar_lordosis_angle <= 30.714024543762207)
        {
         class0+=0.5;
         class1+=0.5;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 35.53619194030762)
        {
         class0+=0.6888888888888889;
         class1+=0.3111111111111111;
         class2+=0.0;
        }
      else
        {
         class0+=0.18421052631578946;
         class1+=0.7368421052631579;
         class2+=0.07894736842105263;
        }
  else
    if (degree_spondylolisthesis <= 16.078891277313232)
      if (pelvic_incidence <= 70.7374324798584)
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
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.06451612903225806;
         class2+=0.9354838709677419;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 22
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_tilt <= 10.485756874084473)
        {
         class0+=0.23076923076923078;
         class1+=0.6153846153846154;
         class2+=0.15384615384615385;
        }
      else
        {
         class0+=0.7333333333333333;
         class1+=0.17777777777777778;
         class2+=0.08888888888888889;
        }
    else
      if (degree_spondylolisthesis <= 32.5670747756958)
        {
         class0+=0.11538461538461539;
         class1+=0.8846153846153846;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_radius <= 85.2918815612793)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.1;
         class1+=0.8333333333333334;
         class2+=0.06666666666666667;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.06666666666666667;
         class2+=0.9333333333333333;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 23
  if (sacral_slope <= 40.53826332092285)
    if (lumbar_lordosis_angle <= 55.60000038146973)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.6363636363636364;
         class1+=0.2909090909090909;
         class2+=0.07272727272727272;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8518518518518519;
         class2+=0.037037037037037035;
        }
    else
      if (lumbar_lordosis_angle <= 63.405269622802734)
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (sacral_slope <= 53.45471954345703)
      if (degree_spondylolisthesis <= 14.380344867706299)
        {
         class0+=0.125;
         class1+=0.8333333333333334;
         class2+=0.041666666666666664;
        }
      else
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
    else
      if (sacral_slope <= 55.55099105834961)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.11904761904761904;
         class2+=0.8809523809523809;
        }
 // TREE: 24
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.8947368421052632;
         class1+=0.10526315789473684;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 45.12681770324707)
        {
         class0+=0.3387096774193548;
         class1+=0.6612903225806451;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.85;
         class2+=0.15;
        }
  else
    if (degree_spondylolisthesis <= 16.078891277313232)
      if (pelvic_tilt <= 19.228031635284424)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 31.374295234680176)
        {
         class0+=0.0;
         class1+=0.08333333333333333;
         class2+=0.9166666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 25
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (sacral_slope <= 35.553558349609375)
        {
         class0+=0.7631578947368421;
         class1+=0.23684210526315788;
         class2+=0.0;
        }
      else
        {
         class0+=0.24324324324324326;
         class1+=0.6756756756756757;
         class2+=0.08108108108108109;
        }
    else
      if (lumbar_lordosis_angle <= 25.224257469177246)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.03333333333333333;
         class1+=0.9666666666666667;
         class2+=0.0;
        }
  else
    if (lumbar_lordosis_angle <= 62.66514015197754)
      if (pelvic_incidence <= 68.58223342895508)
        {
         class0+=0.03333333333333333;
         class1+=0.06666666666666667;
         class2+=0.9;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 26
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 16.65331506729126)
        {
         class0+=0.6071428571428571;
         class1+=0.39285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 22.52758026123047)
        {
         class0+=0.08695652173913043;
         class1+=0.9130434782608695;
         class2+=0.0;
        }
      else
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (degree_spondylolisthesis <= 18.59344720840454)
        {
         class0+=0.25;
         class1+=0.75;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.06896551724137931;
         class2+=0.9310344827586207;
        }
    else
      if (lumbar_lordosis_angle <= 61.07404136657715)
        {
         class0+=0.0;
         class1+=0.18181818181818182;
         class2+=0.8181818181818182;
        }
      else
        {
         class0+=0.0;
         class1+=0.03636363636363636;
         class2+=0.9636363636363636;
        }
 // TREE: 27
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (lumbar_lordosis_angle <= 31.40432643890381)
      if (pelvic_radius <= 123.31332397460938)
        {
         class0+=0.9333333333333333;
         class1+=0.06666666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.2857142857142857;
         class1+=0.7142857142857143;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 19.620058059692383)
        {
         class0+=0.16666666666666666;
         class1+=0.8;
         class2+=0.03333333333333333;
        }
      else
        {
         class0+=0.5769230769230769;
         class1+=0.38461538461538464;
         class2+=0.038461538461538464;
        }
  else
    if (pelvic_tilt <= 14.86863899230957)
      if (pelvic_tilt <= 14.60332202911377)
        {
         class0+=0.0;
         class1+=0.03225806451612903;
         class2+=0.967741935483871;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (lumbar_lordosis_angle <= 55.60000038146973)
        {
         class0+=0.07142857142857142;
         class1+=0.0;
         class2+=0.9285714285714286;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 28
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_tilt <= 10.707735538482666)
      if (degree_spondylolisthesis <= 15.480012655258179)
        {
         class0+=0.12;
         class1+=0.88;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 32.862857818603516)
        {
         class0+=0.5769230769230769;
         class1+=0.4230769230769231;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_tilt <= 15.907419681549072)
        {
         class0+=0.0;
         class1+=0.13043478260869565;
         class2+=0.8695652173913043;
        }
      else
        {
         class0+=0.2857142857142857;
         class1+=0.4642857142857143;
         class2+=0.25;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.03125;
         class1+=0.1875;
         class2+=0.78125;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 29
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 18.811551570892334)
        {
         class0+=0.6923076923076923;
         class1+=0.3076923076923077;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_incidence <= 56.98469161987305)
        {
         class0+=0.1111111111111111;
         class1+=0.8518518518518519;
         class2+=0.037037037037037035;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (pelvic_radius <= 110.70603561401367)
      if (degree_spondylolisthesis <= 11.642083644866943)
        {
         class0+=0.2;
         class1+=0.8;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 14.380344867706299)
        {
         class0+=0.08;
         class1+=0.84;
         class2+=0.08;
        }
      else
        {
         class0+=0.0;
         class1+=0.038461538461538464;
         class2+=0.9615384615384616;
        }
 // TREE: 30
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_incidence <= 66.89922332763672)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.625;
         class1+=0.2857142857142857;
         class2+=0.08928571428571429;
        }
      else
        {
         class0+=0.10714285714285714;
         class1+=0.8214285714285714;
         class2+=0.07142857142857142;
        }
    else
      if (sacral_slope <= 33.18121528625488)
        {
         class0+=0.3333333333333333;
         class1+=0.0;
         class2+=0.6666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_radius <= 113.05963516235352)
        {
         class0+=0.07407407407407407;
         class1+=0.07407407407407407;
         class2+=0.8518518518518519;
        }
      else
        {
         class0+=0.04;
         class1+=0.76;
         class2+=0.2;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.0;
         class1+=0.1935483870967742;
         class2+=0.8064516129032258;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 31
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 17.252374172210693)
        {
         class0+=0.6382978723404256;
         class1+=0.3617021276595745;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.1111111111111111;
         class2+=0.8888888888888888;
        }
    else
      if (degree_spondylolisthesis <= 22.721468925476074)
        {
         class0+=0.10344827586206896;
         class1+=0.896551724137931;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_radius <= 99.58705520629883)
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
      else
        {
         class0+=0.23333333333333334;
         class1+=0.7;
         class2+=0.06666666666666667;
        }
    else
      if (sacral_slope <= 39.34503364562988)
        {
         class0+=0.1111111111111111;
         class1+=0.0;
         class2+=0.8888888888888888;
        }
      else
        {
         class0+=0.0;
         class1+=0.011235955056179775;
         class2+=0.9887640449438202;
        }
 // TREE: 32
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_tilt <= 10.707735538482666)
      if (degree_spondylolisthesis <= 1.974761962890625)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 60.66520690917969)
        {
         class0+=0.5925925925925926;
         class1+=0.4074074074074074;
         class2+=0.0;
        }
      else
        {
         class0+=0.20689655172413793;
         class1+=0.6896551724137931;
         class2+=0.10344827586206896;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 33
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 17.252374172210693)
        {
         class0+=0.6382978723404256;
         class1+=0.3617021276595745;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.1111111111111111;
         class2+=0.8888888888888888;
        }
    else
      if (sacral_slope <= 20.55422878265381)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.03571428571428571;
         class1+=0.8928571428571429;
         class2+=0.07142857142857142;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (sacral_slope <= 42.2171573638916)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.08695652173913043;
         class1+=0.782608695652174;
         class2+=0.13043478260869565;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.037037037037037035;
         class1+=0.037037037037037035;
         class2+=0.9259259259259259;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 34
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (sacral_slope <= 23.494050979614258)
        {
         class0+=0.5833333333333334;
         class1+=0.4166666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.9285714285714286;
         class1+=0.07142857142857142;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 45.12681770324707)
        {
         class0+=0.3387096774193548;
         class1+=0.6612903225806451;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.85;
         class2+=0.15;
        }
  else
    if (degree_spondylolisthesis <= 16.078891277313232)
      if (lumbar_lordosis_angle <= 66.84712982177734)
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
      if (lumbar_lordosis_angle <= 62.66514015197754)
        {
         class0+=0.0;
         class1+=0.0392156862745098;
         class2+=0.9607843137254902;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 35
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (lumbar_lordosis_angle <= 31.40432643890381)
      if (sacral_slope <= 27.234947204589844)
        {
         class0+=0.9;
         class1+=0.1;
         class2+=0.0;
        }
      else
        {
         class0+=0.5833333333333334;
         class1+=0.4166666666666667;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 13.920654773712158)
        {
         class0+=0.3620689655172414;
         class1+=0.6379310344827587;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (sacral_slope <= 43.68538284301758)
      if (pelvic_tilt <= 30.450276374816895)
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
      if (pelvic_radius <= 126.13557434082031)
        {
         class0+=0.0;
         class1+=0.10843373493975904;
         class2+=0.891566265060241;
        }
      else
        {
         class0+=0.0;
         class1+=0.36363636363636365;
         class2+=0.6363636363636364;
        }
 // TREE: 36
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.8888888888888888;
         class1+=0.1111111111111111;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.4666666666666667;
         class1+=0.23333333333333334;
         class2+=0.3;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (degree_spondylolisthesis <= 18.59344720840454)
        {
         class0+=0.25;
         class1+=0.75;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.06896551724137931;
         class2+=0.9310344827586207;
        }
    else
      if (degree_spondylolisthesis <= 8.255654573440552)
        {
         class0+=0.0;
         class1+=0.8571428571428571;
         class2+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 37
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.76;
         class1+=0.24;
         class2+=0.0;
        }
      else
        {
         class0+=0.32727272727272727;
         class1+=0.6727272727272727;
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
      if (sacral_slope <= 43.76664161682129)
        {
         class0+=0.26666666666666666;
         class1+=0.4666666666666667;
         class2+=0.26666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.26666666666666666;
         class2+=0.7333333333333333;
        }
    else
      if (degree_spondylolisthesis <= 8.365471124649048)
        {
         class0+=0.14285714285714285;
         class1+=0.8571428571428571;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.02702702702702703;
         class2+=0.972972972972973;
        }
 // TREE: 38
  if (pelvic_incidence <= 56.49103927612305)
    if (degree_spondylolisthesis <= 17.252374172210693)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.6382978723404256;
         class1+=0.3617021276595745;
         class2+=0.0;
        }
      else
        {
         class0+=0.10344827586206896;
         class1+=0.896551724137931;
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
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_radius <= 113.05963516235352)
        {
         class0+=0.16;
         class1+=0.08;
         class2+=0.76;
        }
      else
        {
         class0+=0.15384615384615385;
         class1+=0.5384615384615384;
         class2+=0.3076923076923077;
        }
    else
      if (lumbar_lordosis_angle <= 34.24835014343262)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.07692307692307693;
         class2+=0.9230769230769231;
        }
 // TREE: 39
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.8947368421052632;
         class1+=0.10526315789473684;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 10.707735538482666)
        {
         class0+=0.08333333333333333;
         class1+=0.9166666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.3275862068965517;
         class1+=0.6206896551724138;
         class2+=0.05172413793103448;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 40
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_tilt <= 10.707735538482666)
      if (pelvic_radius <= 110.853271484375)
        {
         class0+=0.125;
         class1+=0.0;
         class2+=0.875;
        }
      else
        {
         class0+=0.08;
         class1+=0.88;
         class2+=0.04;
        }
    else
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.75;
         class1+=0.2222222222222222;
         class2+=0.027777777777777776;
        }
      else
        {
         class0+=0.16666666666666666;
         class1+=0.7777777777777778;
         class2+=0.05555555555555555;
        }
  else
    if (sacral_slope <= 44.822065353393555)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=0.5625;
         class1+=0.4375;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 11.452327728271484)
        {
         class0+=0.0;
         class1+=0.875;
         class2+=0.125;
        }
      else
        {
         class0+=0.0;
         class1+=0.01282051282051282;
         class2+=0.9871794871794872;
        }
 // TREE: 41
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 16.65331506729126)
        {
         class0+=0.6071428571428571;
         class1+=0.39285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 22.52758026123047)
        {
         class0+=0.08695652173913043;
         class1+=0.9130434782608695;
         class2+=0.0;
        }
      else
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
  else
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (pelvic_radius <= 125.59880447387695)
        {
         class0+=0.11428571428571428;
         class1+=0.2;
         class2+=0.6857142857142857;
        }
      else
        {
         class0+=0.0;
         class1+=0.8;
         class2+=0.2;
        }
    else
      if (pelvic_tilt <= 26.210180282592773)
        {
         class0+=0.018518518518518517;
         class1+=0.14814814814814814;
         class2+=0.8333333333333334;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 42
  if (sacral_slope <= 40.53826332092285)
    if (lumbar_lordosis_angle <= 55.60000038146973)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.6363636363636364;
         class1+=0.2909090909090909;
         class2+=0.07272727272727272;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8518518518518519;
         class2+=0.037037037037037035;
        }
    else
      if (degree_spondylolisthesis <= 23.90088641643524)
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
    if (sacral_slope <= 53.45471954345703)
      if (degree_spondylolisthesis <= 14.380344867706299)
        {
         class0+=0.125;
         class1+=0.8333333333333334;
         class2+=0.041666666666666664;
        }
      else
        {
         class0+=0.0;
         class1+=0.043478260869565216;
         class2+=0.9565217391304348;
        }
    else
      if (degree_spondylolisthesis <= 11.452327728271484)
        {
         class0+=0.0;
         class1+=0.8333333333333334;
         class2+=0.16666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 43
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.6071428571428571;
         class1+=0.39285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.125;
         class1+=0.875;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_tilt <= 30.175579071044922)
      if (degree_spondylolisthesis <= 12.387728691101074)
        {
         class0+=0.14285714285714285;
         class1+=0.75;
         class2+=0.10714285714285714;
        }
      else
        {
         class0+=0.014084507042253521;
         class1+=0.028169014084507043;
         class2+=0.9577464788732394;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 44
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.76;
         class1+=0.24;
         class2+=0.0;
        }
      else
        {
         class0+=0.32727272727272727;
         class1+=0.6727272727272727;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (degree_spondylolisthesis <= 18.59344720840454)
        {
         class0+=0.25;
         class1+=0.75;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.06896551724137931;
         class2+=0.9310344827586207;
        }
    else
      if (lumbar_lordosis_angle <= 61.07404136657715)
        {
         class0+=0.0;
         class1+=0.18181818181818182;
         class2+=0.8181818181818182;
        }
      else
        {
         class0+=0.0;
         class1+=0.03636363636363636;
         class2+=0.9636363636363636;
        }
 // TREE: 45
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_incidence <= 63.882225036621094)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.6071428571428571;
         class1+=0.39285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.10344827586206896;
         class1+=0.896551724137931;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 85.2918815612793)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.18181818181818182;
         class1+=0.7272727272727273;
         class2+=0.09090909090909091;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_radius <= 115.87165069580078)
        {
         class0+=0.03571428571428571;
         class1+=0.0;
         class2+=0.9642857142857143;
        }
      else
        {
         class0+=0.0;
         class1+=0.2;
         class2+=0.8;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 46
  if (pelvic_radius <= 110.70603561401367)
    if (lumbar_lordosis_angle <= 31.751480102539062)
      {
       class0+=1.0;
       class1+=0.0;
       class2+=0.0;
      }
    else
      if (degree_spondylolisthesis <= 11.642083644866943)
        {
         class0+=0.2857142857142857;
         class1+=0.7142857142857143;
         class2+=0.0;
        }
      else
        {
         class0+=0.020833333333333332;
         class1+=0.0;
         class2+=0.9791666666666666;
        }
  else
    if (lumbar_lordosis_angle <= 57.10000038146973)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.7407407407407407;
         class1+=0.2222222222222222;
         class2+=0.037037037037037035;
        }
      else
        {
         class0+=0.2054794520547945;
         class1+=0.6438356164383562;
         class2+=0.1506849315068493;
        }
    else
      if (degree_spondylolisthesis <= 15.148581981658936)
        {
         class0+=0.125;
         class1+=0.75;
         class2+=0.125;
        }
      else
        {
         class0+=0.0;
         class1+=0.0392156862745098;
         class2+=0.9607843137254902;
        }
 // TREE: 47
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (sacral_slope <= 35.553558349609375)
        {
         class0+=0.7631578947368421;
         class1+=0.23684210526315788;
         class2+=0.0;
        }
      else
        {
         class0+=0.24324324324324326;
         class1+=0.6756756756756757;
         class2+=0.08108108108108109;
        }
    else
      if (degree_spondylolisthesis <= -0.4475463032722473)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.16666666666666666;
         class1+=0.8333333333333334;
         class2+=0.0;
        }
  else
    if (pelvic_tilt <= 14.86863899230957)
      if (pelvic_tilt <= 14.60332202911377)
        {
         class0+=0.0;
         class1+=0.03225806451612903;
         class2+=0.967741935483871;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 63.94157600402832)
        {
         class0+=0.125;
         class1+=0.0;
         class2+=0.875;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 48
  if (sacral_slope <= 40.53826332092285)
    if (degree_spondylolisthesis <= 18.811551570892334)
      if (lumbar_lordosis_angle <= 31.40432643890381)
        {
         class0+=0.7272727272727273;
         class1+=0.2727272727272727;
         class2+=0.0;
        }
      else
        {
         class0+=0.4107142857142857;
         class1+=0.5892857142857143;
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
      if (lumbar_lordosis_angle <= 47.28213119506836)
        {
         class0+=0.05263157894736842;
         class1+=0.5263157894736842;
         class2+=0.42105263157894735;
        }
      else
        {
         class0+=0.058823529411764705;
         class1+=0.2647058823529412;
         class2+=0.6764705882352942;
        }
    else
      if (sacral_slope <= 43.447879791259766)
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.078125;
         class2+=0.921875;
        }
 // TREE: 49
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= -0.582274317741394)
        {
         class0+=0.7368421052631579;
         class1+=0.2631578947368421;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5178571428571429;
         class2+=0.05357142857142857;
        }
    else
      if (degree_spondylolisthesis <= -0.4475463032722473)
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.16666666666666666;
         class1+=0.8333333333333334;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
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
 // TREE: 50
  if (pelvic_incidence <= 56.49103927612305)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.8888888888888888;
         class1+=0.1111111111111111;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.4782608695652174;
         class1+=0.21739130434782608;
         class2+=0.30434782608695654;
        }
      else
        {
         class0+=0.07692307692307693;
         class1+=0.8461538461538461;
         class2+=0.07692307692307693;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_tilt <= 15.907419681549072)
        {
         class0+=0.0;
         class1+=0.13043478260869565;
         class2+=0.8695652173913043;
        }
      else
        {
         class0+=0.2857142857142857;
         class1+=0.4642857142857143;
         class2+=0.25;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.03125;
         class1+=0.1875;
         class2+=0.78125;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 51
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 17.252374172210693)
        {
         class0+=0.6382978723404256;
         class1+=0.3617021276595745;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.1111111111111111;
         class2+=0.8888888888888888;
        }
    else
      if (pelvic_tilt <= 19.205841064453125)
        {
         class0+=0.03571428571428571;
         class1+=0.8928571428571429;
         class2+=0.07142857142857142;
        }
      else
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_tilt <= 20.16680145263672)
        {
         class0+=0.0;
         class1+=0.8666666666666667;
         class2+=0.13333333333333333;
        }
      else
        {
         class0+=0.47058823529411764;
         class1+=0.47058823529411764;
         class2+=0.058823529411764705;
        }
    else
      if (sacral_slope <= 39.34503364562988)
        {
         class0+=0.1111111111111111;
         class1+=0.0;
         class2+=0.8888888888888888;
        }
      else
        {
         class0+=0.0;
         class1+=0.011235955056179775;
         class2+=0.9887640449438202;
        }
 // TREE: 52
  if (pelvic_incidence <= 56.49103927612305)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.8888888888888888;
         class1+=0.1111111111111111;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.4782608695652174;
         class1+=0.21739130434782608;
         class2+=0.30434782608695654;
        }
      else
        {
         class0+=0.07692307692307693;
         class1+=0.8461538461538461;
         class2+=0.07692307692307693;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_tilt <= 15.907419681549072)
        {
         class0+=0.0;
         class1+=0.13043478260869565;
         class2+=0.8695652173913043;
        }
      else
        {
         class0+=0.2857142857142857;
         class1+=0.4642857142857143;
         class2+=0.25;
        }
    else
      if (pelvic_tilt <= 30.175579071044922)
        {
         class0+=0.0;
         class1+=0.1111111111111111;
         class2+=0.8888888888888888;
        }
      else
        {
         class0+=0.04;
         class1+=0.0;
         class2+=0.96;
        }
 // TREE: 53
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_incidence <= 60.66520690917969)
        {
         class0+=0.64;
         class1+=0.36;
         class2+=0.0;
        }
      else
        {
         class0+=0.24;
         class1+=0.64;
         class2+=0.12;
        }
    else
      if (lumbar_lordosis_angle <= 25.224257469177246)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.03333333333333333;
         class1+=0.9666666666666667;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
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
 // TREE: 54
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_tilt <= 10.485756874084473)
        {
         class0+=0.17647058823529413;
         class1+=0.5882352941176471;
         class2+=0.23529411764705882;
        }
      else
        {
         class0+=0.6326530612244898;
         class1+=0.24489795918367346;
         class2+=0.12244897959183673;
        }
    else
      if (pelvic_incidence <= 60.99151420593262)
        {
         class0+=0.13043478260869565;
         class1+=0.8695652173913043;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.5;
         class2+=0.5;
        }
  else
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (degree_spondylolisthesis <= 16.84712839126587)
        {
         class0+=0.21052631578947367;
         class1+=0.7368421052631579;
         class2+=0.05263157894736842;
        }
      else
        {
         class0+=0.0;
         class1+=0.038461538461538464;
         class2+=0.9615384615384616;
        }
    else
      if (degree_spondylolisthesis <= 8.365471124649048)
        {
         class0+=0.14285714285714285;
         class1+=0.8571428571428571;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.02702702702702703;
         class2+=0.972972972972973;
        }
 // TREE: 55
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (lumbar_lordosis_angle <= 31.40432643890381)
      if (degree_spondylolisthesis <= -1.0019319355487823)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.625;
         class1+=0.375;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 125.3803825378418)
        {
         class0+=0.41379310344827586;
         class1+=0.5344827586206896;
         class2+=0.05172413793103448;
        }
      else
        {
         class0+=0.03571428571428571;
         class1+=0.9642857142857143;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 16.078891277313232)
      if (pelvic_radius <= 105.5777816772461)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 31.374295234680176)
        {
         class0+=0.0;
         class1+=0.08333333333333333;
         class2+=0.9166666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 56
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_tilt <= 10.485756874084473)
        {
         class0+=0.15;
         class1+=0.5;
         class2+=0.35;
        }
      else
        {
         class0+=0.75;
         class1+=0.2222222222222222;
         class2+=0.027777777777777776;
        }
    else
      if (degree_spondylolisthesis <= 22.721468925476074)
        {
         class0+=0.10344827586206896;
         class1+=0.896551724137931;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_tilt <= 20.16680145263672)
        {
         class0+=0.0;
         class1+=0.8666666666666667;
         class2+=0.13333333333333333;
        }
      else
        {
         class0+=0.47058823529411764;
         class1+=0.47058823529411764;
         class2+=0.058823529411764705;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.037037037037037035;
         class1+=0.037037037037037035;
         class2+=0.9259259259259259;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 57
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.4666666666666667;
         class1+=0.23333333333333334;
         class2+=0.3;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_tilt <= 15.617511749267578)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.22727272727272727;
         class1+=0.5;
         class2+=0.2727272727272727;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.0;
         class1+=0.2;
         class2+=0.8;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 58
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_tilt <= 17.217758178710938)
      if (lumbar_lordosis_angle <= 31.751480102539062)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.13333333333333333;
         class1+=0.7111111111111111;
         class2+=0.15555555555555556;
        }
    else
      if (degree_spondylolisthesis <= 13.794804573059082)
        {
         class0+=0.7777777777777778;
         class1+=0.2222222222222222;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_radius <= 112.11275100708008)
        {
         class0+=0.045454545454545456;
         class1+=0.09090909090909091;
         class2+=0.8636363636363636;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.5555555555555556;
         class2+=0.2962962962962963;
        }
    else
      if (degree_spondylolisthesis <= 8.255654573440552)
        {
         class0+=0.0;
         class1+=0.8571428571428571;
         class2+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 59
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.36764705882352944;
         class1+=0.6323529411764706;
         class2+=0.0;
        }
      else
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
    if (sacral_slope <= 43.68538284301758)
      if (pelvic_tilt <= 30.450276374816895)
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
      if (degree_spondylolisthesis <= 11.452327728271484)
        {
         class0+=0.0;
         class1+=0.8571428571428571;
         class2+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=0.0125;
         class2+=0.9875;
        }
 // TREE: 60
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_tilt <= 10.707735538482666)
      if (lumbar_lordosis_angle <= 30.714024543762207)
        {
         class0+=0.5;
         class1+=0.5;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (lumbar_lordosis_angle <= 49.31392860412598)
        {
         class0+=0.5901639344262295;
         class1+=0.4098360655737705;
         class2+=0.0;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.7727272727272727;
         class2+=0.13636363636363635;
        }
  else
    if (sacral_slope <= 43.074928283691406)
      if (lumbar_lordosis_angle <= 63.31851005554199)
        {
         class0+=0.07142857142857142;
         class1+=0.07142857142857142;
         class2+=0.8571428571428571;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
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
         class1+=0.06666666666666667;
         class2+=0.9333333333333333;
        }
 // TREE: 61
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.6071428571428571;
         class1+=0.39285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.125;
         class1+=0.875;
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
      if (pelvic_tilt <= 14.482349395751953)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.4074074074074074;
         class2+=0.4444444444444444;
        }
    else
      if (sacral_slope <= 53.45471954345703)
        {
         class0+=0.02564102564102564;
         class1+=0.15384615384615385;
         class2+=0.8205128205128205;
        }
      else
        {
         class0+=0.0;
         class1+=0.047619047619047616;
         class2+=0.9523809523809523;
        }
 // TREE: 62
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_tilt <= 10.707735538482666)
      if (pelvic_radius <= 116.0182876586914)
        {
         class0+=0.75;
         class1+=0.25;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 35.53619194030762)
        {
         class0+=0.6888888888888889;
         class1+=0.3111111111111111;
         class2+=0.0;
        }
      else
        {
         class0+=0.18421052631578946;
         class1+=0.7368421052631579;
         class2+=0.07894736842105263;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 63
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 16.65331506729126)
        {
         class0+=0.6071428571428571;
         class1+=0.39285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (sacral_slope <= 20.55422878265381)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.045454545454545456;
         class1+=0.9090909090909091;
         class2+=0.045454545454545456;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (sacral_slope <= 49.18738555908203)
        {
         class0+=0.1724137931034483;
         class1+=0.4482758620689655;
         class2+=0.3793103448275862;
        }
      else
        {
         class0+=0.0;
         class1+=0.2;
         class2+=0.8;
        }
    else
      if (degree_spondylolisthesis <= 8.255654573440552)
        {
         class0+=0.0;
         class1+=0.8571428571428571;
         class2+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 64
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (sacral_slope <= 23.494050979614258)
        {
         class0+=0.5833333333333334;
         class1+=0.4166666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.9285714285714286;
         class1+=0.07142857142857142;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 10.707735538482666)
        {
         class0+=0.08333333333333333;
         class1+=0.9166666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.3275862068965517;
         class1+=0.6206896551724138;
         class2+=0.05172413793103448;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 65
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.76;
         class1+=0.24;
         class2+=0.0;
        }
      else
        {
         class0+=0.32727272727272727;
         class1+=0.6727272727272727;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (sacral_slope <= 43.68538284301758)
      if (degree_spondylolisthesis <= 19.410610675811768)
        {
         class0+=0.35714285714285715;
         class1+=0.6428571428571429;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.05555555555555555;
         class2+=0.9444444444444444;
        }
    else
      if (degree_spondylolisthesis <= 11.452327728271484)
        {
         class0+=0.0;
         class1+=0.8571428571428571;
         class2+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=0.0125;
         class2+=0.9875;
        }
 // TREE: 66
  if (pelvic_radius <= 110.70603561401367)
    if (sacral_slope <= 40.710838317871094)
      if (degree_spondylolisthesis <= 18.811551570892334)
        {
         class0+=0.8333333333333334;
         class1+=0.16666666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_radius <= 100.1068229675293)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.03333333333333333;
         class1+=0.13333333333333333;
         class2+=0.8333333333333334;
        }
  else
    if (lumbar_lordosis_angle <= 57.10000038146973)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.7407407407407407;
         class1+=0.2222222222222222;
         class2+=0.037037037037037035;
        }
      else
        {
         class0+=0.2054794520547945;
         class1+=0.6438356164383562;
         class2+=0.1506849315068493;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.07692307692307693;
         class1+=0.38461538461538464;
         class2+=0.5384615384615384;
        }
      else
        {
         class0+=0.0;
         class1+=0.06521739130434782;
         class2+=0.9347826086956522;
        }
 // TREE: 67
  if (pelvic_incidence <= 56.49103927612305)
    if (degree_spondylolisthesis <= 17.252374172210693)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.76;
         class1+=0.24;
         class2+=0.0;
        }
      else
        {
         class0+=0.27450980392156865;
         class1+=0.7254901960784313;
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
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_tilt <= 20.16680145263672)
        {
         class0+=0.0;
         class1+=0.8666666666666667;
         class2+=0.13333333333333333;
        }
      else
        {
         class0+=0.47058823529411764;
         class1+=0.47058823529411764;
         class2+=0.058823529411764705;
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
         class1+=0.010416666666666666;
         class2+=0.9895833333333334;
        }
 // TREE: 68
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 18.811551570892334)
        {
         class0+=0.6923076923076923;
         class1+=0.3076923076923077;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 32.5670747756958)
        {
         class0+=0.11538461538461539;
         class1+=0.8846153846153846;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_radius <= 85.2918815612793)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.1;
         class1+=0.8333333333333334;
         class2+=0.06666666666666667;
        }
    else
      if (pelvic_tilt <= 14.86863899230957)
        {
         class0+=0.0;
         class1+=0.06666666666666667;
         class2+=0.9333333333333333;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 69
  if (sacral_slope <= 40.53826332092285)
    if (lumbar_lordosis_angle <= 55.60000038146973)
      if (degree_spondylolisthesis <= 18.811551570892334)
        {
         class0+=0.4935064935064935;
         class1+=0.5064935064935064;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_radius <= 115.62433624267578)
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
    if (pelvic_incidence <= 69.48115158081055)
      if (sacral_slope <= 53.330434799194336)
        {
         class0+=0.07142857142857142;
         class1+=0.47619047619047616;
         class2+=0.4523809523809524;
        }
      else
        {
         class0+=0.0;
         class1+=0.1;
         class2+=0.9;
        }
    else
      if (lumbar_lordosis_angle <= 61.07404136657715)
        {
         class0+=0.0;
         class1+=0.18181818181818182;
         class2+=0.8181818181818182;
        }
      else
        {
         class0+=0.0;
         class1+=0.038461538461538464;
         class2+=0.9615384615384616;
        }
 // TREE: 70
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 10.707735538482666)
        {
         class0+=0.08333333333333333;
         class1+=0.9166666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.3275862068965517;
         class1+=0.6206896551724138;
         class2+=0.05172413793103448;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
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
 // TREE: 71
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_tilt <= 10.707735538482666)
      if (sacral_slope <= 39.40337562561035)
        {
         class0+=0.09523809523809523;
         class1+=0.8095238095238095;
         class2+=0.09523809523809523;
        }
      else
        {
         class0+=0.08333333333333333;
         class1+=0.4166666666666667;
         class2+=0.5;
        }
    else
      if (degree_spondylolisthesis <= 32.862857818603516)
        {
         class0+=0.5769230769230769;
         class1+=0.4230769230769231;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (sacral_slope <= 44.822065353393555)
      if (pelvic_radius <= 124.19750213623047)
        {
         class0+=0.375;
         class1+=0.2916666666666667;
         class2+=0.3333333333333333;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 11.452327728271484)
        {
         class0+=0.0;
         class1+=0.875;
         class2+=0.125;
        }
      else
        {
         class0+=0.0;
         class1+=0.01282051282051282;
         class2+=0.9871794871794872;
        }
 // TREE: 72
  if (sacral_slope <= 40.53826332092285)
    if (lumbar_lordosis_angle <= 55.60000038146973)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.7407407407407407;
         class1+=0.2222222222222222;
         class2+=0.037037037037037035;
        }
      else
        {
         class0+=0.32727272727272727;
         class1+=0.6;
         class2+=0.07272727272727272;
        }
    else
      if (pelvic_tilt <= 16.75529384613037)
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
    if (pelvic_incidence <= 69.48115158081055)
      if (degree_spondylolisthesis <= 14.380344867706299)
        {
         class0+=0.13636363636363635;
         class1+=0.8636363636363636;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.06666666666666667;
         class2+=0.9333333333333333;
        }
    else
      if (degree_spondylolisthesis <= 8.255654573440552)
        {
         class0+=0.0;
         class1+=0.8571428571428571;
         class2+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 73
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (sacral_slope <= 28.01943588256836)
      if (degree_spondylolisthesis <= 6.924067258834839)
        {
         class0+=0.8181818181818182;
         class1+=0.18181818181818182;
         class2+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
    else
      if (sacral_slope <= 40.80160331726074)
        {
         class0+=0.3541666666666667;
         class1+=0.5833333333333334;
         class2+=0.0625;
        }
      else
        {
         class0+=0.05555555555555555;
         class1+=0.5;
         class2+=0.4444444444444444;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_radius <= 124.03000259399414)
        {
         class0+=0.2222222222222222;
         class1+=0.6111111111111112;
         class2+=0.16666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
         class2+=0.0;
        }
    else
      if (degree_spondylolisthesis <= 16.84712839126587)
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.020833333333333332;
         class2+=0.9791666666666666;
        }
 // TREE: 74
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (degree_spondylolisthesis <= 13.920654773712158)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.76;
         class1+=0.24;
         class2+=0.0;
        }
      else
        {
         class0+=0.32727272727272727;
         class1+=0.6727272727272727;
         class2+=0.0;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_radius <= 112.11275100708008)
        {
         class0+=0.045454545454545456;
         class1+=0.09090909090909091;
         class2+=0.8636363636363636;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.5555555555555556;
         class2+=0.2962962962962963;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.0;
         class1+=0.2;
         class2+=0.8;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 75
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_tilt <= 17.217758178710938)
      if (lumbar_lordosis_angle <= 31.751480102539062)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.13333333333333333;
         class1+=0.7111111111111111;
         class2+=0.15555555555555556;
        }
    else
      if (degree_spondylolisthesis <= 13.794804573059082)
        {
         class0+=0.7777777777777778;
         class1+=0.2222222222222222;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (sacral_slope <= 43.68538284301758)
      if (pelvic_radius <= 132.19104766845703)
        {
         class0+=0.21739130434782608;
         class1+=0.391304347826087;
         class2+=0.391304347826087;
        }
      else
        {
         class0+=0.0;
         class1+=0.1111111111111111;
         class2+=0.8888888888888888;
        }
    else
      if (pelvic_radius <= 126.13557434082031)
        {
         class0+=0.0;
         class1+=0.10843373493975904;
         class2+=0.891566265060241;
        }
      else
        {
         class0+=0.0;
         class1+=0.36363636363636365;
         class2+=0.6363636363636364;
        }
 // TREE: 76
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (lumbar_lordosis_angle <= 35.93632888793945)
        {
         class0+=0.7666666666666667;
         class1+=0.23333333333333334;
         class2+=0.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.6;
         class2+=0.06666666666666667;
        }
    else
      if (pelvic_tilt <= 25.32889747619629)
        {
         class0+=0.0625;
         class1+=0.9375;
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
         class0+=0.02857142857142857;
         class1+=0.0;
         class2+=0.9714285714285714;
        }
      else
        {
         class0+=0.0;
         class1+=0.11764705882352941;
         class2+=0.8823529411764706;
        }
    else
      {
       class0+=0.0;
       class1+=0.0;
       class2+=1.0;
      }
 // TREE: 77
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 17.252374172210693)
        {
         class0+=0.6382978723404256;
         class1+=0.3617021276595745;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.1111111111111111;
         class2+=0.8888888888888888;
        }
    else
      if (degree_spondylolisthesis <= 22.721468925476074)
        {
         class0+=0.10344827586206896;
         class1+=0.896551724137931;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (sacral_slope <= 44.822065353393555)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=0.5625;
         class1+=0.4375;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 11.452327728271484)
        {
         class0+=0.0;
         class1+=0.875;
         class2+=0.125;
        }
      else
        {
         class0+=0.0;
         class1+=0.01282051282051282;
         class2+=0.9871794871794872;
        }
 // TREE: 78
  if (pelvic_incidence <= 56.49103927612305)
    if (degree_spondylolisthesis <= 17.252374172210693)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.76;
         class1+=0.24;
         class2+=0.0;
        }
      else
        {
         class0+=0.27450980392156865;
         class1+=0.7254901960784313;
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
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_tilt <= 15.907419681549072)
        {
         class0+=0.0;
         class1+=0.13043478260869565;
         class2+=0.8695652173913043;
        }
      else
        {
         class0+=0.2857142857142857;
         class1+=0.4642857142857143;
         class2+=0.25;
        }
    else
      if (sacral_slope <= 33.18121528625488)
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=0.07792207792207792;
         class2+=0.922077922077922;
        }
 // TREE: 79
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_radius <= 105.67694854736328)
        {
         class0+=0.25;
         class1+=0.0;
         class2+=0.75;
        }
      else
        {
         class0+=0.5517241379310345;
         class1+=0.3793103448275862;
         class2+=0.06896551724137931;
        }
    else
      if (degree_spondylolisthesis <= 12.182384729385376)
        {
         class0+=0.125;
         class1+=0.875;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_radius <= 124.03000259399414)
        {
         class0+=0.2222222222222222;
         class1+=0.6111111111111112;
         class2+=0.16666666666666666;
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
         class0+=0.024390243902439025;
         class1+=0.04878048780487805;
         class2+=0.926829268292683;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 80
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_tilt <= 10.707735538482666)
      if (sacral_slope <= 39.40337562561035)
        {
         class0+=0.09523809523809523;
         class1+=0.8095238095238095;
         class2+=0.09523809523809523;
        }
      else
        {
         class0+=0.08333333333333333;
         class1+=0.4166666666666667;
         class2+=0.5;
        }
    else
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.48936170212765956;
         class1+=0.46808510638297873;
         class2+=0.0425531914893617;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (sacral_slope <= 44.822065353393555)
        {
         class0+=0.3333333333333333;
         class1+=0.2916666666666667;
         class2+=0.375;
        }
      else
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.03125;
         class1+=0.1875;
         class2+=0.78125;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 81
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_incidence <= 63.882225036621094)
      if (pelvic_tilt <= 10.707735538482666)
        {
         class0+=0.12;
         class1+=0.88;
         class2+=0.0;
        }
      else
        {
         class0+=0.5666666666666667;
         class1+=0.43333333333333335;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 23.895398139953613)
        {
         class0+=0.0;
         class1+=0.8125;
         class2+=0.1875;
        }
      else
        {
         class0+=0.5714285714285714;
         class1+=0.42857142857142855;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 82
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= -0.582274317741394)
        {
         class0+=0.7368421052631579;
         class1+=0.2631578947368421;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5178571428571429;
         class2+=0.05357142857142857;
        }
    else
      if (pelvic_incidence <= 35.79051399230957)
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.06666666666666667;
         class1+=0.9333333333333333;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
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
 // TREE: 83
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_tilt <= 17.217758178710938)
      if (degree_spondylolisthesis <= 17.68692922592163)
        {
         class0+=0.3018867924528302;
         class1+=0.6981132075471698;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (sacral_slope <= 35.13139533996582)
        {
         class0+=0.8571428571428571;
         class1+=0.14285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.3;
         class1+=0.3;
         class2+=0.4;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (degree_spondylolisthesis <= -0.5330937206745148)
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
      else
        {
         class0+=0.047619047619047616;
         class1+=0.8095238095238095;
         class2+=0.14285714285714285;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.03571428571428571;
         class1+=0.07142857142857142;
         class2+=0.8928571428571429;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 84
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_tilt <= 10.707735538482666)
      if (degree_spondylolisthesis <= 15.480012655258179)
        {
         class0+=0.12;
         class1+=0.88;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.48936170212765956;
         class1+=0.46808510638297873;
         class2+=0.0425531914893617;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
  else
    if (pelvic_tilt <= 30.175579071044922)
      if (lumbar_lordosis_angle <= 58.72928810119629)
        {
         class0+=0.15217391304347827;
         class1+=0.32608695652173914;
         class2+=0.5217391304347826;
        }
      else
        {
         class0+=0.0;
         class1+=0.12727272727272726;
         class2+=0.8727272727272727;
        }
    else
      if (degree_spondylolisthesis <= 15.794094562530518)
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
 // TREE: 85
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_incidence <= 66.89922332763672)
      if (sacral_slope <= 28.01943588256836)
        {
         class0+=0.7407407407407407;
         class1+=0.2222222222222222;
         class2+=0.037037037037037035;
        }
      else
        {
         class0+=0.3157894736842105;
         class1+=0.5789473684210527;
         class2+=0.10526315789473684;
        }
    else
      if (pelvic_incidence <= 72.71776580810547)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=0.3333333333333333;
         class1+=0.0;
         class2+=0.6666666666666666;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_incidence <= 70.737548828125)
        {
         class0+=0.13636363636363635;
         class1+=0.8636363636363636;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.6666666666666666;
         class2+=0.3333333333333333;
        }
    else
      if (pelvic_incidence <= 56.319414138793945)
        {
         class0+=0.0;
         class1+=0.14285714285714285;
         class2+=0.8571428571428571;
        }
      else
        {
         class0+=0.0;
         class1+=0.011363636363636364;
         class2+=0.9886363636363636;
        }
 // TREE: 86
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_radius <= 110.44103240966797)
        {
         class0+=0.2222222222222222;
         class1+=0.0;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.5957446808510638;
         class1+=0.3829787234042553;
         class2+=0.02127659574468085;
        }
    else
      if (degree_spondylolisthesis <= 22.721468925476074)
        {
         class0+=0.10344827586206896;
         class1+=0.896551724137931;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (sacral_slope <= 42.2171573638916)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.08695652173913043;
         class1+=0.782608695652174;
         class2+=0.13043478260869565;
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
         class0+=0.013333333333333334;
         class1+=0.0;
         class2+=0.9866666666666667;
        }
 // TREE: 87
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.8888888888888888;
         class1+=0.1111111111111111;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.4666666666666667;
         class1+=0.23333333333333334;
         class2+=0.3;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
  else
    if (lumbar_lordosis_angle <= 58.72928810119629)
      if (pelvic_radius <= 125.59880447387695)
        {
         class0+=0.11428571428571428;
         class1+=0.2;
         class2+=0.6857142857142857;
        }
      else
        {
         class0+=0.0;
         class1+=0.8;
         class2+=0.2;
        }
    else
      if (lumbar_lordosis_angle <= 76.12190246582031)
        {
         class0+=0.02127659574468085;
         class1+=0.14893617021276595;
         class2+=0.8297872340425532;
        }
      else
        {
         class0+=0.0;
         class1+=0.029411764705882353;
         class2+=0.9705882352941176;
        }
 // TREE: 88
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_tilt <= 10.707735538482666)
      if (pelvic_incidence <= 32.66292953491211)
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
      else
        {
         class0+=0.0625;
         class1+=0.6875;
         class2+=0.25;
        }
    else
      if (degree_spondylolisthesis <= 32.862857818603516)
        {
         class0+=0.5769230769230769;
         class1+=0.4230769230769231;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (sacral_slope <= 44.822065353393555)
      if (pelvic_incidence <= 67.19613265991211)
        {
         class0+=0.3333333333333333;
         class1+=0.3333333333333333;
         class2+=0.3333333333333333;
        }
      else
        {
         class0+=0.13333333333333333;
         class1+=0.0;
         class2+=0.8666666666666667;
        }
    else
      if (degree_spondylolisthesis <= 11.452327728271484)
        {
         class0+=0.0;
         class1+=0.875;
         class2+=0.125;
        }
      else
        {
         class0+=0.0;
         class1+=0.01282051282051282;
         class2+=0.9871794871794872;
        }
 // TREE: 89
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (sacral_slope <= 35.553558349609375)
        {
         class0+=0.7631578947368421;
         class1+=0.23684210526315788;
         class2+=0.0;
        }
      else
        {
         class0+=0.24324324324324326;
         class1+=0.6756756756756757;
         class2+=0.08108108108108109;
        }
    else
      if (sacral_slope <= 20.55422878265381)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.03333333333333333;
         class1+=0.9666666666666667;
         class2+=0.0;
        }
  else
    if (lumbar_lordosis_angle <= 62.66514015197754)
      if (lumbar_lordosis_angle <= 62.31851005554199)
        {
         class0+=0.0196078431372549;
         class1+=0.0196078431372549;
         class2+=0.9607843137254902;
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
 // TREE: 90
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_tilt <= 17.217758178710938)
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.45454545454545453;
         class1+=0.22727272727272727;
         class2+=0.3181818181818182;
        }
      else
        {
         class0+=0.15789473684210525;
         class1+=0.8421052631578947;
         class2+=0.0;
        }
    else
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.5625;
         class1+=0.375;
         class2+=0.0625;
        }
      else
        {
         class0+=0.8;
         class1+=0.0;
         class2+=0.2;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_tilt <= 15.617511749267578)
        {
         class0+=0.0;
         class1+=0.2222222222222222;
         class2+=0.7777777777777778;
        }
      else
        {
         class0+=0.22727272727272727;
         class1+=0.5;
         class2+=0.2727272727272727;
        }
    else
      if (degree_spondylolisthesis <= 8.255654573440552)
        {
         class0+=0.0;
         class1+=0.8571428571428571;
         class2+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 91
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (pelvic_radius <= 124.96423721313477)
      if (sacral_slope <= 35.553558349609375)
        {
         class0+=0.7631578947368421;
         class1+=0.23684210526315788;
         class2+=0.0;
        }
      else
        {
         class0+=0.24324324324324326;
         class1+=0.6756756756756757;
         class2+=0.08108108108108109;
        }
    else
      if (lumbar_lordosis_angle <= 25.224257469177246)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
      else
        {
         class0+=0.03333333333333333;
         class1+=0.9666666666666667;
         class2+=0.0;
        }
  else
    if (pelvic_incidence <= 68.07559204101562)
      if (pelvic_incidence <= 67.52561950683594)
        {
         class0+=0.02702702702702703;
         class1+=0.02702702702702703;
         class2+=0.9459459459459459;
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
 // TREE: 92
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_incidence <= 66.89922332763672)
      if (degree_spondylolisthesis <= 18.811551570892334)
        {
         class0+=0.4935064935064935;
         class1+=0.5064935064935064;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 27.563772678375244)
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
    if (pelvic_incidence <= 69.48115158081055)
      if (degree_spondylolisthesis <= 14.380344867706299)
        {
         class0+=0.13636363636363635;
         class1+=0.8636363636363636;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.06666666666666667;
         class2+=0.9333333333333333;
        }
    else
      if (pelvic_radius <= 111.81633758544922)
        {
         class0+=0.0;
         class1+=0.1935483870967742;
         class2+=0.8064516129032258;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 93
  if (sacral_slope <= 40.53826332092285)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 18.811551570892334)
        {
         class0+=0.6923076923076923;
         class1+=0.3076923076923077;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (degree_spondylolisthesis <= 32.5670747756958)
        {
         class0+=0.11538461538461539;
         class1+=0.8846153846153846;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_tilt <= 18.92229175567627)
        {
         class0+=0.0;
         class1+=0.9444444444444444;
         class2+=0.05555555555555555;
        }
      else
        {
         class0+=0.23076923076923078;
         class1+=0.6153846153846154;
         class2+=0.15384615384615385;
        }
    else
      if (pelvic_incidence <= 56.319414138793945)
        {
         class0+=0.0;
         class1+=0.14285714285714285;
         class2+=0.8571428571428571;
        }
      else
        {
         class0+=0.0;
         class1+=0.011363636363636364;
         class2+=0.9886363636363636;
        }
 // TREE: 94
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_tilt <= 10.707735538482666)
      if (degree_spondylolisthesis <= 15.480012655258179)
        {
         class0+=0.12;
         class1+=0.88;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 21.858789443969727)
        {
         class0+=0.48936170212765956;
         class1+=0.46808510638297873;
         class2+=0.0425531914893617;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
  else
    if (sacral_slope <= 44.822065353393555)
      if (degree_spondylolisthesis <= 16.078891277313232)
        {
         class0+=0.5625;
         class1+=0.4375;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_incidence <= 69.48115158081055)
        {
         class0+=0.0;
         class1+=0.3333333333333333;
         class2+=0.6666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=0.08955223880597014;
         class2+=0.9104477611940298;
        }
 // TREE: 95
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (sacral_slope <= 28.01943588256836)
      if (pelvic_radius <= 124.96423721313477)
        {
         class0+=0.8888888888888888;
         class1+=0.1111111111111111;
         class2+=0.0;
        }
      else
        {
         class0+=0.42857142857142855;
         class1+=0.5714285714285714;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.4666666666666667;
         class1+=0.23333333333333334;
         class2+=0.3;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8333333333333334;
         class2+=0.05555555555555555;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_radius <= 112.11275100708008)
        {
         class0+=0.045454545454545456;
         class1+=0.09090909090909091;
         class2+=0.8636363636363636;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.5555555555555556;
         class2+=0.2962962962962963;
        }
    else
      if (degree_spondylolisthesis <= 8.255654573440552)
        {
         class0+=0.0;
         class1+=0.8571428571428571;
         class2+=0.14285714285714285;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 96
  if (pelvic_incidence <= 56.49103927612305)
    if (pelvic_radius <= 124.96423721313477)
      if (pelvic_tilt <= 10.485756874084473)
        {
         class0+=0.15;
         class1+=0.5;
         class2+=0.35;
        }
      else
        {
         class0+=0.75;
         class1+=0.2222222222222222;
         class2+=0.027777777777777776;
        }
    else
      if (pelvic_tilt <= 19.205841064453125)
        {
         class0+=0.03571428571428571;
         class1+=0.8928571428571429;
         class2+=0.07142857142857142;
        }
      else
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
         class2+=0.0;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (lumbar_lordosis_angle <= 49.31392860412598)
        {
         class0+=0.5384615384615384;
         class1+=0.46153846153846156;
         class2+=0.0;
        }
      else
        {
         class0+=0.05263157894736842;
         class1+=0.7894736842105263;
         class2+=0.15789473684210525;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
        {
         class0+=0.037037037037037035;
         class1+=0.037037037037037035;
         class2+=0.9259259259259259;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 97
  if (degree_spondylolisthesis <= 12.387728691101074)
    if (sacral_slope <= 28.01943588256836)
      if (sacral_slope <= 23.494050979614258)
        {
         class0+=0.5833333333333334;
         class1+=0.4166666666666667;
         class2+=0.0;
        }
      else
        {
         class0+=0.9285714285714286;
         class1+=0.07142857142857142;
         class2+=0.0;
        }
    else
      if (pelvic_radius <= 118.08693313598633)
        {
         class0+=0.46153846153846156;
         class1+=0.48717948717948717;
         class2+=0.05128205128205128;
        }
      else
        {
         class0+=0.06976744186046512;
         class1+=0.9069767441860465;
         class2+=0.023255813953488372;
        }
  else
    if (degree_spondylolisthesis <= 16.078891277313232)
      if (pelvic_radius <= 105.5777816772461)
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 68.07559204101562)
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
 // TREE: 98
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_radius <= 124.96423721313477)
      if (degree_spondylolisthesis <= 16.65331506729126)
        {
         class0+=0.6071428571428571;
         class1+=0.39285714285714285;
         class2+=0.0;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
    else
      if (pelvic_tilt <= 22.52758026123047)
        {
         class0+=0.08695652173913043;
         class1+=0.9130434782608695;
         class2+=0.0;
        }
      else
        {
         class0+=0.5;
         class1+=0.0;
         class2+=0.5;
        }
  else
    if (degree_spondylolisthesis <= 12.387728691101074)
      if (pelvic_radius <= 124.03000259399414)
        {
         class0+=0.2222222222222222;
         class1+=0.6111111111111112;
         class2+=0.16666666666666666;
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
         class0+=0.03571428571428571;
         class1+=0.07142857142857142;
         class2+=0.8928571428571429;
        }
      else
        {
         class0+=0.0;
         class1+=0.0;
         class2+=1.0;
        }
 // TREE: 99
  if (lumbar_lordosis_angle <= 47.53213119506836)
    if (pelvic_tilt <= 17.217758178710938)
      if (pelvic_radius <= 117.35956192016602)
        {
         class0+=0.45454545454545453;
         class1+=0.22727272727272727;
         class2+=0.3181818181818182;
        }
      else
        {
         class0+=0.15789473684210525;
         class1+=0.8421052631578947;
         class2+=0.0;
        }
    else
      if (pelvic_incidence <= 55.399030685424805)
        {
         class0+=0.8333333333333334;
         class1+=0.16666666666666666;
         class2+=0.0;
        }
      else
        {
         class0+=0.46153846153846156;
         class1+=0.23076923076923078;
         class2+=0.3076923076923077;
        }
  else
    if (pelvic_incidence <= 69.48115158081055)
      if (pelvic_radius <= 112.11275100708008)
        {
         class0+=0.045454545454545456;
         class1+=0.09090909090909091;
         class2+=0.8636363636363636;
        }
      else
        {
         class0+=0.14814814814814814;
         class1+=0.5555555555555556;
         class2+=0.2962962962962963;
        }
    else
      if (lumbar_lordosis_angle <= 61.07404136657715)
        {
         class0+=0.0;
         class1+=0.18181818181818182;
         class2+=0.8181818181818182;
        }
      else
        {
         class0+=0.0;
         class1+=0.03636363636363636;
         class2+=0.9636363636363636;
        }
  // VOTER
int classification = -1;
  if( class0>=class1 && class0>=class2)
    classification = 0;
  if( class1>=class0 && class1>=class2)
    classification = 1;
  if( class2>=class0 && class2>=class1)
    classification = 2;
 return classification;
}
}
