package com.twinkle.models.boss.list_boss.android;

import com.girlkun.consts.ConstPlayer;
import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.boss.BossStatus;
import com.twinkle.models.boss.BossesData;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.player.Player;
import com.twinkle.models.skill.Skill;
import com.twinkle.services.PlayerService;
import com.twinkle.services.Service;
import com.twinkle.services.TaskService;
import com.twinkle.utils.Util;


public class DrKore extends Boss {

    public DrKore() throws Exception {
        super(BossID.DR_KORE, BossesData.DR_KORE);
    }

    @Override
    public void reward(Player plKill) {
        int[] itemRan = new int[]{1142, 382, 383, 384, 1142};
        int itemId = itemRan[2];
        if (Util.isTrue(15, 100)) {
            ItemMap it = new ItemMap(this.zone, itemId, 17, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.gI().dropItemMap(this.zone, it);
        }
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

    @Override
    public void chatM() {
        if (Util.isTrue(60, 61)) {
            super.chatM();
            return;
        }
        if (this.bossAppearTogether == null || this.bossAppearTogether[this.currentLevel] == null) {
            return;
        }
        for (Boss boss : this.bossAppearTogether[this.currentLevel]) {
            if (boss.id == BossID.ANDROID_19 && !boss.isDie()) {
                this.chat("Hút năng lượng của nó, mau lên");
                boss.chat("Tuân lệnh đại ca, hê hê hê");
                break;
            }
        }
    }

     @Override
    public void active() {
        super.active();
    }

    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st= System.currentTimeMillis();
    }
    private long st;

    @Override
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (plAtt != null) {
            switch (plAtt.playerSkill.skillSelect.template.id) {
                case Skill.KAMEJOKO:
                case Skill.MASENKO:
                case Skill.ANTOMIC:
                    PlayerService.gI().hoiPhuc(this, damage, 0);
                    if (Util.isTrue(1, 5)) {
                        this.chat("Hấp thụ.. các ngươi nghĩ sao vậy?");
                    }
                    return 0;
            }
        }
        return super.injured(plAtt, damage, piercing, isMobAttack);
    }

    @Override
    public void doneChatS() {
        for (Boss boss : this.bossAppearTogether[this.currentLevel]) {
            if (boss.id == BossID.ANDROID_19) {
                boss.changeToTypePK();
                break;
            }
        }
    }

    @Override
    public void changeToTypePK() {
        super.changeToTypePK();
        this.chat("Mau đền mạng cho thằng em trai ta");
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - GirlBeo
 */
