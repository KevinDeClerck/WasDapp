package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
