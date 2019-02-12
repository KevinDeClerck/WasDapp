package com.realdolmen.hbo5.wasdapp.wasdappcore.rest;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntryRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WasdappServiceImplTest {


    @Mock
    private WasdappEntryRepository repositoryMock;
    private WasdappServiceImpl service;


    @Before
    public void setUp()  {
        service = new WasdappServiceImpl(repositoryMock);
    }

    @Test
    public void findByNameContains() {
        List repoResponse = mock(List.class);
        when(service.findByNameContains("koffie")).thenReturn(repoResponse);
        List<WasdappEntry> serviceResponse = service.findByNameContains("koffie");

        assertThat(serviceResponse).isEqualTo(repoResponse);

    }
}