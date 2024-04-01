package com.example.apiDTO;

import java.io.Serializable;

public class ParticipantIdentityDTO implements Serializable {
    private int participantId;
    private PlayerDTO player;

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }
}
