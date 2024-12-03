public class RandomForest { 

//@ requires(*The double parameter radius_mean is greater than or equal to 15.5 and the double parameter radius_mean is less than or equal to 27.0 and the double parameter texture_mean is greater than or equal to 17.5 and the double parameter texture_mean is less than or equal to 36.0 and the double parameter perimeter_mean is greater than or equal to 108.0 and the double parameter perimeter_mean is less than or equal to 185.0 and the double parameter area_mean is greater than or equal to 750.0 and the double parameter area_mean is less than or equal to 2300*);
//@ ensures(*The integer result is equal to 1.*);
// requires(15 <= radius_mean <= 30 && 15 <= texture_mean <= 40 && 100 <= perimeter_mean <= 200 && 700 <= area_mean <= 2500 && 0.08 <= smoothness_mean <= 0.15 && 0.1 <= compactness_mean <= 0.3 && 0.05 <= concavity_mean <= 0.25 && 0.03 <= concave_points_mean <= 0.15 && 0.2 <= symmetry_mean <= 0.4 && 0.06 <= fractal_dimension_mean <= 0.1);
//@ ensures(\result == 1);
public int randomForest(double radius_mean,double texture_mean,double perimeter_mean,double area_mean,double smoothness_mean,double compactness_mean,double concavity_mean,double concave_points_mean,double symmetry_mean,double fractal_dimension_mean){
double class0 = 0.0;
double class1 = 0.0;
  if (perimeter_mean <= 97.92) {
    if (smoothness_mean <= 0.10850000008940697) {
      if (concave_points_mean <= 0.04891999997198582) {
        if (area_mean <= 697.8000183105469) {
          if (texture_mean <= 19.609999656677246) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (texture_mean <= 19.695) {
               class0+=0.0;
               class1+=1.0;
            } 
            else {
              if (concave_points_mean <= 0.044850001111626625) {
                if (radius_mean <= 13.41) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                  if (perimeter_mean <= 86.54500198364258) {
                    if (symmetry_mean <= 0.1602500006556511) {
                       class0+=1.0;
                       class1+=0.0;
                    } 
                    else {
                       class0+=0.0;
                       class1+=1.0;
                     }
                  } 
                  else {
                     class0+=1.0;
                     class1+=0.0;
                   }
                 }
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
      } 
      else {
        if (texture_mean <= 20.789999961853027) {
          if (area_mean <= 532.6000061035156) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (smoothness_mean <= 0.09669500216841698) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (concavity_mean <= 0.08983999863266945) {
                if (symmetry_mean <= 0.16929999738931656) {
                   class0+=0.0;
                   class1+=1.0;
                } 
                else {
                   class0+=1.0;
                   class1+=0.0;
                 }
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
        } 
        else {
          if (radius_mean <= 13.294999599456787) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
    } 
    else {
      if (concave_points_mean <= 0.04988999851047993) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
        if (compactness_mean <= 0.1164500005543232) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (perimeter_mean <= 68.01) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (concavity_mean <= 0.10319999977946281) {
              if (fractal_dimension_mean <= 0.062379999086260796) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
           }
         }
       }
     }
  } 
  else {
    if (concavity_mean <= 0.06972) {
      if (texture_mean <= 18.835000038146973) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
        if (symmetry_mean <= 0.1704000011086464) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
          if (texture_mean <= 19.83) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
    } 
    else {
      if (perimeter_mean <= 102.85000228881836) {
        if (radius_mean <= 15.59499979019165) {
          if (compactness_mean <= 0.1728) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
            if (compactness_mean <= 0.18935000151395798) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
           }
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
      } 
      else {
         class0+=0.0;
         class1+=1.0;
       }
     }
   }
  if (perimeter_mean <= 97.92) {
    if (concavity_mean <= 0.10370000079274178) {
      if (perimeter_mean <= 90.36999893188477) {
        if (compactness_mean <= 0.13700000196695328) {
          if (texture_mean <= 19.609999656677246) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (perimeter_mean <= 85.53999710083008) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (texture_mean <= 19.88) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                if (fractal_dimension_mean <= 0.056525) {
                   class0+=0.0;
                   class1+=1.0;
                } 
                else {
                   class0+=1.0;
                   class1+=0.0;
                 }
               }
             }
           }
        } 
        else {
          if (fractal_dimension_mean <= 0.070185) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
             class0+=1.0;
             class1+=0.0;
           }
         }
      } 
      else {
        if (area_mean <= 697.8000183105469) {
          if (perimeter_mean <= 91.84000015258789) {
            if (concave_points_mean <= 0.04686499945819378) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
          } 
          else {
            if (concavity_mean <= 0.07144999876618385) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (concavity_mean <= 0.07701) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                if (symmetry_mean <= 0.18755000084638596) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                  if (compactness_mean <= 0.114) {
                     class0+=1.0;
                     class1+=0.0;
                  } 
                  else {
                    if (area_mean <= 647.2) {
                       class0+=0.0;
                       class1+=1.0;
                    } 
                    else {
                       class0+=1.0;
                       class1+=0.0;
                     }
                   }
                 }
               }
             }
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
       }
    } 
    else {
      if (smoothness_mean <= 0.09424499794840813) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
        if (perimeter_mean <= 68.25) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (area_mean <= 527.3) {
            if (smoothness_mean <= 0.1083) {
              if (perimeter_mean <= 81.65) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
     }
  } 
  else {
    if (perimeter_mean <= 108.70000076293945) {
      if (concavity_mean <= 0.07100499793887138) {
        if (compactness_mean <= 0.07167499884963036) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
      } 
      else {
        if (fractal_dimension_mean <= 0.06490999832749367) {
          if (area_mean <= 690.05) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (smoothness_mean <= 0.10410000011324883) {
               class0+=0.0;
               class1+=1.0;
            } 
            else {
              if (concavity_mean <= 0.13284999504685402) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
       }
    } 
    else {
      if (compactness_mean <= 0.06253499910235405) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
         class0+=0.0;
         class1+=1.0;
       }
     }
   }
  if (area_mean <= 697.8000183105469) {
    if (symmetry_mean <= 0.20390000194311142) {
      if (compactness_mean <= 0.114) {
        if (concavity_mean <= 0.1217000000178814) {
          if (perimeter_mean <= 85.81499862670898) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (radius_mean <= 13.445) {
              if (compactness_mean <= 0.068065) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
            } 
            else {
              if (concave_points_mean <= 0.05105) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                if (texture_mean <= 17.65499973297119) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                   class0+=0.0;
                   class1+=1.0;
                 }
               }
             }
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
      } 
      else {
        if (concave_points_mean <= 0.04271000064909458) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (fractal_dimension_mean <= 0.07003499940037727) {
            if (concavity_mean <= 0.09722999855875969) {
              if (perimeter_mean <= 89.945) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
            } 
            else {
              if (radius_mean <= 15.025000095367432) {
                if (compactness_mean <= 0.14855) {
                   class0+=0.0;
                   class1+=1.0;
                } 
                else {
                  if (texture_mean <= 19.53499937057495) {
                     class0+=1.0;
                     class1+=0.0;
                  } 
                  else {
                     class0+=0.0;
                     class1+=1.0;
                   }
                 }
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
             }
          } 
          else {
            if (concavity_mean <= 0.1748499944806099) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (smoothness_mean <= 0.09163499996066093) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
         }
       }
    } 
    else {
      if (area_mean <= 351.25) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
        if (compactness_mean <= 0.1206) {
          if (radius_mean <= 14.41) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
        } 
        else {
          if (texture_mean <= 16.494999885559082) {
            if (smoothness_mean <= 0.12050000205636024) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
     }
  } 
  else {
    if (concavity_mean <= 0.06972) {
      if (compactness_mean <= 0.07167499884963036) {
        if (fractal_dimension_mean <= 0.052685) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
      } 
      else {
         class0+=1.0;
         class1+=0.0;
       }
    } 
    else {
      if (area_mean <= 748.0500183105469) {
        if (radius_mean <= 15.59499979019165) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
      } 
      else {
         class0+=0.0;
         class1+=1.0;
       }
     }
   }
  if (radius_mean <= 15.025000095367432) {
    if (concave_points_mean <= 0.04891999997198582) {
      if (concave_points_mean <= 0.04501) {
        if (fractal_dimension_mean <= 0.05672) {
          if (fractal_dimension_mean <= 0.056700000539422035) {
            if (symmetry_mean <= 0.17819999903440475) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (texture_mean <= 20.445) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                if (compactness_mean <= 0.04922) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                   class0+=0.0;
                   class1+=1.0;
                 }
               }
             }
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
      } 
      else {
        if (concave_points_mean <= 0.045825) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
       }
    } 
    else {
      if (concavity_mean <= 0.1196) {
        if (area_mean <= 537.3000183105469) {
          if (fractal_dimension_mean <= 0.06886) {
            if (radius_mean <= 11.105000019073486) {
               class0+=0.0;
               class1+=1.0;
            } 
            else {
               class0+=1.0;
               class1+=0.0;
             }
          } 
          else {
             class0+=1.0;
             class1+=0.0;
           }
        } 
        else {
          if (texture_mean <= 15.514999866485596) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (concave_points_mean <= 0.0501) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (fractal_dimension_mean <= 0.061045) {
                if (texture_mean <= 19.50999927520752) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                   class0+=0.0;
                   class1+=1.0;
                 }
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
         }
      } 
      else {
        if (concavity_mean <= 0.2868) {
          if (radius_mean <= 10.825) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (area_mean <= 649.6000061035156) {
               class0+=0.0;
               class1+=1.0;
            } 
            else {
              if (symmetry_mean <= 0.1725500002503395) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
       }
     }
  } 
  else {
    if (smoothness_mean <= 0.07839) {
      if (concave_points_mean <= 0.025390000082552433) {
         class0+=0.0;
         class1+=1.0;
      } 
      else {
         class0+=1.0;
         class1+=0.0;
       }
    } 
    else {
      if (texture_mean <= 16.394999504089355) {
        if (area_mean <= 900.5) {
          if (concave_points_mean <= 0.06625999882817268) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (radius_mean <= 15.22) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
      } 
      else {
        if (symmetry_mean <= 0.14999999850988388) {
          if (symmetry_mean <= 0.14809999614953995) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
             class0+=1.0;
             class1+=0.0;
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
       }
     }
   }
  if (area_mean <= 697.8000183105469) {
    if (concavity_mean <= 0.1196) {
      if (compactness_mean <= 0.12135) {
        if (area_mean <= 562.55) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (perimeter_mean <= 86.695) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
            if (concave_points_mean <= 0.05105) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (perimeter_mean <= 94.38000106811523) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
         }
      } 
      else {
        if (concave_points_mean <= 0.042260000482201576) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (fractal_dimension_mean <= 0.07096) {
            if (perimeter_mean <= 94.32) {
              if (smoothness_mean <= 0.10595) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                if (smoothness_mean <= 0.10889999940991402) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                   class0+=0.0;
                   class1+=1.0;
                 }
               }
            } 
            else {
               class0+=1.0;
               class1+=0.0;
             }
          } 
          else {
             class0+=1.0;
             class1+=0.0;
           }
         }
       }
    } 
    else {
      if (smoothness_mean <= 0.09761) {
        if (concavity_mean <= 0.12555) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
      } 
      else {
        if (radius_mean <= 10.405499935150146) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
       }
     }
  } 
  else {
    if (texture_mean <= 16.109999656677246) {
      if (concavity_mean <= 0.1192) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
         class0+=0.0;
         class1+=1.0;
       }
    } 
    else {
      if (concave_points_mean <= 0.052155) {
        if (concave_points_mean <= 0.047895) {
          if (smoothness_mean <= 0.07471000030636787) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
      } 
      else {
         class0+=0.0;
         class1+=1.0;
       }
     }
   }
  if (area_mean <= 697.8000183105469) {
    if (concavity_mean <= 0.1196) {
      if (compactness_mean <= 0.12135) {
        if (area_mean <= 562.55) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (area_mean <= 565.8500061035156) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
            if (fractal_dimension_mean <= 0.059175001457333565) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (fractal_dimension_mean <= 0.05937500111758709) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                if (perimeter_mean <= 94.38000106811523) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                  if (symmetry_mean <= 0.18115) {
                     class0+=1.0;
                     class1+=0.0;
                  } 
                  else {
                     class0+=0.0;
                     class1+=1.0;
                   }
                 }
               }
             }
           }
         }
      } 
      else {
        if (fractal_dimension_mean <= 0.07096) {
          if (concave_points_mean <= 0.042260000482201576) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (perimeter_mean <= 94.32) {
              if (concave_points_mean <= 0.067245002835989) {
                if (smoothness_mean <= 0.10614999756217003) {
                   class0+=0.0;
                   class1+=1.0;
                } 
                else {
                  if (concavity_mean <= 0.09695999696850777) {
                     class0+=1.0;
                     class1+=0.0;
                  } 
                  else {
                     class0+=0.0;
                     class1+=1.0;
                   }
                 }
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
            } 
            else {
               class0+=1.0;
               class1+=0.0;
             }
           }
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
       }
    } 
    else {
      if (smoothness_mean <= 0.09761) {
        if (fractal_dimension_mean <= 0.060225) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
      } 
      else {
        if (area_mean <= 330.90000915527344) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
       }
     }
  } 
  else {
    if (concavity_mean <= 0.06972) {
      if (radius_mean <= 15.874999523162842) {
         class0+=0.0;
         class1+=1.0;
      } 
      else {
        if (concavity_mean <= 0.04047) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
          if (texture_mean <= 21.97) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
    } 
    else {
      if (texture_mean <= 11.585) {
        if (perimeter_mean <= 112.8) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
      } 
      else {
         class0+=0.0;
         class1+=1.0;
       }
     }
   }
  if (radius_mean <= 15.025000095367432) {
    if (compactness_mean <= 0.12305000051856041) {
      if (radius_mean <= 14.235000133514404) {
        if (concavity_mean <= 0.10370000079274178) {
          if (area_mean <= 562.55) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (texture_mean <= 19.38) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (perimeter_mean <= 87.975) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
             }
           }
        } 
        else {
          if (area_mean <= 387.3000030517578) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
             class0+=1.0;
             class1+=0.0;
           }
         }
      } 
      else {
        if (concavity_mean <= 0.09219) {
          if (texture_mean <= 19.945) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (texture_mean <= 20.16999912261963) {
               class0+=0.0;
               class1+=1.0;
            } 
            else {
               class0+=1.0;
               class1+=0.0;
             }
           }
        } 
        else {
          if (concave_points_mean <= 0.04414) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
    } 
    else {
      if (area_mean <= 532.0) {
        if (symmetry_mean <= 0.20844999700784683) {
          if (area_mean <= 477.0500030517578) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (radius_mean <= 12.615000247955322) {
               class0+=0.0;
               class1+=1.0;
            } 
            else {
               class0+=1.0;
               class1+=0.0;
             }
           }
        } 
        else {
          if (perimeter_mean <= 71.72500228881836) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
      } 
      else {
        if (radius_mean <= 14.255) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
          if (radius_mean <= 14.675) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
     }
  } 
  else {
    if (texture_mean <= 16.394999504089355) {
      if (radius_mean <= 17.92) {
        if (fractal_dimension_mean <= 0.06802) {
          if (concavity_mean <= 0.1197499968111515) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
      } 
      else {
         class0+=0.0;
         class1+=1.0;
       }
    } 
    else {
      if (symmetry_mean <= 0.14999999850988388) {
        if (concave_points_mean <= 0.052855) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
      } 
      else {
        if (fractal_dimension_mean <= 0.052729999646544456) {
          if (smoothness_mean <= 0.077325) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
       }
     }
   }
  if (concave_points_mean <= 0.05591999925673008) {
    if (radius_mean <= 15.025000095367432) {
      if (compactness_mean <= 0.12424999848008156) {
        if (perimeter_mean <= 85.81499862670898) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (perimeter_mean <= 85.865) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
            if (symmetry_mean <= 0.20095) {
              if (perimeter_mean <= 86.2599983215332) {
                if (perimeter_mean <= 86.14) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                   class0+=0.0;
                   class1+=1.0;
                 }
              } 
              else {
                if (concave_points_mean <= 0.05105) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                  if (area_mean <= 662.3) {
                     class0+=1.0;
                     class1+=0.0;
                  } 
                  else {
                     class0+=0.0;
                     class1+=1.0;
                   }
                 }
               }
            } 
            else {
              if (radius_mean <= 14.41) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
         }
      } 
      else {
        if (concave_points_mean <= 0.04271000064909458) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (area_mean <= 544.3000183105469) {
            if (radius_mean <= 11.730000019073486) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (perimeter_mean <= 82.0) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
             }
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
    } 
    else {
      if (radius_mean <= 15.874999523162842) {
         class0+=0.0;
         class1+=1.0;
      } 
      else {
        if (compactness_mean <= 0.07167499884963036) {
          if (radius_mean <= 17.6) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
            if (concave_points_mean <= 0.044609999284148216) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
           }
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
       }
     }
  } 
  else {
    if (area_mean <= 676.3) {
      if (smoothness_mean <= 0.09268) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
        if (fractal_dimension_mean <= 0.06586499884724617) {
          if (texture_mean <= 19.18) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
        } 
        else {
          if (area_mean <= 330.90000915527344) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (smoothness_mean <= 0.100965) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (symmetry_mean <= 0.19455000013113022) {
                if (symmetry_mean <= 0.1918499991297722) {
                   class0+=0.0;
                   class1+=1.0;
                } 
                else {
                   class0+=1.0;
                   class1+=0.0;
                 }
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
         }
       }
    } 
    else {
      if (fractal_dimension_mean <= 0.06255) {
         class0+=0.0;
         class1+=1.0;
      } 
      else {
        if (texture_mean <= 11.585) {
          if (fractal_dimension_mean <= 0.07065000012516975) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
       }
     }
   }
  if (compactness_mean <= 0.10215) {
    if (perimeter_mean <= 96.46500015258789) {
      if (radius_mean <= 13.415) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
        if (compactness_mean <= 0.05805999971926212) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (radius_mean <= 13.445) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
            if (perimeter_mean <= 94.22999954223633) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (texture_mean <= 19.734999656677246) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                if (symmetry_mean <= 0.15365) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                   class0+=0.0;
                   class1+=1.0;
                 }
               }
             }
           }
         }
       }
    } 
    else {
      if (smoothness_mean <= 0.07839) {
        if (concave_points_mean <= 0.025390000082552433) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
           class0+=1.0;
           class1+=0.0;
         }
      } 
      else {
        if (area_mean <= 694.6000061035156) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (texture_mean <= 15.769999980926514) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (fractal_dimension_mean <= 0.05563499964773655) {
               class0+=0.0;
               class1+=1.0;
            } 
            else {
              if (symmetry_mean <= 0.15280000120401382) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
           }
         }
       }
     }
  } 
  else {
    if (perimeter_mean <= 99.89) {
      if (concave_points_mean <= 0.04957499913871288) {
        if (fractal_dimension_mean <= 0.069155) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (perimeter_mean <= 79.63000106811523) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
            if (smoothness_mean <= 0.09927) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
           }
         }
      } 
      else {
        if (concave_points_mean <= 0.07941) {
          if (area_mean <= 558.6499938964844) {
            if (concave_points_mean <= 0.060895) {
              if (texture_mean <= 21.055) {
                if (concavity_mean <= 0.11414999887347221) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                  if (fractal_dimension_mean <= 0.08124500140547752) {
                     class0+=0.0;
                     class1+=1.0;
                  } 
                  else {
                     class0+=1.0;
                     class1+=0.0;
                   }
                 }
              } 
              else {
                if (texture_mean <= 21.945000648498535) {
                   class0+=0.0;
                   class1+=1.0;
                } 
                else {
                   class0+=1.0;
                   class1+=0.0;
                 }
               }
            } 
            else {
              if (texture_mean <= 16.21999979019165) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                 class0+=0.0;
                 class1+=1.0;
               }
             }
          } 
          else {
            if (texture_mean <= 15.514999866485596) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (perimeter_mean <= 95.25500106811523) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
             }
           }
        } 
        else {
          if (radius_mean <= 15.025000095367432) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
             class0+=1.0;
             class1+=0.0;
           }
         }
       }
    } 
    else {
      if (concave_points_mean <= 0.05505) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
        if (area_mean <= 748.0500183105469) {
          if (texture_mean <= 11.585) {
             class0+=1.0;
             class1+=0.0;
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
        } 
        else {
           class0+=0.0;
           class1+=1.0;
         }
       }
     }
   }
  if (perimeter_mean <= 97.92) {
    if (compactness_mean <= 0.12305000051856041) {
      if (area_mean <= 697.8000183105469) {
        if (perimeter_mean <= 93.155) {
          if (concave_points_mean <= 0.05628499947488308) {
            if (area_mean <= 562.55) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
              if (radius_mean <= 13.49) {
                 class0+=0.0;
                 class1+=1.0;
              } 
              else {
                 class0+=1.0;
                 class1+=0.0;
               }
             }
          } 
          else {
            if (area_mean <= 403.35) {
               class0+=0.0;
               class1+=1.0;
            } 
            else {
               class0+=1.0;
               class1+=0.0;
             }
           }
        } 
        else {
          if (radius_mean <= 14.429999828338623) {
            if (concavity_mean <= 0.08697499707341194) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
          } 
          else {
            if (symmetry_mean <= 0.20095) {
              if (smoothness_mean <= 0.09861) {
                 class0+=1.0;
                 class1+=0.0;
              } 
              else {
                if (smoothness_mean <= 0.099185) {
                   class0+=0.0;
                   class1+=1.0;
                } 
                else {
                   class0+=1.0;
                   class1+=0.0;
                 }
               }
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
           }
         }
      } 
      else {
         class0+=0.0;
         class1+=1.0;
       }
    } 
    else {
      if (concave_points_mean <= 0.04271000064909458) {
         class0+=1.0;
         class1+=0.0;
      } 
      else {
        if (smoothness_mean <= 0.10044999793171883) {
          if (compactness_mean <= 0.13945000618696213) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
             class0+=1.0;
             class1+=0.0;
           }
        } 
        else {
          if (fractal_dimension_mean <= 0.08622999861836433) {
            if (texture_mean <= 18.625) {
              if (radius_mean <= 13.570000171661377) {
                if (concave_points_mean <= 0.07201) {
                   class0+=1.0;
                   class1+=0.0;
                } 
                else {
                   class0+=0.0;
                   class1+=1.0;
                 }
              } 
              else {
                if (compactness_mean <= 0.1322) {
                   class0+=0.0;
                   class1+=1.0;
                } 
                else {
                  if (perimeter_mean <= 93.365) {
                     class0+=0.0;
                     class1+=1.0;
                  } 
                  else {
                     class0+=1.0;
                     class1+=0.0;
                   }
                 }
               }
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
          } 
          else {
            if (fractal_dimension_mean <= 0.096595) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
           }
         }
       }
     }
  } 
  else {
    if (concave_points_mean <= 0.054965) {
      if (smoothness_mean <= 0.0918550007045269) {
        if (perimeter_mean <= 107.75) {
           class0+=0.0;
           class1+=1.0;
        } 
        else {
          if (concavity_mean <= 0.03667999990284443) {
             class0+=0.0;
             class1+=1.0;
          } 
          else {
            if (smoothness_mean <= 0.083465) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
           }
         }
      } 
      else {
         class0+=1.0;
         class1+=0.0;
       }
    } 
    else {
      if (smoothness_mean <= 0.10415000095963478) {
         class0+=0.0;
         class1+=1.0;
      } 
      else {
        if (concave_points_mean <= 0.07077499851584435) {
           class0+=1.0;
           class1+=0.0;
        } 
        else {
          if (texture_mean <= 16.394999504089355) {
            if (radius_mean <= 15.22) {
               class0+=1.0;
               class1+=0.0;
            } 
            else {
               class0+=0.0;
               class1+=1.0;
             }
          } 
          else {
             class0+=0.0;
             class1+=1.0;
           }
         }
       }
     }
   }
   //@ show radius_mean, texture_mean, perimeter_mean, area_mean, smoothness_mean, compactness_mean, concavity_mean, concave_points_mean, symmetry_mean, fractal_dimension_mean;
  if( class0>=class1)    return 0;
  else 
    return 1;
}
}
