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
public class Referee extends Player {

    private long lastTimeChat;
    private Player playerTarget;

    private long lastTimeTargetPlayer;
    private long timeTargetPlayer = 5000;
    private long lastZoneSwitchTime;
    private long zoneSwitchInterval;
    private List<Zone> availableZones;

    public void initReferee() {
        init();
    }

    @Override
    public short getHead() {
        return 956;
    }

    @Override
    public short getBody() {
        return 957;
    }

    @Override
    public short getLeg() {
        return 958;
    }

    public void joinMap(Zone z, Player player) {
        MapService.gI().goToMap(player, z);
        z.load_Me_To_Another(player);
    }

    @Override
    public void update() {
        if (Util.canDoWithTime(lastTimeChat, 5000)) {
            Service.getInstance().chat(this, "Đại Hội Võ Thuật lần thứ 23 đã chính thức khai mạc");
            Service.getInstance().chat(this, "Còn chờ gì nữa mà không đăng kí tham gia để nhận nhiều phẩn quà hấp dẫn");
            lastTimeChat = System.currentTimeMillis();
        }
    }

    private void init() {
        int id = -1000000;
        for (Map m : Manager.MAPS) {
            if (m.mapId == 52) {
                for (Zone z : m.zones) {
                    Referee pl = new Referee();
                    pl.name = "Nhót";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 387;
                    pl.location.y = 336;
                    joinMap(z, pl);
                    z.setReferee(pl);
                }
            } else if (m.mapId == 129) {
                for (Zone z : m.zones) {
                    Referee pl = new Referee();
                    pl.name = "Goku";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 385;
                    pl.location.y = 264;
                    joinMap(z, pl);
                    z.setReferee(pl);
                 }         
            }else if (m.mapId == 14) {
                for (Zone z : m.zones) {
                    Referee pl = new Referee();
                    pl.name = "Kun";
                    pl.gender = 0;
                    pl.id = id++;
                    pl.nPoint.hpMax = 69;
                    pl.nPoint.hpg = 69;
                    pl.nPoint.hp = 69;
                    pl.nPoint.setFullHpMp();
                    pl.location.x = 1546;
                    pl.location.y = 408;
                    joinMap(z, pl);
                    z.setReferee(pl);
                }
            }
        }
    }
}

