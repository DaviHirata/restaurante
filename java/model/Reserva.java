package model;

import java.sql.Timestamp;

public class Reserva {
    private int id;
    private Mesa mesa;
    private String nomeCliente;
    private Timestamp dataHoraReserva;
    private int numeroPessoas;

    public Reserva() {
    }

    public Reserva(int id, Mesa mesa, String nomeCliente, Timestamp dataHoraReserva, int numeroPessoas) {
        this.id = id;
        this.mesa = mesa;
        this.nomeCliente = nomeCliente;
        this.dataHoraReserva = dataHoraReserva;
        this.numeroPessoas = numeroPessoas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Timestamp getDataHoraReserva() {
        return dataHoraReserva;
    }

    public void setDataHoraReserva(Timestamp dataHoraReserva) {
        this.dataHoraReserva = dataHoraReserva;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }
}