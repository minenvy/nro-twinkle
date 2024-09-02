package com.twinkle.models.boss.dhvt;

import com.twinkle.models.boss.BossData;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.player.Player;

/**
 * @author BTH sieu cap vippr0 
 */
public class ODo extends BossDHVT {

    public ODo(Player player) throws Exception {
        super(BossID.O_DO, BossesData.O_DO);
        this.playerAtt = player;
    }
}
