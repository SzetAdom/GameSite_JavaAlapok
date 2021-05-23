/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CouponType;
import Service.CouponTypeService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author fried
 */
public class CouponTypeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("task").equals("addCouponType")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("shop").isEmpty()
                        && !request.getParameter("value").isEmpty()
                        && !request.getParameter("purchaseable").isEmpty()
                        && !request.getParameter("lasts").isEmpty()) {
                    try {
                        String shop = request.getParameter("shop");
                        Integer value = Integer.parseInt(request.getParameter("value"));
                        Boolean purchaseable = Boolean.parseBoolean(request.getParameter("purchaseable"));
                        Integer lasts = Integer.parseInt(request.getParameter("lasts"));

                        CouponType c = new CouponType(shop, value, purchaseable, lasts);
                        String serviceResult = CouponTypeService.addCouponType(c);
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
            
            
            if (request.getParameter("task").equals("getAllActiveCouponTypes")) {
                JSONArray returnValue = new JSONArray();
                List<CouponType> couponTypes = CouponTypeService.getAllActiveCouponTypes();
                if (couponTypes.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincsenek kuponoktípusok");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for (CouponType c : couponTypes) {
                        returnValue.put(c.toJson());
                    }
                    out.print(returnValue);
                }
            }
            
            if (request.getParameter("task").equals("getCouponTypeById")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        CouponType serviceResult = CouponTypeService.getCouponTypeById(id);
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
            
            if (request.getParameter("task").equals("updateCouponType")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("shop").isEmpty()
                        && !request.getParameter("value").isEmpty()
                        && !request.getParameter("purchaseable").isEmpty()
                        && !request.getParameter("lasts").isEmpty()
                        && !request.getParameter("couponTypeId").isEmpty()){ 
                    try {
                        String shop = request.getParameter("shop");
                        Integer value = Integer.parseInt(request.getParameter("value"));
                        Boolean purchaseable = Boolean.parseBoolean(request.getParameter("purchaseable"));
                        Integer lasts = Integer.parseInt(request.getParameter("lasts"));
                        Integer id = Integer.parseInt(request.getParameter("couponTypeId"));
                        
                        

                        CouponType c = new CouponType(id, shop, value, purchaseable, lasts);
                        String serviceResult = CouponTypeService.updateCouponType(c);
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
            
            if (request.getParameter("task").equals("getAllPurchaseableCouponTypes")) {
                JSONArray returnValue = new JSONArray();
                
                    try {
                        
                        List<CouponType> couponTypes = CouponTypeService.getAllPurchaseableCouponTypes();
                if (couponTypes.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincsenek kuponok");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for (CouponType c : couponTypes) {
                        returnValue.put(c.toJson());
                    }
                    out.print(returnValue);
                }
                        

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                
                
            }
            
            if (request.getParameter("task").equals("deleteCouponType")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String serviceResult = CouponTypeService.logicalDeleteCouponType(id);
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
