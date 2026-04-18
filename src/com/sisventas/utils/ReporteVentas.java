package com.sisventas.utils;

import com.sisventas.model.beans.ItemPedido;
import com.sisventas.model.beans.Pedido;
import com.sisventas.model.logic.PedidoLogic;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
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

public class ReporteVentas {

    public static void generarReportePedido() throws Exception {
        List<Pedido> lstPedidos = PedidoLogic.listarPedidos();
        File dir = new File("Reportes");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File pdf = new File(dir, "reportes_pedidos.pdf");
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
        Paragraph pt = new Paragraph("Reportes Ventas", titulo);
        pt.setAlignment(Element.ALIGN_CENTER);
        doc.add(pt);
        doc.add(new Paragraph(" "));

        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{1.2f, 2.0f, 3.5f, 4.5f, 1.6f, 1.2f, 1.6f});

        addHeader(tabla, "ID");
        addHeader(tabla, "Fecha");
        addHeader(tabla, "Cliente");
        addHeader(tabla, "Productos");
        addHeader(tabla, "Subtotal");
        addHeader(tabla, "Descuento");
        addHeader(tabla, "Total");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Pedido pCabecera : lstPedidos) {
            Pedido p = PedidoLogic.obtenerPedido(pCabecera.getIdPedido());

            String fecha = p.getFecha() != null ? sdf.format(p.getFecha()) : "";
            String cliente = p.getCliente() != null
                    ? p.getCliente().getNombre() + " " + p.getCliente().getApellidos()
                    : "";

            StringBuilder prod = new StringBuilder();
            for (ItemPedido it : p.getItemPedido()) {
                if (prod.length() > 0) {
                    prod.append(", ");
                }
                String nombre = it.getProducto() != null ? it.getProducto().getNombre() : "";
                prod.append(nombre).append(" (").append(it.getCantidad()).append(")");
            }

            tabla.addCell(String.valueOf(p.getIdPedido()));
            tabla.addCell(fecha);
            tabla.addCell(cliente);
            tabla.addCell(prod.toString());
            tabla.addCell(String.format("%.2f", p.getSubtotal()));
            tabla.addCell(String.format("%.2f", p.getDescuento()));
            tabla.addCell(String.format("%.2f", p.getTotal()));
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
