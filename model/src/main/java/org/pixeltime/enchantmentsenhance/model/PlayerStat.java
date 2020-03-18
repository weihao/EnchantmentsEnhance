package org.pixeltime.enchantmentsenhance.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerStat {

    private String playername;
    private int failstack;
    private int[] items;
    private List<Integer> valks;
    private int grind;

    public PlayerStat(String playername, int failstack, int[] items, List<Integer> valks, int grind) {
        this.playername = playername;
        this.failstack = failstack;
        this.items = items;
        this.valks = valks;
        this.grind = grind;
    }

    public PlayerStat() {
        this.playername = "";
        this.failstack = 0;
        this.grind = 2;
        this.items = new int[5];
        this.valks = new ArrayList<>();
    }

    public int getGrind() {
        return grind;
    }

    public void setGrind(int grind) {
        this.grind = grind;
    }

    public List<Integer> getValks() {
        return valks;
    }

    public void setValks(List<Integer> valks) {
        this.valks = valks;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public int getFailstack() {
        return failstack;
    }

    public void setFailstack(int failstack) {
        this.failstack = failstack;
    }
}
