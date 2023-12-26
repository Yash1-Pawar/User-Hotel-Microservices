package com.rating.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.model.RatingDTO;
import com.rating.service.RatingService;


@RestController
@RequestMapping("rating/")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;

	@PostMapping("create")
	public ResponseEntity<Object> createRating(@RequestBody RatingDTO ratingDTO) {
		return ratingService.createRating(ratingDTO);
	}

	@GetMapping("{ratingID}")
	public ResponseEntity<Object> getRating(@PathVariable String ratingID) {
		return ratingService.getRating(ratingID);
	}
	
	@PutMapping("{ratingID}")
	public ResponseEntity<Object> updateRating(@PathVariable String ratingID, @RequestBody RatingDTO ratingDTO) {
		return ratingService.updateRating(ratingID, ratingDTO);
	}


	@GetMapping("fetch")
	public ResponseEntity<Object> getAllRating() {
		System.out.println("fetching....");
		return ratingService.getAllRating();
	}

	@DeleteMapping("remove/{ratingID}")
	public ResponseEntity<Object> removeRating(@PathVariable String ratingID) {
		return ratingService.removeRating(ratingID);
	}

}
