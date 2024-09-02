package com.twinkle.models.player;

import com.twinkle.models.shop.ShopServiceNew;
import com.twinkle.services.MapService;
import com.girlkun.consts.ConstMap;
import com.twinkle.models.map.Map;
import com.twinkle.models.map.Zone;
import com.twinkle.server.Manager;
import com.twinkle.services.MapService;
import com.twinkle.services.PlayerService;
import com.twinkle.services.Service;
import com.twinkle.utils.Util;
// đây
import java.util.ArrayList;
import java.util.List;

/**
 * @author BTH sieu cap vippr0
 */
public class Referee1 extends Player {

    private long lastTimeChat;
    private Player playerTarget;

    private long lastTimeTargetPlayer;
    private long timeTargetPlayer = 5000;
    private long lastZoneSwitchTime;
    private long zoneSwitchInterval;
    private List<Zone> availableZones;

    public void initReferee1() {
        init();
    }

    @Override
    public short getHead() {
        return 418;
    }

    @Override
    public short getBody() {
        return 419;
    }

    @Override
    public short getLeg() {
        return 420;
    }

    public void joinMap(Zone z, Player player) {
        MapService.gI().goToMap(player, z);
        z.load_Me_To_Another(player);
    }

    @Override
    public void update() {
        if (Util.canDoWithTime(lastTimeChat, 5000)) {
            Service.getInstance().chat(this, "NRO Twinkle Xin Chào !");
//            Service.getInstance().chat(this, "Danh Sách"
 //                                   + "\n|3|[ Top 1 ] kencuto"
 //                                   + "\n|2|[ Top 2 ] sucute123"                                   
  //                                  + "\n|4|[ Top 3 ] freak");
            Service.getInstance().chat(this, "NRO Twinkle !");
            lastTimeChat = System.currentTimeMillis();
        }
    }
// Nro Pico Xin Chào  Wellcom to Pico ADMIN 1
    private void init() {
        int id = -1000000;
        for (Map m : Manager.MAPS) {
            if (m.mapId == 0) {
                for (Zone z : m.zones) {
                    Referee1 pl = new Referee1();
                    pl.name = "ADMIN";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 714;
                    pl.location.y = 432;
                    joinMap(z, pl);
                    z.setReferee(pl);
                }
            } else if (m.mapId == 7) {                      
                    for (Zone z : m.zones) {
                    Referee1 pl = new Referee1();
                    pl.name = "ADMIN";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 761;
                    pl.location.y = 432;
                    joinMap(z, pl);
                    z.setReferee(pl);
                 }
              } else if (m.mapId == 14) {                      
                    for (Zone z : m.zones) {
                    Referee1 pl = new Referee1();
                    pl.name = "ADMIN";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 752;
                    pl.location.y = 408;
                    joinMap(z, pl);
                    z.setReferee(pl);
                 }
            }
        }
    }
}

