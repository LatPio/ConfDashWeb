package com.flystonedev.cutomer.controller;

import com.flystonedev.cutomer.records.DepartmentResponse;
import com.flystonedev.cutomer.service.DepartmentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/department")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @PostMapping
    public void addDepartment (@RequestBody DepartmentResponse departmentResponse){
        log.info("New Department registration to dataBase {}", departmentResponse);
        departmentService.addDepartment(departmentResponse);
    }

    @GetMapping
    public ResponseEntity<DepartmentResponse> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DepartmentResponse>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.departmentResponseList());
    }
    @PutMapping
    public ResponseEntity<DepartmentResponse> update(@RequestBody DepartmentResponse departmentResponse){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.update(departmentResponse));
    }

    @DeleteMapping
    public ResponseEntity dlete(@RequestParam Integer id){
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
