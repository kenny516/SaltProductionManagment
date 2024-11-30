package com.analytique.gestion_analytique.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.analytique.gestion_analytique.Models.Notification;
import com.analytique.gestion_analytique.Repositories.NotificationRepository;

@Service
public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getRead(Integer candidatId) {
        return notificationRepository.findAll().stream()
                .filter(notification -> notification.getCandidat().getId().equals(candidatId) && notification.getStatut().equalsIgnoreCase("lu"))
                .collect(Collectors.toList());
    }

    public List<Notification> getNonRead(Integer candidatId) {
        return notificationRepository.findAll().stream()
                .filter(notification -> notification.getCandidat().getId().equals(candidatId) && notification.getStatut().equalsIgnoreCase("non_lu"))
                .collect(Collectors.toList());
    }

    public void markAsRead(Integer notifId) {
        Notification n = notificationRepository.getReferenceById(notifId);

        n.setStatut("lu");
        n = notificationRepository.save(n);
    }
}
