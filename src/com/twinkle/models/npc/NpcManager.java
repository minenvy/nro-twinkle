package com.twinkle.models.npc;

import com.girlkun.consts.ConstNpc;
import com.girlkun.consts.ConstTask;
import com.twinkle.models.player.Player;
import com.twinkle.server.Manager;
import com.twinkle.services.TaskService;
import java.util.ArrayList;
import java.util.List;

public class NpcManager {

    public static Npc getByIdAndMap(int id, int mapId) {
        for (Npc npc : Manager.NPCS) {
            if (npc.tempId == id && npc.mapId == mapId) {
                return npc;
            }
        }
        return null;
    }

    public static Npc getNpc(byte tempId) {
        for (Npc npc : Manager.NPCS) {
            if (npc.tempId == tempId) {
                return npc;
            }
        }
        return null;
    }

    public static List<Npc> getNpcsByMapPlayer(Player player) {
        List<Npc> list = new ArrayList<>();
        if (player.zone != null) {
            for (Npc npc : player.zone.map.npcs) {
                if (npc.tempId == ConstNpc.QUA_TRUNG && player.mabuEgg == null && player.zone.map.mapId == (21 + player.gender)) {
                    continue;
                } else if (npc.tempId == ConstNpc.QUA_TRUNG && player.billEgg == null && player.zone.map.mapId == 7) {
                    continue;
                } else if (npc.tempId == ConstNpc.CALICK && TaskService.gI().getIdTask(player) < ConstTask.TASK_20_0) {
                    continue;
                }
                list.add(npc);
            }
        }
        return list;
    }
}
