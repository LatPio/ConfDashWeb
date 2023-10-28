package com.flystonedev.customer.repository;


import com.flystonedev.customer.model.InvoiceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDataRepository extends JpaRepository<InvoiceData, Integer> {

    InvoiceData findDepartmentByName (String name);
    long count();
    @Query("SELECT count (DISTINCT country) from InvoiceData ")
    Long countDistinctCountries();

    @Query("SELECT count (DISTINCT institution) from InvoiceData ")
    Long countDistinctInstitutions();
}
