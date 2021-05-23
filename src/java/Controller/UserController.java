package Controller;

import Model.User;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author adams
 */
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("task").equals("addUser")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("username").isEmpty()
                        && !request.getParameter("password").isEmpty()
                        && !request.getParameter("email").isEmpty()
                        && !request.getParameter("birthDate").isEmpty()
                        && !request.getParameter("isAdmin").isEmpty()
                        && !request.getParameter("currentPoints").isEmpty()) {
                    try {

                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String email = request.getParameter("email");
                        Date birthDate = Date.valueOf(request.getParameter("birthDate"));
                        Boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
                        Integer currentPoints = Integer.parseInt(request.getParameter("currentPoints"));

                        User user = new User(username, password, email, birthDate, isAdmin, currentPoints);
                        Boolean serviceResult = UserService.addUser(user);
                        if (serviceResult != null) {
                            result.put("result", serviceResult);
                        } else {
                            result.put("result", "Hibás értékek!");
                        }

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor! - " + e.getMessage());
                    }

                } else {
                    result.put("result", "A mezők nincsenek megfelelően kitöltve!");
                }
                out.println(result);
            }

            if (request.getParameter("task").equals("getUserById")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        User serviceResult = UserService.getUserById(id);
                        if (serviceResult != null) {
                            result.put("result", serviceResult.toString());
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

            if (request.getParameter("task").equals("getAllActiveUser")) {
                JSONArray returnValue = new JSONArray();
                List<User> users = UserService.getAllActiveUser();
                if (users.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincs aktív statisztika");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for (User u : users) {
                        returnValue.put(u.toJson());
                    }
                    out.print(returnValue);
                }
            }

            if (request.getParameter("task").equals("updateUser")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()
                        && !request.getParameter("username").isEmpty()
                        && !request.getParameter("email").isEmpty()
                        && !request.getParameter("birthDate").isEmpty()
                        && !request.getParameter("isAdmin").isEmpty()
                        && !request.getParameter("currentPoints").isEmpty()) {
                    try {

                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String email = request.getParameter("email");
                        Date birthDate = Date.valueOf(request.getParameter("birthDate"));
                        Boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
                        Integer currentPoints = Integer.parseInt(request.getParameter("currentPoints"));

                        User user = new User(id, username, password, email, birthDate, isAdmin, currentPoints);
                        Boolean serviceResult = UserService.updateUser(user);
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

            if (request.getParameter("task").equals("deleteUser")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Boolean serviceResult = UserService.deleteUser(id);
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

            if (request.getParameter("task").equals("updatePassword")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()
                        && !request.getParameter("old").isEmpty()
                        && !request.getParameter("password").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String old = request.getParameter("old");
                        String password = request.getParameter("password");
                        Boolean serviceResult = UserService.updatePassword(id, old, password);
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

            if (request.getParameter("task").equals("login")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("username").isEmpty()
                        && !request.getParameter("password").isEmpty()) {
                    try {
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        Boolean serviceResult = UserService.login(username, password);
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
