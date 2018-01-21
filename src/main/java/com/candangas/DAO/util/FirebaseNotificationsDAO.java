package com.candangas.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.util.FirebaseNotifications;

public interface FirebaseNotificationsDAO extends JpaRepository<FirebaseNotifications, Long> {

	FirebaseNotifications findByIdFirebaseNotifications(Integer idFirebaseNotifications);
}
