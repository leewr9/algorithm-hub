package com.example.apiDTO;

import java.io.Serializable;

public class TeamStatsDTO implements Serializable {
    private int teamId;
    private String win;
    private int towerKills;
    private int dragonKills;
    private int baronKills;
    private int gold;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public int getTowerKills() {
        return towerKills;
    }

    public void setTowerKills(int towerKills) {
        this.towerKills = towerKills;
    }

    public int getDragonKills() {
        return dragonKills;
    }

    public void setDragonKills(int dragonKills) {
        this.dragonKills = dragonKills;
    }

    public int getBaronKills() {
        return baronKills;
    }

    public void setBaronKills(int baronKills) {
        this.baronKills = baronKills;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
