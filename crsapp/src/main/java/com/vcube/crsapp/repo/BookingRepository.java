package com.vcube.crsapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.crsapp.model.Booking;
import com.vcube.crsapp.model.BookingStatus;
import com.vcube.crsapp.model.Car;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByCarAndStatus(Car car, BookingStatus status);
}
