package com.empresa.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.empresa.userservice.entity.User;
import com.empresa.userservice.feignclients.BikeFeignClient;
import com.empresa.userservice.feignclients.CarFeignClient;
import com.empresa.userservice.model.Bike;
import com.empresa.userservice.model.Car;
import com.empresa.userservice.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CarFeignClient carFeignClient;

	@Autowired
	BikeFeignClient bikeFeignClient;
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<Car> getCars(int userId){
		List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byUserId/"+userId, List.class);
		return cars;
	}
	
	public List<Bike> getBikes(int userId){
		List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byUserId/"+userId, List.class);
		return bikes;
	}
	
	public Car saveCar(int userId, Car car) {
		car.setUserId(userId);
		Car newCar = carFeignClient.save(car);
		return newCar;
	}

	public Bike saveBike(int userId, Bike bike) {
		bike.setUserId(userId);
		Bike newBike = bikeFeignClient.save(bike);
		return newBike;
	}
	
	public Map<String, Object> getUserAndVehicles(int userId){
		Map<String, Object> result = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			result.put("mensaje", "No existe el usuario");
			return result;
		}
		result.put("user", user);
		List<Car> cars = carFeignClient.getCars(userId);
		result.put("cars", cars);
		if(cars.isEmpty()) {
			result.put("cars", "Este usuario no tiene coches");
		}
		
		List<Bike> bikes = bikeFeignClient.getBikes(userId);
		result.put("bikes", bikes);
		if(bikes.isEmpty()) {
			result.put("bikes", "Este usuario no tiene motos");
		}
		
		return result;
	}
}
