package com.twinkle.models.boss.list_boss.bojack;

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

public class bido extends Boss {

    public bido() throws Exception {
        super(BossID.STANG, BossesData.STANG);
    }
   @Override
    public void reward(Player plKill) {
        int[] itemDos = new int[]{674};
        int[] NRs = new int[]{77};
        int randomDo = new Random().nextInt(itemDos.length);
        int randomNR = new Random().nextInt(NRs.length);
        if (Util.isTrue(100, 100)) {
            if (Util.isTrue(50, 50)) {
                Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, 673, 2, this.location.x, this.location.y, plKill.id));
                return;
            }
            Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], 2, this.location.x, this.location.y, plKill.id));
        } else  if (Util.isTrue(100, 100)){
            Service.gI().dropItemMap(this.zone, new ItemMap(zone, NRs[randomNR], 3000, this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
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
    
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - GirlBeo
 */
