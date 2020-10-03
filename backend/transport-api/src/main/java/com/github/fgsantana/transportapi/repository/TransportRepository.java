package com.github.fgsantana.transportapi.repository;

import com.github.fgsantana.transportapi.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransportRepository extends JpaRepository<Transport, Long > {



}

