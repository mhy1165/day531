package com.mhy.spring.springboot02.controller;


import com.mhy.spring.springboot02.dao.DepartmentDao;
import com.mhy.spring.springboot02.dao.EmployeeDao;
import com.mhy.spring.springboot02.entities.Department;
import com.mhy.spring.springboot02.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    //注入员工信息
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工信息
    @GetMapping("/emps")//等价于 @RequestMapping(value="/emps")method=RequestMethod.GET)
    public String list(Model model) {

        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);

        //thymeleaf 默认会拼装
        //默认路径：classpath:/templates/xxx.html
        return "emp/list";
    }

    /*
       来到添加页面
     */
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";

    }

    //SpringMvc自动将请求参数和入参对象的属性进行一一绑定：
    //要求请求参数的名字和JavaBean入参的对象里面的属性（name="xx")名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println("保存成功" + employee);
        employeeDao.save(employee);
        //添加后来到员工页面
        //redirect:表示重定向到一个地址  /代表当前项目路径
        // response：表示转发到一个地址
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
//    @PathVariable可以用来映射URL中的占位符到目标方法的参数中
    public String editPage(@PathVariable("id") Integer id, Model model) {
        //1.查出员工
        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        //2.将员工值存入Model中
        model.addAttribute("emp", employee);
        model.addAttribute("depts", departments);
//  3.返回到add页面
        return "emp/add";
    }


    @PutMapping("/emp")
    public String update(Employee employee) {

        employeeDao.save(employee);

        return "redirect:/emps";

    }

    //delete
    @PostMapping("/emp/{id}")
    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        System.out.println("删除成功");
        return "redirect:/emps";
    }
}
