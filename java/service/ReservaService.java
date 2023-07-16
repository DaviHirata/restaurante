package service;

import dao.ReservaDAO;
import model.Reserva;

public class ReservaService {
    public boolean cadastrar(Reserva reserva) {
        ReservaDAO reservaDAO = new ReservaDAO();
        Reserva reservaCadastrada = reservaDAO.cadastrarReserva(reserva);
        return reservaCadastrada != null; // Retorna true se a reserva foi cadastrada com sucesso, false caso contrário
    }

    public boolean editarReserva(Reserva reserva) {
        ReservaDAO reservaDAO = new ReservaDAO();
        return reservaDAO.atualizarReserva(reserva);
    }

    public Reserva getReservaById(int id) {
        ReservaDAO reservaDAO = new ReservaDAO();
        return reservaDAO.getReserva(id);
    }

    public boolean deletarReserva(int id) {
        ReservaDAO reservaDAO = new ReservaDAO();
        return reservaDAO.excluirReserva(id);
    }
}
