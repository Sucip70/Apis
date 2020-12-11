PVector click = new PVector(mouseX, mouseY);

void mousePressed(){
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

void mouseReleased(){
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

void mouseDragged(){
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

void keyPressed(){
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

void mouseClicked(){
  println(mouseX+" "+mouseY);
}
