package com.prajwal.bookingservice.repositories;

import com.prajwal.entityservice.models.Booking;
import com.prajwal.entityservice.models.BookingStatus;
import com.prajwal.entityservice.models.Driver;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Transactional
    @Query("update Booking b set b.bookingStatus = :status , b.driver=:driver  where b.id = :id")
    @Modifying
    public void updateBookingStatusAndDriverById(@Param("id") Long id, @Param("status") BookingStatus status , @Param("driver") Driver driver);
}
