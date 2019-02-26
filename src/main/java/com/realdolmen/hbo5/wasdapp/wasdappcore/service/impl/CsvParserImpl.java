package com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WasdappRuntimeException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.CsvParser;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.WasdappService;
import com.realdolmen.hbo5.wasdapp.wasdappcore.util.Logger;
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
import static java.util.stream.Collectors.toList;
import org.apache.logging.log4j.LogManager;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class CsvParserImpl implements CsvParser {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    private WasdappService wasdappService;

    private String errMsg;

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
        try {
            String[] split = s.split(";", -1);
            WasdappEntry wasdappEntry = new WasdappEntry();
            if (split[0].length() < 8) {
                wasdappEntry.setId(getLongValue(split[0]));
            } else {
                errMsg = "ID has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[1] != null && split[1].length() < 64) {
                wasdappEntry.setName(getText(split[1]));
            } else {
                errMsg = "Empty title or title too long in csv-file.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[2].length() < 64) {
                wasdappEntry.setLocatie(getText(split[2]));
            } else {
                errMsg = "Location has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[3].length() < 32) {
                wasdappEntry.setStraat(getText(split[3]));
            } else {
                errMsg = "Street has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[4].length() < 8) {
                wasdappEntry.setNummer(getText(split[4]));
            } else {
                errMsg = "Number has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[5].length() < 8) {
                wasdappEntry.setPostCode(getText(split[5]));
            } else {
                errMsg = "Postalcode has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[6].length() < 32) {
                wasdappEntry.setGemeente(getText(split[6]));
            } else {
                errMsg = "City has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[7].length() < 16) {
                wasdappEntry.setLand(getText(split[7]));
            } else {
                errMsg = "Country has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[8].length() < 512) {
                wasdappEntry.setOmschrijving(getText(split[8]));
            } else {
                errMsg = "Description has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[9].length() < 32) {
                wasdappEntry.setWikiLink(getText(split[9]));
            } else {
                errMsg = "Wikipedia link has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[10].length() < 32) {
                wasdappEntry.setWebsite(getText(split[10]));
            } else {
                errMsg = "Website has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[11].length() < 16) {
                wasdappEntry.setTelefoonNummer(getText(split[11]));
            } else {
                errMsg = "Telephone number has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[12].length() < 64) {
                wasdappEntry.setEmail(getText(split[12]));
            } else {
                errMsg = "E-mail has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[13].length() < 8) {
                wasdappEntry.setPrijs(getDoubleValue(split[13]));
            } else {
                errMsg = "Price has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[14].length() < 32) {
                wasdappEntry.setPersoon(getText(split[14]));
            } else {
                errMsg = "Person has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[15].length() < 16) {
                wasdappEntry.setLat(getDoubleValue(split[15]));
            } else {
                errMsg = "Latitude has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            if (split[16].length() < 16) {
                wasdappEntry.setLon(getDoubleValue(split[16]));
            } else {
                errMsg = "Longitude has too many characters.";
                LOGGER.error(errMsg);
                throw new Exception();
            }
            wasdappEntry.setAanmaakDatum(Timestamp.valueOf(LocalDateTime.now()));
            wasdappEntry.setWijzigDatum(Timestamp.valueOf(LocalDateTime.now()));
            return wasdappEntry;
        } catch (Exception e) {
            errMsg = "mapLineToEntry caught an error.";
            LOGGER.error(errMsg);
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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
