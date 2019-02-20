package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CsvParserImplTest {

    @InjectMocks
    private CsvParserImpl csvParser;

    @Mock
    private WasdappService wasdappService;

    @Captor
    private ArgumentCaptor<WasdappEntry> captor;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void tearDown() {
        verifyNoMoreInteractions(wasdappService);
    }

    @Test
    public void shouldImportACsv() {
        WasdappEntry wasdappEntry0 = new WasdappEntry();
        wasdappEntry0.setStraat("Gaston crommenlaan");
        wasdappEntry0.setGemeente("Gent");
        wasdappEntry0.setNummer("4");
        wasdappEntry0.setName("koffiemachien");
        wasdappEntry0.setLat(51.037028);
        wasdappEntry0.setLon(3.735785);
        wasdappEntry0.setOmschrijving("Kantoor gent in de refter");

        WasdappEntry wasdappEntry1 = new WasdappEntry();
        wasdappEntry1.setStraat("Gaston crommenlaan");
        wasdappEntry1.setGemeente("Gent");
        wasdappEntry1.setNummer("4");
        wasdappEntry1.setName("koffiemachien");
        wasdappEntry1.setLat(51.037028);
        wasdappEntry1.setLon(3.735785);
        wasdappEntry1.setOmschrijving("Kantoor gent in de keuken");

        WasdappEntry wasdappEntry2 = new WasdappEntry();
        wasdappEntry2.setStraat("Prins Boudewijnlaan");
        wasdappEntry2.setGemeente("Kontich");
        wasdappEntry2.setNummer("26");
        wasdappEntry2.setName("theemachien");
        wasdappEntry2.setLat(51.037028);
        wasdappEntry2.setLon(3.735785);
        wasdappEntry2.setOmschrijving("Kantoor gent in de keuken");

        WasdappEntry wasdappEntry3 = new WasdappEntry();
        wasdappEntry3.setName("a");

        csvParser.importCsv("import.csv");

        verify(wasdappService, times(4)).save(captor.capture());
        wasdappEntry0.setAanmaakDatum(captor.getAllValues().get(0).getAanmaakDatum());
        wasdappEntry1.setAanmaakDatum(captor.getAllValues().get(1).getAanmaakDatum());
        wasdappEntry2.setAanmaakDatum(captor.getAllValues().get(2).getAanmaakDatum());
        wasdappEntry1.setWijzigDatum(captor.getAllValues().get(1).getWijzigDatum());
        wasdappEntry0.setWijzigDatum(captor.getAllValues().get(0).getWijzigDatum());
        wasdappEntry2.setWijzigDatum(captor.getAllValues().get(2).getWijzigDatum());
        assertThat(captor.getAllValues().size(), is(4));
        assertThat(captor.getAllValues().get(0), samePropertyValuesAs(wasdappEntry0));
        assertThat(captor.getAllValues().get(1), samePropertyValuesAs(wasdappEntry1));
        assertThat(captor.getAllValues().get(2), samePropertyValuesAs(wasdappEntry2));
    }

    @Test
    public void shouldThrowANullpointerExceptionIfTheFileDoesNotExist() {

        thrown.expect(NullPointerException.class);
        csvParser.importCsv("szq.csv");
    }
}
