int select = -1;

TableRow User;

class Login{
  static final int page = 3;
  int isKeep = 0;
  boolean input = false;
  int inputStatus = -1;
  PVector scroll = new PVector(0, 0);
  float scStat = 0;
  int direction = 0;
  
  void sketch(){
    textAlign(LEFT);
    textSize(26);
    imageMode(CENTER);
    image(box, width/2, height/2, box.width, box.height);
    addBar(width/2, height/2);
    image(upbox, width/2, height/2-box.height/2+upbox.height/2, upbox.width, upbox.height);
    image(lowbox, width/2, height/2+box.height/2-lowbox.height/2, lowbox.width, lowbox.height);
    image(board, width/2, height/2-box.height/2+board.height/4, board.width, board.height);
    image(l1, width/2, height/2+130*res, l1.width, l1.height);
    image(remember, width/2+btn_play.width/2-remember.width/2-26*res, height/2+130*res, remember.width, remember.height);
    button();
    if(scStat>0){
      strokeWeight(8*res);
      stroke(0, 50);
      line(width/2+280*res, height/2-156*res, width/2+280*res, height/2+40*res);
      stroke(#D67C23);
      float stLine = height/2-156*res;
      int lnDat = data.getRowCount();
      float lineLn = lnDat>2?stLine+588*res/lnDat:stLine;
      float yMove = map(scroll.y, 0, lnDat*90*res, 0, height/2-156*res);
      line(width/2+280*res, stLine-yMove, width/2+280*res, lineLn-yMove);
      scStat-=0.2;
      noStroke();
    }
    if(input)textField();
  }
  
  void button(){
    image(btn_create, width/2-btn_play.width/2+btn_create.width/2, height/2+130*res, btn_create.width, btn_create.height);
    if(select>-1)image(btn_play, width/2, height/2+210*res ,btn_play.width, btn_play.height);
    else clicked(btn_play.get(), width/2, height/2+210*res ,btn_play.width, btn_play.height, GRAY);
    image(btn_close, width/2+box.width/2-40*res, height/2-box.height/2+40*res, btn_close.width, btn_close.height);
  }
 
  void keep(){
    remember = login.get(1829+255*isKeep, 694, 209, 51);
    remember.resize(parseInt(remember.width*res), parseInt((remember.height*res)));
  }
  
  void click_on(){
    float x = mouseX, y = mouseY;
    float midW = width/2, midH = height/2;
    float upY = midH-box.height/2+upbox.height+45*res;
    if(input){
      if(overRect(x, y, midW, midH, 400*res, 60*res)){
      }else input = false;
    }else if(overRect(x, y, midW, midH+upbox.height/2-lowbox.height/2, upbox.width, box.height-upbox.height-lowbox.height)){ //bar selection
      int lnDat = data.getRowCount();
      for(int i=0;i<lnDat;i++){
        float posY = upY + scroll.y + i*90*res;
        if(overRect(x, y, midW+220*res-btn_update.width, posY, btn_update.width, btn_update.height)){
          clicked(btn_update.get(), midW+220*res-btn_update.width, posY, btn_update.width, btn_update.height, INVERT);
          TableRow tmp = data.getRow(i);
          textInput = tmp.getString("nama");
          inputStatus = i;
          input = true;
          sfx(4);
          break;
        }else
        if(overRect(x, y, midW+262.5*res-btn_delete.width/2, posY, btn_delete.width, btn_delete.height)){
          clicked(btn_delete.get(), midW+262.5*res-btn_delete.width/2, posY, btn_delete.width, btn_delete.height, INVERT);      
          if(select==i){
            select=-1;
            isKeep=0;
            keep();
          }
          qqTmp = 3;
          qq = 6;
          sp = 2;
          iTmp = i;
          sfx(4);
        }else
        if(overRect(x, y, midW, posY, upbox.width, 90*res)){
          select = i;
        }
      }
    }
    if(overRect(x, y, width/2+btn_play.width/2-remember.width/2-26*res, height/2+130*res, remember.width, remember.height)&&select!=-1){
      sfx(4);
      isKeep = (isKeep+1)%2;
      keep();
    }
    else if(overRect(x, y, width/2-btn_play.width/2+btn_create.width/2, height/2+130*res, btn_create.width, btn_create.height)){
      input=true;
      select=-1;
      clicked(btn_play.get(), width/2-btn_play.width/2+btn_create.width/2, height/2+130*res, btn_create.width, btn_create.height, INVERT);
      sfx(4);
    }
    else if(overRect(x, y, width/2, height/2+210*res ,btn_play.width, btn_play.height)&&select!=-1){
      clicked(btn_play.get(), width/2, height/2+210*res ,btn_play.width, btn_play.height, INVERT);
      User = data.getRow(select);
      pp=4;qq=-1;
      sfx(4);
    }
    else if(overRect(x, y, width/2+box.width/2-40*res, height/2-box.height/2+40*res, btn_close.width, btn_close.height)){
      clicked(btn_close.get(), width/2+box.width/2-40*res, height/2-box.height/2+40*res, btn_close.width, btn_close.height, INVERT);
      qq = 6;
      qqTmp = 3;
      sp = 1;
      sfx(4);
    } 
  }
  
  void press_on(){
    if(input){
      if(keyCode==BACKSPACE){
        if(textInput.length()!=0)
          textInput = textInput.substring(0, textInput.length()-1);
      }
      else if(keyCode==ENTER){
        if(textInput.length()!=0){
          if(inputStatus==-1){
            createUser(textInput);            
          }else{
            TableRow tmp = data.getRow(inputStatus);
            tmp.setString("nama", textInput);
            saveTables();
          }
        }  
        textInput = "";
        input = false;
      }
      else if(textInput.length()<10)textInput+=key;
    }
  }
  
  void drag_on(){
    float upY = height/2-box.height/2+upbox.height;
    float doY = height/2+box.height/2-lowbox.height;
    if(input)return;
    int lnDat = data.getRowCount();
    if(mouseY<direction&&doY<upY+scroll.y+90*res*lnDat)scroll.y-=4*res;
    else if(mouseY>direction&&upY>upY+scroll.y)scroll.y+=4*res;
    direction = mouseY;
    scStat = 10;
  }
  
  void addBar(float midW, float midH){
    float leftX = midW-upbox.width/2;
    float upY = midH-box.height/2+upbox.height+45*res;
    textSize(40*res);
    int lnDat = data.getRowCount();
    for(int i=0;i<lnDat;i++){
      TableRow tmp = data.getRow(i);
      fill((select>-1&&select==i)?#FFCD8A:#FFEFD9);
      float y = upY + scroll.y + i*90*res;
      if(y>3*midH/2||y<midH/3)continue;
      int bb = data.getRow(i).getInt("badge");
      int col = bb<22?0:(bb<43?1:2);
      PImage Itmp = badge[col].get();
      Itmp.resize(Itmp.width/2, Itmp.height/2);
      image(Itmp, leftX+Itmp.width/2+2*(2-col), y, Itmp.width, Itmp.height);
      image(barLine, midW-30*res, y+30*res, barLine.width, barLine.height);
      image(btn_update, midW+220*res-btn_update.width, y, btn_update.width, btn_update.height);
      image(btn_delete, midW+262.5*res-btn_delete.width/2, y, btn_delete.width, btn_delete.height);      
      text(tmp.getString("nama"), midW-barLine.width/2-20*res, y+10*res);
    }
  }
}
