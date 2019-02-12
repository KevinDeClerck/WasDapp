package com.realdolmen.hbo5.wasdapp.wasdappcore.service;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;

import java.util.List;

public interface WasdappService {

    List<WasdappEntry> findByNameContains(String nameShouldStartWith);
}
