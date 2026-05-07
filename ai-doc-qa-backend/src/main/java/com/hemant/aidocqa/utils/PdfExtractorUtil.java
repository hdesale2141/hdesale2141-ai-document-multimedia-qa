package com.hemant.aidocqa.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfExtractorUtil {

    public static String extractText(String filePath) {

        try (PDDocument document = PDDocument.load(new File(filePath))) {

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            return pdfTextStripper.getText(document);

        } catch (IOException e) {

            throw new RuntimeException("Failed to extract PDF text: " + e.getMessage());
        }
    }
}