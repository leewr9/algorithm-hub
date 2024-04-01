package com.example.apiDTO;

public class LeagueEntryDTO {
    private String queueType;
    private String tier = "UNRANKED";
    private String rank = "X";
    private int leaguePoints = 0;
    private int wins = 0;
    private int losses = 0;
    private long percent = 0;

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public long getPercent() {
        return percent;
    }

    public void setPercent() {
        this.percent = Math.round((double)((double) this.wins / (this.wins + this.losses)) * 100);
    }
}
