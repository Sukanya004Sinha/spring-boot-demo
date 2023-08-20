package com.springbootdemo.demospringboot.service;

import com.jayway.jsonpath.internal.function.latebinding.ILateBindingValue;
import com.springbootdemo.demospringboot.entity.Department;
import com.springbootdemo.demospringboot.repository.DepartmentRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .departmentName("EE")
                        .departmentAddress("London")
                        .departmentCode("EE-01")
                        .departmentId(1L)
                        .build();
              Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("EE")).thenReturn(department);
    }
    @Test
    @DisplayName("Get data based on valid department name")
    /**
     * When multiple test cases is running and disabled particular test case then use disabled annotation " @Disabled "
     */

    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "EE";
        Department found =
                departmentService.fetchDepartmentByName(departmentName);
          assertEquals(departmentName,found.getDepartmentName()) ;
    }
    public void whenValidDepartmentName_thenDepartmentShouldNotFound(){
        String departmentName = "IT";
        Department notFound =
                departmentService.fetchDepartmentByName(departmentName);
        assertEquals(null,notFound) ;
    }

}