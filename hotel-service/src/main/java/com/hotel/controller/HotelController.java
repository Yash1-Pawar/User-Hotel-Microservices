package com.hotel.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entity.HotelEntity;
import com.hotel.model.HotelDTO;
import com.hotel.repository.HotelRepo;

@RestController
@RequestMapping("hotel/")
public class HotelController {

	@Autowired
	private HotelRepo hotelRepo;

	@PostMapping("create")
	public ResponseEntity<HotelEntity> createHotel(@RequestBody HotelDTO hotelDTO) {
		HotelEntity hotelEntity = new HotelEntity(hotelDTO.getHotelName(), hotelDTO.getAbout());
		HotelEntity reponse = hotelRepo.save(hotelEntity);
		return ResponseEntity.ok(reponse);
	}

	@GetMapping("{hotelID}")
	public ResponseEntity<Object> getHotel(@PathVariable String hotelID) {
		HotelEntity reponse = hotelRepo.findById(hotelID).orElse(null);
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("Hotel Not Found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(reponse);
	}
	
	@PutMapping("{hotelID}")
	public ResponseEntity<Object> updateHotel(@PathVariable String hotelID, @RequestBody HotelDTO hotelDTO) {
		HotelEntity reponse = hotelRepo.findById(hotelID).orElse(null);
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("Hotel Not Found", HttpStatus.NOT_FOUND);
		} else {
			reponse.setHotelName(hotelDTO.getHotelName());
			reponse.setAbout(hotelDTO.getAbout());
			hotelRepo.save(reponse);
		}
		return ResponseEntity.ok(reponse);
	}

	@GetMapping("fetch")
	public ResponseEntity<Object> getAllHotel() {
		List<HotelEntity> reponse = hotelRepo.findAll();
		return ResponseEntity.ok(reponse);
	}

	@DeleteMapping("remove/{hotelID}")
	public ResponseEntity<Object> removeHotel(@PathVariable String hotelID) {
		HotelEntity reponse = hotelRepo.findById(hotelID).orElse(null);
		if (Objects.isNull(reponse)) {
			return new ResponseEntity<>("Hotel Not Found", HttpStatus.NOT_FOUND);
		} else {
			hotelRepo.delete(reponse);
			return new ResponseEntity<>("Hotel Deleted", HttpStatus.OK);
		}
	}

}
