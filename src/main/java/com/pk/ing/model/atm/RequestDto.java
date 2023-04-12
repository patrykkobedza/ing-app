package com.pk.ing.model.atm;

import com.google.gson.annotations.Expose;

public record RequestDto(@Expose int region,
                         @Expose int atmId,
                         @Expose(serialize = false) RequestType requestType)
        implements Comparable<RequestDto> {

    @Override
    public int compareTo(RequestDto o) {
        int result = Integer.compare(region, o.region);
        return result != 0 ? result : Integer.compare(requestType.getPriority(), o.requestType.getPriority());
    }
}
