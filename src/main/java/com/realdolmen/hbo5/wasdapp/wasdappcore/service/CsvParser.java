package com.realdolmen.hbo5.wasdapp.wasdappcore.service;

import com.realdolmen.hbo5.wasdapp.wasdappcore.exception.WrongCSVException;
import java.io.IOException;
import java.io.InputStream;

public interface CsvParser {
    void importCsv(InputStream is) throws IOException, WrongCSVException;
    void importCsvFile(String fileName);
}
