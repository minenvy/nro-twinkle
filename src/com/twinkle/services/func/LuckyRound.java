package com.twinkle.services.func;

import com.twinkle.models.item.Item;
import com.twinkle.models.player.Player;
import com.girlkun.network.io.Message;
import com.twinkle.services.RewardService;
import com.twinkle.services.Service;
import java.util.List;


public class LuckyRound {

    private static final byte MAX_ITEM_IN_BOX = 100;
//    private static final int IFOX_BOX = 400;

    //1 gem and ruby
    public static final byte USING_RUBY = 2;
    public static final byte USING_GOLD = 0;

    private static final byte PRICE_RUBY = 4;
    private static final int PRICE_GOLD = 10000000;

    private static LuckyRound i;

    private LuckyRound() {

    }

    public static LuckyRound gI() {
        if (i == null) {
            i = new LuckyRound();
        }
        return i;
    }

    public void openCrackBallUI(Player pl, byte type) {
        pl.iDMark.setTypeLuckyRound(type);
        Message msg;
        try {
            msg = new Message(-127);
            msg.writer().writeByte(0);
            msg.writer().writeByte(7);
            for (int i = 0; i < 7; i++) {
                msg.writer().writeShort(419 + i);
            }
            msg.writer().writeByte(type); //type price
            msg.writer().writeInt(type == USING_RUBY ? PRICE_RUBY : PRICE_GOLD); //price
            msg.writer().writeShort(14); //id ticket
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void readOpenBall(Player player, Message msg) {
        try {
            byte type = msg.reader().readByte();
            byte count = msg.reader().readByte();
            switch (player.iDMark.getTypeLuckyRound()) {
                case USING_RUBY:
                    openBallByRuby(player, count);
                    break;
                case USING_GOLD:
                    openBallByGold(player, count);
                    break;
            }
        } catch (Exception e) {
            openCrackBallUI(player, player.iDMark.getTypeLuckyRound());
        }
    }

    private void openBallByRuby(Player player, byte count) {
        int rubyNeed = (count * PRICE_RUBY);
        if (player.inventory.ruby < rubyNeed) {
            Service.gI().sendThongBao(player, "Bạn không đủ ngọc để mở");
            return;
        } else {
            if (count + player.inventory.itemsBoxCrackBall.size() <= MAX_ITEM_IN_BOX) {
                player.inventory.ruby -= rubyNeed;
                List<Item> list = RewardService.gI().getListItemLuckyRound(player, count);
                addItemToBox(player, list);
                sendReward(player, list);
                Service.gI().sendMoney(player);
            } else {
                Service.gI().sendThongBao(player, "Rương phụ đã đầy");
            }
        }
    }

    private void openBallByGold(Player player, byte count) {
        int goldNeed = (count * PRICE_GOLD);
        if (player.inventory.gold < goldNeed) {
            Service.gI().sendThongBao(player, "Bạn không đủ vàng để mở");
            return;
        } else {
            if (count + player.inventory.itemsBoxCrackBall.size() <= MAX_ITEM_IN_BOX) {
                player.inventory.gold -= goldNeed;
                List<Item> list = RewardService.gI().getListItemLuckyRound(player, count);
                addItemToBox(player, list);
                sendReward(player, list);
                Service.gI().sendMoney(player);
            } else {
                Service.gI().sendThongBao(player, "Rương phụ đã đầy");
            }
        }
    }

    private void sendReward(Player player, List<Item> items) {
        Message msg;
        try {
            msg = new Message(-127);
            msg.writer().writeByte(1);
            msg.writer().writeByte(items.size());
            for (Item item : items) {
                msg.writer().writeShort(item.template.iconID);
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    private void addItemToBox(Player player, List<Item> items) {
        for (Item item : items) {
            player.inventory.itemsBoxCrackBall.add(item);
        }
    }
}
