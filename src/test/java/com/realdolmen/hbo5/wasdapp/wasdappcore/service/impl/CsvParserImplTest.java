package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
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
        wasdappEntry0.setStreet("Gaston crommenlaan");
        wasdappEntry0.setCity("Gent");
        wasdappEntry0.setNumber("4");
        wasdappEntry0.setName("koffiemachien");
        wasdappEntry0.setLat(51.037028);
        wasdappEntry0.setLon(3.735785);
        wasdappEntry0.setDescription("Kantoor gent in de refter");

        WasdappEntry wasdappEntry1 = new WasdappEntry();
        wasdappEntry1.setStreet("Gaston crommenlaan");
        wasdappEntry1.setCity("Gent");
        wasdappEntry1.setNumber("4");
        wasdappEntry1.setName("koffiemachien");
        wasdappEntry1.setLat(51.037028);
        wasdappEntry1.setLon(3.735785);
        wasdappEntry1.setDescription("Kantoor gent in de keuken");

        WasdappEntry wasdappEntry2 = new WasdappEntry();
        wasdappEntry2.setStreet("Prins Boudewijnlaan");
        wasdappEntry2.setCity("Kontich");
        wasdappEntry2.setNumber("26");
        wasdappEntry2.setName("theemachien");
        wasdappEntry2.setLat(51.142005);
        wasdappEntry2.setLon(4.438177);
        wasdappEntry2.setDescription("Kantoor gent in de keuken");

        WasdappEntry wasdappEntry3 = new WasdappEntry();
        wasdappEntry3.setName("a");

        csvParser.importCsv("import.csv");

        verify(wasdappService, times(4)).save(captor.capture());

        assertThat(captor.getAllValues().size(), is(4));
        assertThat(captor.getAllValues().get(0), samePropertyValuesAs(wasdappEntry0));
        assertThat(captor.getAllValues().get(1), samePropertyValuesAs(wasdappEntry1));
        assertThat(captor.getAllValues().get(2), samePropertyValuesAs(wasdappEntry2));
        assertThat(captor.getAllValues().get(3), samePropertyValuesAs(wasdappEntry3));
    }

    @Test
    public void shouldThrowANullpointerExceptionIfTheFileDoesNotExist() {

        thrown.expect(NullPointerException.class);
        csvParser.importCsv("szq.csv");
    }
}
