package com.analytique.gestion_analytique.Repositories;

import com.analytique.gestion_analytique.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    
}