package com.empresa.userservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.empresa.userservice.model.Car;


@FeignClient(name = "car-service", url = "http://localhost:8002/car")
public interface CarFeignClient {
	
	@PostMapping
	public Car save(@RequestBody Car car);
	
	@GetMapping("byUserId/{userId}")
	List<Car> getCars(@PathVariable("userId") int userId);

}
