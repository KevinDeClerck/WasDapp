package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.rest.WasdappEntryResponseMapper;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
public class WasdappServiceImpl implements WasdappService {

    private WasdappEntryRepository wasdappRepository;
    
    private WasdappEntryResponseMapper wasdappEntryResponseMapper;

    public WasdappServiceImpl(WasdappEntryRepository wasdappRepository) {
        this.wasdappRepository = wasdappRepository;
    }


    @Override
    public List<WasdappEntry> findByNameContains(String shouldContain) {
        return wasdappRepository.findByNameContaining(shouldContain);
    }

    @Override
    public WasdappEntry save(WasdappEntry entry) {
        return wasdappRepository.save(entry);
    }

    @Override
    public List<WasdappEntry> save(List<WasdappEntry> entries) {
        return entries
                .stream()
                .map(this::save)
                .collect(toList());
    }
    
    public WasdappEntry update(WasdappEntryResponse entryResponse){
        WasdappEntry entry = wasdappEntryResponseMapper.mapToEntry(entryResponse);
        if(entry.getAanmaakDatum() == null){
            entry.setAanmaakDatum(Timestamp.valueOf(LocalDateTime.now()));
        }
        entry.setWijzigDatum(Timestamp.valueOf(LocalDateTime.now()));
        return wasdappRepository.save(entry);
    }
}
