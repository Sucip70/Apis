import processing.sound.*;

Table data;
PImage mapI, homeI;
PFont font;
SoundFile[] sound = new SoundFile[4];
SoundFile[] effect = new SoundFile[12];

void loadData(){
  font = createFont("Cubano.ttf", 20);
  data = loadTable("data/basisdata.csv", "header");
  textFont(font); 
  loadSounds();
}

void loadSounds(){
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

boolean createUser(String name){
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

void saveTables(){
  saveTable(data, "data/basisdata.csv");
}
