package com.twinkle.models.boss.list_boss.doanh_trai;

import com.girlkun.consts.ConstRatio;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.map.doanhtrai.DoanhTrai;
import com.twinkle.models.player.Player;
import com.twinkle.services.SkillService;
import com.twinkle.services.func.ChangeMapService;
import com.twinkle.utils.Util;

/**
 *
 * @author 💖 BTH💖
 *
 */
public class RobotVeSi extends BossDoanhTrai {

    public RobotVeSi(byte id, DoanhTrai doanhTrai) throws Exception {
        super(id, BossesData.ROBOT_VE_SI, doanhTrai);
    }


    @Override
    public void attack() {
        super.attack();
    }

    @Override
    public void joinMap() {
//        super.joinMap();
        this.zone = this.doanhTrai.getMapById(57);
        ChangeMapService.gI().changeMap(this, this.zone, 435, this.zone.map.yPhysicInTop(1065, 0));
    }

}
