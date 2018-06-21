package com.vikrant.mediaocean.repository;

import com.vikrant.mediaocean.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public long count();
}
