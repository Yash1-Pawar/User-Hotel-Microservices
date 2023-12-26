package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entity.HotelEntity;

public interface HotelRepo extends JpaRepository<HotelEntity, String> {

}
