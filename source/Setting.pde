class Setting{
  static final int page = 7;
  
  Setting(){
    setB=setA=0;
  }
  
  void sketch(){
    fill(0, 100);
    rect(0, 0, width, height);
    settingGame();
    if(input)textField();
  }
  
  float setA, setB;
  void settingGame(){
    image(box_set, width/2, height/2);
    image(tit_set, width/2, 80*res);
    fill(255);
    textSize(14);
    textAlign(LEFT);
    text("Volume", 504*res, 320*res);
    image(scr_vol0, width/2, 350*res);
    PImage scrA = scr_vol1.get(0, 0, scr_vol1.width+floor(setA), scr_vol1.height);
    image(scrA, width/2+setA/2, 350*res);
    image(scr_vol2, width/2+scrA.width/2-5+setA/2, 350*res);
    text("0%", 504*res, 390*res);
    text("50%", 744*res, 390*res);
    text("100%", 984*res, 390*res);
    
    text("Sensitivity", 504*res, 450*res);
    image(scr_vol0, width/2, 480*res);
    PImage scrB = scr_vol1.get(0, 0, scr_vol1.width+floor(setB), scr_vol1.height);
    image(scrB, width/2+setB/2, 480*res);
    image(scr_vol2, width/2+scrB.width/2-5+setB/2, 480*res);
    text("0%", 504*res, 520*res);
    text("50%", 744*res, 520*res);
    text("100%", 984*res, 520*res);
    
    image(ok, width/2, 580*res);
    
    image(btn_update, 900*res, 210*res);
    image(btn_change, 960*res, 210*res);
    image(div, 840*res, 210*res);
    textAlign(CENTER);
    
    textSize(33);
    text(User.getString("nama"), 340, 115);
  }
  
  boolean set = false;
  void click_on(){
    int x = mouseX; 
    int y = mouseY;
    if(input){
      if(overRect(x, y, width/2, height/2, 400*res, 60*res)){
      }else input = false;
    }else
    if(overRect(x, y, 900*res, 210*res, btn_update.width, btn_update.height)){sfx(4);
      clicked(btn_update.get(), 900*res, 210*res, btn_update.width, btn_update.height, INVERT);
      input = true;
    }else
    if(overRect(x, y, 960*res, 210*res, btn_change.width, btn_change.height)){sfx(4);
      clicked(btn_change.get(), 960*res, 210*res, btn_change.width, btn_change.height, INVERT);
      pp = -1;
      qq = 3;
    }else
    if(overRect(x, y, width/2, 580*res, ok.width, ok.height)){sfx(4);
      set = false;
      qq=-1;
    }
  }
  
  int flex = 100;
  float ges = 0;
  PVector click = new PVector(mouseX, mouseY);
  boolean btnV_A = false, btnV_B = false;
  void drag_on(){
    PImage scrA = scr_vol1.get(0, 0, scr_vol1.width+floor(setA), scr_vol1.height);
    PImage scrB = scr_vol1.get(0, 0, scr_vol1.width+floor(setB), scr_vol1.height);
    if(btnV_A){
      setA+=mouseX-(width/2+scrA.width/2-10*res+setA/2);
      if(setA>0)setA=0;
      else if(setA<-492*res)setA=-492*res;
      float volume = map(setA, -492*res, 0, 0, 1);
      for(int i=0;i<4;i++)
        sound[i].amp(volume);
      for(int i=0;i<12;i++)
        effect[i].amp(volume);
    }else 
    if(btnV_B){
      setB+=mouseX-(width/2+scrB.width/2-10*res+setB/2);
      if(setB>0)setB=0;
      else if(setB<-492*res)setB=-492*res;  
      flex = (int)map(setB, -492*res, 0, 0, 100);
    }
    ges = mouseX;
  }
  
  void pressm_on(){
    PImage scrA = scr_vol1.get(0, 0, scr_vol1.width+floor(setA), scr_vol1.height);
    PImage scrB = scr_vol1.get(0, 0, scr_vol1.width+floor(setB), scr_vol1.height);
    if(overObject(scr_vol2.height, width/2+scrA.width/2-5+setA/2, 350*res, mouseX, mouseY)){
      btnV_A = true;
    }else
    if(overObject(scr_vol2.height, width/2+scrB.width/2-5+setB/2, 480*res, mouseX, mouseY)){
      btnV_B = true;
    }
    click.x = mouseX;
    click.y = mouseY;
  }
  
  void releas_on(){
    btnV_A = false;
    btnV_B = false;
  }
  
  boolean input = false;
  void press_on(){
    if(input){
      if(keyCode==BACKSPACE){
        if(textInput.length()!=0)
          textInput = textInput.substring(0, textInput.length()-1);
      }
      else if(keyCode==ENTER){
        if(textInput.length()!=0){
          User.setString("nama", textInput);
          saveTables();
        }  
        textInput = "";
        input = false;
      }
      else if(textInput.length()<10)textInput+=key;
    }
  }
}
