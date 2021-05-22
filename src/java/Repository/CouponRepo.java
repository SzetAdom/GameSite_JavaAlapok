
package Repository;

import Model.Database;
import Model.Coupon;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;


public class CouponRepo {
    
    public static boolean addCoupon(Integer userId, Integer couponTypeId){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addCoupon");
        
            
            spq.registerStoredProcedureParameter("in_user_id", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_coupon_type_id", Integer.class, ParameterMode.IN);
            
            
            spq.setParameter("in_user_id", userId);
            spq.setParameter("in_coupon_type_id", couponTypeId);
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static List<Coupon> getAllActiveCoupons(){
        List<Coupon> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveCoupons");
            
            List<Object[]> coupons = spq.getResultList();
            for(Object[] coupon : coupons){
                int id = Integer.parseInt(coupon[0].toString());
                Coupon c = em.find(Coupon.class, id);
                result.add(c);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            return result;
        }
    }
    
    public static List<Coupon> getUsersCoupons(Integer userId){
        List<Coupon> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getUsersCoupons");
            spq.registerStoredProcedureParameter("in_user_id", Integer.class, ParameterMode.IN);
            
            spq.setParameter("in_user_id", userId);
            
            spq.execute();
            
            List<Object[]> coupons = spq.getResultList();
            for(Object[] coupon : coupons){
                int id = Integer.parseInt(coupon[0].toString());
                Coupon c = em.find(Coupon.class, id);
                result.add(c);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
            return result;
        }
    }
        
        public static boolean logicalDeleteCoupon(Integer id){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteCoupon");
        
            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
            
            spq.setParameter("in_id", id);
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
    public static boolean updateCoupon(Coupon in){
        try{
            EntityManager em = Database.getDbConn();
            em.getTransaction().begin();
            Coupon uj = em.find(Coupon.class, in.getCouponId());
            uj.setCouponTypeId(in.getCouponTypeId());
            uj.setUserId(in.getUserId());
            em.getTransaction().commit();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
        
    public static Coupon getCouponById(Integer id) {

        try{
            EntityManager em = Database.getDbConn();
            Coupon c = em.find(Coupon.class, id);
                em.close();
                return c;
        }
        catch(Exception ex){
            return null;
        }
    }    
    
    
}
