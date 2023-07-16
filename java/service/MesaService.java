package service;

import dao.MesaDAO;
import model.Mesa;

public class MesaService {
    public boolean cadastrar(Mesa mesa){
        MesaDAO mesaDAO = new MesaDAO();

        boolean cadastrado;
        if(cadastrado = mesaDAO.cadastrar(mesa)){
            System.out.println(cadastrado);
            return cadastrado;
        } else{
            return false;
        }
    }

    public boolean editarMesa(Mesa mesa){
        MesaDAO mesaDAO = new MesaDAO();
        return  mesaDAO.atualizar(mesa);
    }

    public Mesa getMesaById(int id){
        MesaDAO mesaDAO = new MesaDAO();
        return mesaDAO.getMesa(id);
    }

    public boolean deletarMesa(int id){
        MesaDAO mesaDAO = new MesaDAO();
        return mesaDAO.apagarMesa(id);
    }
}