package com.pk.ing.model.game;

import com.google.gson.annotations.Expose;

public record ClanDto(@Expose int numberOfPlayers,
                      @Expose int points) implements Comparable<ClanDto> {
    @Override
    public int compareTo(ClanDto o) {
        int result = Integer.compare(o.points, this.points);
        return result != 0 ? result : Integer.compare(this.numberOfPlayers, o.numberOfPlayers);
    }
}
