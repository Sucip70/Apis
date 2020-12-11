import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Apis extends PApplet {

Routes route = new Routes();
Table table;
String last = "";
float res = 0.5f;
Image img;

public void setup(){
  
  loadData();
  frameRate(60);
  img = new Image();
  _0 = new Converter();
  _1 = new Game();
  _2 = new Journey();
  _3 = new Login();
  _4 = new Menu();
  _5 = new Opening();
  _6 = new Popup();
  _7 = new Setting();
  _8 = new Shop();
  imageMode(CENTER);
  noCursor();
}

int pp = 5, qq = -1;
//int pp = -1, qq = 3;
public void draw(){
  textAlign(CENTER);
  route.update(pp, qq);
  SoundSys();
  fill(0xff31F0FF, 127);
  noStroke();
  ellipse(mouseX, mouseY, 30, 30);
}
public boolean overObject(float d, float lx, float ly, float x, float y){
  float disX = lx - x;
  float disY = ly - y;
  if(sqrt(sq(disX)+sq(disY))<d/2)
    return true;
  return false;
}

public boolean overRect(float x, float y, float x0, float y0, float w, float h){
  if(x0-w/2<x && x<x0+w/2 && y0-h/2<y && y<y0+h/2)return true;
  return false;
}

public void clicked(PImage img, float x, float y, float w, float h, int mode){
  img.filter(mode);
  image(img, x, y, w, h);
}

String textInput = "";
public void textField(){
  stroke(0);
  strokeWeight(2*res);
  fill(0, 100);
  rect(0, 0, width, height);
  fill(255);
  rect(width/2-200*res, height/2-30*res, 400*res, 60*res);
  textSize(40*res);
  textAlign(LEFT);
  fill(0);
  text(textInput, width/2-180*res, height/2+14*res);
  float tw = textWidth(textInput)+width/2-180*res;
  if((2*frameCount/60)%2>0)
    line(tw, height/2-20*res, tw, height/2+20*res);
  fill(255);
}
class Converter{
  static final int page = 0;
  
  String[] paket = {"Pemula", "Profesional", "Grandmaster", "Jenius"};
  int[] sellC = {5, 10, 24, 24};
  int[] timeP = {5, 10, 24, 36};
  int[] pric = {5000, 10000, 24000, 42000};
  public void sketch(){
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
 
  public void click_on(){
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
int selectedLevel = 0;

class Game{
  static final int page = 1;
  float[] dis = {57, 61.5f, 101.5f, 63.5f, 66.5f, -27.5f, 67.5f, 104.5f, 165, 166, 140};
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
  
  public void sketch(){
    back();
    Sprite();
    GUI();
    solution();
  }
  
  public void releas_on(){
    disM.x = 9999;
    disM.y = 9999;
    sens.x = 0;
    sens.y = 0;
  }
  
  public void click_on(){
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
  
  public void drag_on(){
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
  public void result(boolean c){
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
  public void solution(){
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
  public void GUI(){
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
  public void navigate(){
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
  public void pauseGame(){
    fill(0, 200);
    rect(0, 0, width, height);
    image(box_pas, width/2, height/2);
    image(tit_pas, width/2, 120*res);
    image(btn_res, width/2, 280*res);
    image(btn_sett, width/2, 380*res);
    image(btn_exit, width/2, 480*res);
    
  }
  
  int maxF, f;
  public void task(){
    image(xbar0, 888*res, 60*res);
    image(i_flower[sm], 780*res, 60*res);
    fill(255);
    textSize(24*res);
    text(str(f)+"/"+str(maxF), 892*res, 66*res);
  }
  
  public void i_coin(){
    image(xbar0, 600*res, 60*res);
    image(i_coin, 500*res, 60*res);
    text(str(koin), 614*res-textWidth(str(koin))/2, 66*res);
  }
  
  public void Sprite(){
    seti_flower();
    player();
    if(sm==1)
    for(int i=0;i<wasp.length;i++)
      wasp[i].create();
  }
  
  float blood;
  public void blood(){
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
  
  public void init_flower(){
    pi_flower.clear();
    for(int i=0;i<maxF;i++)
      pi_flower.add(random(limitArea.x+width/6, limitArea.y-width/6)); 
  }
  
  ArrayList<Float> pi_flower = new ArrayList<Float>();
  public void seti_flower(){
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
  public void player(){
    if(!(_7.set||pausing||end||tur))move();
    playerEvent(pBee.x, pBee.y);
    pushMatrix();
      translate(pBee.x, pBee.y);
      if(!(_7.set||pausing||end||tur))fra = frameCount%15/3;
      scale(m, 1);
      noStroke();
      if(full)fill(0xffFFCD00, 200);
      else noFill();
      ellipse(20*res, 60*res, 40*res, 40*res);
      image(bee[fra], 0, 0);
    popMatrix();
  }
  
  int [] xHive = {470, 470, 616}, yHive = {90, 90, 139};
  public void back(){ 
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
  public void randomKoin(){
    bKoin.x = random(limitArea.x+100, limitArea.y-100);
    bKoin.y = random(20, height-20);
  }
  
  public void moveBoost(int x){
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
  public void boosted(){
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
  public void move(){
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
  public void pickUp(float x){
    strokeWeight(6);
    stroke(20);
    float tmp = flower[sm].width/2;
    line(x-tmp+realX, 580*res, x+tmp+realX, 580*res);
    stroke(0, 255, 0);
    line(x-tmp+realX, 580*res, x-tmp+realX+timePick, 580*res);
    strokeWeight(2*res);
    stroke(0);
    if(timePick>0)
      timePick-=0.5f;
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
  public void playerEvent(float x, float y){
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
          timeSet+=0.05f;
        else{
          f++;
          full = false;
          sfx(5);
        }
      }else timeSet = 0;
    }
  }
  
  public void initGame(){
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
  
  public void resetBeehivePos(){
    switch(sm){
      case 0:xHive[0] = 470;break;
      case 1:xHive[1] = 470;break;
      case 2:xHive[2] = 616;break;
    }
  }
  
  public void setSprite(){
    for(int i=0;i<wasp.length;i++)
      wasp[i] = new Wasp(randomSpawn(), random(0, height));  
    for(int i=0;i<frog.length;i++)
      frog[i] = new Frog(randomSpawn(), random(0, height), (int)random(0, height));
    for(int i=0;i<snowman.length;i++)
      snowman[i] = new Snowman(randomSpawn(), height/2);
  }
  
  public float randomSpawn(){
    float a = random(limitArea.x, 0);
    float b = random(width, limitArea.y);
    return floor(random(0, 10))%2==0?a:b;
  }
  
  float[] xBG = new float[5];
  public void initBG(){
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
  public void setLevel(){
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
  PVector gravity = new PVector(0, 0.2f*res);
  PVector velocity = new PVector(0, 0.2f*res);
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
  public void create(){
    pushMatrix();
      translate(pos.x+realX, pos.y);
      scale(m, 1);
      image(Ifrog[iF], -disI[iF]/2, 0);
    popMatrix();
    if(!(_7.set||pausing||end||tur))jump();
  }
  
  public void jump(){
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
  public void shoot(){
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
          stroke(0xffFFB27E);
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
    public void create(){
      pushMatrix();
        translate(pos.x+realX, pos.y);
        scale(this.m, 1);
        if(!(_7.set||pausing||end||tur))fra = frameCount%25/5;
        image(Iwasp[fra], 0, 0);
      popMatrix();
      if(!(_7.set||pausing||end||tur))fly();
    }
    
    float degree = random(0, 360);
    public void fly(){
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
    
    public void sting(){
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
  
  public void create(){
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
  public void shoot(){
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
PImage[][] bg = new PImage[3][5];
PImage[] open = new PImage[3];
PImage bgHive, beehive, home, login;
PImage box_pick, star0, star1, level_on, level_off, tit_area, tit_lev, btn_next, btn_prev;//area level
PImage box_con, time, dimn, tit_con;//converter
PImage xbar, xbar0, xbar1, healt, i_heart, i_coin, i_dimn, arr_flow, arr_hiv, boost, boostC,
      btn_pause, btn_quit, btn_set, btn_tip, btn_ext, btn_back, btn_list, btn_repl, btn_con;//icons
PImage box_tur, box_pop, btn_ya, btn_tidak;//popup
PImage box_sol, tit_win, tit_los, star00, star01, i_c1, i_h1;//level complete
PImage barLine, board, box, btn_close, btn_create, btn_delete, btn_play, btn_update, l1, remember, upbox, lowbox;//login
PImage hive, shop, sign, Iball;//object
PImage box_pas, btn_res, btn_sett, btn_exit, tit_pas;//paused
PImage box_set, btn_change, div, ok, scr_vol0, scr_vol1, scr_vol2, tit_set;//setting
PImage[] bee = new PImage[5], Iwasp = new PImage[5], Ifrog = new PImage[6], Isnow = new PImage[11];//sprite
PImage box_shp, tit_shp, add_on, add_off, prog0, prog1;//upgrade
PImage box_alma, tit_alma;//alamanac
PImage[] latar = new PImage[3], price = new PImage[4], flower = new PImage[3], badge = new PImage[3], icon = new PImage[4], turto = new PImage[12], i_flower = new PImage[3];

class Image{
  PImage area, sprite, sprite2, icons, conv, popup, lev_com, obj, pause, setting, upgrade, turtorial, almanac;

  Image(){
    loadAllImage();
    initImage();
    initSize();
  }
  
  public void initImage(){
    //area level
    box_pick = area.get(1016, 83, 824, 585);
    latar[0] = area.get(1899, 102, 509, 352);
    latar[1] = area.get(1899, 487, 509, 352);
    latar[2] = area.get(1899, 867, 509, 352);
    tit_area = area.get(1084, 765, 405, 110);
    tit_lev = area.get(1084, 905, 405, 110);
    btn_ext = area.get(1525, 905, 100, 110);
    btn_next = area.get(1676, 923, 75, 82); 
    btn_prev = area.get(1676, 783, 75, 82);
    star0 = area.get(1096, 1042, 129, 48);
    star1 = area.get(1097, 1109, 127, 46);
    level_on = area.get(1249, 1037, 109, 111);
    level_off = area.get(1382, 1037, 109, 111); 
    btn_back = area.get(1525, 1043, 100, 109);
    
    //converter
    box_con = conv.get(1013, 83, 825, 585);
    price[0] = conv.get(1892, 237, 166, 52);
    price[1] = conv.get(1892, 313, 166, 52);
    price[2] =  conv.get(2079, 237, 166, 52);
    price[3] = conv.get(2079, 313, 166, 52);
    time = conv.get(2270, 243, 36, 38);
    dimn = conv.get(2321, 248, 39, 33);
    tit_con = conv.get(1892, 80, 405, 110);
    
    //icons
    xbar0 = icons.get(50, 660, 208, 52);
    xbar1 = icons.get(50, 735, 363, 59);
    healt = icons.get(60, 815, 342, 35);
    i_heart = icons.get(719, 763, 105, 90);
    i_coin = icons.get(476, 767, 91, 87);
    i_flower[0] = icons.get(850, 760, 109, 97);
    i_flower[1] = icons.get(1374, 526, 109, 97);
    i_flower[2] = icons.get(1649, 526, 109, 97);
    i_dimn = icons.get(589, 766, 103, 87);
    arr_flow = icons.get(1046, 755, 90, 102);
    arr_hiv = icons.get(1174, 755, 89, 102);
    boost = icons.get(1074, 118, 143, 152);
    boostC = icons.get(1074, 290, 143, 152);
    flower[0] = icons.get(1099, 519, 104, 103);
    flower[1] = icons.get(1239, 519, 104, 103);
    flower[2] = icons.get(1516, 519, 104, 103);
    badge[0] = icons.get(477, 505, 135, 135);
    badge[1] = icons.get(654, 505, 145, 135);
    badge[2] = icons.get(838, 505, 165, 135);
    btn_quit = icons.get(53, 288, 91, 99);
    btn_set = icons.get(54, 123, 91, 99);
    btn_tip = icons.get(220, 288, 91, 99);
    btn_pause = icons.get(554, 288, 91, 99);
    xbar = icons.get(50, 535, 307, 60);
    
    //popup
    box_pop = popup.get(19, 0, 712, 336);
    box_tur = popup.get(0, 379, 975, 627);
    btn_tidak = popup.get(764, 17, 201, 64);
    btn_ya = popup.get(764, 100, 201, 64);
    
    //level complete
    box_sol = lev_com.get(770, 115, 564, 538);
    tit_win = lev_com.get(1412, 90, 372, 102);
    tit_los = lev_com.get(1414, 221, 372, 102);
    star00 = lev_com.get(1409, 345, 281, 95);
    star01 = lev_com.get(1412, 452, 275, 90);
    i_c1 = lev_com.get(1718, 440, 49, 43);
    i_h1 = lev_com.get(1721, 498, 46, 44);
    btn_con = lev_com.get(1670, 566, 106, 116);
    btn_list = lev_com.get(1412, 566, 106, 116);
    btn_repl = lev_com.get(1541, 566, 106, 116);
    
    //login
    barLine = login.get(1827, 599, 316, 12);
    board = login.get(1832, 237, 405, 109);
    box = login.get(980, 222, 743, 588);
    btn_close = login.get(2239, 355, 100, 109);
    btn_create = login.get(1832, 383, 237, 57);
    btn_delete = login.get(2166, 387, 49, 51);
    btn_play = login.get(1832, 483, 521, 68);
    btn_update = login.get(2094, 387, 49, 51);
    l1 = login.get(2173, 575, 7, 59);
    remember = login.get(1829, 694, 209, 51);
    upbox = login.get(1089, 222, 526, 100);
    lowbox = login.get(1089, 595, 526, 215);
    
    //object
    hive = obj.get(210, 2, 140, 152);
    shop = obj.get(6, 2, 180, 191);
    sign = obj.get(9, 217, 203, 122);
    
    //pausing
    box_pas = pause.get(1026, 160, 576, 520);
    btn_res = pause.get(1745, 333, 473, 77);
    btn_sett = pause.get(1745, 450, 473, 77);
    btn_exit = pause.get(1745, 563, 473, 77);
    tit_pas = pause.get(1772, 173, 405, 110);
    
    //setting
    box_set = setting.get(979, 126, 746, 585);
    btn_change = setting.get(2167, 292, 51, 53);
    div = setting.get(2062, 284, 18, 69);
    ok = setting.get(1831, 286, 203, 64);
    scr_vol0 = setting.get(1836, 484, 516, 24);
    scr_vol1 = setting.get(1837, 400, 514, 22);
    scr_vol2 = setting.get(2363, 393, 27, 37);
    tit_set = setting.get(1831, 139, 405, 110);
    
    //sprite
    bee[0] = sprite.get(177, 116, 269, 288);
    bee[1] = sprite.get(551, 116, 266, 288);
    bee[2] = sprite.get(920, 116, 270, 288);
    bee[3] = sprite.get(1286, 116, 277, 288);
    bee[4] = sprite.get(1634, 116, 291, 288);
    Ifrog[0] = sprite.get(201, 901, 181, 252);
    Ifrog[1] = sprite.get(205, 1238, 181, 289);
    Ifrog[2] = sprite.get(211, 1627, 181, 279);
    Ifrog[3] = sprite.get(572, 910, 181, 248);
    Ifrog[4] = sprite.get(523, 1255, 228, 248);
    Ifrog[5] = sprite.get(538, 1628, 230, 248);
    Iball = sprite2.get(6125, 392, 197, 162);
    Isnow[0] = sprite2.get(185, 54, 464, 677);
    Isnow[1] = sprite2.get(733, 54, 397, 677);
    Isnow[2] = sprite2.get(1277, 54, 423, 677);
    Isnow[3] = sprite2.get(1801, 54, 349, 677);
    Isnow[4] = sprite2.get(2344, 54, 339, 677);
    Isnow[5] = sprite2.get(2712, 54, 465, 677);
    Isnow[6] = sprite2.get(3412, 54, 319, 677);
    Isnow[7] = sprite2.get(3942, 54, 383, 677);
    Isnow[8] = sprite2.get(4469, 54, 520, 677);
    Isnow[9] = sprite2.get(4996, 54, 516, 677);
    Isnow[10] = sprite2.get(5521, 54, 468, 677);
    Iwasp[0] = sprite.get(109, 469, 326, 296);
    Iwasp[1] = sprite.get(476, 469, 326, 296);
    Iwasp[2] = sprite.get(843, 469, 326, 296);
    Iwasp[3] = sprite.get(1207, 469, 326, 296);
    Iwasp[4] = sprite.get(1578, 469, 326, 296);
    
    //upgrade
    box_shp = upgrade.get(1013, 83, 825, 585);
    tit_shp = upgrade.get(1871, 80, 405, 110); 
    icon[0] = upgrade.get(1884, 231, 40, 35); 
    icon[2] = upgrade.get(1955, 232, 39, 38); 
    icon[3] = upgrade.get(1953, 292, 39, 34); 
    icon[1] = upgrade.get(1885, 287, 39, 41); 
    add_on = upgrade.get(2029, 231, 33, 35); 
    add_off = upgrade.get(2029, 290, 33, 35); 
    prog0 = upgrade.get(1872, 360, 328, 21); 
    prog1 = upgrade.get(1872, 400, 328, 21);
    
    //turtorial
    for(int i=0;i<12;i++){
      turto[i] = turtorial.get(0, 260*i, turtorial.width, 260);
    }
    
    //almanac
    box_alma = almanac.get(1082, 60, 764, 593);
    tit_alma = almanac.get(1911, 11, 357, 97);
  }
  
  public void loadAllImage(){
    area = loadImage("data/area_level_screen.png");
    sprite = loadImage("data/sprite.png");
    sprite2 = loadImage("data/sprite2.png");
    icons = loadImage("data/icons.png");
    conv = loadImage("data/converter_screen.png");
    popup = loadImage("data/jendela_popup.png");
    lev_com = loadImage("data/level_complete.png");
    login = loadImage("data/login_assets.png");
    obj = loadImage("data/menu_object.png");
    pause = loadImage("data/paused_screen.png");
    setting = loadImage("data/setting_screen.png");
    upgrade = loadImage("data/upgrade_screen.png");
    turtorial = loadImage("data/turtorial.png");
    almanac = loadImage("data/jenis_musuh_screen.png");
    
    open[0] = loadImage("data/Gemastik13.png");
    open[1] = loadImage("data/lazuardi.png");
    open[2] = loadImage("data/opening.png");
    bgHive = loadImage("data/beehive.png");
    beehive = loadImage("data/hive.png");
    home = loadImage("data/menu_utama.png");
    
    loadBG();
  }
  
  public void loadBG(){
    for(int i=0;i<5;i++){
      bg[0][i] = loadImage("data/a_"+(i+1)+".png");
      bg[1][i] = loadImage("data/b_"+(i+1)+".png");
      bg[2][i] = loadImage("data/c_"+(i+1)+".png");
    }
  }
  
  public void resizeImg(PImage tmp){
    tmp.resize(tmp.width/2, tmp.height/2);
  }
 
  public void initSize(){
    resizeImg(bgHive);
    resizeImg(beehive);
    resizeImg(home);
    for(int i=0;i<5;i++){
      resizeImg(bg[0][i]);
      resizeImg(bg[1][i]);
      resizeImg(bg[2][i]);
    }
    resizeImg(box_pick);
    resizeImg(tit_area);
    resizeImg(tit_lev);
    resizeImg(btn_ext);
    resizeImg(btn_next);
    resizeImg(btn_prev);
    resizeImg(star0);
    resizeImg(star1);
    resizeImg(level_on);
    resizeImg(level_off);
    resizeImg(btn_back);
    resizeImg(box_con);
    resizeImg(time);
    resizeImg(dimn);
    resizeImg(tit_con);
    resizeImg(xbar0);
    resizeImg(xbar1);
    resizeImg(healt);
    resizeImg(i_heart);
    resizeImg(i_coin);
    resizeImg(i_dimn);
    resizeImg(arr_flow);
    resizeImg(arr_hiv);
    resizeImg(boost);
    resizeImg(boostC);
    for(int i=0;i<3;i++){
      resizeImg(flower[i]);
      resizeImg(i_flower[i]);
      resizeImg(badge[i]);
      resizeImg(open[i]);
      resizeImg(latar[i]);
    }
    resizeImg(open[0]);
    resizeImg(btn_quit);
    resizeImg(btn_set);
    resizeImg(btn_tip);
    resizeImg(btn_pause);
    resizeImg(xbar);
    resizeImg(box_tur);
    resizeImg(box_pop);
    resizeImg(btn_ya);
    resizeImg(btn_tidak);
    resizeImg(box_sol);
    resizeImg(tit_win);
    resizeImg(tit_los);
    resizeImg(star00);
    resizeImg(star01); 
    resizeImg(i_c1);
    resizeImg(i_h1);
    resizeImg(btn_con);
    resizeImg(btn_list);
    resizeImg(btn_repl);
    resizeImg(barLine);
    resizeImg(board);
    resizeImg(box);
    resizeImg(btn_close);
    resizeImg(btn_create);
    resizeImg(btn_delete);
    resizeImg(btn_play);
    resizeImg(btn_update);
    resizeImg(l1);
    resizeImg(remember);
    resizeImg(upbox);
    resizeImg(lowbox);
    resizeImg(hive);
    resizeImg(shop);
    resizeImg(sign);
    resizeImg(box_pas);
    resizeImg(btn_res);
    resizeImg(btn_sett);
    resizeImg(btn_exit);
    resizeImg(tit_pas);
    resizeImg(box_set);
    resizeImg(btn_change); 
    resizeImg(div);
    resizeImg(ok);
    resizeImg(scr_vol0);
    resizeImg(scr_vol1);
    resizeImg(scr_vol2);
    resizeImg(tit_set);
    for(int i=0;i<5;i++){
      resizeImg(bee[i]);
      resizeImg(Iwasp[i]);
      resizeImg(bee[i]);
      resizeImg(Iwasp[i]);
    }
    for(int i=0;i<11;i++)resizeImg(Isnow[i]);
    for(int i=0;i<6;i++)resizeImg(Ifrog[i]);
    for(int i=0;i<4;i++){
      resizeImg(price[i]);
      resizeImg(icon[i]);
    }
    resizeImg(Iball);
    resizeImg(box_shp);
    resizeImg(tit_shp);
    resizeImg(add_on);
    resizeImg(add_off);
    resizeImg(prog0); 
    resizeImg(prog1);
    for(int i=0;i<12;i++){
      resizeImg(turto[i]);
    }
    resizeImg(box_alma);
    resizeImg(tit_alma);
  }
}
int sl = -1; //selected level
int sm = -1; //selected map

class Journey{
  static final int page = 2;
  boolean level_pick = false;
  
  int k = 0;
  String[] areas = {"Lembah", "Hutan", "Dataran Tinggi", ""};
  public void sketch(){
    fill(0, 100);
    rect(0, 0, width, height);
    image(box_pick, width/2, height/2);
    image(btn_tip, 190, 50);  
    image(btn_ext, 570, 50);
    if(level_pick){
      image(tit_lev, width/2, 40);
      image(btn_back, 190, 50);  
      for(int i=0;i<7;i++){
        float xx = 252.5f;
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
  public void click_on(){
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
          float xx = 252.5f;
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
PVector click = new PVector(mouseX, mouseY);

public void mousePressed(){
  switch(qq==-1?pp:qq){
    case 0:break;
    case 1:break;
    case 2:break;
    case 3:break;
    case 4:break;
    case 5:break;
    case 6:break;
    case 7:_7.pressm_on();break;
    default:break;
  }
  click.x = mouseX;
  click.y = mouseY;
}

public void mouseReleased(){
  int to = qq==-1?pp:qq; 
  switch(to){
    case 0:break;
    case 1:_1.releas_on();break;
    case 2:break;
    case 3:break;
    case 4:break;
    case 5:break;
    case 6:break;
    case 7:_7.releas_on();break;
    default:break;
  }
  if(!(click.x==mouseX&&click.y==mouseY))return;
  switch(to){
    case 0:_0.click_on();break;
    case 1:_1.click_on();break;
    case 2:_2.click_on();break;
    case 3:_3.click_on();break;
    case 4:_4.click_on();break;
    case 5:break;
    case 6:_6.click_on();break;
    case 7:_7.click_on();break;
    case 8:_8.click_on();break;
    default:break;
  }
}

public void mouseDragged(){
  switch(qq==-1?pp:qq){
    case 0:break;
    case 1:_1.drag_on();break;
    case 2:break;
    case 3:_3.drag_on();break;
    case 4:break;
    case 5:break;
    case 6:break;
    case 7:_7.drag_on();break;
    default:break;
  }
}

public void keyPressed(){
  switch(qq==-1?pp:qq){
    case 0:break;
    case 1:break;
    case 2:break;
    case 3:_3.press_on();break;
    case 4:break;
    case 5:break;
    case 6:break;
    case 7:_7.press_on();break;
    default:break;
  }
  if(keyCode == UP)User.setInt("koin", User.getInt("koin")+100);
  if(keyCode == DOWN)User.setInt("berlian", User.getInt("berlian")+10);
}

public void mouseClicked(){
  println(mouseX+" "+mouseY);
}


Table data;
PImage mapI, homeI;
PFont font;
SoundFile[] sound = new SoundFile[4];
SoundFile[] effect = new SoundFile[12];

public void loadData(){
  font = createFont("Cubano.ttf", 20);
  data = loadTable("data/basisdata.csv", "header");
  textFont(font); 
  loadSounds();
}

public void loadSounds(){
  for(int i=0;i<4;i++)
    sound[i] = new SoundFile(this, "bgm"+(i+1)+".wav");
  effect[0] = new SoundFile(this, "sfx_area1.wav");
  effect[1] = new SoundFile(this, "sfx_area2.wav");
  effect[2] = new SoundFile(this, "sfx_area3.wav");
  effect[3] = new SoundFile(this, "sfx_boost.wav");
  effect[4] = new SoundFile(this, "sfx_click2.wav");
  effect[5] = new SoundFile(this, "sfx_collect.wav");
  effect[6] = new SoundFile(this, "sfx_home.wav");
  effect[7] = new SoundFile(this, "sfx_lose.wav");
  effect[8] = new SoundFile(this, "sfx_refuse.wav");
  effect[9] = new SoundFile(this, "sfx_refuse2.wav");
  effect[10] = new SoundFile(this, "sfx_win.wav");
  effect[11] = new SoundFile(this, "sfx_wlood.wav");
}

public boolean createUser(String name){
  if(data.findRow(name, "nama")==null){
    TableRow nRow = data.addRow();
    nRow.setString("nama", name);
    nRow.setInt("badge", 0);
    nRow.setInt("koin", 0);
    nRow.setInt("berlian", 0);
    nRow.setInt("wtm", 0);
    for(int i=0;i<3;i++){
      for(int j=0;j<7;j++){
        char o = (char)('A'+i);
        if(i==0&&j==0)nRow.setInt(o+str(j+1), 0);
        else nRow.setInt(o+str(j+1), 0);
      }
    }
    nRow.setInt("Kwaktu", 0);
    nRow.setInt("Kberlian", 0);
    nRow.setInt("Uboost", 0);
    nRow.setInt("Ukoin", 0);
    nRow.setInt("Uberlian", 0);
    nRow.setInt("Uhealth", 0);
    saveTables();
    return true;
  }
  return false;
}

public void saveTables(){
  saveTable(data, "data/basisdata.csv");
}
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
  
  public void sketch(){
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
      stroke(0xffD67C23);
      float stLine = height/2-156*res;
      int lnDat = data.getRowCount();
      float lineLn = lnDat>2?stLine+588*res/lnDat:stLine;
      float yMove = map(scroll.y, 0, lnDat*90*res, 0, height/2-156*res);
      line(width/2+280*res, stLine-yMove, width/2+280*res, lineLn-yMove);
      scStat-=0.2f;
      noStroke();
    }
    if(input)textField();
  }
  
  public void button(){
    image(btn_create, width/2-btn_play.width/2+btn_create.width/2, height/2+130*res, btn_create.width, btn_create.height);
    if(select>-1)image(btn_play, width/2, height/2+210*res ,btn_play.width, btn_play.height);
    else clicked(btn_play.get(), width/2, height/2+210*res ,btn_play.width, btn_play.height, GRAY);
    image(btn_close, width/2+box.width/2-40*res, height/2-box.height/2+40*res, btn_close.width, btn_close.height);
  }
 
  public void keep(){
    remember = login.get(1829+255*isKeep, 694, 209, 51);
    remember.resize(parseInt(remember.width*res), parseInt((remember.height*res)));
  }
  
  public void click_on(){
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
        if(overRect(x, y, midW+262.5f*res-btn_delete.width/2, posY, btn_delete.width, btn_delete.height)){
          clicked(btn_delete.get(), midW+262.5f*res-btn_delete.width/2, posY, btn_delete.width, btn_delete.height, INVERT);      
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
  
  public void press_on(){
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
  
  public void drag_on(){
    float upY = height/2-box.height/2+upbox.height;
    float doY = height/2+box.height/2-lowbox.height;
    if(input)return;
    int lnDat = data.getRowCount();
    if(mouseY<direction&&doY<upY+scroll.y+90*res*lnDat)scroll.y-=4*res;
    else if(mouseY>direction&&upY>upY+scroll.y)scroll.y+=4*res;
    direction = mouseY;
    scStat = 10;
  }
  
  public void addBar(float midW, float midH){
    float leftX = midW-upbox.width/2;
    float upY = midH-box.height/2+upbox.height+45*res;
    textSize(40*res);
    int lnDat = data.getRowCount();
    for(int i=0;i<lnDat;i++){
      TableRow tmp = data.getRow(i);
      fill((select>-1&&select==i)?0xffFFCD8A:0xffFFEFD9);
      float y = upY + scroll.y + i*90*res;
      if(y>3*midH/2||y<midH/3)continue;
      int bb = data.getRow(i).getInt("badge");
      int col = bb<22?0:(bb<43?1:2);
      PImage Itmp = badge[col].get();
      Itmp.resize(Itmp.width/2, Itmp.height/2);
      image(Itmp, leftX+Itmp.width/2+2*(2-col), y, Itmp.width, Itmp.height);
      image(barLine, midW-30*res, y+30*res, barLine.width, barLine.height);
      image(btn_update, midW+220*res-btn_update.width, y, btn_update.width, btn_update.height);
      image(btn_delete, midW+262.5f*res-btn_delete.width/2, y, btn_delete.width, btn_delete.height);      
      text(tmp.getString("nama"), midW-barLine.width/2-20*res, y+10*res);
    }
  }
}
class Menu{
  static final int page = 4;
 
  public void sketch(){
    textAlign(LEFT);
    textSize(16);
    image(home, width/2, height/2);
    image(shop, 270, 112);
    image(sign, 413, 234);
    image(hive, 589, 112);
    countDown();
    GUI();
  }
  
  public void countDown(){
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
  
  public int countDate(Dates o){
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
  
  public void GUI(){
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
  
  public void treasure(){
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
  
  public String co(int c){
    if(c/10>0)return str(c);
    return "0"+c;
  }
  
  public void click_on(){
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
class Opening{
  static final int page = 5;
  int i=0, time = 200;
  
  public void sketch(){
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
int sp = -1, qqTmp = -1, iTmp = -1;

class Popup{
  static final int page = 6;
  int timeR = 0, timeD = 0, k=0;
  String[] desc = {
    "Hati-hati dengan makhluk yang satu ini!. Manusia \nSalju. Dia dapat melemparkan bola salju ke arah mu. \nSegera hindari bola salju dan terbang dengan \ncepat setelah dia melempar.",
    "Para tawon sangat sensitif. Jangan terbang \ndi sekitarnya atau kamu akan disengat!. Mereka tidak \nakan mati setelah menyengat musuhnya.",
    "Katak lembah sangatlah menggangu. Hati-hati \ndengan juluran lidahnya. Terbang menjauh akan \nlebih baik. Kamu dapat terbang di atasnya ketika \nia melompat.",
    "Kamu dapat menggerakan Apis dengan menekan layar \ndimanapun. Kemudian geser ke segala penjuru, maka \nApis akan mengikuti pergerakan jarimu.",
    "Carilah bunga, kemudian geser Apis ke bunga \ntersebut!. Tunggu beberapa saat hingga kamu \nmendapatkan sari bunga.",
    "Bawalah sari bunga ke sarang!. Tunggu beberapa \nsaat hingga kamu mendapatkan persediaan madu.",
    "Ikuti arah tanda ini untuk menemukan bunga!",
    "Ikuti arah tanda ini untuk membawamu ke sarang \nlebah madu!",
    "Tekan tombol ini untuk mempercepat terbang Apis \nselama beberapa saat",
    "Pastikan life point mu di atas angka 0 untuk \nmemenangkan permainan.",
    "Setiap bonus koin yang kamu dapatkan akan \ntercatat di sini.",
    "Carilah bunga dan bawa ke sarang dengan jumlah \nsesuai dengan angka yang tertuls di kolom ini!"
  };
  String[] alma1 = {"Lebah Madu", "Katak Lembah", "Tawon", "Manusia Salju"};
  String[] alma2 = {
    "Species : Apis mellifera\nGenus   : Apis\nFamily  : Apidae",
    "Species : Lithobates fisheri\nGenus   : Lithobates\nFamily  : Ranidae", 
    "Species : Vespula vulgaris\nGenus   : Vespula\nFamily  : Vespidae", 
    "Species : -\nGenus   : -\nFamily  :-"
  };
  String[] alma3 = {
    "Tubuh lebah madu terbagi menjadi 3 bagian yaitu \nkepala, thorax, dan abdomen. Lebah madu memiliki 6 \nkaki, sepasang sayap depan dan belakang, 2 mata \nmajemuk, lidah untuk menghisap sari bunga, dan antup. \nLebah madu akan mati setelah menyengat musuhnya.",
    "Kamu akan bertemu katak lembah di area ini. \nKatak lembah memiliki warna hijau dengan dua kaki \ndan mata. Lidahnya dapat menjulur panjang \nsebesar 2 kali tubuhnya. ",
    "Tubuh tawon terbagi menjadi 3 bagian yaitu \nkepala, thorax, dan abdomen. Tawon memiliki 6 kaki, \nsepasang sayap depan dan belakang, 2 mata majemuk, \ndan antup. Ketika menyengat musuhnya tawon \ntidak akan mati.",
    "Manusia Salju adalah makhluk asli di dataran ini. \nKeberadaannya sangat misterius. Tidak banyak \ninformasi tentang dirinya."
  };
  
  public void sketch(){
    fill(0, 100);
    rect(0, 0, width, height);
    switch(sp){
      case 0:turtorial();break;//turtorial
      case 1:confirm("Apakah anda ingin \nkeluar dari permainan?", 20);break;//exit
      case 2:confirm("Apakah anda ingin \nmenghapus akun ini?", 20);break;//hapus akun
      case 3:
        String str = "Apakah anda ingin \nmembeli ";
        if(qqTmp==0){
          str+= _0.paket[iTmp]+"\ndengan harga ";
          str+= str(_0.pric[iTmp]) + " koin?";
        }else{
          str+= _8.paket[iTmp]+"\ndengan harga ";
          str+= str(_8.price[iTmp]) + " berlian?";
        }
        confirm(str, 30);
        break;//pembayaran
      case 4: 
        String st = "Apakah anda ingin \nmempercepat dengan \nmengurangi ";
        timeR = User.getInt("Kwaktu") - timeD;
        st+= timeR + " koin anda?";
        confirm(st, 30);
        break; //percepat
      case 5:almanacs();break;//almanac
      case 6:credit();break;
    }
  }
  
  public void credit(){
    image(box_pick, width/2, height/2);
    image(btn_ext, 570, 50);
    fill(255);
    text("Credit", width/2, 80);
    String des = "Permainan ini dibuat oleh Tim Lazuardi\n- Ahmad Sucipto (Programmer)\n- Umar Sani (Graphics Desainer)\n- Candra Ilyasa (Graphics Desainer)\n\nTerimakasih kepada:\n- Allah SWT\n- Tree vector designed by 0melapics / Freepik\n- Set of Different Tree designed by brgfx / Freepik\n- Zoo Park Without Animal with Blank Sky Scene designed \nby brgfx / Freepik\n- Free Cartoon Forest Game Backgrounds designed by \nCraftpix\n- Sound effects obtained from https://www.zapsplat.com";
    textAlign(LEFT);
    textSize(12);
    text(des, 200, 100);
  }
  
  public void turtorial(){
    image(box_tur, width/2, height/2, box_tur.width, box_tur.height);
    image(btn_ext, 600, 40);
    if(k==0)image(turto[2-sm], width/2, height/2-50, turto[2-sm].width, turto[2-sm].height);
    else image(turto[2+k], width/2, height/2-50, turto[2+k].width, turto[2+k].height);
    image(btn_next, width/2+200, height/2, btn_next.width, btn_next.height);
    image(btn_prev, width/2-200, height/2, btn_prev.width, btn_prev.height);
    textAlign(LEFT);
    fill(255);
    textSize(14);
    if(k==0)text(desc[2-sm], 200, height/2+69);
    else text(desc[2+k], 200, height/2+69);
  }
  
  public void confirm(String s, int j){
    fill(255);
    image(box_pop, width/2, height/2, box_pop.width, box_pop.height);
    image(btn_ya, width/2+70, height/2+52, btn_ya.width, btn_ya.height);
    image(btn_tidak, width/2-70, height/2+52, btn_tidak.width, btn_tidak.height);
    text(s, width/2, height/2-j);
  }
  
  public void almanacs(){
    image(box_alma, width/2, height/2, box_alma.width, box_alma.height);
    image(tit_alma, width/2-1, 74, tit_alma.width, tit_alma.height);
    image(btn_ext, 529, 87);
    fill(255);
    textAlign(LEFT);
    textSize(16);
    text(alma1[sm+1], 340, 137);
    textSize(14);
    text(alma2[sm+1], 340, 156);
    textSize(10);
    text(alma3[sm+1], 245, 232);
    PImage tmp;
    switch(sm){
      case 0: tmp = Ifrog[0].get();img.resizeImg(tmp);break;
      case 1: tmp = Iwasp[0].get();break;
      case 2: tmp = Isnow[0].get();img.resizeImg(tmp);img.resizeImg(tmp);break;
      default: tmp = bee[0].get();break;
    }
    image(tmp, 280, 155);
  }
  
  public void click_on(){
    float x = mouseX;
    float y = mouseY;
    switch(sp){
      case 0:
        if(overRect(x, y, width/2+200, height/2, btn_next.width, btn_next.height)){sfx(4);
          k=(k+1)%10;
        }else if(overRect(x, y, width/2-200, height/2, btn_prev.width, btn_prev.height)){sfx(4);
          k--;
          if(k<0)k=9;
        }else if(overRect(x, y, 600, 40, btn_ext.width, btn_ext.height)){
          _1.tur = false;
          qq = qqTmp;
        }
        break;
      case 1:
        if(overRect(x, y, width/2+70, height/2+52, btn_ya.width, btn_ya.height)){sfx(4);exit();}
        else if(overRect(x, y, width/2-70, height/2+52, btn_tidak.width, btn_tidak.height)){sfx(4);qq=qqTmp;}
        break;
      case 2:
        if(overRect(x, y, width/2+70, height/2+52, btn_ya.width, btn_ya.height)){sfx(4);
          data.removeRow(iTmp);
          saveTables();
          qq=qqTmp;
        }
        else if(overRect(x, y, width/2-70, height/2+52, btn_tidak.width, btn_tidak.height)){sfx(4);qq=qqTmp;}
        break;
      case 3:
        if(overRect(x, y, width/2+70, height/2+52, btn_ya.width, btn_ya.height)){sfx(4);
          if(qqTmp==0){
            User.setInt("koin", User.getInt("koin")-_0.pric[iTmp]);
            Dates hiveCD = new Dates(year(), month(), day(), hour()+_0.timeP[iTmp], minute(), second());
            User.setInt("Kwaktu", _4.countDate(hiveCD));
            User.setInt("Kberlian",_0. sellC[iTmp]+_0.sellC[iTmp]*User.getInt("Uberlian")/5);
            qq=qqTmp;
          }else{
            User.setInt("berlian", User.getInt("berlian")-_8.price[iTmp]);
            User.setInt(_8.dPack[iTmp], User.getInt(_8.dPack[iTmp])+1);
            qq=qqTmp;
          }
        }
        else if(overRect(x, y, width/2-70, height/2+52, btn_tidak.width, btn_tidak.height)){sfx(4);qq=qqTmp;}
        break;
      case 4:
        if(overRect(x, y, width/2+70, height/2+52, btn_ya.width, btn_ya.height)){
          if(User.getInt("koin")<timeR){sfx(8);break;}
          sfx(4);
          User.setInt("koin", User.getInt("koin")-timeR);
          User.setInt("Kwaktu", _4.countDate(new Dates(year(), month(), day(), hour(), minute(), second()+10)));
          qq = qqTmp;
        }
        else if(overRect(x, y, width/2-70, height/2+52, btn_tidak.width, btn_tidak.height)){sfx(4);qq=qqTmp;}
        break;
      case 5:
        if(overRect(x, y, 529, 87, btn_ext.width, btn_ext.height)){
          sfx(4);qq=qqTmp;
        }
        break;
      case 6:if(overRect(x, y, 570, 50, btn_ext.width, btn_ext.height)){
          sfx(4);qq=qqTmp;
        }
        break;
    }
  }
}
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
  
  public void update(int x, int y){
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
class Setting{
  static final int page = 7;
  
  Setting(){
    setB=setA=0;
  }
  
  public void sketch(){
    fill(0, 100);
    rect(0, 0, width, height);
    settingGame();
    if(input)textField();
  }
  
  float setA, setB;
  public void settingGame(){
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
  public void click_on(){
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
  public void drag_on(){
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
  
  public void pressm_on(){
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
  
  public void releas_on(){
    btnV_A = false;
    btnV_B = false;
  }
  
  boolean input = false;
  public void press_on(){
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
class Shop{
  static final int page = 8;
  
  String[] paket = {"Healt", "Boost", "Bonus Koin", "Bonus Diamond"};
  String[] dPack = {"Uhealth", "Uboost", "Ukoin", "Uberlian"};
  int [] price = {10, 10, 5, 5};
  
  public void sketch(){
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
      if(User.getInt(dPack[i])>=floor(3+bb*3.5f)) image(add_off, 547, 127+49*i);
      else image(add_on, 547, 127+49*i);
    }
    textSize(30);
    text(User.getString("nama"), 243, 130);
    image(bee[0], 280, 215);
  }
  
  public void click_on(){
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
        if(overRect(x, y, 547, 127+49*i, add_on.width, add_on.height)&&User.getInt(dPack[i])<floor(3+bb*3.5f)){
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
public void SoundSys(){
  switch(pp){
    case 1:
      if(sm!=-1){
        if(!sound[sm].isPlaying())sound[sm].play();
        sound[3].stop();
        if(!effect[sm].isPlaying()){
          if(sm==1){
            if(frameCount%360==0)effect[sm].play();
          }else effect[sm].play();
        }
        effect[6].stop();
      }
      break;
    case 4:
      if(!sound[3].isPlaying())sound[3].play();
      if(!effect[6].isPlaying())effect[6].play();
      if(sm!=-1){
        sound[sm].stop();
        effect[sm].stop();
      }break;
  }
}

public void sfx(int x){
  switch(x){
    case 0:effect[0].play();break;
    case 1:effect[1].play();break;
    case 2:effect[2].play();break;
    case 3:effect[3].play();break;
    case 4:effect[4].play();break;
    case 5:effect[5].play();break;
    case 6:effect[6].play();break;
    case 7:effect[7].play();break;
    case 8:effect[8].play();break;
    case 9:effect[9].play();break;
    case 10:effect[10].play();break;
    case 11:effect[11].play();break;
  }
}
  public void settings() {  size(760, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Apis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
