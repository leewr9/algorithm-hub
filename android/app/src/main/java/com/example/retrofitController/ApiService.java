package com.example.retrofitController;

import com.example.apiDTO.LeagueEntryDTO;
import com.example.apiDTO.MatchDTO;
import com.example.apiDTO.SummonerDTO;

import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    final String key = "RGAPI-0f121c22-0e4c-4433-beb4-e8cc099b753d";

    @GET("{name}?api_key=" + key)
    Call<SummonerDTO> getSummoner(@Path("name") String name);

    @GET("{accountid}")
    Call<SummonerDTO> getGameId(@Path("accountid") String id, @Query("endIndex") int endIndex, @Query("beginIndex") int beginIndex, @Query("api_key") String api_key);

    @GET("{gameid}?api_key=" + key)
    Call<MatchDTO> getMatches(@Path("gameid") long gameid);

    @GET("{id}?api_key=" + key)
    Call<Set<LeagueEntryDTO>> getLeague(@Path("id") String id);
}
