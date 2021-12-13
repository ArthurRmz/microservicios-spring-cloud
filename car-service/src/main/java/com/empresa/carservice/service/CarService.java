package com.empresa.carservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.carservice.entity.Car;
import com.empresa.carservice.repository.CarRepository;


@Service
public class CarService {

	@Autowired
	CarRepository carRepository;
	
	public List<Car> getAll(){
		return carRepository.findAll();
	}
	
	public Car getCarById(int id) {
		return carRepository.findById(id).orElse(null);
	}
	
	public Car save(Car car) {
		return carRepository.save(car);
	}
	
	public List<Car> findByUserId(int userId){
		return carRepository.findByUserId(userId);
	}
}
