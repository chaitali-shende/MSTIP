package com.hotel.config;

import com.hotel.entity.*;
import com.hotel.repository.CustomerRepository;
import com.hotel.repository.ReservationRepository;
import com.hotel.repository.RoomRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {

        // ======== Add Rooms ========
        List<Room> rooms = List.of(
                Room.builder().roomType("Standard").price(3000.0).available(true).build(),
                Room.builder().roomType("Deluxe").price(5000.0).available(true).build(),
                Room.builder().roomType("Suite").price(8000.0).available(true).build(),
                Room.builder().roomType("Family").price(10000.0).available(true).build(),
                Room.builder().roomType("Presidential").price(20000.0).available(true).build()
        );
        roomRepository.saveAll(rooms);

        // ======== Add Customers ========
        List<Customer> customers = List.of(
                Customer.builder().name("Alice Johnson").email("alice.johnson@example.com").mobile("9876543210").build(),
                Customer.builder().name("Bob Smith").email("bob.smith@example.com").mobile("9123456780").build(),
                Customer.builder().name("Charlie Brown").email("charlie.brown@example.com").mobile("9988776655").build(),
                Customer.builder().name("Diana Prince").email("diana.prince@example.com").mobile("9112233445").build(),
                Customer.builder().name("Ethan Hunt").email("ethan.hunt@example.com").mobile("9001122334").build()
        );
        customerRepository.saveAll(customers);

        // ======== Create Random Reservations ========
        for (Customer customer : customers) {
            // Each customer can have 1-3 reservations
            int reservationsCount = random.nextInt(3) + 1;

            for (int i = 0; i < reservationsCount; i++) {

                // Pick a random available room
                Room room = rooms.get(random.nextInt(rooms.size()));

                if (!room.isAvailable()) continue; // skip if room already booked

                // Random check-in between today and next 30 days
                LocalDate checkIn = LocalDate.now().plusDays(random.nextInt(30));
                // Random stay between 1 and 5 nights
                int nights = random.nextInt(5) + 1;
                LocalDate checkOut = checkIn.plusDays(nights);

                // Random reservation status
                ReservationStatus status = ReservationStatus.values()[random.nextInt(ReservationStatus.values().length)];

                // Create reservation
                Reservation reservation = Reservation.builder()
                        .customer(customer)
                        .room(room)
                        .checkInDate(checkIn)
                        .checkOutDate(checkOut)
                        .totalAmount(room.getPrice() * nights)
                        .status(status)
                        .build();

                // If confirmed or checked-in, mark room unavailable
                if (status == ReservationStatus.CONFIRMED || status == ReservationStatus.CHECKED_IN) {
                    room.setAvailable(false);
                }

                reservationRepository.save(reservation);
            }
        }

        // ======== Update room availability in DB ========
        roomRepository.saveAll(rooms);

        System.out.println("✅ Sample data loaded successfully!");
    }
}
