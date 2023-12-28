package com.rating.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rating.model.HotelDTO;

@FeignClient(name = "HOTEL", path = "hotel/")
public interface HotelFeign {
	
	@GetMapping("{hotelID}")
	public ResponseEntity<HotelDTO> getHotel(@PathVariable String hotelID);

}
