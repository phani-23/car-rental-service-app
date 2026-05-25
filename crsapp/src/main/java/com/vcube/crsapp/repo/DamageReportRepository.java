package com.vcube.crsapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.crsapp.model.DamageReport;

@Repository
public interface DamageReportRepository extends JpaRepository<DamageReport, Long> {
	
}
