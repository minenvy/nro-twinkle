package com.twinkle.models.boss.list_boss.doanh_trai;

import com.girlkun.consts.ConstRatio;
import com.twinkle.models.boss.BossData;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.map.doanhtrai.DoanhTrai;
import com.twinkle.models.player.Player;
import com.twinkle.services.PlayerService;
import com.twinkle.services.SkillService;
import com.twinkle.services.func.ChangeMapService;
import com.twinkle.utils.Util;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 💖 BTH💖
 *
 */
public class NinjaAoTim extends BossDoanhTrai {

    public NinjaAoTim(DoanhTrai doanhTrai) throws Exception {
        super(BossID.NINJA_AO_TIM, BossesData.NINJA_AO_TIM, doanhTrai);
    }

    public NinjaAoTim(byte id, BossData bossData, DoanhTrai doanhTrai) throws Exception {
        super(id, bossData, doanhTrai);
    }

    @Override
    public void joinMap() {
        super.joinMap();
        this.zone = this.doanhTrai.getMapById(54);
        ChangeMapService.gI().changeMap(this, this.zone, 435, 312);
        super.active();
    }


    @Override
    public void attack() {
        super.attack();
        if (Util.isTrue(0, 100)) {
            try {
                this.phanThan();
            } catch (Exception ex) {
                Logger.getLogger(NinjaAoTim.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private int maxCountIdle;
    private int countIdle;

    @Override
    public void active() {
        if (countIdle >= maxCountIdle) {
            this.countIdle = 0;
            this.attack();
        } else {
            this.countIdle++;
            int xMove = this.location.x += Util.nextInt(-200, 200);
            if (xMove < 50) {
                xMove = 50;
            } else if (xMove > this.zone.map.mapWidth - 50) {
                xMove = this.zone.map.mapWidth - 50;
            }
            PlayerService.gI().playerMove(this, xMove, this.zone.map.yPhysicInTop(xMove, 100));
        }
    }



    private boolean phanThan;

    private void phanThan() throws Exception {
        if (!phanThan) {
            doanhTrai.bosses.add(new NinjaAoTimFake(BossID.NINJA_AO_TIM_FAKE_1, this.doanhTrai));
            doanhTrai.bosses.add(new NinjaAoTimFake(BossID.NINJA_AO_TIM_FAKE_2, this.doanhTrai));
            doanhTrai.bosses.add(new NinjaAoTimFake(BossID.NINJA_AO_TIM_FAKE_3, this.doanhTrai));
            doanhTrai.bosses.add(new NinjaAoTimFake(BossID.NINJA_AO_TIM_FAKE_4, this.doanhTrai));
            doanhTrai.bosses.add(new NinjaAoTimFake(BossID.NINJA_AO_TIM_FAKE_5, this.doanhTrai));
            doanhTrai.bosses.add(new NinjaAoTimFake(BossID.NINJA_AO_TIM_FAKE_6, this.doanhTrai));
            phanThan = true;
        }
    }

}
