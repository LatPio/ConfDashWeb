package com.flystonedev.cutomer.controller;

import com.flystonedev.cutomer.DTO.DepartmentDTO;
import com.flystonedev.cutomer.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public void addDepartment (@RequestBody DepartmentDTO departmentDTO){
        log.info("New Department registration to dataBase {}", departmentDTO);
        departmentService.addDepartment(departmentDTO);
    }

    @GetMapping
    public ResponseEntity<DepartmentDTO> get(@RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DepartmentDTO>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.departmentResponseList());
    }
    @PutMapping
    public ResponseEntity<DepartmentDTO> update(@RequestBody DepartmentDTO departmentDTO){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.update(departmentDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id){
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
