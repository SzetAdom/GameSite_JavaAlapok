/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Coupon;
import Model.CouponType;
import Model.User;
import Service.CouponService;
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
public class CouponController extends HttpServlet {

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
            if (request.getParameter("task").equals("addCoupon")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("userId").isEmpty()
                        && !request.getParameter("couponTypeId").isEmpty()) {
                    try {
                        Integer userId = Integer.parseInt(request.getParameter("userId"));
                        Integer couponTypeId = Integer.parseInt(request.getParameter("couponTypeId"));

                        
                        String serviceResult = CouponService.addCoupon(userId, couponTypeId);
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
            
            
            if (request.getParameter("task").equals("getAllActiveCoupons")) {
                JSONArray returnValue = new JSONArray();
                List<Coupon> coupons = CouponService.getAllActiveCoupons();
                if (coupons.isEmpty()){
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincsenek kuponok");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for (Coupon c : coupons) {
                        returnValue.put(c.toJson());
                    }
                    out.print(returnValue);
                }
            }
            
            if (request.getParameter("task").equals("getCouponById")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Coupon serviceResult = CouponService.getCouponById(id);
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
            
            if (request.getParameter("task").equals("updateCoupon")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("couponTypeId").isEmpty()
                        && !request.getParameter("userId").isEmpty()
                        && !request.getParameter("couponId").isEmpty()) {
                    try {
                        Integer couponId = Integer.parseInt(request.getParameter("couponId"));
                        Integer couponTypeId = Integer.parseInt(request.getParameter("couponTypeId"));
                        Integer userId = Integer.parseInt(request.getParameter("userId"));
                        User u = new User(userId);
                        CouponType ct = new CouponType(couponTypeId);
                        

                        Coupon c = new Coupon(couponId, ct, u);
                        String serviceResult = CouponService.updateCoupon(c);
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
            
            if (request.getParameter("task").equals("getUsersCoupons")) {
                JSONArray returnValue = new JSONArray();
                if (!request.getParameter("userId").isEmpty()) {
                    try {
                        Integer userId = Integer.parseInt(request.getParameter("userId"));
                        List<Coupon> coupons = CouponService.getUsersCoupons(userId);
                if (coupons.isEmpty()) {
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincsenek kuponok");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                } else {
                    for (Coupon c : coupons) {
                        returnValue.put(c.toJson());
                    }
                    out.print(returnValue);
                }
                        

                    } catch (Exception e) {
                        System.out.println("Hiba a JSON adatok beolvasásakor!");
                    }

                } else {
                    JSONObject obj = new JSONObject();
                    obj.put("result", "A mezők nincsenek megfelelően kitöltve!");
                    returnValue.put(obj);
                }
                
            }
            
            if (request.getParameter("task").equals("deleteCoupon")) {
                JSONObject result = new JSONObject();
                if (!request.getParameter("id").isEmpty()) {
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        String serviceResult = CouponService.logicalDeleteCoupon(id);
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
        catch (Exception ex){
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
