package com.rating.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rating.entity.RatingEntity;
import com.rating.model.HotelDTO;
import com.rating.model.RatingDTO;
import com.rating.repo.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelFeign hotelFeign;

	@Autowired
	private RatingRepository ratingRepository;

	public ResponseEntity<Object> createRating(RatingDTO ratingDTO) {
		RatingDTO ratingDTO1 = null;
		RatingEntity ratingEntity = new RatingEntity(ratingDTO.getUserId(), ratingDTO.getHotelId(),
				ratingDTO.getRating());
		RatingEntity reponse = ratingRepository.save(ratingEntity);
		ratingDTO1 = new RatingDTO(reponse.getRatingUUID(), reponse.getUserId(), reponse.getHotelId(),
				getHotelById(reponse.getHotelId()), reponse.getRating());
		return ResponseEntity.ok(ratingDTO1);
	}

	public ResponseEntity<Object> getRating(String ratingID) {
		RatingDTO ratingDTO = null;
		RatingEntity reponse = ratingRepository.findById(ratingID).orElse(null);
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("Rating Not Found", HttpStatus.NOT_FOUND);
		}
		ratingDTO = new RatingDTO(reponse.getRatingUUID(), reponse.getUserId(), reponse.getHotelId(),
				getHotelById(reponse.getHotelId()), reponse.getRating());
		return ResponseEntity.ok(ratingDTO);
	}

	public ResponseEntity<Object> updateRating(String ratingID, RatingDTO ratingDTO) {
		RatingEntity reponse = ratingRepository.findById(ratingID).orElse(null);
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("Rating Not Found", HttpStatus.NOT_FOUND);
		} else {
			reponse.setRating(ratingDTO.getRating());
			ratingRepository.save(reponse);
		}
		return ResponseEntity.ok(reponse);
	}

	public ResponseEntity<Object> getAllRating() {
		List<RatingDTO> ratingDTO = new ArrayList<>();
		List<RatingEntity> reponse = ratingRepository.findAll();
		reponse.stream().forEach(rating -> ratingDTO.add(new RatingDTO(rating.getRatingUUID(), rating.getUserId(),
				rating.getHotelId(), getHotelById(rating.getHotelId()), rating.getRating())));
		return ResponseEntity.ok(ratingDTO);
	}

	public ResponseEntity<Object> removeRating(String ratingID) {
		RatingEntity reponse = ratingRepository.findById(ratingID).orElse(null);
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("Rating Not Found", HttpStatus.NOT_FOUND);
		} else {
			ratingRepository.delete(reponse);
			return new ResponseEntity<>("Rating Deleted", HttpStatus.OK);
		}
	}

	public HotelDTO getHotelById(String hotelId) {
		HotelDTO hotelDTO = new HotelDTO();
//		hotelDTO = restTemplate.getForObject("http://HOTEL/hotel/" + hotelId, HotelDTO.class);
		hotelDTO = hotelFeign.getHotel(hotelId).getBody();
		return hotelDTO;
	}

}
