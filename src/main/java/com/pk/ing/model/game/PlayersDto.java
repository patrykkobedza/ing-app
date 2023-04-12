package com.pk.ing.model.game;

import com.google.gson.annotations.Expose;

import java.util.List;

public record PlayersDto(@Expose int groupCount, @Expose List<ClanDto> clans) {
}
