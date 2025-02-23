package com.twinkle.models.boss;

import com.twinkle.models.boss.list_boss.nappa.MapDauDinh;
import com.twinkle.models.boss.list_boss.nappa.Rambo;
import com.twinkle.models.boss.list_boss.nappa.Kuku;
import com.twinkle.models.boss.list_boss.android.Android19;
import com.twinkle.models.boss.list_boss.android.Android14;
import com.twinkle.models.boss.list_boss.android.Android13;
import com.twinkle.models.boss.list_boss.android.DrKore;
import com.twinkle.models.boss.list_boss.android.Android15;
import com.twinkle.models.boss.list_boss.android.Pic;
import com.twinkle.models.boss.list_boss.android.Poc;
import com.twinkle.models.boss.list_boss.android.KingKong;
import com.twinkle.models.boss.list_boss.BLACK.ZamasMax;
import com.twinkle.models.boss.list_boss.BLACK.SuperBlack2;
import com.twinkle.models.boss.list_boss.BLACK.Black;
import com.twinkle.models.boss.list_boss.BLACK.ZamasKaio;
import com.twinkle.models.boss.list_boss.BLACK.BlackGokuBase;
import com.twinkle.models.boss.list_boss.BLACK.BlackGokuTl;
import com.twinkle.models.boss.list_boss.Cooler.Cooler;
import com.girlkun.models.boss.list_boss.HuyDiet.Champa;
import com.girlkun.models.boss.list_boss.HuyDiet.ThanHuyDiet;
import com.girlkun.models.boss.list_boss.HuyDiet.ThienSuWhis;
import com.girlkun.models.boss.list_boss.HuyDiet.Vados;
import com.twinkle.models.boss.list_boss.NgucTu.CoolerGold;
import com.twinkle.models.boss.list_boss.Doraemon.Doraemon;
import com.twinkle.models.boss.list_boss.FideBack.Kingcold;
import com.twinkle.models.boss.list_boss.Mabu;
import com.twinkle.models.boss.list_boss.SuperXen;
import com.twinkle.models.boss.list_boss.NgucTu.Cumber;
import com.twinkle.models.boss.list_boss.cell.Xencon;
import com.twinkle.models.boss.list_boss.ginyu.TDST;
import com.twinkle.models.boss.list_boss.ginyunamec.so1;
import com.twinkle.models.boss.list_boss.ginyunamec.so2;
import com.twinkle.models.boss.list_boss.ginyunamec.so3;
import com.twinkle.models.boss.list_boss.ginyunamec.so4;
import com.twinkle.models.boss.list_boss.ginyunamec.tieudoitruong;
import com.twinkle.models.boss.list_boss.cell.SieuBoHung;
import com.twinkle.models.boss.list_boss.cell.XenBoHung;
import com.twinkle.models.boss.list_boss.Broly.Broly;
import com.twinkle.models.boss.list_boss.Doraemon.Nobita;
import com.twinkle.models.boss.list_boss.Doraemon.Xeko;
import com.twinkle.models.boss.list_boss.Doraemon.Xuka;
import com.twinkle.models.boss.list_boss.FideBack.FideRobot;
import com.twinkle.models.boss.list_boss.NgucTu.SongokuTaAc;
import com.twinkle.models.boss.list_boss.fide.Fide;
import com.twinkle.models.boss.list_boss.Doraemon.Chaien;
import com.twinkle.models.boss.list_boss.NRD.Rong1Sao;
import com.twinkle.models.boss.list_boss.NRD.Rong2Sao;
import com.twinkle.models.boss.list_boss.NRD.Rong3Sao;
import com.twinkle.models.boss.list_boss.NRD.Rong4Sao;
import com.twinkle.models.boss.list_boss.NRD.Rong5Sao;
import com.twinkle.models.boss.list_boss.NRD.Rong6Sao;
import com.twinkle.models.boss.list_boss.NRD.Rong7Sao;
import com.twinkle.models.boss.list_boss.Mabu12h.MabuBoss;
import com.twinkle.models.boss.list_boss.Mabu12h.BuiBui;
import com.twinkle.models.boss.list_boss.Mabu12h.BuiBui2;
import com.twinkle.models.boss.list_boss.Mabu12h.Drabura;
import com.twinkle.models.boss.list_boss.Mabu12h.Drabura2;
import com.twinkle.models.boss.list_boss.Mabu12h.Yacon;
import com.twinkle.models.boss.list_boss.kami.cumberBlack;
import com.twinkle.models.boss.list_boss.kami.cumberYellow;
import com.twinkle.models.boss.list_boss.kami.kamiLoc;
import com.twinkle.models.boss.list_boss.kami.kamiRin;
import com.twinkle.models.boss.list_boss.kami.kamiSooMe;
import com.twinkle.models.boss.list_boss.sontinhthuytinh.Sontinh;
import com.twinkle.models.boss.list_boss.sontinhthuytinh.Thuytinh;
import com.twinkle.models.boss.list_boss.concu.concu;
import com.twinkle.models.boss.list_boss.concu.concat;
import com.twinkle.models.boss.list_boss.bojack.kogu;
import com.twinkle.models.boss.list_boss.bojack.bojack;
import com.twinkle.models.boss.list_boss.bojack.zangya;
import com.twinkle.models.boss.list_boss.bojack.spbojack;
import com.twinkle.models.boss.list_boss.bojack.bido;
import com.twinkle.models.boss.list_boss.Broly.Broly;
import com.twinkle.models.boss.list_boss.Broly.SuperBroly;
import com.twinkle.models.boss.list_boss.x3x4.x3;
import com.twinkle.models.boss.list_boss.x3x4.x4;
 

import com.twinkle.models.player.Player;
import com.girlkun.network.io.Message;
import com.twinkle.models.boss.list_boss.Broly.SuperBroly;
import com.twinkle.server.ServerManager;
import com.twinkle.services.ItemMapService;
import com.twinkle.services.MapService;

import java.util.ArrayList;
import java.util.List;

public class BossManager implements Runnable {

    private static BossManager I;
    public static final byte ratioReward = 30;

    public static BossManager gI() {
        if (BossManager.I == null) {
            BossManager.I = new BossManager();
        }
        return BossManager.I;
    }

    private BossManager() {
        this.bosses = new ArrayList<>();
    }

    private boolean loadedBoss;
    private final List<Boss> bosses;

    public void addBoss(Boss boss) {
        this.bosses.add(boss);
    }
    
    public void removeBoss(Boss boss) {
        this.bosses.remove(boss);
    }
    public void loadBoss() {
        if (this.loadedBoss) {
            return;
        }
        try {
          // NEW
           this.createBoss(BossID.KAMIRIN);
           this.createBoss(BossID.KAMILOC);
           this.createBoss(BossID.KAMI_SOOME);
           this.createBoss(BossID.CUMBERBLACK);
           this.createBoss(BossID.CUMBERYELLOW);
            this.createBoss(BossID.SUPER_XEN);

            this.createBoss(BossID.TDST);           
            this.createBoss(BossID.BROLY);
            this.createBoss(BossID.BROLY);
            this.createBoss(BossID.BROLY_1);
            this.createBoss(BossID.BROLY_2);
            this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
             this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
             this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);
	    this.createBoss(BossID.BROLY);

      // NEW
           this.createBoss(BossID.S_BROLY);
           this.createBoss(BossID.S_BROLY);
           this.createBoss(BossID.S_BROLY);

             this.createBoss(BossID.SO_4_NM);
	    this.createBoss(BossID.SO_3_NM);
            this.createBoss(BossID.SO_2_NM);
            this.createBoss(BossID.SO_1_NM);
            this.createBoss(BossID.TIEU_DOI_TRUONG_NM);
			
            this.createBoss(BossID.PIC);
            this.createBoss(BossID.POC);
            this.createBoss(BossID.KING_KONG);
            this.createBoss(BossID.SONGOKU_TA_AC);
            this.createBoss(BossID.CUMBER);
            this.createBoss(BossID.COOLER_GOLD);
            this.createBoss(BossID.COOLER_GOLD);
            this.createBoss(BossID.COOLER_GOLD);
            this.createBoss(BossID.XEN_BO_HUNG);
            this.createBoss(BossID.SIEU_BO_HUNG);
            this.createBoss(BossID.XEN_CON_1);
            this.createBoss(BossID.XEN_CON_1);
            this.createBoss(BossID.XEN_CON_1);
            this.createBoss(BossID.XEN_CON_1);

            this.createBoss(BossID.THIEN_SU_VADOS);
            this.createBoss(BossID.THIEN_SU_WHIS);
            this.createBoss(BossID.THIEN_SU_VADOS);
            this.createBoss(BossID.THIEN_SU_WHIS);
            this.createBoss(BossID.THIEN_SU_VADOS);
            this.createBoss(BossID.THIEN_SU_WHIS);
            this.createBoss(BossID.THIEN_SU_VADOS);
            this.createBoss(BossID.THIEN_SU_WHIS);
            
            this.createBoss(BossID.COOLER);
            
            
             this.createBoss(BossID.DORAEMON);
             this.createBoss(BossID.NOBITA);
             this.createBoss(BossID.XUKA);
             this.createBoss(BossID.CHAIEN);
             this.createBoss(BossID.XEKO);           
            this.createBoss(BossID.D_TANG);
            this.createBoss(BossID.NKHONG);
            this.createBoss(BossID.HHAINHI);
            this.createBoss(BossID.TBACGIOI);
            this.createBoss(BossID.STANG);
            this.createBoss(BossID.BLACK);
            this.createBoss(BossID.ZAMASZIN);
            this.createBoss(BossID.BLACK2);
            this.createBoss(BossID.ZAMASMAX);
            this.createBoss(BossID.BLACK);
            this.createBoss(BossID.BLACK3);            
            this.createBoss(BossID.KUKU);
            this.createBoss(BossID.MAP_DAU_DINH);
            this.createBoss(BossID.RAMBO);

            this.createBoss(BossID.FIDE);

            this.createBoss(BossID.DR_KORE);
             this.createBoss(BossID.SON_TINH);
             this.createBoss(BossID.THUY_TINH);
           //  this.createBoss(BossID.CON_CU);
           //  this.createBoss(BossID.CON_CAC);
            this.createBoss(BossID.ANDROID_14);
            this.createBoss(BossID.ANDROID_13);
            this.createBoss(BossID.ANDROID_15);
             this.createBoss(BossID.SUPER_ANDROID_17); 
            this.createBoss(BossID.MABU);
            
           this.createBoss(BossID.X3);
           this.createBoss(BossID.X4);
 
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.loadedBoss = true;
        new Thread(BossManager.I, "Update boss").start();
    }

    public Boss createBoss(int bossID) {
        try {
            switch (bossID) {
                case BossID.S_BROLY:
                    return new SuperBroly();
                case BossID.CUMBERYELLOW:
                    return new cumberYellow();
                case BossID.CUMBERBLACK:
                    return new cumberBlack();
                case BossID.KAMIRIN:
                    return new kamiRin();
                case BossID.KAMILOC:
                    return new kamiLoc();
                case BossID.KAMI_SOOME:
                    return new kamiSooMe();
                case BossID.KUKU:
                    return new Kuku();
                case BossID.MAP_DAU_DINH:
                    return new MapDauDinh();
                case BossID.RAMBO:
                    return new Rambo();
                case BossID.DRABURA:
                    return new Drabura();
                case BossID.DRABURA_2:
                    return new Drabura2();
                case BossID.BUI_BUI:
                    return new BuiBui();
                case BossID.BUI_BUI_2:
                    return new BuiBui2();
                case BossID.YA_CON:
                    return new Yacon();
                case BossID.MABU_12H:
                    return new MabuBoss();
                case BossID.Rong_1Sao:
                    return new Rong1Sao();
                case BossID.Rong_2Sao:
                    return new Rong2Sao();
                case BossID.Rong_3Sao:
                    return new Rong3Sao();
                case BossID.Rong_4Sao:
                    return new Rong4Sao();
                case BossID.Rong_5Sao:
                    return new Rong5Sao();
                case BossID.Rong_6Sao:
                    return new Rong6Sao();
                case BossID.Rong_7Sao:
                    return new Rong7Sao();
                case BossID.FIDE:
                    return new Fide();
                case BossID.DR_KORE:
                    return new DrKore();
                case BossID.ANDROID_19:
                    return new Android19();
                case BossID.ANDROID_13:
                    return new Android13();
                case BossID.ANDROID_14:
                    return new Android14();
                case BossID.ANDROID_15:
                    return new Android15();
                case BossID.SUPER_XEN:
                    return new SuperXen();
                case BossID.CON_CU:
                    return new concu();
                case BossID.CON_CAC:
                    return new concat();    
                case BossID.PIC:
                    return new Pic();
                case BossID.POC:
                    return new Poc();
                case BossID.KING_KONG:
                    return new KingKong();
                case BossID.XEN_BO_HUNG:
                    return new XenBoHung();
                case BossID.SIEU_BO_HUNG:
                    return new SieuBoHung();
                case BossID.XUKA:
                    return new Xuka();
                case BossID.NOBITA:
                    return new Nobita();
                case BossID.XEKO:
                    return new Xeko();
                case BossID.CHAIEN:
                    return new Chaien();
                case BossID.DORAEMON:
                    return new Doraemon();
                case BossID.HHAINHI:
                    return new zangya();
                case BossID.NKHONG:
                    return new spbojack();
                case BossID.STANG:
                    return new bido();
                case BossID.TBACGIOI:
                    return new kogu();
                case BossID.D_TANG:
                    return new bojack();    
                case BossID.VUA_COLD:
                    return new Kingcold();
                case BossID.FIDE_ROBOT:
                    return new FideRobot();
                case BossID.COOLER:
                    return new Cooler();
                case BossID.ZAMASMAX:
                    return new ZamasMax();
                case BossID.ZAMASZIN:
                    return new ZamasKaio();
                case BossID.BLACK2:
                    return new SuperBlack2();
                case BossID.BLACK1:
                    return new BlackGokuTl();
                case BossID.BLACK:
                    return new Black();
                 case BossID.BLACK3:
                    return new BlackGokuBase();   
                case BossID.XEN_CON_1:
                    return new Xencon();
                case BossID.MABU:
                    return new Mabu();
                case BossID.TDST:
                    return new TDST();                  
                case BossID.COOLER_GOLD:
                    return new CoolerGold();
                case BossID.CUMBER:
                    return new Cumber();
                case BossID.THAN_HUY_DIET_CHAMPA:
                    return new Champa();
                case BossID.THIEN_SU_VADOS:
                    return new Vados();
                case BossID.THAN_HUY_DIET:
                    return new ThanHuyDiet();
                case BossID.THIEN_SU_WHIS:
                    return new ThienSuWhis();
                case BossID.SON_TINH:
                    return new Sontinh();
                case BossID.THUY_TINH:
                    return new Thuytinh();    
                case BossID.SONGOKU_TA_AC:
                    return new SongokuTaAc();
                case BossID.BROLY:
                    return new Broly();               
                case BossID.BROLY_1:
                    return new Broly();
                case BossID.BROLY_2:
                    return new Broly();
                case BossID.SO_4_NM:
                    return new so4();    
                case BossID.SO_3_NM:
                    return new so3();
                case BossID.SO_2_NM:
                    return new so2();
                case BossID.SO_1_NM:
                    return new so1();
                case BossID.TIEU_DOI_TRUONG_NM:
                    return new tieudoitruong();    
                case BossID.X3:
    return new x3();
               case BossID.X4:
    return new x4();
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean existBossOnPlayer(Player player) {
        return player.zone.getBosses().size() > 0;
    }

    public void showListBoss(Player player) {
        if (!player.isAdmin()) {
            return;
        }
        Message msg;
        try {
            msg = new Message(-96);
            msg.writer().writeByte(0);
            msg.writer().writeUTF("Boss");
            msg.writer().writeByte((int) bosses.stream().filter(boss -> !MapService.gI().isMapMaBu(boss.data[0].getMapJoin()[0]) && !MapService.gI().isMapBlackBallWar(boss.data[0].getMapJoin()[0])).count());
            for (int i = 0; i < bosses.size(); i++) {
                Boss boss = this.bosses.get(i);
                if (MapService.gI().isMapMaBu(boss.data[0].getMapJoin()[0]) || MapService.gI().isMapBlackBallWar(boss.data[0].getMapJoin()[0])) {
                    continue;
                }
                msg.writer().writeInt(i);
                msg.writer().writeInt(i);
                msg.writer().writeShort(boss.data[0].getOutfit()[0]);
                msg.writer().writeShort(boss.data[0].getOutfit()[1]);
                if(player.getSession().version > 214){
                    msg.writer().writeShort(-1);
                }
                msg.writer().writeShort(boss.data[0].getOutfit()[2]);
                msg.writer().writeUTF(boss.data[0].getName());
                if (boss.zone != null) {
                    msg.writer().writeUTF("Sống");
                    msg.writer().writeUTF(boss.zone.map.mapName + "(" + boss.zone.map.mapId + ") khu " + boss.zone.zoneId + "");
                } else {
                    msg.writer().writeUTF("Chết");
                    msg.writer().writeUTF("Chết rồi");
                }
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void callBoss(Player player, int mapId) {
        try {
            if (BossManager.gI().existBossOnPlayer(player) ||
                    player.zone.items.stream().anyMatch(itemMap -> ItemMapService.gI().isBlackBall(itemMap.itemTemplate.id)) ||
                    player.zone.getPlayers().stream().anyMatch(p -> p.iDMark.isHoldBlackBall())) {
                return;
            }
            Boss k = null;
            switch (mapId) {
                case 85:
                    k = BossManager.gI().createBoss(BossID.Rong_1Sao);
                    break;
                case 86:
                    k = BossManager.gI().createBoss(BossID.Rong_2Sao);
                    break;
                case 87:
                    k = BossManager.gI().createBoss(BossID.Rong_3Sao);
                    break;
                case 88:
                    k = BossManager.gI().createBoss(BossID.Rong_4Sao);
                    break;
                case 89:
                    k = BossManager.gI().createBoss(BossID.Rong_5Sao);
                    break;
                case 90:
                    k = BossManager.gI().createBoss(BossID.Rong_6Sao);
                    break;
                case 91:
                    k = BossManager.gI().createBoss(BossID.Rong_7Sao);
                    break;
            }
            if (k != null) {
                k.currentLevel = 0;
                k.joinMapByZone(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boss getBossById(int bossId) {
        return BossManager.gI().bosses.stream().filter(boss -> boss.id == bossId && !boss.isDie()).findFirst().orElse(null);
    }

    @Override
    public void run() {
        while (ServerManager.isRunning) {
            try {
                long st = System.currentTimeMillis();
                for (Boss boss : this.bosses) {
                    boss.update();
                }
                Thread.sleep(150 - (System.currentTimeMillis() - st));
            } catch (Exception ignored) {
            }

        }
    }}

   
