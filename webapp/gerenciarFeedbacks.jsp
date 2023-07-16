<%@ page import="model.Feedback" %>
<%@ page import="dao.FeedbackDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Feedbacks</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
  </head>
  <body>
    <div class="container">
      <h1 class="mt-3">Feedbacks</h1>

      <a href="/restaurante_teste/cadastrarFeedback.jsp" class="btn btn-primary">Cadastrar novo feedback</a>

      <table class="table mt-3">
        <thead>
        <tr>
          <th>ID</th>
          <th>Nome do Cliente</th>
          <th>Data do Feedback</th>
          <th>Classificação</th>
          <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
          ArrayList<Feedback> feedbacks = new FeedbackDAO().getFeedbacks();
          for (Feedback feedback : feedbacks) {
        %>
        <tr>
          <td><%= feedback.getId() %></td>
          <td><%= feedback.getNomeCliente() %></td>
          <td><%= feedback.getDataFeedback() %></td>
          <td><%= feedback.getClassificacao() %></td>
          <td>
            <a href="editarFeedback.jsp?id=<%= feedback.getId() %>" class="btn btn-primary">Editar</a>
            <form method="post" action="feedback" style="display: inline;">
              <input type="hidden" name="action" value="excluir" />
              <input type="hidden" name="id" value="<%= feedback.getId() %>" />
              <button type="submit" class="btn btn-danger" onclick="return confirm('Deseja realmente excluir este feedback?')">Excluir</button>
            </form>
          </td>
        </tr>
        <% } %>
        </tbody>
      </table>

      <a href="principal.jsp" class="btn btn-link mt-3">Voltar</a>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
  </body>
</html>