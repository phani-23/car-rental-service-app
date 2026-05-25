package com.vcube.crsapp.service;

import java.time.LocalDate;
import java.util.List;

import com.vcube.crsapp.model.Booking;

public interface BookingService {
	Booking createBooking(Long carId, Long customerId, LocalDate start, LocalDate end) throws Exception;

	String returnCar(Long bookingId, LocalDate actualReturnDate, String damageDescription, Double repairCost);

	List<Booking> getAllBookings();
}
