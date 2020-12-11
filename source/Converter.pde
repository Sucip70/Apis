class Converter{
  static final int page = 0;
  
  String[] paket = {"Pemula", "Profesional", "Grandmaster", "Jenius"};
  int[] sellC = {5, 10, 24, 24};
  int[] timeP = {5, 10, 24, 36};
  int[] pric = {5000, 10000, 24000, 42000};
  void sketch(){
    noStroke();
    fill(0, 100);
    rect(0, 0, width, height);
    image(box_con, width/2, height/2);
    image(tit_con, width/2, 40);
    image(btn_ext, 570, 50);
    fill(255);
    textAlign(LEFT);
    for(int i=0;i<4;i++){
      textSize(16);
      text(paket[i], 243, 102+53*i);
      textSize(12);
      image(time, 250, 117+53*i);
      text(timeP[i]+" jam", 263, 122+53*i);
      image(dimn, 320, 117+53*i);
      text(sellC[i]+" diamond", 333, 122+53*i);
      image(price[i], 490, 110+53*i);
    }
  }
 
  void click_on(){
    int x = mouseX;
    int y = mouseY;
    if(overRect(x, y, 570, 50, btn_ext.width, btn_ext.height)){sfx(4);
      clicked(btn_ext.get(), 570, 50, btn_ext.width, btn_ext.height, INVERT);
      qq = -1;
    }else{
      for(int i=0;i<4;i++){
        if(overRect(x, y, 490, 110+53*i, btn_ext.width, btn_ext.height)){
          if(User.getInt("koin")<pric[i]){sfx(8);continue;}
          sfx(4);
          iTmp = i;
          sp = 3;
          qqTmp = 0;
          qq = 6;
        }
      }
    }
  }
}
