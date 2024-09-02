package com.twinkle.models.boss.list_boss.doanh_trai;

import com.girlkun.consts.ConstPlayer;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.map.doanhtrai.DoanhTrai;

/**
 *
 * @author 💖 BTH💖
 *
 */
public class NinjaAoTimFake extends NinjaAoTim {

    public NinjaAoTimFake(byte id, DoanhTrai doanhTrai) throws Exception {
        super(id, BossesData.NINJA_AO_TIM_FAKE, doanhTrai);
        this.typePk = ConstPlayer.PK_ALL;
    }

    @Override
    public void active() {
        this.attack();
    }

}
