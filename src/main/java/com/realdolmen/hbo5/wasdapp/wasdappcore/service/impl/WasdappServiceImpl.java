package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;

@Service
public class WasdappServiceImpl implements WasdappService {

    private WasdappEntryRepository wasdappRepository;

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
}
