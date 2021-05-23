
package Controller;

import Model.Comment;
import Model.Game;
import Model.User;
import Service.CommentService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;


public class CommentController extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("task").equals("addComment")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("text").isEmpty()
                        && !request.getParameter("likes").isEmpty()
                        && !request.getParameter("userId").isEmpty()
                        && !request.getParameter("gameId").isEmpty()) {
                    try {
                        String text = request.getParameter("text");
                        Integer likes = Integer.parseInt(request.getParameter("likes"));
                        User u = new User(Integer.parseInt(request.getParameter("likes")));
                        Game g = new Game(Integer.parseInt(request.getParameter("likes")));

                        Comment comment = new Comment(text, likes, u, g);
                        Boolean serviceResult = CommentService.addComment(comment);
                        if (serviceResult != null) {
                            result.put("result", serviceResult);
                        } else {
                            result.put("result", "Hibás értékek!");
                        }

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve!");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getCommentById")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Comment serviceResult = CommentService.getCommentById(id);
                        if (serviceResult != null) {
                            result.put("result", serviceResult.toJson());
                        } else {
                            result.put("result", "Hibás értékek!");
                        }

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve!");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getAllActiveComments")) {
                JSONArray returnValue = new JSONArray();
                List<Comment> comments = CommentService.getAllActiveComments();
                if (comments.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincs aktív játék");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for (Comment g : comments) {
                        returnValue.put(g.toJson());
                    }
                    out.print(returnValue);
                }
            }

            if (request.getParameter("task").equals("updateComment")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()
                        && !request.getParameter("text").isEmpty()
                        && !request.getParameter("likes").isEmpty()
                        && !request.getParameter("userId").isEmpty()
                        && !request.getParameter("gameId").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String text = request.getParameter("text");
                        Integer likes = Integer.parseInt(request.getParameter("likes"));
                        User u = new User(Integer.parseInt(request.getParameter("likes")));
                        Game g = new Game(Integer.parseInt(request.getParameter("likes")));

                        Comment comment = new Comment(id, text, likes, u, g);
                        Boolean serviceResult = CommentService.updateComment(comment);
                        if (serviceResult != null) {
                            result.put("result", serviceResult);
                        } else {
                            result.put("result", "Hibás értékek!");
                        }

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve!");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("deleteComment")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Boolean serviceResult = CommentService.deleteComment(id);
                        if (serviceResult != null) {
                            result.put("result", serviceResult);
                        } else {
                            result.put("result", "Hibás értékek!");
                        }

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve!");
                }
                out.println(result);
            }

            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
