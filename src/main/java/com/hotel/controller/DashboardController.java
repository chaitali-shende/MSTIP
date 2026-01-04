package com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotel.service.CustomerService;
import com.hotel.service.ReservationService;
import com.hotel.service.RoomService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final RoomService roomService;
    private final ReservationService reservationService;
    private final CustomerService customerService;

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute("totalCustomers",
                customerService.getAllCustomers().size());

        model.addAttribute("totalRooms",
                roomService.getAllRooms().size());

        model.addAttribute("totalReservations",
                reservationService.getAllReservations().size());

        return "dashboard";
    }
}
