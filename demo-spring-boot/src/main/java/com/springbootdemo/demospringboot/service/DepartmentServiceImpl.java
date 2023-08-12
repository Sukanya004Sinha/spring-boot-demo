package com.springbootdemo.demospringboot.service;

import com.springbootdemo.demospringboot.entity.Department;
import com.springbootdemo.demospringboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);

    }
}
