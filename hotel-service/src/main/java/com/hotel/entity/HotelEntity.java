package com.hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Hotel")
public class HotelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String hotelUUID;
	private String about;
	private String hotelName;

	public HotelEntity(String hotelName, String about) {
		this.about = about;
		this.hotelName = hotelName;
	}

}
