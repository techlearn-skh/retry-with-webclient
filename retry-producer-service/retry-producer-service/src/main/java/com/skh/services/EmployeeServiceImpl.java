package com.skh.services;

import com.skh.controllers.Employee;
import com.skh.entities.EmployeeEntity;
import com.skh.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    // Create

    public EmployeeEntity createEmployee(Employee e) {
        EmployeeEntity emp = new EmployeeEntity(null, e.getEmpName(), e.getEmpDOJ(), e.getEmpSalary(), e.getIsPermenentEmp());
        /**
         * Above dont assign/set empId to the  EmployeeEntity class, why because we are using @GeneratedValue(strategy = GenerationType.IDENTITY)
         * in entity class.
         */

        return repo.save(emp);
    }

    // Read all
    public List<EmployeeEntity> getAllEmployees() {
        return repo.findAll();
    }

    // Read by id
    public Optional<EmployeeEntity> getEmployeeById(Integer id) {
        return repo.findById(id);
    }

    // Update (full replace)
    public EmployeeEntity updateEmployee(Integer id, EmployeeEntity updated) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setEmpName(updated.getEmpName());
                    existing.setEmpDOJ(updated.getEmpDOJ());
                    existing.setEmpSalary(updated.getEmpSalary());
                    existing.setIsPermenentEmp(updated.getIsPermenentEmp());
                    return repo.save(existing);
                })
                .orElseGet(() -> {
                    // If not found, set id and create new (optional behavior)
                    updated.setEmpId(id);
                    return repo.save(updated);
                });
    }

    // Delete
    public void deleteEmployee(Integer id) {
        repo.deleteById(id);
    }

    // Exists helper
    public boolean exists(Integer id) {
        return repo.existsById(id);
    }
}
