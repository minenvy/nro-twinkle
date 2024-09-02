/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twinkle.models.boss.list_boss.kami;
import com.girlkun.consts.ConstPlayer;
import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossManager;
import com.twinkle.models.boss.BossStatus;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.boss.list_boss.kami.kamiSooMe;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.player.Player;
import com.twinkle.services.EffectSkillService;
import com.twinkle.services.PlayerService;
import com.twinkle.services.Service;
import com.twinkle.utils.Util;
import java.util.Random;

public class kamiLoc extends Boss {

    public kamiLoc() throws Exception {
        super(BossID.KAMILOC, BossesData.KAMILOC);
    }
    @Override
    public void reward(Player plKill) {
       int[] itemDos = new int[]{569,555,556,557,558,559,560,561,562,563,564,565,566,567};
        int[] NRs = new int[]{17,16,15,18,19,20};
        int randomDo = new Random().nextInt(itemDos.length);
        int randomNR = new Random().nextInt(NRs.length);
        if (Util.isTrue(15, 100)) {
            if (Util.isTrue(1, 50)) {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 561, 1, this.location.x, this.location.y, plKill.id));
                return;
            }
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], 1, this.location.x, this.location.y, plKill.id));
         } else if (Util.isTrue(50, 100)) {
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, NRs[randomNR], 1, this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        }
    }
    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 900000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }
     
    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (Util.isTrue(40, 100) && plAtt != null) {//tỉ lệ hụt của thiên sứ
            Util.isTrue(this.nPoint.tlNeDon, 1);
            if (Util.isTrue(1, 100)) {
                this.chat("Ta Chính Là Thần SooMe");
                this.chat("Ta Chính Là Thần SooMe");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Ta Chính Là Thần SooMe");
                this.chat("Chỉ cần hoàn thiện nó!");
                this.chat("Các ngươi sẽ tránh được mọi nguy hiểm");
            } else if (Util.isTrue(1, 100)) {
                this.chat("Ta Chính Là Thần SooMe");
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
                damage = damage;
                 if (damage > nPoint.mpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = damage; 
                 if (damage > nPoint.tlNeDon) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = damage; 
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

   

    public void recoverHP() {
        PlayerService.gI().hoiPhuc(this, this.nPoint.hpMax, 0);
    }

   

}