package com.bazaronline.bazaronline.repository;

import com.bazaronline.bazaronline.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {

}
