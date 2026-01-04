package com.hotel.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hotel.entity.*;
import com.hotel.repository.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationRepository reservationRepo;
    private final RoomRepository roomRepo;
    private final CustomerRepository customerRepo;

    // Show all reservations
    @GetMapping
    public String reservations(Model model) {
        List<Reservation> reservations = reservationRepo.findAll();
        model.addAttribute("reservations", reservations != null ? reservations : new ArrayList<>());
        return "reservations";  // reservations.html
    }

    // Show booking page
    @GetMapping("/book-room")
    public String showBookingPage(Model model) {
        model.addAttribute("rooms", roomRepo.findByAvailableTrue());
        return "book-room";  // book-room.html
    }

    // Book room
    @PostMapping("/book")
    public String bookRoom(
            @RequestParam Long roomId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String mobile,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkInDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkOutDate,
            Model model
    ) {
        Room room = roomRepo.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        if (!room.isAvailable()) {
            model.addAttribute("errorMessage", "Room is already booked");
            return "error";
        }

        // Save or get existing customer
        Customer customer = customerRepo.findByEmail(email)
                .orElseGet(() -> customerRepo.save(
                        Customer.builder()
                                .name(name)
                                .email(email)
                                .mobile(mobile)
                                .build()
                ));

        // Book reservation
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setStatus(ReservationStatus.CONFIRMED);
        reservation.setTotalAmount(room.getPrice() * (checkOutDate.toEpochDay() - checkInDate.toEpochDay()));

        // Update room availability
        room.setAvailable(false);
        roomRepo.save(room);

        reservationRepo.save(reservation);

        model.addAttribute("reservation", reservation);
        return "booking-success";  // booking-success.html
    }

    // Cancel reservation
    @PostMapping("/cancel")
    public String cancelReservation(@RequestParam Long reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.getRoom().setAvailable(true);

        roomRepo.save(reservation.getRoom());
        reservationRepo.save(reservation);

        return "redirect:/reservations";
    }
}
