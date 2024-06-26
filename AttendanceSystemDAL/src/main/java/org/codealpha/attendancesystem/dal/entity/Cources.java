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

/**
 * Cources generated by hbm2java
 */
@Entity
@Table(name="cources"
    ,catalog="attendance_management_system_java"
)
public class Cources  implements java.io.Serializable {


     private Integer id;
     private String courseName;
     private String instructor;
     private Date startDate;
     private Date endDate;
     private String location;
     private int capacity;
     private Set<StudentsCources> studentsCourceses = new HashSet<StudentsCources>(0);
     private Set<Assignments> assignmentses = new HashSet<Assignments>(0);

    public Cources() {
    }

	
    public Cources(String courseName, String instructor, Date startDate, Date endDate, int capacity) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
    }
    public Cources(String courseName, String instructor, Date startDate, Date endDate, String location, int capacity, Set<StudentsCources> studentsCourceses, Set<Assignments> assignmentses) {
       this.courseName = courseName;
       this.instructor = instructor;
       this.startDate = startDate;
       this.endDate = endDate;
       this.location = location;
       this.capacity = capacity;
       this.studentsCourceses = studentsCourceses;
       this.assignmentses = assignmentses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="course_name", nullable=false, length=100)
    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    
    @Column(name="instructor", nullable=false, length=100)
    public String getInstructor() {
        return this.instructor;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="start_date", nullable=false, length=10)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="end_date", nullable=false, length=10)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    @Column(name="location", length=100)
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    
    @Column(name="capacity", nullable=false)
    public int getCapacity() {
        return this.capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

@OneToMany(fetch=FetchType.EAGER, mappedBy="cources")
    public Set<StudentsCources> getStudentsCourceses() {
        return this.studentsCourceses;
    }
    
    public void setStudentsCourceses(Set<StudentsCources> studentsCourceses) {
        this.studentsCourceses = studentsCourceses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cources")
    public Set<Assignments> getAssignmentses() {
        return this.assignmentses;
    }
    
    public void setAssignmentses(Set<Assignments> assignmentses) {
        this.assignmentses = assignmentses;
    }




}


