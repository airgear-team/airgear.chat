package com.airgear.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "settlement", nullable = false)
    private String settlement;

    @Column(name = "region_id", nullable = false)
    private Long regionId;

    public Location() {
    }

    public Location(Long id, String settlement, Long regionId) {
        this.id = id;
        this.settlement = settlement;
        this.regionId = regionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) && Objects.equals(settlement, location.settlement)
                && Objects.equals(regionId, location.regionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, settlement, regionId);
    }
}
