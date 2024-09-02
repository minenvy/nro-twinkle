package com.twinkle.models.boss.dhvt;

import com.twinkle.models.boss.BossData;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.player.Player;
/**
 * @author BTH sieu cap vippr0 
 */
public class ChaPa extends BossDHVT {

    public ChaPa(Player player) throws Exception {
        super(BossID.CHA_PA, BossesData.CHA_PA);
        this.playerAtt = player;
    }
}