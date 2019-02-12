package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WasdappRuntimeException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CsvParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class CsvParserImpl implements CsvParser {

    @Override
    public void importCsv() {
//        getLines("temp.csv")
//                .forEach(s -> );

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
}
