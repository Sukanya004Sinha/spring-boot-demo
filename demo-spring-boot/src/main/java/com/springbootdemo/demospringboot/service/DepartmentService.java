package com.springbootdemo.demospringboot.service;

import com.springbootdemo.demospringboot.entity.Department;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    Department fetchDepartmentById(Long departmentId);
}
