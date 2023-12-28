package com.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.user.model.RatingDTO;

@FeignClient(name = "RATING", path = "rating/")
public interface RatingFeign {
	
	@GetMapping("fetch")
	public ResponseEntity<RatingDTO[]> getAllRating();

}
