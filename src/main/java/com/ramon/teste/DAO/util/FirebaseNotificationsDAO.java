package com.ramon.teste.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.FirebaseNotifications;

public interface FirebaseNotificationsDAO extends JpaRepository<FirebaseNotifications, Long> {

	FirebaseNotifications findByIdFirebaseNotifications(Integer idFirebaseNotifications);
}
