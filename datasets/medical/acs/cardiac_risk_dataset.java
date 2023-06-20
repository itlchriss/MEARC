class RandomForest { 

private static final int CLASS_0 = 0;
private static final int CLASS_1 = 1;
public int randomForest(double age,double heartRate,double systolicBloodPressure,double serumCreatinine,double cardiacArrestAtAdmission,double deviatedSTSegment,double elevatedCardiacEnzymes,double killipClass){
double  class0 = 0;
double  class1 = 0;
 // TREE: 0
  if (killipClass <= 2.5)
    if (age <= 56.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8753117206982544;
         class1+=0.12468827930174564;
        }
      else
        {
         class0+=0.5543478260869565;
         class1+=0.44565217391304346;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.3964984552008239;
         class1+=0.6035015447991761;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (age <= 35.5)
        {
         class0+=0.6;
         class1+=0.4;
        }
      else
        {
         class0+=0.08173076923076923;
         class1+=0.9182692307692307;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 1
  if (killipClass <= 2.5)
    if (systolicBloodPressure <= 136.5)
      if (heartRate <= 99.5)
        {
         class0+=0.4675;
         class1+=0.5325;
        }
      else
        {
         class0+=0.32367149758454106;
         class1+=0.6763285024154589;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8383838383838383;
         class1+=0.16161616161616163;
        }
      else
        {
         class0+=0.4523281596452328;
         class1+=0.5476718403547672;
        }
  else
    if (age <= 35.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.6;
         class1+=0.4;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (serumCreatinine <= 1.2050000429153442)
        {
         class0+=0.04242424242424243;
         class1+=0.9575757575757575;
        }
      else
        {
         class0+=0.0684931506849315;
         class1+=0.9315068493150684;
        }
 // TREE: 2
  if (heartRate <= 94.5)
    if (age <= 75.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.7924528301886793;
         class1+=0.20754716981132076;
        }
      else
        {
         class0+=0.436950146627566;
         class1+=0.5630498533724341;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.3258426966292135;
         class1+=0.6741573033707865;
        }
      else
        {
         class0+=0.01818181818181818;
         class1+=0.9818181818181818;
        }
  else
    if (systolicBloodPressure <= 119.5)
      if (heartRate <= 98.5)
        {
         class0+=0.4583333333333333;
         class1+=0.5416666666666666;
        }
      else
        {
         class0+=0.22762148337595908;
         class1+=0.7723785166240409;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.6633064516129032;
         class1+=0.33669354838709675;
        }
      else
        {
         class0+=0.1557377049180328;
         class1+=0.8442622950819673;
        }
 // TREE: 3
  if (cardiacArrestAtAdmission <= 0.5)
    if (age <= 56.5)
      if (killipClass <= 2.5)
        {
         class0+=0.7289473684210527;
         class1+=0.2710526315789474;
        }
      else
        {
         class0+=0.14583333333333334;
         class1+=0.8541666666666666;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.5404040404040404;
         class1+=0.4595959595959596;
        }
      else
        {
         class0+=0.1258741258741259;
         class1+=0.8741258741258742;
        }
  else
    if (serumCreatinine <= 0.7949999868869781)
      if (heartRate <= 85.5)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.5;
         class1+=0.5;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 4
  if (heartRate <= 94.5)
    if (killipClass <= 1.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7915831663326653;
         class1+=0.20841683366733466;
        }
      else
        {
         class0+=0.2626728110599078;
         class1+=0.7373271889400922;
        }
    else
      if (age <= 49.5)
        {
         class0+=0.4342105263157895;
         class1+=0.5657894736842105;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8888888888888888;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.6767676767676768;
         class1+=0.32323232323232326;
        }
      else
        {
         class0+=0.10476190476190476;
         class1+=0.8952380952380953;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.26021505376344084;
         class1+=0.7397849462365591;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 5
  if (heartRate <= 94.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.7253668763102725;
         class1+=0.2746331236897275;
        }
      else
        {
         class0+=0.14285714285714285;
         class1+=0.8571428571428571;
        }
    else
      if (age <= 55.5)
        {
         class0+=0.5714285714285714;
         class1+=0.42857142857142855;
        }
      else
        {
         class0+=0.16226415094339622;
         class1+=0.8377358490566038;
        }
  else
    if (age <= 58.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7321937321937322;
         class1+=0.2678062678062678;
        }
      else
        {
         class0+=0.2222222222222222;
         class1+=0.7777777777777778;
        }
    else
      if (systolicBloodPressure <= 107.5)
        {
         class0+=0.09883720930232558;
         class1+=0.9011627906976745;
        }
      else
        {
         class0+=0.33404255319148934;
         class1+=0.6659574468085107;
        }
 // TREE: 6
  if (deviatedSTSegment <= 0.5)
    if (killipClass <= 2.5)
      if (age <= 77.5)
        {
         class0+=0.8014941302027748;
         class1+=0.19850586979722518;
        }
      else
        {
         class0+=0.364;
         class1+=0.636;
        }
    else
      if (killipClass <= 3.5)
        {
         class0+=0.2;
         class1+=0.8;
        }
      else
        {
         class0+=0.038834951456310676;
         class1+=0.9611650485436893;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (heartRate <= 100.5)
        {
         class0+=0.38323353293413176;
         class1+=0.6167664670658682;
        }
      else
        {
         class0+=0.1793478260869565;
         class1+=0.8206521739130435;
        }
    else
      if (heartRate <= 58.5)
        {
         class0+=0.025;
         class1+=0.975;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 7
  if (elevatedCardiacEnzymes <= 0.5)
    if (age <= 77.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.863406408094435;
         class1+=0.13659359190556492;
        }
      else
        {
         class0+=0.35424354243542433;
         class1+=0.6457564575645757;
        }
    else
      if (systolicBloodPressure <= 217.5)
        {
         class0+=0.336734693877551;
         class1+=0.6632653061224489;
        }
      else
        {
         class0+=0.7916666666666666;
         class1+=0.20833333333333334;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (killipClass <= 1.5)
        {
         class0+=0.5069306930693069;
         class1+=0.49306930693069306;
        }
      else
        {
         class0+=0.10734463276836158;
         class1+=0.8926553672316384;
        }
    else
      if (heartRate <= 58.5)
        {
         class0+=0.025;
         class1+=0.975;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 8
  if (elevatedCardiacEnzymes <= 0.5)
    if (killipClass <= 2.5)
      if (killipClass <= 1.5)
        {
         class0+=0.7594627594627594;
         class1+=0.24053724053724054;
        }
      else
        {
         class0+=0.525;
         class1+=0.475;
        }
    else
      if (systolicBloodPressure <= 179.5)
        {
         class0+=0.07874015748031496;
         class1+=0.9212598425196851;
        }
      else
        {
         class0+=0.3275862068965517;
         class1+=0.6724137931034483;
        }
  else
    if (cardiacArrestAtAdmission <= 0.5)
      if (systolicBloodPressure <= 139.5)
        {
         class0+=0.14545454545454545;
         class1+=0.8545454545454545;
        }
      else
        {
         class0+=0.40476190476190477;
         class1+=0.5952380952380952;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 9
  if (killipClass <= 2.5)
    if (heartRate <= 98.5)
      if (age <= 74.5)
        {
         class0+=0.7044025157232704;
         class1+=0.29559748427672955;
        }
      else
        {
         class0+=0.31004366812227074;
         class1+=0.6899563318777293;
        }
    else
      if (systolicBloodPressure <= 119.5)
        {
         class0+=0.277602523659306;
         class1+=0.722397476340694;
        }
      else
        {
         class0+=0.5761245674740484;
         class1+=0.42387543252595156;
        }
  else
    if (heartRate <= 54.5)
      if (age <= 35.5)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.08;
         class1+=0.92;
        }
    else
      if (systolicBloodPressure <= 193.0)
        {
         class0+=0.03305785123966942;
         class1+=0.9669421487603306;
        }
      else
        {
         class0+=0.20588235294117646;
         class1+=0.7941176470588235;
        }
 // TREE: 10
  if (killipClass <= 2.5)
    if (cardiacArrestAtAdmission <= 0.5)
      if (systolicBloodPressure <= 139.5)
        {
         class0+=0.4043062200956938;
         class1+=0.5956937799043063;
        }
      else
        {
         class0+=0.6715083798882682;
         class1+=0.32849162011173183;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.07692307692307693;
         class1+=0.9230769230769231;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (age <= 35.5)
      if (heartRate <= 67.0)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.2608695652173913;
         class1+=0.7391304347826086;
        }
    else
      if (age <= 69.5)
        {
         class0+=0.08994708994708994;
         class1+=0.91005291005291;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 11
  if (killipClass <= 2.5)
    if (deviatedSTSegment <= 0.5)
      if (age <= 77.5)
        {
         class0+=0.8014941302027748;
         class1+=0.19850586979722518;
        }
      else
        {
         class0+=0.364;
         class1+=0.636;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.33797909407665505;
         class1+=0.662020905923345;
        }
      else
        {
         class0+=0.0034965034965034965;
         class1+=0.9965034965034965;
        }
  else
    if (systolicBloodPressure <= 179.5)
      if (age <= 35.5)
        {
         class0+=0.34782608695652173;
         class1+=0.6521739130434783;
        }
      else
        {
         class0+=0.009345794392523364;
         class1+=0.9906542056074766;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.2638888888888889;
         class1+=0.7361111111111112;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 12
  if (killipClass <= 2.5)
    if (cardiacArrestAtAdmission <= 0.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7206512425021423;
         class1+=0.27934875749785776;
        }
      else
        {
         class0+=0.17375886524822695;
         class1+=0.8262411347517731;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.07692307692307693;
         class1+=0.9230769230769231;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (serumCreatinine <= 0.8349999785423279)
      if (heartRate <= 63.5)
        {
         class0+=0.4;
         class1+=0.6;
        }
      else
        {
         class0+=0.06451612903225806;
         class1+=0.9354838709677419;
        }
    else
      if (age <= 31.5)
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
        }
      else
        {
         class0+=0.058365758754863814;
         class1+=0.9416342412451362;
        }
 // TREE: 13
  if (age <= 56.5)
    if (age <= 49.5)
      if (systolicBloodPressure <= 139.5)
        {
         class0+=0.552870090634441;
         class1+=0.4471299093655589;
        }
      else
        {
         class0+=0.7891566265060241;
         class1+=0.21084337349397592;
        }
    else
      if (systolicBloodPressure <= 165.0)
        {
         class0+=0.3971631205673759;
         class1+=0.6028368794326241;
        }
      else
        {
         class0+=0.6880733944954128;
         class1+=0.3119266055045872;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (killipClass <= 1.5)
        {
         class0+=0.5838926174496645;
         class1+=0.4161073825503356;
        }
      else
        {
         class0+=0.11917098445595854;
         class1+=0.8808290155440415;
        }
    else
      if (serumCreatinine <= 0.9449999928474426)
        {
         class0+=0.021897810218978103;
         class1+=0.9781021897810219;
        }
      else
        {
         class0+=0.07279693486590039;
         class1+=0.9272030651340997;
        }
 // TREE: 14
  if (elevatedCardiacEnzymes <= 0.5)
    if (systolicBloodPressure <= 119.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7075098814229249;
         class1+=0.2924901185770751;
        }
      else
        {
         class0+=0.06153846153846154;
         class1+=0.9384615384615385;
        }
    else
      if (age <= 58.5)
        {
         class0+=0.852760736196319;
         class1+=0.147239263803681;
        }
      else
        {
         class0+=0.608;
         class1+=0.392;
        }
  else
    if (age <= 55.5)
      if (killipClass <= 2.5)
        {
         class0+=0.5658263305322129;
         class1+=0.4341736694677871;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.18546365914786966;
         class1+=0.8145363408521303;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 15
  if (age <= 56.5)
    if (deviatedSTSegment <= 0.5)
      if (serumCreatinine <= 2.1149998903274536)
        {
         class0+=0.7957860615883307;
         class1+=0.20421393841166938;
        }
      else
        {
         class0+=1.0;
         class1+=0.0;
        }
    else
      if (heartRate <= 145.5)
        {
         class0+=0.28846153846153844;
         class1+=0.7115384615384616;
        }
      else
        {
         class0+=0.037037037037037035;
         class1+=0.9629629629629629;
        }
  else
    if (killipClass <= 1.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.6535087719298246;
         class1+=0.34649122807017546;
        }
      else
        {
         class0+=0.16216216216216217;
         class1+=0.8378378378378378;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.16483516483516483;
         class1+=0.8351648351648352;
        }
      else
        {
         class0+=0.04081632653061224;
         class1+=0.9591836734693877;
        }
 // TREE: 16
  if (deviatedSTSegment <= 0.5)
    if (age <= 56.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.8035426731078905;
         class1+=0.1964573268921095;
        }
      else
        {
         class0+=0.2;
         class1+=0.8;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.479328165374677;
         class1+=0.520671834625323;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (systolicBloodPressure <= 119.0)
        {
         class0+=0.06153846153846154;
         class1+=0.9384615384615385;
        }
      else
        {
         class0+=0.40271493212669685;
         class1+=0.5972850678733032;
        }
    else
      if (serumCreatinine <= 0.5450000166893005)
        {
         class0+=0.1;
         class1+=0.9;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 17
  if (killipClass <= 2.5)
    if (deviatedSTSegment <= 0.5)
      if (systolicBloodPressure <= 121.5)
        {
         class0+=0.5433255269320844;
         class1+=0.4566744730679157;
        }
      else
        {
         class0+=0.8026315789473685;
         class1+=0.19736842105263158;
        }
    else
      if (age <= 51.5)
        {
         class0+=0.34536082474226804;
         class1+=0.654639175257732;
        }
      else
        {
         class0+=0.08179419525065963;
         class1+=0.9182058047493403;
        }
  else
    if (systolicBloodPressure <= 179.5)
      if (age <= 35.5)
        {
         class0+=0.34782608695652173;
         class1+=0.6521739130434783;
        }
      else
        {
         class0+=0.009345794392523364;
         class1+=0.9906542056074766;
        }
    else
      if (age <= 65.5)
        {
         class0+=0.3392857142857143;
         class1+=0.6607142857142857;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 18
  if (systolicBloodPressure <= 139.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (heartRate <= 103.5)
        {
         class0+=0.6142322097378277;
         class1+=0.3857677902621723;
        }
      else
        {
         class0+=0.4262948207171315;
         class1+=0.5737051792828686;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.225;
         class1+=0.775;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.8450106157112527;
         class1+=0.15498938428874734;
        }
      else
        {
         class0+=0.25263157894736843;
         class1+=0.7473684210526316;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.5607734806629834;
         class1+=0.43922651933701656;
        }
      else
        {
         class0+=0.006535947712418301;
         class1+=0.9934640522875817;
        }
 // TREE: 19
  if (deviatedSTSegment <= 0.5)
    if (age <= 56.5)
      if (heartRate <= 93.5)
        {
         class0+=0.8581081081081081;
         class1+=0.14189189189189189;
        }
      else
        {
         class0+=0.7454545454545455;
         class1+=0.2545454545454545;
        }
    else
      if (serumCreatinine <= 1.4550000429153442)
        {
         class0+=0.5040387722132472;
         class1+=0.49596122778675283;
        }
      else
        {
         class0+=0.34705882352941175;
         class1+=0.6529411764705882;
        }
  else
    if (age <= 51.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.5739130434782609;
         class1+=0.4260869565217391;
        }
      else
        {
         class0+=0.008928571428571428;
         class1+=0.9910714285714286;
        }
    else
      if (systolicBloodPressure <= 176.5)
        {
         class0+=0.012345679012345678;
         class1+=0.9876543209876543;
        }
      else
        {
         class0+=0.20149253731343283;
         class1+=0.7985074626865671;
        }
 // TREE: 20
  if (deviatedSTSegment <= 0.5)
    if (killipClass <= 2.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.4782608695652174;
         class1+=0.5217391304347826;
        }
    else
      if (age <= 35.5)
        {
         class0+=0.6;
         class1+=0.4;
        }
      else
        {
         class0+=0.08173076923076923;
         class1+=0.9182692307692307;
        }
  else
    if (systolicBloodPressure <= 136.5)
      if (systolicBloodPressure <= 119.5)
        {
         class0+=0.03088803088803089;
         class1+=0.9691119691119691;
        }
      else
        {
         class0+=0.12941176470588237;
         class1+=0.8705882352941177;
        }
    else
      if (age <= 69.5)
        {
         class0+=0.3440366972477064;
         class1+=0.6559633027522935;
        }
      else
        {
         class0+=0.032520325203252036;
         class1+=0.967479674796748;
        }
 // TREE: 21
  if (killipClass <= 2.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (serumCreatinine <= 1.524999976158142)
        {
         class0+=0.7568681318681318;
         class1+=0.24313186813186813;
        }
      else
        {
         class0+=0.6608187134502924;
         class1+=0.3391812865497076;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.32662721893491126;
         class1+=0.6733727810650888;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (age <= 35.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.6;
         class1+=0.4;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (systolicBloodPressure <= 204.5)
        {
         class0+=0.022900763358778626;
         class1+=0.9770992366412213;
        }
      else
        {
         class0+=0.22448979591836735;
         class1+=0.7755102040816326;
        }
 // TREE: 22
  if (killipClass <= 2.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 1.5)
        {
         class0+=0.7594627594627594;
         class1+=0.24053724053724054;
        }
      else
        {
         class0+=0.525;
         class1+=0.475;
        }
    else
      if (systolicBloodPressure <= 141.5)
        {
         class0+=0.17411764705882352;
         class1+=0.8258823529411765;
        }
      else
        {
         class0+=0.463302752293578;
         class1+=0.536697247706422;
        }
  else
    if (age <= 35.5)
      if (age <= 31.5)
        {
         class0+=0.5555555555555556;
         class1+=0.4444444444444444;
        }
      else
        {
         class0+=0.35;
         class1+=0.65;
        }
    else
      if (killipClass <= 3.5)
        {
         class0+=0.09815950920245399;
         class1+=0.901840490797546;
        }
      else
        {
         class0+=0.006756756756756757;
         class1+=0.9932432432432432;
        }
 // TREE: 23
  if (elevatedCardiacEnzymes <= 0.5)
    if (age <= 77.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.863406408094435;
         class1+=0.13659359190556492;
        }
      else
        {
         class0+=0.35424354243542433;
         class1+=0.6457564575645757;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.5222929936305732;
         class1+=0.47770700636942676;
        }
      else
        {
         class0+=0.047619047619047616;
         class1+=0.9523809523809523;
        }
  else
    if (age <= 55.5)
      if (heartRate <= 94.5)
        {
         class0+=0.5714285714285714;
         class1+=0.42857142857142855;
        }
      else
        {
         class0+=0.4072398190045249;
         class1+=0.5927601809954751;
        }
    else
      if (systolicBloodPressure <= 162.5)
        {
         class0+=0.04155844155844156;
         class1+=0.9584415584415584;
        }
      else
        {
         class0+=0.27102803738317754;
         class1+=0.7289719626168224;
        }
 // TREE: 24
  if (killipClass <= 2.5)
    if (cardiacArrestAtAdmission <= 0.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.7483069977426636;
         class1+=0.2516930022573363;
        }
      else
        {
         class0+=0.32662721893491126;
         class1+=0.6733727810650888;
        }
    else
      if (age <= 38.5)
        {
         class0+=0.25;
         class1+=0.75;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (systolicBloodPressure <= 179.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.0641025641025641;
         class1+=0.9358974358974359;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (serumCreatinine <= 1.3550000190734863)
        {
         class0+=0.1506849315068493;
         class1+=0.8493150684931506;
        }
      else
        {
         class0+=0.26666666666666666;
         class1+=0.7333333333333333;
        }
 // TREE: 25
  if (deviatedSTSegment <= 0.5)
    if (killipClass <= 2.5)
      if (age <= 77.5)
        {
         class0+=0.8014941302027748;
         class1+=0.19850586979722518;
        }
      else
        {
         class0+=0.364;
         class1+=0.636;
        }
    else
      if (killipClass <= 3.5)
        {
         class0+=0.2;
         class1+=0.8;
        }
      else
        {
         class0+=0.038834951456310676;
         class1+=0.9611650485436893;
        }
  else
    if (serumCreatinine <= 1.2050000429153442)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.2895927601809955;
         class1+=0.7104072398190046;
        }
      else
        {
         class0+=0.005494505494505495;
         class1+=0.9945054945054945;
        }
    else
      if (systolicBloodPressure <= 127.0)
        {
         class0+=0.018018018018018018;
         class1+=0.9819819819819819;
        }
      else
        {
         class0+=0.18128654970760233;
         class1+=0.8187134502923976;
        }
 // TREE: 26
  if (elevatedCardiacEnzymes <= 0.5)
    if (systolicBloodPressure <= 119.5)
      if (killipClass <= 2.5)
        {
         class0+=0.5859872611464968;
         class1+=0.4140127388535032;
        }
      else
        {
         class0+=0.043478260869565216;
         class1+=0.9565217391304348;
        }
    else
      if (systolicBloodPressure <= 195.5)
        {
         class0+=0.6849593495934959;
         class1+=0.3150406504065041;
        }
      else
        {
         class0+=0.8086124401913876;
         class1+=0.19138755980861244;
        }
  else
    if (heartRate <= 89.5)
      if (age <= 55.5)
        {
         class0+=0.5771428571428572;
         class1+=0.4228571428571429;
        }
      else
        {
         class0+=0.17154811715481172;
         class1+=0.8284518828451883;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.3401015228426396;
         class1+=0.6598984771573604;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 27
  if (deviatedSTSegment <= 0.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.2396694214876033;
         class1+=0.7603305785123967;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.4098360655737705;
         class1+=0.5901639344262295;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (age <= 51.5)
        {
         class0+=0.5739130434782609;
         class1+=0.4260869565217391;
        }
      else
        {
         class0+=0.13135593220338984;
         class1+=0.8686440677966102;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.003048780487804878;
         class1+=0.9969512195121951;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 28
  if (age <= 56.5)
    if (killipClass <= 2.5)
      if (heartRate <= 145.5)
        {
         class0+=0.7401129943502824;
         class1+=0.2598870056497175;
        }
      else
        {
         class0+=0.5081967213114754;
         class1+=0.4918032786885246;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.25925925925925924;
         class1+=0.7407407407407407;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (heartRate <= 91.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.6255144032921811;
         class1+=0.37448559670781895;
        }
      else
        {
         class0+=0.16736401673640167;
         class1+=0.8326359832635983;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.41865509761388287;
         class1+=0.5813449023861171;
        }
      else
        {
         class0+=0.03278688524590164;
         class1+=0.9672131147540983;
        }
 // TREE: 29
  if (cardiacArrestAtAdmission <= 0.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (age <= 77.5)
        {
         class0+=0.7107728337236534;
         class1+=0.2892271662763466;
        }
      else
        {
         class0+=0.391705069124424;
         class1+=0.6082949308755761;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.4098360655737705;
         class1+=0.5901639344262295;
        }
      else
        {
         class0+=0.003048780487804878;
         class1+=0.9969512195121951;
        }
  else
    if (systolicBloodPressure <= 158.5)
      if (heartRate <= 94.5)
        {
         class0+=0.14285714285714285;
         class1+=0.8571428571428571;
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
 // TREE: 30
  if (systolicBloodPressure <= 139.5)
    if (age <= 49.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.7034883720930233;
         class1+=0.29651162790697677;
        }
      else
        {
         class0+=0.389937106918239;
         class1+=0.610062893081761;
        }
    else
      if (heartRate <= 103.5)
        {
         class0+=0.2897727272727273;
         class1+=0.7102272727272727;
        }
      else
        {
         class0+=0.17261904761904762;
         class1+=0.8273809523809523;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.8450106157112527;
         class1+=0.15498938428874734;
        }
      else
        {
         class0+=0.25263157894736843;
         class1+=0.7473684210526316;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.40476190476190477;
         class1+=0.5952380952380952;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 31
  if (deviatedSTSegment <= 0.5)
    if (age <= 56.5)
      if (heartRate <= 93.5)
        {
         class0+=0.8581081081081081;
         class1+=0.14189189189189189;
        }
      else
        {
         class0+=0.7454545454545455;
         class1+=0.2545454545454545;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.5838926174496645;
         class1+=0.4161073825503356;
        }
      else
        {
         class0+=0.11917098445595854;
         class1+=0.8808290155440415;
        }
  else
    if (cardiacArrestAtAdmission <= 0.5)
      if (heartRate <= 97.5)
        {
         class0+=0.2012779552715655;
         class1+=0.7987220447284346;
        }
      else
        {
         class0+=0.09668508287292818;
         class1+=0.9033149171270718;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 32
  if (age <= 56.5)
    if (killipClass <= 2.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8753117206982544;
         class1+=0.12468827930174564;
        }
      else
        {
         class0+=0.5543478260869565;
         class1+=0.44565217391304346;
        }
    else
      if (systolicBloodPressure <= 225.0)
        {
         class0+=0.12318840579710146;
         class1+=0.8768115942028986;
        }
      else
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
        }
  else
    if (serumCreatinine <= 1.4550000429153442)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.5793991416309013;
         class1+=0.4206008583690987;
        }
      else
        {
         class0+=0.12987012987012986;
         class1+=0.8701298701298701;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.375;
         class1+=0.625;
        }
      else
        {
         class0+=0.0975609756097561;
         class1+=0.9024390243902439;
        }
 // TREE: 33
  if (elevatedCardiacEnzymes <= 0.5)
    if (systolicBloodPressure <= 119.5)
      if (age <= 78.5)
        {
         class0+=0.5598705501618123;
         class1+=0.4401294498381877;
        }
      else
        {
         class0+=0.1891891891891892;
         class1+=0.8108108108108109;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.86875;
         class1+=0.13125;
        }
      else
        {
         class0+=0.40271493212669685;
         class1+=0.5972850678733032;
        }
  else
    if (killipClass <= 2.5)
      if (killipClass <= 1.5)
        {
         class0+=0.33550913838120106;
         class1+=0.664490861618799;
        }
      else
        {
         class0+=0.2;
         class1+=0.8;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 34
  if (deviatedSTSegment <= 0.5)
    if (heartRate <= 93.5)
      if (heartRate <= 85.5)
        {
         class0+=0.6685606060606061;
         class1+=0.3314393939393939;
        }
      else
        {
         class0+=0.7757009345794392;
         class1+=0.22429906542056074;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.7679012345679013;
         class1+=0.23209876543209876;
        }
      else
        {
         class0+=0.33066666666666666;
         class1+=0.6693333333333333;
        }
  else
    if (age <= 51.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.5739130434782609;
         class1+=0.4260869565217391;
        }
      else
        {
         class0+=0.008928571428571428;
         class1+=0.9910714285714286;
        }
    else
      if (age <= 69.5)
        {
         class0+=0.12272727272727273;
         class1+=0.8772727272727273;
        }
      else
        {
         class0+=0.01680672268907563;
         class1+=0.9831932773109243;
        }
 // TREE: 35
  if (heartRate <= 94.5)
    if (systolicBloodPressure <= 136.5)
      if (serumCreatinine <= 1.6100000143051147)
        {
         class0+=0.4225721784776903;
         class1+=0.5774278215223098;
        }
      else
        {
         class0+=0.22580645161290322;
         class1+=0.7741935483870968;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7827298050139275;
         class1+=0.21727019498607242;
        }
      else
        {
         class0+=0.32167832167832167;
         class1+=0.6783216783216783;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.6597774244833068;
         class1+=0.34022257551669316;
        }
      else
        {
         class0+=0.08148148148148149;
         class1+=0.9185185185185185;
        }
    else
      if (heartRate <= 145.5)
        {
         class0+=0.12307692307692308;
         class1+=0.8769230769230769;
        }
      else
        {
         class0+=0.015151515151515152;
         class1+=0.9848484848484849;
        }
 // TREE: 36
  if (age <= 56.5)
    if (systolicBloodPressure <= 139.5)
      if (heartRate <= 59.5)
        {
         class0+=0.7540983606557377;
         class1+=0.2459016393442623;
        }
      else
        {
         class0+=0.4671916010498688;
         class1+=0.5328083989501312;
        }
    else
      if (age <= 49.5)
        {
         class0+=0.7891566265060241;
         class1+=0.21084337349397592;
        }
      else
        {
         class0+=0.6474820143884892;
         class1+=0.35251798561151076;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7531486146095718;
         class1+=0.24685138539042822;
        }
      else
        {
         class0+=0.1073170731707317;
         class1+=0.8926829268292683;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.1836734693877551;
         class1+=0.8163265306122449;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 37
  if (deviatedSTSegment <= 0.5)
    if (killipClass <= 2.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.4782608695652174;
         class1+=0.5217391304347826;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.2396694214876033;
         class1+=0.7603305785123967;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (systolicBloodPressure <= 136.5)
      if (age <= 42.5)
        {
         class0+=0.2112676056338028;
         class1+=0.7887323943661971;
        }
      else
        {
         class0+=0.014652014652014652;
         class1+=0.9853479853479854;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.430939226519337;
         class1+=0.569060773480663;
        }
      else
        {
         class0+=0.00625;
         class1+=0.99375;
        }
 // TREE: 38
  if (age <= 56.5)
    if (killipClass <= 2.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8753117206982544;
         class1+=0.12468827930174564;
        }
      else
        {
         class0+=0.5543478260869565;
         class1+=0.44565217391304346;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.25925925925925924;
         class1+=0.7407407407407407;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (systolicBloodPressure <= 136.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.39925373134328357;
         class1+=0.6007462686567164;
        }
      else
        {
         class0+=0.017361111111111112;
         class1+=0.9826388888888888;
        }
    else
      if (systolicBloodPressure <= 201.5)
        {
         class0+=0.38620689655172413;
         class1+=0.6137931034482759;
        }
      else
        {
         class0+=0.576530612244898;
         class1+=0.42346938775510207;
        }
 // TREE: 39
  if (deviatedSTSegment <= 0.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.2396694214876033;
         class1+=0.7603305785123967;
        }
    else
      if (age <= 55.5)
        {
         class0+=0.7102473498233216;
         class1+=0.28975265017667845;
        }
      else
        {
         class0+=0.18546365914786966;
         class1+=0.8145363408521303;
        }
  else
    if (systolicBloodPressure <= 136.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.11176470588235295;
         class1+=0.888235294117647;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.430939226519337;
         class1+=0.569060773480663;
        }
      else
        {
         class0+=0.00625;
         class1+=0.99375;
        }
 // TREE: 40
  if (age <= 56.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (age <= 49.5)
        {
         class0+=0.8115942028985508;
         class1+=0.18840579710144928;
        }
      else
        {
         class0+=0.6715328467153284;
         class1+=0.3284671532846715;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.5543478260869565;
         class1+=0.44565217391304346;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (heartRate <= 91.5)
      if (age <= 75.5)
        {
         class0+=0.5095057034220533;
         class1+=0.49049429657794674;
        }
      else
        {
         class0+=0.2648401826484018;
         class1+=0.7351598173515982;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.41865509761388287;
         class1+=0.5813449023861171;
        }
      else
        {
         class0+=0.03278688524590164;
         class1+=0.9672131147540983;
        }
 // TREE: 41
  if (systolicBloodPressure <= 139.5)
    if (systolicBloodPressure <= 119.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.30993377483443707;
         class1+=0.6900662251655629;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (age <= 42.5)
        {
         class0+=0.6956521739130435;
         class1+=0.30434782608695654;
        }
      else
        {
         class0+=0.324468085106383;
         class1+=0.675531914893617;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (age <= 76.5)
        {
         class0+=0.8161888701517707;
         class1+=0.18381112984822934;
        }
      else
        {
         class0+=0.41916167664670656;
         class1+=0.5808383233532934;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.22929936305732485;
         class1+=0.7707006369426752;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 42
  if (age <= 56.5)
    if (killipClass <= 2.5)
      if (killipClass <= 1.5)
        {
         class0+=0.743065693430657;
         class1+=0.2569343065693431;
        }
      else
        {
         class0+=0.5476190476190477;
         class1+=0.4523809523809524;
        }
    else
      if (systolicBloodPressure <= 225.0)
        {
         class0+=0.12318840579710146;
         class1+=0.8768115942028986;
        }
      else
        {
         class0+=0.6666666666666666;
         class1+=0.3333333333333333;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (killipClass <= 1.5)
        {
         class0+=0.5838926174496645;
         class1+=0.4161073825503356;
        }
      else
        {
         class0+=0.11917098445595854;
         class1+=0.8808290155440415;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.1073170731707317;
         class1+=0.8926829268292683;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 43
  if (deviatedSTSegment <= 0.5)
    if (age <= 56.5)
      if (killipClass <= 2.5)
        {
         class0+=0.9089184060721063;
         class1+=0.09108159392789374;
        }
      else
        {
         class0+=0.21212121212121213;
         class1+=0.7878787878787878;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.7531486146095718;
         class1+=0.24685138539042822;
        }
      else
        {
         class0+=0.1836734693877551;
         class1+=0.8163265306122449;
        }
  else
    if (systolicBloodPressure <= 136.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.11176470588235295;
         class1+=0.888235294117647;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.430939226519337;
         class1+=0.569060773480663;
        }
      else
        {
         class0+=0.00625;
         class1+=0.99375;
        }
 // TREE: 44
  if (elevatedCardiacEnzymes <= 0.5)
    if (heartRate <= 100.5)
      if (systolicBloodPressure <= 118.5)
        {
         class0+=0.5714285714285714;
         class1+=0.42857142857142855;
        }
      else
        {
         class0+=0.7914285714285715;
         class1+=0.20857142857142857;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7617728531855956;
         class1+=0.23822714681440443;
        }
      else
        {
         class0+=0.1793478260869565;
         class1+=0.8206521739130435;
        }
  else
    if (systolicBloodPressure <= 139.5)
      if (serumCreatinine <= 0.5649999976158142)
        {
         class0+=0.4117647058823529;
         class1+=0.5882352941176471;
        }
      else
        {
         class0+=0.13429752066115702;
         class1+=0.8657024793388429;
        }
    else
      if (serumCreatinine <= 1.5449999570846558)
        {
         class0+=0.38072289156626504;
         class1+=0.619277108433735;
        }
      else
        {
         class0+=0.46;
         class1+=0.54;
        }
 // TREE: 45
  if (deviatedSTSegment <= 0.5)
    if (systolicBloodPressure <= 121.5)
      if (age <= 51.5)
        {
         class0+=0.7049180327868853;
         class1+=0.29508196721311475;
        }
      else
        {
         class0+=0.314540059347181;
         class1+=0.685459940652819;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.8026315789473685;
         class1+=0.19736842105263158;
        }
      else
        {
         class0+=0.1925925925925926;
         class1+=0.8074074074074075;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (age <= 51.5)
        {
         class0+=0.5739130434782609;
         class1+=0.4260869565217391;
        }
      else
        {
         class0+=0.13135593220338984;
         class1+=0.8686440677966102;
        }
    else
      if (age <= 36.5)
        {
         class0+=0.024390243902439025;
         class1+=0.975609756097561;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 46
  if (killipClass <= 2.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (heartRate <= 100.5)
        {
         class0+=0.8173719376391982;
         class1+=0.18262806236080179;
        }
      else
        {
         class0+=0.66;
         class1+=0.34;
        }
    else
      if (systolicBloodPressure <= 141.5)
        {
         class0+=0.17411764705882352;
         class1+=0.8258823529411765;
        }
      else
        {
         class0+=0.463302752293578;
         class1+=0.536697247706422;
        }
  else
    if (serumCreatinine <= 0.8349999785423279)
      if (killipClass <= 3.5)
        {
         class0+=0.17647058823529413;
         class1+=0.8235294117647058;
        }
      else
        {
         class0+=0.038461538461538464;
         class1+=0.9615384615384616;
        }
    else
      if (systolicBloodPressure <= 180.5)
        {
         class0+=0.032432432432432434;
         class1+=0.9675675675675676;
        }
      else
        {
         class0+=0.16666666666666666;
         class1+=0.8333333333333334;
        }
 // TREE: 47
  if (killipClass <= 2.5)
    if (heartRate <= 98.5)
      if (systolicBloodPressure <= 160.5)
        {
         class0+=0.49904397705544934;
         class1+=0.5009560229445507;
        }
      else
        {
         class0+=0.7543859649122807;
         class1+=0.24561403508771928;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.47732426303854875;
         class1+=0.5226757369614512;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.2396694214876033;
         class1+=0.7603305785123967;
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
 // TREE: 48
  if (deviatedSTSegment <= 0.5)
    if (systolicBloodPressure <= 121.5)
      if (heartRate <= 98.5)
        {
         class0+=0.5603112840466926;
         class1+=0.4396887159533074;
        }
      else
        {
         class0+=0.34600760456273766;
         class1+=0.6539923954372624;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.8026315789473685;
         class1+=0.19736842105263158;
        }
      else
        {
         class0+=0.1925925925925926;
         class1+=0.8074074074074075;
        }
  else
    if (age <= 51.5)
      if (systolicBloodPressure <= 119.5)
        {
         class0+=0.0963855421686747;
         class1+=0.9036144578313253;
        }
      else
        {
         class0+=0.4097222222222222;
         class1+=0.5902777777777778;
        }
    else
      if (systolicBloodPressure <= 176.5)
        {
         class0+=0.012345679012345678;
         class1+=0.9876543209876543;
        }
      else
        {
         class0+=0.20149253731343283;
         class1+=0.7985074626865671;
        }
 // TREE: 49
  if (systolicBloodPressure <= 139.5)
    if (deviatedSTSegment <= 0.5)
      if (killipClass <= 1.5)
        {
         class0+=0.6053719008264463;
         class1+=0.39462809917355374;
        }
      else
        {
         class0+=0.14035087719298245;
         class1+=0.8596491228070176;
        }
    else
      if (serumCreatinine <= 0.6349999904632568)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.07761194029850746;
         class1+=0.9223880597014925;
        }
  else
    if (heartRate <= 89.5)
      if (age <= 75.5)
        {
         class0+=0.764525993883792;
         class1+=0.23547400611620795;
        }
      else
        {
         class0+=0.34615384615384615;
         class1+=0.6538461538461539;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.7057057057057057;
         class1+=0.29429429429429427;
        }
      else
        {
         class0+=0.3312302839116719;
         class1+=0.668769716088328;
        }
 // TREE: 50
  if (deviatedSTSegment <= 0.5)
    if (systolicBloodPressure <= 121.5)
      if (killipClass <= 1.5)
        {
         class0+=0.5688311688311688;
         class1+=0.43116883116883115;
        }
      else
        {
         class0+=0.11851851851851852;
         class1+=0.8814814814814815;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.869198312236287;
         class1+=0.1308016877637131;
        }
      else
        {
         class0+=0.5320665083135392;
         class1+=0.4679334916864608;
        }
  else
    if (serumCreatinine <= 1.2050000429153442)
      if (killipClass <= 1.5)
        {
         class0+=0.20766773162939298;
         class1+=0.792332268370607;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.25384615384615383;
         class1+=0.7461538461538462;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 51
  if (deviatedSTSegment <= 0.5)
    if (age <= 56.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8839285714285714;
         class1+=0.11607142857142858;
        }
      else
        {
         class0+=0.7;
         class1+=0.3;
        }
    else
      if (systolicBloodPressure <= 121.5)
        {
         class0+=0.2905405405405405;
         class1+=0.7094594594594594;
        }
      else
        {
         class0+=0.5780933062880325;
         class1+=0.42190669371196754;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (heartRate <= 100.5)
        {
         class0+=0.38323353293413176;
         class1+=0.6167664670658682;
        }
      else
        {
         class0+=0.1793478260869565;
         class1+=0.8206521739130435;
        }
    else
      if (systolicBloodPressure <= 205.0)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.023255813953488372;
         class1+=0.9767441860465116;
        }
 // TREE: 52
  if (age <= 56.5)
    if (killipClass <= 2.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.7289473684210527;
         class1+=0.2710526315789474;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8888888888888888;
        }
    else
      if (heartRate <= 55.0)
        {
         class0+=0.46153846153846156;
         class1+=0.5384615384615384;
        }
      else
        {
         class0+=0.11450381679389313;
         class1+=0.8854961832061069;
        }
  else
    if (age <= 77.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.4157458563535912;
         class1+=0.5842541436464088;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.30847457627118646;
         class1+=0.6915254237288135;
        }
      else
        {
         class0+=0.006493506493506494;
         class1+=0.9935064935064936;
        }
 // TREE: 53
  if (deviatedSTSegment <= 0.5)
    if (systolicBloodPressure <= 121.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.7104247104247104;
         class1+=0.28957528957528955;
        }
      else
        {
         class0+=0.19540229885057472;
         class1+=0.8045977011494253;
        }
    else
      if (heartRate <= 93.5)
        {
         class0+=0.7719298245614035;
         class1+=0.22807017543859648;
        }
      else
        {
         class0+=0.6612903225806451;
         class1+=0.3387096774193548;
        }
  else
    if (heartRate <= 97.5)
      if (systolicBloodPressure <= 136.5)
        {
         class0+=0.06962025316455696;
         class1+=0.930379746835443;
        }
      else
        {
         class0+=0.325;
         class1+=0.675;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.18421052631578946;
         class1+=0.8157894736842105;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 54
  if (systolicBloodPressure <= 139.5)
    if (deviatedSTSegment <= 0.5)
      if (systolicBloodPressure <= 119.5)
        {
         class0+=0.44930417495029823;
         class1+=0.5506958250497018;
        }
      else
        {
         class0+=0.5986842105263158;
         class1+=0.40131578947368424;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.09252669039145907;
         class1+=0.9074733096085409;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (killipClass <= 2.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8450106157112527;
         class1+=0.15498938428874734;
        }
      else
        {
         class0+=0.4604966139954853;
         class1+=0.5395033860045146;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.25263157894736843;
         class1+=0.7473684210526316;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 55
  if (elevatedCardiacEnzymes <= 0.5)
    if (heartRate <= 100.5)
      if (age <= 77.5)
        {
         class0+=0.7837209302325582;
         class1+=0.21627906976744185;
        }
      else
        {
         class0+=0.44036697247706424;
         class1+=0.5596330275229358;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7617728531855956;
         class1+=0.23822714681440443;
        }
      else
        {
         class0+=0.1793478260869565;
         class1+=0.8206521739130435;
        }
  else
    if (age <= 55.5)
      if (heartRate <= 94.5)
        {
         class0+=0.5714285714285714;
         class1+=0.42857142857142855;
        }
      else
        {
         class0+=0.4072398190045249;
         class1+=0.5927601809954751;
        }
    else
      if (heartRate <= 92.5)
        {
         class0+=0.16929133858267717;
         class1+=0.8307086614173228;
        }
      else
        {
         class0+=0.08985507246376812;
         class1+=0.9101449275362319;
        }
 // TREE: 56
  if (cardiacArrestAtAdmission <= 0.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (systolicBloodPressure <= 119.5)
        {
         class0+=0.4921052631578947;
         class1+=0.5078947368421053;
        }
      else
        {
         class0+=0.7308248914616498;
         class1+=0.2691751085383502;
        }
    else
      if (heartRate <= 89.5)
        {
         class0+=0.3506172839506173;
         class1+=0.6493827160493827;
        }
      else
        {
         class0+=0.2255892255892256;
         class1+=0.7744107744107744;
        }
  else
    if (serumCreatinine <= 0.7949999868869781)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.5;
         class1+=0.5;
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
 // TREE: 57
  if (deviatedSTSegment <= 0.5)
    if (systolicBloodPressure <= 121.5)
      if (killipClass <= 1.5)
        {
         class0+=0.5688311688311688;
         class1+=0.43116883116883115;
        }
      else
        {
         class0+=0.11851851851851852;
         class1+=0.8814814814814815;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.8026315789473685;
         class1+=0.19736842105263158;
        }
      else
        {
         class0+=0.1925925925925926;
         class1+=0.8074074074074075;
        }
  else
    if (systolicBloodPressure <= 136.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.05571847507331378;
         class1+=0.9442815249266863;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (age <= 69.5)
        {
         class0+=0.3440366972477064;
         class1+=0.6559633027522935;
        }
      else
        {
         class0+=0.032520325203252036;
         class1+=0.967479674796748;
        }
 // TREE: 58
  if (deviatedSTSegment <= 0.5)
    if (killipClass <= 2.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.7206512425021423;
         class1+=0.27934875749785776;
        }
      else
        {
         class0+=0.05;
         class1+=0.95;
        }
    else
      if (killipClass <= 3.5)
        {
         class0+=0.2;
         class1+=0.8;
        }
      else
        {
         class0+=0.038834951456310676;
         class1+=0.9611650485436893;
        }
  else
    if (age <= 51.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.5739130434782609;
         class1+=0.4260869565217391;
        }
      else
        {
         class0+=0.008928571428571428;
         class1+=0.9910714285714286;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.13135593220338984;
         class1+=0.8686440677966102;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 59
  if (killipClass <= 2.5)
    if (heartRate <= 98.5)
      if (heartRate <= 51.5)
        {
         class0+=0.5041322314049587;
         class1+=0.49586776859504134;
        }
      else
        {
         class0+=0.6155913978494624;
         class1+=0.3844086021505376;
        }
    else
      if (systolicBloodPressure <= 119.5)
        {
         class0+=0.277602523659306;
         class1+=0.722397476340694;
        }
      else
        {
         class0+=0.5761245674740484;
         class1+=0.42387543252595156;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (serumCreatinine <= 0.8349999785423279)
        {
         class0+=0.20833333333333334;
         class1+=0.7916666666666666;
        }
      else
        {
         class0+=0.10555555555555556;
         class1+=0.8944444444444445;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 60
  if (killipClass <= 2.5)
    if (age <= 56.5)
      if (systolicBloodPressure <= 135.5)
        {
         class0+=0.5936599423631124;
         class1+=0.40634005763688763;
        }
      else
        {
         class0+=0.8270142180094787;
         class1+=0.17298578199052134;
        }
    else
      if (age <= 77.5)
        {
         class0+=0.4741100323624595;
         class1+=0.5258899676375405;
        }
      else
        {
         class0+=0.24664879356568364;
         class1+=0.7533512064343163;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 3.5)
        {
         class0+=0.26881720430107525;
         class1+=0.7311827956989247;
        }
      else
        {
         class0+=0.043478260869565216;
         class1+=0.9565217391304348;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 61
  if (cardiacArrestAtAdmission <= 0.5)
    if (killipClass <= 2.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7206512425021423;
         class1+=0.27934875749785776;
        }
      else
        {
         class0+=0.17375886524822695;
         class1+=0.8262411347517731;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.12719298245614036;
         class1+=0.8728070175438597;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (age <= 38.5)
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
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 62
  if (deviatedSTSegment <= 0.5)
    if (systolicBloodPressure <= 121.5)
      if (killipClass <= 1.5)
        {
         class0+=0.5688311688311688;
         class1+=0.43116883116883115;
        }
      else
        {
         class0+=0.11851851851851852;
         class1+=0.8814814814814815;
        }
    else
      if (heartRate <= 93.5)
        {
         class0+=0.7719298245614035;
         class1+=0.22807017543859648;
        }
      else
        {
         class0+=0.6612903225806451;
         class1+=0.3387096774193548;
        }
  else
    if (cardiacArrestAtAdmission <= 0.5)
      if (heartRate <= 97.5)
        {
         class0+=0.2012779552715655;
         class1+=0.7987220447284346;
        }
      else
        {
         class0+=0.09668508287292818;
         class1+=0.9033149171270718;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 63
  if (deviatedSTSegment <= 0.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.2396694214876033;
         class1+=0.7603305785123967;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.4098360655737705;
         class1+=0.5901639344262295;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (cardiacArrestAtAdmission <= 0.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.27953890489913547;
         class1+=0.7204610951008645;
        }
      else
        {
         class0+=0.003048780487804878;
         class1+=0.9969512195121951;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 64
  if (systolicBloodPressure <= 139.5)
    if (heartRate <= 98.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.6188524590163934;
         class1+=0.38114754098360654;
        }
      else
        {
         class0+=0.19753086419753085;
         class1+=0.8024691358024691;
        }
    else
      if (systolicBloodPressure <= 119.5)
        {
         class0+=0.22762148337595908;
         class1+=0.7723785166240409;
        }
      else
        {
         class0+=0.3900709219858156;
         class1+=0.6099290780141844;
        }
  else
    if (killipClass <= 2.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.8204334365325078;
         class1+=0.17956656346749225;
        }
      else
        {
         class0+=0.26865671641791045;
         class1+=0.7313432835820896;
        }
    else
      if (serumCreatinine <= 0.7150000035762787)
        {
         class0+=0.05;
         class1+=0.95;
        }
      else
        {
         class0+=0.1564625850340136;
         class1+=0.8435374149659864;
        }
 // TREE: 65
  if (killipClass <= 2.5)
    if (deviatedSTSegment <= 0.5)
      if (killipClass <= 1.5)
        {
         class0+=0.7360980207351555;
         class1+=0.2639019792648445;
        }
      else
        {
         class0+=0.48412698412698413;
         class1+=0.5158730158730159;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.33797909407665505;
         class1+=0.662020905923345;
        }
      else
        {
         class0+=0.0034965034965034965;
         class1+=0.9965034965034965;
        }
  else
    if (age <= 35.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.5454545454545454;
         class1+=0.45454545454545453;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (age <= 69.5)
        {
         class0+=0.08994708994708994;
         class1+=0.91005291005291;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 66
  if (deviatedSTSegment <= 0.5)
    if (killipClass <= 2.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.4782608695652174;
         class1+=0.5217391304347826;
        }
    else
      if (age <= 35.5)
        {
         class0+=0.6;
         class1+=0.4;
        }
      else
        {
         class0+=0.08173076923076923;
         class1+=0.9182692307692307;
        }
  else
    if (systolicBloodPressure <= 136.5)
      if (age <= 42.5)
        {
         class0+=0.2112676056338028;
         class1+=0.7887323943661971;
        }
      else
        {
         class0+=0.014652014652014652;
         class1+=0.9853479853479854;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.430939226519337;
         class1+=0.569060773480663;
        }
      else
        {
         class0+=0.00625;
         class1+=0.99375;
        }
 // TREE: 67
  if (deviatedSTSegment <= 0.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (systolicBloodPressure <= 98.5)
        {
         class0+=0.6604938271604939;
         class1+=0.3395061728395062;
        }
      else
        {
         class0+=0.8563922942206655;
         class1+=0.1436077057793345;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.5069306930693069;
         class1+=0.49306930693069306;
        }
      else
        {
         class0+=0.10734463276836158;
         class1+=0.8926553672316384;
        }
  else
    if (killipClass <= 1.5)
      if (systolicBloodPressure <= 136.5)
        {
         class0+=0.07196969696969698;
         class1+=0.928030303030303;
        }
      else
        {
         class0+=0.3038461538461538;
         class1+=0.6961538461538461;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 68
  if (age <= 56.5)
    if (age <= 49.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.8387799564270153;
         class1+=0.16122004357298475;
        }
      else
        {
         class0+=0.29411764705882354;
         class1+=0.7058823529411765;
        }
    else
      if (systolicBloodPressure <= 165.0)
        {
         class0+=0.3971631205673759;
         class1+=0.6028368794326241;
        }
      else
        {
         class0+=0.6880733944954128;
         class1+=0.3119266055045872;
        }
  else
    if (heartRate <= 91.5)
      if (killipClass <= 1.5)
        {
         class0+=0.4891891891891892;
         class1+=0.5108108108108108;
        }
      else
        {
         class0+=0.09821428571428571;
         class1+=0.9017857142857143;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.35660377358490564;
         class1+=0.6433962264150943;
        }
      else
        {
         class0+=0.06857142857142857;
         class1+=0.9314285714285714;
        }
 // TREE: 69
  if (killipClass <= 2.5)
    if (deviatedSTSegment <= 0.5)
      if (systolicBloodPressure <= 121.5)
        {
         class0+=0.5433255269320844;
         class1+=0.4566744730679157;
        }
      else
        {
         class0+=0.8026315789473685;
         class1+=0.19736842105263158;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.33797909407665505;
         class1+=0.662020905923345;
        }
      else
        {
         class0+=0.0034965034965034965;
         class1+=0.9965034965034965;
        }
  else
    if (heartRate <= 54.5)
      if (age <= 35.5)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.08;
         class1+=0.92;
        }
    else
      if (heartRate <= 142.5)
        {
         class0+=0.05947955390334572;
         class1+=0.9405204460966543;
        }
      else
        {
         class0+=0.14634146341463414;
         class1+=0.8536585365853658;
        }
 // TREE: 70
  if (killipClass <= 2.5)
    if (age <= 56.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8753117206982544;
         class1+=0.12468827930174564;
        }
      else
        {
         class0+=0.5543478260869565;
         class1+=0.44565217391304346;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.55;
         class1+=0.45;
        }
      else
        {
         class0+=0.06646525679758308;
         class1+=0.9335347432024169;
        }
  else
    if (age <= 35.5)
      if (serumCreatinine <= 0.5699999928474426)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.4444444444444444;
         class1+=0.5555555555555556;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.10429447852760736;
         class1+=0.8957055214723927;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 71
  if (deviatedSTSegment <= 0.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.2396694214876033;
         class1+=0.7603305785123967;
        }
    else
      if (age <= 55.5)
        {
         class0+=0.7102473498233216;
         class1+=0.28975265017667845;
        }
      else
        {
         class0+=0.18546365914786966;
         class1+=0.8145363408521303;
        }
  else
    if (age <= 51.5)
      if (age <= 32.5)
        {
         class0+=0.18518518518518517;
         class1+=0.8148148148148148;
        }
      else
        {
         class0+=0.31;
         class1+=0.69;
        }
    else
      if (age <= 69.5)
        {
         class0+=0.12272727272727273;
         class1+=0.8772727272727273;
        }
      else
        {
         class0+=0.01680672268907563;
         class1+=0.9831932773109243;
        }
 // TREE: 72
  if (elevatedCardiacEnzymes <= 0.5)
    if (age <= 77.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.863406408094435;
         class1+=0.13659359190556492;
        }
      else
        {
         class0+=0.35424354243542433;
         class1+=0.6457564575645757;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.6;
         class1+=0.4;
        }
      else
        {
         class0+=0.0125;
         class1+=0.9875;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.4098360655737705;
         class1+=0.5901639344262295;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.003048780487804878;
         class1+=0.9969512195121951;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 73
  if (elevatedCardiacEnzymes <= 0.5)
    if (killipClass <= 2.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.7483069977426636;
         class1+=0.2516930022573363;
        }
      else
        {
         class0+=0.07692307692307693;
         class1+=0.9230769230769231;
        }
    else
      if (age <= 35.5)
        {
         class0+=0.5454545454545454;
         class1+=0.45454545454545453;
        }
      else
        {
         class0+=0.10429447852760736;
         class1+=0.8957055214723927;
        }
  else
    if (systolicBloodPressure <= 139.5)
      if (killipClass <= 1.5)
        {
         class0+=0.184;
         class1+=0.816;
        }
      else
        {
         class0+=0.023809523809523808;
         class1+=0.9761904761904762;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.5607734806629834;
         class1+=0.43922651933701656;
        }
      else
        {
         class0+=0.006535947712418301;
         class1+=0.9934640522875817;
        }
 // TREE: 74
  if (elevatedCardiacEnzymes <= 0.5)
    if (killipClass <= 2.5)
      if (heartRate <= 100.5)
        {
         class0+=0.8173719376391982;
         class1+=0.18262806236080179;
        }
      else
        {
         class0+=0.66;
         class1+=0.34;
        }
    else
      if (age <= 35.5)
        {
         class0+=0.5454545454545454;
         class1+=0.45454545454545453;
        }
      else
        {
         class0+=0.10429447852760736;
         class1+=0.8957055214723927;
        }
  else
    if (heartRate <= 89.5)
      if (systolicBloodPressure <= 151.5)
        {
         class0+=0.2145748987854251;
         class1+=0.7854251012145749;
        }
      else
        {
         class0+=0.5329341317365269;
         class1+=0.46706586826347307;
        }
    else
      if (serumCreatinine <= 2.0399999618530273)
        {
         class0+=0.22945205479452055;
         class1+=0.7705479452054794;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 75
  if (heartRate <= 94.5)
    if (cardiacArrestAtAdmission <= 0.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.69375;
         class1+=0.30625;
        }
      else
        {
         class0+=0.1972318339100346;
         class1+=0.8027681660899654;
        }
    else
      if (serumCreatinine <= 0.8400000035762787)
        {
         class0+=0.3333333333333333;
         class1+=0.6666666666666666;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (killipClass <= 2.5)
      if (heartRate <= 150.5)
        {
         class0+=0.48642533936651583;
         class1+=0.5135746606334841;
        }
      else
        {
         class0+=0.34210526315789475;
         class1+=0.6578947368421053;
        }
    else
      if (age <= 39.5)
        {
         class0+=0.2;
         class1+=0.8;
        }
      else
        {
         class0+=0.030303030303030304;
         class1+=0.9696969696969697;
        }
 // TREE: 76
  if (deviatedSTSegment <= 0.5)
    if (cardiacArrestAtAdmission <= 0.5)
      if (heartRate <= 93.5)
        {
         class0+=0.6971153846153846;
         class1+=0.30288461538461536;
        }
      else
        {
         class0+=0.5642023346303502;
         class1+=0.4357976653696498;
        }
    else
      if (serumCreatinine <= 0.7949999868869781)
        {
         class0+=0.5;
         class1+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (age <= 51.5)
      if (killipClass <= 1.5)
        {
         class0+=0.38285714285714284;
         class1+=0.6171428571428571;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (serumCreatinine <= 0.8349999785423279)
        {
         class0+=0.018691588785046728;
         class1+=0.9813084112149533;
        }
      else
        {
         class0+=0.08262108262108261;
         class1+=0.9173789173789174;
        }
 // TREE: 77
  if (elevatedCardiacEnzymes <= 0.5)
    if (cardiacArrestAtAdmission <= 0.5)
      if (age <= 77.5)
        {
         class0+=0.7107728337236534;
         class1+=0.2892271662763466;
        }
      else
        {
         class0+=0.391705069124424;
         class1+=0.6082949308755761;
        }
    else
      if (serumCreatinine <= 0.8100000023841858)
        {
         class0+=0.5;
         class1+=0.5;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (systolicBloodPressure <= 139.0)
        {
         class0+=0.225;
         class1+=0.775;
        }
      else
        {
         class0+=0.5607734806629834;
         class1+=0.43922651933701656;
        }
    else
      if (heartRate <= 58.5)
        {
         class0+=0.025;
         class1+=0.975;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 78
  if (killipClass <= 2.5)
    if (systolicBloodPressure <= 136.5)
      if (age <= 56.5)
        {
         class0+=0.5954415954415955;
         class1+=0.4045584045584046;
        }
      else
        {
         class0+=0.24190064794816415;
         class1+=0.7580993520518359;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8383838383838383;
         class1+=0.16161616161616163;
        }
      else
        {
         class0+=0.4523281596452328;
         class1+=0.5476718403547672;
        }
  else
    if (killipClass <= 3.5)
      if (systolicBloodPressure <= 203.5)
        {
         class0+=0.0915032679738562;
         class1+=0.9084967320261438;
        }
      else
        {
         class0+=0.39285714285714285;
         class1+=0.6071428571428571;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.038834951456310676;
         class1+=0.9611650485436893;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 79
  if (deviatedSTSegment <= 0.5)
    if (age <= 56.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8839285714285714;
         class1+=0.11607142857142858;
        }
      else
        {
         class0+=0.7;
         class1+=0.3;
        }
    else
      if (systolicBloodPressure <= 121.5)
        {
         class0+=0.2905405405405405;
         class1+=0.7094594594594594;
        }
      else
        {
         class0+=0.5780933062880325;
         class1+=0.42190669371196754;
        }
  else
    if (killipClass <= 1.5)
      if (systolicBloodPressure <= 136.5)
        {
         class0+=0.07196969696969698;
         class1+=0.928030303030303;
        }
      else
        {
         class0+=0.3038461538461538;
         class1+=0.6961538461538461;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 80
  if (killipClass <= 2.5)
    if (age <= 56.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.7289473684210527;
         class1+=0.2710526315789474;
        }
      else
        {
         class0+=0.1111111111111111;
         class1+=0.8888888888888888;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.6285140562248996;
         class1+=0.3714859437751004;
        }
      else
        {
         class0+=0.1460446247464503;
         class1+=0.8539553752535497;
        }
  else
    if (age <= 35.5)
      if (heartRate <= 67.0)
        {
         class0+=1.0;
         class1+=0.0;
        }
      else
        {
         class0+=0.2608695652173913;
         class1+=0.7391304347826086;
        }
    else
      if (serumCreatinine <= 1.2050000429153442)
        {
         class0+=0.04242424242424243;
         class1+=0.9575757575757575;
        }
      else
        {
         class0+=0.0684931506849315;
         class1+=0.9315068493150684;
        }
 // TREE: 81
  if (age <= 56.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (serumCreatinine <= 1.3350000381469727)
        {
         class0+=0.7445482866043613;
         class1+=0.2554517133956386;
        }
      else
        {
         class0+=0.8260869565217391;
         class1+=0.17391304347826086;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7;
         class1+=0.3;
        }
      else
        {
         class0+=0.0070921985815602835;
         class1+=0.9929078014184397;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.479328165374677;
         class1+=0.520671834625323;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.1073170731707317;
         class1+=0.8926829268292683;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 82
  if (systolicBloodPressure <= 139.5)
    if (age <= 49.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7361111111111112;
         class1+=0.2638888888888889;
        }
      else
        {
         class0+=0.20869565217391303;
         class1+=0.7913043478260869;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.35990888382687924;
         class1+=0.6400911161731208;
        }
      else
        {
         class0+=0.008032128514056224;
         class1+=0.9919678714859438;
        }
  else
    if (killipClass <= 2.5)
      if (age <= 76.5)
        {
         class0+=0.7457865168539326;
         class1+=0.2542134831460674;
        }
      else
        {
         class0+=0.35148514851485146;
         class1+=0.6485148514851485;
        }
    else
      if (heartRate <= 89.5)
        {
         class0+=0.22727272727272727;
         class1+=0.7727272727272727;
        }
      else
        {
         class0+=0.0891089108910891;
         class1+=0.9108910891089109;
        }
 // TREE: 83
  if (elevatedCardiacEnzymes <= 0.5)
    if (deviatedSTSegment <= 0.5)
      if (age <= 78.5)
        {
         class0+=0.8623548922056384;
         class1+=0.13764510779436154;
        }
      else
        {
         class0+=0.5846153846153846;
         class1+=0.4153846153846154;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.3688212927756654;
         class1+=0.6311787072243346;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (age <= 55.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7102473498233216;
         class1+=0.28975265017667845;
        }
      else
        {
         class0+=0.007462686567164179;
         class1+=0.9925373134328358;
        }
    else
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.18546365914786966;
         class1+=0.8145363408521303;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 84
  if (elevatedCardiacEnzymes <= 0.5)
    if (deviatedSTSegment <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.2396694214876033;
         class1+=0.7603305785123967;
        }
    else
      if (systolicBloodPressure <= 119.0)
        {
         class0+=0.06153846153846154;
         class1+=0.9384615384615385;
        }
      else
        {
         class0+=0.40271493212669685;
         class1+=0.5972850678733032;
        }
  else
    if (systolicBloodPressure <= 139.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.225;
         class1+=0.775;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (heartRate <= 89.5)
        {
         class0+=0.5;
         class1+=0.5;
        }
      else
        {
         class0+=0.3312302839116719;
         class1+=0.668769716088328;
        }
 // TREE: 85
  if (killipClass <= 2.5)
    if (deviatedSTSegment <= 0.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.4782608695652174;
         class1+=0.5217391304347826;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.33797909407665505;
         class1+=0.662020905923345;
        }
      else
        {
         class0+=0.0034965034965034965;
         class1+=0.9965034965034965;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (age <= 35.5)
        {
         class0+=0.5454545454545454;
         class1+=0.45454545454545453;
        }
      else
        {
         class0+=0.10429447852760736;
         class1+=0.8957055214723927;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 86
  if (cardiacArrestAtAdmission <= 0.5)
    if (killipClass <= 2.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.7206512425021423;
         class1+=0.27934875749785776;
        }
      else
        {
         class0+=0.17375886524822695;
         class1+=0.8262411347517731;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.15675675675675677;
         class1+=0.8432432432432433;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
  else
    if (age <= 38.5)
      if (heartRate <= 78.5)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.5;
         class1+=0.5;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 87
  if (elevatedCardiacEnzymes <= 0.5)
    if (heartRate <= 100.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.8629032258064516;
         class1+=0.13709677419354838;
        }
      else
        {
         class0+=0.38323353293413176;
         class1+=0.6167664670658682;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.66;
         class1+=0.34;
        }
      else
        {
         class0+=0.11578947368421053;
         class1+=0.8842105263157894;
        }
  else
    if (killipClass <= 2.5)
      if (age <= 55.5)
        {
         class0+=0.5658263305322129;
         class1+=0.4341736694677871;
        }
      else
        {
         class0+=0.14682539682539683;
         class1+=0.8531746031746031;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 88
  if (elevatedCardiacEnzymes <= 0.5)
    if (deviatedSTSegment <= 0.5)
      if (age <= 78.5)
        {
         class0+=0.8623548922056384;
         class1+=0.13764510779436154;
        }
      else
        {
         class0+=0.5846153846153846;
         class1+=0.4153846153846154;
        }
    else
      if (age <= 51.5)
        {
         class0+=0.5739130434782609;
         class1+=0.4260869565217391;
        }
      else
        {
         class0+=0.13135593220338984;
         class1+=0.8686440677966102;
        }
  else
    if (systolicBloodPressure <= 139.5)
      if (age <= 49.5)
        {
         class0+=0.389937106918239;
         class1+=0.610062893081761;
        }
      else
        {
         class0+=0.029239766081871343;
         class1+=0.9707602339181286;
        }
    else
      if (heartRate <= 89.5)
        {
         class0+=0.5;
         class1+=0.5;
        }
      else
        {
         class0+=0.3312302839116719;
         class1+=0.668769716088328;
        }
 // TREE: 89
  if (systolicBloodPressure <= 139.5)
    if (deviatedSTSegment <= 0.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.7313432835820896;
         class1+=0.26865671641791045;
        }
      else
        {
         class0+=0.225;
         class1+=0.775;
        }
    else
      if (age <= 47.5)
        {
         class0+=0.21621621621621623;
         class1+=0.7837837837837838;
        }
      else
        {
         class0+=0.007905138339920948;
         class1+=0.9920948616600791;
        }
  else
    if (killipClass <= 2.5)
      if (age <= 76.5)
        {
         class0+=0.7457865168539326;
         class1+=0.2542134831460674;
        }
      else
        {
         class0+=0.35148514851485146;
         class1+=0.6485148514851485;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.25263157894736843;
         class1+=0.7473684210526316;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 90
  if (deviatedSTSegment <= 0.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (systolicBloodPressure <= 98.5)
        {
         class0+=0.6604938271604939;
         class1+=0.3395061728395062;
        }
      else
        {
         class0+=0.8563922942206655;
         class1+=0.1436077057793345;
        }
    else
      if (systolicBloodPressure <= 139.0)
        {
         class0+=0.225;
         class1+=0.775;
        }
      else
        {
         class0+=0.5607734806629834;
         class1+=0.43922651933701656;
        }
  else
    if (cardiacArrestAtAdmission <= 0.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.27953890489913547;
         class1+=0.7204610951008645;
        }
      else
        {
         class0+=0.003048780487804878;
         class1+=0.9969512195121951;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 91
  if (elevatedCardiacEnzymes <= 0.5)
    if (heartRate <= 100.5)
      if (deviatedSTSegment <= 0.5)
        {
         class0+=0.8629032258064516;
         class1+=0.13709677419354838;
        }
      else
        {
         class0+=0.38323353293413176;
         class1+=0.6167664670658682;
        }
    else
      if (systolicBloodPressure <= 139.5)
        {
         class0+=0.4344569288389513;
         class1+=0.5655430711610487;
        }
      else
        {
         class0+=0.6906474820143885;
         class1+=0.30935251798561153;
        }
  else
    if (systolicBloodPressure <= 139.5)
      if (age <= 49.5)
        {
         class0+=0.389937106918239;
         class1+=0.610062893081761;
        }
      else
        {
         class0+=0.029239766081871343;
         class1+=0.9707602339181286;
        }
    else
      if (serumCreatinine <= 1.5449999570846558)
        {
         class0+=0.38072289156626504;
         class1+=0.619277108433735;
        }
      else
        {
         class0+=0.46;
         class1+=0.54;
        }
 // TREE: 92
  if (deviatedSTSegment <= 0.5)
    if (cardiacArrestAtAdmission <= 0.5)
      if (killipClass <= 2.5)
        {
         class0+=0.7206512425021423;
         class1+=0.27934875749785776;
        }
      else
        {
         class0+=0.12719298245614036;
         class1+=0.8728070175438597;
        }
    else
      if (heartRate <= 83.5)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.1;
         class1+=0.9;
        }
  else
    if (killipClass <= 1.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.3688212927756654;
         class1+=0.6311787072243346;
        }
      else
        {
         class0+=0.0038314176245210726;
         class1+=0.9961685823754789;
        }
    else
      {
       class0+=0.0;
       class1+=1.0;
      }
 // TREE: 93
  if (deviatedSTSegment <= 0.5)
    if (heartRate <= 93.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.6971153846153846;
         class1+=0.30288461538461536;
        }
      else
        {
         class0+=0.09090909090909091;
         class1+=0.9090909090909091;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.660436137071651;
         class1+=0.3395638629283489;
        }
      else
        {
         class0+=0.07971014492753623;
         class1+=0.9202898550724637;
        }
  else
    if (age <= 51.5)
      if (heartRate <= 145.5)
        {
         class0+=0.32038834951456313;
         class1+=0.6796116504854369;
        }
      else
        {
         class0+=0.047619047619047616;
         class1+=0.9523809523809523;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.13135593220338984;
         class1+=0.8686440677966102;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 94
  if (age <= 56.5)
    if (heartRate <= 98.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.6978260869565217;
         class1+=0.30217391304347824;
        }
      else
        {
         class0+=0.16666666666666666;
         class1+=0.8333333333333334;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.717948717948718;
         class1+=0.28205128205128205;
        }
      else
        {
         class0+=0.40375586854460094;
         class1+=0.596244131455399;
        }
  else
    if (heartRate <= 91.5)
      if (killipClass <= 1.5)
        {
         class0+=0.4891891891891892;
         class1+=0.5108108108108108;
        }
      else
        {
         class0+=0.09821428571428571;
         class1+=0.9017857142857143;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.35660377358490564;
         class1+=0.6433962264150943;
        }
      else
        {
         class0+=0.06857142857142857;
         class1+=0.9314285714285714;
        }
 // TREE: 95
  if (heartRate <= 94.5)
    if (killipClass <= 1.5)
      if (systolicBloodPressure <= 137.5)
        {
         class0+=0.4925373134328358;
         class1+=0.5074626865671642;
        }
      else
        {
         class0+=0.7532808398950132;
         class1+=0.24671916010498687;
        }
    else
      if (killipClass <= 2.5)
        {
         class0+=0.38095238095238093;
         class1+=0.6190476190476191;
        }
      else
        {
         class0+=0.12413793103448276;
         class1+=0.8758620689655172;
        }
  else
    if (age <= 58.5)
      if (age <= 42.5)
        {
         class0+=0.6533333333333333;
         class1+=0.3466666666666667;
        }
      else
        {
         class0+=0.5069444444444444;
         class1+=0.4930555555555556;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.4523809523809524;
         class1+=0.5476190476190477;
        }
      else
        {
         class0+=0.0718954248366013;
         class1+=0.9281045751633987;
        }
 // TREE: 96
  if (killipClass <= 2.5)
    if (age <= 56.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8753117206982544;
         class1+=0.12468827930174564;
        }
      else
        {
         class0+=0.5543478260869565;
         class1+=0.44565217391304346;
        }
    else
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.6285140562248996;
         class1+=0.3714859437751004;
        }
      else
        {
         class0+=0.1460446247464503;
         class1+=0.8539553752535497;
        }
  else
    if (age <= 35.5)
      if (serumCreatinine <= 0.5699999928474426)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.4444444444444444;
         class1+=0.5555555555555556;
        }
    else
      if (systolicBloodPressure <= 204.5)
        {
         class0+=0.022900763358778626;
         class1+=0.9770992366412213;
        }
      else
        {
         class0+=0.22448979591836735;
         class1+=0.7755102040816326;
        }
 // TREE: 97
  if (deviatedSTSegment <= 0.5)
    if (age <= 56.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.8839285714285714;
         class1+=0.11607142857142858;
        }
      else
        {
         class0+=0.7;
         class1+=0.3;
        }
    else
      if (killipClass <= 1.5)
        {
         class0+=0.5838926174496645;
         class1+=0.4161073825503356;
        }
      else
        {
         class0+=0.11917098445595854;
         class1+=0.8808290155440415;
        }
  else
    if (systolicBloodPressure <= 136.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.05571847507331378;
         class1+=0.9442815249266863;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
    else
      if (systolicBloodPressure <= 188.5)
        {
         class0+=0.195;
         class1+=0.805;
        }
      else
        {
         class0+=0.28368794326241137;
         class1+=0.7163120567375887;
        }
 // TREE: 98
  if (age <= 56.5)
    if (elevatedCardiacEnzymes <= 0.5)
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.7777777777777778;
         class1+=0.2222222222222222;
        }
      else
        {
         class0+=0.2;
         class1+=0.8;
        }
    else
      if (heartRate <= 94.5)
        {
         class0+=0.5533980582524272;
         class1+=0.44660194174757284;
        }
      else
        {
         class0+=0.4;
         class1+=0.6;
        }
  else
    if (deviatedSTSegment <= 0.5)
      if (killipClass <= 1.5)
        {
         class0+=0.5838926174496645;
         class1+=0.4161073825503356;
        }
      else
        {
         class0+=0.11917098445595854;
         class1+=0.8808290155440415;
        }
    else
      if (cardiacArrestAtAdmission <= 0.5)
        {
         class0+=0.05612244897959184;
         class1+=0.9438775510204082;
        }
      else
        {
         class0+=0.0;
         class1+=1.0;
        }
 // TREE: 99
  if (deviatedSTSegment <= 0.5)
    if (killipClass <= 2.5)
      if (elevatedCardiacEnzymes <= 0.5)
        {
         class0+=0.9264705882352942;
         class1+=0.07352941176470588;
        }
      else
        {
         class0+=0.4782608695652174;
         class1+=0.5217391304347826;
        }
    else
      if (systolicBloodPressure <= 179.5)
        {
         class0+=0.0641025641025641;
         class1+=0.9358974358974359;
        }
      else
        {
         class0+=0.2638888888888889;
         class1+=0.7361111111111112;
        }
  else
    if (elevatedCardiacEnzymes <= 0.5)
      if (systolicBloodPressure <= 119.0)
        {
         class0+=0.06153846153846154;
         class1+=0.9384615384615385;
        }
      else
        {
         class0+=0.40271493212669685;
         class1+=0.5972850678733032;
        }
    else
      if (systolicBloodPressure <= 205.0)
        {
         class0+=0.0;
         class1+=1.0;
        }
      else
        {
         class0+=0.023255813953488372;
         class1+=0.9767441860465116;
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
