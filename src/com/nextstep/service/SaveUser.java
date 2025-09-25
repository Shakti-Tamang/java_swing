package com.nextstep.service;

import java.util.List;

import com.nextstep.model.UserModel;

public interface SaveUser {
	
	public void saveUser(UserModel model);
    public List<UserModel> getAll();
    public List<UserModel>serchByName(String name);
    public void deleteById(int id);

}
