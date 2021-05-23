
package Service;

import Model.CouponType;
import Repository.CouponTypeRepo;
import java.util.List;


public class CouponTypeService {
    public static String addCouponType(CouponType c) {
        
        if (c.getShop().length() > 0 && c.getShop().length() <= 20 && c.getValue() > 0 && c.getLasts() > 0) {
            if(CouponTypeRepo.addCouponType(c)){
                return "A rögzítés sikeresen megtörtént";
            }else{
                return "Az adatok helyesek de a rögzítés nem sikerült";
            }
        } else {
            return "Az adatok helytelenek";
        }
    }
    
    public static List<CouponType> getAllActiveCouponTypes(){
        return CouponTypeRepo.getAllActiveCouponTypes();
    }
    
    public static List<CouponType> getAllPurchaseableCouponTypes(){
        return CouponTypeRepo.getAllPurchaseableCouponTypes();
    }
    public static String logicalDeleteCouponType(Integer id){
        if (id > 0) {
            if( CouponTypeRepo.logicalDeleteCouponType(id))
                return "sikeres törlés";
            else return " a törlés nem sikerült";
        } else {
            System.out.println("Hibás értékek");
            return null;
        }
    }
    
    public static CouponType getCouponTypeById(Integer id){
        
        if (id > 0) {
            return CouponTypeRepo.getCouponTypeById(id);
        } else {
            System.out.println("Hibás id");
            return null;
        }
        
    }
    
    public static String updateCouponType(CouponType c){
        
        if (c.getShop().length() > 0 && c.getShop().length() <= 20 && c.getValue() > 0 && c.getLasts() > 0) {
            if(CouponTypeRepo.updateCouponType(c)){
                return "A változtatás rögzítése sikeresen megtörtént";
            }else{
                return "Az adatok helyesek de a rögzítés nem sikerült";
            }
        } else {
            return "Az adatok helytelenek";
        }
        
    }
    
   
}
