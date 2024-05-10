package org.codealpha.attendancesystem.dal.entity;
// Generated Apr 30, 2024 6:32:15 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Assignments generated by hbm2java
 */
@Entity
@Table(name="assignments"
    ,catalog="attendance_management_system_java"
)
public class Assignments  implements java.io.Serializable {


     private Integer id;
     private Cources cources;
     private String title;
     private String description;
     private Date dueDate;
     private BigDecimal maxScore;

    public Assignments() {
    }

	
    public Assignments(Cources cources, String title, Date dueDate, BigDecimal maxScore) {
        this.cources = cources;
        this.title = title;
        this.dueDate = dueDate;
        this.maxScore = maxScore;
    }
    public Assignments(Cources cources, String title, String description, Date dueDate, BigDecimal maxScore) {
       this.cources = cources;
       this.title = title;
       this.description = description;
       this.dueDate = dueDate;
       this.maxScore = maxScore;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="cources_id", nullable=false)
    public Cources getCources() {
        return this.cources;
    }
    
    public void setCources(Cources cources) {
        this.cources = cources;
    }

    
    @Column(name="title", nullable=false, length=100)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="description", length=65535)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="due_date", nullable=false, length=10)
    public Date getDueDate() {
        return this.dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    
    @Column(name="max_score", nullable=false, precision=5)
    public BigDecimal getMaxScore() {
        return this.maxScore;
    }
    
    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }

}


