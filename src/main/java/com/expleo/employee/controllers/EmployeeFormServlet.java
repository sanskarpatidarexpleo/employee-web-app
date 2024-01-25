package com.expleo.employee.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.expleo.employee.emp.EmpDaoImpl;
import com.expleo.employee.emp.EmpModel;


@WebServlet("/employeeForm")
public class EmployeeFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Insert data into DB.
		
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		float salary = Float.parseFloat(request.getParameter("salary"));
		
		EmpModel model = new EmpModel(id, name , salary);
		System.out.println("Employee : "+model);
		
		PrintWriter writer = response.getWriter();
		if(insertData(model) > 0) {
			writer.println("<h1> Data Inserted </h1>");
		} else {
			writer.println("<h1> Something went wrong </h1>");
		}
	}

	private int insertData(EmpModel model) {
		EmpDaoImpl daoImpl = new EmpDaoImpl();
		return daoImpl.save(model);
	
	}

}
