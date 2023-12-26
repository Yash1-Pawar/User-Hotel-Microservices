package com.rating.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.entity.RatingEntity;

public interface RatingRepository extends JpaRepository<RatingEntity, String> {

}
