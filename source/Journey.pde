int sl = -1; //selected level
int sm = -1; //selected map

class Journey{
  static final int page = 2;
  boolean level_pick = false;
  
  int k = 0;
  String[] areas = {"Lembah", "Hutan", "Dataran Tinggi", ""};
  void sketch(){
    fill(0, 100);
    rect(0, 0, width, height);
    image(box_pick, width/2, height/2);
    image(btn_tip, 190, 50);  
    image(btn_ext, 570, 50);
    if(level_pick){
      image(tit_lev, width/2, 40);
      image(btn_back, 190, 50);  
      for(int i=0;i<7;i++){
        float xx = 252.5;
        float yy = 130;
        if(i>3){
          xx = -45;
          yy = 215;
        }
        if(User.getInt((char)('A'+k)+str(i+1))==-1)image(level_off, xx+85*i, yy);
        else {
          image(level_on, xx+85*i, yy);
          image(star0, xx+85*i, yy+25);
          int st = User.getInt((char)('A'+k)+str(i+1))*star1.width/3;
          image(star1.get(0, 0, st, star1.height), -star1.width/2+st/2+xx+85*i, yy+25);
          textSize(35);
          textAlign(CENTER);
          fill(255);
          text((i+1), xx+85*i, yy+10);
        }
      }
    }else {
      image(tit_area, width/2, 40);
      image(btn_next, 540, height/2);
      image(btn_prev, 220, height/2);
      image(latar[k], width/2, height/2);
      text(areas[k], width/2, 300);
    }
  }
  
  //int dela = 0;
  void click_on(){
    float x = mouseX;
    float y = mouseY;
    //if(dela>0)dela--;
    if(overRect(x, y, 570, 50, btn_ext.width, btn_ext.height)){sfx(4);
      clicked(btn_ext.get(), 570, 50, btn_ext.width, btn_ext.height, INVERT);
      qq = -1;
    }else if(overRect(x, y, 190, 50, btn_tip.width, btn_tip.height)&&!level_pick){sfx(4);
      sm = k;
      sp = 5;
      qq = 6;
      qqTmp = 2;
    }else if(level_pick){
      if(overRect(x, y, 190, 50, btn_back.width, btn_back.height)){sfx(4);
        clicked(btn_back.get(), 190, 50, btn_back.width, btn_back.height, INVERT);
        level_pick = false;
      }else{
        for(int i=0;i<7;i++){
          float xx = 252.5;
          float yy = 130;
          if(i>3){
            xx = -45;
            yy = 215;
          }
          if(User.getInt((char)('A'+k)+str(i+1))!=-1){
            if(overRect(x, y, xx+85*i, yy, level_on.width, level_on.height)){sfx(4);
              sl = i;
              _1.setLevel();
              _1.initGame();
              pp = 1;qq = -1;
            }
          }
        }
      }
    }else{
      if(overRect(x, y, 540, height/2, btn_next.width, btn_next.height)){sfx(4);
        clicked(btn_next.get(), 540, height/2, btn_next.width, btn_next.height, INVERT);
        k=(k+1)%3;
      }
      else if(overRect(x, y, 220, height/2, btn_prev.width, btn_prev.height)){sfx(4);
        clicked(btn_prev.get(), 220, height/2, btn_prev.width, btn_prev.height, INVERT);
        k--;
        if(k<0)k=2;
      }else if(overRect(x, y, width/2, height/2, latar[k].width, latar[k].height)){sfx(4);
        level_pick = true;
        sm = k;
      }
    }
  }
}
