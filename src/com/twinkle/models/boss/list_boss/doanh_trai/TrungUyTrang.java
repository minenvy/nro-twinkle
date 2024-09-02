package com.twinkle.models.boss.list_boss.doanh_trai;

import com.girlkun.consts.ConstMob;
import com.girlkun.consts.ConstRatio;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.map.doanhtrai.DoanhTrai;
import com.twinkle.models.mob.Mob;
import com.twinkle.models.player.Player;
import com.twinkle.services.SkillService;
import com.twinkle.services.func.ChangeMapService;
import com.twinkle.utils.Util;

/**
 *
 * @author 💖 BTH💖
 *
 */
public class TrungUyTrang extends BossDoanhTrai {

    public TrungUyTrang(DoanhTrai doanhTrai) throws Exception {
        super(BossID.TRUNG_UY_TRANG, BossesData.TRUNG_UY_TRANG, doanhTrai);
    }


    @Override
    public void attack() {
        super.attack();
    }

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (!piercing) {
            boolean haveBulon = false;  //true: diết bulong mới pem dc false: k cần bulong
            for (Mob mob : this.zone.mobs) {
                if (mob.tempId == ConstMob.BULON && !mob.isDie()) {
                    haveBulon = true;
                    break;
                }
            }
            if (haveBulon) {
                damage = 1;
            }
        }
        return super.injured(plAtt, damage, piercing, isMobAttack);
    }


    @Override
    public void joinMap() {
//        super.joinMap();
        this.zone = this.doanhTrai.getMapById(59);
        ChangeMapService.gI().changeMap(this, this.zone, 435, this.zone.map.yPhysicInTop(1065, 0));
    }


}
