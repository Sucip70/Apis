class Shop{
  static final int page = 8;
  
  String[] paket = {"Healt", "Boost", "Bonus Koin", "Bonus Diamond"};
  String[] dPack = {"Uhealth", "Uboost", "Ukoin", "Uberlian"};
  int [] price = {10, 10, 5, 5};
  
  void sketch(){
    fill(0, 100);
    rect(0, 0, width, height);
    image(box_shp, width/2, height/2);
    image(tit_shp, width/2, 40);
    image(btn_ext, 570, 50);

    fill(255);
    textSize(16);
    textAlign(LEFT);
    int bb = (User.getInt("badge")-1)/21;
    for(int i=0;i<4;i++){
      image(prog0, 450, 127+49*i);
      int pLn =  User.getInt(dPack[i])*prog1.width/10;
      image(prog1.get(0, 0, pLn, prog1.height), 450-prog1.width/2+pLn/2, 127+49*i);
      image(icon[i], 380, 107+49*i);      
      text(paket[i], 395, 113+49*i);
      if(User.getInt(dPack[i])>=floor(3+bb*3.5)) image(add_off, 547, 127+49*i);
      else image(add_on, 547, 127+49*i);
    }
    textSize(30);
    text(User.getString("nama"), 243, 130);
    image(bee[0], 280, 215);
  }
  
  void click_on(){
    int x = mouseX;
    int y = mouseY;
    if(overRect(x, y, 570, 50, btn_ext.width, btn_ext.height)){sfx(4);
      clicked(btn_ext.get(), 570, 50, btn_ext.width, btn_ext.height, INVERT);
      qq = -1;
    }else if(overRect(x, y, 280, 215, bee[0].width, bee[0].height)){
      sm = -1;
      sp = 5;
      qq = 6;
      qqTmp = 8;
    }else{
      int bb = (User.getInt("badge")-1)/21;
      for(int i=0;i<4;i++){
        if(overRect(x, y, 547, 127+49*i, add_on.width, add_on.height)&&User.getInt(dPack[i])<floor(3+bb*3.5)){
          clicked(add_on.get(), 547, 127+49*i, add_on.width, add_on.height, INVERT);
          if(User.getInt("berlian")<price[i]){sfx(8);continue;}
          sfx(4);
          iTmp = i;
          sp = 3;
          qq = 6;
          qqTmp = 8;
        }
      }
    }
  }
}
