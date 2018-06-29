package com.vikrant.mediaocean.repository;

import com.vikrant.mediaocean.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Integer> {
}
