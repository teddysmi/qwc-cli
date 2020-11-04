package org.qwc.cli.tool.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FebRepository extends JpaRepository<FebEntity, Long> {

    Optional<FebEntity> findByMsisdn(String msisdn);
}
