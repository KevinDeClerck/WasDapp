package com.realdolmen.hbo5.wasdapp.wasdappcore.rest;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("/wasdapp")
public class WasdappController {

    private final WasdappService wasdappService;

    public WasdappController(WasdappService wasdappService) {
        this.wasdappService = wasdappService;
    }

    @GetMapping("/wasdapp/byname/{}")
    public List<WasdappEntryResponse> findByNameLike(String nameShouldStartWith){
       List<WasdappEntry> results =  wasdappService.findByNameContains(nameShouldStartWith);
        return results.stream()
                .map(WasdappEntryMapper::mapToDto)
                .collect(Collectors.toList());
    }



}
