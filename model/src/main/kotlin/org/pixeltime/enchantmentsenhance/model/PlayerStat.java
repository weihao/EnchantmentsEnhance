package org.pixeltime.enchantmentsenhance.model;

import java.util.List;

public class PlayerStat {

    public PlayerStat() {

    }

    private String playername;
    private int failstack;
    private int[] items;
    private List<Integer> valks;
    private int grind;

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
