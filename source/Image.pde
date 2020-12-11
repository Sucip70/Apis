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
  
  void initImage(){
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
  
  void loadAllImage(){
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
  
  void loadBG(){
    for(int i=0;i<5;i++){
      bg[0][i] = loadImage("data/a_"+(i+1)+".png");
      bg[1][i] = loadImage("data/b_"+(i+1)+".png");
      bg[2][i] = loadImage("data/c_"+(i+1)+".png");
    }
  }
  
  void resizeImg(PImage tmp){
    tmp.resize(tmp.width/2, tmp.height/2);
  }
 
  void initSize(){
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
