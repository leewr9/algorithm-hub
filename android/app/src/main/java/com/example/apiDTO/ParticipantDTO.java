package com.example.apiDTO;

import java.io.Serializable;

public class ParticipantDTO implements Serializable {
    private int championId;
    private int teamId;
    private ParticipantStatsDTO stats;
    private int spell1Id;
    private int spell2Id;

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public ParticipantStatsDTO getStats() {
        return stats;
    }

    public void setStats(ParticipantStatsDTO stats) {
        this.stats = stats;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public void setSpell1Id(int spell1Id) {
        this.spell1Id = spell1Id;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public void setSpell2Id(int spell2Id) {
        this.spell2Id = spell2Id;
    }
}
