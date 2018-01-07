package com.ramon.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.model.util.FirebaseNotifications;

public interface FirebaseNotificationsDAO extends JpaRepository<FirebaseNotifications, Long> {

	FirebaseNotifications findByIdFirebaseNotifications(Integer idFirebaseNotifications);
}
