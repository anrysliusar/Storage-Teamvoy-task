package com.sliusar.storageteamvoytask.repository;

import com.sliusar.storageteamvoytask.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    void deleteAllByCreatedAtBefore(Date date);
}
