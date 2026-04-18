package com.sisventas.utils;

import com.sisventas.model.beans.Producto;
import com.sisventas.model.logic.ProductoLogic;
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

public class ReporteProducto {

    public static void generarReporProductos() throws Exception {
        List<Producto> lstProducto = ProductoLogic.listarProducto();
        File dir = new File("Reportes");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File pdf = new File(dir, "reportes_productos.pdf");
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
        Paragraph pt = new Paragraph("Reportes Productos", titulo);
        pt.setAlignment(Element.ALIGN_CENTER);
        doc.add(pt);
        doc.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{2, 4, 2, 2, 3});
        addHeader(tabla, "ID");
        addHeader(tabla, "Nombre");
        addHeader(tabla, "Precio");
        addHeader(tabla, "Stock");
        addHeader(tabla, "Categoria");
        for (Producto p : lstProducto) {
            tabla.addCell(String.valueOf(p.getIdProducto()));
            tabla.addCell(p.getNombre());
            tabla.addCell(String.valueOf(p.getPrecio()));
            tabla.addCell(String.valueOf(p.getStock()));
            tabla.addCell(p.getCategoria() != null ? p.getCategoria().getNombre() : " ");
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
