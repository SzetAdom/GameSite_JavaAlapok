
package Service;

import Model.Coupon;
import Repository.CouponRepo;
import java.util.List;


public class CouponService {
    
    public static String addCoupon(Integer userId, Integer couponTypeId) {
        
        if (userId > 0 && couponTypeId > 0) {
            if(CouponRepo.addCoupon(userId, couponTypeId)){
                return "A rögzítés sikeresen megtörtént";
            }else{
                return "Az adatok helyesek de a rögzítés nem sikerült";
            }
        } else {
            return "Az adatok helytelenek";
        }
    }
    
    public static List<Coupon> getAllActiveCoupons(){
        return CouponRepo.getAllActiveCoupons();
    }
    
    public static List<Coupon> getUsersCoupons(Integer userId){
        return CouponRepo.getUsersCoupons(userId);
    }
    
    public static String logicalDeleteCoupon(Integer id){
        if (id > 0) {
            if( CouponRepo.logicalDeleteCoupon(id))
                return "sikeres törlés";
            else return " a törlés nem sikerült";
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }
    
    public static Coupon getCouponById(Integer id){
        
        if (id > 0) {
            return CouponRepo.getCouponById(id);
        } else {
            System.out.println("Hibás id");
            return null;
        }
        
    }
    
    public static String updateCoupon(Coupon c){
        
        
            if(CouponRepo.updateCoupon(c)){
                return "A változtatás rögzítése sikeresen megtörtént";
            }else{
                return "Az adatok nem megfelelőek";
            }
        
        
    }
    
    
}
