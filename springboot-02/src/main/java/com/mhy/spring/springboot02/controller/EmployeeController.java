package com.mhy.spring.springboot02.controller;


import com.mhy.spring.springboot02.dao.DepartmentDao;
import com.mhy.spring.springboot02.dao.EmployeeDao;
import com.mhy.spring.springboot02.entities.Department;
import com.mhy.spring.springboot02.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    //注入员工信息
    @Autowired
    EmployeeDao employeeDao;
    DepartmentDao departmentDao;
    //查询所有员工信息
    @GetMapping("/emps")//等价于 @RequestMapping(value="/emps")method=RequestMethod.GET)
    public String list(Model model){

        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps" ,employees);

        //thymeleaf 默认会拼装
        //默认路径：classpath:/templates/xxx.html
        return "emp/list";
    }

    /*
       来到添加页面
     */
    @GetMapping("/emp")
    public  String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";

    }

}
