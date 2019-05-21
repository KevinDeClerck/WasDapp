package com.realdolmen.hbo5.wasdapp.wasdappcore.rest;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class WasdappEntryResponseMapper {

    public static WasdappEntry mapToEntry(WasdappEntryResponse entry){
        
        Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
        if(entry.getAanmaakDatum() != null){
            createDate = new Timestamp(entry.getAanmaakDatum().getTime());
        }
        Timestamp updateDate = Timestamp.valueOf(LocalDateTime.now());
        if(entry.getWijzigDatum() != null){
            updateDate = new Timestamp(entry.getWijzigDatum().getTime());
        }   
        return WasdappEntry.builder()
                .withId(entry.getId())
                .withName(entry.getName())
                .withLocatie(entry.getLocatie())
                .withStraat(entry.getStraat())
                .withNummer(entry.getNummer())
                .withPostCode(entry.getPostCode())
                .withGemeente(entry.getGemeente())
                .withLand(entry.getLand())
                .withOmschrijving(entry.getOmschrijving())
                .withWikiLink(entry.getWikiLink())
                .withWebsite(entry.getWebsite())
                .withTelefoonNummer(entry.getTelefoonNummer())
                .withEmail(entry.getEmail())
                .withPrijs(entry.getPrijs())
                .withPersoon(entry.getPersoon())
                .withAanmaakDatum(createDate)
                .withWijzigDatum(updateDate)
                .withLat(entry.getLat())
                .withLon(entry.getLon())
                .build();
    }
}
