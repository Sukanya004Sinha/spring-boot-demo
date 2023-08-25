package com.springbootdemo.demospringboot.controller;

import com.springbootdemo.demospringboot.entity.Department;
import com.springbootdemo.demospringboot.error.DepartmentNotFoundException;
import com.springbootdemo.demospringboot.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static javax.management.Query.value;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;
    private Department department;


    @BeforeEach
    void setUp() {
        department= Department.builder()
                .departmentAddress("Ahmedabad")
                .departmentId(1L)
                .departmentCode("IT-06")
                .departmentName("IT")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
       Department inputDepartment =  Department.builder()
                .departmentAddress("Ahmedabad")
                .departmentId(1L)
                .departmentCode("IT-06")
                .departmentName("EE")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);
        mockMvc.perform(post( "/departments")
        .contentType(MediaType.APPLICATION_JSON)
                .content("\n" +
                        "{\"departmentName\": \"CB\",\n" +
                        "\"departmentAddress\" : \"London\",\n" +
                        " \"departmentCode\" : \"Commercial banking\"\n" +
                        "}\n")) .andExpect(status().isOk());

    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);
       mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(status().isOk())
               .andExpect(jsonPath("$.departmentName").
                value(department.getDepartmentName()));



    }
}
