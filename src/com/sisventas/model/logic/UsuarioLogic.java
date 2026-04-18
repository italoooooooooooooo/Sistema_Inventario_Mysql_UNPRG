 package com.sisventas.model.logic;

import com.sisventas.model.beans.Usuario;
import com.sisventas.model.dao.UsuarioDao;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioLogic {

    private static UsuarioDao instance;

    private UsuarioLogic() {
    }

    private static UsuarioDao getInstance() {
        if (instance == null) {
            instance = new UsuarioDao();
        }
        return instance;
    }

    public static boolean insertarUsuario(Usuario usuario) throws Exception {
        try {
            return getInstance().insertar(usuario);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean modificarUsuario(Usuario usuario) throws Exception {
        try {
            return getInstance().modificar(usuario);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean eliminarUsuario(int id) throws Exception {
        try {
            return getInstance().eliminar(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Usuario obetenerUsuario(int id) throws Exception {
        try {
            return getInstance().obtener(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<Usuario> listarUsuarios() throws Exception {
        try {
            return getInstance().listar();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public static Usuario autenticarUsuarios(String correoOusername,String password) throws Exception{
        try {
            return getInstance().Autenticar(correoOusername, password);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static DefaultTableModel getTableModelUsuario() throws Exception {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID Usuario");
        modelo.addColumn("Username");
        modelo.addColumn("Nombre");
        modelo.addColumn("Correo");
        modelo.addColumn("Rol");
        List<Usuario> lista = getInstance().listar();
        for (Usuario u : lista) {
            Object[] fila = {
                u.getIdUsuario(),
                u.getUsername(),
                u.getNombre(),
                u.getCorreo(),
                u.getRol()
            };
            modelo.addRow(fila);
        }
        return modelo;
    }
}
