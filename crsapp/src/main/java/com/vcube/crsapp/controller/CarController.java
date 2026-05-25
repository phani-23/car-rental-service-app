package com.vcube.crsapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.crsapp.model.Car;
import com.vcube.crsapp.service.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {

	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping("/add")
	public ResponseEntity<Car> addCar(@RequestBody Car car) {
		return ResponseEntity.ok(carService.addCar(car));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Car>> getAllCars() {
		return ResponseEntity.ok(carService.getAllCars());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> getCar(@PathVariable Long id) {
		return ResponseEntity.ok(carService.getCarById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
		return ResponseEntity.ok(carService.updateCar(id, car));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCar(@PathVariable Long id) {
		carService.deleteCar(id);
		return ResponseEntity.ok("Car deleted successfully");
	}
}
