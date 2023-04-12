package com.pk.ing.service;

import com.pk.ing.model.atm.RequestDto;
import com.pk.ing.model.atm.RequestType;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AtmService {
    public List<RequestDto> calculateAtmOrder(final List<RequestDto> requests) {
        requests.sort((Comparator.naturalOrder()));
        removeDuplicatedAtmsPerRegion(requests);
        return requests;
    }

    private void removeDuplicatedAtmsPerRegion(List<RequestDto> requests) {
        final Iterator<RequestDto> iterator = requests.iterator();
        final Set<Integer> processedAtmIdsInCurrentRegion = new HashSet<>();
        RequestDto currentElement;
        int processedRegion = Integer.MIN_VALUE;
        while (iterator.hasNext()) {
            currentElement = iterator.next();
            if (processedAtmIdsInCurrentRegion.contains(currentElement.atmId()) && currentElement.region() == processedRegion) {
                iterator.remove();
                processedAtmIdsInCurrentRegion.remove(currentElement.atmId());
            } else if (currentElement.region() != processedRegion) {
                processedRegion = currentElement.region();
                processedAtmIdsInCurrentRegion.clear();
            }
            if (currentElement.requestType() != RequestType.STANDARD) {
                processedAtmIdsInCurrentRegion.add(currentElement.atmId());
            } else {
                processedAtmIdsInCurrentRegion.remove(currentElement.atmId());
            }
        }
    }
}
