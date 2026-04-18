package com.sisventas.model.logic;

import com.sisventas.model.beans.Pedido;
import com.sisventas.model.dao.PedidoDao;
import java.util.List;

public class PedidoLogic {

    private static PedidoDao instance;

    private PedidoLogic() {
    }

    private static PedidoDao getInstance() {
        if (instance == null) {
            instance = new PedidoDao();
        }
        return instance;
    }

    public static boolean insertarPedido(Pedido pedido) throws Exception {
        try {
            return getInstance().insertar(pedido);
        } catch (Exception ex) {
            throw ex;
        }
    }


    public static boolean eliminarPedido(int id) throws Exception {
        try {
            return getInstance().eliminar(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Pedido obtenerPedido(int id) throws Exception {
        try {
            return getInstance().obtener(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static List<Pedido> listarPedidos() throws Exception {
        try {
            return getInstance().listar();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
