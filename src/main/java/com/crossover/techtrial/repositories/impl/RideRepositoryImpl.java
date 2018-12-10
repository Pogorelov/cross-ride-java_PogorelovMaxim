package com.crossover.techtrial.repositories.impl;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.repositories.RideRepository;
import com.crossover.techtrial.repositories.RideRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pogorelov on 12/10/18.
 */
public class RideRepositoryImpl implements RideRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<TopDriverDTO> findTopRiders(Long max, LocalDateTime startTime, LocalDateTime endTime) {
        return em.createNativeQuery("SELECT " +
                "person.name as name, " +
                "person.email as email, " +
                "MAX(durationSum) as totalRideDurationInSeconds, " +
                "maxDuration as maxRideDurationInSeconds, " +
                "avgDistance as averageDistance" +
                "      FROM (SELECT driver_id, SUM(TIMESTAMPDIFF(SECOND, start_time, end_time)) AS durationSum, MAX(TIMESTAMPDIFF(SECOND, start_time, end_time)) AS maxDuration, AVG(distance + 0.0) AS avgDistance" +
                "            FROM ride" +
                "            WHERE (start_time >= :startTime AND end_time < :endTime)" +
                "            GROUP BY driver_id" +
                "        ) AS subRide" +
                "        INNER JOIN person ON driver_id = person.id" +
                "        GROUP BY driver_id" +
                "        ORDER BY MAX(durationSum) DESC" +
                "        LIMIT :maxNum", "TopDriversMapping")
                .setParameter("startTime", startTime.toString())
                .setParameter("endTime", endTime.toString())
                .setParameter("maxNum", max)
                .getResultList();
    }
}
