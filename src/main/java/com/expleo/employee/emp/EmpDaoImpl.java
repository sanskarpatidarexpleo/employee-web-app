package com.expleo.employee.emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.expleo.employee.db.MySqlCon;


public class EmpDaoImpl implements EmpDao{

	private MySqlCon mySqlCon;
	
	public EmpDaoImpl() {
		mySqlCon = MySqlCon.getInstance();
	}
	

	@Override
	public int save(EmpModel e) {
		Connection con = mySqlCon.getConnection();
		if(con != null) {
			PreparedStatement st;
			try {
				st = con.prepareStatement("INSERT INTO employee_form(id,name , salary) VALUES (?,?,?)");
				st.setInt(1, e.getId());
				st.setString(2, e.getName());
				st.setFloat(3, e.getSalary());
				
				return st.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			finally {
				mySqlCon.closeConnection();
			}
		} else
			System.err.println("Connection is NULL");
		return -1;
	}

	
	
	@Override
	public EmpModel getEmp(int id) {
		Connection con = mySqlCon.getConnection();
		if(con != null) {
			PreparedStatement st;
			try {
				st = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE id =  ? ");
				st.setInt(1, id);
				
				ResultSet rs = st.executeQuery();
				rs.next();
				
				EmpModel empModel = new EmpModel( rs.getInt(1) , rs.getString(2), rs.getFloat(3));
				empModel.setId(rs.getInt(1));
				return empModel;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				mySqlCon.closeConnection();
			}
			
		} else
			System.err.println("Connection is NULL");
		return null;
	}

	
	@Override
	public List<EmpModel> getAllEmp() {
		Connection con = mySqlCon.getConnection();
		if(con != null) {
			Statement st;
			try {
				st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM EMPLOYEE");
				
				List<EmpModel> modelList = new ArrayList<>();
				
				while(rs.next()) {
					EmpModel model = new EmpModel(rs.getInt(1) ,rs.getString(2), rs.getFloat(3));
					model.setId(rs.getInt(1));
					modelList.add(model);
				}
				
				return modelList;
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				mySqlCon.closeConnection();
			}
			
		} else
			System.err.println("Connection is NULL");
		return null;
	}

}
