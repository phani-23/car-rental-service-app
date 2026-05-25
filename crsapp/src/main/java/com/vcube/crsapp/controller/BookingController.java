package com.vcube.crsapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.crsapp.model.Booking;
import com.vcube.crsapp.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	private final BookingService service;

	public BookingController(BookingService service) {
		this.service = service;
	}

	@PostMapping("/add")
	public ResponseEntity<Booking> createBooking(@RequestParam Long carId, @RequestParam Long customerId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws Exception {

		return ResponseEntity.ok(service.createBooking(carId, customerId, startDate, endDate));
	}

	@PutMapping("/{id}/return")
	public ResponseEntity<String> returnCar(@PathVariable Long id,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate actualReturnDate,
			@RequestParam(required = false) String damageDescription,
			@RequestParam(required = false) Double repairCost) {

		return ResponseEntity.ok(service.returnCar(id, actualReturnDate, damageDescription, repairCost));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Booking>> getAllBookings() {
		return ResponseEntity.ok(service.getAllBookings());
	}
}
