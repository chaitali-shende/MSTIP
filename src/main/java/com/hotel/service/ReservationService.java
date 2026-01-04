package com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import com.hotel.entity.Customer;
import com.hotel.entity.Reservation;

public interface ReservationService {
	void bookRoom(Long roomId, Customer customer,
            LocalDate checkIn, LocalDate checkOut);
	
	List<Reservation> getAllReservations(); 
	
	Reservation getReservationById(Long id);

    void cancelReservation(Long reservationId);
}
