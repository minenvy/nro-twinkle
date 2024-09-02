package com.twinkle.models.boss.dhvt;

import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.player.Player;

/**
 * @author BTH sieu cap vippr0 
 */
public class Xinbato extends BossDHVT {

    public Xinbato(Player player) throws Exception {
        super(BossID.XINBATO, BossesData.XINBATO);
        this.playerAtt = player;
    }
}