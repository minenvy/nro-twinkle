package com.twinkle.models.boss.list_boss.BLACK;

import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossManager;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.boss.BossStatus;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.player.Player;
import com.twinkle.server.Manager;
import com.twinkle.services.EffectSkillService;
import com.twinkle.services.Service;
import com.twinkle.utils.Util;

import java.util.Random;


public class Black extends Boss {

    public Black() throws Exception {
        super(BossID.BLACK, BossesData.BLACK_GOKU);
    }

    @Override
    public void reward(Player plKill) {
        if (Util.isTrue(BossManager.ratioReward, 100)) {
            Service.gI().dropItemMap(this.zone, Util.khongthegiaodich(zone, 992, 1, this.location.x, this.location.y, plKill.id));
        }
    }

    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 400000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }
    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 1000)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage/1);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = damage/1;
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
    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }

    private long st;

//    @Override
//    public void moveTo(int x, int y) {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.moveTo(x, y);
//    }
//
//    @Override
//    public void reward(Player plKill) {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.reward(plKill);
//    }
//    
//    @Override
//    protected void notifyJoinMap() {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.notifyJoinMap();
//    }
}






















