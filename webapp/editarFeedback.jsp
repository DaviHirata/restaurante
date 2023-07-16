<%@ page import="model.Feedback" %>
<%@ page import="dao.FeedbackDAO" %>
<%@ page import="dao.ReservaDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Editar Feedback</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
  </head>
  <body>
    <div class="container">
      <h1 class="mt-3">Editar Feedback</h1>

      <%
        int feedbackId = Integer.parseInt(request.getParameter("id"));
        Feedback feedback = new FeedbackDAO().getFeedback(feedbackId);

        if (feedback != null) {
      %>
      <form method="post" action="feedback">
        <input type="hidden" name="action" value="atualizar" />
        <input type="hidden" name="id" value="<%= feedback.getId() %>" />

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
            <option value="5" <% if (feedback.getClassificacao() == 5) out.print("selected"); %>>5 - Excelente</option>
            <option value="4" <% if (feedback.getClassificacao() == 4) out.print("selected"); %>>4 - Muito Bom</option>
            <option value="3" <% if (feedback.getClassificacao() == 3) out.print("selected"); %>>3 - Bom</option>
            <option value="2" <% if (feedback.getClassificacao() == 2) out.print("selected"); %>>2 - Regular</option>
            <option value="1" <% if (feedback.getClassificacao() == 1) out.print("selected"); %>>1 - Ruim</option>
          </select>
        </div>

        <input type="submit" value="Atualizar" class="btn btn-primary" />
      </form>
      <% } else { %>
      <p>O feedback não foi encontrado.</p>
      <% } %>

      <a href="gerenciarFeedbacks.jsp" class="btn btn-link mt-3">Voltar</a>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
  </body>
</html>