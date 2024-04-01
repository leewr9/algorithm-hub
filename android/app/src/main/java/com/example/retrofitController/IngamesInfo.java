package com.example.retrofitController;

import com.example.anyDTO.ChampionDTO;
import com.example.anyDTO.ItemDTO;
import com.example.anyDTO.RuneDTO;
import com.example.anyDTO.SpellDTO;
import com.example.apiDTO.SummonerDTO;
import com.example.apiDTO.TotalDTO;
import com.example.recyclerView.ChampionViewAdapter;
import com.example.recyclerView.ItemViewAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngamesInfo {
    public static IngamesInfo ingamesInfo;
    private RetrofitInstance retrofitMySql = new RetrofitInstance("http://59.27.161.150/nagneo_gg/");

    //Summoner
    private Call<List<SummonerDTO>> summonerCall;
    private Call<List<SpellDTO>> spellCall;
    private Call<List<RuneDTO>> runeCall;
    private Call<Void> insertCall;
    private Call<Void> updateCall;

    private Map<String, TotalDTO> map_summoner;
    private Map<Integer, String> map_rune;
    private Map<Integer, String> map_spell;
    private TotalDTO summoner;

    //Champion
    private Call<List<ChampionDTO>> championCall;
    private Call<List<ChampionDTO.SkillDTO>> skillCall;
    private Call<List<ChampionDTO.SkinDTO>> skinCall;

    private Map<Integer, ChampionDTO> map_champion;
    private Map<Integer, Integer> integer_champion;
    private List<ChampionDTO> list_champion;
    private ChampionViewAdapter adapter_champion;

    //Item
    private Call<List<ItemDTO>> itemCall;
    private Call<List<ItemDTO.MainDTO>> mainCall;
    private Call<List<ItemDTO.SubDTO>> subCall;
    private Call<List<ItemDTO.TagDTO>> tagCall;

    private Map<String, ItemDTO> map_item;
    private List<ItemDTO> list_item;
    private ItemViewAdapter adapter_item;

    private IngamesInfo() {
    }

    public static IngamesInfo getInstance() {
        if (ingamesInfo == null) ingamesInfo = new IngamesInfo();
        return ingamesInfo;
    }

    public Map<String, TotalDTO> getSummonerMap() {
        return map_summoner;
    }

    public Map<Integer, String> getRuneMap() {
        return map_rune;
    }

    public Map<Integer, String> getSpellMap() {
        return map_spell;
    }

    public ChampionViewAdapter getChampionAdapter() {
        return adapter_champion;
    }

    public List<ChampionDTO> getChampionList() {
        return list_champion;
    }

    public Map<Integer, ChampionDTO> getChampionMap() {
        return map_champion;
    }

    public Map<Integer, Integer> getChampionInteger() {
        return integer_champion;
    }

    public List<ItemDTO> getItemList() {
        return list_item;
    }

    public Map<String, ItemDTO> getItemMap() {
        return map_item;
    }

    public ItemViewAdapter getItemAdapter() {
        return adapter_item;
    }

    public void summonerInfoMysql() {
        new Thread(() -> {
            map_summoner = new HashMap<>();
            map_spell = new HashMap<>();
            map_rune = new HashMap<>();

            summonerCall = retrofitMySql.getMySqlService().summoner_Select();
            spellCall = retrofitMySql.getMySqlService().spell_Select();
            runeCall = retrofitMySql.getMySqlService().rune_Select();

            try {
                for (SummonerDTO i : summonerCall.execute().body()) {
                    if (i == null) break;
                    summoner = new TotalDTO();
                    summoner.setSummoner(i);
                    map_summoner.put(i.getName().replace(" ", "").toLowerCase(), summoner);
                }

                for (SpellDTO i : spellCall.execute().body())
                    map_spell.put(i.getNumid(), i.getEngid());

                for (RuneDTO i : runeCall.execute().body())
                    map_rune.put(i.getNumid(), i.getEngid());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void summonerInsertMysql(SummonerDTO summoner) {
        insertCall = retrofitMySql.getMySqlService().summoner_Insert(summoner);
        insertCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void summonerUpdateMysql(SummonerDTO summoner) {
        updateCall = retrofitMySql.getMySqlService().summoner_Update(summoner);
        updateCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void championInfoMysql() {
        new Thread(() -> {
            championCall = retrofitMySql.getMySqlService().champion_Select();
            try {
                list_champion = championCall.execute().body();
                adapter_champion = new ChampionViewAdapter(list_champion);
                championDetailMysql();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void championDetailMysql() {
        new Thread(() -> {
            map_champion = new HashMap<>();
            integer_champion = new HashMap<>();
            int next = 0;
            for (ChampionDTO i : list_champion) {
                map_champion.put(i.getNumid(), i);
                integer_champion.put(i.getNumid(), next++);
            }

            skillCall = retrofitMySql.getMySqlService().champion_Skill();
            skinCall = retrofitMySql.getMySqlService().champion_Skin();
            try {
                for (ChampionDTO.SkillDTO i : skillCall.execute().body())
                    map_champion.get(i.getNumid()).setSkill(i);

                for (ChampionDTO.SkinDTO i : skinCall.execute().body())
                    map_champion.get(i.getNumid()).setSkin(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void itemInfoMysql() {
        new Thread(() -> {
            itemCall = retrofitMySql.getMySqlService().item_Select();
            try {
                list_item = itemCall.execute().body();
                adapter_item = new ItemViewAdapter(list_item);
                itemDetailMysql();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void itemDetailMysql() {
        new Thread(() -> {
            map_item = new HashMap<>();
            for (ItemDTO i : list_item)
                map_item.put(i.getNumid(), i);

            mainCall = retrofitMySql.getMySqlService().item_Main();
            subCall = retrofitMySql.getMySqlService().item_Sub();
            tagCall = retrofitMySql.getMySqlService().item_Tag();

            try {
                for (ItemDTO.MainDTO i : mainCall.execute().body())
                    map_item.get(i.getNumid()).setMain(i);

                for (ItemDTO.SubDTO i : subCall.execute().body())
                    map_item.get(i.getNumid()).setSub(i);

                for (ItemDTO.TagDTO i : tagCall.execute().body()) {
                    switch (i.getTag().toUpperCase()) {
                        case "LANE":
                            map_item.get(i.getNumid()).setTag("시작");
                            break;
                        case "JUNGLE":
                            map_item.get(i.getNumid()).setTag("정글");
                            break;
                        case "VISION":
                            map_item.get(i.getNumid()).setTag("시야");
                            break;
                        case "CONSUMABLE":
                            map_item.get(i.getNumid()).setTag("소모");
                            break;
                        case "BOOTS":
                            map_item.get(i.getNumid()).setTag("이동");
                            break;
                        case "MANA":
                            map_item.get(i.getNumid()).setTag("마법");
                            break;
                        case "SPELLDAMAGE":
                            map_item.get(i.getNumid()).setTag("마법");
                            break;
                        case "MANAREGEN":
                            map_item.get(i.getNumid()).setTag("마법");
                            break;
                        case "DAMAGE":
                            map_item.get(i.getNumid()).setTag("물리");
                            break;
                        case "ATTACKSPEED":
                            map_item.get(i.getNumid()).setTag("물리");
                            break;
                        case "CRITICALSTRIKE":
                            map_item.get(i.getNumid()).setTag("물리");
                            break;
                        case "SPELLBLOCK":
                            map_item.get(i.getNumid()).setTag("방어");
                            break;
                        case "ARMOR":
                            map_item.get(i.getNumid()).setTag("방어");
                            break;
                        case "HEALTHREGEN":
                            map_item.get(i.getNumid()).setTag("방어");
                            break;
                        case "HEALTH":
                            map_item.get(i.getNumid()).setTag("방어");
                            break;
                        default:
                            map_item.get(i.getNumid()).setTag("기타");
                            break;
                    }
                }

                for (ItemDTO i : list_item) {
                    if (!i.getTag().contains("시야") && i.getTag().contains("시작"))
                        i.finishTag("시작");
                    else if (!i.getTag().contains("시야") && i.getTag().contains("정글"))
                        i.finishTag("시작");
                    else if (i.getTag().contains("시야") || i.getKorid().contains("와드"))
                        i.finishTag("시야");
                    else if (i.getTag().contains("이동"))
                        i.finishTag("이동");
                    else if (i.getTag().contains("소모"))
                        i.finishTag("소모");
                    else if (i.getTag().contains("마법"))
                        i.finishTag("마법");
                    else if (i.getTag().contains("물리"))
                        i.finishTag("물리");
                    else if (i.getTag().contains("방어"))
                        i.finishTag("방어");
                    else i.finishTag("기타");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
