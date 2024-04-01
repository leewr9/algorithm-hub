package com.example.apiDTO;

import java.io.Serializable;
import java.util.ArrayList;

public class SummonerDTO implements Serializable {
    private String accountId;
    private int profileIconId;
    private String name;
    private String id;
    private String puuid;
    private long summonerLevel;
    private int timer = 0;
    private ArrayList<MatchReferenceDTO> matches;

    public ArrayList<MatchReferenceDTO> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<MatchReferenceDTO> matches) {
        this.matches = matches;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer() {
        this.timer = 120;
        new Thread(()->{
            try {
                while (timer != 0){
                    Thread.sleep(1000);
                    this.timer--;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

    public class MatchReferenceDTO implements Serializable {
        private long gameId;

        public long getGameId() {
            return gameId;
        }

        public void setGameId(long gameId) {
            this.gameId = gameId;
        }
    }

}
