class Opening{
  static final int page = 5;
  int i=0, time = 200;
  
  void sketch(){
    background(255);
    image(open[i], width/2, height/2);
    
    if(i==2){
      if(mousePressed){
        pp = -1;
        qq = 3;
      }
    }else
    if(--time==0){i++;time=200;}
  }
} 
