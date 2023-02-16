package com.example.demo.service.auditor;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public abstract class Auditor<U> {
	
	@CreatedBy
	private String reguser;
	
	@CreatedDate
	private LocalDateTime regdate;
	
	@LastModifiedBy
	private String moduser;
	
	@LastModifiedDate
	private LocalDateTime moddate;
	
}
