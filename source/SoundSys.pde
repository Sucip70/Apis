void SoundSys(){
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

void sfx(int x){
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
