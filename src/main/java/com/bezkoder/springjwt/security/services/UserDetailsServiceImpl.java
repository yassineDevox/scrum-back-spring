package com.bezkoder.springjwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.FileStorageService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, IserviceUser {
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	private FileStorageService fileStorageService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	@Override
	public String delete(long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return "user is deleted by id "+ id;
		}
		
		throw new RuntimeException("User not found");		
	}

	@Override
	public User findById(long id) {
		return userRepository.findById(id).get();

	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(long id, User user) {
		User u=userRepository.findById(id).get();
		if(u != null){
			u.setUsername(user.getUsername());
			u.setEmail(user.getEmail());
			u.setPhoto(user.getPhoto());
			

		}
		return userRepository.save(u);
		
	}

	

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	

	@Override
	public String uploadImage(MultipartFile file) {
		return fileStorageService.storeFile(file);
	}
	
	@Override
    public String getFileName(long id) {
           User user =  userRepository.findById(id).get();
           return user.getPhoto();
    }
	
	@Override
	public void updateImage(long id, String image){
		User user=findById(id);
		user.setPhoto(image);
		userRepository.save(user);
	}

	
	
}
