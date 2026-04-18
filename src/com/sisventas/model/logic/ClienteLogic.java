package com.sisventas.model.logic;

import com.sisventas.model.beans.Cliente;
import com.sisventas.model.dao.ClienteDao;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ClienteLogic {

    public static ClienteDao instance;

    private ClienteLogic() {
    }

    private static ClienteDao getInstance() {
        if (instance == null) {
            instance = new ClienteDao();
        }
        return instance;
    }

    public static boolean insertarCliente(Cliente cliente) throws Exception {
        try {
            return getInstance().insertar(cliente);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean modificarCliente(Cliente cliente) throws Exception {
        try {
            return getInstance().modificar(cliente);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean eliminarCliente(int id) throws Exception {
        try {
            return getInstance().eliminar(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<Cliente> listarCliente() throws Exception {
        try {
            return getInstance().listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Cliente obtenerCliente(int id) throws Exception {
        try {
            return getInstance().obtener(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static DefaultTableModel getTableModelCliente() throws Exception{
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");

        List<Cliente> lista = getInstance().listar();

        for (Cliente cliente : lista) {
            Object[] fila = {
                cliente.getIdCliente(),
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellidos(),
                cliente.getDireccion(),
                cliente.getTelefono()
            };
            modelo.addRow(fila);
        }

        return modelo;
    }

    public static DefaultComboBoxModel getComboModelCliente() throws Exception{
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        List<Cliente> lista = getInstance().listar();
        for (Cliente c : lista) {
            modelo.addElement(c);
        }
        return modelo;
    }
}
