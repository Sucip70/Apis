int selectedLevel = 0;

class Game{
  static final int page = 1;
  float[] dis = {57, 61.5, 101.5, 63.5, 66.5, -27.5, 67.5, 104.5, 165, 166, 140};
  float[] disI = {0, 0, 0, 0, 24, 25};
  int koin;
  int boosted = 0;
  int m = -1, w = 0;
  float xx = 0, yy = 0;
  PVector disM = new PVector(9999, 9999);
  PVector sens = new PVector(0, 0);
  Frog[] frog = new Frog[1];
  Wasp[] wasp = new Wasp[5];
  Snowman[] snowman = new Snowman[5];
  
  void sketch(){
    back();
    Sprite();
    GUI();
    solution();
  }
  
  void releas_on(){
    disM.x = 9999;
    disM.y = 9999;
    sens.x = 0;
    sens.y = 0;
  }
  
  void click_on(){
    int x = mouseX;
    int y = mouseY;
    if(_7.set||tur){}
    else if(pausing){
      float mid = width/2;
      if(overRect(x, y, mid, 280*res, btn_res.width, btn_res.height)){sfx(4);
        clicked(btn_res.get(), mid, 280*res, btn_res.width, btn_res.height, INVERT);
        pausing = false;
      }else
      if(overRect(x, y, mid, 380*res, btn_sett.width, btn_sett.height)){sfx(4);
        clicked(btn_sett.get(), mid, 380*res, btn_sett.width, btn_sett.height, INVERT);
        _7.set = true;
        qq = 7;
      }else
      if(overRect(x, y, mid, 480*res, btn_exit.width, btn_exit.height)){sfx(4);
        clicked(btn_exit.get(), mid, 480*res, btn_exit.width, btn_exit.height, INVERT);
        pp = 4;qq = 2; 
      }
    }
    else if(end){
      if(overRect(x, y, 320, 300, btn_list.width, btn_list.height)){sfx(4);
        clicked(btn_list.get(), 320, 300, btn_list.width, btn_list.height, INVERT);
        pp = 4;qq = 2;sl = -1;
      }
      else if(overRect(x, y, 380, 300, btn_repl.width, btn_repl.height)){sfx(4);
        clicked(btn_repl.get(), 380, 300, btn_repl.width, btn_repl.height, INVERT);
        initGame();
        end = false;
      }
      else if(overRect(x, y, 440, 300, btn_con.width, btn_con.height)&&sl<6){sfx(4);
        clicked(btn_con.get(), 440, 300, btn_con.width, btn_con.height, INVERT);
        if(User.getInt((char)('A'+sm)+str(sl+2))==0){
          sl++;
          setLevel();
          initGame();
          end = false;
        }
      }
    }
    else if(overObject(btn_pause.height, 1460*res, 60*res, mouseX, mouseY)){sfx(4);
      clicked(btn_pause.get(), 1460*res, 90*res, btn_pause.width, btn_pause.height, INVERT);
      pausing = true;
    }else if(overObject(btn_tip.height, 1330*res, 60*res, mouseX, mouseY)){sfx(4);
      clicked(btn_pause.get(), 1460*res, 90*res, btn_pause.width, btn_pause.height, INVERT);
      tur = true;
      qq = 6;
      sp = 0;
      qqTmp = -1;
    }else if(overObject(boost.width, 120*res, 600*res, mouseX, mouseY)&&timeBoost==0){sfx(3);
      boosted = 10;
      timeBoost = 300;
    }
  }
  
  void drag_on(){
    if(pausing||end){
  
    }else{
      float x = mouseX;
      float y = mouseY;
      if(disM.x==9999&&disM.y==9999){
        disM.x = x-pBee.x;
        disM.y = y-pBee.y;
        sens.x = x;
        sens.y = y;
      }
      if(xx<x)m = 1;
      else m = -1;
      xx = x;
      if(yy<y)w = 1;
      else w = -1;
      yy = y;
      float vx = map(sens.x-x, 0, 200*res-_7.flex/2, 0, 200*res)-(sens.x-x);
      float vy = map(sens.y-y, 0, 200*res-_7.flex/2, 0, 200*res)-(sens.y-y);
      float ox = x-disM.x-vx;
      float oy = y-disM.y-vy;
      if(ox>width/8 && ox<width-width/8){
        pBee.x = ox;
      }
      if(oy>bee[0].height/2 && oy<height-bee[0].height/2){
        pBee.y = oy;
      }
    }
  }
  
  boolean getRes;
  void result(boolean c){
    fill(0, 100);
    rect(0, 0, width, height);
    image(box_sol, width/2, height/2);
    image(c?tit_win:tit_los, width/2, 100*res);
    fill(255);
    textSize(20);
    text("LEVEL "+1, width/2, 100);
    image(star00, width/2, 140);
    int hh = User.getInt("Uhealth")*40+100;
    int str = (floor(blood)+hh/5-5)/((hh-hh/10)/3);
    int st = str*star01.width/3;
    
    image(star01.get(0, 0, st, star01.height), width/2-star01.width/2+st/2, 140);
    textSize(16);
    textAlign(LEFT);
    text("Sisa Healt: ", 300, 192);
    text("Jumlah Koin: ", 300, 218);
    text("Total Skor: ", 300, 254);
    textAlign(RIGHT);
    text(floor(blood), 440, 192);
    text(koin, 440, 218);
    int skor = (floor(blood)+koin)*10;
    text(skor, 440, 254);
    if(getRes){
      getRes = false;
      if(c){sfx(10);
        if(User.getInt((char)('A'+sm)+str(sl+1))==0){
          if(sl!=6)User.setInt((char)('A'+sm)+str(sl+2), 0);
          else if(sm!=2)User.setInt((char)('A'+sm+1)+"1", 0);
        }
        int stN = str - User.getInt((char)('A'+sm)+str(sl+1));
        if(stN>0){
          User.setInt((char)('A'+sm)+str(sl+1), str);
          User.setInt("badge", User.getInt("badge")+stN);
        }
        User.setInt("koin", User.getInt("koin")+skor+skor*User.getInt("Ukoin")/2);
      }else sfx(7);
    }
    
    textAlign(CENTER);
    image(i_c1, 460, 185);
    image(i_h1, 460, 211);
    
    image(btn_list, 320, 300);
    image(btn_repl, 380, 300);
    image(btn_con, 440, 300);
  }
  
  boolean end = false;
  void solution(){
    if(f==maxF) {
      end=true;
      result(true);
    }
    if(blood<1) {
      end=true;
      result(false);
    }
  }
  
  boolean tur = false;
  void GUI(){
    blood();
    image(btn_pause, 1460*res, 60*res);
    image(btn_tip, 1330*res, 60*res);
    task();
    i_coin();
    boosted();  
    navigate();
    if(pausing){
      pauseGame();
    }
  }
  
  float hPos, mN, fPos, mF;
  void navigate(){
    boolean flagF = true;
    for(int i=0;i<pi_flower.size();i++){
      if(width>pi_flower.get(i)+realX&&0<pi_flower.get(i)+realX){
        flagF = false;
        break;
      }else{
        if (width<pi_flower.get(i)+realX){mF=-1;fPos=1460*res;}
        else {mF=1;fPos=60*res;}
      }
    }
    boolean flagH = false;
    if(width<xHive[sm]||0>xHive[sm]){
      flagH = true;
      if (width<xHive[sm]){mN=1;hPos=1460*res;}
      else {mN=-1;hPos=60*res;}
      pushMatrix();
        translate(hPos, (350+(flagF&&mN!=mF?60:0))*res);
        scale(mN, 1);
        image(arr_hiv, 0, 0);
      popMatrix(); 
    }
    if(flagF){
      pushMatrix();
        translate(fPos, (350-(flagH&&mN!=mF?60:0))*res);
        scale(mF, 1);
        image(arr_flow, 0, 0);
      popMatrix(); 
    }
  }
  
  boolean pausing;
  void pauseGame(){
    fill(0, 200);
    rect(0, 0, width, height);
    image(box_pas, width/2, height/2);
    image(tit_pas, width/2, 120*res);
    image(btn_res, width/2, 280*res);
    image(btn_sett, width/2, 380*res);
    image(btn_exit, width/2, 480*res);
    
  }
  
  int maxF, f;
  void task(){
    image(xbar0, 888*res, 60*res);
    image(i_flower[sm], 780*res, 60*res);
    fill(255);
    textSize(24*res);
    text(str(f)+"/"+str(maxF), 892*res, 66*res);
  }
  
  void i_coin(){
    image(xbar0, 600*res, 60*res);
    image(i_coin, 500*res, 60*res);
    text(str(koin), 614*res-textWidth(str(koin))/2, 66*res);
  }
  
  void Sprite(){
    seti_flower();
    player();
    if(sm==1)
    for(int i=0;i<wasp.length;i++)
      wasp[i].create();
  }
  
  float blood;
  void blood(){
    float bb = 100+User.getInt("Uhealth")*40;
    image(xbar1, 240*res, 60*res);
    int decB = floor(map(blood, 0, bb, -healt.width, 0));
    PImage hlt = healt.get(0, 0, healt.width+decB, healt.height);
    image(hlt, 240*res+decB/2, 56*res);
    image(i_heart, 80*res, 60*res);
    fill(255);
    textSize(24*res);
    text(str((int)blood)+"/"+floor(bb), 254*res, 66*res);
  }
  
  void init_flower(){
    pi_flower.clear();
    for(int i=0;i<maxF;i++)
      pi_flower.add(random(limitArea.x+width/6, limitArea.y-width/6)); 
  }
  
  ArrayList<Float> pi_flower = new ArrayList<Float>();
  void seti_flower(){
    for(int i=0;i<pi_flower.size();i++){
      float px = pi_flower.get(i);
      image(flower[sm], px+realX, 670*res);
      if(overObject(flower[sm].width, pBee.x, pBee.y, px+realX, 640*res)){
        if(suck&&i==sFlo&&!full){
          pickUp(px);
        }else
        if(!suck){
          sFlo = i;
          suck = true;
        }
      }else if(i==sFlo){
        suck = false;
        timePick=80*res;
        sFlo = -1;
      }
    }
  }
  
  PVector pBee;

  int fra;
  void player(){
    if(!(_7.set||pausing||end||tur))move();
    playerEvent(pBee.x, pBee.y);
    pushMatrix();
      translate(pBee.x, pBee.y);
      if(!(_7.set||pausing||end||tur))fra = frameCount%15/3;
      scale(m, 1);
      noStroke();
      if(full)fill(#FFCD00, 200);
      else noFill();
      ellipse(20*res, 60*res, 40*res, 40*res);
      image(bee[fra], 0, 0);
    popMatrix();
  }
  
  int [] xHive = {470, 470, 616}, yHive = {90, 90, 139};
  void back(){ 
    for(int i=4;i>=0;i--){
      if(i==1&&sm!=2)image(beehive, xHive[sm], yHive[sm]);
      if(i==0){
        if(sm==0)
        for(int j=0;j<frog.length;j++){
          println("ee"+j+" "+frog.length);
          frog[j].create();
        }
        else if(sm==2)
        for(int j=0;j<snowman.length;j++)
          snowman[j].create();
      }
      image(bg[sm][i], xBG[i], height/2);
      image(bg[sm][i], bg[sm][i].width+xBG[i], height/2);
      if(i==1&&sm==2)image(beehive, xHive[sm], yHive[sm]);
    }
    
    image(icon[2], bKoin.x+realX, bKoin.y);
    if(overObject(bee[0].width, pBee.x, pBee.y, bKoin.x+realX, bKoin.y)){
      koin+=10;
      randomKoin();
      sfx(5);
    }
  }
  
  PVector bKoin = new PVector();
  void randomKoin(){
    bKoin.x = random(limitArea.x+100, limitArea.y-100);
    bKoin.y = random(20, height-20);
  }
  
  void moveBoost(int x){
    int f = x/4;
    if(x==-1)return;
    for(int i=4;i>=0;i--){
      xBG[i]-=m*(x-i*f);
      if(i==1)xHive[sm]-=m*(x-i*f);
      if(xBG[i]<-bg[sm][0].width/2)xBG[i]=bg[sm][0].width/2;
      else if(xBG[i]>bg[sm][0].width/2)xBG[i]=-bg[sm][0].width/2;
    }
  }
  
  int timeBoost = 0;
  void boosted(){
    if(timeBoost==0)image(boost, 120*res, 600*res);
    else{
      image(boostC, 120*res, 600*res);
      timeBoost--;
      fill(255);
      textSize(40);
      text(str(timeBoost/60+1), 120*res, 628*res);
    }
  }
  
  int realX;
  PVector limitArea = new PVector(-1520, 1520);
  void move(){
    if(pBee.x<width/6 || pBee.x>width-width/6){
      fill(0, 30);
      if(pBee.x<width/6){
        rect(0, 0, width/6, height);
        pushMatrix();
          translate(65, 180);
          scale(m, 1);
          triangle(-25, -30, -25, 30, 25, 0);
        popMatrix();
      }else {
        rect(width, 0, -width/6, height);
        pushMatrix();
          translate(695, 180);
          scale(m, 1);
          triangle(-25, -30, -25, 30, 25, 0);
        popMatrix();
      }
      if(m==1&&realX-4>limitArea.x){
        moveBoost(4);
        realX-=4;
      }else if(m==-1&&realX+4<limitArea.y){
        moveBoost(4);
        realX+=4;
      }
    }
    if(boosted>0){
      int bb = User.getInt("Uboost")*2+16;
      if(m==1){
        if(realX-bb>limitArea.x){
          moveBoost(bb);
          realX-=bb;
        }else if(pBee.x+bb<width-width/8)pBee.x+=bb;
        else pBee.x = width-width/8;
      }else if(m==-1){
        if(realX+bb<limitArea.y){
          moveBoost(bb);
          realX+=bb;
        }else if(pBee.x-bb>width/8)pBee.x-=bb;
        else pBee.x = width/8;
      }
      boosted--;
    }
  }

  boolean suck;
  int sFlo;
  float timePick;
  void pickUp(float x){
    strokeWeight(6);
    stroke(20);
    float tmp = flower[sm].width/2;
    line(x-tmp+realX, 580*res, x+tmp+realX, 580*res);
    stroke(0, 255, 0);
    line(x-tmp+realX, 580*res, x-tmp+realX+timePick, 580*res);
    strokeWeight(2*res);
    stroke(0);
    if(timePick>0)
      timePick-=0.5;
    else {
      suck = false;
      timePick=flower[sm].width;
      pi_flower.remove(sFlo);
      sFlo = -1;
      full = true;
    }
  }
  
  boolean full;
  float timeSet;
  void playerEvent(float x, float y){
    if(full){
      float ln = beehive.height;
      if(overObject((int)ln, xHive[sm], yHive[sm], x, y)){
        noFill();
        strokeWeight(8*res);
        stroke(200);
        ellipse(xHive[sm], yHive[sm], ln, ln);
        stroke(0, 255, 0);
        arc(xHive[sm], yHive[sm], ln, ln, 0, timeSet);
        strokeWeight(2*res);
        stroke(0);
        if(timeSet<TWO_PI)
          timeSet+=0.05;
        else{
          f++;
          full = false;
          sfx(5);
        }
      }else timeSet = 0;
    }
  }
  
  void initGame(){
    pBee = new PVector(width/2, height/2);
    pi_flower.clear();
    f = 0;
    init_flower();
    blood = 100+User.getInt("Uhealth")*40;
    full = false;
    suck = false;
    pausing = false;
    timeSet = 0;
    timePick = 40;
    sFlo = -1;
    for(int i=0;i<5;i++)
      xBG[i] = 0;
    realX = 0;
    getRes = true;
    fra = 0;koin=0;
    hPos=0;fPos=0;
    end = false;
    resetBeehivePos();
    setSprite();
    randomKoin();
  }
  
  void resetBeehivePos(){
    switch(sm){
      case 0:xHive[0] = 470;break;
      case 1:xHive[1] = 470;break;
      case 2:xHive[2] = 616;break;
    }
  }
  
  void setSprite(){
    for(int i=0;i<wasp.length;i++)
      wasp[i] = new Wasp(randomSpawn(), random(0, height));  
    for(int i=0;i<frog.length;i++)
      frog[i] = new Frog(randomSpawn(), random(0, height), (int)random(0, height));
    for(int i=0;i<snowman.length;i++)
      snowman[i] = new Snowman(randomSpawn(), height/2);
  }
  
  float randomSpawn(){
    float a = random(limitArea.x, 0);
    float b = random(width, limitArea.y);
    return floor(random(0, 10))%2==0?a:b;
  }
  
  float[] xBG = new float[5];
  void initBG(){
    for(int i=0;i<5;i++){
      bg[0][i] = loadImage("data/a_"+(i+1)+".png");
      bg[0][i].resize(bg[0][i].width/2, bg[0][i].height/2); 
      bg[1][i] = loadImage("data/b_"+(i+1)+".png");
      bg[1][i].resize(bg[1][i].width/2, bg[1][i].height/2);
      bg[2][i] = loadImage("data/c_"+(i+1)+".png");
      bg[2][i].resize(bg[2][i].width/2, bg[2][i].height/2);
    }
  }
  
  OLevel log = new OLevel();
  void setLevel(){
    switch(sm){
      case 0:
        switch(sl){
          case 0:log.e=1;log.f=1;break;
          case 1:log.e=2;log.f=3;break;
          case 2:log.e=2;log.f=5;break;
          case 3:log.e=3;log.f=3;break;
          case 4:log.e=3;log.f=5;break;
          case 5:log.e=4;log.f=3;break;
          case 6:log.e=4;log.f=5;break;
        }
        frog = new Frog[log.e];
        println("frogie: "+log.e);
        break;
      case 1:
        switch(sl){
          case 0:log.e=3;log.f=1;break;
          case 1:log.e=3;log.f=3;break;
          case 2:log.e=3;log.f=5;break;
          case 3:log.e=5;log.f=3;break;
          case 4:log.e=5;log.f=5;break;
          case 5:log.e=7;log.f=3;break;
          case 6:log.e=7;log.f=5;break;
        }
        wasp = new Wasp[log.e];
        break;
      case 2:
        switch(sl){
          case 0:log.e=1;log.f=1;break;
          case 1:log.e=1;log.f=5;break;
          case 2:log.e=2;log.f=3;break;
          case 3:log.e=2;log.f=5;break;
          case 4:log.e=3;log.f=3;break;
          case 5:log.e=3;log.f=5;break;
          case 6:log.e=3;log.f=10;break;
        }
        snowman = new Snowman[log.e];
        break;
    }
    maxF = log.f;
    pi_flower.clear();
    init_flower();
  }
  
class OLevel{
  int e;
  int f;
}

class Frog{
  PVector gravity = new PVector(0, 0.2*res);
  PVector velocity = new PVector(0, 0.2*res);
  PVector pos; 
  boolean frogJump = false;
  float toungeV = 0;
  float toungeG = 2*res;
  float toungeL = 2*res;
  float targetX, targetY;
  int div;
  
  Frog(float x, float y, int d){
    pos = new PVector(x, y);
    div = d;
  }
  
  int iF = 0, m = -1;
  void create(){
    pushMatrix();
      translate(pos.x+realX, pos.y);
      scale(m, 1);
      image(Ifrog[iF], -disI[iF]/2, 0);
    popMatrix();
    if(!(_7.set||pausing||end||tur))jump();
  }
  
  void jump(){
    pos.add(velocity);
    velocity.add(gravity);
    if(pos.y>height-Ifrog[0].height/2){
      if(((frameCount+div)/60)%6==0&&!sht)
        velocity.y = -12*res;
      pos.y = height-Ifrog[0].height/2;
      if(pos.x+realX<pBee.x){
        frogJump=false;
        m=-1;
      }else{
        frogJump=true;
        m=1;
      }
      if(iF==5){
        shoot();
      }   
      if(!sht&&iF>3){
        if(iF==5){
          if(!(_7.set||pausing||end||tur))iF = iF-(frameCount+div)%30/29;
        }else iF = iF-(frameCount+div)%4/3;
      }else if(sht&&iF>0&&overObject(720, pos.x+realX, pos.y, pBee.x, pBee.y)){
        if(!(_7.set||pausing||end||tur))iF = iF+(frameCount+div)%4/3;
      }else{
        if(!(_7.set||pausing||end||tur))iF = 0;
        if(((frameCount+div)/60)%3==0)sht=false;
      }
    }else if(pos.y<height){
      if(frogJump)pos.x-=3*res;
      else pos.x+=3*res;
      if(!(_7.set||pausing||end||tur))iF = (frameCount+div)%24/12+1;
      sht = true;
      toungeV = 0;
      toungeL = 2*res;
    }
  }
  boolean sht = false;
  void shoot(){
    pushMatrix();
      if(keyPressed)sht=true;
      if(sht){
        targetX = pBee.x;
        targetY = pBee.y;
        toungeV = 90*res;
        sht = false;
      }
      else{
        toungeL += toungeV;
        toungeV -= toungeG;
        if(toungeL+toungeV>dist(targetX, targetY, pos.x+realX, pos.y)){
          toungeL = dist(targetX, targetY, pos.x+realX, pos.y)-10*res;  
          if(overObject(bee[0].width, pBee.x, pBee.y, targetX, targetY)){
            if(!effect[11].isPlaying())sfx(11);
            if(blood-5>0)blood-=5;
            else blood = 0;
          }
        }
        if(toungeL<2*res){
          toungeV = 0;
          toungeL = 2*res;
        }
        float a1 = atan2(targetY - pos.y, targetX - pos.x-realX);
        float tx = targetX - cos(a1)*toungeL;
        float ty = targetY - sin(a1)*toungeL;
        float a2 = atan2(ty - pos.y, tx - pos.x-realX);
        pushMatrix();
          translate(pos.x+realX, pos.y);
          rotate(a2);
          stroke(#FFB27E);
          strokeWeight(16*res);
          line(0, 0, toungeL, 0);
          noStroke();
        popMatrix();
      }
    popMatrix();
  }
}

  class Wasp{
    PVector pos;
    int m = 1;
    
    Wasp(float x, float y){
      pos = new PVector(x, y);
    }
    
    int fra = 0;
    void create(){
      pushMatrix();
        translate(pos.x+realX, pos.y);
        scale(this.m, 1);
        if(!(_7.set||pausing||end||tur))fra = frameCount%25/5;
        image(Iwasp[fra], 0, 0);
      popMatrix();
      if(!(_7.set||pausing||end||tur))fly();
    }
    
    float degree = random(0, 360);
    void fly(){
      float mm = pos.x;  
      degree = random(-25+degree, 25+degree);
      pos.x += v*sin(radians(degree));
      pos.y += v*cos(radians(degree));
      fill(255);
      if(overObject(200, pos.x+realX, pos.y, pBee.x, pBee.y)){
        if(overObject(50, pos.x+realX, pos.y, pBee.x, pBee.y)&&blood>0){
          if(!effect[11].isPlaying())sfx(11);
          blood--;
          if(blood<0)blood=0;
        }
        if(pos.x+realX<pBee.x){
          this.m=-1;
        }else{
          this.m=1;
        }
        sting();
        v = 10*res;
      }else{
        if(pos.x<mm)this.m=1;
        else this.m=-1;
        v = 4*res;
      }
      if(pos.x+realX<limitArea.x)pos.x = limitArea.x-realX;
      else if(pos.x+realX>limitArea.y)pos.x = limitArea.y-realX;
      if(pos.y<-Iwasp[0].height)pos.y = -Iwasp[0].height;
      else if(pos.y>width)pos.y = width;
    }
    
    void sting(){
      float a1 = atan2(pBee.y - pos.y, pBee.x - (pos.x+realX));
      float tx = pos.x - cos(a1);
      float ty = pos.y - sin(a1);
      float a2 = atan2(ty - pos.y, tx - pos.x);
      degree = 270-degrees(a2);
    }
    float v = 4*res;
  }
  
class Snowman{
  PVector pos;
  int m = 1;
  int frame = 0;
  
  Snowman(float x, float y){
    pos = new PVector(x, y);
  }
  
  void create(){
    pushMatrix();
      translate(pos.x+realX, pos.y);
      scale(m, 1);
      image(Isnow[frame], dis[frame]/2-35, 0);
    popMatrix();
    if(!(_7.set||pausing||end||tur))shoot();
  }
  
  PVector to = new PVector();
  ArrayList<PVector> ball = new ArrayList<PVector>();
  boolean sht = false;
  void shoot(){
    if(pBee.x<pos.x+realX)m=-1;
    else m=1;
    float xx = pos.x+realX;
    if(overObject(1520, xx, pos.y, pBee.x, pBee.y)){
      if(frame==10){
        if(!(_7.set||pausing||end||tur))frame = 0;
        sht = true;
        float a1 = atan2(pBee.y - pos.y, pBee.x - xx);
        float tx = xx - cos(a1);
        float ty = pos.y - sin(a1);
        float a2 = atan2(ty - pos.y, tx - xx);
        ball.add(new PVector(xx, pos.y, 270-degrees(a2)));
      }else if(!sht){
        if(!(_7.set||pausing||end||tur))frame=(frame+frameCount%10/9)%11;
      }
    }else frame = 0;
    if(ball.size()!=0){
      if(sht){
        println(ball.get(0).x+" "+ball.get(0).y);
        ball.get(0).x += 8*sin(radians(ball.get(0).z));
        ball.get(0).y += 8*cos(radians(ball.get(0).z));
        println(ball.get(0).x+" "+ball.get(0).y+" "+ball.get(0).z);
        image(Iball, ball.get(0).x, ball.get(0).y);
        if(overObject(Iball.width, ball.get(0).x, ball.get(0).y, pBee.x, pBee.y)){
          if(!effect[11].isPlaying())sfx(11);
          blood-=20;
          if(blood<0)blood=0;
        }
        if(dist(ball.get(0).x, ball.get(0).y, pos.x+realX, pos.y)>600){
          ball.remove(0);
          sht = false;
        }
      }
    }
  }
}
}
