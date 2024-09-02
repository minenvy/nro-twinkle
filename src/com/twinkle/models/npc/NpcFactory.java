package com.twinkle.models.npc;

import com.twinkle.services.NpcService;
import com.twinkle.services.Service;
import com.twinkle.services.ClanService;
import com.twinkle.services.InventoryServiceNew;
import com.twinkle.services.MapService;
import com.twinkle.services.ItemMapService;
import com.twinkle.services.NgocRongNamecService;
import com.twinkle.services.PetService;
import com.twinkle.services.FriendAndEnemyService;
import com.twinkle.services.TaskService;
import com.twinkle.services.OpenPowerService;
import com.twinkle.services.IntrinsicService;
import com.twinkle.services.PlayerService;
import com.twinkle.services.ItemService;
import com.arriety.MaQuaTang.MaQuaTangManager;
import com.girlkun.network.io.Message;
import com.twinkle.models.boss.BossManager;
import com.twinkle.models.boss.list_boss.NhanBan;
import com.girlkun.consts.ConstMap;
import com.twinkle.models.map.bando.BanDoKhoBau;
import com.twinkle.models.map.bando.BanDoKhoBauService;
import com.twinkle.models.map.khigas.KhiGas;
import com.twinkle.models.map.khigas.KhiGasService;
import com.twinkle.models.map.challenge.MartialCongressService;
import com.girlkun.consts.ConstNpc;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.consts.ConstTask;
import com.twinkle.jdbc.daos.PlayerDAO;
import com.twinkle.models.boss.Boss;
import com.twinkle.models.boss.BossData;
import com.twinkle.models.boss.BossID;
import com.twinkle.models.clan.Clan;
import com.twinkle.models.clan.ClanMember;
import com.twinkle.models.boss.list_boss.bahatmit.BossBahatmit;

import java.util.HashMap;
import java.util.List;

import com.twinkle.services.func.ChangeMapService;
import com.twinkle.services.func.SummonDragon;
import static com.twinkle.services.func.SummonDragon.SHENRON_1_STAR_WISHES_1;
import static com.twinkle.services.func.SummonDragon.SHENRON_1_STAR_WISHES_2;
import static com.twinkle.services.func.SummonDragon.SHENRON_SAY;

import com.twinkle.models.player.Player;
import com.twinkle.models.item.Item;
import com.twinkle.models.item.Item.ItemOption;
import com.twinkle.models.map.Map;
import com.twinkle.models.map.Zone;
import com.twinkle.models.map.blackball.BlackBallWar;
import com.twinkle.models.map.MapMaBu.MapMaBu;
import com.twinkle.models.map.doanhtrai.DoanhTrai;
import com.twinkle.models.map.doanhtrai.DoanhTraiService;
import com.twinkle.models.player.Inventory;
import com.twinkle.models.player.NPoint;
import com.twinkle.models.matches.PVPService;
import com.twinkle.models.matches.pvp.DaiHoiVoThuat;
import com.twinkle.models.matches.pvp.DaiHoiVoThuatService;
import com.twinkle.models.shop.ShopServiceNew;
import com.twinkle.models.skill.Skill;
import com.twinkle.server.Client;
import com.twinkle.server.Maintenance;
import com.twinkle.server.Manager;
import com.twinkle.services.func.CombineServiceNew;
import com.twinkle.services.func.Input;
import com.twinkle.services.func.LuckyRound;
import com.twinkle.services.func.TopService;
import com.twinkle.utils.Logger;
import com.twinkle.utils.TimeUtil;
import com.twinkle.utils.Util;
import java.util.ArrayList;
import com.twinkle.services.func.ChonAiDay;
import static com.twinkle.services.func.CombineServiceNew.CHE_TAO_TRANG_BI_TS;
import com.kygui.ItemKyGui;
import com.kygui.ShopKyGuiService;
import com.kygui.ShopKyGuiManager;
import static com.twinkle.services.func.CombineServiceNew.NANG_CAP_SKH_VIP;
//import com.twinkle.models.player.Archivement;
import java.io.DataOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.io.IOException;

import java.util.logging.Level;

public class NpcFactory {

    private static final int COST_HD = 50000000;
    
     public static int timebahatmit;

    private static boolean nhanVang = false;
    private static boolean nhanDeTu = false;

    //playerid - object
    public static final java.util.Map<Long, Object> PLAYERID_OBJECT = new HashMap<Long, Object>();

    private static Npc maygap(int mapId, int status, int cx, int cy, int tempId, int avatar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   

   // private static Npc npcminuong(int mapId, int status, int cx, int cy, int tempId, int avatar) {
     //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}

    private NpcFactory() {

    }

    private static Npc trungLinhThu(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đổi Trứng Linh thú cần:\b|7|X99 Hồn Linh Thú + 1 Tỷ vàng", "Đổi Trứng\nLinh thú", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 2029);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 99) {
                                        this.npcChat(player, "Bạn không đủ 99 Hồn Linh thú");
                                    } else if (player.inventory.gold < 1_000_000_000) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 1_000_000_000;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 99);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 2028);
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được 1 Trứng Linh thú");
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
    }   
    private static Npc sukien(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ngươi muốn gì ở ta\nSự Kiện Hè Đang Diễn Ra:\b|7|Thông Tin Sự Kiện\n ngươi hãy mang x99 cua đỏ cho ta\n ngươi xẽ có tỷ lệ nhận đc cải trang hè vĩnh viễn\n ngươi có thể úp cua đỏ ở map úp cua ", "cải trang\ntrái đất", "cải trang\nnamec", "cải trang\nxayda", "map\núp cua");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 1008);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 99) {
                                        this.npcChat(player, "Bạn không đủ 99 cua đỏ");
                                    } else if (player.inventory.gold < 0) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 99);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 905);
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(49, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(10) + 1));
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang hè");
                                    }
                                    break;
                                     case 1:
                                    Item honLinhThu1 = null;
                                    try {
                                        honLinhThu1 = InventoryServiceNew.gI().findItemBag(player, 1008);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu1 == null || honLinhThu1.quantity < 99) {
                                        this.npcChat(player, "Bạn không đủ 99 cua đỏ");
                                    } else if (player.inventory.gold < 0) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu1, 99);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 907);
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(49, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(10) + 1));
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang hè");
                                    }
                                    break;
                                     case 2:
                                    Item honLinhThu2 = null;
                                    try {
                                        honLinhThu2 = InventoryServiceNew.gI().findItemBag(player, 1008);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu2 == null || honLinhThu2.quantity < 99) {
                                        this.npcChat(player, "Bạn không đủ 99 cua đỏ");
                                    } else if (player.inventory.gold < 0) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu2, 99);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 911);
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(49, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(10) + 1));
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang hè");
                                    }
                                    break;
                                   case 3:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 208, -1, 354);
                                    Service.getInstance().changeFlag(player, Util.nextInt(8));
                                    break;
                                }
                                }
                            }
                        }
            }
        };
    }
    public static Npc gokugod(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Xin chào, tôi có thể giúp gì cho cậu?", "siêu thị 2.0", "Từ chối");
                    } else if (this.mapId == 210) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                //đến siêu thị 2.0
                                ChangeMapService.gI().changeMapBySpaceShip(player, 210, -1, 360);
                            }
                        }
                    } else if (this.mapId == 210) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về dkm
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 432);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
    
            public static Npc thoren(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 5) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "\b|7|Bạn cần đổi gì?\b|7|", "Đổi đồ\nKích Hoạt\nTrái Đất", "Đổi đồ\nKích Hoạt\nNamec", "Đổi Đồ\nKích Hoạt\nXayda");
                    }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                            case 0:
                                    this.createOtherMenu(player, 1,
                                       "\b|7|Bạn muốn đổi 15 đns, 3 món đồ thần linh \nTrái đất \n|6|Để đổi lấy 1 món đồ kích hoạt",  "Áo", "Quần", "Găng" ,"Giày","Nhẫn","Thôi Khỏi");
                                    break;
                            case 1:
                                    this.createOtherMenu(player, 2,
                                       "\b|7|Bạn muốn đổi 15 đns, 3 món đồ thần linh \nNamec \n|6|Để đổi lấy 1 món đồ kích hoạt",  "Áo", "Quần", "Găng" ,"Giày","Nhẫn","Thôi Khỏi");
                                    break;
                            case 2:
                                    this.createOtherMenu(player, 3,
                                        "\b|7|Bạn muốn đổi 15 đns, 3 món đồ thần linh \nXayda \n|6|Để đổi lấy 1 món đồ kích hoạt",  "Áo", "Quần", "Găng" ,"Giày","Nhẫn","Thôi Khỏi");
                                    break;
                            case 3:
                                    this.createOtherMenu(player, 4,
                                       "\b|7|Bạn muốn đổi 1 món đồ húy diệt \nTrái đất cùng loại và x99 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ ra SKH",  "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ" ,"Giày\nThiên Sứ","Nhẫn\nThiên Sứ","Từ Chối");
                                    break;
                            case 4:
                                    this.createOtherMenu(player, 5,
                                       "\b|7|Bạn muốn đổi 1 món đồ húy diệt \nNamek cùng loại và x99 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ ra SKH",  "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ" ,"Giày\nThiên Sứ","Nhẫn\nThiên Sứ","Từ Chối");
                                    break;
                            case 5:
                                    this.createOtherMenu(player, 6,
                                       "\b|7|Bạn muốn đổi 1 món đồ húy diệt \nXayda cùng loại và x99 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ ra SKH",  "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ" ,"Giày\nThiên Sứ","Nhẫn\nThiên Sứ","Từ Chối");
                                    break;
                                }
                            }
                            else if (player.iDMark.getIndexMenu() == 1){ // action đổi dồ húy diệt
                            switch (select){
                            case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 555);
                            int soLuong = 0;
                            int aotltd = 0;
                            if (tl != null);
                                aotltd = tl.quantity;
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0  ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 555+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 555)&& soLuong >= 15 && aotltd>=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2000 + i);
                                        this.npcChat(player, "Bạn nhận được hộp  kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo Thần linh trái đất");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 556);
                                       int soLuong = 0;
                                       int quantltd = 0;
                                       if (tl != null);
                                       quantltd = tl.quantity;
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 556+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 556+i)&& soLuong >= 15 && quantltd>=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2000+i);
                                        this.npcChat(player,"Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần Thần linh trái đất");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 562);
                                       int soLuong = 0;
                                       int gangtltd =0;
                                       if (tl !=null);{
                                       gangtltd = tl.quantity;
                                    }   
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 562+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 562+i)&& soLuong >= 15 && gangtltd >=1 ){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2000+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Găng Thần linh trái đất");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 563);
                                       int soLuong = 0;
                                       int giaytltd = 0;
                                       if (tl != null){
                                       giaytltd = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 563+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 563+i)&& soLuong >= 15 && giaytltd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2000+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày Thần linh trái đất");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561);
                                       int soLuong = 0;
                                       int nhantl = 0;
                                       if (tl != null){
                                       nhantl = tl.quantity;    
                                    }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 561+i)&& soLuong >= 15 && nhantl >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2000+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");
                                        break; 
                                    } else {
                                        this.npcChat(player, "Yêu cầu cần Nhẫn Thần linh trái đất");
                                    }                            
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;                
                                case 5: // canel
                                  break;
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 2){ // action đổi dồ húy diệt
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 557);
                            int soLuong = 0;
                            int aotlnm =0 ;
                            if (tl != null){
                            aotlnm = tl.quantity;    
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 557+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 557+i)&& soLuong >= 15 && aotlnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2001 + i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo Thần linh namec");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 558);
                                       int soLuong = 0;
                                       int quantlnm =0;
                                       if (tl != null){
                                       quantlnm = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 558+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 558+i)&& soLuong >= 15 && quantlnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2001+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần Thần linh namec");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 564);
                                       int soLuong = 0;
                                       int gangtlnm = 0;
                                       if (tl != null) {
                                       gangtlnm = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 564);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 564)&& soLuong >= 15 && gangtlnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2001);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Găng Thần linh namec");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 565);
                                       int soLuong = 0;
                                       int giaytlnm = 0;
                                       if (tl !=null){
                                       giaytlnm = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 565+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 565+i)&& soLuong >= 15 && giaytlnm >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2001+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày Thần linh namec");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561);
                                       int soLuong = 0;
                                       int nhantl =0;
                                       if (tl != null){
                                       nhantl = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 561+i)&& soLuong >= 15 && nhantl>=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2001+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");
                                        break; 
                                    } else {
                                        this.npcChat(player, "Yêu cầu cần Nhẫn Thần linh namec");
                                    }                            
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;                
                                case 5: // canel
                                  break;
                            }
                        }
                        else if (player.iDMark.getIndexMenu() == 3){ // action đổi dồ húy diệt
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 559);
                            int soLuong = 0;
                            int aotlxd = 0;
                            if (tl != null){
                                aotlxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 559+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 559+i)&& soLuong >= 15 && aotlxd>= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2002+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo Thần linh xayda");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 560);
                                       int soLuong = 0;
                                       int quantlxd =0 ;
                                       if (tl != null){
                                           quantlxd = tl.quantity;
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 560+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 560+i)&& soLuong >= 15 && quantlxd >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2002+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần Thần linh xayda");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 566);
                                       int soLuong = 0;
                                       int gangtlxd = 0;
                                       if (tl != null){
                                           gangtlxd = tl.quantity;
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 566+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 566+i)&& soLuong >= 15 && gangtlxd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2002+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 15 Đá, 1 Găng Thần linh xayda");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 567);
                                       int soLuong = 0;
                                       int giaytlxd = 0;
                                       if (tl != null){
                                           giaytlxd = tl.quantity;
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 567+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 567+i)&& soLuong >= 15 && giaytlxd >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2002+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày Thần linh xayda");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561);
                                       int soLuong = 0;
                                       int nhantl = 0;
                                       if ( tl != null){
                                           nhantl= tl.quantity;
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 561+i)&& soLuong >= 15 && nhantl >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 15);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 3);
                                        CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 2002+i);
                                        this.npcChat(player, "Bạn nhận được hộp kích hoạt");
                                        break; 
                                    } else {
                                        this.npcChat(player, "Yêu cầu cần Nhẫn Thần linh xayde");
                                    }                            
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;                
                                case 5: // canel
                                  break;
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 4){ // action đổi dồ thiên sứ
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 650);
                            int soLuong = 0;
                            int aohdtd = 0;
                            if (tl != null){
                                aohdtd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 650+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 650+i)&& soLuong >= 99 && aohdtd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1048+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 651);
                            int soLuong = 0;
                            int quanhdtd = 0;
                            if ( tl !=null){
                                quanhdtd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 651+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 651+i)&& soLuong >= 99 && quanhdtd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1051+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 657);
                            int soLuong = 0;
                            int ganghdtd= 0;
                            if (tl != null){
                                ganghdtd= tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 657+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 657+i)&& soLuong >= 99  && ganghdtd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1054);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Găng húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 658);
                            int soLuong = 0;
                            int giayhdtd = 0;                            
                            if (tl != null){
                                giayhdtd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 658+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 658+i)&& soLuong >= 99 && giayhdtd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1057+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    int nhanhd= 0;
                                    if (tl != null){
                                        nhanhd= tl.quantity;
                                    }
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 656+i)&& soLuong >= 99 && nhanhd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1060+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần nhận húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;              
                                case 5: // canel
                                  break;
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 5){ // action đổi dồ thiên sứ
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 652);
                            int soLuong = 0;
                            int aohdnm = 0;
                            if (tl != null){
                                aohdnm = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 652+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 652+i)&& soLuong >= 99 && aohdnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1049+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo húy diệt namec + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 653);
                            int soLuong = 0;
                            int quanhdnm=0;
                            if (tl != null){
                                quanhdnm= tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 653+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 653+i)&& soLuong >= 99 && quanhdnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1052+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần húy diệt namec + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 659);
                            int soLuong = 0;
                            int ganghdnm= 0;
                            if (tl !=null){
                                ganghdnm= tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 659+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 659+i)&& soLuong >= 99 && ganghdnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1055+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Găng húy diệt namec + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 660);
                            int soLuong = 0;
                            int giayhdnm =0;
                            if (tl != null){
                                giayhdnm = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 660+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 660+i)&& soLuong >= 99 && giayhdnm >=1 ){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1058+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày húy diệt namec + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    int nhanhd = 0;
                                    if (tl !=null){
                                        nhanhd= tl.quantity;
                                    }
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 656+i)&& soLuong >= 99 && nhanhd>=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1061+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần nhận húy diệt namec + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;              
                                case 5: // canel
                                  break;
                            }
                        }
                        else if (player.iDMark.getIndexMenu() == 6){ // action đổi dồ thiên sứ
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 654);
                            int soLuong = 0;
                            int aohdxd = 0;
                            if (tl != null){
                                aohdxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 654+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 654+i)&& soLuong >= 99 && aohdxd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1050+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo húy diệt xayda + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 655);
                            int soLuong = 0;
                            int quanhdxd = 0;
                            if (tl != null){
                                quanhdxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 655+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 655+i)&& soLuong >= 99 && quanhdxd >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1053+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần húy diệt xayda + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 661);
                            int soLuong = 0;
                            int ganghdxd = 0;
                            if (tl != null){
                                ganghdxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 661+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 661+i)&& soLuong >= 99 && ganghdxd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1056+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Găng húy diệt xayda + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 662);
                            int soLuong = 0;
                            int giayhdxd =0;
                            if (tl != null){
                                giayhdxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 662+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 662+i)&& soLuong >= 99 && giayhdxd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1059+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày húy diệt xayda + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    int nhanhd = 0;
                                    if (tl != null){
                                        nhanhd = tl.quantity;
                                    }
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 656+i)&& soLuong >= 99 && nhanhd >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1062+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần nhận húy diệt xayda + x99 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;              
                                case 5: // canel
                                  break;
                            }
                        } 
                    }
                }
            }
        };
    }
    
    
     private static Npc bucac1(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 42) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ngươi muốn gì ở ta\nĐổi bí kiếp lấy cải trang\n|4|lựa chọn 1 cải trang dành cho  mị nương\nlựa chọn 2 cải trang jaky chun\nlựa chọn 3 cải trang bunma\ncải trang có chỉ số ngẫu ngiên", "lựa chọn\n1", "lựa chọn\n2", "lựa chọn\n3", "đóng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 42) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 590);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 1) {
                                        this.npcChat(player, "Bạn không đủ 1 bí kiếp");
                                    } else if (player.inventory.gold < 0) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 860);
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(49, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(33,0));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(15) + 15));
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang");
                                    }
                                    break;
                                     case 1:
                                    Item honLinhThu1 = null;
                                    try {
                                        honLinhThu1 = InventoryServiceNew.gI().findItemBag(player, 590);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu1 == null || honLinhThu1.quantity < 1) {
                                        this.npcChat(player, "Bạn không đủ 1 bí kiếp");
                                    } else if (player.inventory.gold < 0) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu1, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 711);
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(49, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(33,0));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(15) + 15));
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang");
                                    }
                                    break;
                                     case 2:
                                    Item honLinhThu2 = null;
                                    try {
                                        honLinhThu2 = InventoryServiceNew.gI().findItemBag(player, 464);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu2 == null || honLinhThu2.quantity < 1) {
                                        this.npcChat(player, "Bạn không đủ 1 bí kiếp");
                                    } else if (player.inventory.gold < 0) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu2, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 593);
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(49, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(33,0));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(15) + 15));
                                      trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(5) + 5));
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang");
                                    }
                                    break;
                                   
                                }
                                }
                            }
                        }
            }
        };
    }  
    private static Npc DETU(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ngươi có muốn đổi đệ tử xịn không?\nngươi đang có:"+player.getSession().coinBar + " Vnd\n|7|tùy chọn 1:Đệ Bill[30k]:hợp thể tăng 30%\ntuỳ chọn 2:Đệ Whis[50k]:hợp thể tăng 40%\ntuỳ chọn 3:Đệ goku vô cực[150k]:hợp thể tăng 50%\ntuỳ chọn 4:Đệ cumber[300]: hợp thể tăng 60%", "tùy chọn\n1", "tùy chọn\n2", "tùy chọn\n3", "tùy chọn\n4", "đóng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                               case 0:
                                   if (player.getSession().coinBar < 30000) {
                                        this.npcChat(player, "Bạn không đủ 30000 vnd");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.getSession().coinBar -= 30000;
                                        Service.gI().sendMoney(player);
                                    PetService.gI().changeBerusPet(player); 
                                        InventoryServiceNew.gI().sendItemBags(player);
                                       Service.gI().sendThongBao(player, "Bạn vừa nhận được đệ tử");
                                    break;
                                    }
                                     case 1:
                                   if (player.getSession().coinBar < 50000) {
                                        this.npcChat(player, "Bạn không đủ 50000 vnd");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.getSession().coinBar -= 50000;
                                        Service.gI().sendMoney(player);
                                    PetService.gI().changeMabuPet(player); 
                                        InventoryServiceNew.gI().sendItemBags(player);
                                       Service.gI().sendThongBao(player, "Bạn vừa nhận được đệ tử");
                                    break;
                                    }
                                     case 2:
                                   if (player.getSession().coinBar < 150000) {
                                        this.npcChat(player, "Bạn không đủ 150000 vnd");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.getSession().coinBar -= 150000;
                                        Service.gI().sendMoney(player);
                                    PetService.gI().changePicPet(player);  
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        Service.gI().sendThongBao(player, "Bạn vừa nhận được đệ tử");
                                    break;
                                    }
                                    case 3:
                                           this.npcChat(player, "chức năng này đang cập nhập");
                                           break;
                                    }
                                }
                            }
                        }
            }
        };
    }  
     private static Npc bucac(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Nhân dịp open nro kami\n|7|đội ngũ chúng tôi cho ra mắt sự kiện này\n|4| chi tiết sự kiện\nngươi hãy đến map ở dưới để săn bos\nkhi săn người may mắn xẽ nhận được vật phẩm sự kiện\nhãy đem nó về cho ta xẽ đưa cho ngươi bí kíp\nx5 vật phẩm sự kiện = 1 bí kiếp\nngươi có thể đổi bí kíp ở vách núi aru\nx1 bí kiếp ngươi xẽ nhận được 1 cải trang yaddat\n hết bài", "Đến map\núp vpsk", "Đổi\nvpsk", "từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                               case 0:
                                  ChangeMapService.gI().changeMapBySpaceShip(player, 209, -1, 354);
                                    Service.getInstance().changeFlag(player, Util.nextInt(8));
                                    break;
                                     case 1:
                                   Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 695);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 5) {
                                        this.npcChat(player, "Bạn không đủ 5 võ ốc");
                                    } else if (player.inventory.gold < 0) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 5);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 590);
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang hè");
                                    }
                                    break;
                                    }
                                }
                            }
                        }
            }
        };
    }  
   private static Npc kyGui(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
           @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, 0, "Cửa hàng chúng tôi chuyên mua bán hàng hiệu, hàng độc, cảm ơn bạn đã ghé thăm.", "Hướng\ndẫn\nthêm", "Mua bán\nKý gửi", "Từ chối");
              }
            }

            @Override
            public void confirmMenu(Player pl, int select) {
              if (canOpenNpc(pl)) {
                    switch (select) {
                        case 0:
                           Service.getInstance().sendPopUpMultiLine(pl, tempId, avartar, "Cửa hàng chuyên nhận ký gửi mua bán vật phẩm\bChỉ với 5 hồng ngọc\bGiá trị ký gửi 10k-200Tr vàng hoặc 2-2k ngọc\bMột người bán, vạn người mua, mại dô, mại dô");
                            break;
                        case 1:
                            ShopKyGuiService.gI().openShopKyGui(pl);
                            break;

                    }
                }
            }
        };
    }

    private static Npc poTaGe(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 140) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đa vũ trụ song song \b|7|Con muốn gọi con trong đa vũ trụ \b|1|Với giá 500 tr vàng không?", "Gọi Boss\nNhân bản", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 140) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Boss oldBossClone = BossManager.gI().getBossById(Util.createIdBossClone((int) player.id));
                                    if (oldBossClone != null) {
                                        this.npcChat(player, "Nhà ngươi hãy tiêu diệt Boss lúc trước gọi ra đã, con boss đó đang ở khu " + oldBossClone.zone.zoneId);
                                    } else if (player.inventory.gold < 500_000_000) {
                                        this.npcChat(player, "Nhà ngươi không đủ 500 tr vàng ");
                                    } else {
                                        List<Skill> skillList = new ArrayList<>();
                                        for (byte i = 0; i < player.playerSkill.skills.size(); i++) {
                                            Skill skill = player.playerSkill.skills.get(i);
                                            if (skill.point > 0) {
                                                skillList.add(skill);
                                            }
                                        }
                                        int[][] skillTemp = new int[skillList.size()][3];
                                        for (byte i = 0; i < skillList.size(); i++) {
                                            Skill skill = skillList.get(i);
                                            if (skill.point > 0) {
                                                skillTemp[i][0] = skill.template.id;
                                                skillTemp[i][1] = skill.point;
                                                skillTemp[i][2] = skill.coolDown;
                                            }
                                        }
                                        BossData bossDataClone = new BossData(
                                                "Nhân Bản" + player.name,
                                                player.gender,
                                                new short[]{player.getHead(), player.getBody(), player.getLeg(), player.getFlagBag(), player.idAura, player.getEffFront()},
                                                player.nPoint.dame,
                                                new int[]{player.nPoint.hpMax},
                                                new int[]{140},
                                                skillTemp,
                                                new String[]{"|-2|Boss nhân bản đã xuất hiện rồi"}, //text chat 1
                                                new String[]{"|-1|Ta sẽ chiếm lấy thân xác của ngươi hahaha!"}, //text chat 2
                                                new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
                                                60
                                        );

                                        try {
                                            new NhanBan(Util.createIdBossClone((int) player.id), bossDataClone, player.zone);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        //trừ vàng khi gọi boss
                                        player.inventory.gold -= 200_000_000;
                                        Service.gI().sendMoney(player);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
    }

    private static Npc quyLaoKame(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (player.getSession().is_gift_box) {
//                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn ta giúp gì nào?", "Giải tán bang hội", "Nhận quà\nđền bù");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn bố giúp gì nào?","Kho báu dưới biển", "Giải tán bang hội", "Lãnh địa Bang Hội", "Hang Sói");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                if (player.clan != null) {
                                    if (player.clan.banDoKhoBau != null) {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_DBKB,
                                                "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
                                                        + player.clan.banDoKhoBau.level + "\nCon có muốn đi theo không?",
                                                "Đồng ý", "Từ chối");
                                    } else {

                                        this.createOtherMenu(player, ConstNpc.MENU_OPEN_DBKB,
                                                "Đây là bản đồ kho báu \nCác con cứ yên tâm lên đường\n"
                                                        + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                "Chọn\ncấp độ", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Không có bang đòi đi cái cc à ");
                                }
                                break;
                            case 1:
                                Clan clan = player.clan;
                                if (clan != null) {
                                    ClanMember cm = clan.getClanMember((int) player.id);
                                    if (cm != null) {
                                        if (clan.members.size() > 1) {
                                            Service.gI().sendThongBao(player, "Bang phải còn một người");
                                            break;
                                        }
                                        if (!clan.isLeader(player)) {
                                            Service.gI().sendThongBao(player, "Phải là bảng chủ");
                                            break;
                                        }
//                                        
                                        NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1, "Con có chắc chắn muốn giải tán bang hội không? Ta cho con 2 lựa chọn...",
                                                "Đồng ý!", "Từ chối!");
                                    }
                                    break;
                                }
                                Service.gI().sendThongBao(player, "Có bang hội đâu ba!!!");
                                break;
                            case 2:
                                if (player.getSession().player.nPoint.power >= 100000000000L) {

                                    ChangeMapService.gI().changeMapBySpaceShip(player, 153, -1, 432);
                                } else {
                                    this.npcChat(player, "Mày chưa đủ 100 tỏi sức mạnh để vào");
                                }
                                break; // qua lanh dia
                            case 3:
                                this.createOtherMenu(player, ConstNpc.HANG_SOI,
                                        "Hang sói là nơi xuất hiện của 3 loài Sói đến từ Vũ trụ khác\n"
                                        + "Mang tới Trái đất nhiều vật phẩm giá trị\n"
                                        + "Con có muốn vào tham chiến không?", "Đồng ý", "Từ chối");
                                break;
                                }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.HANG_SOI) {
                        switch (select) {
                            case 0:
                                if (player.nPoint.power >= 80000000000L) {
                                    Item bandosoi = InventoryServiceNew.gI().findItemBag(player, 1310);
                                    if (bandosoi != null && bandosoi.quantity > 0) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 192, -1, 144);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, bandosoi, 1);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        break;
                                    } else {
                                        Service.gI().sendThongBao(player, "Cần có 1 Bản đồ Sói");
                                    }
                                } else {
                                    Service.gI().sendThongBao(player, "Yêu cầu sức mạnh đạt 80 tỷ");
                                }
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_DBKB) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    ChangeMapService.gI().goToDBKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_DBKB) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    Input.gI().createFormChooseLevelBDKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_BDKB) {
                        switch (select) {
                            case 0:
                                BanDoKhoBauService.gI().openBanDoKhoBau(player, Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                break;
                        }
                    }
                }
            }
        };
    }
    
    public static Npc truongLaoGuru(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
             @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc vuaVegeta(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc ongGohan_ongMoori_ongParagus(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    String checkvnd;
                    int thoivang;
                    if (player.vnd >= 0 && player.vnd < 500000) {
                        checkvnd = "Tân Thủ";
                        thoivang = 15;
                    } else if (player.vnd > 500000 && player.vnd < 1000000) {
                        checkvnd = "VIP";
                        thoivang = 50;
                    } else {
                        checkvnd = "SVIP";
                        thoivang = 80;
                    }
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Cố Gắng Có Làm Mới Có Ăn Con, đừng lo lắng cho ta.\n"
                                        .replaceAll("%1", player.gender == ConstPlayer.TRAI_DAT ? "Quy lão Kamê"
                                                : player.gender == ConstPlayer.NAMEC ? "Trưởng lão Guru" : "Vua Vegeta") + "Ta đang giữ tiền tiết kiệm của con\n|1| Hiện tại con đang có: " + player.getSession().goldBar + " Thỏi vàng"
                                + "\n\n|7| Cấp VIP được tính như sau:"
                                + "\n|2| Quy đổi tiền <500.000đ = Tân Thủ (Nhận 15 Thỏi vàng/Ngày)"
                                + "\n Quy đổi tiền >500.000đ và <1.000.000đ = VIP (Nhận 50 Thỏi vàng/Ngày)"
                                + "\n Quy đổi tiền >1.000.000đ = SVIP (Nhận 80 Thỏi vàng/Ngày)"
                                + "\n|3|TỔNG QUY ĐỔI : " + Util.format(player.vnd) + "đ"
                                + "\n\n|7|Cấp VIP hiện tại của bạn là : " + checkvnd + "\n|1| Điểm danh hằng ngày sẽ nhận được " + thoivang + " Thỏi vàng",
                                "Đổi Mật Khẩu", "Nhận 20tr Ngọc xanh", "Nhận\nVàng", "Giftcode", "Điểm danh\nngày", "Đóng");

                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                Input.gI().createFormChangePassword(player);
                                break;
                            case 1:
                                if (player.inventory.gem >= 200000000) {
                                    this.npcChat(player, "Tham Lam");
                                    break;
                                }
                                player.inventory.gem = 200000000;
                                Service.getInstance().sendMoney(player);
                                Service.getInstance().sendThongBao(player, "|1|Bạn vừa nhận được 20tr Ngọc xanh");
                                break;
                            case 2:
                                if (Maintenance.isRuning) {
                                    break;
                                }
                                if (player.getSession().goldBar > 0) {
                                    if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                        int quantity = player.getSession().goldBar;
                                        if (PlayerDAO.subGoldBar(player, player.getSession().goldBar)) {
                                            Item goldBar = ItemService.gI().createNewItem((short) 457, quantity);
                                            InventoryServiceNew.gI().addItemBag(player, goldBar);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                            this.npcChat(player, "Ta đã gửi " + quantity + " thỏi vàng vào hành trang của con\n con hãy kiểm tra ");
                                        } else {
                                            this.npcChat(player, "Lỗi vui lòng báo admin...");
                                        }
                                    } else {
                                        this.npcChat(player, "Hãy chừa cho ta 1 ô trống");
                                    }
                                } else {
                                    this.npcChat(player, "Con đang không có thỏi vàng hãy ib AD để được buff !!!");
                                }
                                break;

                            case 3:
                                Input.gI().createFormGiftCode(player);
                                break;
                            case 4:
                                if (player.diemdanh == 0) {
                                    int thoivang1;
                                    if (player.vnd >= 0 && player.vnd < 500000) {
                                        thoivang1 = 15;
                                    } else if (player.vnd > 500000 && player.vnd < 1000000) {
                                        thoivang1 = 50;
                                    } else {
                                        thoivang1 = 80;
                                    }
                                    Item thoivang = ItemService.gI().createNewItem((short) 457, thoivang1);
                                    InventoryServiceNew.gI().addItemBag(player, thoivang);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    player.diemdanh = System.currentTimeMillis();
                                    Service.getInstance().sendThongBao(player, "|7|Bạn vừa nhận được " + thoivang1 + " Thỏi vàng");
                                } else {
                                    this.npcChat(player, "Hôm nay đã nhận rồi mà !!!");
                                }
                                break;
                        }
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.QUA_TAN_THU) {
                    switch (select) {
                        case 0:
                            if (true) {
                                player.inventory.gem = 100000;
                                Service.getInstance().sendMoney(player);
                                Service.getInstance().sendThongBao(player, "Bạn vừa nhận được 100K ngọc xanh");
                                player.gift.gemTanThu = true;
                            } else {
                                this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Con đã nhận phần quà này rồi mà",
                                        "Đóng");
                            }
                            break;
//                            
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHAN_THUONG) {
                    switch (select) {
                        case 0:
                            ShopServiceNew.gI().opendShop(player, "ITEMS_REWARD", true);
                            break;
//                            
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.NAP_THE) {
                    Input.gI().createFormNapThe(player, (byte) select);
                }
            }
        };
    }

    public static Npc bulmaQK(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Cậu cần trang bị gì cứ đến chỗ tôi nhé", "Cửa\nhàng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.TRAI_DAT) {
                                    ShopServiceNew.gI().opendShop(player, "BUNMA", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Xin lỗi cưng, chị chỉ bán đồ cho người Trái Đất", "Đóng");
                                }
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc dende(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (player.idNRNM != -1) {
                            if (player.zone.map.mapId == 7) {
                                this.createOtherMenu(player, 1, "Ồ, ngọc rồng namếc, bạn thật là may mắn\nnếu tìm đủ 7 viên sẽ được Rồng Thiêng Namếc ban cho điều ước", "Hướng\ndẫn\nGọi Rồng", "Gọi rồng", "Từ chối");
                            }
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                    "Anh cần trang bị gì cứ đến chỗ em nhé", "Cửa\nhàng");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.NAMEC) {
                                    ShopServiceNew.gI().opendShop(player, "DENDE", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Xin lỗi mày, tao chỉ bán đồ cho dân tộc Namếc", "Đóng");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 1) {
                        if (player.zone.map.mapId == 7 && player.idNRNM != -1) {
                            if (player.idNRNM == 353) {
                                NgocRongNamecService.gI().tOpenNrNamec = System.currentTimeMillis() + 86400000;
                                NgocRongNamecService.gI().firstNrNamec = true;
                                NgocRongNamecService.gI().timeNrNamec = 0;
                                NgocRongNamecService.gI().doneDragonNamec();
                                NgocRongNamecService.gI().initNgocRongNamec((byte) 1);
                                NgocRongNamecService.gI().reInitNrNamec((long) 86399000);
 //                               SummonDragon.gI().summonNamec(player);
                            } else {
                                Service.gI().sendThongBao(player, "Anh phải có viên ngọc rồng Namếc 1 sao");
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc appule(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi cần trang bị gì cứ đến chỗ ta nhé", "Cửa\nhàng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.XAYDA) {
                                    ShopServiceNew.gI().opendShop(player, "APPULE", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Về hành tinh hạ đẳng của ngươi mà mua đồ cùi nhé. Tại đây ta chỉ bán đồ cho người Xayda thôi", "Đóng");
                                }
                                break;
                        }
                    }
                }
            }
        };
    }

    
    public static Npc drDrief(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (this.mapId == 84) {
                        this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây. Cậu muốn đi đâu?",
                                pl.gender == ConstPlayer.TRAI_DAT ? "Đến\nTrái Đất" : pl.gender == ConstPlayer.NAMEC ? "Đến\nNamếc" : "Đến\nXayda");
                    } else if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
                        if (pl.playerTask.taskMain.id == 7) {
                            NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
                                    + "Chắc bây giờ nó đang sợ hãi lắm rồi");
                        } else {
                            this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                    "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây. Cậu muốn đi đâu?",
                                    "Đến\nNamếc", "Đến\nXayda", "Siêu thị");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 84) {
                        ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 24, -1, -1);
                    } else if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 25, -1, -1);
                                break;
                            case 1:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 26, -1, -1);
                                break;
                            case 2:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc cargo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
                        if (pl.playerTask.taskMain.id == 7) {
                            NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
                                    + "Chắc bây giờ nó đang sợ hãi lắm rồi");
                        } else {
                            this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                    "Tàu Điện của tao sẽ đưa chúng mày đi chơi gái chỉ trong 3 giây. MÀY muốn đi đâu?",
                                    "Đến\nTrái Đất", "Đến\nXayda", "Siêu BÍM");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 24, -1, -1);
                                break;
                            case 1:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 26, -1, -1);
                                break;
                            case 2:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc cui(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            private final int COST_FIND_BOSS = 50000000;

            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
                        if (pl.playerTask.taskMain.id == 7) {
                            NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
                                    + "Chắc bây giờ nó đang sợ hãi lắm rồi");
                        } else {
                            if (this.mapId == 19) {
                                int taskId = TaskService.gI().getIdTask(pl);
                                switch (taskId) {
                                    case ConstTask.TASK_19_0:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_KUKU,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nKuku\n(" + Util.numberToMoney(COST_FIND_BOSS) + " vàng)", "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    case ConstTask.TASK_19_1:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_MAP_DAU_DINH,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nMập đầu đinh\n(" + Util.numberToMoney(COST_FIND_BOSS) + " vàng)", "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    case ConstTask.TASK_19_2:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_RAMBO,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nRambo\n(" + Util.numberToMoney(COST_FIND_BOSS) + " vàng)", "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    default:
                                        this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến Cold", "Đến\nNappa", "Từ chối");

                                        break;
                                }
                            } else if (this.mapId == 68) {
                                this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                        "Ngươi có muốn về Thành Phố Vegeta", "Có", "Không");
                            }
                                else if (this.mapId == 192) {
                            this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                    "Ngươi cần hỗ trợ gì?", "Về nhà", "Từ chối");
                            } else {
                                this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                        "Tàu vũ trụ Xayda của bố sử dụng công nghệ mới nhất, "
                                        + "có thể đưa các con đi đi bất kỳ đâu, chỉ cần trả tiền là được.",
                                        "Đến\nTrái Đất", "Đến\nNamếc", "Siêu thị");
                                
                            }
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 26) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 24, -1, -1);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 25, -1, -1);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                    break;
                            }
                        }
                    }
                    if (this.mapId == 192) {
                        if (player.iDMark.getIndexMenu() == ConstNpc.BASE_MENU) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, -1);
                                    break;
                            }
                        }

                    }
                    if (this.mapId == 19) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                              case 0:
                                  if (TaskService.gI().getIdTask(player) < ConstTask.TASK_29_0) {
                                        Service.gI().sendThongBao(player, "Hãy làm nhiệm vụ trước");
                                        return;
                                    } else {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                        break;
                                    }
                                case 1:
                                    if (TaskService.gI().getIdTask(player) < ConstTask.TASK_18_0) {
                                        Service.gI().sendThongBao(player, "Hãy làm nhiệm vụ trước");
                                        return;
                                    } else {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                        break;
                                    }
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_KUKU) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.KUKU);
                                    if (boss != null && !boss.isDie()) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId, boss.zone.zoneId);
                                            if (z != null && z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x, boss.location.y);
                                                Service.gI().sendMoney(player);
                                            } else {
                                                Service.gI().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.gI().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold) + " vàng");
                                        }
                                        break;
                                    }
                                    Service.gI().sendThongBao(player, "Chết mẹ rồi...");
                                    break;
                                case 1:
                                    if (TaskService.gI().getIdTask(player) < ConstTask.TASK_29_0) {
                                        Service.gI().sendThongBao(player, "Hãy làm nhiệm vụ trước");
                                        return;
                                    } else {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                        break;
                                    }
                                case 2:
                                    if (TaskService.gI().getIdTask(player) < ConstTask.TASK_18_0) {
                                        Service.gI().sendThongBao(player, "Hãy làm nhiệm vụ trước");
                                        return;
                                    } else {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                        break;
                                    }
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_MAP_DAU_DINH) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.MAP_DAU_DINH);
                                    if (boss != null && !boss.isDie()) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId, boss.zone.zoneId);
                                            if (z != null && z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x, boss.location.y);
                                                Service.gI().sendMoney(player);
                                            } else {
                                                Service.gI().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.gI().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold) + " vàng");
                                        }
                                        break;
                                    }
                                    Service.gI().sendThongBao(player, "Chết mẹ rồi...");
                                    break;
                                case 1:
                                  if (TaskService.gI().getIdTask(player) < ConstTask.TASK_29_0) {
                                        Service.gI().sendThongBao(player, "Hãy làm nhiệm vụ trước");
                                        return;
                                    } else {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                        break;
                                    }
                                case 2:
                                    if (TaskService.gI().getIdTask(player) < ConstTask.TASK_18_0) {
                                        Service.gI().sendThongBao(player, "Hãy làm nhiệm vụ trước");
                                        return;
                                    } else {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                        break;
                                    }
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_RAMBO) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.RAMBO);
                                    if (boss != null && !boss.isDie()) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId, boss.zone.zoneId);
                                            if (z != null && z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x, boss.location.y);
                                                Service.gI().sendMoney(player);
                                            } else {
                                                Service.gI().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.gI().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold) + " vàng");
                                        }
                                        break;
                                    }
                                    Service.gI().sendThongBao(player, "Chết mẹ rồi...");
                                    break;
                                case 1:
                                     if (TaskService.gI().getIdTask(player) < ConstTask.TASK_22_0) {
                                        Service.gI().sendThongBao(player, "Hãy làm nhiệm vụ trước");
                                        return;
                                    } else {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                        break;
                                    }
                                case 2:
                                    if (TaskService.gI().getIdTask(player) < ConstTask.TASK_18_0) {
                                        Service.gI().sendThongBao(player, "Hãy làm nhiệm vụ trước");
                                        return;
                                    } else {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                        break;
                                    }
                            }
                        }
                    }
                    if (this.mapId == 68) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 19, -1, 1100);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
    private static Npc noibanh(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 0) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn muốn nấu bánh à, bạn cần:\bx999 Bột Mì + x999 Đậu Xanh + x999 Trứng vịt muối + x999 Gà quay nguyên con",
                                "Nấu bánh",
                                "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 0) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Item honLinhThu = null;
                                    Item honLinhThu1 = null;
                                    Item honLinhThu2 = null;
                                    Item honLinhThu3 = null;
                                    int id = Util.nextInt(0, 100);
                                    int[] rdhongden = new int[]{467,468,469,470,471};
                                    int[] rdbanh = new int[]{465,466,472};
                                    int randomld = new Random().nextInt(rdhongden.length);
                                    int randombanh = new Random().nextInt(rdbanh.length);
                                    Item ld = ItemService.gI().createNewItem((short) rdhongden[randomld]);
                                    Item banh = ItemService.gI().createNewItem((short) rdbanh[randombanh]);
                                    ///    
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 886);
                                        honLinhThu1 = InventoryServiceNew.gI().findItemBag(player, 887);
                                        honLinhThu2 = InventoryServiceNew.gI().findItemBag(player, 888);
                                        honLinhThu3 = InventoryServiceNew.gI().findItemBag(player, 889);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu1 == null || honLinhThu2 == null || honLinhThu3 == null || honLinhThu.quantity < 999 || honLinhThu1.quantity < 999 || honLinhThu2.quantity <999 || honLinhThu3.quantity < 999) {
                                        this.npcChat(player, "Bạn không đủ nguyên liệu");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 999);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu1, 999);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu2, 999);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu3, 999);
                                        ///                                     
                                        if (id <= 50){           
                                            ld.itemOptions.add(new Item.ItemOption(50, Util.nextInt(25, 30)));
                                            ld.itemOptions.add(new Item.ItemOption(77, Util.nextInt(25, 30)));
                                            ld.itemOptions.add(new Item.ItemOption(103, Util.nextInt(25, 30))); 
                                            InventoryServiceNew.gI().addItemBag(player, ld);
                                            this.npcChat(player, "Bạn nhận được " + ld.template.name);
                                        } else {                      
                                            InventoryServiceNew.gI().addItemBag(player, banh);
                                            this.npcChat(player, "Bạn nhận được " + banh.template.name);          
                                        }///                                        
                                        InventoryServiceNew.gI().sendItemBags(player);                                       
                                    }
                                    break;
                                
                                
                                }
                            }
                        }
                    }
                }
            }
        };
    }
    public static Npc santa(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Xin chào, CHỖ TAO CHỈ BÁN MA TÚY ĐÁ CHO DÂN CHƠI?",
                            "Cửa Hàng","Tiệm Tỉa LÔNG","SHOP\nVIP");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5 || this.mapId == 13 || this.mapId == 20) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: //shop
                                    ShopServiceNew.gI().opendShop(player, "SANTA", false);
                                    break;                                
                                case 1:
                        //            this.createOtherMenu(player, ConstNpc.QUY_DOI, "|7|Số tiền của bạn còn : " + player.getSession().coinBar + "\n"
                        //                   + "Tỉ lệ quy đổi là x2\n" + "Muốn quy đổi không", "Quy đổi\n Thỏi vàng", "không");
                                    break;

                                case 2: //shop
                                    ShopServiceNew.gI().opendShop(player, "SANTA_RUBY", false);
                                    break;
                                
                                case 3:
                                     ShopServiceNew.gI().opendShop(player, "SANTA", false);

                                    break;    

                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.NAP_THE) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, ConstNpc.VIETTEL, "Khó Đã Có AD", "Cái Nịt",
                                            "Cái Nịt");
                                    break;
                                case 1:
                                    this.createOtherMenu(player, ConstNpc.MOBIFONE, "Khó Đã Có AD", "Cái Nịt",
                                            "Cái Nịt");
                                    break;
                                case 2:
                                    this.createOtherMenu(player, ConstNpc.VINAPHONE, "Khó Đã Có AD", "Cái Nịt",
                                            "Cái Nịt");
                                    break;
                            }

                        } else if (player.iDMark.getIndexMenu() == ConstNpc.VINAPHONE) {
                            switch (select) {
                                case 0:
                                    this.npcChat(player, "Hệ thống đang bảo trì!");
                                case 1:
                                    Input.gI().createFormNapThe(player, "VINAPHONE", "20000");
                                    break;
                                case 2:
                                    Input.gI().createFormNapThe(player, "VINAPHONE", "50000");
                                    break;
                                case 3:
                                    Input.gI().createFormNapThe(player, "VINAPHONE", "100000");
                                    break;
                                case 4:
                                    Input.gI().createFormNapThe(player, "VINAPHONE", "200000");
                                    break;
                                case 5:
                                    Input.gI().createFormNapThe(player, "VINAPHONE", "500000");
                                    break;
                                case 6:
                                    Input.gI().createFormNapThe(player, "VINAPHONE", "1000000");
                                    break;
                            }

                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MOBIFONE) {
                            switch (select) {
                                case 0:
                                    this.npcChat(player, "Hệ thống đang bảo trì!");
                                case 1:
                                    Input.gI().createFormNapThe(player, "MOBIFONE", "20000");
                                    break;
                                case 2:
                                    Input.gI().createFormNapThe(player, "MOBIFONE", "50000");
                                    break;
                                case 3:
                                    Input.gI().createFormNapThe(player, "MOBIFONE", "100000");
                                    break;
                                case 4:
                                    Input.gI().createFormNapThe(player, "MOBIFONE", "200000");
                                    break;
                                case 5:
                                    Input.gI().createFormNapThe(player, "MOBIFONE", "500000");
                                    break;
                                case 6:
                                    Input.gI().createFormNapThe(player, "MOBIFONE", "1000000");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.VIETTEL) {
                            switch (select) {
                                case 0:
                                    this.npcChat(player, "Hệ thống đang bảo trì!");
                                case 1:
                                    Input.gI().createFormNapThe(player, "VIETTEL", "20000");
                                    break;
                                case 2:
                                    Input.gI().createFormNapThe(player, "VIETTEL", "50000");
                                    break;
                                case 3:
                                    Input.gI().createFormNapThe(player, "VIETTEL", "100000");
                                    break;
                                case 4:
                                    Input.gI().createFormNapThe(player, "VIETTEL", "200000");
                                    break;
                                case 5:
                                    Input.gI().createFormNapThe(player, "VIETTEL", "500000");
                                    break;
                                case 6:
                                    Input.gI().createFormNapThe(player, "VIETTEL", "1000000");
                                    break;
                            }

                        } else if (player.iDMark.getIndexMenu() == ConstNpc.QUY_DOI) {
                            switch (select) {
                                case 0:
                                    Input.gI().createFormQDTV(player);
                                    break;
                                case 1:
                                    Input.gI().createFormQDTV(player);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

     public static Npc thodaika(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn Đã Bị Bư Han Nhuốt Hãy Tìm nó để tiêu diệt<", "Tới Bụng\nBư", "Từ chối");
                     if (player.getSession().player.nPoint.power >= 8000000L) {

                                    ChangeMapService.gI().changeMapBySpaceShip(player, 128, -1, 432);
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 8tr sức mạnh để vào");
                                }
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn Đã Bị Bư Han Nhuốt Hãy Tìm nó để tiêu diệt?", "Quay về", "Từ chối");
                    } else {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.BASE_MENU:
                            if (this.mapId == 128) {
                                if (select == 0) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 870);
                                }
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc uron(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    ShopServiceNew.gI().opendShop(pl, "URON", false);
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc baHatMit(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5 || this.mapId == 13) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Ép sao\ntrang bị",
                                "Pha lê\nhóa\ntrang bị",
                                "Pháp sư hoá\ntrang bị",
                                "Tẩy\npháp sư",
                                "Chuyển hóa\nđồ Hủy diệt",
                                "Chuyển hóa\nSKH",
                                "Gia Hạn\nvật phẩm");
                    } else if (this.mapId == 121) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Về đảo\nrùa");

                    } else {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Cửa hàng\nBùa", "Nâng cấp\nVật phẩm",
                                "Nhập\nNgọc Rồng",
                                "Nâng cấp\nBông tai\nPorata",
                                "Mở chỉ số\n bông tai 2",
                                "Mở chỉ số\n bông tai 3",
                                "Mở chỉ số\n bông tai 4");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5 || this.mapId == 13) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.EP_SAO_TRANG_BI);
                                    break;
                                case 1:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.PHA_LE_HOA_TRANG_BI);
                                    break;
                                case 2:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.PHAP_SU_HOA);
                                    break;
                                case 3:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.TAY_PHAP_SU);
                                    break;
                                case 4:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHUYEN_HOA_DO_HUY_DIET);
                                    break;
                                case 5:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.RANDOM_SKH);
                                    break;
                                case 6:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.GIA_HAN_VAT_PHAM);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.EP_SAO_TRANG_BI:
                                case CombineServiceNew.PHA_LE_HOA_TRANG_BI:
                                case CombineServiceNew.CHUYEN_HOA_TRANG_BI:
                                case CombineServiceNew.PHAP_SU_HOA:
                                case CombineServiceNew.TAY_PHAP_SU:
                                case CombineServiceNew.CHUYEN_HOA_DO_HUY_DIET:
                                case CombineServiceNew.RANDOM_SKH:
                                case CombineServiceNew.GIA_HAN_VAT_PHAM:
                                    if (select == 0) {
                                        CombineServiceNew.gI().startCombine(player);
                                    }
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_CHUYEN_HOA_DO_HUY_DIET) {
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_RANDOM_SKH) {
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                        }
                    } else if (this.mapId == 112) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 1156);
                                    break;
                            }
                        }
                    } else if (this.mapId == 42 || this.mapId == 43 || this.mapId == 44 || this.mapId == 84) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: //shop bùa
                                    createOtherMenu(player, ConstNpc.MENU_OPTION_SHOP_BUA,
                                            "Bùa của ta rất lợi hại, nhìn ngươi yếu đuối thế này, chắc muốn mua bùa để "
                                            + "mạnh mẽ à, mua không ta bán cho, xài rồi lại thích cho mà xem.",
                                            "Bùa\n1 giờ", "Bùa\n8 giờ", "Bùa\n1 tháng", "Đóng");
                                    break;
                                case 1://nâng cấp vật phẩm
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_VAT_PHAM);
                                    break;
                                case 2: //nâng cấp bông tai
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_BONG_TAI);
                                    break;
                                case 3: //
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.MO_CHI_SO_BONG_TAI);
                                    break;
                                case 4://nhập ngọc rồng
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NHAP_NGOC_RONG);
                                    break;
                                case 5:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_DO_TS);
                                    break;
                                case 6://nâng cấp linh thú
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_LINH_THU);
                                    //Service.gI().sendThongBao(player, "MO MENU DAP LT");
                                    break;
                                case 7: //phân rã đồ thần linh
                                  CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.PHAN_RA_DO_THAN_LINH);

                                   break;            
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_SHOP_BUA) {
                            switch (select) {
                                case 0:
                                    ShopServiceNew.gI().opendShop(player, "BUA_1H", true);
                                    break;
                                case 1:
                                    ShopServiceNew.gI().opendShop(player, "BUA_8H", true);
                                    break;
                                case 2:
                                    ShopServiceNew.gI().opendShop(player, "BUA_1M", true);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.NANG_CAP_VAT_PHAM:
                                case CombineServiceNew.NANG_CAP_BONG_TAI:
                                case CombineServiceNew.MO_CHI_SO_BONG_TAI:
                                case CombineServiceNew.NHAP_NGOC_RONG:            
                                case CombineServiceNew.NANG_CAP_DO_TS:
                                case CombineServiceNew.NANG_CAP_SKH_VIP:

                                    if (select == 0) {
                                        CombineServiceNew.gI().startCombine(player);
                                    }
                                    break;
                            }
                       } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHAN_RA_DO_THAN_LINH) {
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_NANG_CAP_DO_TS) {
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                            
                        }
                        else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_NANG_CAP_LINH_THU) {
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                            
                        }
                    }
                }
            }
        };
    }

    public static Npc ruongDo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    InventoryServiceNew.gI().sendItemBox(player);
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc duongtank(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (mapId == 0) {
                        this.createOtherMenu(player, 0, "Ngũ Hành chảo x2 Tnsm\nHỗ trợ cho Ae Từ\b|1|Dưới 1tr5 SM dến 16 Tỷ SM quá 16 tỷ mà bay ra thì cút luôn cấm vô lại?", "OK", "Oéo");
                    }
                    if (mapId == 123) {
                        this.createOtherMenu(player, 0, "Mày Có Muốn Về Với Mẹ Không?", "OK", "Từ chối");

                    }
                    if (mapId == 122) {
                        this.createOtherMenu(player, 0, "Xia xia thua phùa\b|7|Thí chủ đang có: " + player.NguHanhSonPoint + " điểm ngũ hành sơn\b|1|Thí chủ muốn đổi cải trang x4 chưởng ko?", "Âu kê", "Top Ngu Hanh Son", "No");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (select) {
                        case 0:
                            if (mapId == 0) {
                                if (player.nPoint.power < 1500000 || player.nPoint.power >= 16000000000L) {
                                    Service.gI().sendThongBao(player, "Sức mạnh bạn không phù hợp để qua map!");
                                    return;
                                }
                                ChangeMapService.gI().changeMapInYard(player, 123, -1, 174);
                            }
                            if (mapId == 123) {
                                ChangeMapService.gI().changeMapInYard(player, 0, -1, 469);
                            }
                            if (mapId == 122) {
                                if (select == 0) {
                                    if (player.NguHanhSonPoint >= 500) {
                                        player.NguHanhSonPoint -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (711));
                                        item.itemOptions.add(new Item.ItemOption(49, 80));
                                        item.itemOptions.add(new Item.ItemOption(77, 80));
                                        item.itemOptions.add(new Item.ItemOption(103, 50));
                                        item.itemOptions.add(new Item.ItemOption(207, 50));
                                        item.itemOptions.add(new Item.ItemOption(33, 50));
//                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Vật Phẩm Thành Công !");
                                    } else {
                                        Service.gI().sendThongBao(player, "Không đủ điểm, bạn còn " + (500 - player.pointPvp) + " điểm nữa");
                                    }
                                    break;
                                }
                                if (select == 1) {
                                    Service.gI().showListTop(player, Manager.topNHS);
                                }
                            }
                            break;
                    }
                }
            }
        };
    }
     private static Npc whis(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
           @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 154) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "\b|7|Ngươi Muốn Gì Ở Ta?\b|7|", "Học Tuyệt Kỹ", "Nâng Cấp Tuyệt Kỹ\nTrái Đất","Nâng Cấp Tuyệt Kỹ\nNamec","Nâng Cấp Tuyệt Kỹ\nXayda","Không");
                    }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 154) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                            case 0:
                                    this.createOtherMenu(player, 1,
                                       "\b|7|Chọn Hành Tinh Của Ngươi",  "Trái Đất", "Namec", "Xayda" ,"Không");
                                    break;
                            case 1:
                                    this.createOtherMenu(player, 2,
                                       "\b|7|Chọn Lv Skill",  "lv2", "lv3", "lv4" ,"lv5","lv6","lv7","lv8","lv9","Không");
                                    break;
                            case 2:
                                    this.createOtherMenu(player, 3,
                                       "\b|7|Chọn Lv Skill",  "lv2", "lv3", "lv4" ,"lv5","lv6","lv7","lv8","lv9","Không");                                  
                                    break;
                            case 3:
                                    this.createOtherMenu(player, 4,
                                       "\b|7|Chọn Lv Skill",  "lv2", "lv3", "lv4" ,"lv5","lv6","lv7","lv8","lv9","Không");                                  
                                    break;
                            case 4:
                                    break;
                                }
                            }
                            else if (player.iDMark.getIndexMenu() == 1){ // action đổi dồ húy diệt
                            switch (select){
                            case 0: // trade
                                 Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 1178);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 99) {
                                        this.npcChat(player, "Bạn cần có 99 bí kiếp tuyệt kỹ");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 99);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1175);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame ");
                                    }
                                    break;
                                case 1: // trade
                                    Item nm = null;
                                    try {
                                        nm = InventoryServiceNew.gI().findItemBag(player, 1178);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (nm == null || nm.quantity < 99) {
                                        this.npcChat(player, "Bạn cần có 99 bí kiếp tuyệt kỹ");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, nm, 99);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1176);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba ");
                                    }
                                    break;
                                case 2: // trade
                                    Item xd = null;
                                    try {
                                        xd = InventoryServiceNew.gI().findItemBag(player, 1178);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (xd == null || xd.quantity < 99) {
                                        this.npcChat(player, "Bạn cần có 99 bí kiếp tuyệt kỹ");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, xd, 99);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1177);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic ");
                                    }
                                    break;
                                 case 3: // thôi khỏi                                   
                                    break;
                                   
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 2){ // nâng skil trái đất
                            switch (select){
                                case 0: // trade
                               Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 1175);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1179);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame Lv2 ");
                                    }
                                    break;
                                case 1: // trade                                    
                               Item lv3 = null;
                                    try {
                                        lv3 = InventoryServiceNew.gI().findItemBag(player, 1179);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv3 == null || lv3.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv3, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1182);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame Lv3 ");
                                    }
                                    break;
                                case 2: // trade
                                    Item lv4 = null;
                                    try {
                                        lv4 = InventoryServiceNew.gI().findItemBag(player, 1182);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv4 == null || lv4.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv4, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1185);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame Lv4 ");
                                    }
                                    break;
                                 case 3: // trade
                                    Item lv5 = null;
                                    try {
                                        lv5 = InventoryServiceNew.gI().findItemBag(player, 1185);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv5 == null || lv5.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv5, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1188);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame Lv5 ");
                                    }
                                    break;
                                    case 4: // trade
                                    Item lv6 = null;
                                    try {
                                        lv6 = InventoryServiceNew.gI().findItemBag(player, 1188);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv6 == null || lv6.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv6, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1191);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame Lv6 ");
                                    }
                                    break;
                                case 5: // canel
                                 Item lv7 = null;
                                    try {
                                        lv7 = InventoryServiceNew.gI().findItemBag(player, 1191);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv7 == null || lv7.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv7, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1194);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame Lv7 ");
                                    }
                                    break;
                                 case 6: // canel
                                 Item lv8 = null;
                                    try {
                                        lv8 = InventoryServiceNew.gI().findItemBag(player, 1194);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv8 == null || lv8.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv8, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1197);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame Lv8 ");
                                    }
                                    break;
                                case 7: // canel
                                 Item lv9 = null;
                                    try {
                                        lv9 = InventoryServiceNew.gI().findItemBag(player, 1197);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv9 == null || lv9.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv9, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1200);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Super Kame Lv9 ");
                                    }
                                    break;    
                                 case 8: // canel                                 
                                    break;    
                            }
                        }
                        else if (player.iDMark.getIndexMenu() == 3){ // nâng skill namec
                            switch (select){
                                case 0: // trade
                               Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 1176);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1180);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba Lv2 ");
                                    }
                                    break;
                                case 1: // trade                                    
                               Item lv3 = null;
                                    try {
                                        lv3 = InventoryServiceNew.gI().findItemBag(player, 1180);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv3 == null || lv3.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv3, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1183);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba Lv3 ");
                                    }
                                    break;
                                case 2: // trade
                                    Item lv4 = null;
                                    try {
                                        lv4 = InventoryServiceNew.gI().findItemBag(player, 1183);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv4 == null || lv4.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv4, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1186);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba Lv4 ");
                                    }
                                    break;
                                 case 3: // trade
                                    Item lv5 = null;
                                    try {
                                        lv5 = InventoryServiceNew.gI().findItemBag(player, 1186);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv5 == null || lv5.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv5, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1189);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba Lv5 ");
                                    }
                                    break;
                                    case 4: // trade
                                    Item lv6 = null;
                                    try {
                                        lv6 = InventoryServiceNew.gI().findItemBag(player, 1189);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv6 == null || lv6.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv6, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1192);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba Lv6 ");
                                    }
                                    break;
                                case 5: // canel
                                 Item lv7 = null;
                                    try {
                                        lv7 = InventoryServiceNew.gI().findItemBag(player, 1192);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv7 == null || lv7.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv7, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1195);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba Lv7 ");
                                    }
                                    break;
                                 case 6: // canel
                                 Item lv8 = null;
                                    try {
                                        lv8 = InventoryServiceNew.gI().findItemBag(player, 1195);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv8 == null || lv8.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv8, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1198);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba Lv8 ");
                                    }
                                    break;
                                case 7: // canel
                                 Item lv9 = null;
                                    try {
                                        lv9 = InventoryServiceNew.gI().findItemBag(player, 1198);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv9 == null || lv9.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv9, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1201);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Ma Phong Ba Lv9 ");
                                    }
                                    break;     
                                case 8: // canel
                                  break;
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 4){ // nâng cấp skil xd
                            switch (select){
                                case 0: // trade
                               Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 1177);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1181);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic Lv2 ");
                                    }
                                    break;
                                case 1: // trade                                    
                               Item lv3 = null;
                                    try {
                                        lv3 = InventoryServiceNew.gI().findItemBag(player, 1181);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv3 == null || lv3.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv3, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1184);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic Lv3 ");
                                    }
                                    break;
                                case 2: // trade
                                    Item lv4 = null;
                                    try {
                                        lv4 = InventoryServiceNew.gI().findItemBag(player, 1184);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv4 == null || lv4.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv4, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1187);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic Lv4 ");
                                    }
                                    break;
                                 case 3: // trade
                                    Item lv5 = null;
                                    try {
                                        lv5 = InventoryServiceNew.gI().findItemBag(player, 1187);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv5 == null || lv5.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv5, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1190);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic Lv5 ");
                                    }
                                    break;
                                    case 4: // trade
                                    Item lv6 = null;
                                    try {
                                        lv6 = InventoryServiceNew.gI().findItemBag(player, 1190);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv6 == null || lv6.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv6, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1193);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic Lv6 ");
                                    }
                                    break;
                                case 5: // canel
                                 Item lv7 = null;
                                    try {
                                        lv7 = InventoryServiceNew.gI().findItemBag(player, 1193);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv7 == null || lv7.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv7, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1196);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic Lv7 ");
                                    }
                                    break;
                                 case 6: // canel
                                 Item lv8 = null;
                                    try {
                                        lv8 = InventoryServiceNew.gI().findItemBag(player, 1196);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv8 == null || lv8.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv8, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1199);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic Lv8 ");
                                    }
                                    break;
                                case 7: // canel
                                 Item lv9 = null;
                                    try {
                                        lv9 = InventoryServiceNew.gI().findItemBag(player, 1199);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (lv9 == null || lv9.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có sách skill lv trước đó");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, lv9, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1202);                                       
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được sách Chưởng Cadic Lv9 ");
                                    }
                                    break;     
                                case 8: // canel
                                  break;
                            }
                        }                         
                    }
                }
            }
        };
    }

    public static Npc dauThan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    player.magicTree.openMenuTree();
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MAGIC_TREE_NON_UPGRADE_LEFT_PEA:
                            if (select == 0) {
                                player.magicTree.harvestPea();
                            } else if (select == 1) {
                                if (player.magicTree.level == 10) {
                                    player.magicTree.fastRespawnPea();
                                } else {
                                    player.magicTree.showConfirmUpgradeMagicTree();
                                }
                            } else if (select == 2) {
                                player.magicTree.fastRespawnPea();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_NON_UPGRADE_FULL_PEA:
                            if (select == 0) {
                                player.magicTree.harvestPea();
                            } else if (select == 1) {
                                player.magicTree.showConfirmUpgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_CONFIRM_UPGRADE:
                            if (select == 0) {
                                player.magicTree.upgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_UPGRADE:
                            if (select == 0) {
                                player.magicTree.fastUpgradeMagicTree();
                            } else if (select == 1) {
                                player.magicTree.showConfirmUnuppgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_CONFIRM_UNUPGRADE:
                            if (select == 0) {
                                player.magicTree.unupgradeMagicTree();
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc calick(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            private final byte COUNT_CHANGE = 50;
            private int count;

            private void changeMap() {
                if (this.mapId != 102) {
                    count++;
                    if (this.count >= COUNT_CHANGE) {
                        count = 0;
                        this.map.npcs.remove(this);
                        Map map = MapService.gI().getMapForCalich();
                        this.mapId = map.mapId;
                        this.cx = Util.nextInt(100, map.mapWidth - 100);
                        this.cy = map.yPhysicInTop(this.cx, 0);
                        this.map = map;
                        this.map.npcs.add(this);
                    }
                }
            }

            @Override
            public void openBaseMenu(Player player) {
                player.iDMark.setIndexMenu(ConstNpc.BASE_MENU);
                if (TaskService.gI().getIdTask(player) < ConstTask.TASK_22_0) {
                    Service.gI().hideWaitDialog(player);
                    Service.gI().sendThongBao(player, "Bạn Chưa Hoàn Thành Nhiệm Vụ");
                    return;
                }
                if (this.mapId != player.zone.map.mapId) {
                    Service.gI().sendThongBao(player, "Calích đã rời khỏi map!");
                    Service.gI().hideWaitDialog(player);
                    return;
                }

                if (this.mapId == 102) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Chào chú, cháu có thể giúp gì?",
                            "Kể\nChuyện", "Quay về\nQuá khứ");
                } else {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Chào chú, cháu có thể giúp gì?", "Kể\nChuyện", "Đi đến\nTương lai", "Từ chối");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (this.mapId == 102) {
                    if (player.iDMark.isBaseMenu()) {
                        if (select == 0) {
                            //kể chuyện
                            NpcService.gI().createTutorial(player, this.avartar, ConstNpc.CALICK_KE_CHUYEN);
                        } else if (select == 1) {
                            //về quá khứ
                            ChangeMapService.gI().goToQuaKhu(player);
                        }
                    }
                } else if (player.iDMark.isBaseMenu()) {
                    if (select == 0) {
                        //kể chuyện
                        NpcService.gI().createTutorial(player, this.avartar, ConstNpc.CALICK_KE_CHUYEN);
                    } else if (select == 1) {
                        //đến tương lai
//                                    changeMap();
                        if (TaskService.gI().getIdTask(player) >= ConstTask.TASK_22_0) {
                            ChangeMapService.gI().goToTuongLai(player);
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Bạn Chưa Hoàn Thành Nhiệm Vụ");
                    }
                }
            }
        };
    }

    public static Npc jaco(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Gô Tên, Calich và Monaka đang gặp chuyện ở hành tinh Potaufeu \n Hãy đến đó ngay", "Đến \nPotaufeu");
                    } else if (this.mapId == 139) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26) {
                        if (player.getSession().player.nPoint.power >= 800000000000L) {

                            ChangeMapService.gI().goToPotaufeu(player);
                        } else {
                            this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh để vào!");
                        }
                    } else if (this.mapId == 139) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về trạm vũ trụ
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 24 + player.gender, -1, -1);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

//public static Npc Potage(int mapId, int status, int cx, int cy, int tempId, int avartar) {
//        return new Npc(mapId, status, cx, cy, tempId, avartar) {
//            @Override
//            public void openBaseMenu(Player player) {
//                if (canOpenNpc(player)) {
//                    if (this.mapId == 149) {
//                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                                "tét", "Gọi nhân bản");
//                    }
//                }
//            }
//            @Override
//            public void confirmMenu(Player player, int select) {
//                if (canOpenNpc(player)) {
//                   if (select == 0){
//                        BossManager.gI().createBoss(-214);
//                   }
//                }
//            }
//        };
//    }
    public static Npc npclytieunuong54(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                createOtherMenu(player, 0, "Em Đứng Ở Đây Canh Xem có ẻm nào chạy qua không tiện thể em có Trò chơi Chọn ai đây đang được diễn ra, nếu bạn tin tưởng mình đang tràn đầy may mắn thì có thể tham gia thử", "Thể lệ", "Chọn\nThỏi vàng");
            }

            @Override
            public void confirmMenu(Player pl, int select) {
                if (canOpenNpc(pl)) {
                    String time = ((ChonAiDay.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) + " giây";
                    if (pl.iDMark.getIndexMenu() == 0) {
                        if (select == 0) {
                            createOtherMenu(pl, ConstNpc.IGNORE_MENU, "Thời gian giữa các giải là 5 phút\nKhi hết giờ, hệ thống sẽ ngẫu nhiên chọn ra 1 người may mắn.\nLưu ý: Số thỏi vàng nhận được sẽ bị nhà cái lụm đi 5%!Trong quá trình diễn ra khi đặt cược nếu thoát game mọi phần đặt đều sẽ bị hủy", "Ok");
                        } else if (select == 1) {
                            createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                        }
                    } else if (pl.iDMark.getIndexMenu() == 1) {
                        if (((ChonAiDay.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                    break;
                                case 1: {
                                    try {
                                        if (InventoryServiceNew.gI().findItemBag(pl, 457).isNotNullItem() && InventoryServiceNew.gI().findItemBag(pl, 457).quantity >= 20) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(pl, InventoryServiceNew.gI().findItemBag(pl, 457), 20);
                                            InventoryServiceNew.gI().sendItemBags(pl);
                                            pl.goldNormar += 20;
                                            ChonAiDay.gI().goldNormar += 20;
                                            ChonAiDay.gI().addPlayerNormar(pl);
                                            createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                        } else {
                                            Service.gI().sendThongBao(pl, "Bạn không đủ thỏi vàng");
                                        }
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;

                                case 2: {
                                    try {
                                        if (InventoryServiceNew.gI().findItemBag(pl, 457).isNotNullItem() && InventoryServiceNew.gI().findItemBag(pl, 457).quantity >= 200) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(pl, InventoryServiceNew.gI().findItemBag(pl, 457), 200);
                                            InventoryServiceNew.gI().sendItemBags(pl);
                                            pl.goldVIP += 200;
                                            ChonAiDay.gI().goldVip += 200;
                                            ChonAiDay.gI().addPlayerVIP(pl);
                                            createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                        } else {
                                            Service.gI().sendThongBao(pl, "Bạn không đủ thỏi vàng");
                                        }
                                    } catch (Exception ex) {
                                            java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;

                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc thuongDe(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 45) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Con muốn làm gì nào", "Đến Kaio", "Quay số\nmay mắn");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 45) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 48, -1, 354);
                                    break;
                                case 1:
                                    this.createOtherMenu(player, ConstNpc.MENU_CHOOSE_LUCKY_ROUND,
                                            "Con muốn làm gì nào?", "Quay bằng\ngọc", "Quay bằng\nvàng",
                                            "Rương phụ\n("
                                            + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa hết\ntrong rương", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_CHOOSE_LUCKY_ROUND) {
                            switch (select) {
                                case 0:
                                    LuckyRound.gI().openCrackBallUI(player, LuckyRound.USING_RUBY);
                                    break;
                                case 1:
                                    LuckyRound.gI().openCrackBallUI(player, LuckyRound.USING_GOLD);
                                    break;    
                                case 2:
                                    ShopServiceNew.gI().opendShop(player, "ITEMS_LUCKY_ROUND", true);
                                    break;
                                case 3:
                                    NpcService.gI().createMenuConMeo(player,
                                            ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND, this.avartar,
                                            "Con có chắc muốn xóa hết vật phẩm trong rương phụ? Sau khi xóa "
                                            + "sẽ không thể khôi phục!",
                                            "Đồng ý", "Hủy bỏ");
                                    break;
                            }
                        }
                    }

                }
            }
        };
    }

    public static Npc thanVuTru(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 48) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Con muốn làm gì nào", "Di chuyển");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 48) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, ConstNpc.MENU_DI_CHUYEN,
                                            "Con muốn đi đâu?", "Về\nthần điện", "Thánh địa\nKaio", "Con\nđường\nrắn độc", "Từ chối");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DI_CHUYEN) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 45, -1, 354);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMap(player, 50, -1, 318, 336);
                                    break;
                                case 2:
                                    //con đường rắn độc
                                    break;
                            }
                        }
                    }
                }
            }

        };
    }

    public static Npc kibit(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Đến\nKaio", "Từ chối");
                    }
                    if (this.mapId == 114) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 48, -1, 354, 240);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc osin(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Đến\nKaio", "Đến\nhành tinh\nBill", "Từ chối");
                    } else if (this.mapId == 154) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Về thánh địa", "Đến\nhành tinh\nngục tù", "Từ chối");
                    } else if (this.mapId == 155) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Quay về", "Từ chối");
                    } else if (this.mapId == 52) {
                        try {
                            MapMaBu.gI().setTimeJoinMapMaBu();
                            if (this.mapId == 52) {
                                long now = System.currentTimeMillis();
                                if (now > MapMaBu.TIME_OPEN_MABU && now < MapMaBu.TIME_CLOSE_MABU) {
                                    this.createOtherMenu(player, ConstNpc.MENU_OPEN_MMB, "Đại chiến Ma Bư đã mở, "
                                            + "ngươi có muốn tham gia không?",
                                            "Hướng dẫn\nthêm", "Tham gia", "Từ chối");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_NOT_OPEN_MMB,
                                            "Ta có thể giúp gì cho ngươi?", "Hướng dẫn", "Từ chối");
                                }

                            }
                        } catch (Exception ex) {
                            Logger.error("Lỗi mở menu osin");
                        }

                    } else if (this.mapId >= 114 && this.mapId < 120 && this.mapId != 116) {
                        if (player.fightMabu.pointMabu >= player.fightMabu.POINT_MAX) {
                            this.createOtherMenu(player, ConstNpc.GO_UPSTAIRS_MENU, "Ta có thể giúp gì cho ngươi ?",
                                    "Lên Tầng!", "Quay về", "Từ chối");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                    "Quay về", "Từ chối");
                        }
                    } else if (this.mapId == 120) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Quay về", "Từ chối");
                    } else {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 48, -1, 354, 240);
                                    break;
                                case 1:
                                    if (player.getSession().player.nPoint.power >= 60000000000L) {                                
                //                Service.gI().sendMoney(player);
                                    ChangeMapService.gI().changeMap(player, 154, -1, 200, 312);
                                    } else {
                                this.npcChat(player, "Bạn chưa đủ 60 tỷ để vào");
                            }
                                    break;                                           
                            }
                        }
                    } else if (this.mapId == 154) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 50, -1, 318, 336);
                                    break;
                                case 1:
                                    
                                    ChangeMapService.gI().changeMap(player, 155, -1, 111, 792);
                                    break;
                            }
                        }
                    } else if (this.mapId == 155) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMap(player, 154, -1, 200, 312);
                            }
                        }
                    } else if (this.mapId == 52) {
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.MENU_REWARD_MMB:
                                break;
                            case ConstNpc.MENU_OPEN_MMB:
                                if (select == 0) {
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_MA_BU);
                                } else if (select == 1) {
//                                    if (!player.getSession().actived) {
//                                        Service.gI().sendThongBao(player, "Vui lòng kích hoạt tài khoản để sử dụng chức năng này");
//                                    } else
                                    ChangeMapService.gI().changeMap(player, 114, -1, 318, 336);
                                }
                                break;
                            case ConstNpc.MENU_NOT_OPEN_BDW:
                                if (select == 0) {
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_MA_BU);
                                }
                                break;
                        }
                    } else if (this.mapId >= 114 && this.mapId < 120 && this.mapId != 116) {
                        if (player.iDMark.getIndexMenu() == ConstNpc.GO_UPSTAIRS_MENU) {
                            if (select == 0) {
                                player.fightMabu.clear();
                                ChangeMapService.gI().changeMap(player, this.map.mapIdNextMabu((short) this.mapId), -1, this.cx, this.cy);
                            } else if (select == 1) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        } else {
                            if (select == 0) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        }
                    } else if (this.mapId == 120) {
                        if (player.iDMark.getIndexMenu() == ConstNpc.BASE_MENU) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc linhCanh(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (player.clan == null) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Chỉ tiếp các bang hội, miễn tiếp khách vãng lai", "Đóng");
                        return;
                    }
                    if (player.clan.getMembers().size() < DoanhTrai.N_PLAYER_CLAN) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bang hội phải có ít nhất 5 thành viên mới có thể mở", "Đóng");
                        return;
                    }
                    if (player.clan.doanhTrai != null) {
                        createOtherMenu(player, ConstNpc.MENU_JOIN_DOANH_TRAI,
                                "Bang hội của ngươi đang đánh trại độc nhãn\n"
                                +"Cút"
                                + TimeUtil.getSecondLeft(player.clan.doanhTrai.getLastTimeOpen(), DoanhTrai.TIME_DOANH_TRAI / 1000)
                                + "Chức năng đang được bảo trì để nghiên cứu !",
                               "Hướng\ndẫn\nthêm");
                        return;
                    }
                    int nPlSameClan = 0;
                    for (Player pl : player.zone.getPlayers()) {
                        if (!pl.equals(player) && pl.clan != null
                                && pl.clan.equals(player.clan) && pl.location.x >= 1285
                                && pl.location.x <= 1645) {
                            nPlSameClan++;
                        }
                    }
                    if (nPlSameClan < DoanhTrai.N_PLAYER_MAP) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ngươi phải có ít nhất " + DoanhTrai.N_PLAYER_MAP + " đồng đội cùng bang đứng gần mới có thể\nvào\n"
                                + "tuy nhiên ta khuyên ngươi nên đi cùng với 3-4 người để khỏi chết.\n"
                                + "Hahaha.", "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clanMember.getNumDateFromJoinTimeToToday() < 0) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Doanh trại chỉ cho phép những người ở trong bang trên 1 ngày. Hẹn ngươi quay lại vào lúc khác",
                                "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clan.haveGoneDoanhTrai) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bang hội của ngươi đã đi trại lúc " + TimeUtil.formatTime(player.clan.lastTimeOpenDoanhTrai, "HH:mm:ss") + " hôm nay. Người mở\n"
                                + "(" + player.clan.playerOpenDoanhTrai + "). Hẹn ngươi quay lại vào ngày mai", "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    createOtherMenu(player, ConstNpc.MENU_JOIN_DOANH_TRAI,
                            "Hôm nay bang hội của ngươi chưa vào trại lần nào. Ngươi có muốn vào\n"
                            + "không?\nĐể vào, ta khuyên ngươi nên có 3-4 người cùng bang đi cùng",
                            "Vào\n(miễn phí)", "Không", "Hướng\ndẫn\nthêm");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_JOIN_DOANH_TRAI:
                            if (select == 0) {
                        try {
                            DoanhTraiService.gI().joinDoanhTrai(player);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            } else if (select == 2) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);
                            }
                            break;
                        case ConstNpc.IGNORE_MENU:
                            if (select == 1) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc quaTrung(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            private final int COST_AP_TRUNG_NHANH = 1000000000;

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == (21 + player.gender)) {
                        player.mabuEgg.sendMabuEgg();
                        if (player.mabuEgg.getSecondDone() != 0) {
                            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG, "Bư bư bư...",
                                    "Hủy bỏ\ntrứng", "Ấp nhanh\n" + Util.numberToMoney(COST_AP_TRUNG_NHANH) + " vàng", "Đóng");
                        } else {
                            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG, "Bư bư bư...", "Nở", "Hủy bỏ\ntrứng", "Đóng");
                        }
                    }
                    if (this.mapId == 7) {
                        player.billEgg.sendBillEgg();
                        if (player.billEgg.getSecondDone() != 0) {
                            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG, "Bư bư bư...",
                                    "Hủy bỏ\ntrứng", "Ấp nhanh\n" + Util.numberToMoney(COST_AP_TRUNG_NHANH) + " vàng", "Đóng");
                        } else {
                            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG, "Bư bư bư...", "Nở", "Hủy bỏ\ntrứng", "Đóng");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == (21 + player.gender)) {
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.CAN_NOT_OPEN_EGG:
                                if (select == 0) {
                                    this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                            "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                } else if (select == 1) {
                                    if (player.inventory.gold >= COST_AP_TRUNG_NHANH) {
                                        player.inventory.gold -= COST_AP_TRUNG_NHANH;
                                        player.mabuEgg.timeDone = 0;
                                        Service.gI().sendMoney(player);
                                        player.mabuEgg.sendMabuEgg();
                                    } else {
                                        Service.gI().sendThongBao(player,
                                                "Bạn không đủ vàng để thực hiện, còn thiếu "
                                                + Util.numberToMoney((COST_AP_TRUNG_NHANH - player.inventory.gold)) + " vàng");
                                    }
                                }
                                break;
                            case ConstNpc.CAN_OPEN_EGG:
                                switch (select) {
                                    case 0:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_EGG,
                                                "Bạn có chắc chắn cho trứng nở?\n"
                                                + "Đệ tử của bạn sẽ được thay thế bằng đệ Mabư",
                                                "Đệ mabư\nTrái Đất", "Đệ mabư\nNamếc", "Đệ mabư\nXayda", "Từ chối");
                                        break;
                                    case 1:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                                "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_OPEN_EGG:
                                switch (select) {
                                    case 0:
                                        player.mabuEgg.openEgg(ConstPlayer.TRAI_DAT);
                                        break;
                                    case 1:
                                        player.mabuEgg.openEgg(ConstPlayer.NAMEC);
                                        break;
                                    case 2:
                                        player.mabuEgg.openEgg(ConstPlayer.XAYDA);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_DESTROY_EGG:
                                if (select == 0) {
                                    player.mabuEgg.destroyEgg();
                                }
                                break;
                        }
                    }
                  /*  if (this.mapId == 7) {
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.CAN_NOT_OPEN_BILL:
                                if (select == 0) {
                                    this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_BILL,
                                            "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                } else if (select == 1) {
                                    if (player.inventory.gold >= COST_AP_TRUNG_NHANH) {
                                        player.inventory.gold -= COST_AP_TRUNG_NHANH;
                                        player.billEgg.timeDone = 0;
                                        Service.gI().sendMoney(player);
                                        player.billEgg.sendBillEgg();
                                    } else {
                                        Service.gI().sendThongBao(player,
                                                "Bạn không đủ vàng để thực hiện, còn thiếu "
                                                + Util.numberToMoney((COST_AP_TRUNG_NHANH - player.inventory.gold)) + " vàng");
                                    }
                                }
                                break;
                            case ConstNpc.CAN_OPEN_EGG:
                                switch (select) {
                                    case 0:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_BILL,
                                                "Bạn có chắc chắn cho trứng nở?\n"
                                                + "Đệ tử của bạn sẽ được thay thế bằng đệ Mabư",
                                                "Đệ mabư\nTrái Đất", "Đệ mabư\nNamếc", "Đệ mabư\nXayda", "Từ chối");
                                        break;
                                    case 1:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_BILL,
                                                "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_OPEN_BILL:
                                switch (select) {
                                    case 0:
                                        player.billEgg.openEgg(ConstPlayer.TRAI_DAT);
                                        break;
                                    case 1:
                                        player.billEgg.openEgg(ConstPlayer.NAMEC);
                                        break;
                                    case 2:
                                        player.billEgg.openEgg(ConstPlayer.XAYDA);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_DESTROY_BILL:
                                if (select == 0) {
                                    player.billEgg.destroyEgg();
                                }
                                break;
                        }
                    }
*/
                }
            }
        };
    } 
   /* public static Npc duahau(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            private final int COST_AP_TRUNG_NHANH = 1000000000;

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                
                    if (this.mapId == 7 * player.gender) {
//                        player.billEgg.sendBillEgg();
                        if (player.billEgg.getSecondDone() != 0) {
                            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG, "Mang Đến Gặp Vua Hùng Để Được Những Món Quà Vô Giá...",
                                    "Thu Hoạch\nSớm" + Util.numberToMoney(COST_AP_TRUNG_NHANH) + " vàng", "Đóng");
                        } 
                    else {
                            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG, "Mau thu hoạch nào...", "Thu Hoạch", "Đóng");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                 
                    if (this.mapId == 7 * player.gender ) {
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.CAN_NOT_OPEN_BILL:
                                 if (select == 0) {
                                    if (player.inventory.gold >= COST_AP_TRUNG_NHANH) {
                                        player.inventory.gold -= COST_AP_TRUNG_NHANH;
                                        player.billEgg.timeDone = 0;
                                        Service.gI().sendMoney(player);
                                        player.billEgg.sendBillEgg();
                                    } else {
                                        Service.gI().sendThongBao(player,
                                                "Bạn không đủ vàng để thực hiện, còn thiếu "
                                                + Util.numberToMoney((COST_AP_TRUNG_NHANH - player.inventory.gold)) + " vàng");
                                    }
                                }
                                break;
                            case ConstNpc.CAN_OPEN_EGG:
                                switch (select) {
                                    case 0:
                                        this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_BILL,
                                                "ôi bạn ơi?\n"
                                                + "Chọn Một Trong Những Món Quà Giá Trị Nào",
                                                "Ngọc Rồng\nTorobo", "Dưa\nHấu", "Ngọc\nBội", "Từ chối");
                                        break;                             
                                }
                                break;
                            case ConstNpc.CONFIRM_OPEN_BILL:
                                switch (select) {
                                    case 0:
                                      ItemService.gI().openBoxtorobo(player);
player.billEgg.destroyEgg();
//                                        player.billEgg.openEgg(ConstPlayer.TRAI_DAT);
                                        break;
                                    case 1:
                                      ItemService.gI().openBoxdua(player);
player.billEgg.destroyEgg();
//                                if (player.inventory.ruby == 10000) {
//                                    this.npcChat(player, "Bú ít thôi con");
//                                    break;
//                                }
//                                player.inventory.ruby = 100;
//                                Service.gI().sendMoney(player);
//                                Service.gI().sendThongBao(player, "Bạn vừa nhận được 200K Hồng Ngọc");
                                break;
                                    case 2://2072
                                      ItemService.gI().openBoxngocboi(player);
                            player.billEgg.destroyEgg();
//                                        player.billEgg.openEgg(ConstPlayer.XAYDA);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case ConstNpc.CONFIRM_DESTROY_BILL:
                                if (select == 0) {
                                    player.billEgg.destroyEgg();
                                }
                                break;
                        }
                    }

                }
            }
        };
    }
    */
    public static Npc quocVuong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Nâng giới hạn làm cái con cặc gì hả?",
                        "Chó Mẹ", "Chó Con", "Cặc");
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                if (player.nPoint.limitPower < NPoint.MAX_LIMIT) {
                                    this.createOtherMenu(player, ConstNpc.OPEN_POWER_MYSEFT,
                                            "Ta sẽ chuyền cho mày ít cứt vào não nè "
                                            + Util.numberToMoney(player.nPoint.getPowerNextLimit()),
                                            "Nâng\ngiới hạn\nsức mạnh",
                                            "Nâng ngay\n" + Util.numberToMoney(OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) + " vàng", "Đóng");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                            "Tinh trùng đã đạt mức tối đa",
                                            "Đóng");
                                }
                                break;
                            case 1:
                                if (player.pet != null) {
                                    if (player.pet.nPoint.limitPower < NPoint.MAX_LIMIT) {
                                        this.createOtherMenu(player, ConstNpc.OPEN_POWER_PET,
                                                "Ta sẽ chuyền cứt vô não con đệ mày "
                                                + Util.numberToMoney(player.pet.nPoint.getPowerNextLimit()),
                                                "Nâng ngay\n" + Util.numberToMoney(OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) + " vàng", "Đóng");
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                                "Não của đệ mày đã nhét đủ cứt rồi",
                                                "Đóng");
                                    }
                                } else {
                                    Service.gI().sendThongBao(player, "Không thể thực hiện");
                                }
                                //giới hạn đệ tử
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_MYSEFT) {
                        switch (select) {
                            case 0:
                                OpenPowerService.gI().openPowerBasic(player);
                                break;
                            case 1:
                                if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                                    if (OpenPowerService.gI().openPowerSpeed(player)) {
                                        player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                                        Service.gI().sendMoney(player);
                                    }
                                } else {
                                    Service.gI().sendThongBao(player,
                                            "Hết vàng rồi thằng óc chó "
                                            + Util.numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER - player.inventory.gold)) + " vàng");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_PET) {
                        if (select == 0) {
                            if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                                if (OpenPowerService.gI().openPowerSpeed(player.pet)) {
                                    player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                                    Service.gI().sendMoney(player);
                                }
                            } else {
                                Service.gI().sendThongBao(player,
                                        "Hết vàng rồi thằng óc chó "
                                        + Util.numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER - player.inventory.gold)) + " vàng");
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc bulmaTL(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 102) {
                        if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu bé muốn mua gì nào?", "Cửa hàng", "Đóng");
                        }
                    } else if (this.mapId == 104) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Kính chào Ngài Linh thú sư!", "Cửa hàng", "Đóng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 102) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "BUNMA_FUTURE", true);
                            }
                        }
                    } else if (this.mapId == 104) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "BUNMA_LINHTHU", true);
                            }
                        }
                    }
                }
            }
        };
    }
    
    public static Npc rongOmega(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    BlackBallWar.gI().setTime();
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26) {
                        try {
                            long now = System.currentTimeMillis();
                            if (now > BlackBallWar.TIME_OPEN && now < BlackBallWar.TIME_CLOSE) {
                                this.createOtherMenu(player, ConstNpc.MENU_OPEN_BDW, "Vào đại chiến ngọc rồng đen để buồi to hơn, "
                                        + "Có mún vô không thằng đần?",
                                        "Hướng dẫn\nthêm", "ZOZO", "Từ chối");
                            } else {
                                String[] optionRewards = new String[7];
                                int index = 0;
                                for (int i = 0; i < 7; i++) {
                                    if (player.rewardBlackBall.timeOutOfDateReward[i] > System.currentTimeMillis()) {
                                        String quantily = player.rewardBlackBall.quantilyBlackBall[i] > 1 ? "x" + player.rewardBlackBall.quantilyBlackBall[i] + " " : "";
                                        optionRewards[index] = quantily + (i + 1) + " sao";
                                        index++;
                                    }
                                }
                                if (index != 0) {
                                    String[] options = new String[index + 1];
                                    for (int i = 0; i < index; i++) {
                                        options[i] = optionRewards[i];
                                    }
                                    options[options.length - 1] = "Từ chối";
                                    this.createOtherMenu(player, ConstNpc.MENU_REWARD_BDW, "Ngươi có một vài phần thưởng ngọc "
                                            + "rồng sao đen đây!",
                                            options);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_NOT_OPEN_BDW,
                                            "Ta có thể giúp gì cho ngươi?", "Hướng dẫn", "Từ chối");
                                }
                            }
                        } catch (Exception ex) {
                            Logger.error("Lỗi mở menu rồng Omega");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_REWARD_BDW:
                            player.rewardBlackBall.getRewardSelect((byte) select);
                            break;
                        case ConstNpc.MENU_OPEN_BDW:
                            if (select == 0) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_BLACK_BALL_WAR);
                            } else if (select == 1) {
//                                if (!player.getSession().actived) {
//                                    Service.gI().sendThongBao(player, "Vui lòng kích hoạt tài khoản để sử dụng chức năng này");
//
//                                } else
                                player.iDMark.setTypeChangeMap(ConstMap.CHANGE_BLACK_BALL);
                                ChangeMapService.gI().openChangeMapTab(player);
                            }
                            break;
                        case ConstNpc.MENU_NOT_OPEN_BDW:
                            if (select == 0) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_BLACK_BALL_WAR);
                            }
                            break;
                    }
                }
            }

        };
    }

    public static Npc rong1_to_7s(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isHoldBlackBall()) {
                        this.createOtherMenu(player, ConstNpc.MENU_PHU_HP, "Ta có thể giúp gì cho ngươi?", "Phù ĐỂ CHO BUỒI NÓ TO", "Từ chối");
                    } else {
                        if (BossManager.gI().existBossOnPlayer(player)
                                || player.zone.items.stream().anyMatch(itemMap -> ItemMapService.gI().isBlackBall(itemMap.itemTemplate.id))
                                || player.zone.getPlayers().stream().anyMatch(p -> p.iDMark.isHoldBlackBall())) {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_GO_HOME, "Ta có thể giúp gì cho ngươi?", "Về nhà", "Từ chối");
                        } else {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_GO_HOME, "Ta có thể giúp gì cho ngươi?", "Về nhà", "Từ chối", "Gọi BOSS");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHU_HP) {
                        if (select == 0) {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_PHU_HP,
                                    "Ta sẽ giúp ngươi tăng HP lên mức kinh hoàng, ngươi chọn đi",
                                    "x3 HP\n" + Util.numberToMoney(BlackBallWar.COST_X3) + " vàng",
                                    "x5 HP\n" + Util.numberToMoney(BlackBallWar.COST_X5) + " vàng",
                                    "x7 HP\n" + Util.numberToMoney(BlackBallWar.COST_X7) + " vàng",
                                    "Từ chối"
                            );
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_GO_HOME) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
                        } else if (select == 2) {
                            BossManager.gI().callBoss(player, mapId);
                        } else if (select == 1) {
                            this.npcChat(player, "Để ta xem ngươi trụ được bao lâu");
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_PHU_HP) {
                        if (player.effectSkin.xHPKI > 1) {
                            Service.gI().sendThongBao(player, "Bạn đã được phù hộ rồi!");
                            return;
                        }
                        switch (select) {
                            case 0:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X3);
                                break;
                            case 1:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X5);
                                break;
                            case 2:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X7);
                                break;
                            case 3:
                                this.npcChat(player, "Để ta xem ngươi trụ được bao lâu");
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc npcThienSu64(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 14) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh "
                            + "\n 3. chi phí vào cổng  50 triệu vàng", "Tới ngay", "Từ chối");
                }
                if (this.mapId == 7) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh "
                            + "\n 3. chi phí vào cổng  50 triệu vàng", "Tới ngay", "Từ chối");
                }
                if (this.mapId == 0) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh "
                            + "\n 3. chi phí vào cổng  50 triệu vàng", "Tới ngay", "Từ chối");
                }
                if (this.mapId == 146) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 147) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 148) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 48) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đã tìm đủ nguyên liệu cho tôi chưa?\n Tôi sẽ giúp cậu mạnh lên kha khá đấy!", "Hướng Dẫn",
                            "Đổi SKH VIP", "Từ Chối");
                }
                if (this.mapId == 5) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đã tìm đủ nguyên liệu cho tôi chưa?\n Tôi sẽ giúp cậu mạnh lên kha khá đấy!",
                            "Chế Tạo trang bị thiên sứ", "Từ Chối");
                }
            }

            //if (player.inventory.gold < 500000000) {
//                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
//                return;
//            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu() && this.mapId == 7) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 146, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 14) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 148, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 0) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 147, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 147) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 0, -1, 450);
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 148) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 14, -1, 450);
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 146) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 7, -1, 450);
                        }
                        if (select == 1) {
                        }

                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 48) {
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOI_SKH_VIP);
                        }
                        if (select == 1) {
                            CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_SKH_VIP);
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_NANG_DOI_SKH_VIP) {
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                    }
                   if (player.iDMark.isBaseMenu() && this.mapId == 5) {
                        if (select == 0) {
                            CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_TRANG_BI_TS);
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DAP_DO) {
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                    }
                }
            }

        };
    }

     public static Npc taixiu(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Đưa cho ta thỏi vàng và ngươi sẽ mua đc oto\nĐây không phải chẵn lẻ tài xỉu đâu=)))",
                            "Xỉu", "Tài");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    Input.gI().TAI(player);
                                    break;
                                case 1:
                                    Input.gI().XIU(player);
                                    break;

                            }
                        }
                    }
                }
            }
        };
    }
    public static Npc Berrus(int mapId, int status, int cx, int cy, int tempId, int avartar) {
       return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 48) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "\b|7|Có mang thức ăn cho ta không?\b|7|", "Đổi đồ\nHD\nTrái Đất", "Đổi đồ\nHD\nNamek", "Đổi Đồ\nHD\nxayda", "Đổi đồ\nThiên Sứ\nTrái Đất", "Đổi đồ\nThiên Sứ\nNamek", "Đổi Đồ\nThiên Sứ\nxayda");
                    }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 48) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                            case 0:
                                    this.createOtherMenu(player, 1,
                                      "\b|7|Có rồi à?",  "Pudding\nÁo hủy diệt", "Xúc xích\nQuần hủy diệt", "Kem dâu\nGăng hủy diệt" ,"Mì ly\nGiày hủy diệt","Sushi\nNhẫn hủy diệt","Thôi Khỏi");
                                    break;
                            case 1:
                                    this.createOtherMenu(player, 2,
                                       "\b|7|Có rồi à?",  "Pudding\nÁo hủy diệt", "Xúc xích\nQuần hủy diệt", "Kem dâu\nGăng hủy diệt" ,"Mì ly\nGiày hủy diệt","Sushi\nNhẫn hủy diệt","Thôi Khỏi");
                                    break;
                            case 2:
                                    this.createOtherMenu(player, 3,
                                      "\b|7|Có rồi à?",  "Pudding\nÁo hủy diệt", "Xúc xích\nQuần hủy diệt", "Kem dâu\nGăng hủy diệt" ,"Mì ly\nGiày hủy diệt","Sushi\nNhẫn hủy diệt","Thôi Khỏi");
                                    break;
                            case 3:
                                    this.createOtherMenu(player, 4,
                                       "\b|7|Bạn muốn đổi 1 món đồ húy diệt \nTrái đất cùng loại và x30 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ ra SKH",  "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ" ,"Giày\nThiên Sứ","Nhẫn\nThiên Sứ","Từ Chối");
                                    break;
                            case 4:
                                    this.createOtherMenu(player, 5,
                                       "\b|7|Bạn muốn đổi 1 món đồ húy diệt \nNamek cùng loại và x30 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ ra SKH",  "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ" ,"Giày\nThiên Sứ","Nhẫn\nThiên Sứ","Từ Chối");
                                    break;
                            case 5:
                                    this.createOtherMenu(player, 6,
                                       "\b|7|Bạn muốn đổi 1 món đồ húy diệt \nXayda cùng loại và x30 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ ra SKH",  "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ" ,"Giày\nThiên Sứ","Nhẫn\nThiên Sứ","Từ Chối");
                                    break;
                                }
                            }
                            else if (player.iDMark.getIndexMenu() == 1){ // action đổi dồ húy diệt
                            switch (select){
                               case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 663);
                            int soLuong = 0;
                            int aotltd = 0;
                            if (tl != null);
                                aotltd = tl.quantity;
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0  ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 663+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 663)&& soLuong >= 0 && aotltd>=99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1156 + i);
                                        this.npcChat(player, "Bạn nhận được áo hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Pudding");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 664);
                                       int soLuong = 0;
                                       int quantltd = 0;
                                       if (tl != null);
                                       quantltd = tl.quantity;
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 664+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 664+i)&& soLuong >= 0 && quantltd>=99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1157+i);
                                        this.npcChat(player,"Bạn nhận được quần hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Xúc xích");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 665);
                                       int soLuong = 0;
                                       int gangtltd =0;
                                       if (tl !=null);{
                                       gangtltd = tl.quantity;
                                    }   
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 665+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 665+i)&& soLuong >= 0 && gangtltd >=99 ){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1163+i);
                                        this.npcChat(player, "Bạn nhận được găng hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Kem dâu");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 666);
                                       int soLuong = 0;
                                       int giaytltd = 0;
                                       if (tl != null){
                                       giaytltd = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 666+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 666+i)&& soLuong >= 0 && giaytltd >=99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1164+i);
                                        this.npcChat(player, "Bạn nhận được giày hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Mì ly");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 667);
                                       int soLuong = 0;
                                       int nhantl = 0;
                                       if (tl != null){
                                       nhantl = tl.quantity;    
                                    }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 667+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 667+i)&& soLuong >= 0 && nhantl >= 99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1162+i);
                                        this.npcChat(player, "Bạn nhận được nhẫn hủy diệt");
                                        break; 
                                    } else {
                                        this.npcChat(player, "Yêu cầu cần 99 Sushi");
                                    }                            
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;                
                                case 5: // canel
                                  break;
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 2){ // action đổi dồ húy diệt
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 663);
                            int soLuong = 0;
                            int aotlnm =0 ;
                            if (tl != null){
                            aotlnm = tl.quantity;    
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 663+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 663+i)&& soLuong >= 0 && aotlnm >=99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1158 + i);
                                        this.npcChat(player, "Bạn nhận được áo hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Pudding");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 664);
                                       int soLuong = 0;
                                       int quantlnm =0;
                                       if (tl != null){
                                       quantlnm = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 664+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 664+i)&& soLuong >= 0 && quantlnm >=99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1159+i);
                                        this.npcChat(player, "Bạn nhận được quần hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 xúc xích");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 665);
                                       int soLuong = 0;
                                       int gangtlnm = 0;
                                       if (tl != null) {
                                       gangtlnm = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 665);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 665)&& soLuong >= 0 && gangtlnm >=99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1165+i);
                                        this.npcChat(player, "Bạn nhận được găng hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Kem dâu");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 666);
                                       int soLuong = 0;
                                       int giaytlnm = 0;
                                       if (tl !=null){
                                       giaytlnm = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 666+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 666+i)&& soLuong >= 0 && giaytlnm >= 99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1166+i);
                                        this.npcChat(player, "Bạn nhận được giày hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Mì ly");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 667);
                                       int soLuong = 0;
                                       int nhantl =0;
                                       if (tl != null){
                                       nhantl = tl.quantity;    
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 667+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 667+i)&& soLuong >= 0 && nhantl>=99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1162+i);
                                        this.npcChat(player, "Bạn nhận được nhẫn hủy diệt");
                                        break; 
                                    } else {
                                        this.npcChat(player, "Yêu cầu cần 99 Sushi");
                                    }                            
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;                
                                case 5: // canel
                                  break;
                            }
                        }
                        else if (player.iDMark.getIndexMenu() == 3){ // action đổi dồ húy diệt
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 663);
                            int soLuong = 0;
                            int aotlxd = 0;
                            if (tl != null){
                                aotlxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 663+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 663+i)&& soLuong >= 0 && aotlxd>= 99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1160+i);
                                        this.npcChat(player, "Bạn nhận được áo hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Pudding");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 664);
                                       int soLuong = 0;
                                       int quantlxd =0 ;
                                       if (tl != null){
                                           quantlxd = tl.quantity;
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 664+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 664+i)&& soLuong >= 0 && quantlxd >= 99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1161+i);
                                        this.npcChat(player, "Bạn nhận được quần hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Xúc xích");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 665);
                                       int soLuong = 0;
                                       int gangtlxd = 0;
                                       if (tl != null){
                                           gangtlxd = tl.quantity;
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 665+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 665+i)&& soLuong >= 0 && gangtlxd >=99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1167+i);
                                        this.npcChat(player, "Bạn nhận được găng hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Kem dâu");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 666);
                                       int soLuong = 0;
                                       int giaytlxd = 0;
                                       if (tl != null){
                                           giaytlxd = tl.quantity;
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 666+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 666+i)&& soLuong >= 0 && giaytlxd >= 99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1168+i);
                                        this.npcChat(player, "Bạn nhận được giày hủy diệt");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần 99 Mì ly");
                                    }
                                    
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                       Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                       Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 667);
                                       int soLuong = 0;
                                       int nhantl = 0;
                                       if ( tl != null){
                                           nhantl= tl.quantity;
                                       }
                                       if (dns != null) {
                                       soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 667+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 667+i)&& soLuong >= 0 && nhantl >= 99){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 0);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 99);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1162+i);
                                        this.npcChat(player, "Bạn nhận được nhẫn hủy diệt");
                                        break; 
                                    } else {
                                        this.npcChat(player, "Yêu cầu cần 99 Sushi");
                                    }                            
                                }
                            }catch (Exception e){
                                    
                                    }
                                    break;                
                                case 5: // canel
                                  break;
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 4){ // action đổi dồ thiên sứ
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 650);
                            int soLuong = 0;
                            int aohdtd = 0;
                            if (tl != null){
                                aohdtd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 650+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 650+i)&& soLuong >= 30 && aohdtd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1048+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo húy diệt trái đất + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 651);
                            int soLuong = 0;
                            int quanhdtd = 0;
                            if ( tl !=null){
                                quanhdtd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 651+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 651+i)&& soLuong >= 30 && quanhdtd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1051+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần húy diệt trái đất + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 657);
                            int soLuong = 0;
                            int ganghdtd= 0;
                            if (tl != null){
                                ganghdtd= tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 657+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 657+i)&& soLuong >= 30  && ganghdtd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1054);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Găng húy diệt trái đất + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 658);
                            int soLuong = 0;
                            int giayhdtd = 0;                            
                            if (tl != null){
                                giayhdtd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 658+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 658+i)&& soLuong >= 30 && giayhdtd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1057+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày húy diệt trái đất + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    int nhanhd= 0;
                                    if (tl != null){
                                        nhanhd= tl.quantity;
                                    }
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 656+i)&& soLuong >= 30 && nhanhd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1060+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần nhận húy diệt trái đất + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;              
                                case 5: // canel
                                  break;
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 5){ // action đổi dồ thiên sứ
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 652);
                            int soLuong = 0;
                            int aohdnm = 0;
                            if (tl != null){
                                aohdnm = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 652+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 652+i)&& soLuong >= 30 && aohdnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1049+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo húy diệt namec + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 653);
                            int soLuong = 0;
                            int quanhdnm=0;
                            if (tl != null){
                                quanhdnm= tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 653+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 653+i)&& soLuong >= 30 && quanhdnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1052+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần húy diệt namec + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 659);
                            int soLuong = 0;
                            int ganghdnm= 0;
                            if (tl !=null){
                                ganghdnm= tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 659+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 659+i)&& soLuong >= 30 && ganghdnm >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1055+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Găng húy diệt namec + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 660);
                            int soLuong = 0;
                            int giayhdnm =0;
                            if (tl != null){
                                giayhdnm = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 660+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 660+i)&& soLuong >= 30 && giayhdnm >=1 ){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1058+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày húy diệt namec + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    int nhanhd = 0;
                                    if (tl !=null){
                                        nhanhd= tl.quantity;
                                    }
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 656+i)&& soLuong >= 30 && nhanhd>=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1061+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần nhận húy diệt namec + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;              
                                case 5: // canel
                                  break;
                            }
                        }
                        else if (player.iDMark.getIndexMenu() == 6){ // action đổi dồ thiên sứ
                            switch (select){
                                case 0: // trade
                                try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 654);
                            int soLuong = 0;
                            int aohdxd = 0;
                            if (tl != null){
                                aohdxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 654+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 654+i)&& soLuong >= 30 && aohdxd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1050+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Áo húy diệt xayda + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 1: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 655);
                            int soLuong = 0;
                            int quanhdxd = 0;
                            if (tl != null){
                                quanhdxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 655+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 655+i)&& soLuong >= 30 && quanhdxd >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1053+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Quần húy diệt xayda + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                case 2: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 661);
                            int soLuong = 0;
                            int ganghdxd = 0;
                            if (tl != null){
                                ganghdxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 661+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 661+i)&& soLuong >= 30 && ganghdxd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1056+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Găng húy diệt xayda + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                 case 3: // trade
                                    try{
                            Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                            Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 662);
                            int soLuong = 0;
                            int giayhdxd =0;
                            if (tl != null){
                                giayhdxd = tl.quantity;
                            }
                            if (dns != null) {
                                soLuong = dns.quantity;
                            }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 662+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 662+i)&& soLuong >= 30 && giayhdxd >=1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1059+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần Giày húy diệt xayda + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;
                                    case 4: // trade
                                    try{
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    int nhanhd = 0;
                                    if (tl != null){
                                        nhanhd = tl.quantity;
                                    }
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0 ; i < 12;i++){
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656+i);

                                    if (InventoryServiceNew.gI().isExistItemBag(player, 656+i)&& soLuong >= 30 && nhanhd >= 1){
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                        CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1062+i);
                                        this.npcChat(player, "Chuyển Hóa Thành Công!");

                                        break;
                                    }else{
                                        this.npcChat(player, "Yêu cầu cần nhận húy diệt xayda + x30 Đá Ngũ Sắc!");
                                    }
                                    
                                    }
                            }catch (Exception e){
                                    
                                    }
                                    break;              
                                case 5: // canel
                                  break;
                            }
                        } 
                    }
                }
            }
        };
    }
    

     public static Npc tosukaio(int mapId, int status, int cx, int cy, int tempId, int avartar) {
       return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 50) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "\b|7|Bạn cần đổi gì?\b|7|", "Đổi cải trang\nTDST", "Đổi cải trang \nCooler Vàng");
                    }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                            case 0:
                                    this.createOtherMenu(player, 1,
                                       "\b|7|Cần 1 Phiếu boss?",  "Số 4", "Số 3", "Số 2" ,"Số 1","Tiểu đội\ntrưởng","Thôi Khỏi");
                                    break;
                            case 1:
                                    this.createOtherMenu(player, 2,
                                       "\b|7|Cần 1 Phiếu Cooler vàng",  "Cooler Vàng");
                                    break;                            
                                }
                            }
                            else if (player.iDMark.getIndexMenu() == 1){ // action đổi dồ húy diệt
                            switch (select){
                               case 0:
                                    Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 459);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có 1 phiếu boss");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 429);
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(50, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(2) + 3));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang thành Số 4 ");
                                    }
                                    break;
                                case 1:
                                    Item so3 = null;
                                    try {
                                        so3 = InventoryServiceNew.gI().findItemBag(player, 459);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (so3 == null || so3.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có 1 phiếu boss");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, so3, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 430);
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(50, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(2) + 3));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang thành Số 3 ");
                                    }
                                    break;
                                case 2:
                                    Item so2 = null;
                                    try {
                                        so2 = InventoryServiceNew.gI().findItemBag(player, 459);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (so2 == null || so2.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có 1 phiếu boss");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, so2, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 431);
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(50, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(2) + 3));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang thành Số 2 ");
                                    }
                                    break;
                                 case 3:
                                    Item so1 = null;
                                    try {
                                        so1 = InventoryServiceNew.gI().findItemBag(player, 459);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (so1 == null || so1.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có 1 phiếu boss");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, so1, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 432);
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(50, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(2) + 3));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang thành Số 1 ");
                                    }
                                    break;
                                    case 4:
                                    Item tdt = null;
                                    try {
                                        tdt = InventoryServiceNew.gI().findItemBag(player, 459);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (tdt == null || tdt.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có 1 phiếu boss");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, tdt, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 433);
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(50, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(2) + 3));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang thành Tiểu đội trưởng ");
                                    }
                                    break;
                                case 5: // canel
                                  break;
                            }
                        } 
                        else if (player.iDMark.getIndexMenu() == 2){ // action đổi dồ húy diệt
                            switch (select){
                                case 0: // trade
                               Item tdt = null;
                                    try {
                                        tdt = InventoryServiceNew.gI().findItemBag(player, 751);                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (tdt == null || tdt.quantity < 1) {
                                        this.npcChat(player, "Bạn cần có 1 phiếu boss");                                    
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 0;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, tdt, 1);
                                        Service.gI().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 878);
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(50, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(77, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(103, new Random().nextInt(10) + 15));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(93, new Random().nextInt(2) + 3));
                                        trungLinhThu.itemOptions.add(new Item.ItemOption(30, new Random().nextInt(15) + 15));                                      
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được cải trang thành Coller ");
                                    }
                                    break;                               
                            }
                        } 
                    }
                }
            }
        };
    }

    public static Npc boMong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 47 || this.mapId == 84) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Xin chào, cậu muốn tôi giúp gì?", "Nhiệm vụ\nhàng ngày", "Nhận ngọc miễn phí", "Từ chối");
                    }
//                    if (this.mapId == 47) {
//                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                                "Xin chào, cậu muốn tôi giúp gì?", "Từ chối");
//                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 47 || this.mapId == 84) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    if (player.playerTask.sideTask.template != null) {
                                        String npcSay = "Nhiệm vụ hiện tại: " + player.playerTask.sideTask.getName() + " ("
                                                + player.playerTask.sideTask.getLevel() + ")"
                                                + "\nHiện tại đã hoàn thành: " + player.playerTask.sideTask.count + "/"
                                                + player.playerTask.sideTask.maxCount + " ("
                                                + player.playerTask.sideTask.getPercentProcess() + "%)\nSố nhiệm vụ còn lại trong ngày: "
                                                + player.playerTask.sideTask.leftTask + "/" + ConstTask.MAX_SIDE_TASK;
                                        this.createOtherMenu(player, ConstNpc.MENU_OPTION_PAY_SIDE_TASK,
                                                npcSay, "Trả nhiệm\nvụ", "Hủy nhiệm\nvụ");
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPTION_LEVEL_SIDE_TASK,
                                                "Tôi có vài nhiệm vụ theo cấp bậc, "
                                                        + "sức cậu có thể làm được cái nào?",
                                                "Dễ", "Bình thường", "Khó", "Siêu khó", "Địa ngục", "Từ chối");
                                    }
                                    break;
                                case 1:
    //                                List<Archivement> list = player.getArchivement().getList();
   //                                 try {
    //                                    Message m = new Message(-76);
     //                                   DataOutputStream ds = m.writer();
     //                                   ds.writeByte(0);
    //                                    ds.writeByte(list.size());

     //                                   for (Archivement a : list) {
       //                                     ds.writeUTF(a.getInfo1());
         //                                   ds.writeUTF(a.getInfo2());
           //                                 ds.writeShort(a.getMoney());
             //                               ds.writeBoolean(a.isFinish());
               //                             ds.writeBoolean(a.isRecieve());
                 //                       }

                   //                     ds.flush();
                     //                   player.sendMessage(m);
                       //                 m.cleanup();

                         //           } catch (IOException e) {
                           //             e.printStackTrace();
                             //       }
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_LEVEL_SIDE_TASK) {
                            switch (select) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    TaskService.gI().changeSideTask(player, (byte) select);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_PAY_SIDE_TASK) {
                            switch (select) {
                                case 0:
                                    TaskService.gI().paySideTask(player);
                                    break;
                                case 1:
                                    TaskService.gI().removeSideTask(player);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc karin(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "|1|Cửa Hàng Của Ta Sưu Tầm Nhưng Đồ Vip Độc Lạ"
                                    + "\n|3|Cách Kiếm Ngọc"
                                    + "\n|4|Nhiệm Vụ Hàng Ngày"
                                    + "\n|5|Nạp Tiền "
                                    + "\n|6|Có Rồi Hả? Giỏi,Hãy Mang Cho Ta nào", "Cửa Hàng", "Đóng");
                        }
                    } else if (this.mapId == 104) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Kính chào Ngài Linh thú sư!", "Cửa hàng", "Đóng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "KARIN", true);
                            }
                        }
                    } else if (this.mapId == 104) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "BUNMA_LINHTHU", true);
                            }
                        }
                    }
                }
            }
        };
    }
    
     public static Npc thuan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 210) {
                        if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "|1|Cửa Hàng Của Ta Sưu Tầm Nhưng Đồ Vip Độc Lạ"
                                    + "\n|3|Cách Kiếm Ngọc"
                                    + "\n|4|Săn Boss Hũy Diệt"
                                    + "\n|5|Mua Tại Đây "
                                    + "\n|6|Có Rồi Hả? Giỏi,Hãy Mang Cho Ta nào", "Cửa Hàng", "Đóng");
                        }
                    } else if (this.mapId == 104) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Kính chào Ngài Linh thú sư!", "Cửa hàng", "Đóng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 210) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "THUAN", true);
                            }
                        }
                    } else if (this.mapId == 104) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "BUNMA_LINHTHU", true);
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc vados(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "|2|Ta Vừa Hắc Mắp Xêm Được T0p Của Toàn Server\b|7|Mi Muống Xem Tóp Gì?",
                            "Top Sức Mạnh", "Top Nhiệm Vụ", "Top Hồng Ngọc", "Top Sức Đánh", "Đóng");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (this.mapId) {
                        case 5:
                            switch (player.iDMark.getIndexMenu()) {
                                case ConstNpc.BASE_MENU:
                                    if (select == 0) {
                                        Service.gI().showListTop(player, Manager.topSM);
                                        break;
                                    }
                                    if (select == 1) {
                                        Service.gI().showListTop(player, Manager.topNV);
                                        break;
                                    }
                                    if (select == 2) {
                                        Service.gI().showListTop(player, Manager.topPVP);
                                        break;
                                    }
                                    if (select == 3) {
                                        Service.gI().showListTop(player, Manager.topSD);
                                        break;
                                    }
                                    break;
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc gokuSSJ_1(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 80) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Xin chào, tôi có thể giúp gì cho cậu?", "Tới hành tinh\nYardart", "Từ chối");
                    } else if (this.mapId == 131) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Xin chào, tôi có thể giúp gì cho cậu?", "Quay về", "Từ chối");
                    } else {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.BASE_MENU:
                            if (this.mapId == 80) {
                                if (select == 0) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 131, -1, 870);
                                }
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc mavuong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 153) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Xin chào, tôi có thể giúp gì cho cậu?", "Tây thánh địa", "Từ chối");
                    } else if (this.mapId == 156) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 153) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                //đến tay thanh dia
                                ChangeMapService.gI().changeMapBySpaceShip(player, 156, -1, 360);
                            }
                        }
                    } else if (this.mapId == 156) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về lanh dia bang hoi
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 153, -1, 432);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc gokuSSJ_2(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    try {
                        Item biKiep = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 590);
                        if (biKiep != null) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn đang có " + biKiep.quantity + " bí kiếp.\n"
                                    + "Hãy kiếm đủ 10000 bí kiếp tôi sẽ dạy bạn cách dịch chuyển tức thời của người Yardart", "Học dịch\nchuyển", "Đóng");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    try {
                        Item biKiep = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 590);
                        if (biKiep != null) {
                            if (biKiep.quantity >= 10000 && InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                Item yardart = ItemService.gI().createNewItem((short) (player.gender + 592));
                                yardart.itemOptions.add(new Item.ItemOption(47, 400));
                                yardart.itemOptions.add(new Item.ItemOption(108, 10));
                                InventoryServiceNew.gI().addItemBag(player, yardart);
                                InventoryServiceNew.gI().subQuantityItemsBag(player, biKiep, 10000);
                                InventoryServiceNew.gI().sendItemBags(player);
                                Service.gI().sendThongBao(player, "Bạn vừa nhận được trang phục tộc Yardart");
                            }
                        }
                    } catch (Exception ex) {

                    }
                }
            }
        };
    }

    public static Npc tapion(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 19) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Bạn muốn nâng cấp khỉ ư?", "Nâng cấp\nkhỉ", "Shop GOKU", "Từ chối");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 19) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, 1,
                                            "|7|Cần Khỉ Lv1 hoặc 2,4,6 để nâng cấp lên lv8\b|2|Mỗi lần nâng cấp tiếp thì mỗi cấp cần thêm 10-20 đá ngũ sắc",
                                            "Khỉ\ncấp 2",
                                            "Khỉ\ncấp 4",
                                            "Khỉ\ncấp 6",
                                            "Khỉ\ncấp 8",
                                            "Từ chối");
                                    break;
                                case 1: //shop
                                    ShopServiceNew.gI().opendShop(player, "KHI", false);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 1) { // action đổi dồ húy diệt
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv1 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1137);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1137 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1137 + i) && soLuong >= 20) {
                                            CombineServiceNew.gI().khilv2(player, 1138 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 20);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần 30 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1138);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1138 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1138 + i) && soLuong >= 30) {
                                            CombineServiceNew.gI().khilv3(player, 1139 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần 40 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1139);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1139 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1139 + i) && soLuong >= 40) {
                                            CombineServiceNew.gI().khilv4(player, 1140 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 40);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần 50 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1140);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1140 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1140 + i) && soLuong >= 50) {
                                            CombineServiceNew.gI().khilv5(player, 1136 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 50);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 3 với 50 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;

                                case 5: // canel
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
    
    public static Npc Kamin(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 0) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Bạn muốn nâng cấp khỉ ư?", "Nâng cấp\nkhỉ", "Shop Kamin", "Từ chối");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 0) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, 1,
                                            "|7|Cần Khỉ Lv1 hoặc 2,4,6 để nâng cấp lên lv8\b|2|Mỗi lần nâng cấp tiếp thì mỗi cấp cần thêm 10 đá ngũ sắc",
                                            "Khỉ\ncấp 2",
                                            "Khỉ\ncấp 4",
                                            "Khỉ\ncấp 6",
                                            "Khỉ\ncấp 8",
                                            "Từ chối");
                                    break;
                                case 1: //shop
                                    ShopServiceNew.gI().opendShop(player, "KAMIN", false);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 1) { // action đổi dồ húy diệt
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv1 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1137);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1137 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1137 + i) && soLuong >= 20) {
                                            CombineServiceNew.gI().khilv2(player, 1138 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 20);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 1 với 20 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1138);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1138 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1138 + i) && soLuong >= 30) {
                                            CombineServiceNew.gI().khilv3(player, 1139 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 2 với 30 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1139);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1139 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1139 + i) && soLuong >= 40) {
                                            CombineServiceNew.gI().khilv4(player, 1140 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 40);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 3 với 40 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1140);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1140 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1140 + i) && soLuong >= 50) {
                                            CombineServiceNew.gI().khilv5(player, 1136 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 50);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 3 với 50 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;

                                case 5: // canel
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
    
        public static Npc Cumber(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 7) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Bạn muốn nâng cấp khỉ ư?", "Nâng cấp\nkhỉ", "Shop Cumber", "Từ chối");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 7) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, 1,
                                            "|7|Cần Khỉ Lv1 hoặc 2,4,6 để nâng cấp lên lv8\b|2|Mỗi lần nâng cấp tiếp thì mỗi cấp cần thêm 10 đá ngũ sắc",
                                            "Khỉ\ncấp 2",
                                            "Khỉ\ncấp 4",
                                            "Khỉ\ncấp 6",
                                            "Khỉ\ncấp 8",
                                            "Từ chối");
                                    break;
                                case 1: //shop
                                    ShopServiceNew.gI().opendShop(player, "CUMBER", false);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 1) { // action đổi dồ húy diệt
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv1 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1137);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1137 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1137 + i) && soLuong >= 20) {
                                            CombineServiceNew.gI().khilv2(player, 1138 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 20);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 1 với 20 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1138);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1138 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1138 + i) && soLuong >= 30) {
                                            CombineServiceNew.gI().khilv3(player, 1139 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 2 với 30 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1139);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1139 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1139 + i) && soLuong >= 40) {
                                            CombineServiceNew.gI().khilv4(player, 1140 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 40);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 3 với 40 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item klv2 = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1140);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item klv = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1140 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 1140 + i) && soLuong >= 50) {
                                            CombineServiceNew.gI().khilv5(player, 1136 + i);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 50);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, klv, 1);
                                            this.npcChat(player, "Upgrede Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần cái trang khỉ cấp 3 với 50 đá ngũ sắc");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;

                                case 5: // canel
                                    break;
                            }
                        }
                    }
                }
            }
        };
        }
    
    

    public static Npc GhiDanh(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            String[] menuselect = new String[]{};
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (this.mapId == 52) {
                    createOtherMenu(pl, 0, DaiHoiVoThuatService.gI(DaiHoiVoThuat.gI().getDaiHoiNow()).Giai(pl), "Thông tin\nChi tiết", DaiHoiVoThuatService.gI(DaiHoiVoThuat.gI().getDaiHoiNow()).CanReg(pl) ? "Đăng ký" : "OK", "Đại Hội\nVõ Thuật\nLần thứ\n23");
                }else if(this.mapId == 129){
                        int goldchallenge = pl.goldChallenge;
                        if (pl.levelWoodChest == 0) {
                            menuselect = new String[]{"Thi đấu\n" + Util.numberToMoney(goldchallenge) + " vàng", "Về\nĐại Hội\nVõ Thuật"};
                        } else {
                            menuselect = new String[]{"Thi đấu\n" + Util.numberToMoney(goldchallenge) + " vàng", "Nhận thưởng\nRương cấp\n" + pl.levelWoodChest, "Về\nĐại Hội\nVõ Thuật"};
                        }
                        this.createOtherMenu(pl, ConstNpc.BASE_MENU, "Đại hội võ thuật lần thứ 23\nDiễn ra bất kể ngày đêm,ngày nghỉ ngày lễ\nPhần thưởng vô cùng quý giá\nNhanh chóng tham gia nào", menuselect, "Từ chối");

                    }else{
                    super.openBaseMenu(pl);
                    }
                    }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if(this.mapId == 52) {
                        switch (select) {
                            case 0:
                                Service.gI().sendPopUpMultiLine(player, tempId, avartar, DaiHoiVoThuat.gI().Info());
                                break;
                            case 1:
                                if (DaiHoiVoThuatService.gI(DaiHoiVoThuat.gI().getDaiHoiNow()).CanReg(player)) {
                                    DaiHoiVoThuatService.gI(DaiHoiVoThuat.gI().getDaiHoiNow()).Reg(player);
                                }
                                break;
                            case 2:
                                ChangeMapService.gI().changeMapNonSpaceship(player, 129, player.location.x, 360);
                                break;
                        }
                    }
                    else if (this.mapId == 129) {
                            int goldchallenge = player.goldChallenge;
                            if (player.levelWoodChest == 0) {
                                switch (select) {
                                    case 0:
                                        if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                            if (player.inventory.gold >= goldchallenge) {
                                                MartialCongressService.gI().startChallenge(player);
                                                player.inventory.gold -= (goldchallenge);
                                                PlayerService.gI().sendInfoHpMpMoney(player);
                                                player.goldChallenge += 2000000;
                                            } else {
                                                Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                            }
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                        }
                                        break;
                                    case 1:
                                        ChangeMapService.gI().changeMapNonSpaceship(player, 52, player.location.x, 336);
                                        break;
                                }
                            } else {
                                switch (select) {
                                    case 0:
                                        if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                            if (player.inventory.gold >= goldchallenge) {
                                                MartialCongressService.gI().startChallenge(player);
                                                player.inventory.gold -= (goldchallenge);
                                                PlayerService.gI().sendInfoHpMpMoney(player);
                                                player.goldChallenge += 2000000;
                                            } else {
                                                Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                            }
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                        }
                                        break;
                                    case 1:
                                        if (!player.receivedWoodChest) {
                                            if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                                Item it = ItemService.gI().createNewItem((short) 570);
                                                it.itemOptions.add(new Item.ItemOption(72, player.levelWoodChest));
                                                it.itemOptions.add(new Item.ItemOption(30, 0));
                                                it.createTime = System.currentTimeMillis();
                                                InventoryServiceNew.gI().addItemBag(player, it);
                                                InventoryServiceNew.gI().sendItemBags(player);

                                                player.receivedWoodChest = true;
                                                player.levelWoodChest = 0;
                                                Service.getInstance().sendThongBao(player, "Bạn nhận được rương gỗ");
                                            } else {
                                                this.npcChat(player, "Hành trang đã đầy");
                                            }
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Mỗi ngày chỉ có thể nhận rương báu 1 lần");
                                        }
                                        break;
                                    case 2:
                                        ChangeMapService.gI().changeMapNonSpaceship(player, 52, player.location.x, 336);
                                        break;
                                }
                            }
                        }
                    }
                }
        };
    }

    public static Npc unkonw(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, 0,
                                "Éc éc Bạn muốn gì ở tôi :3?", "Đến Võ đài ", "Võ Đài Siêu Cấp");
                                
                    }
                    if (this.mapId == 112) {
                        this.createOtherMenu(player, 0,
                                "Bạn đang còn : " + player.pointPvp + " điểm PvP Point", "Về đảo Kame", "Đổi Cải trang sự kiên", "Top PVP");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    if (player.getSession().player.nPoint.power >= 10000000000L) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 112, -1, 495);
                                        Service.gI().changeFlag(player, Util.nextInt(8));
                                    } else {
                                        this.npcChat(player, "Bạn cần 10 tỷ sức mạnh mới có thể vào");
                                    }
                                    break; // qua vo dai
                                case 1:             
                                    if (player.getSession().player.nPoint.power >= 10000000000L) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 145, -1, 495);
                                        Service.gI().changeFlag(player, Util.nextInt(8));
                                    } else {
                                        this.npcChat(player, "Bạn cần 10 tỷ sức mạnh mới có thể vào");
                                    }
                                    break; // qua vo dai
                                    
                            }
                        }
                    }

                    if (this.mapId == 112) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 319);
                                    break; // ve dao kame
                                case 1:  // 
                                    this.createOtherMenu(player, 1,
                                            "Bạn có muốn đổi 500 điểm PVP lấy \n|6|Cải trang Mèo Kid Lân với tất cả chỉ số là 80%\n ", "Ok", "Không");
                                    // bat menu doi item
                                    break;

                                case 2:  // 
                                    Service.gI().showListTop(player, Manager.topPVP);
                                    // mo top pvp
                                    break;

                            }
                        }
                        if (player.iDMark.getIndexMenu() == 1) { // action doi item
                            switch (select) {
                                case 0: // trade
                                    if (player.pointPvp >= 500) {
                                        player.pointPvp -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (1104));
                                        item.itemOptions.add(new Item.ItemOption(49, 30));
                                        item.itemOptions.add(new Item.ItemOption(77, 15));
                                        item.itemOptions.add(new Item.ItemOption(103, 20));
                                        item.itemOptions.add(new Item.ItemOption(207, 0));
                                        item.itemOptions.add(new Item.ItemOption(33, 0));
//                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Cải Trang Thành Công !");
                                    } else {
                                        Service.gI().sendThongBao(player, "Không đủ điểm bạn còn " + (500 - player.pointPvp) + " Điểm nữa");
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc monaito(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 7) {
                        this.createOtherMenu(player, 0,
                                "Chào bạn tôi sẽ đưa bạn đến hành tinh Cereal?", "Đồng ý", "Từ chối");
                    }
                    if (this.mapId == 170) {
                        this.createOtherMenu(player, 0,
                                "Ta ở đây để đưa con về", "Về Làng Mori", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 7) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 170, -1, 264);
                                    break; // den hanh tinh cereal
                            }
                        }
                    }
                    if (this.mapId == 170) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 7, -1, 432);
                                    break; // quay ve

                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc granala(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "|1|Xin Chào,Sự Kiện 10/3 Đang Diễn Ra Các Cư Dân Có Thể Đổi Item Vip ở Đây nhé"
                                + "\n|3|Tách Ngọc Bội Lấy Điểm Sk"                              
                                + "\n|3|Đổi Công Thức Chế Tạo Đồ Thiên Sứ"
                                + "\n|3|Sử Dụng Ngọc Bội Để Đổi Random Item c2"
                                + "\n|3|Sử Dụng Điểm Sự Kiện Đổi Cải Trang Vip random Có Vĩnh Viễn"                               
                                + "\n|3|Thử Vận May Ra NGọc Rồng vip Tỉ Lệ Cao "
                                + "\n|6|Ngoài Ra Các Bạn Có Thể Trồng Dưa Hấu,Hãy Chat'duahau' để nhận dưa trồng", "Tách Ngọc Bội Lấy Điểm Sk", "Xem Điểm Sk", "Đổi Công Thức", "Đổi item Cấp 2", "Đổi Cải Trang", "Thử Vận May Ngọc Vip", "Tặng Dưa Hấu Cho Vua Hùng", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: //phân rã đồ thần linh
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.PHAN_RA_DO_THAN_LINH);
                                    break;
                                case 1:
                                    this.createOtherMenu(player, ConstNpc.NAP_THE, "|2|Không có gì :3 \nNgươi đang có: " + player.inventory.coupon + " điểm sự kiện", "Đóng");
                                    break;
                                case 2:
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DOI_DIEM_DUA, -1, "Đổi Công Thức Chế Tạo Đồ Thiên Sứ?\nTa Cần 200 điểm sự kiện đấy... ",
                                        "Đồng ý", "Từ chối");
                                     break;
                                case 3:
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DOI_DIEM_ITEMC2, -1, "Ta Sẽ Cho Con Item siêu cấp ngẫu nhiên?\nTa Cần 100 Điểm Sự Kiện... ",
                                        "Đồng ý", "Từ chối");
                                     break;            
                                case 4:
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DOI_DIEM_CT, -1, "Cần 999 Điểm Sự Kiện Để Lấy Cải Trang Random \nCó Tỉ Lệ May Mắn Được Vĩnh Viễn...Thử Ngay Nào ",
                                        "Đồng ý", "Từ chối");
                                     break;
                                case 5:
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DOI_ITEM_NR, -1, "Còn Thở Còn Gỡ Còn Điểm Còn Đổi ..?\nPhải giao cho ta 200 điểm sự kiện đấy...\nNếu May Mắn Sẽ Nhận Được Đồ Thiên Sứ jiren Và Nro Víp 1 Sao ",
                                        "Đồng ý", "Từ chối");
                                     break;
                                case 6:
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_GIAO_BONG, -1, "Dưa Hấu Ngoài Biển Đã Bị Ngươi Cướp ..?\nHãy Giao Dưa Hấu Để Nhận x1 Rương kho Báu của Ta...\nCần 1 Quả Dưa... ",
                                        "Đồng ý", "Từ chối");
                                     break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.PHAN_RA_DO_THAN_LINH:
                                    if (select == 0) {
                                        CombineServiceNew.gI().startCombine(player);
                                    }
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHAN_RA_DO_THAN_LINH) {
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                        }
                    }
                }
            }
        };
    }
    public static Npc mabu (int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 20) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn Đã Bị Bư béo Nuốt Hãy Tìm nó để tiêu diệt<");

                                    ChangeMapService.gI().changeMapBySpaceShip(player, 128, -1, 432);
                                } else {

                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn Đã Bị Bư béo Nuốt Hãy Tìm nó để tiêu diệt?", "Sợ chưa để anh về nhe cuuu");
                    } 
                        super.openBaseMenu(player);
          if (this.mapId == 128) {
                        this.createOtherMenu(player, 0,
                                "Sao mi vẫn chưa bị tiêu hóa à", "Sợ chưa để anh về nhe cuuu", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.BASE_MENU:
                            if (this.mapId == 20) {
                                if (select == 0) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 870);
                                }
                            }
                            break;
                     }
                   
                              if (this.mapId == 128) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 20, -1, 432);
                                    break; // quay ve

                            }
                        }
                    }
                }
            }
        };
    } 
      private static Npc popo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (player.getSession().is_gift_box) {
//                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn ta giúp gì nào?", "Giải tán bang hội", "Nhận quà\nđền bù");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn ta giúp gì nào?","Đến\nkhí Ga");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                if (player.clan != null) {
                                    if (player.clan.khigas != null) {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_KG,
                                                "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
                                                        + player.clan.khigas.level + "\nCon có muốn đi theo không?",
                                                "Đồng ý", "Từ chối");
                                    } else {

                                        this.createOtherMenu(player, ConstNpc.MENU_OPEN_KG,
                                                "Đây là bản đồ kho báu \nCác con cứ yên tâm lên đường\n"
                                                        + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                "Chọn\ncấp độ", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                }
                                break;

                         }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_KG) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= KhiGas.POWER_CAN_GO_TO_KG) {
                                    ChangeMapService.gI().goToKG(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(KhiGas.POWER_CAN_GO_TO_KG));
                                }
                                break;

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_KG) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= KhiGas.POWER_CAN_GO_TO_KG) {
                                    Input.gI().createFormChooseLevelKG(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(KhiGas.POWER_CAN_GO_TO_KG));
                                }
                                break;
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_KG) {
                        switch (select) {
                            case 0:
                                KhiGasService.gI().openKhiGas(player, Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                break;
                        }
                    }
                }
            }
        };
    }
    private static Npc maygapthu(int mapId, int status, int cx, int cy, int tempId, int avartar) {
    return new Npc(mapId, status, cx, cy, tempId, avartar) {
        @Override
        public void openBaseMenu(Player player) {
            if (canOpenNpc(player)) {
                if (this.mapId == 5) {
                    this.createOtherMenu(player, 1234, "|7|Source By Cường Trần\nMÁY GẮP LINH THÚ\n"+"|6|GẮP THƯỜNG : 5-10% CHỈ SỐ\nGẮP CAO CẤP : 10-20% CHỈ SỐ\nGẮP VIP : 15-25% CHỈ SỐ"+"\nGẮP X1 : GẮP THỦ CÔNG\nGẮP X10 : AUTO X10 LẦN GẮP\nGẮP X100 : AUTO X100 LẦN GẮP\n"+"|4|LƯU Ý : MỌI CHỈ SỐ ĐỀU RANDOM KHÔNG CÓ OPTION NHẤT ĐỊNH\nNẾU MUỐN NGƯNG AUTO GẤP CHỈ CẦN THOÁT GAME VÀ VÀO LẠI!",
                    "Gắp Thường","Gắp Cao Cấp","Gắp VIP","Xem Top","Rương Đồ");
                }
            }
        }
        @Override
        public void confirmMenu(Player player, int select) {
            if (canOpenNpc(player)) {
                if (this.mapId == 5) {
                    if (player.iDMark.getIndexMenu()==1234) {
                        switch (select) {
                            case 0:
                                this.createOtherMenu(player, 12345, "|6|Gắp Pet Thường"+"\n|7|địt nhau ngay!",
                                 "Gắp x1","Gắp x10","Gắp x100","Rương Đồ");
                                break;
                            case 1:
                                this.createOtherMenu(player, 12346, "|6|Gắp Pet Cao Cấp"+"\n|7|địt nhau ngay!",
                                 "Gắp x1","Gắp x10","Gắp x100","Rương Đồ");
                                break;
                            case 2:
                                this.createOtherMenu(player, 12347, "|6|Gắp Pet VIP"+"\n|7|địt nhau ngay!",
                                 "Gắp x1","Gắp x10","Gắp x100","Rương Đồ");
                                break;
                            case 3:
                                Service.gI().sendThongBaoFromAdmin(player, "Số lần đã gắp của bạn : "+player.NguHanhSonPoint);
                                break;
                            case 4:
                                this.createOtherMenu(player, ConstNpc.RUONG_PHU,
                                        "|1|Tình yêu như một dây đàn\n" +
                                        "Tình vừa được thì đàn đứt dây\n" +
                                        "Đứt dây này anh thay dây khác\n" +
                                        "Mất em rồi anh biết thay ai?",
                                        "Rương Phụ\n("+ (player.inventory.itemsBoxCrackBall.size()
                                        - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                        + " món)",
                                        "Xóa Hết\nRương Phụ", "Đóng");
                                break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 12345) {
                        switch (select) { 
                            case 0:
                                if (player.inventory.ruby < 300) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                    Service.gI().sendThongBao(player, "Hết chỗ trống rồi");
                                    return;
                                }
                                    player.inventory.ruby -= 300;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                Item gapt = Util.petrandom(Util.nextInt(1143,1154));
                                if(Util.isTrue(10, 100)) {
                                    InventoryServiceNew.gI().addItemBag(player, gapt);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12345, "|2|Bạn vừa gắp được : "+gapt.template.name+"\nSố Hồng Ngọc Trừ : 100"+"\n|7|Chiến tiếp ngay!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                } else {
                                    this.createOtherMenu(player, 12345, "|6|Gắp hụt rồi, bạn bỏ cuộc sao?"+"\nSố Hồng Ngọc Trừ : 100"+"\n|7|Chiến tiếp ngay!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }
                                break;
                            case 1:
                                if (player.inventory.ruby < 3000) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                try {
                                Service.gI().sendThongBao(player, "Tiến hành auto gắp x10 lần");
                                int timex10 = 10;
                                int hn = 0;
                                while (timex10 > 0) {
                                    timex10--;
                                    hn+=100;
                                    Thread.sleep(100);
                                    if(1+player.inventory.itemsBoxCrackBall.size() > 100) {
                                    this.createOtherMenu(player, 12345, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n"+"|2|TỔNG LƯỢT GẮP : "+hn/800+" LƯỢT"+"\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    break;
                                    }
                                    player.inventory.ruby -= 300;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                    Item gapx10 = Util.petrandom(Util.nextInt(1143,1154));  
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                    if(Util.isTrue(10, 100)) {
                                    InventoryServiceNew.gI().addItemBag(player, gapx10);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Đã gắp được : "+gapx10.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    }}
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) == 0){
                                    if(Util.isTrue(10, 100)) {
                                    player.inventory.itemsBoxCrackBall.add(gapx10);
                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Đã gắp được : "+gapx10.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }}}} catch (Exception e) {
                                }
                                break;
                            case 2:
                                if (player.inventory.ruby < 30000) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                try {
                                Service.gI().sendThongBao(player, "Tiến hành auto gắp x100 lần");
                                int timex100 = 100;
                                int hn = 0;
                                while (timex100 > 0) {
                                    timex100--;
                                    hn+=100;
                                    Thread.sleep(100);
                                    if(1+player.inventory.itemsBoxCrackBall.size() > 100) {
                                    this.createOtherMenu(player, 12345, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n"+"|2|TỔNG LƯỢT GẮP : "+hn/800+" LƯỢT"+"\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    break;
                                    }
                                    player.inventory.ruby -= 300;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                    Item gapx100 = Util.petrandom(Util.nextInt(1143,1154));  
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                    if(Util.isTrue(10, 100)) {
                                    InventoryServiceNew.gI().addItemBag(player, gapx100);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X100\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Đã gắp được : "+gapx100.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12345, "|7|ĐANG TIẾN HÀNH GẮP AUTO X100\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    }}
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) == 0){
                                    if(Util.isTrue(10, 100)) {
                                    player.inventory.itemsBoxCrackBall.add(gapx100);
                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X100 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Đã gắp được : "+gapx100.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12345, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X100 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }}}} catch (Exception e) {
                                }
                                break;
                            case 3:
                                this.createOtherMenu(player, ConstNpc.RUONG_PHU,
                                        "|1|Tình yêu như một dây đàn\n" +
                                        "Tình vừa được thì đàn đứt dây\n" +
                                        "Đứt dây này anh thay dây khác\n" +
                                        "Mất em rồi anh biết thay ai?",
                                        "Rương Phụ\n("+ (player.inventory.itemsBoxCrackBall.size()
                                        - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                        + " món)",
                                        "Xóa Hết\nRương Phụ", "Đóng");
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 12346) {
                        switch (select) { 
                            case 0:
                                if (player.inventory.ruby < 400) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                    Service.gI().sendThongBao(player, "Hết chỗ trống rồi");
                                    return;
                                }
                                    player.inventory.ruby -= 600;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                Item gapt = Util.petccrandom(Util.nextInt(1167,1178));
                                if(Util.isTrue(12, 100)) {
                                    InventoryServiceNew.gI().addItemBag(player, gapt);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12346, "|2|Bạn vừa gắp được : "+gapt.template.name+"\nSố Hồng Ngọc Trừ : 100"+"\n|7|Chiến tiếp ngay!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                } else {
                                    this.createOtherMenu(player, 12346, "|6|Gắp hụt rồi, bạn bỏ cuộc sao?"+"\n|7|Chiến tiếp ngay!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }
                                break;
                            case 1:
                                if (player.inventory.ruby < 4000) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                try {
                                Service.gI().sendThongBao(player, "Tiến hành auto gắp x10 lần");
                                int timex10 = 10;
                                int hn = 0;
                                while (timex10 > 0) {
                                    timex10--;
                                    hn+=400;
                                    Thread.sleep(100);
                                    if(1+player.inventory.itemsBoxCrackBall.size() > 100) {
                                    this.createOtherMenu(player, 12346, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n"+"|2|TỔNG LƯỢT GẮP : "+hn/800+" LƯỢT"+"\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    break;
                                    }
                                    player.inventory.ruby -= 600;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                    Item gapx10 = Util.petccrandom(Util.nextInt(1167,1178));  
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                    if(Util.isTrue(12, 100)) {
                                    InventoryServiceNew.gI().addItemBag(player, gapx10);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Đã gắp được : "+gapx10.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    }}
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) == 0){
                                    if(Util.isTrue(10, 100)) {
                                    player.inventory.itemsBoxCrackBall.add(gapx10);
                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Đã gắp được : "+gapx10.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }}}} catch (Exception e) {
                                }
                                break;
                            case 2:
                                if (player.inventory.ruby < 60000) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                try {
                                Service.gI().sendThongBao(player, "Tiến hành auto gắp x100 lần");
                                int timex100 = 100;
                                int hn = 0;
                                while (timex100 > 0) {
                                    timex100--;
                                    hn+=400;
                                    Thread.sleep(100);
                                    if(1+player.inventory.itemsBoxCrackBall.size() > 100) {
                                    this.createOtherMenu(player, 12346, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n"+"|2|TỔNG LƯỢT GẮP : "+hn/800+" LƯỢT"+"\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    break;
                                    }
                                    player.inventory.ruby -= 600;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                    Item gapx100 = Util.petccrandom(Util.nextInt(1167,1178));  
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                    if(Util.isTrue(12, 100)) {
                                    InventoryServiceNew.gI().addItemBag(player, gapx100);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X100\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Đã gắp được : "+gapx100.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12346, "|7|ĐANG TIẾN HÀNH GẮP AUTO X100\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    }}
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) == 0){
                                    if(Util.isTrue(10, 100)) {
                                    player.inventory.itemsBoxCrackBall.add(gapx100);
                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X100 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Đã gắp được : "+gapx100.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12346, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X100 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }}}} catch (Exception e) {
                                }
                                break;
                            case 3:
                                this.createOtherMenu(player, ConstNpc.RUONG_PHU,
                                        "|1|Tình yêu như một dây đàn\n" +
                                        "Tình vừa được thì đàn đứt dây\n" +
                                        "Đứt dây này anh thay dây khác\n" +
                                        "Mất em rồi anh biết thay ai?",
                                        "Rương Phụ\n("+ (player.inventory.itemsBoxCrackBall.size()
                                        - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                        + " món)",
                                        "Xóa Hết\nRương Phụ", "Đóng");
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 12347) {
                        switch (select) { 
                            case 0:
                                if (player.inventory.ruby < 1000) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                    Service.gI().sendThongBao(player, "Hết chỗ trống rồi");
                                    return;
                                }
                                    player.inventory.ruby -= 1000;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                Item gapt = ItemService.gI().createNewItem((short)Util.nextInt(1280,1295));
                                if(Util.isTrue(15, 100)) {
                                    gapt.itemOptions.add(new ItemOption(50,Util.nextInt(20,30)));
                                    gapt.itemOptions.add(new ItemOption(77,Util.nextInt(20,30)));
                                    gapt.itemOptions.add(new ItemOption(103,Util.nextInt(20,30)));
                                    if(Util.isTrue(80, 100)) {
                                    gapt.itemOptions.add(new ItemOption(93,Util.nextInt(7,15)));    
                                    }
                                    InventoryServiceNew.gI().addItemBag(player, gapt);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12347, "|2|Bạn vừa gắp được : "+gapt.template.name+"\nSố Hồng Ngọc Trừ : 100"+"\n|7|Chiến tiếp ngay!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                } else {
                                    this.createOtherMenu(player, 12347, "|6|Gắp hụt rồi, bạn bỏ cuộc sao?"+"\n|7|Thất bại ở đâu gắp đôi ở đó!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }
                                break;
                            case 1:
                                if (player.inventory.ruby < 10000) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                try {
                                Service.gI().sendThongBao(player, "Tiến hành auto gắp x10 lần");
                                int timex10 = 10;
                                int hn = 0;
                                while (timex10 > 0) {
                                    timex10--;
                                    hn+=800;
                                    Thread.sleep(100);
                                    if(1+player.inventory.itemsBoxCrackBall.size() > 100) {
                                    this.createOtherMenu(player, 12347, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n"+"|2|TỔNG LƯỢT GẮP : "+hn/800+" LƯỢT"+"\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    break;
                                    }
                                    player.inventory.ruby -= 1000;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                    Item gapx10 = Util.petviprandom(Util.nextInt(1280,1295));  
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                    if(Util.isTrue(15, 100)) {
                                    InventoryServiceNew.gI().addItemBag(player, gapx10);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Đã gắp được : "+gapx10.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X10\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    }}
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) == 0){
                                    if(Util.isTrue(15, 100)) {
                                    player.inventory.itemsBoxCrackBall.add(gapx10);
                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Đã gắp được : "+gapx10.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X10 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex10+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }}}} catch (Exception e) {
                                }
                                break;
                            case 2:
                                if (player.inventory.ruby < 100000) {
                                    Service.gI().sendThongBao(player, "Hết Tiền Roài");
                                    return;
                                }
                                try {
                                Service.gI().sendThongBao(player, "Tiến hành auto gắp x100 lần");
                                int timex100 = 100;
                                int hn = 0;
                                while (timex100 > 0) {
                                    timex100--;
                                    hn+=800;
                                    Thread.sleep(100);
                                    if(1+player.inventory.itemsBoxCrackBall.size() > 100) {
                                    this.createOtherMenu(player, 12347, "|7|DỪNG AUTO GẮP, RƯƠNG PHỤ ĐÃ ĐẦY!\n"+"|2|TỔNG LƯỢT GẮP : "+hn/800+" LƯỢT"+"\n|7|VUI LÒNG LÀM TRỐNG RƯƠNG PHỤ!",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    break;
                                    }
                                    player.inventory.ruby -= 1000;
                                    player.NguHanhSonPoint += 1;
                                    Service.gI().sendMoney(player);
                                    Item gapx100 = Util.petviprandom(Util.nextInt(1280,1295));  
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                    if(Util.isTrue(15, 100)) {
                                    InventoryServiceNew.gI().addItemBag(player, gapx100);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X100\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Đã gắp được : "+gapx100.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12347, "|7|ĐANG TIẾN HÀNH GẮP AUTO X100\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint+"\nNẾU HÀNH TRANG ĐẦY, ITEM SẼ ĐƯỢC THÊM VÀO RƯƠNG PHỤ",
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    }}
                                    if(InventoryServiceNew.gI().getCountEmptyBag(player) == 0){
                                    if(Util.isTrue(15, 100)) {
                                    player.inventory.itemsBoxCrackBall.add(gapx100);
                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X100 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Đã gắp được : "+gapx100.template.name+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                    } else {
                                    this.createOtherMenu(player, 12347, "|7|HÀNH TRANG ĐÃ ĐẦY\nĐANG TIẾN HÀNH GẮP AUTO X100 VÀO RƯƠNG PHỤ\nSỐ LƯỢT CÒN : "+timex100+" LƯỢT\n"+"|2|Gắp hụt rồi!"+"\nSố hồng ngọc đã trừ : "+hn+"\n|7|TỔNG ĐIỂM : "+player.NguHanhSonPoint,
                                    "Gắp X1","Gắp X10","Gắp X100","Rương Đồ");
                                }}}} catch (Exception e) {
                                }
                                break;
                            case 3:
                                this.createOtherMenu(player, ConstNpc.RUONG_PHU,
                                        "|1|Tình yêu như một dây đàn\n" +
                                        "Tình vừa được thì đàn đứt dây\n" +
                                        "Đứt dây này anh thay dây khác\n" +
                                        "Mất em rồi anh biết thay ai?",
                                        "Rương Phụ\n("+ (player.inventory.itemsBoxCrackBall.size()
                                        - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                        + " món)",
                                        "Xóa Hết\nRương Phụ", "Đóng");
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.RUONG_PHU) {
                        switch (select) {
                            case 0:
                              ShopServiceNew.gI().opendShop(player, "ITEMS_LUCKY_ROUND", true);
                                break;
                            case 1:
                                NpcService.gI().createMenuConMeo(player,
                                        ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND, this.avartar,
                                        "|3|Bạn chắc muốn xóa hết vật phẩm trong rương phụ?\n"
                                                +"|7|Sau khi xóa sẽ không thể khôi phục!",
                                        "Đồng ý", "Hủy bỏ");
                                break;
                        }
                    }
                }
            }
        }
    };
}

    public static Npc createNPC(int mapId, int status, int cx, int cy, int tempId) {
        int avatar = Manager.NPC_TEMPLATES.get(tempId).avatar;
        try {
            switch (tempId) {
                case ConstNpc.UNKOWN:
                    return unkonw(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GHI_DANH:
                    return GhiDanh(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.TRUNG_LINH_THU:
                    return trungLinhThu(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.POTAGE:
                    return poTaGe(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUY_LAO_KAME:
                    return quyLaoKame(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.POPO:
                    return popo(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THO_DAI_CA:
                    return thodaika(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.TRUONG_LAO_GURU:
                    return truongLaoGuru(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.VUA_VEGETA:
                    return vuaVegeta(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.ONG_GOHAN:
                case ConstNpc.ONG_MOORI:
                case ConstNpc.ONG_PARAGUS:
                    return ongGohan_ongMoori_ongParagus(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BUNMA:
                    return bulmaQK(mapId, status, cx, cy, tempId, avatar);
  //              case ConstNpc.DUA_HAU:
//                    return duahau(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DENDE:
                    return dende(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.APPULE:
                    return appule(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.KAMIN:
                    return Kamin(mapId, status, cx, cy, tempId, avatar); 
                case ConstNpc.DR_DRIEF:
                    return drDrief(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CARGO:
                    return cargo(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CUI:
                    return cui(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.SANTA:
                    return santa(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.URON:
                    return uron(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BA_HAT_MIT:
                    return baHatMit(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.RUONG_DO:
                    return ruongDo(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DAU_THAN:
                    return dauThan(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CALICK:
                    return calick(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.JACO:
                    return jaco(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THUONG_DE:
                    return thuongDe(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CUA_HANG_KY_GUI:
                    return kyGui(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.Granola:
                    return granala(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GIUMA_DAU_BO:
                    return mavuong(mapId, status, cx, cy, tempId, avatar);  
                case ConstNpc.Monaito:
                    return monaito(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.VADOS:
                    return vados(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.TAPION:
                    return tapion(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THAN_VU_TRU:
                    return thanVuTru(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.KIBIT:
                    return kibit(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.OSIN:
                    return osin(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.LY_TIEU_NUONG:
                    return npclytieunuong54(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.LINH_CANH:
                    return linhCanh(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUA_TRUNG:
                    return quaTrung(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUOC_VUONG:
                    return quocVuong(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BUNMA_TL:
                    return bulmaTL(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.RONG_OMEGA:
                    return rongOmega(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.RONG_1S:
                case ConstNpc.RONG_2S:
                case ConstNpc.RONG_3S:
                case ConstNpc.RONG_4S:
                case ConstNpc.RONG_5S:
                case ConstNpc.RONG_6S:
                case ConstNpc.RONG_7S:
                    return rong1_to_7s(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.NPC_64:
                    return npcThienSu64(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.MI_NUONG:
                //    return npcminuong(mapId, status, cx, cy, tempId, avatar);    
                case ConstNpc.BILL:
                    return Berrus(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.WHIS:
                    return whis(mapId, status, cx, cy, tempId, avatar);   
                case ConstNpc.BO_MONG:
                    return boMong(mapId, status, cx, cy, tempId, avatar);
                  case  ConstNpc.DE_TU:
                    return DETU(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THAN_MEO_KARIN:
                    return karin(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.MAY_GAP_THU:
                    return maygapthu(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GOKU_SSJ:
                    return gokuSSJ_1(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GOKU_SSJ_:
                    return gokuSSJ_2(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DUONG_TANG:
                    return duongtank(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.BU_CU:
                    return bucac(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.BU_CU1:
                    return bucac1(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.ONGIA:
                    return sukien(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.TAI_XIU:
                    return taixiu(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.GOKU_GOD:
                    return gokugod(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.THO_REN:
                    return thoren(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.THUAN:
                    return thuan(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.TO_SU_KAIO:
                    return tosukaio(mapId, status, cx, cy, tempId, avatar);
                 case ConstNpc.NOI_BANH:
                    return noibanh(mapId, status, cx, cy, tempId, avatar);   
                default:
                    return new Npc(mapId, status, cx, cy, tempId, avatar) {
                        @Override
                        public void openBaseMenu(Player player) {
                            if (canOpenNpc(player)) {
                                super.openBaseMenu(player);
                            }
                        }

                        @Override
                        public void confirmMenu(Player player, int select) {
                            if (canOpenNpc(player)) {
//                                ShopService.gI().openShopNormal(player, this, ConstNpc.SHOP_BUNMA_TL_0, 0, player.gender);
                            }
                        }
                    };
            }
        } catch (Exception e) {
            Logger.logException(NpcFactory.class, e, "Lỗi load npc");
            return null;
        }
    }

    //girlbeo-mark
   public static void createNpcRongThieng() {
       Npc npc = new Npc(-1, -1, -1, -1, ConstNpc.RONG_THIENG, -1) {
           @Override
            public void confirmMenu(Player player, int select) {
                switch (player.iDMark.getIndexMenu()) {
                    case ConstNpc.IGNORE_MENU:

                      break;
                    case ConstNpc.SHENRON_CONFIRM:
                        if (select == 0) {
                            SummonDragon.gI().confirmWish();
                        } else if (select == 1) {
                            SummonDragon.gI().reOpenShenronWishes(player);
                        }
                        break;
                    case ConstNpc.SHENRON_1_1:
                        if (player.iDMark.getIndexMenu() == ConstNpc.SHENRON_1_1 && select == SHENRON_1_STAR_WISHES_1.length - 1) {
                            NpcService.gI().createMenuRongThieng(player, ConstNpc.SHENRON_1_2, SHENRON_SAY, SHENRON_1_STAR_WISHES_2);
                            break;
                        }
                    case ConstNpc.SHENRON_1_2:
                        if (player.iDMark.getIndexMenu() == ConstNpc.SHENRON_1_2 && select == SHENRON_1_STAR_WISHES_2.length - 1) {
                            NpcService.gI().createMenuRongThieng(player, ConstNpc.SHENRON_1_1, SHENRON_SAY, SHENRON_1_STAR_WISHES_1);
                            break;
                        }
                    default:
                        SummonDragon.gI().showConfirmShenron(player, player.iDMark.getIndexMenu(), (byte) select);
                       break;
                }
            }
        };
    }

    public static void createNpcConMeo() {
        Npc npc = new Npc(-1, -1, -1, -1, ConstNpc.CON_MEO, 351) {
            @Override
            public void confirmMenu(Player player, int select) {
                switch (player.iDMark.getIndexMenu()) {
                    case ConstNpc.IGNORE_MENU:

                        break;
                    case ConstNpc.MAKE_MATCH_PVP: //                        if (player.getSession().actived) 
                    {
                        if (Maintenance.isRuning) {
                            break;
                        }
                        PVPService.gI().sendInvitePVP(player, (byte) select);
                        break;
                    }
//                        else {
//                            Service.gI().sendThongBao(player, "|5|VUI LÒNG KÍCH HOẠT TÀI KHOẢN TẠI\n|7|NROGOD.COM\n|5|ĐỂ MỞ KHÓA TÍNH NĂNG");
//                            break;
//                        }
                    case ConstNpc.MAKE_FRIEND:
                        if (select == 0) {
                            Object playerId = PLAYERID_OBJECT.get(player.id);
                            if (playerId != null) {
                                FriendAndEnemyService.gI().acceptMakeFriend(player,
                                        Integer.parseInt(String.valueOf(playerId)));
                            }
                        }
                        break;
                    case ConstNpc.REVENGE:
                        if (select == 0) {
                            PVPService.gI().acceptRevenge(player);
                        }
                        break;
                    case ConstNpc.TUTORIAL_SUMMON_DRAGON:
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TUTORIAL);
                        }
                        break;
                    case ConstNpc.SUMMON_SHENRON:
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TUTORIAL);
                        } else if (select == 1) {
                            SummonDragon.gI().summonShenron(player);
                        }
                        break;
                        case ConstNpc.TUTORIAL_SUMMON_DRAGONTRB://TRB
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TRB);
                        }
                        break;
                         case ConstNpc.SUMMON_SHENRONTRB:
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TRB);
                        } else if (select == 1) {
                            SummonDragon.gI().summonShenronTRB(player);
                        }
                       break;
                    case ConstNpc.MENU_OPTION_USE_ITEM1105:
                        if (select == 0) {
                            IntrinsicService.gI().sattd(player);
                        } else if (select == 1) {
                            IntrinsicService.gI().satnm(player);
                        } else if (select == 2) {
                            IntrinsicService.gI().setxd(player);
                        }
                        break;
                    case ConstNpc.MENU_OPTION_USE_ITEM2000:
                    case ConstNpc.MENU_OPTION_USE_ITEM2001:
                    case ConstNpc.MENU_OPTION_USE_ITEM2002:
                        try {
                        ItemService.gI().OpenSKH(player, player.iDMark.getIndexMenu(), select);
                    } catch (Exception e) {
                        Logger.error("Lỗi mở hộp quà");
                    }
                    break;
                    case ConstNpc.MENU_OPTION_USE_ITEM2003:
                    case ConstNpc.MENU_OPTION_USE_ITEM2004:
                    case ConstNpc.MENU_OPTION_USE_ITEM2005:
                        try {
                        ItemService.gI().OpenDHD(player, player.iDMark.getIndexMenu(), select);
                    } catch (Exception e) {
                        Logger.error("Lỗi mở hộp quà");
                    }
                    break;
                    case ConstNpc.MENU_OPTION_USE_ITEM736:
                        try {
                        ItemService.gI().OpenDHD(player, player.iDMark.getIndexMenu(), select);
                    } catch (Exception e) {
                        Logger.error("Lỗi mở hộp quà");
                    }
                    break;
                    case ConstNpc.INTRINSIC:
                        if (select == 0) {
                            IntrinsicService.gI().showAllIntrinsic(player);
                        } else if (select == 1) {
                            IntrinsicService.gI().showConfirmOpen(player);
                        } else if (select == 2) {
                            IntrinsicService.gI().showConfirmOpenVip(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_OPEN_INTRINSIC:
                        if (select == 0) {
                            IntrinsicService.gI().open(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_OPEN_INTRINSIC_VIP:
                        if (select == 0) {
                            IntrinsicService.gI().openVip(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_LEAVE_CLAN:
                        if (select == 0) {
                            ClanService.gI().leaveClan(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_NHUONG_PC:
                        if (select == 0) {
                            ClanService.gI().phongPc(player, (int) PLAYERID_OBJECT.get(player.id));
                        }
                        break;
                    case ConstNpc.BAN_PLAYER:
                        if (select == 0) {
                            PlayerService.gI().banPlayer((Player) PLAYERID_OBJECT.get(player.id));
                            Service.gI().sendThongBao(player, "Ban người chơi " + ((Player) PLAYERID_OBJECT.get(player.id)).name + " thành công");
                        }
                        break;

                    case ConstNpc.BUFF_PET:
                        if (select == 0) {
                            Player pl = (Player) PLAYERID_OBJECT.get(player.id);
                            if (pl.pet == null) {
                                PetService.gI().createNormalPet(pl);
                                Service.gI().sendThongBao(player, "Phát đệ tử cho " + ((Player) PLAYERID_OBJECT.get(player.id)).name + " thành công");
                            }
                        }
                        break;
                    case ConstNpc.UP_TOP_ITEM:

                    case ConstNpc.MENU_ADMIN:
                        switch (select) {
                            case 0:
                                for (int i = 14; i <= 20; i++) {
                                    Item item = ItemService.gI().createNewItem((short) i);
                                    InventoryServiceNew.gI().addItemBag(player, item);
                                }
                                InventoryServiceNew.gI().sendItemBags(player);
                                break;
                            case 1:
                                if (player.pet == null) {
                                    PetService.gI().createNormalPet(player);
                                } else {
                                    if (player.pet.typePet == 1) {
                                        PetService.gI().changePicPet(player);
                                    } else if (player.pet.typePet == 2) {
                                        PetService.gI().changeMabuPet(player);
                                    }
                                    PetService.gI().changeBerusPet(player);
                                }
                                break;
                            case 2:
                                if (player.isAdmin()) {
                                    System.out.println(player.name);
//                                PlayerService.gI().baoTri();
                                    Maintenance.gI().start(15);
                                    System.out.println(player.name);
                                }
                                break;
                            case 3:
                                Input.gI().createFormFindPlayer(player);
                                break;
                            case 4:
                                BossManager.gI().showListBoss(player);
                                break;
                            case 5:
                                MaQuaTangManager.gI().checkInfomationGiftCode(player);
                                break;
                        }
                        break;

                    case ConstNpc.menutd:
                        switch (select) {
                            case 0:
                                try {
                                ItemService.gI().settaiyoken(player);
                            } catch (Exception e) {
                            }
                            break;
                            case 1:
                                try {
                                ItemService.gI().setgenki(player);
                            } catch (Exception e) {
                            }
                            break;
                            case 2:
                                try {
                                ItemService.gI().setkamejoko(player);
                            } catch (Exception e) {
                            }
                            break;
                        }
                        break;

                    case ConstNpc.menunm:
                        switch (select) {
                            case 0:
                                try {
                                ItemService.gI().setgodki(player);
                            } catch (Exception e) {
                            }
                            break;
                            case 1:
                                try {
                                ItemService.gI().setgoddam(player);
                            } catch (Exception e) {
                            }
                            break;
                            case 2:
                                try {
                                ItemService.gI().setsummon(player);
                            } catch (Exception e) {
                            }
                            break;
                        }
                        break;

                    case ConstNpc.menuxd:
                        switch (select) {
                            case 0:
                                try {
                                ItemService.gI().setgodgalick(player);
                            } catch (Exception e) {
                            }
                            break;
                            case 1:
                                try {
                                ItemService.gI().setmonkey(player);
                            } catch (Exception e) {
                            }
                            break;
                            case 2:
                                try {
                                ItemService.gI().setgodhp(player);
                            } catch (Exception e) {
                            }
                            break;
                        }
                        break;

                    case ConstNpc.CONFIRM_DISSOLUTION_CLAN:
                        switch (select) {
                            case 0:
                                Clan clan = player.clan;
                                clan.deleteDB(clan.id);
                                Manager.CLANS.remove(clan);
                                player.clan = null;
                                player.clanMember = null;
                                ClanService.gI().sendMyClan(player);
                                ClanService.gI().sendClanId(player);
                                Service.gI().sendThongBao(player, "Đã giải tán bang hội.");
                                break;
                        }
                        break;
//                    case ConstNpc.CONFIRM_ACTIVE:
//                        switch (select) {
//                            case 0:
//                                if (player.getSession().goldBar >= 20) {
//                                    player.getSession().actived = true;
//                                    if (PlayerDAO.subGoldBar(player, 20)) {
//                                        Service.gI().sendThongBao(player, "Đã mở thành viên thành công!");
//                                        break;
//                                    } else {
//                                        this.npcChat(player, "Lỗi vui lòng báo admin...");
//                                    }
//                                }
////                                Service.gI().sendThongBao(player, "Bạn không có vàng\n Vui lòng NROGOD.COM để nạp thỏi vàng");
//                                break;
//                        }
//                        break;
                    case ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND:
                        if (select == 0) {
                            for (int i = 0; i < player.inventory.itemsBoxCrackBall.size(); i++) {
                                player.inventory.itemsBoxCrackBall.set(i, ItemService.gI().createItemNull());
                            }
                            player.inventory.itemsBoxCrackBall.clear();
                            Service.gI().sendThongBao(player, "Đã xóa hết vật phẩm trong rương");
                        }
                        break;
                    case ConstNpc.MENU_FIND_PLAYER:
                        Player p = (Player) PLAYERID_OBJECT.get(player.id);
                        if (p != null) {
                            switch (select) {
                                case 0:
                                    if (p.zone != null) {
                                        ChangeMapService.gI().changeMapYardrat(player, p.zone, p.location.x, p.location.y);
                                    }
                                    break;
                                case 1:
                                    if (p.zone != null) {
                                        ChangeMapService.gI().changeMap(p, player.zone, player.location.x, player.location.y);
                                    }
                                    break;
                                case 2:
                                    Input.gI().createFormChangeName(player, p);
                                    break;
                                case 3:
                                    String[] selects = new String[]{"Đồng ý", "Hủy"};
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.BAN_PLAYER, -1,
                                            "Bạn có chắc chắn muốn ban " + p.name, selects, p);
                                    break;
                                case 4:
                                    Service.gI().sendThongBao(player, "Kik người chơi " + p.name + " thành công");
                                    Client.gI().getPlayers().remove(p);
                                    Client.gI().kickSession(p.getSession());
                                    break;
                            }
                        }
                        break;
                    case ConstNpc.MENU_EVENT:
                        switch (select) {
                            case 0:
                                Service.gI().sendThongBaoOK(player, "Điểm sự kiện: " + player.inventory.event + " ngon ngon...");
                                break;
                            case 1:
                                Service.gI().showListTop(player, Manager.topSK);
                                break;
                            case 2:
                                Service.gI().sendThongBao(player, "Sự kiện đã kết thúc...");
//                                NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_GIAO_BONG, -1, "Người muốn giao bao nhiêu bông...",
//                                        "100 bông", "1000 bông", "10000 bông");
                                break;
                            case 3:
                                Service.gI().sendThongBao(player, "Sự kiện đã kết thúc...");
//                                NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DOI_THUONG_SU_KIEN, -1, "Con có thực sự muốn đổi thưởng?\nPhải giao cho ta 3000 điểm sự kiện đấy... ",
//                                        "Đồng ý", "Từ chối");
                                break;

                        }
                        break;
                    case ConstNpc.MENU_GIAO_BONG:
                        ItemService.gI().giaobong(player, (int) Util.tinhLuyThua(10, select + 2));
                        break;
                    case ConstNpc.CONFIRM_DOI_THUONG_SU_KIEN:
                        if (select == 0) {
                            ItemService.gI().openBoxVip(player);
                        }
                        break;
                   case ConstNpc.CONFIRM_DOI_DIEM_DUA:
                        if (select == 0) {
                            ItemService.gI().openBoxCongThuc(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_DOI_DIEM_ITEMC2:
                        if (select == 0) {
                            ItemService.gI().openBoxitemc2(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_DOI_ITEM_NR:
                        if (select == 0) {
                            ItemService.gI().openBoxitemnr(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_DOI_DIEM_CT:
                        if (select == 0) {
                            ItemService.gI().openBoxCt(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_TELE_NAMEC:
                        if (select == 0) {
                            NgocRongNamecService.gI().teleportToNrNamec(player);
                            player.inventory.subGemAndRuby(50);
                            Service.gI().sendMoney(player);
                        }
                        break;
                }
            }
        };
    } 

}
