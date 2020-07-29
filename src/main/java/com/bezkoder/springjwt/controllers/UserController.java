package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.security.jwt.FileStorageService;
import com.bezkoder.springjwt.security.services.IserviceUser;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	

	@Autowired
    private IserviceUser userService;
	
	@Autowired
	private FileStorageService fs;

	
	 @GetMapping(value="/getall")
		@PreAuthorize(" hasRole('USER') or hasRole('ADMIN')")
	    public List<User> listUser(){
	        return userService.getAll();
	    }


	    
	    

	    @PostMapping("/update/{id}")
	    public @ResponseBody User update(@PathVariable (value ="id") int id,@RequestBody  User user) {
	       return userService.updateUser(id, user);
	    
	    }




		@DeleteMapping("/deleteUser/{id}")
		public String delete(@PathVariable (value="id")long id) {
			return userService.delete(id);
		}




		@GetMapping("/getOne/{id}")
		public User findById(@PathVariable(value="id")long id) {
			return userService.findById(id);
		}
	    
	    
		  @PostMapping("/update-photo/{id}")
		    public void updatePhoto(@PathVariable int id, @RequestBody String image){
		        userService.updateImage(id, image);
		    }

		    @GetMapping(value = "/photo/{id}")
		    public Resource getPhoto(@PathVariable int id) throws IOException {
		    	String fileName = userService.getFileName(id);
		    	return fs.loadFileAsResource(fileName);

		    }
		    
		    @PostMapping(value="/upload-image", consumes =  {"multipart/form-data"})
		    public String uploadImage(@RequestParam("file")  MultipartFile file) {
		    	return userService.uploadImage(file);
		    }


}
