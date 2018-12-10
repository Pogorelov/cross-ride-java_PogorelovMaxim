package com.crossover.techtrial.repositories;


import com.crossover.techtrial.dto.TopDriverDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by pogorelov on 12/10/18.
 */
public interface RideRepositoryCustom {

    List<TopDriverDTO> findTopRiders(Long max, LocalDateTime startTime, LocalDateTime endTime);
}
