package com.project.backend.user.model;

import java.time.LocalDateTime;

import com.project.backend.user.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "fullName", nullable = false)
	private String fullName;
	
	 @Column(nullable = false, unique = true)
	    private String email;
	 
	 @Column(nullable = false)
	    private String password;
	 
	 @Column(name = "mobile_number", nullable = false, length = 15)
	    private String mobileNumber;
	 
	 private String city;
	 
	 @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private UserRole role;
	 @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private UserStatus status;
	 
	 @ManyToOne
	 @JoinColumn(name = "created_by")
	 private User createdBy;
	 
	 @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;
	 
	 @Column(name = "updated_at")
	    private LocalDateTime updatedAt;
	 
	 @Column(name = "deleted_at")
	    private LocalDateTime deletedAt;
	 
	 @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	    }
	 
	 @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }
	 

	

}
