Converter _0;
Game _1;
Journey _2;
Login _3;
Menu _4;
Opening _5;
Popup _6;
Setting _7;
Shop _8;

class Routes{
  
  void update(int x, int y){
    switch(x){
      case 1:_1.sketch();break;
      case 4:_4.sketch();break;
      case 5:_5.sketch();break;
      default:
        image(bgHive, width/2, height/2, bgHive.width, bgHive.height);
        break;
    }
    switch(y){
      case 0:_0.sketch();break;    
      case 2:_2.sketch();break;
      case 3:_3.sketch();break;
      case 6:_6.sketch();break;
      case 7:_7.sketch();break;
      case 8:_8.sketch();break;
      default:break;
    }
  }
}
