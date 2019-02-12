package com.realdolmen.hbo5.wasdapp.wasdappcore.rest;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;

public class WasdappEntryMapper {

    public static WasdappEntryResponse mapToDto(WasdappEntry entry){
        return WasdappEntryResponse.builder()
                .withDesciprion(entry.getDescription())
                .withName(entry.getName())
                .build();
    }
}