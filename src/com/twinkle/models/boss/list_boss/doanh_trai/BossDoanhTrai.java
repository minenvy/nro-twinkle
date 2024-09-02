package com.twinkle.models.boss.list_boss.doanh_trai;

import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossData;
import com.twinkle.models.boss.BossManager;
import com.twinkle.models.clan.Clan;
import com.twinkle.models.clan.ClanMember;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.map.doanhtrai.DoanhTrai;
import com.twinkle.models.player.Player;
import com.twinkle.services.PlayerService;
import com.twinkle.services.Service;
import com.twinkle.services.func.ChangeMapService;
import com.twinkle.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 💖 BTH💖
 *
 */
public abstract class BossDoanhTrai extends Boss {

    private int highestDame; //dame lớn nhất trong clan
    private int highestHp; //hp lớn nhất trong clan

    private int xHpForDame = 50; //dame gốc = highesHp / xHpForDame;
    private int xDameForHp = 50; //hp gốc = xDameForHp * highestDame;

    protected DoanhTrai doanhTrai;

    public BossDoanhTrai(byte id, BossData data, DoanhTrai doanhTrai) throws Exception {
        super(id, data);
        this.xHpForDame = data.getDame();

        int arrHp = data.getHp()[Util.nextInt(0, data.getHp().length - 1)];
        this.xDameForHp = arrHp;
        this.doanhTrai = doanhTrai;

        this.spawn(doanhTrai.getClan());
    }

    private void spawn(Clan clan) {
                for (ClanMember cm : clan.getMembers()) {
                    for (Player pl : clan.membersInGame) {
                        if (pl.id == cm.id && pl.nPoint.hpMax >= highestHp) {
                            this.highestHp = pl.nPoint.hpMax;
                        }
                    }
                }
                this.nPoint.dameg = this.highestHp / this.xHpForDame;
                for (ClanMember cm : clan.getMembers()) {
                    for (Player pl : clan.membersInGame) {
                        if (pl.id == cm.id && pl.nPoint.dame >= highestDame) {
                            this.highestDame = pl.nPoint.dame;
                        }
                    }
                }
                this.nPoint.hpg = this.highestDame * this.xDameForHp;
                this.nPoint.calPoint();
                this.nPoint.setFullHpMp();
        }



    @Override
    public void checkPlayerDie(Player pl) {
        if (pl.isDie()) {
            Service.getInstance().chat(this, "Chừa chưa ranh con, nên nhớ ta là " + this.name);
        }
    }

    @Override
    public void attack() {
        super.attack();
    }

    @Override
    public void active(){
        super.active();
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }

    @Override
    public void reward(Player pl) {
        if (Util.isTrue(1, 5)) {
            ItemMap itemMap = new ItemMap(this.zone, 611, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, 100), -1);
            Service.getInstance().dropItemMap(this.zone, itemMap);
        }
        int[] nro = {17, 18, 19, 20};
        ItemMap itemMap = new ItemMap(this.zone, nro[Util.nextInt(0, nro.length - 1)], 1,
                this.location.x, this.zone.map.yPhysicInTop(this.location.x, 100), -1);
        Service.getInstance().dropItemMap(this.zone, itemMap);
    }
}
