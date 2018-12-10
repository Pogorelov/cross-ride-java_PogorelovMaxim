/**
 * 
 */
package com.crossover.techtrial.repositories;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Ride;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author crossover
 *
 */
@RestResource(exported = false)
public interface RideRepository extends RideRepositoryCustom, CrudRepository<Ride, Long> {
}
