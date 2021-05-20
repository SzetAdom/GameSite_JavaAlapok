package Controller;

import Model.Game;
import Service.GameService;
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
public class GameController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("task").equals("addGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("name").isEmpty()
                        && !request.getParameter("category").isEmpty()
                        && !request.getParameter("description").isEmpty()
                        && !request.getParameter("releasedate").isEmpty()) {
                    try {
                        String name = request.getParameter("name");
                        String category = request.getParameter("category");
                        String description = request.getParameter("description");
                        Date releaseDate = Date.valueOf(request.getParameter("releasedate"));

                        Game game = new Game(name, description, category, releaseDate);
                        Boolean serviceResult = GameService.addGame(game);
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

            if (request.getParameter("task").equals("getGameById")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Game serviceResult = GameService.getGameById(id);
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

            if (request.getParameter("task").equals("getAllActiveGames")) {
                JSONArray returnValue = new JSONArray();
                List<Game> games = GameService.getAllActiveGames();
                if (games.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincs aktív játék");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for (Game g : games) {
                        returnValue.put(g.toJson());
                    }
                    out.print(returnValue);
                }
            }

            if (request.getParameter("task").equals("updateGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()
                        && !request.getParameter("name").isEmpty()
                        && !request.getParameter("category").isEmpty()
                        && !request.getParameter("description").isEmpty()
                        && !request.getParameter("releasedate").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String name = request.getParameter("name");
                        String category = request.getParameter("category");
                        String description = request.getParameter("description");
                        Date releaseDate = Date.valueOf(request.getParameter("releasedate"));

                        Game game = new Game(id, name, description, category, releaseDate);
                        Boolean serviceResult = GameService.updateGame(game);
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

            if (request.getParameter("task").equals("deleteGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Boolean serviceResult = GameService.deleteGame(id);
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

            if (request.getParameter("task").equals("likeGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Boolean serviceResult = GameService.likeGame(id);
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

            if (request.getParameter("task").equals("dislikeGame")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Boolean serviceResult = GameService.dislikeGame(id);
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
        } catch (Exception ex) {
            System.out.println("JSON exception - " + ex.getMessage());
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
