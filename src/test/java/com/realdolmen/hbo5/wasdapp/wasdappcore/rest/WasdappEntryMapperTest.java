package com.realdolmen.hbo5.wasdapp.wasdappcore.rest;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WasdappEntryMapperTest {


    private WasdappEntryResponse mapped;

    @Before
    public void setUp() throws Exception {
        WasdappEntry wasdappEntry = new WasdappEntry();

        wasdappEntry.setId(1L);
        wasdappEntry.setName("name");
        wasdappEntry.setDescription("description");
        wasdappEntry.setStreet("street");
        wasdappEntry.setNumber("number");
        wasdappEntry.setCity("city");
        wasdappEntry.setLat(2.02);
        wasdappEntry.setLon(3.03);
        mapped = WasdappEntryMapper.mapToDto(wasdappEntry);


    }

    @Test
    public void mapsDescription() {
        assertThat(mapped.getDescription()).isEqualTo("description");

    }

    @Test
    public void mapsName() {
        assertThat(mapped.getName()).isEqualTo("name");
    }
}