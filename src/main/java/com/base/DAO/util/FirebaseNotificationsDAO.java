package com.base.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.util.FirebaseNotifications;

public interface FirebaseNotificationsDAO extends JpaRepository<FirebaseNotifications, Long> {

	FirebaseNotifications findByIdFirebaseNotifications(Long idFirebaseNotifications);
}
