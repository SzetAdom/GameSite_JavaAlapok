
package Repository;

import Model.Database;
import Model.Comment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;


public class CommentRepo {
    public static Boolean addComment(Comment comment) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("addComment");

                spq.registerStoredProcedureParameter("in_text", String.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_likes", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_user_id", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("in_game_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_text", comment.getText());
                spq.setParameter("in_likes", comment.getLikes());
                spq.setParameter("in_user_id", comment.getUserId().getUserId());
                spq.setParameter("in_game_id", comment.getGameId().getGameId());

                spq.execute();

                em.close();

                System.out.println("Comment sikeresen hozzáadva!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: addComment - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static List<Comment> getAllActiveComments() {

        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveComments");
                List<Comment> result = new ArrayList<>();

                List<Object[]> comments = spq.getResultList();
                for (Object[] comment : comments) {
                    int id = Integer.parseInt(comment[0].toString());
                    Comment g = em.find(Comment.class, id);
                    result.add(g);
                }

                em.close();

                return result;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getAllActiveComments - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Comment getCommentById(Integer id) {

        try {

            EntityManager em = Database.getDbConn();
            try {
                Comment comment = em.find(Comment.class, id);
                em.close();
                return comment;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: getCommentById - " + ex.getMessage());
                return null;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return null;
        }
    }

    public static Boolean updateComment(Comment comment) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                em.getTransaction().begin();
                Comment uj = em.find(Comment.class, comment.getCommentId());
                uj.setText(comment.getText());
                uj.setLikes(comment.getLikes());
                uj.setUserId(comment.getUserId());
                uj.setGameId(comment.getGameId());
                em.getTransaction().commit();

                System.out.println("Comment sikeresen frissítve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: updateComment - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    public static Boolean deleteComment(Integer id) {
        try {

            EntityManager em = Database.getDbConn();
            try {
                StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteComment");

                spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);

                spq.setParameter("in_id", id);

                spq.execute();

                em.close();

                System.out.println("Comment sikeresen törölve!");
                return true;

            } catch (Exception ex) {
                em.close();

                System.out.println("Hiba: deleteComment - " + ex.getMessage());
                return false;
            }
        } catch (Exception e) {
            System.out.println("Database connection hiba! - " + e.getMessage());
            return false;
        }
    }

    
}
