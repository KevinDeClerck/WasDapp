package com.realdolmen.hbo5.wasdapp.wasdappcore.domain;

import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.service.impl.WasdappServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneratePdfReport {

    private List<WasdappEntryResponse> result = new ArrayList<>();

    @Autowired
    WasdappServiceImpl wasdappService;

    public void generatePdf() throws IOException {
        result = wasdappService.findAll();
        try {
            if (result != null) {
                PDDocument doc = new PDDocument();
                int x = 0;
                ArrayList<PDPage> paginas = new ArrayList<>();
                for (WasdappEntryResponse i : result) {
                    PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
                    doc.addPage(page);
                    paginas.add(page);
                    PDPageContentStream CS = new PDPageContentStream(doc, paginas.get(x), PDPageContentStream.AppendMode.APPEND, true, true);
                    CS.beginText();
                    CS.newLineAtOffset(25, 550);
                    CS.setFont(PDType1Font.TIMES_ROMAN, 13);
                    CS.showText(i.getName());
                    CS.newLineAtOffset(650, 0);
                    CS.showText("WASDAPP");
                    CS.newLineAtOffset(-600, -250);
                    CS.showText("-- " + i.getOmschrijving() + " --");
                    CS.newLineAtOffset(-50, -220);
                    CS.showText(i.getLocatie());
                    CS.newLineAtOffset(0, -20);
                    CS.endText();
                    CS.close();
                    x++;
                }
                doc.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
