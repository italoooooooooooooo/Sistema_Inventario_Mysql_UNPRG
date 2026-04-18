package com.sisventas.utils;

import com.sisventas.model.beans.Empresa;
import com.sisventas.model.beans.ItemPedido;
import com.sisventas.model.beans.Pedido;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.Font;
import org.openpdf.text.FontFactory;
import org.openpdf.text.Image;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Rectangle;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

public class GeneradorFacturaPDF {

    public static void GenerarFactura(Empresa empresa, Pedido pedido) throws Exception {
        File carpeta = new File("Facturas");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        String nombreArchivo = "factura_" + pedido.getIdPedido() + ".pdf";
        File archivo = new File(carpeta, nombreArchivo);

        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream(archivo));
        documento.open();

        Font tituloEmpresa = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLACK);
        Font tituloFactura = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Color.BLACK);
        Font subtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.BLACK);
        Font normal = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.BLACK);
        Font normalBold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.BLACK);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        PdfPTable tablaEncabezado = new PdfPTable(2);
        tablaEncabezado.setWidthPercentage(100);
        tablaEncabezado.setWidths(new float[]{7, 3});

        PdfPCell celdaEmpresa = new PdfPCell();
        celdaEmpresa.setBorder(Rectangle.NO_BORDER);

        PdfPTable tablaEmpresa = new PdfPTable(2);
        tablaEmpresa.setWidthPercentage(100);
        tablaEmpresa.setWidths(new float[]{2, 8});

        PdfPCell celdaLogo = new PdfPCell();
        celdaLogo.setBorder(Rectangle.NO_BORDER);
        celdaLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celdaLogo.setPaddingTop(12f);

        Image logo = Image.getInstance(
                GeneradorFacturaPDF.class.getResource("/Img/minorista.png")
        );  
        logo.scaleToFit(60, 60);
        logo.setAlignment(Element.ALIGN_CENTER);
        celdaLogo.addElement(logo);

        PdfPCell celdaDatosEmpresa = new PdfPCell();
        celdaDatosEmpresa.setBorder(Rectangle.NO_BORDER);
        celdaDatosEmpresa.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Paragraph pEmpresa = new Paragraph(empresa.getNombre(), tituloEmpresa);
        pEmpresa.setAlignment(Element.ALIGN_LEFT);
        celdaDatosEmpresa.addElement(pEmpresa);

        Paragraph pRuc = new Paragraph("RUC: " + empresa.getRuc(), normal);
        pRuc.setAlignment(Element.ALIGN_LEFT);
        celdaDatosEmpresa.addElement(pRuc);

        Paragraph pDireccion = new Paragraph("Direccion: " + empresa.getDireccion(), normal);
        pDireccion.setAlignment(Element.ALIGN_LEFT);
        celdaDatosEmpresa.addElement(pDireccion);

        Paragraph pTelefono = new Paragraph("Telefono: " + empresa.getTelefono(), normal);
        pTelefono.setAlignment(Element.ALIGN_LEFT);
        celdaDatosEmpresa.addElement(pTelefono);

        tablaEmpresa.addCell(celdaLogo);
        tablaEmpresa.addCell(celdaDatosEmpresa);

        celdaEmpresa.addElement(tablaEmpresa);

        PdfPCell celdaFactura = new PdfPCell();
        celdaFactura.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaFactura.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celdaFactura.setPadding(10f);

        Paragraph pTituloFactura = new Paragraph("FACTURA", tituloFactura);
        pTituloFactura.setAlignment(Element.ALIGN_CENTER);
        celdaFactura.addElement(pTituloFactura);

        Paragraph pNumero = new Paragraph("Nro: " + pedido.getIdPedido(), subtitulo);
        pNumero.setAlignment(Element.ALIGN_CENTER);
        celdaFactura.addElement(pNumero);

        Paragraph pFecha = new Paragraph("Fecha: " + formatoFecha.format(pedido.getFecha()), normal);
        pFecha.setAlignment(Element.ALIGN_CENTER);
        celdaFactura.addElement(pFecha);

        tablaEncabezado.addCell(celdaEmpresa);
        tablaEncabezado.addCell(celdaFactura);

        documento.add(tablaEncabezado);
        documento.add(new Paragraph(" "));

        PdfPTable tablaCliente = new PdfPTable(2);
        tablaCliente.setWidthPercentage(100);
        tablaCliente.setWidths(new float[]{2, 8});

        PdfPCell cLabelCliente = new PdfPCell(new Paragraph("Cliente:", normalBold));
        cLabelCliente.setBorder(Rectangle.NO_BORDER);
        cLabelCliente.setPadding(5f);

        PdfPCell cDatoCliente = new PdfPCell(new Paragraph(
                pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellidos(), normal));
        cDatoCliente.setBorder(Rectangle.NO_BORDER);
        cDatoCliente.setPadding(5f);

        PdfPCell cLabelDni = new PdfPCell(new Paragraph("DNI:", normalBold));
        cLabelDni.setBorder(Rectangle.NO_BORDER);
        cLabelDni.setPadding(5f);

        PdfPCell cDatoDni = new PdfPCell(new Paragraph(pedido.getCliente().getDni(), normal));
        cDatoDni.setBorder(Rectangle.NO_BORDER);
        cDatoDni.setPadding(5f);

        tablaCliente.addCell(cLabelCliente);
        tablaCliente.addCell(cDatoCliente);
        tablaCliente.addCell(cLabelDni);
        tablaCliente.addCell(cDatoDni);

        documento.add(tablaCliente);
        documento.add(new Paragraph(" "));

        PdfPTable tablaItems = new PdfPTable(4);
        tablaItems.setWidthPercentage(100);
        tablaItems.setWidths(new float[]{4, 2, 2, 2});

        PdfPCell h1 = new PdfPCell(new Paragraph("Producto", normalBold));
        PdfPCell h2 = new PdfPCell(new Paragraph("Precio", normalBold));
        PdfPCell h3 = new PdfPCell(new Paragraph("Cantidad", normalBold));
        PdfPCell h4 = new PdfPCell(new Paragraph("Importe", normalBold));

        Color colorCabecera = new Color(220, 220, 220);

        h1.setBackgroundColor(colorCabecera);
        h2.setBackgroundColor(colorCabecera);
        h3.setBackgroundColor(colorCabecera);
        h4.setBackgroundColor(colorCabecera);

        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);

        tablaItems.addCell(h1);
        tablaItems.addCell(h2);
        tablaItems.addCell(h3);
        tablaItems.addCell(h4);

        for (ItemPedido item : pedido.getItemPedido()) {
            double importe = item.getCantidad() * item.getPrecioVenta();

            PdfPCell c1 = new PdfPCell(new Paragraph(item.getProducto().getNombre(), normal));
            PdfPCell c2 = new PdfPCell(new Paragraph("S/ " + String.format("%.2f", item.getPrecioVenta()), normal));
            PdfPCell c3 = new PdfPCell(new Paragraph(String.valueOf(item.getCantidad()), normal));
            PdfPCell c4 = new PdfPCell(new Paragraph("S/ " + String.format("%.2f", importe), normal));

            c1.setPadding(5f);
            c2.setPadding(5f);
            c3.setPadding(5f);
            c4.setPadding(5f);

            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            c4.setHorizontalAlignment(Element.ALIGN_RIGHT);

            tablaItems.addCell(c1);
            tablaItems.addCell(c2);
            tablaItems.addCell(c3);
            tablaItems.addCell(c4);
        }

        documento.add(tablaItems);
        documento.add(new Paragraph(" "));

        PdfPTable tablaTotales = new PdfPTable(2);
        tablaTotales.setWidthPercentage(40);
        tablaTotales.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablaTotales.setWidths(new float[]{5, 3});

        agregarFilaTotal(tablaTotales, "Subtotal:", "S/ " + String.format("%.2f", pedido.getSubtotal()), normalBold, normal);
        agregarFilaTotal(tablaTotales, "Descuento:", "S/ " + String.format("%.2f", pedido.getDescuento()), normalBold, normal);
        agregarFilaTotal(tablaTotales, "Total a pagar:", "S/ " + String.format("%.2f", pedido.getTotal()), normalBold, normalBold);
        agregarFilaTotal(tablaTotales, "Efectivo:", "S/ " + String.format("%.2f", pedido.getEfectivo()), normalBold, normal);
        agregarFilaTotal(tablaTotales, "Cambio:", "S/ " + String.format("%.2f", pedido.getCambio()), normalBold, normal);

        documento.add(tablaTotales);
        documento.add(new Paragraph(" "));

        Paragraph gracias = new Paragraph("Gracias por su compra", subtitulo);
        gracias.setAlignment(Element.ALIGN_CENTER);
        documento.add(gracias);

        documento.close();

        Desktop.getDesktop().open(archivo);
    }

    private static void agregarFilaTotal(PdfPTable tabla, String etiqueta, String valor, Font fontEtiqueta, Font fontValor) {
        PdfPCell celdaEtiqueta = new PdfPCell(new Paragraph(etiqueta, fontEtiqueta));
        PdfPCell celdaValor = new PdfPCell(new Paragraph(valor, fontValor));

        celdaEtiqueta.setBorder(Rectangle.NO_BORDER);
        celdaValor.setBorder(Rectangle.NO_BORDER);

        celdaEtiqueta.setHorizontalAlignment(Element.ALIGN_LEFT);
        celdaValor.setHorizontalAlignment(Element.ALIGN_RIGHT);

        celdaEtiqueta.setPadding(4f);
        celdaValor.setPadding(4f);

        tabla.addCell(celdaEtiqueta);
        tabla.addCell(celdaValor);
    }
}
