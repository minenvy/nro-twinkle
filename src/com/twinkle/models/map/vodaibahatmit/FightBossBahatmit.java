package com.twinkle.models.map.vodaibahatmit;

import com.twinkle.models.player.Player;
import com.twinkle.services.func.ChangeMapService;
import com.girlkun.consts.ConstPlayer;
import com.twinkle.models.npc.NpcFactory;
import com.twinkle.services.PlayerService;
import com.twinkle.services.Service;

public class FightBossBahatmit {
    public final byte POINT_MAX = 5;

    public int pointbahatmit = 0;
    private Player player;

    public FightBossBahatmit(Player player){
        this.player = player;
    }

    public void changePoint(byte pointAdd) {
        if (player.pkbahatmit.zone.map.mapId==112) {
            pointbahatmit += pointAdd;
            if (pointbahatmit == POINT_MAX) {
                Service.getInstance().sendThongBao(player.pkbahatmit, "Chúc mừng bạn đã chiến thắng các đệ tử của bà hạt mít");
                ChangeMapService.gI().changeMapBaHatMit(player.pkbahatmit, 112, -1, 217,408);
                PlayerService.gI().changeAndSendTypePK(player, ConstPlayer.NON_PK);
                NpcFactory.timebahatmit=0;
            }
        }
    }

    public void clear() {
        this.pointbahatmit=0;
    }
}
