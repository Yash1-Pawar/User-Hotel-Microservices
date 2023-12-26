package com.rating.entity;

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
@Table(name = "Rating")
public class RatingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String ratingUUID;
	private String userId;
	private String hotelId;
	private String rating;

	public RatingEntity(String userId, String hotelId, String rating) {
		super();
		this.userId = userId;
		this.hotelId = hotelId;
		this.rating = rating;
	}

}
