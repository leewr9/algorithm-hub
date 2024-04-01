package com.example.apiDTO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MatchDTO implements Serializable {
    private long gameId = 0;
    private ArrayList<ParticipantIdentityDTO> participantIdentities;
    private long gameDuration;
    private long gameCreation;
    private long creation;
    private ArrayList<TeamStatsDTO> teams;
    private int queueId;
    private String type;
    private ArrayList<ParticipantDTO> participants;
    private String gameTime;
    private String day;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public ArrayList<ParticipantIdentityDTO> getParticipantIdentities() {
        return participantIdentities;
    }

    public void setParticipantIdentities(ArrayList<ParticipantIdentityDTO> participantIdentities) {
        this.participantIdentities = participantIdentities;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public String getCreation() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        try {
            date = sdf.parse(sdf.format(date));
            long sum = date.getTime() - (gameCreation);
            if (sum / (24 * 60 * 60 * 1000) > 0) {
                day = String.valueOf(sum / (24 * 60 * 60 * 1000)) + "일 전";
            } else if (sum / (60 * 60 * 1000) > 0) {
                day = String.valueOf(sum / (60 * 60 * 1000)) + "시간 전";
            } else if (sum / (60 * 1000) > 0) {
                day = String.valueOf((sum / (60 * 1000)) - Long.valueOf(this.gameTime.substring(0, 2))) + "분 전";
            } else {
                day = String.valueOf(sum / (1000)) + "초 전";
            }
        } catch (Exception e) {

        }

        return day;
    }

    public void setCreation(long creation) {
        this.creation = creation;
    }

    public ArrayList<TeamStatsDTO> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<TeamStatsDTO> teams) {
        this.teams = teams;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public String getType() {
        switch (queueId) {
            case 420:
                this.type = "솔로 랭크";
                break;
            case 430:
                this.type = "일반 게임";
                break;
            case 440:
                this.type = "자유 5:5 랭크";
                break;
            case 450:
                this.type = "무작위 총력전";
                break;
            default:
                this.type = "사용자 지정 게임";
        }
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<ParticipantDTO> participants) {
        this.participants = participants;
    }

    public String getGameTime() {
        String min;
        String sec;
        if (gameDuration / 60 < 10) {
            min = "0" + String.valueOf(gameDuration / 60);
        } else {
            min = String.valueOf(gameDuration / 60);
        }
        if (gameDuration % 60 < 10) {
            sec = "0" + String.valueOf(gameDuration % 60);
        } else {
            sec = String.valueOf(gameDuration % 60);
        }
        this.gameTime = min + ":" + sec;
        return gameTime;
    }

}
