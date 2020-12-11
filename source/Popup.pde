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
  
  void sketch(){
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
  
  void credit(){
    image(box_pick, width/2, height/2);
    image(btn_ext, 570, 50);
    fill(255);
    text("Credit", width/2, 80);
    String des = "Permainan ini dibuat oleh Tim Lazuardi\n- Ahmad Sucipto (Programmer)\n- Umar Sani (Graphics Desainer)\n- Candra Ilyasa (Graphics Desainer)\n\nTerimakasih kepada:\n- Allah SWT\n- Tree vector designed by 0melapics / Freepik\n- Set of Different Tree designed by brgfx / Freepik\n- Zoo Park Without Animal with Blank Sky Scene designed \nby brgfx / Freepik\n- Free Cartoon Forest Game Backgrounds designed by \nCraftpix\n- Sound effects obtained from https://www.zapsplat.com";
    textAlign(LEFT);
    textSize(12);
    text(des, 200, 100);
  }
  
  void turtorial(){
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
  
  void confirm(String s, int j){
    fill(255);
    image(box_pop, width/2, height/2, box_pop.width, box_pop.height);
    image(btn_ya, width/2+70, height/2+52, btn_ya.width, btn_ya.height);
    image(btn_tidak, width/2-70, height/2+52, btn_tidak.width, btn_tidak.height);
    text(s, width/2, height/2-j);
  }
  
  void almanacs(){
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
  
  void click_on(){
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
