/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adams
 */
@Entity
@Table(name = "statistics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statistics.findAll", query = "SELECT s FROM Statistics s"),
    @NamedQuery(name = "Statistics.findByStatisticsId", query = "SELECT s FROM Statistics s WHERE s.statisticsId = :statisticsId"),
    @NamedQuery(name = "Statistics.findByFirstPlayed", query = "SELECT s FROM Statistics s WHERE s.firstPlayed = :firstPlayed"),
    @NamedQuery(name = "Statistics.findByLastPlayed", query = "SELECT s FROM Statistics s WHERE s.lastPlayed = :lastPlayed"),
    @NamedQuery(name = "Statistics.findByPlayedMinutes", query = "SELECT s FROM Statistics s WHERE s.playedMinutes = :playedMinutes"),
    @NamedQuery(name = "Statistics.findByIsactive", query = "SELECT s FROM Statistics s WHERE s.isactive = :isactive")})
public class Statistics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "statistics_id")
    private Integer statisticsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "first_played")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstPlayed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_played")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPlayed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "played_minutes")
    private int playedMinutes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive")
    private boolean isactive;
    @JoinColumn(name = "game_id", referencedColumnName = "game_id")
    @ManyToOne(optional = false)
    private Game gameId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public Statistics() {
    }

    public Statistics(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Statistics(Integer statisticsId, Date firstPlayed, Date lastPlayed, int playedMinutes, boolean isactive) {
        this.statisticsId = statisticsId;
        this.firstPlayed = firstPlayed;
        this.lastPlayed = lastPlayed;
        this.playedMinutes = playedMinutes;
        this.isactive = isactive;
    }

    public Integer getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }

    public Date getFirstPlayed() {
        return firstPlayed;
    }

    public void setFirstPlayed(Date firstPlayed) {
        this.firstPlayed = firstPlayed;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public int getPlayedMinutes() {
        return playedMinutes;
    }

    public void setPlayedMinutes(int playedMinutes) {
        this.playedMinutes = playedMinutes;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statisticsId != null ? statisticsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statistics)) {
            return false;
        }
        Statistics other = (Statistics) object;
        if ((this.statisticsId == null && other.statisticsId != null) || (this.statisticsId != null && !this.statisticsId.equals(other.statisticsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Statistics[ statisticsId=" + statisticsId + " ]";
    }

}
