package com.example.apiDTO;

import java.io.Serializable;

public class PlayerDTO implements Serializable {
    private String summonerName;
    private String summonerId;

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }
}
