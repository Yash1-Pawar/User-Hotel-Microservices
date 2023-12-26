package com.rating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

	private String ratingID;
	private String userId;
	private String hotelId;
	private HotelDTO hotel;
	private String rating;

}
