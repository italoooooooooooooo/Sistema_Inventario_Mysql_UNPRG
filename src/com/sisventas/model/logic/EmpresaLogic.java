package com.sisventas.model.logic;

import com.sisventas.model.beans.Empresa;
import com.sisventas.model.dao.EmpresaDao;
import java.util.List;

public class EmpresaLogic {

    private static EmpresaDao instance;

    public static EmpresaDao getInstance() {
        if (instance == null) {
            instance = new EmpresaDao();
        }
        return instance;
    }

    public EmpresaLogic() {
    }

    public static boolean insertarEmpresa(Empresa empresa) throws Exception {
        try {
            return getInstance().insertar(empresa);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean modificarEmpresa(Empresa empresa) throws Exception {
        try {
            return getInstance().modificar(empresa);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static boolean eliminarEmpresa(int id) throws Exception {
        try {
            return getInstance().eliminar(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Empresa obtenerEmpresa(int id) throws Exception{
        try {
            return getInstance().obtener(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<Empresa> listarEmpresa() throws Exception {
        try {
            return getInstance().listar();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
