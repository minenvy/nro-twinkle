package com.twinkle.models.boss.list_boss.doanh_trai;

import com.girlkun.consts.ConstRatio;
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
public class TrungUyXanhLo extends BossDoanhTrai {

    private boolean activeAttack;

    public TrungUyXanhLo(DoanhTrai doanhTrai) throws Exception {
        super(BossID.TRUNG_UY_XANH_LO, BossesData.TRUNG_UY_XANH_LO, doanhTrai);
    }

    @Override
    public void joinMap() {
//        super.joinMap();
        this.zone = this.doanhTrai.getMapById(62);
        ChangeMapService.gI().changeMap(this, this.zone, 435, this.zone.map.yPhysicInTop(1065, 0));
    }


    @Override
    public void attack() {
        super.attack();
    }

}
