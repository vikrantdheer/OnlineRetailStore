package com.vikrant.mediaocean.repository;

import com.vikrant.mediaocean.entity.Bills;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bills, Integer> {
}
