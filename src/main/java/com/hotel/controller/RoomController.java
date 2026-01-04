package com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotel.service.RoomService;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

	  private final RoomService roomService;

    @GetMapping
    public String showRooms(Model model) {
         model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms";
    }
}