package com.vikrant.mediaocean.repository;

import com.vikrant.mediaocean.entity.Bill;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Id;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
