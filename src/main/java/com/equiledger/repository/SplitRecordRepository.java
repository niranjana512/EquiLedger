package com.equiledger.repository;

import com.equiledger.model.SplitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SplitRecordRepository extends JpaRepository<SplitRecord, Long> {

}
