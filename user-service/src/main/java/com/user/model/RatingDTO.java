package com.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingDTO {

	private String ratingID;
	private String userId;
	private String hotelId;
	private HotelDTO hotel;
	private String rating;

}
