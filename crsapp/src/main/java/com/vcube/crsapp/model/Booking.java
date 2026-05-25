package com.vcube.crsapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "booking65")
@Data
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Car car;

	@ManyToOne
	private Customer customer;

	private LocalDate startDate;
	private LocalDate endDate;

	private Double totalPrice;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;
}
