package tn.leaguestorm.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tn.leaguestorm.utils.MyConnection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Pdf2 {

    private Connection cnx;
    private Statement ste;

    public Pdf2() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void generatePDF(String filePath, String nomAbonn, String type, String description, String date) throws FileNotFoundException, SQLException, DocumentException {
        /* Create Connection objects */

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // We have four columns in our table
        PdfPTable table = new PdfPTable(2);

        // Create a cell object
        PdfPCell cell;

        // Add content to the table
        cell = new PdfPCell(new Phrase("Nom Commercial Organisme"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(nomAbonn));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nom Juridique Organisme"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(type));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Description"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(description));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Date"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(date));
        table.addCell(cell);

        /* Attach report table to PDF */
        document.add(table);

        document.close();

        /* Close all DB related objects */
    }
}
