package com.twinkle.models.boss.list_boss.doanh_trai;

import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.map.doanhtrai.DoanhTrai;
import com.twinkle.models.player.Player;
import com.twinkle.services.SkillService;
import com.twinkle.services.func.ChangeMapService;
import com.twinkle.utils.Util;

import java.util.List;

/**
 *
 * @author 💖 BTH💖
 *
 */
public class TrungUyThep extends BossDoanhTrai {

    public TrungUyThep(DoanhTrai doanhTrai) throws Exception {
        super(BossID.TRUNG_UD_THEP, BossesData.TRUNG_UY_THEP, doanhTrai);
    }

    @Override
    public void attack() {
        super.attack();
    }

    @Override
    public void joinMap() {
//        super.joinMap();
        this.zone = this.doanhTrai.getMapById(55);
        ChangeMapService.gI().changeMap(this, this.zone, 435, this.zone.map.yPhysicInTop(1065, 0));
    }

}
