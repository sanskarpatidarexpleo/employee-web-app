package com.expleo.employee.emp;

import java.util.List;

public interface EmpDao {

	int save(EmpModel e);
	EmpModel getEmp(int id);
	List<EmpModel> getAllEmp();
}
