package coworking.space.repository;

import coworking.space.entities.Reservation;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ReservationRepository {
    private Set<Reservation> reservations = new HashSet<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Set<Reservation> getReservationsByCustomer(String customerLogin) {
        return reservations.stream()
                .filter(r -> r.getCustomerLogin().equals(customerLogin))
                .collect(Collectors.toSet());
    }

    public void deleteReservationById(long id, String customerLogin) {
        Reservation reservation = reservations.stream()
                .filter(r -> r.getId() == id && r.getCustomerLogin().equals(customerLogin))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
        reservations.remove(reservation);
    }

    public Set<Reservation> getAllReservations() {
        return reservations;
    }
}
