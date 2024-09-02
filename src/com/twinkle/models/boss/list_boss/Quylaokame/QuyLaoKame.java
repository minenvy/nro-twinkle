package com.twinkle.models.boss.list_boss.Quylaokame;

import com.girlkun.consts.ConstPlayer;
import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossStatus;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossManager;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.player.Player;
import com.twinkle.server.Manager;
import com.twinkle.services.EffectSkillService;
import com.twinkle.services.PlayerService;
import com.twinkle.services.Service;
import com.twinkle.services.TaskService;
import com.twinkle.utils.Util;

import java.util.Random;


public class QuyLaoKame extends Boss {

  

    public QuyLaoKame() throws Exception {
        super(BossID.QUY_LAO_KAME);
    }

    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;

    @Override
  public void reward(Player plKill) {
        int[] item = new int[]{710};
        int randomDo = new Random().nextInt(item.length);
        if (Util.isTrue(99, 100)) {
            if (Util.isTrue(99, 100)) {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 710, 1, this.location.x, this.location.y, plKill.id));
                return;
                
            }
            Service.gI().dropItemMap(this.zone, Util.useItem(zone, item[randomDo], 1, this.location.x, this.location.y, plKill.id));
        } 
    }

    @Override
    public void active() {
        if (this.typePk == ConstPlayer.NON_PK) {
            this.changeToTypePK();
        }
      
        this.attack();
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 900000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }
}
    
   
   