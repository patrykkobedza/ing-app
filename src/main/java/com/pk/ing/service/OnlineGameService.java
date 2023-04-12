package com.pk.ing.service;

import com.pk.ing.model.game.ClanDto;
import com.pk.ing.model.game.PlayersDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class OnlineGameService {

    public List<List<ClanDto>> calculatePlayersOrder(final PlayersDto playersDto) {
        int maxGroupSize = playersDto.groupCount();
        final List<ClanDto> clans = playersDto.clans();
        clans.sort(Comparator.naturalOrder());
        final List<List<ClanDto>> result = new ArrayList<>();
        while (!clans.isEmpty()) {
            final List<ClanDto> group = new ArrayList<>();
            int leftGroupSlots = maxGroupSize;
            Iterator<ClanDto> iterator = clans.iterator();
            while (iterator.hasNext() && leftGroupSlots > 0) {
                ClanDto currentClan = iterator.next();
                if (currentClan.numberOfPlayers() <= leftGroupSlots) {
                    group.add(currentClan);
                    leftGroupSlots -= currentClan.numberOfPlayers();
                    iterator.remove();
                } else if (currentClan.numberOfPlayers() > maxGroupSize) {
                    throw new RuntimeException("Detected clan bigger than maxGroupSize!");
                }
            }
            result.add(group);
        }
        return result;
    }
}
