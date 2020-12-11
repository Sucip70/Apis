boolean overObject(float d, float lx, float ly, float x, float y){
  float disX = lx - x;
  float disY = ly - y;
  if(sqrt(sq(disX)+sq(disY))<d/2)
    return true;
  return false;
}

boolean overRect(float x, float y, float x0, float y0, float w, float h){
  if(x0-w/2<x && x<x0+w/2 && y0-h/2<y && y<y0+h/2)return true;
  return false;
}

void clicked(PImage img, float x, float y, float w, float h, int mode){
  img.filter(mode);
  image(img, x, y, w, h);
}

String textInput = "";
void textField(){
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
