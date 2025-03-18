package com.tu.etl.poc2.repository;

import com.tu.etl.poc2.model.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<DataModel, Long> {
}