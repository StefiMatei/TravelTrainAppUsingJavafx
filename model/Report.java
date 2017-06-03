package model;

import controller.DbConnection;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class Report {
    public void generateReportQuantity() throws SQLException, FileNotFoundException, DocumentException {
        Connection connection = DbConnection.getConnection();
        String sql = "Select * from Ticket where quantity=0";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        Document pdf = new Document();
        PdfWriter.getInstance(pdf, new FileOutputStream("pdf_repo.pdf"));
        pdf.open();
        pdf.addTitle("In this report we see the tickets that were sold!");
        PdfPTable reportTable = new PdfPTable(5);
        PdfPCell tableCell;
        while (resultSet.next()){
            int ticketId = resultSet.getInt(1);
            tableCell = new PdfPCell(new Phrase(String.valueOf(ticketId)));
            reportTable.addCell(tableCell);
            int trainId = resultSet.getInt(2);
            tableCell = new PdfPCell(new Phrase(String.valueOf(trainId)));
            reportTable.addCell(tableCell);
            Double price = resultSet.getDouble(3);
            tableCell = new PdfPCell(new Phrase(String.valueOf(Double.valueOf(price))));
            reportTable.addCell(tableCell);
            int quantity = resultSet.getInt(4);
            tableCell = new PdfPCell(new Phrase(String.valueOf(quantity)));
            reportTable.addCell(tableCell);
            int placeNumber = resultSet.getInt(5);
            tableCell = new PdfPCell(new Phrase(String.valueOf(placeNumber)));
            reportTable.addCell(tableCell);

        }
        pdf.add(reportTable);

        pdf.close();
        resultSet.close();
        statement.close();
        connection.close();

    }

    public void generateReceipt(int ticketId) throws SQLException, FileNotFoundException, DocumentException {
        Connection connection = DbConnection.getConnection();
        String sql = "SELECT Ticket.price, Ticket.placeNumber from Ticket where ticketId=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ticketId);
        ResultSet resultSet = statement.executeQuery();
        Document pdf = new Document();
        PdfWriter.getInstance(pdf, new FileOutputStream("pdf_receipt.pdf"));
        pdf.open();
        pdf.addTitle("Below you have the bill for the purchased ticket and the place number. Have a good journey!");
        PdfPTable reportTable = new PdfPTable(2);
        PdfPCell tableCell;
        while (resultSet.next()){
            Double price = resultSet.getDouble("price");
            tableCell = new PdfPCell(new Phrase(String.valueOf(price)));
            reportTable.addCell(tableCell);
            int place = resultSet.getInt("placeNumber");
            tableCell = new PdfPCell(new Phrase(String.valueOf(place)));
            reportTable.addCell(tableCell);


        }
        pdf.add(reportTable);
        pdf.close();
        resultSet.close();
        statement.close();
        connection.close();



    }
}
