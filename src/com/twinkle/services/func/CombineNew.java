package com.twinkle.services.func;

import com.twinkle.models.item.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Stole By Arriety 💖
 *
 */
public class CombineNew {

    public long lastTimeCombine;
    
    public List<Item> itemsCombine;
    public int typeCombine;
    
    public int goldCombine;
    public int gemCombine;
    public int rubycombine;
    public int tvcombine;
    public float ratioCombine;
    public int countDaNangCap;
    public short countDaBaoVe;
    public int countMVBT;
    
    public CombineNew() {
        this.itemsCombine = new ArrayList<>();
    }
    
    public void setTypeCombine(int type){
        this.typeCombine = type;
    }
    
    public void clearItemCombine(){
        this.itemsCombine.clear();
    }
    
    public void clearParamCombine(){
        this.goldCombine = 0;
        this.gemCombine = 0;
        this.rubycombine = 0;
        this.tvcombine = 0;
        this.ratioCombine = 0;
        this.countDaNangCap = 0;
        this.countDaBaoVe = 0;

    }
    
    public void dispose(){
        this.itemsCombine = null;
    }
}
