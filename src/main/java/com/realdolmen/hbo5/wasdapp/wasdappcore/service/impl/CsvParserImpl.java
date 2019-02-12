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
import static java.util.stream.Collectors.toList;

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
        String[] split = s.split(";");
        WasdappEntry wasdappEntry = new WasdappEntry();
        wasdappEntry.setId(getLongValue(split[0]));
        wasdappEntry.setName(getText(split[1]));
        wasdappEntry.setCity(getText(split[2]));
        wasdappEntry.setStreet(getText(split[3]));
        wasdappEntry.setNumber(getText(split[4]));
        wasdappEntry.setLat(getDoubleValue(split[5]));
        wasdappEntry.setLon(getDoubleValue(split[6]));
        wasdappEntry.setDescription(getText(split[7]));
        return wasdappEntry;
    }

    private String getText(String s) {
        return s.trim().equals("") ? null : s;
    }

    private Double getDoubleValue(String s) {
        return s.trim().equals("") ? null : valueOf(s);
    }

    private Long getLongValue(String s) {
        return s.trim().equals("") ? null : Long.valueOf(s);
    }
}
