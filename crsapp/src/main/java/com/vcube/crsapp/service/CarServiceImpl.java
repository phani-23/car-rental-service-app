package com.vcube.crsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.crsapp.exception.ResourceNotFoundException;
import com.vcube.crsapp.model.Car;
import com.vcube.crsapp.repo.CarRepository;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepository carRepository;

	@Override
	public Car addCar(Car car) {
		return carRepository.save(car);
	}

	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public Car getCarById(Long id) throws ResourceNotFoundException {
		return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
	}

	@Override
	public Car updateCar(Long id, Car car) throws ResourceNotFoundException {
		Car existing = getCarById(id);

		existing.setBrand(car.getBrand());
		existing.setModel(car.getModel());
		existing.setWeekdayPrice(car.getWeekdayPrice());
		existing.setWeekendPrice(car.getWeekendPrice());
		existing.setAvailable(car.getAvailable());

		return carRepository.save(existing);
	}

	@Override
	public void deleteCar(Long id) throws ResourceNotFoundException {
		Car car = getCarById(id);
		carRepository.delete(car);
	}

}
