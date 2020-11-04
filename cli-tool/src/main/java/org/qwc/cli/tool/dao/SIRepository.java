package org.qwc.cli.tool.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SIRepository extends JpaRepository<SIEntity, Long> {

Optional<SIEntity> findByMsisdn(String msisdn);

}
