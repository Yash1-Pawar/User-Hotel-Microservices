package com.user.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String userId;
	private String userName;
	private String about;
	private List<RatingDTO> userRatings;
	
}
