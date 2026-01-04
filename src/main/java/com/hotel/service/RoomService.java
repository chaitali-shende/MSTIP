package com.hotel.service;

import java.util.List;

import com.hotel.entity.Room;

public interface RoomService {
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
    Room getRoomById(Long id);
}