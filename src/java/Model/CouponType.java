/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adams
 */
@Entity
@Table(name = "coupon_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CouponType.findAll", query = "SELECT c FROM CouponType c"),
    @NamedQuery(name = "CouponType.findByCouponTypeId", query = "SELECT c FROM CouponType c WHERE c.couponTypeId = :couponTypeId"),
    @NamedQuery(name = "CouponType.findByShop", query = "SELECT c FROM CouponType c WHERE c.shop = :shop"),
    @NamedQuery(name = "CouponType.findByValue", query = "SELECT c FROM CouponType c WHERE c.value = :value"),
    @NamedQuery(name = "CouponType.findByPurchaseable", query = "SELECT c FROM CouponType c WHERE c.purchaseable = :purchaseable"),
    @NamedQuery(name = "CouponType.findByLasts", query = "SELECT c FROM CouponType c WHERE c.lasts = :lasts"),
    @NamedQuery(name = "CouponType.findByIsactive", query = "SELECT c FROM CouponType c WHERE c.isactive = :isactive")})
public class CouponType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "coupon_type_id")
    private Integer couponTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "shop")
    private String shop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private int value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "purchaseable")
    private boolean purchaseable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lasts")
    private int lasts;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive")
    private boolean isactive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "couponTypeId")
    private Collection<Coupon> couponCollection;

    public CouponType() {
    }

    public CouponType(Integer couponTypeId) {
        this.couponTypeId = couponTypeId;
    }

    public CouponType(Integer couponTypeId, String shop, int value, boolean purchaseable, int lasts, boolean isactive) {
        this.couponTypeId = couponTypeId;
        this.shop = shop;
        this.value = value;
        this.purchaseable = purchaseable;
        this.lasts = lasts;
        this.isactive = isactive;
    }

    public Integer getCouponTypeId() {
        return couponTypeId;
    }

    public void setCouponTypeId(Integer couponTypeId) {
        this.couponTypeId = couponTypeId;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean getPurchaseable() {
        return purchaseable;
    }

    public void setPurchaseable(boolean purchaseable) {
        this.purchaseable = purchaseable;
    }

    public int getLasts() {
        return lasts;
    }

    public void setLasts(int lasts) {
        this.lasts = lasts;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    @XmlTransient
    public Collection<Coupon> getCouponCollection() {
        return couponCollection;
    }

    public void setCouponCollection(Collection<Coupon> couponCollection) {
        this.couponCollection = couponCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (couponTypeId != null ? couponTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CouponType)) {
            return false;
        }
        CouponType other = (CouponType) object;
        if ((this.couponTypeId == null && other.couponTypeId != null) || (this.couponTypeId != null && !this.couponTypeId.equals(other.couponTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.CouponType[ couponTypeId=" + couponTypeId + " ]";
    }

}
