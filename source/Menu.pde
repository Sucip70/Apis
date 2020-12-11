class Menu{
  static final int page = 4;
 
  void sketch(){
    textAlign(LEFT);
    textSize(16);
    image(home, width/2, height/2);
    image(shop, 270, 112);
    image(sign, 413, 234);
    image(hive, 589, 112);
    countDown();
    GUI();
  }
  
  void countDown(){
    int tmp = User.getInt("Kwaktu");
    if(tmp!=0){
      int timeN = countDate(new Dates(year(), month(), day(), hour(), minute(), second()));
      int timeR = tmp - timeN;
      if(timeR<0){
        User.setInt("berlian", User.getInt("berlian")+User.getInt("Kberlian"));
        User.setInt("Kwaktu", 0);
      }else{
        int h = timeR/3600;
        int m = (timeR%3600)/60;
        int s = (timeR%3600)%60;
        textSize(20);
        fill(255);
        textAlign(CENTER);
        text(co(h)+":"+co(m)+":"+co(s), 589, 70);
        textAlign(LEFT);
      }
    }
  }
  
  int countDate(Dates o){
    int ye = year();
    int y = (ye%4==0?366:365)*(o.year-ye)*86400;
    int mo = month();
    int mt = (mo==2?(ye%4==0?29:28):(mo%2==(mo<8?1:0)?31:30))*(o.month-mo)*86400;
    int d = 86400*o.day;
    int h = 3600*o.hour;
    int m = 60*o.minute;
    int s = o.second;
    return y+mt+d+h+m+s;
  }
  
  void GUI(){
    int col_star = User.getInt("badge");
    fill(255);
    image(xbar.get(45, 0, xbar.width-45, xbar.height), 110, 36);
    image(badge[col_star<22?0:(col_star<43?1:2)], 50, 40);
    textSize(16);
    text(col_star+"/63", 95, 43);
    treasure();
    
    fill(200);
    image(btn_quit, 700, 320);
    image(btn_tip, 640, 320);
    image(btn_set, 580, 320);
  }
  
  void treasure(){
    textSize(18);
    textAlign(CENTER);
    fill(255);
    image(xbar.get(30, 0, xbar.width-30, xbar.height), 500, 36);
    image(i_coin, 444, 36);
    text(str(User.getInt("koin")), 504, 43);
    image(xbar.get(30, 0, xbar.width-30, xbar.height), 670, 36);
    image(i_dimn, 614, 36);
    text(str(User.getInt("berlian")), 674, 43);
  }
  
  String co(int c){
    if(c/10>0)return str(c);
    return "0"+c;
  }
  
  void click_on(){
    int x = mouseX;
    int y = mouseY;
    
    if(overRect(x, y, 589, 113, hive.width, hive.height)){ //beehive
      if(User.getInt("Kwaktu")==0){
        clicked(hive.get(), 589, 113, hive.width, hive.height, INVERT);
        qq = 0;
      }else{
        _6.timeD = countDate(new Dates(year(), month(), day(), hour(), minute(), second()));
        sp = 4;
        qq = 6;
        qqTmp = -1;
      }
    }else 
    if(overRect(x, y, 413, 234, sign.width, sign.height)){
      clicked(sign.get(), 413, 234, sign.width, sign.height, INVERT);    
      qq = 2;
    }else
    if(overRect(x, y, 270, 112, shop.width, shop.height)){
      clicked(shop.get(), 270, 112, shop.width, shop.height, INVERT);    
      qq = 8;
    }else
    if(overRect(x, y, 700, 320, btn_quit.width, btn_quit.height)){sfx(4);
      clicked(btn_quit.get(), 700, 320, btn_quit.width, btn_quit.height, INVERT);  
      sp = 1;
      qq = 6;
      qqTmp = -1;
    }else
    if(overRect(x, y, 640, 320, btn_tip.width, btn_tip.height)){sfx(4);
      clicked(btn_tip.get(), 640, 320, btn_set.width, btn_set.height, INVERT);    
      qq = 6;
      sp = 6;
      qqTmp = -1;
    }else
    if(overRect(x, y, 580, 320, btn_set.width, btn_set.height)){sfx(4);
      clicked(btn_set.get(), 580, 320, btn_set.width, btn_set.height, INVERT);
      qq = 7;
    }
  }
}

class Dates{
  int year, month, day, hour, minute, second;
  Dates(int y, int mt, int d, int h, int m, int s){
    year = y;
    month = mt;
    day = d;
    hour = h;
    minute = m;
    second = s;
  }
}
