package com.twinkle.models.boss.list_boss.ginyunamec;

import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossStatus;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.player.Player;
import com.twinkle.services.EffectSkillService;
import com.twinkle.services.Service;
import com.twinkle.services.TaskService;
import com.twinkle.utils.Util;
import java.util.Random;


public class so1 extends Boss {

    public so1() throws Exception {
        super(BossID.SO_1_NM, BossesData.SO_1_NM);
    }
     @Override
    public void reward(Player plKill) {
        int[] itemDos = new int[]{457};
        int[] NRs = new int[]{457};
        int randomDo = new Random().nextInt(itemDos.length);
        int randomNR = new Random().nextInt(NRs.length);
        if (Util.isTrue(50, 100)) {
            if (Util.isTrue(50, 50)) {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 457, 10, this.location.x, this.location.y, plKill.id));
                return;
            }
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], 10, this.location.x, this.location.y, plKill.id));
        } else  if (Util.isTrue(50, 100)){
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, NRs[randomNR], 10, this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
        }
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

   @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if(Util.canDoWithTime(st,900000)){
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }

    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st= System.currentTimeMillis();
    }
    private long st;

    @Override
    public void wakeupAnotherBossWhenDisappear() {
        if (this.parentBoss == null) {
            return;
        }
        for (Boss boss : this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel]) {
            if (boss.id == BossID.STANG && boss.isDie()) {
                this.parentBoss.changeToTypePK();
                return;
            }
        }
        
    }
  
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - GirlBeo
 */
