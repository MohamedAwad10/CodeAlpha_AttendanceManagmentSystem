package org.codealpha.attendancesystem.dal.entity;
// Generated Apr 30, 2024 6:32:15 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * StudentsCources generated by hbm2java
 */
@Entity
@Table(name="students_cources"
    ,catalog="attendance_management_system_java"
)
public class StudentsCources  implements java.io.Serializable {


     private Integer id;
     private Cources cources;
     private Students students;
     private Set<AttendanceRecords> attendanceRecordses = new HashSet<AttendanceRecords>(0);

    public StudentsCources() {
    }

	
    public StudentsCources(Cources cources, Students students) {
        this.cources = cources;
        this.students = students;
    }
    public StudentsCources(Cources cources, Students students, Set<AttendanceRecords> attendanceRecordses) {
       this.cources = cources;
       this.students = students;
       this.attendanceRecordses = attendanceRecordses;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="students_id", nullable=false)
    public Students getStudents() {
        return this.students;
    }
    
    public void setStudents(Students students) {
        this.students = students;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="studentsCources")
    public Set<AttendanceRecords> getAttendanceRecordses() {
        return this.attendanceRecordses;
    }
    
    public void setAttendanceRecordses(Set<AttendanceRecords> attendanceRecordses) {
        this.attendanceRecordses = attendanceRecordses;
    }




}


