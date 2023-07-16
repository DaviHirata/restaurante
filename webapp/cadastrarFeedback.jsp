<%@ page import="java.util.List" %>
<%@ page import="dao.ReservaDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cadastrar Feedback</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h2 class="mt-3">Cadastrar Feedback</h2>
            <form method="POST" action="feedback">
                <div class="mb-3">
                    <label for="nomeCliente" class="form-label">Nome do Cliente:</label>
                    <select id="nomeCliente" name="nomeCliente" class="form-select" required>
                        <option value="">Selecione um cliente</option>
                        <% List<String> nomesClientes = ReservaDAO.getNomesClientes(); %>
                        <% for (String nomeCliente : nomesClientes) { %>
                        <option value="<%= nomeCliente %>"><%= nomeCliente %></option>
                        <% } %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="classificacao" class="form-label">Classificação:</label>
                    <select id="classificacao" name="classificacao" class="form-select" required>
                        <option value="5">5 - Excelente</option>
                        <option value="4">4 - Muito Bom</option>
                        <option value="3">3 - Bom</option>
                        <option value="2">2 - Regular</option>
                        <option value="1">1 - Ruim</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Inserir</button>
            </form>

            <a href="gerenciarFeedbacks.jsp" class="btn btn-link mt-3">Voltar</a>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>