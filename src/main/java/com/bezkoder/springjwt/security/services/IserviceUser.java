package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.User;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IserviceUser {

	
	public String delete(long id);
    public User findById(long id);
    public User findByUsername(String username);
    public User updateUser(long id,User user);
    public void updateImage(long id, String image);
    public List<User>  getAll();
   public String getFileName(long id);
   public String uploadImage(MultipartFile file);



}
