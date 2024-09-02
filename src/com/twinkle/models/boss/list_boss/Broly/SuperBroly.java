package com.twinkle.models.boss.list_boss.Broly;

import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossData;
import com.twinkle.models.boss.BossStatus;
import com.girlkun.consts.ConstPlayer;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossManager;
import static com.twinkle.models.boss.BossStatus.ACTIVE;
import static com.twinkle.models.boss.BossStatus.JOIN_MAP;
import static com.twinkle.models.boss.BossStatus.RESPAWN;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.boss.list_boss.cell.SieuBoHung;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.map.Zone;
import com.twinkle.models.map.challenge.MartialCongressService;
import com.twinkle.models.player.Player;
import com.twinkle.models.skill.Skill;
import com.twinkle.server.Manager;
import com.twinkle.services.EffectSkillService;
import com.twinkle.services.PetService;
import com.twinkle.services.PlayerService;
import com.twinkle.services.Service;
import com.twinkle.services.SkillService;
import com.twinkle.services.func.ChangeMapService;
import com.twinkle.utils.SkillUtil;
import com.twinkle.utils.Util;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuperBroly extends Boss {

    public SuperBroly() throws Exception {
        super(BossID.S_BROLY, BossesData.BROLY_3);
    }

    public void reward(Player plKill) {
        byte randomDo = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length - 1);
        if (plKill.pet == null) {
            int gender = Util.nextInt(0, 2);
            PetService.gI().createNormalPet(plKill, gender);
            Service.getInstance().sendThongBao(plKill, "Bạn vừa nhận được đệ tử");
        } else {
            //    Service.gI().dropItemMap(this.zone, Util.ratiItem(zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.location.y, plKill.id));
        }
    }

    @Override
    public void joinMap() {
        super.joinMap();
    }

    @Override
    public void active() {
        super.active();

    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);

    }

}
