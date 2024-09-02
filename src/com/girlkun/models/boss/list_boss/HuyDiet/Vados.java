package com.girlkun.models.boss.list_boss.HuyDiet;

import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossStatus;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.player.Player;
import com.twinkle.server.Manager;
import com.twinkle.services.EffectSkillService;
import com.twinkle.services.Service;
import com.twinkle.utils.Util;
import java.util.Random;

public class Vados extends Boss {

    public Vados() throws Exception {
        super(Util.randomBossId(), BossesData.THIEN_SU_VADOS);
    }

    @Override
    public void reward(Player plKill) {
       byte randomDo = (byte) new Random().nextInt(Manager.itemIds_HD.length - 1);
        
        if (Util.isTrue(100, 100)) {
         
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, Manager.itemIds_HD[randomDo], 1, this.location.x, this.location.y, plKill.id));
            }
        
    }

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (Util.isTrue(10, 100) && plAtt != null) {//tỉ lệ hụt của thiên sứ
            Util.isTrue(this.nPoint.tlNeDon, 1);
            if (Util.isTrue(50, 100)) {
                this.chat("Hãy để bản năng tự vận động");
                this.chat("Tránh các động tác thừa");
            } else if (Util.isTrue(50, 100)) {
                this.chat("Chậm lại,các ngươi quá nhanh rồi");
                this.chat("Chỉ cần hoàn thiện nó!");
                this.chat("Các ngươi sẽ tránh được mọi nguy hiểm");
            } else if (Util.isTrue(50, 100)) {
                this.chat("Đây chính là bản năng vô cực");
            }
            damage = 0;

        }
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 1)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            if (damage >= 2000000000) {
                damage = 2000000000;
            }
            this.nPoint.subHP(damage);
            if (isDie()) {
                this.setDie(plAtt);
                die(plAtt);
            }
            return damage;
        } else {
            return 0;
        }
    }

//    @Override
//    public void active() {
//        super.active(); //To change body of generated methods, choose Tools | Templates.
//        if (Util.canDoWithTime(st, 1000000)) {
//            this.changeStatus(BossStatus.LEAVE_MAP);
//        }
//    }
//
//    @Override
//    public void joinMap() {
//        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
//        st = System.currentTimeMillis();
//    }
//    private long st;
}
