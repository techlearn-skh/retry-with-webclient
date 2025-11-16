package com.skh.repositories;


import com.skh.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    // All basic CRUD methods are available from JpaRepository:
    // save, findById, findAll, deleteById, existsById, count, etc.
}
