<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Mesa" %>
<%@ page import="dao.MesaDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Mesa" %>
<%@ page import="dao.MesaDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cadastrar Reserva</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h2 class="mt-3">Cadastrar Reserva</h2>
            <div>
                <form method="POST" action="reserva">
                    <div class="mb-3">
                        <label for="idMesa" class="form-label">Mesa:</label>
                        <select id="idMesa" name="idMesa" class="form-select" required>
                            <% ArrayList<Mesa> mesas = new MesaDAO().getMesas();
                                for (Mesa mesa : mesas) { %>
                            <option value="<%= mesa.getId() %>"><%= mesa.getId() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="nomeCliente" class="form-label">Nome do Cliente:</label>
                        <input type="text" id="nomeCliente" name="nomeCliente" class="form-control" required />
                    </div>

                    <div class="mb-3">
                        <label for="numeroPessoas" class="form-label">Número de Pessoas:</label>
                        <input type="number" id="numeroPessoas" name="numeroPessoas" class="form-control" required />
                    </div>

                    <input type="submit" value="Inserir" class="btn btn-primary" />
                    <c:if test="${not empty cadastroFeito}">
                        <h2 class="mt-3">Cadastro efetuado ${cadastroFeito.id}</h2>
                    </c:if>
                </form>
            </div>

            <a href="gerenciarReservas.jsp" class="btn btn-link mt-3">Voltar</a>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>