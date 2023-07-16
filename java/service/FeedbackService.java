package service;

import dao.FeedbackDAO;
import model.Feedback;

public class FeedbackService {
    public boolean cadastrar(Feedback feedback) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        return feedbackDAO.cadastrar(feedback);
    }

    public boolean atualizar(Feedback feedback) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        return feedbackDAO.atualizar(feedback);
    }

    public Feedback getFeedbackById(int id) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        return feedbackDAO.getFeedback(id);
    }

    public boolean excluirFeedback(int id) {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        return feedbackDAO.excluirFeedback(id);
    }
}