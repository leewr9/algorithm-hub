package com.example.retrofitController;

import com.example.anyDTO.ChampionDTO;
import com.example.anyDTO.ItemDTO;
import com.example.anyDTO.RuneDTO;
import com.example.anyDTO.SpellDTO;
import com.example.apiDTO.SummonerDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MySqlService {
    //소환사 데이터베이스
    @POST("Summoner_Insert.php")
    Call<Void> summoner_Insert(@Body SummonerDTO s);

    @POST("Summoner_Update.php")
    Call<Void> summoner_Update(@Body SummonerDTO s);

    @GET("Summoner_Select.php")
    Call<List<SummonerDTO>> summoner_Select();

    @GET("Spell_Select.php")
    Call<List<SpellDTO>> spell_Select();

    @GET("Rune_Select.php")
    Call<List<RuneDTO>> rune_Select();

    //챔피언 데이터베이스
    @GET("Champion_Select.php")
    Call<List<ChampionDTO>> champion_Select();

    @GET("Champion_Skill.php")
    Call<List<ChampionDTO.SkillDTO>> champion_Skill();

    @GET("Champion_Skin.php")
    Call<List<ChampionDTO.SkinDTO>> champion_Skin();

    //아이템 데이터베이스
    @GET("Item_Select.php")
    Call<List<ItemDTO>> item_Select();

    @GET("Item_Main.php")
    Call<List<ItemDTO.MainDTO>> item_Main();

    @GET("Item_Sub.php")
    Call<List<ItemDTO.SubDTO>> item_Sub();

    @GET("Item_Tag.php")
    Call<List<ItemDTO.TagDTO>> item_Tag();
}
