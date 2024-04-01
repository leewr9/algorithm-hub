package com.example.apiDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TotalDTO implements Serializable {
    private Set<LeagueEntryDTO> leagueEntry;
    private List<MatchDTO> matches = new ArrayList<>();
    private SummonerDTO summoner;
    private int endIndex = 10;
    private int beginIndex = 0;

    public Set<LeagueEntryDTO> getLeagueEntry() {
        return leagueEntry;
    }

    public void setLeagueEntry(Set<LeagueEntryDTO> leagueEntry) {
        this.leagueEntry = leagueEntry;
    }

    public List<MatchDTO> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<MatchDTO> matches) {
        this.matches = matches;
    }

    public SummonerDTO getSummoner() {
        return summoner;
    }

    public void setSummoner(SummonerDTO summoner) {
        this.summoner = summoner;
    }

    public int getEndIndex() {
        int ret = endIndex;
        endIndex += 10;
        return ret;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getBeginIndex() {
        int ret = beginIndex;
        beginIndex = endIndex;
        return ret;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }
}
