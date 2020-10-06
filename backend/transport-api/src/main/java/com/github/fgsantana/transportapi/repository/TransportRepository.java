package com.github.fgsantana.transportapi.repository;

import com.github.fgsantana.transportapi.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    @Query(value = "SELECT lo_get(logo) FROM transport WHERE transport.id=?1", nativeQuery=true)
    public byte[] getLogoByid(Long id);

}

