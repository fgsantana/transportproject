package com.github.fgsantana.transportapi.repository;

import com.github.fgsantana.transportapi.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    @Query(value = "SELECT lo_get(logo) FROM transport WHERE transport.id=?1", nativeQuery = true)
    byte[] getLogoByid(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE transport SET logo=null WHERE transport.id=?1", nativeQuery = true)
    void deleteLogoById(Long id);


}

