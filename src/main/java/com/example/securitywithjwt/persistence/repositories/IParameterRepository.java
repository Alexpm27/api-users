package com.example.securitywithjwt.persistence.repositories;

import com.example.securitywithjwt.persistence.models.Parameter;
import com.example.securitywithjwt.persistence.models.Statistic;
import com.example.securitywithjwt.persistence.models.projections.StatisticsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IParameterRepository extends JpaRepository<Parameter, Long> {

    List<Parameter> findAllByUserId(Long id);

    @Query(value = "SELECT s.id, s.median, p.date FROM parameters as p\n" +
            "INNER JOIN statistics as s ON p.id = s.parameter_id\n" +
            "INNER JOIN users ON users.id = p.user_id\n" +
            "WHERE users.id = :id", nativeQuery = true)
    List<StatisticsProjection> findAllStatisticsByUserId(Long id);

}
