package com.empresa.bikeservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.bikeservice.entity.Bike;
import com.empresa.bikeservice.repository.BikeRepository;


@Service
public class BikeService {

	@Autowired
	BikeRepository carRepository;
	
	public List<Bike> getAll(){
		return carRepository.findAll();
	}
	
	public Bike getBikeById(int id) {
		return carRepository.findById(id).orElse(null);
	}
	
	public Bike save(Bike bike) {
		return carRepository.save(bike);
	}
	
	public List<Bike> findByUserId(int userId){
		return carRepository.findByUserId(userId);
	}
}
