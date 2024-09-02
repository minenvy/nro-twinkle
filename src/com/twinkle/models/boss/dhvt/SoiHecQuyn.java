package com.twinkle.models.boss.dhvt;

import com.twinkle.models.boss.BossData;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.player.Player;

/**
 * @author BTH sieu cap vippr0 
 */
public class SoiHecQuyn extends BossDHVT {
    public SoiHecQuyn(Player player) throws Exception {
        super(BossID.SOI_HEC_QUYN, BossesData.SOI_HEC_QUYN);
        this.playerAtt = player;
    }
}
