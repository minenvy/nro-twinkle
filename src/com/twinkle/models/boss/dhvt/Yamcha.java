package com.twinkle.models.boss.dhvt;

import com.twinkle.models.boss.BossData;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.player.Player;
/**
 * @author BTH sieu cap vippr0 
 */
public class Yamcha extends BossDHVT {

    public Yamcha(Player player) throws Exception {
        super(BossID.YAMCHA, BossesData.YAMCHA);
        this.playerAtt = player;
    }
}