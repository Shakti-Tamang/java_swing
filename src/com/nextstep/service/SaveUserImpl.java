package com.nextstep.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nextstep.connection.DbConnection;
import com.nextstep.model.UserModel;

public class SaveUserImpl implements SaveUser {
	
	PreparedStatement ps=null;

	@Override
	public void saveUser(UserModel model) {
		// TODO Auto-generated method stub
		
		try {
			
			String sql="insert into userInsert(name,address,gender,course,age,imagepath)values(?,?,?,?,?,?)";
			
			ps=DbConnection.getConnection().prepareStatement(sql);
			
			ps.setString(1, model.getName());
			ps.setString(2, model.getAdress());
			ps.setString(3, model.getGender());
			ps.setString(4,model.getCourse());
			ps.setInt(5, model.getAge());  
			ps.setString(6, model.getImagePath());
			ps.executeUpdate();
		}
		
		catch(Exception ex) {
			
			System.out.println(ex);
		}
	}

	@Override
	public List<UserModel> getAll() {
	
		
		List<UserModel>list=new ArrayList<UserModel>();
		
		try {
		
		String sql="select * from userInsert";
		
		ps=DbConnection.getConnection().prepareStatement(sql);
		
		ResultSet rs=ps.executeQuery();
		
		
		while(rs.next()) {
			
			UserModel model=new UserModel();
			
			model.setId(rs.getInt("id"));
			model.setName(rs.getString("name"));
			model.setAdress(rs.getString("address"));
			model.setGender(rs.getString("gender"));
			model.setCourse(rs.getString("course"));
			model.setAge(rs.getInt("age"));
			
			list.add(model);
			
		}
		}
		
		catch(Exception ex) {
			System.out.println(ex);
		}
		return list;
	}

	@Override
	public List<UserModel> serchByName(String name) {

		
		List<UserModel>list=new ArrayList<UserModel>();
		
		try {
		
		String sql="select * from userInsert where name like ?";
		
		ps=DbConnection.getConnection().prepareStatement(sql);
		ps.setString(1, "%"+name+"%");
		ResultSet rs=ps.executeQuery();
		
		
		while(rs.next()) {
			
			UserModel model=new UserModel();
			
			model.setId(rs.getInt("id"));
			model.setName(rs.getString("name"));
			model.setAdress(rs.getString("address"));
			model.setGender(rs.getString("gender"));
			model.setCourse(rs.getString("course"));
			model.setAge(rs.getInt("age"));
			
			list.add(model);
			
		}
		}
		
		catch(Exception ex) {
			System.out.println(ex);
		}
		return list;
	}

	@Override
	public void deleteById(int id) {

		try {
			
			String sql="delete from userInsert where id=?";
			
			ps=DbConnection.getConnection().prepareStatement(sql);

			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			
		}
		
		catch(Exception exception) {
			System.out.println(exception);
		}
		
	}

}
