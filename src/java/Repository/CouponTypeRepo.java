
package Repository;

import Model.Database;
import Model.CouponType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class CouponTypeRepo {
    public static boolean addCouponType(CouponType c){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addCouponType");
        
            spq.registerStoredProcedureParameter("in_shop", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_value", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_purchaseable", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("in_lasts", Integer.class, ParameterMode.IN);
            
            spq.setParameter("in_shop", c.getShop());
            spq.setParameter("in_value", c.getValue());
            spq.setParameter("in_purchaseable", c.getPurchaseable());
            spq.setParameter("in_lasts", c.getLasts());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static List<CouponType> getAllActiveCouponTypes(){
        List<CouponType> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveCouponTypes");
            
            List<Object[]> couponTypes = spq.getResultList();
            for(Object[] couponType : couponTypes){
                int id = Integer.parseInt(couponType[0].toString());
                CouponType c = em.find(CouponType.class, id);
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
    
    public static List<CouponType> getAllPurchaseableCouponTypes(){
        List<CouponType> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllPurchaseadbleCouponTypes");
            
            
            List<Object[]> couponTypes = spq.getResultList();
            for(Object[] couponType : couponTypes){
                int id = Integer.parseInt(couponType[0].toString());
                CouponType c = em.find(CouponType.class, id);
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
        
        public static boolean logicalDeleteCouponType(CouponType c){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteCouponType");
        
            spq.registerStoredProcedureParameter("in_id", Integer.class, ParameterMode.IN);
            
            spq.setParameter("in_id", c.getCouponTypeId());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
        
    public static boolean updateCouponType(CouponType in){
        try{
            EntityManager em = Database.getDbConn();
            em.getTransaction().begin();
            CouponType uj = em.find(CouponType.class, in.getCouponTypeId());
            uj.setShop(in.getShop());
            uj.setValue(in.getValue());
            uj.setPurchaseable(in.getPurchaseable());
            uj.setLasts(in.getLasts());
            em.getTransaction().commit();
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
        
    public static CouponType getCouponTypeById(Integer id) {

        try{
            EntityManager em = Database.getDbConn();
            CouponType c = em.find(CouponType.class, id);
                em.close();
                return c;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }  
}
