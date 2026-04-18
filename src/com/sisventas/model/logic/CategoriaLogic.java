package com.sisventas.model.logic;

import com.sisventas.model.beans.Categoria;
import com.sisventas.model.dao.CategoriaDao;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class CategoriaLogic {

    private static CategoriaDao instance;

    private CategoriaLogic() {
    }

    private static CategoriaDao getInstance() {
        if (instance == null) {
            instance = new CategoriaDao();
        }
        return instance;
    }

    public static boolean insertarCategoria(Categoria categoria) throws Exception {
        try {
            return getInstance().insertar(categoria);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean modificarCategoria(Categoria categoria) throws Exception {
        try {
            return getInstance().modificar(categoria);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean eliminarCategoria(int id) throws Exception {
        try {
            return getInstance().eliminar(id);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public static List<Categoria> listarCarCategoria() throws Exception{
        try {
            return getInstance().listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Categoria obtenerCategoria(int id) throws Exception{
        try {
            return getInstance().obtener(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static DefaultListModel getListModelCategoria() throws Exception{
        DefaultListModel modelo = new DefaultListModel();
        List<Categoria> lista = getInstance().listar();

        for (Categoria c : lista) {
            modelo.addElement(c);
        }

        return modelo;
    }

    public static DefaultComboBoxModel getComboModelCategoria() throws Exception{
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        List<Categoria> lista = getInstance().listar();

        for (Categoria c : lista) {
            modelo.addElement(c);
        }

        return modelo;
    }

}
