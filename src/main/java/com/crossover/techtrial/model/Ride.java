/**
 * 
 */
package com.crossover.techtrial.model;

import com.crossover.techtrial.dto.TopDriverDTO;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ride")
@SqlResultSetMapping(name = "TopDriversMapping",
        classes = @ConstructorResult(targetClass = TopDriverDTO.class,
                columns = {
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "email"),
                        @ColumnResult(name = "totalRideDurationInSeconds", type = Long.class),
                        @ColumnResult(name = "maxRideDurationInSeconds", type = Long.class),
                        @ColumnResult(name = "averageDistance", type = Double.class)
                }
        )
)
public class Ride implements Serializable{

  private static final long serialVersionUID = 9097639215351514001L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull
  @Column(name = "start_time")
  String startTime;
  
  @NotNull
  @Column(name = "end_time")
  String endTime;
  
  @Column(name = "distance")
  Long distance;
  
  @ManyToOne
  @JoinColumn(name = "driver_id", referencedColumnName = "id")
  Person driver;
  
  @ManyToOne
  @JoinColumn(name = "rider_id", referencedColumnName = "id")
  Person rider;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Long getDistance() {
    return distance;
  }

  public void setDistance(Long distance) {
    this.distance = distance;
  }

  public Person getDriver() {
    return driver;
  }

  public void setDriver(Person driver) {
    this.driver = driver;
  }

  public Person getRider() {
    return rider;
  }

  public void setRider(Person rider) {
    this.rider = rider;
  }
  
  

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((distance == null) ? 0 : distance.hashCode());
    result = prime * result + ((driver == null) ? 0 : driver.hashCode());
    result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((rider == null) ? 0 : rider.hashCode());
    result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Ride ride = (Ride) o;

    return (id != null ? id.equals(ride.id) : ride.id == null) &&
            (startTime != null ? startTime.equals(ride.startTime) : ride.startTime == null) &&
            (endTime != null ? endTime.equals(ride.endTime) : ride.endTime == null) &&
            (distance != null ? distance.equals(ride.distance) : ride.distance == null) &&
            (driver != null ? driver.equals(ride.driver) : ride.driver == null) &&
            (rider != null ? rider.equals(ride.rider) : ride.rider == null);
  }

  @Override
  public String toString() {
    return "Ride [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", distance=" + distance + ", driver=" + driver + ", rider=" + rider + "]";
  }
  
  
  
}
