package com.user.entity;

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
@Table(name = "Users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String userUUID;
	private String about;
	private String userName;

	public UserEntity(String userName, String about) {
		this.about = about;
		this.userName = userName;
	}

}
