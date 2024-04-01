package com.example.retrofitController;

import com.example.apiDTO.LeagueEntryDTO;
import com.example.apiDTO.MatchDTO;
import com.example.apiDTO.SummonerDTO;
import com.example.apiDTO.TotalDTO;
import com.example.recyclerView.MatchViewAdapter;

import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesInfo {
    private Map<String, TotalDTO> totalMap;
    private Map<Integer, String> spellMap;
    private Map<Integer, String> runeMap;

    private MatchViewAdapter adapter;
    private RetrofitInstance retrofitSummoner = new RetrofitInstance("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/");
    private RetrofitInstance retrofitLeague = new RetrofitInstance("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/");
    private RetrofitInstance retrofitMatches = new RetrofitInstance("https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/");
    private RetrofitInstance retrofitMatch = new RetrofitInstance("https://kr.api.riotgames.com/lol/match/v4/matches/");

    private Call<SummonerDTO> summonerCall;
    private Call<Set<LeagueEntryDTO>> leagueCall;
    private Call<SummonerDTO> matchesCall;
    private Call<MatchDTO> matchCall;

    private TotalDTO total;
    private SummonerDTO summoner;

    private final String key = "RGAPI-0f121c22-0e4c-4433-beb4-e8cc099b753d";

    private static MatchesInfo matchesInfo;

    private MatchesInfo() {
    }

    public static MatchesInfo getInstance() {
        if (matchesInfo == null) matchesInfo = new MatchesInfo();
        return matchesInfo;
    }

    public MatchViewAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MatchViewAdapter adapter) {
        this.adapter = adapter;
    }

    public Map<String, TotalDTO> getTotalMap() {
        return totalMap;
    }

    public void setTotalMap(Map<String, TotalDTO> totalMap) {
        this.totalMap = totalMap;
    }

    public boolean chkSummoner(String summonerName) {
        return totalMap.containsKey(summonerName);
    }

    public String getSpellName(int key) {
        return spellMap.get(key);
    }

    public void setSpellMap(Map<Integer, String> spellMap) {
        this.spellMap = spellMap;
    }

    public String getRuneName(int key) {
        return runeMap.get(key);
    }

    public void setRuneMap(Map<Integer, String> runeMap) {
        this.runeMap = runeMap;
    }

    public void summonerSearchApi(String summonerName) {
        new Thread(() -> {
            summonerCall = retrofitSummoner.getApiService().getSummoner(summonerName);
            try {
                total = new TotalDTO();
                summoner = summonerCall.execute().body();
                if (summoner.getProfileIconId() == -1) summoner.setProfileIconId(0);
                total.setSummoner(summoner);
                if (total.getSummoner() != null) {
                    totalMap.put(total.getSummoner().getName().replace(" ", "").toLowerCase(), total);

                    adapter = new MatchViewAdapter(matchesInfo.getTotalMap().get(summonerName).getMatches());
                    adapter.setSummonerName(summonerName);

                    leagueSearchApi(summonerName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void leagueSearchApi(String summonerName) {
        new Thread(() -> {
            leagueCall = retrofitLeague.getApiService().getLeague(totalMap.get(summonerName).getSummoner().getId());
            try {
                Set<LeagueEntryDTO> i = leagueCall.execute().body();
                totalMap.get(summonerName).setLeagueEntry(i);
                matchesSearchApi(summonerName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void matchesSearchApi(String summonerName) {
        new Thread(() -> {
            String accountId = totalMap.get(summonerName).getSummoner().getAccountId();
            int beginIndex = totalMap.get(summonerName).getBeginIndex();
            int endIndex = totalMap.get(summonerName).getEndIndex();

            matchesCall = retrofitMatches.getApiService().getGameId(accountId, endIndex, beginIndex, key);
            try {
                totalMap.get(summonerName).getSummoner().setMatches(matchesCall.execute().body().getMatches());
                matchSearchApi(summonerName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void matchSearchApi(String summonerName) {
        try {
            for (int i = 0; i < totalMap.get(summonerName).getSummoner().getMatches().size(); i++) {
                matchCall = retrofitMatch.getApiService().getMatches(totalMap.get(summonerName).getSummoner().getMatches().get(i).getGameId());

                Callback<MatchDTO> matchCallBack = new Callback<MatchDTO>() {
                    @Override
                    public void onResponse(Call<MatchDTO> call, Response<MatchDTO> response) {
                        if (response.isSuccessful()) {
                            MatchDTO match = response.body();
                            totalMap.get(summonerName).getMatches().add(match);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<MatchDTO> call, Throwable t) {

                    }
                };
                matchCall.enqueue(matchCallBack);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
