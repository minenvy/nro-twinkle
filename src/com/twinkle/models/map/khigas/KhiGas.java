package com.twinkle.models.map.khigas;

import com.twinkle.models.boss.bdkb.TrungUyXanhLo;
import com.twinkle.models.clan.Clan;
import com.twinkle.models.map.TrapMap;
import com.twinkle.models.map.Zone;
import com.twinkle.models.mob.Mob;
import com.twinkle.models.player.Player;
import com.twinkle.services.ItemTimeService;
import com.twinkle.services.MapService;
import com.twinkle.services.Service;
import com.twinkle.services.func.ChangeMapService;
import com.twinkle.utils.Logger;
import com.twinkle.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BTH
 *
 */
public class KhiGas {

    public static final long POWER_CAN_GO_TO_KG = 2000000000;

    public static final List<KhiGas> KHI_GAS;
    public static final int MAX_AVAILABLE = 70;
    public static final int TIME_KHI_GAS = 1800000;
    public static final int AVAILABLE = 10;

    private Player player;

    static {
        KHI_GAS = new ArrayList<>();
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            KHI_GAS.add(new KhiGas(i));
        }
    }

    public int id;
    public byte level;
    public final List<Zone> zones;

    public Clan clan;
    public boolean isOpened;
    private long lastTimeOpen;

    public KhiGas(int id) {
        this.id = id;
        this.zones = new ArrayList<>();
    }

    public void update() {
        if (this.isOpened) {
            if (Util.canDoWithTime(lastTimeOpen, TIME_KHI_GAS)) {
                this.finish();
            }
        }
    }

    public void openKhiGas(Player plOpen, Clan clan, byte level) {
        this.level = level;
        this.lastTimeOpen = System.currentTimeMillis();
        this.isOpened = true;
        this.clan = clan;
        this.clan.timeOpenKhiGas = this.lastTimeOpen;
        this.clan.playerOpenKhiGas = plOpen;
        this.clan.khigas = this;

        resetBanDo();
        ChangeMapService.gI().goToKG(plOpen);
      sendTextKhiGas();
    }

    private void resetBanDo() {
        for (Zone zone : zones) {
            for (TrapMap trap : zone.trapMaps) {
                trap.dame = this.level * 10000;
            }
        }
        for (Zone zone : zones) {
            for (Mob m : zone.mobs) {
                Mob.initMobKhiGas(m, this.level);
                Mob.hoiSinhMob(m);
            }
        }
    }

    //kết thúc bản đồ kho báu
    public void finish() {
        List<Player> plOutDT = new ArrayList();
        for (Zone zone : zones) {
            List<Player> players = zone.getPlayers();
            for (Player pl : players) {
                plOutDT.add(pl);
            }

        }
        for (Player pl : plOutDT) {
            ChangeMapService.gI().changeMapBySpaceShip(pl, 5, -1, 64);
        }
        this.clan.khigas = null;
        this.clan = null;
        this.isOpened = false;
    }


    public Zone getMapById(int mapId) {
        for (Zone zone : zones) {
            if (zone.map.mapId == mapId) {
                return zone;
            }
        }
        return null;
    }

    public static void addZone(int idBanDo, Zone zone) {
        KHI_GAS.get(idBanDo).zones.add(zone);
    }

    private void sendTextKhiGas() {
        for (Player pl : this.clan.membersInGame) {
            ItemTimeService.gI().sendTextKhiGas(pl);
        }
    }
}
