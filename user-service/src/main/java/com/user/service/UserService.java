package com.user.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.entity.UserEntity;
import com.user.model.RatingDTO;
import com.user.model.UserDTO;
import com.user.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<Object> createUser(UserDTO userDTO) {
		UserDTO userDTO1 = null;
		UserEntity userEntity = new UserEntity(userDTO.getUserName(), userDTO.getAbout());
		UserEntity reponse = userRepo.save(userEntity);
		List<RatingDTO> ratingDTOs = new ArrayList<>();
		userDTO1 = new UserDTO(reponse.getUserUUID(), reponse.getUserName(), reponse.getAbout(), ratingDTOs);
		return ResponseEntity.ok(userDTO1);
	}

	public ResponseEntity<Object> getUser(String userID) {
		UserDTO userDTO = null;
		UserEntity reponse = userRepo.findById(userID).orElse(null);
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
		}
		List<RatingDTO> ratingDTOs = getAllRatingByUserId(userID);
		userDTO = new UserDTO(reponse.getUserUUID(), reponse.getUserName(), reponse.getAbout(), ratingDTOs);
		return ResponseEntity.ok(userDTO);
	}

	public ResponseEntity<Object> updateUser(String userID, UserDTO userDTO) {
		UserDTO userDTO1 = null;
		UserEntity reponse = userRepo.findById(userID).orElse(null);
		List<RatingDTO> ratingDTOs = new ArrayList<>();
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
		} else {
			reponse.setUserName(userDTO.getUserName());
			reponse.setAbout(userDTO.getAbout());
			userRepo.save(reponse);
			userDTO1 = new UserDTO(reponse.getUserUUID(), reponse.getUserName(), reponse.getAbout(), ratingDTOs);
		}
		return ResponseEntity.ok(userDTO1);
	}

	public ResponseEntity<Object> getAllUser() {
		List<UserDTO> userDTOs = new ArrayList<>();
		List<UserEntity> reponse = userRepo.findAll();
		reponse.stream().forEach(entity -> userDTOs.add(new UserDTO(entity.getUserUUID(), entity.getUserName(),
				entity.getAbout(), getAllRatingByUserId(entity.getUserUUID()))));
		System.out.println(userDTOs.size());
		return ResponseEntity.ok(userDTOs);
	}

	public ResponseEntity<Object> removeUser(String userID) {
		UserEntity reponse = userRepo.findById(userID).orElse(null);
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
		} else {
			userRepo.delete(reponse);
			return new ResponseEntity<>("User Deleted", HttpStatus.OK);
		}
	}

	public List<RatingDTO> getAllRatingByUserId(String userId) {
		RatingDTO ratings[] = restTemplate.getForObject("http://RATING/rating/fetch", RatingDTO[].class);
		List<RatingDTO> ratingDTOs = Arrays.asList(ratings).stream().filter(e -> e.getUserId().equals(userId))
				.collect(Collectors.toList());
		return ratingDTOs;
	}

}
