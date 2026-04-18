package com.sisventas.model.logic;

import com.sisventas.model.beans.Producto;
import com.sisventas.model.dao.ProductoDao;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ProductoLogic {

    public static ProductoDao instance;

    private ProductoLogic() {

    }

    public static ProductoDao getInstance() {
        if (instance == null) {
            instance = new ProductoDao();
        }
        return instance;
    }

    public static boolean insertarProducto(Producto producto) throws Exception {
        try {
            return getInstance().insertar(producto);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean modificarProducto(Producto producto) throws Exception {
        try {
            return getInstance().modificar(producto);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean eliminarProducto(int id) throws Exception {
        try {
            return getInstance().eliminar(id);
        } catch (Exception ex) {
            throw ex;
        }

    }

    public static Producto obtenerProducto(int id) throws Exception {
        try {
            return getInstance().obtener(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<Producto> listarProducto() throws Exception {
        try {
            return getInstance().listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static DefaultTableModel getTableModelProducto() throws Exception{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Categoria");

        List<Producto> lista = getInstance().listar();

        for (Producto p : lista) {
            Object[] fila = {
                p.getIdProducto(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getCategoria()
            };
            modelo.addRow(fila);
        }

        return modelo;
    }

    public static DefaultComboBoxModel getComboModelProducto() throws Exception{
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        List<Producto> lista = getInstance().listar();
        for (Producto p : lista) {
            modelo.addElement(p);
        }
        return modelo;
    }
}
