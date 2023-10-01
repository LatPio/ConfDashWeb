package com.flystonedev.customer.repository;


import com.flystonedev.customer.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findDepartmentByName (String name);
    long count();
    @Query("SELECT count (DISTINCT country) from Department ")
    Long countDistinctCountries();
}
