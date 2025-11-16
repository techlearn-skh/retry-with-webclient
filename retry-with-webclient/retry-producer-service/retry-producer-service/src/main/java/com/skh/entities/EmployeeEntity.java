package com.skh.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "emp_name", nullable = false, length = 100)
    private String empName;

    @Temporal(TemporalType.DATE)
    @Column(name = "emp_doj")
    private Date empDOJ;

    @Column(name = "emp_salary")
    private Double empSalary;

    @Column(name = "is_permanent_emp")
    private Boolean isPermenentEmp;

    // --- Constructors ---
    public EmployeeEntity() {}

    public EmployeeEntity(Integer empId, String empName, Date empDOJ,
                    Double empSalary, Boolean isPermenentEmp) {
        this.empId = empId;
        this.empName = empName;
        this.empDOJ = empDOJ;
        this.empSalary = empSalary;
        this.isPermenentEmp = isPermenentEmp;
    }

    // --- Getters & Setters ---
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getEmpDOJ() {
        return empDOJ;
    }

    public void setEmpDOJ(Date empDOJ) {
        this.empDOJ = empDOJ;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    public Boolean getIsPermenentEmp() {
        return isPermenentEmp;
    }

    public void setIsPermenentEmp(Boolean isPermenentEmp) {
        this.isPermenentEmp = isPermenentEmp;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empDOJ=" + empDOJ +
                ", empSalary=" + empSalary +
                ", isPermenentEmp=" + isPermenentEmp +
                '}';
    }
}
