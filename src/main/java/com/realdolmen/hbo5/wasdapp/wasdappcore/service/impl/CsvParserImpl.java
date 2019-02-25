package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WasdappRuntimeException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CsvParser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Double.valueOf;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class CsvParserImpl implements CsvParser {

    private WasdappService wasdappService;

    @Autowired
    public CsvParserImpl(WasdappService wasdappService) {
        this.wasdappService = wasdappService;
    }

    @Override
    public void importCsv(String filename) {
        getLines(filename)
                .stream()
                .map(this::mapLineToEntry)
                .forEach(entry -> wasdappService.save(entry));
    }

    private List<String> getLines(String filename) {
        Path path;
        try {
            path = Paths.get(
                    getClass()
                            .getClassLoader()
                            .getResource(filename)
                            .toURI()
            );
        } catch (URISyntaxException e) {
            throw new WasdappRuntimeException(e);
        }

        try {
            Stream<String> lines = Files.lines(path);
            List<String> stringList = lines.collect(toList());
            lines.close();
            return stringList;
        } catch (IOException e) {
            throw new WasdappRuntimeException(e);
        }
    }

    private WasdappEntry mapLineToEntry(String s) {
        try{
        String[] split = s.split(";",-1);
        WasdappEntry wasdappEntry = new WasdappEntry();
        wasdappEntry.setId(getLongValue(split[0]));
        wasdappEntry.setName(getText(split[1]));
        wasdappEntry.setLocatie(getText(split[2]));
        wasdappEntry.setStraat(getText(split[3]));
        wasdappEntry.setNummer(getText(split[4]));
        wasdappEntry.setPostCode(getText(split[5]));
        wasdappEntry.setGemeente(getText(split[6]));
        wasdappEntry.setLand(getText(split[7]));
        wasdappEntry.setOmschrijving(getText(split[8]));
        wasdappEntry.setWikiLink(getText(split[9]));
        wasdappEntry.setWebsite(getText(split[10]));
        wasdappEntry.setTelefoonNummer(getText(split[11]));
        wasdappEntry.setEmail(getText(split[12]));
        wasdappEntry.setPrijs(getDoubleValue(split[13]));
        wasdappEntry.setPersoon(getText(split[14]));
        wasdappEntry.setLat(getDoubleValue(split[15]));
        wasdappEntry.setLon(getDoubleValue(split[16]));
        wasdappEntry.setAanmaakDatum(Timestamp.valueOf(LocalDateTime.now()));
        wasdappEntry.setWijzigDatum(Timestamp.valueOf(LocalDateTime.now()));
        return wasdappEntry;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String getText(String s) {
        return isBlank(s) ? null : s;
    }

    private Double getDoubleValue(String s) {
        return isBlank(s) ? null : valueOf(s);
    }

    private Long getLongValue(String s) {
        return isBlank(s) ? null : Long.valueOf(s);
    }
}
