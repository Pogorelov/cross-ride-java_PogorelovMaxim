/**
 * 
 */
package com.crossover.techtrial.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crossover.techtrial.dto.TopDriverDTO;
import com.crossover.techtrial.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;

/**
 * @author crossover
 *
 */
@Service
public class RideServiceImpl implements RideService {

  @Autowired
  private RideRepository rideRepository;
  
  public Ride save(Ride ride) {
    return rideRepository.save(ride);
  }
  
  public Ride findById(Long rideId) {
    Optional<Ride> optionalRide = rideRepository.findById(rideId);
    return optionalRide.orElse(null);
  }

  @Override
  public List<TopDriverDTO> findTopRiders(Long max, LocalDateTime startTime, LocalDateTime endTime) {


    List topRiders = rideRepository.findTopRiders(max, startTime, endTime);
    return topRiders;
  }

}
