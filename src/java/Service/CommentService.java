package Service;

import Model.Comment;
import Repository.CommentRepo;
import java.util.List;


public class CommentService {

    public static Boolean addComment(Comment comment) {
        if (comment.getText().length() > 0
                && comment.getUserId().getUserId() >= 0
                && comment.getGameId().getGameId() >= 0) {
            return CommentRepo.addComment(comment);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Comment getCommentById(Integer id) {
        if (id > 0) {
            return CommentRepo.getCommentById(id);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }

    }

    public static List<Comment> getAllActiveComments() {
        return CommentRepo.getAllActiveComments();
    }

    public static Boolean updateComment(Comment comment) {
        System.out.println("------------------------");
        System.out.println("updateComment");
        if (comment.getText().length() > 0
                && comment.getUserId().getUserId() >= 0
                && comment.getGameId().getGameId() >= 0) {
            return CommentRepo.updateComment(comment);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    public static Boolean deleteComment(Integer id) {
        if (id > 0) {
            return CommentRepo.deleteComment(id);
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }

    
}
