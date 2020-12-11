Routes route = new Routes();
Table table;
String last = "";
float res = 0.5;
Image img;

void setup(){
  size(760, 360);
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
void draw(){
  textAlign(CENTER);
  route.update(pp, qq);
  SoundSys();
  fill(#31F0FF, 127);
  noStroke();
  ellipse(mouseX, mouseY, 30, 30);
}
