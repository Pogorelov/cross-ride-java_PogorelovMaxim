/**
 * 
 */
package com.crossover.techtrial.service;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.model.Ride;

import java.time.LocalDateTime;
import java.util.List;

/**
 * RideService for rides.
 * @author crossover
 *
 */
public interface RideService {
  
  Ride save(Ride ride);
  
  Ride findById(Long rideId);

  List<TopDriverDTO> findTopRiders(Long max, LocalDateTime startTime, LocalDateTime endTime);
  

}
