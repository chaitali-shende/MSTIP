package com.hotel.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.entity.*;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.exception.RoomNotAvailableException;
import com.hotel.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
    }

    @Override
    public void bookRoom(Long roomId, Customer customer,
                         LocalDate checkIn, LocalDate checkOut) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (!room.isAvailable()) {
            throw new RoomNotAvailableException("Room is already booked");
        }

        Customer savedCustomer = customerRepository
                .findByEmail(customer.getEmail())
                .orElseGet(() -> customerRepository.save(customer));

        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (days <= 0) days = 1;

        double totalAmount = days * room.getPrice();

        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setCustomer(savedCustomer);
        reservation.setCheckInDate(checkIn);
        reservation.setCheckOutDate(checkOut);
        reservation.setTotalAmount(totalAmount);
        reservation.setStatus(ReservationStatus.CONFIRMED);

        room.setAvailable(false);

        reservationRepository.save(reservation);
        roomRepository.save(room);
    }

    @Override
    public void cancelReservation(Long reservationId) {

        Reservation reservation = getReservationById(reservationId);

        reservation.setStatus(ReservationStatus.CANCELLED);

        Room room = reservation.getRoom();
        room.setAvailable(true);

        reservationRepository.save(reservation);
        roomRepository.save(room);
    }
}
