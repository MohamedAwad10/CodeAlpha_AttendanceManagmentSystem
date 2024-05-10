package org.codealpha.attendancesystem.dal.entity;
// Generated Apr 30, 2024 6:32:15 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Students generated by hbm2java
 */
@Entity
@Table(name="students"
    ,catalog="attendance_management_system_java"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class Students  implements java.io.Serializable {


     private Integer id;
     private String firstName;
     private String lastName;
     private String email;
     private String phoneNumber;
     private Date enrollmentDate;
     private Set<StudentsCources> studentsCourceses = new HashSet<StudentsCources>(0);

    public Students() {
    }

	
    public Students(String firstName, String lastName, String email, Date enroDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enroDate;
    }
    public Students(String firstName, String lastName, String email, String phoneNumber, Date enrollmentDate, Set<StudentsCources> studentsCourceses) {
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.phoneNumber = phoneNumber;
       this.enrollmentDate = enrollmentDate;
       this.studentsCourceses = studentsCourceses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="first_name", nullable=false, length=50)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name="last_name", nullable=false, length=50)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    @Column(name="email", unique=true, nullable=false, length=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="phone_number", length=15)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="enrollment_date", nullable=false, length=10)
    public Date getEnrollmentDate() {
        return this.enrollmentDate;
    }
    
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="students")
    public Set<StudentsCources> getStudentsCourceses() {
        return this.studentsCourceses;
    }
    
    public void setStudentsCourceses(Set<StudentsCources> studentsCourceses) {
        this.studentsCourceses = studentsCourceses;
    }




}

