package com.sisventas.utils;

import com.sisventas.model.beans.Cliente;
import com.sisventas.model.logic.ClienteLogic;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.Image;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

public class ReporteCliente {

    public static void generarReportesClientes() throws Exception {
        List<Cliente> lstCliente = ClienteLogic.listarCliente();
        File dir = new File("Reportes");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File pdf = new File(dir, "reportes_cliente.pdf");
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(pdf));
        doc.open();

        Image logo = Image.getInstance(
                ReporteVentas.class.getResource("/Img/reportes.png")
        );
        float ancho = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
        logo.scaleToFit(ancho, 500);
        logo.setAlignment(Element.ALIGN_CENTER);
        doc.add(logo);
        doc.add(new Paragraph(" "));

        Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph pt = new Paragraph("Reportes Clientes", titulo);
        pt.setAlignment(Element.ALIGN_CENTER);
        doc.add(pt);
        doc.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(6);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{1.2f, 2.5f, 2.5f, 1.8f, 3.0f, 2.0f});
        addHeader(tabla, "ID");
        addHeader(tabla, "Nombre");
        addHeader(tabla, "Apellidos");
        addHeader(tabla, "Dni");
        addHeader(tabla, "Direccion");
        addHeader(tabla, "Telefono");
        for (Cliente c : lstCliente) {
            tabla.addCell(String.valueOf(c.getIdCliente()));
            tabla.addCell(c.getNombre());
            tabla.addCell(c.getApellidos());
            tabla.addCell(String.valueOf(c.getDni()));
            tabla.addCell(c.getDireccion());
            tabla.addCell(String.valueOf(c.getTelefono()));
        }
        doc.add(tabla);
        doc.close();
        Desktop.getDesktop().open(pdf);
    }

    private static void addHeader(PdfPTable tabla, String texto) {
        Font head = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
        PdfPCell cell = new PdfPCell(new Paragraph(texto, head));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(220, 220, 220));
        cell.setPadding(5f);
        tabla.addCell(cell);
    }

}
