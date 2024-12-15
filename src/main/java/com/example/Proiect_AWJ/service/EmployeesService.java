package com.example.Proiect_AWJ.service;

import com.example.Proiect_AWJ.model.Employees;
import com.example.Proiect_AWJ.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employees> getAllEmployees(){
        return (List<Employees>) employeesRepository.findAll();
    }

    public Optional<Employees> findById(Long id){
        return employeesRepository.findById(id);
    }

    public Employees save(Employees employees){
        return employeesRepository.save(employees);
    }

    public void deleteById(Long id){
        employeesRepository.deleteById(id);
    }
}
