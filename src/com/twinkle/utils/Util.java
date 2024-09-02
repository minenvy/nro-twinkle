package com.twinkle.utils;

import com.twinkle.jdbc.daos.GodGK;
import com.twinkle.models.boss.BossManager;
import com.twinkle.models.item.Item;
import com.twinkle.models.map.ItemMap;
import com.twinkle.models.map.Zone;
import java.text.NumberFormat;
import java.util.*;

import com.twinkle.models.matches.TOP;
import com.twinkle.models.mob.Mob;
import com.twinkle.models.npc.Npc;
import com.twinkle.models.player.Player;
import com.girlkun.network.io.Message;
import com.twinkle.server.Client;
import com.twinkle.server.Manager;
import com.twinkle.services.ItemService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang.ArrayUtils;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

public class Util {

    private static final Random rand;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Locale locale = new Locale("vi", "VN");
    private static final NumberFormat num = NumberFormat.getInstance(locale);

    static {
        rand = new Random();
    }


    public static int createIdBossClone(int idPlayer) {
        return -idPlayer - 100_000_000;
    }
     public static int[] pickNRandInArr(int[] array, int n) {
        List<Integer> list = new ArrayList<Integer>(array.length);
        for (int i : array)
            list.add(i);
        Collections.shuffle(list);
        int[] answer = new int[n];
        for (int i = 0; i < n; i++)
            answer[i] = list.get(i);
        Arrays.sort(answer);
        return answer;
    }

    public static boolean contains(String[] arr, String key) {
        return Arrays.toString(arr).contains(key);
    }

    public static String numberToMoney(long power) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat num = NumberFormat.getInstance(locale);
        num.setMaximumFractionDigits(1);
        if (power >= 1000000000) {
            return num.format((double) power / 1000000000) + " Tỷ";
        } else if (power >= 1000000) {
            return num.format((double) power / 1000000) + " Tr";
        } else if (power >= 1000) {
            return num.format((double) power / 1000) + " k";
        } else {
            return num.format(power);
        }
    }
    
    public static Item petrandom(int tempId) {
        Item gapthuong = ItemService.gI().createNewItem((short) tempId);
        if(Util.isTrue(90, 100)) {    
            gapthuong.itemOptions.add(new Item.ItemOption(50, Util.nextInt(5,10)));
            gapthuong.itemOptions.add(new Item.ItemOption(103, Util.nextInt(5,10)));
            gapthuong.itemOptions.add(new Item.ItemOption(77, Util.nextInt(5,10)));
        if(Util.isTrue(80, 100)) {
            gapthuong.itemOptions.add(new Item.ItemOption(93,Util.nextInt(1,9)));    
        }} else {   
            gapthuong.itemOptions.add(new Item.ItemOption(0, Util.nextInt(1511,4512)));
            gapthuong.itemOptions.add(new Item.ItemOption(6, Util.nextInt(1151,4515)));
            gapthuong.itemOptions.add(new Item.ItemOption(7, Util.nextInt(1152,4115)));
        if(Util.isTrue(80, 100)) {
            gapthuong.itemOptions.add(new Item.ItemOption(93,Util.nextInt(1,9)));    
        }}
        return gapthuong;
    } 
    
    public static Item petccrandom(int tempId) {
        Item gapcc = ItemService.gI().createNewItem((short) tempId);
        if(Util.isTrue(90, 100)) {    
            gapcc.itemOptions.add(new Item.ItemOption(50, Util.nextInt(10,20)));
            gapcc.itemOptions.add(new Item.ItemOption(103, Util.nextInt(10,20)));
            gapcc.itemOptions.add(new Item.ItemOption(77, Util.nextInt(10,20)));
        if(Util.isTrue(80, 100)) {
            gapcc.itemOptions.add(new Item.ItemOption(93,Util.nextInt(5,12)));    
        }} else {   
            gapcc.itemOptions.add(new Item.ItemOption(0, Util.nextInt(3511,7512)));
            gapcc.itemOptions.add(new Item.ItemOption(6, Util.nextInt(3151,8515)));
            gapcc.itemOptions.add(new Item.ItemOption(7, Util.nextInt(3152,8115)));
        if(Util.isTrue(80, 100)) {
            gapcc.itemOptions.add(new Item.ItemOption(93,Util.nextInt(5,12)));    
        }}
        return gapcc;
    }
    
    public static Item petviprandom(int tempId) {
        Item gapvip = ItemService.gI().createNewItem((short) tempId);
        if(Util.isTrue(90, 100)) {    
            gapvip.itemOptions.add(new Item.ItemOption(50, Util.nextInt(15,25)));
            gapvip.itemOptions.add(new Item.ItemOption(103, Util.nextInt(15,25)));
            gapvip.itemOptions.add(new Item.ItemOption(77, Util.nextInt(15,25)));
        if(Util.isTrue(80, 100)) {
            gapvip.itemOptions.add(new Item.ItemOption(93,Util.nextInt(7,15)));    
        }} else {   
            gapvip.itemOptions.add(new Item.ItemOption(0, Util.nextInt(5511,10512)));
            gapvip.itemOptions.add(new Item.ItemOption(6, Util.nextInt(7151,12515)));
            gapvip.itemOptions.add(new Item.ItemOption(7, Util.nextInt(7152,12115)));
        if(Util.isTrue(80, 100)) {
            gapvip.itemOptions.add(new Item.ItemOption(93,Util.nextInt(7,15)));    
        }}
        return gapvip;
    } 

    public static String powerToString(long power) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat num = NumberFormat.getInstance(locale);
        num.setMaximumFractionDigits(1);
        if (power >= 1000000000) {
            return num.format((double) power / 1000000000) + " Tỷ";
        } else if (power >= 1000000) {
            return num.format((double) power / 1000000) + " Tr";
        } else if (power >= 1000) {
            return num.format((double) power / 1000) + " k";
        } else {
            return num.format(power);
        }
    }

    public static int getDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static int getDistance(Player pl1, Player pl2) {
        return getDistance(pl1.location.x, pl1.location.y, pl2.location.x, pl2.location.y);
    }

    public static int getDistance(Player pl, Npc npc) {
        return getDistance(pl.location.x, pl.location.y, npc.cx, npc.cy);
    }

    public static int getDistance(Player pl, Mob mob) {
        return getDistance(pl.location.x, pl.location.y, mob.location.x, mob.location.y);
    }

    public static int getDistance(Mob mob1, Mob mob2) {
        return getDistance(mob1.location.x, mob1.location.y, mob2.location.x, mob2.location.y);
    }

    public static int nextInt(int from, int to) {
        return from + rand.nextInt(to - from + 1);
    }

    public static int nextInt(int max) {
        return rand.nextInt(max);
    }

    public static int nextInt(int[] percen) {
        int next = nextInt(1000), i;
        for (i = 0; i < percen.length; i++) {
            if (next < percen[i]) {
                return i;
            }
            next -= percen[i];
        }
        return i;
    }

    public static int getOne(int n1, int n2) {
        return rand.nextInt() % 2 == 0 ? n1 : n2;
    }
    
    public static String format(double power) {
        return num.format(power);
    }

    public static int currentTimeSec() {
        return (int) System.currentTimeMillis() / 1000;
    }

    public static String replace(String text, String regex, String replacement) {
        return text.replace(regex, replacement);
    }

    public static boolean isTrue(int ratio, int typeRatio) {
        int num = Util.nextInt(typeRatio);
        if (num < ratio) {
            return true;
        }
        return false;
    }
    
    public static boolean kituvip(String text) {
        if (text.contains("[svip]") || text.contains("[vip]") || text.contains("[SVIP]")
                || text.contains("[VIP]") || text.contains("VIP") || text.contains("vip")
                || text.contains("SVIP") || text.contains("svip")) {
            return false;
        }
        return true;
    }

    public static boolean isTrue(float ratio, int typeRatio) {
        if (ratio < 1) {
            ratio *= 10;
            typeRatio *= 10;
        }
        int num = Util.nextInt(typeRatio);
        if (num < ratio) {
            return true;
        }
        return false;
    }

    public static boolean haveSpecialCharacter(String text) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        boolean b = m.find();
        return b || text.contains(" ");
    }

    public static boolean canDoWithTime(long lastTime, long miniTimeTarget) {
        return System.currentTimeMillis() - lastTime > miniTimeTarget;
    }

    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
        'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
        'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
        'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
        'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
        'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
        'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
        'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
        'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
        'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
        'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
        'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
        'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
        'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
        'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
        'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
        'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
        'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
        'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
        'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
        'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static String generateRandomText(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                + "lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static Object[] addArray(Object[]... arrays) {
        if (arrays == null || arrays.length == 0) {
            return null;
        }
        if (arrays.length == 1) {
            return arrays[0];
        }
        Object[] arr0 = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            arr0 = ArrayUtils.addAll(arr0, arrays[i]);
        }
        return arr0;
    }

    public static ItemMap manhTS(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        return new ItemMap(zone, tempId, quantity, x, y, playerId);
    }

    public static ItemMap ratiDTL(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> ao = Arrays.asList(555, 557, 559);
        List<Integer> quan = Arrays.asList(556, 558, 560);
        List<Integer> gang = Arrays.asList(562, 564, 566);
        List<Integer> giay = Arrays.asList(563, 565, 567);
        int ntl = 561;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(501) + 1300)));
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(11) + 45)));
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(1001) + 3500)));
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(11) + 35)));
        }
        if (ntl == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(2) + 15));
        }
        it.options.add(new Item.ItemOption(209, 1)); // đồ rơi từ boss
        it.options.add(new Item.ItemOption(21, 18)); // ycsm 18 tỉ
        it.options.add(new Item.ItemOption(30, 1)); // ko thể gd
        if (Util.isTrue(90, 100)) {// tỉ lệ ra spl
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 1));
        } else if (Util.isTrue(4, 100)) {
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 5));
        } else {
            it.options.add(new Item.ItemOption(107, new Random().nextInt(5) + 1));
        }
        return it;
    }
public static ItemMap ratiDHD(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> ao = Arrays.asList(650, 652, 654);
        List<Integer> quan = Arrays.asList(651, 653, 655);
        List<Integer> gang = Arrays.asList(657, 659, 661);
        List<Integer> giay = Arrays.asList(658, 660, 662);
        int ntl = 656;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(1300) + 2200)));
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(40) + 180)));
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, Util.nextInt(6600, 8800))));
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(11) + 9)));
        }
        if (ntl == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(2) + 15));
        }
        it.options.add(new Item.ItemOption(209, 1)); // đồ rơi từ boss
        it.options.add(new Item.ItemOption(21, 18)); // ycsm 18 tỉ
        it.options.add(new Item.ItemOption(30, 1)); // ko thể gd
        if (Util.isTrue(90, 100)) {// tỉ lệ ra spl
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 1));
        } else if (Util.isTrue(4, 100)) {
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 5));
        } else {
            it.options.add(new Item.ItemOption(107, new Random().nextInt(5) + 1));
        }
        return it;
    }
    public static ItemMap RaitiDoc12(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        List<Integer> ao = Arrays.asList(233, 237, 241);
        List<Integer> quan = Arrays.asList(245, 249, 253);
        List<Integer> gang = Arrays.asList(257, 261, 265);
        List<Integer> giay = Arrays.asList(269, 273, 277);
        int rd12 = 281;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(121) + 350)));//giáp 350-470
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(5) + 20)));//hp 20-24k
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(51) + 2200)));//2200-2250
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(4) + 20)));//20-23k ki
        }
        if (rd12 == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 10));//10-12cm
        }
        it.options.add(new Item.ItemOption(209, 1));//đồ rơi từ boss
        if (Util.isTrue(70, 100)) {// tỉ lệ ra spl 1-3 sao 70%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(1) + 3));
        } else if (Util.isTrue(4, 100)) {// tỉ lệ ra spl 5-7 sao 4%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 5));
        } else {// tỉ lệ ra spl 1-5 sao 6%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(2) + 3));
        }
        return it;
    }

    public static Item ratiItemTL(int tempId) {
        Item it = ItemService.gI().createItemSetKichHoat(tempId, 1);
        List<Integer> ao = Arrays.asList(555, 557, 559);
        List<Integer> quan = Arrays.asList(556, 558, 560);
        List<Integer> gang = Arrays.asList(562, 564, 566);
        List<Integer> giay = Arrays.asList(563, 565, 567);
        int ntl = 561;
        if (ao.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(47, highlightsItem(it.template.gender == 2, new Random().nextInt(501) + 1000)));
        }
        if (quan.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(22, highlightsItem(it.template.gender == 0, new Random().nextInt(11) + 45)));
        }
        if (gang.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(0, highlightsItem(it.template.gender == 2, new Random().nextInt(1001) + 3500)));
        }
        if (giay.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(23, highlightsItem(it.template.gender == 1, new Random().nextInt(11) + 35)));
        }
        if (ntl == tempId) {
            it.itemOptions.add(new Item.ItemOption(14, new Random().nextInt(3) + 15));
        }
        it.itemOptions.add(new Item.ItemOption(21, 15));
        return it;
    }
     public static ItemMap useItem(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> tanjiro = Arrays.asList(1087,1088,1091,1090);
            if (tanjiro.contains(tempId)) {               
               it.options.add(new Item.ItemOption(77, highlightsItem(it.itemTemplate.gender == 3, new Random().nextInt(30) + 1)));
               it.options.add(new Item.ItemOption(103,highlightsItem(it.itemTemplate.gender == 3, new Random().nextInt(30) + 1)));
               it.options.add(new Item.ItemOption(50,highlightsItem(it.itemTemplate.gender == 3, new Random().nextInt(30) + 1)));
            }          
            it.options.add(new Item.ItemOption(209, 1)); // đồ rơi từ boss
            it.options.add(new Item.ItemOption(30, 1)); // ko thể gd
        return it;
    }
     public static ItemMap useItem2(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
            it.options.add(new Item.ItemOption(209, 1)); // đồ rơi từ boss
            it.options.add(new Item.ItemOption(211, 1)); // đồ rơi từ boss
            it.options.add(new Item.ItemOption(93,Util.nextInt(1,3))); // đồ rơi từ boss
        return it;
    }     
    public static ItemMap ratiItem(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        List<Integer> ao = Arrays.asList(555, 557, 559);
        List<Integer> quan = Arrays.asList(556, 558, 560);
        List<Integer> gang = Arrays.asList(562, 564, 566);
        List<Integer> giay = Arrays.asList(563, 565, 567);
        List<Integer> ao1 = Arrays.asList(232, 236, 240 );
        List<Integer> quan1 = Arrays.asList(244, 248, 252);
        List<Integer> gang1 = Arrays.asList(256, 260, 264);
        List<Integer> giay1 = Arrays.asList(268, 272, 276);
        List<Integer> ao2 = Arrays.asList(233, 237, 241);
        List<Integer> quan2 = Arrays.asList(245, 249, 253);
        List<Integer> gang2 = Arrays.asList(257, 261, 265);
        List<Integer> giay2 = Arrays.asList(269, 273, 277);
        int ntl = 561; int rada9 = 278; int rada10 = 279; int rada11 = 280; int rada12 = 281;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(501) + 1000)));
            it.options.add(new Item.ItemOption(209, 1));
            it.options.add(new Item.ItemOption(21, 15));
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(11) + 45)));
            it.options.add(new Item.ItemOption(27, 12000));
            it.options.add(new Item.ItemOption(209, 1));
            it.options.add(new Item.ItemOption(21, 15));
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(1001) + 3500)));
            it.options.add(new Item.ItemOption(209, 1));
            it.options.add(new Item.ItemOption(21, 15));
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(11) + 35)));
            it.options.add(new Item.ItemOption(28, 10000));
            it.options.add(new Item.ItemOption(209, 1));
            it.options.add(new Item.ItemOption(21, 15));
        }
        if (ntl == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 15));
            it.options.add(new Item.ItemOption(209, 1));
            it.options.add(new Item.ItemOption(21, 15));
    //  đồ kaio      
        }
        if (ao1.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(100) + 300)));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (quan1.contains(tempId)) {
            it.options.add(new Item.ItemOption(6, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(6000) + 19000)));
            it.options.add(new Item.ItemOption(27, 3100));
            it.options.add(new Item.ItemOption(209, 1));           
        }
        if (gang1.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(1700) + 400)));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (giay1.contains(tempId)) {
            it.options.add(new Item.ItemOption(7, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(3000) + 17000)));
            it.options.add(new Item.ItemOption(28, 2300));
            it.options.add(new Item.ItemOption(209, 1));
    //  đồ lưỡng long      
        }
        if (ao2.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(40) + 430)));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (quan2.contains(tempId)) {
            it.options.add(new Item.ItemOption(6, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(3000) + 23000)));
            it.options.add(new Item.ItemOption(27, 3400));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (gang2.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(200) + 2100)));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (giay2.contains(tempId)) {
            it.options.add(new Item.ItemOption(7, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(3000) + 21000)));
            it.options.add(new Item.ItemOption(28, 2700));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (rada9 == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 7));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (rada10 == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 8));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (rada11 == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 9));
            it.options.add(new Item.ItemOption(209, 1));
        }
        if (rada12 == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 10));
            it.options.add(new Item.ItemOption(209, 1));
        }
//        it.options.add(new Item.ItemOption(209, 1));
//        it.options.add(new Item.ItemOption(21, 15));
        
        if (Util.isTrue(70, 100)) {// tỉ lệ ra spl 1-2 sao 70%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(1) + 2));
        } else if (Util.isTrue(70, 100)) {// tỉ lệ ra spl 2-5 sao 70%
           it.options.add(new Item.ItemOption(107, new Random().nextInt(2) + 4));
        } else if (Util.isTrue(70, 100)){// tỉ lệ ra spl 3-6 sao 70%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 4));
        }
        return it;
    }
    
    public static ItemMap khongthegiaodich(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        it.options.add(new Item.ItemOption(30, 1));
        return it;
    }
    
    public static ItemMap item865(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        int haha = 865;
        if (haha == tempId) {
            it.options.add(new Item.ItemOption(50, Util.nextInt(20, 30)));//id 50 suc danh. param chi so 25%
            it.options.add(new Item.ItemOption(77, Util.nextInt(20, 30)));
            it.options.add(new Item.ItemOption(103, Util.nextInt(20, 30)));
        }
        return it;
    }
    
    public static ItemMap item710(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        int quylaokame = 710;
        if (quylaokame == tempId) {
            it.options.add(new Item.ItemOption(50, 25));//id 50 suc danh. param chi so 25%
            it.options.add(new Item.ItemOption(77, 25));
            it.options.add(new Item.ItemOption(103, 25));
            it.options.add(new Item.ItemOption(159, 3)); //x3 chưởng mỗi phút
        }
        return it;
    }
    
    public static ItemMap item711(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        int jackichun = 711;
        if (jackichun == tempId) {
            it.options.add(new Item.ItemOption(50, 30));//id 50 suc danh. param chi so 30%
            it.options.add(new Item.ItemOption(77, 30));
            it.options.add(new Item.ItemOption(103, 30));
            it.options.add(new Item.ItemOption(159, 4)); //x4 chưởng mỗi phút
        }
        return it;
    }

    public static int highlightsItem(boolean highlights, int value) {
        double highlightsNumber = 1.1;
        return highlights ? (int) (value * highlightsNumber) : value;
    }

    public static Item sendDo(int itemId, int sql, List<Item.ItemOption> ios) {
//        InventoryServiceNew.gI().addItemBag(player, ItemService.gI().createItemFromItemShop(is));
//        InventoryServiceNew.gI().sendItemBags(player);
        Item item = ItemService.gI().createNewItem((short) itemId);
        item.itemOptions.addAll(ios);
        item.itemOptions.add(new Item.ItemOption(107, sql));
        return item;
    }

    public static boolean checkDo(Item.ItemOption itemOption) {
        switch (itemOption.optionTemplate.id) {
            case 0:// tấn công
                if (itemOption.param > 12000) {
                    return false;
                }
                break;
            case 14:// chí mạng
                if (itemOption.param > 30) {
                    return false;
                }
                break;
            case 107:// spl
            case 102:// spl
                if (itemOption.param > 8) {
                    return false;
                }
                break;
            case 77:
            case 103:
            case 95:
            case 96:
                if (itemOption.param > 41) {
                    return false;
                }
                break;
            case 50:// sd 3%
                if (itemOption.param > 24) {
                    return false;
                }
                break;
            case 6:// hp
            case 7:// ki
                if (itemOption.param > 120000) {
                    return false;
                }
                break;
            case 47:// giáp
                if (itemOption.param > 3500) {
                    return false;
                }
                break;
        }
        return true;
    }

    public static void useCheckDo(Player player, Item item, String position) {
        try {
            if (item.template != null) {
                if (item.template.id >= 381 && item.template.id <= 385) {
                    return;
                }
                if (item.template.id >= 66 && item.template.id <= 135) {
                    return;
                }
                if (item.template.id >= 474 && item.template.id <= 515) {
                    return;
                }
                item.itemOptions.forEach(itemOption -> {
                    if (!Util.checkDo(itemOption)) {
                        Logger.error(player.name + "-" + item.template.name + "-" + position + "\n");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public static void showListTop(Player player, byte select) {
        List<TOP> tops = Manager.topSK;
        switch (select) {
            case 0:
                tops = Manager.topSM;
                break;
            case 1:
                tops = Manager.topNV;
                break;
            case 2:
                tops = Manager.topSK;
                break;
            case 3:
                tops = Manager.topPVP;
                break;
        }
     }

    public static String phanthuong(int i) {
        switch (i) {
            case 1:
                return "5tr";
            case 2:
                return "3tr";
            case 3:
                return "1tr";
            default:
                return "100k";
        }
    }
    
    public static int randomBossId() {
        int bossId = Util.nextInt(10000);
        while (BossManager.gI().getBossById(bossId) != null) {
            bossId = Util.nextInt(10000);
        }
        return bossId;
    }

    public static long tinhLuyThua(int coSo, int soMu) {
        long ketQua = 1;

        for (int i = 0; i < soMu; i++) {
            ketQua *= coSo;
        }
        return ketQua;
    }

    public static void checkPlayer(Player player) {
        new Thread(() -> {
            List<Player> list = Client.gI().getPlayers().stream().filter(p -> !p.isPet && !p.isNewPet && p.getSession().userId == player.getSession().userId).collect(Collectors.toList());
            if (list.size() > 1) {
                list.forEach(pp -> Client.gI().kickSession(pp.getSession()));
                list.clear();
            }
        }).start();
    }

    public static String strSQL(final String str) {
        return str.replaceAll("['\"\\\\%]", "\\\\$0");
    }
}
