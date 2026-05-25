package com.vcube.crsapp.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vcube.crsapp.exception.BookingConflictException;
import com.vcube.crsapp.exception.ResourceNotFoundException;
import com.vcube.crsapp.model.Booking;
import com.vcube.crsapp.model.BookingStatus;
import com.vcube.crsapp.model.Car;
import com.vcube.crsapp.model.Customer;
import com.vcube.crsapp.model.DamageReport;
import com.vcube.crsapp.repo.BookingRepository;
import com.vcube.crsapp.repo.CarRepository;
import com.vcube.crsapp.repo.CustomerRepository;
import com.vcube.crsapp.repo.DamageReportRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final CarRepository carRepository;
	private final CustomerRepository customerRepository;
	private final DamageReportRepository damageReportRepository;

	public BookingServiceImpl(BookingRepository bookingRepository, CarRepository carRepository,
			CustomerRepository customerRepository, DamageReportRepository damageReportRepository) {

		this.bookingRepository = bookingRepository;
		this.carRepository = carRepository;
		this.customerRepository = customerRepository;
		this.damageReportRepository = damageReportRepository;
	}

	@Override
	public Booking createBooking(Long carId, Long customerId, LocalDate start, LocalDate end) throws Exception {

		Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
		validateBookingConflict(car, start, end);

//		 double totalPrice = calculatePrice(car, start, end);

		Booking booking = new Booking();
		booking.setCar(car);
		booking.setCustomer(customer);
		booking.setStartDate(start);
		booking.setEndDate(end);
//	        booking.setTotalPrice(totalPrice);
		booking.setStatus(BookingStatus.BOOKED);

		return bookingRepository.save(booking);

	}

	// 25th 27th
	private void validateBookingConflict(Car car, LocalDate start, LocalDate end) {

		List<Booking> bookings = bookingRepository.findByCarAndStatus(car, BookingStatus.BOOKED);

		for (Booking b : bookings) {
			if (!(end.isBefore(b.getStartDate()) || start.isAfter(b.getEndDate()))) {
				throw new BookingConflictException("Car already booked for selected dates");
			}
		}
	}

	private double calculatePrice(Car car, LocalDate start, LocalDate end) {

		double total = 0;
		for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
			if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
				total += car.getWeekendPrice();
			} else {
				total += car.getWeekdayPrice();
			}
		}
		return total;
	}

	@Override
	public String returnCar(Long bookingId, LocalDate actualReturnDate, String damageDescription, Double repairCost) {

		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

		double fine = calculateLateFine(booking, actualReturnDate);

		if (repairCost != null && repairCost > 0) {
			DamageReport report = new DamageReport();
			report.setBooking(booking);
			report.setDescription(damageDescription);
			report.setRepairCost(repairCost);
			damageReportRepository.save(report);

			fine += repairCost;
		}

		booking.setStatus(BookingStatus.RETURNED);
		bookingRepository.save(booking);

		return "Car returned. Extra charges: ₹" + fine;
	}

	private double calculateLateFine(Booking booking, LocalDate actualReturnDate) {
		if (actualReturnDate.isAfter(booking.getEndDate())) {
			long lateDays = ChronoUnit.DAYS.between(booking.getEndDate(), actualReturnDate);
			return lateDays * 1000;
		}
		return 0;
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

}
