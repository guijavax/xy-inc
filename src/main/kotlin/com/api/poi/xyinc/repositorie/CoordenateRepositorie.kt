package com.api.poi.xyinc.repositorie
import com.api.poi.xyinc.entities.CoordenatesEntitie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CoordenateRepositorie : JpaRepository<CoordenatesEntitie, Long> {

    @Query(value="select name_coordenates from coordenates " +
                    "where :dmax >= sqrt(((:coordenate_x - coordenate_x)^2) + ((:coordenate_y - coordenate_y)^2))"
    ,nativeQuery = true)
    fun searchPoiByProximity (@Param(value="coordenate_x")  coordernateX : Long,
                              @Param(value="coordenate_y")  coordernateY : Long,
                              @Param(value="dmax") dMax : Long) : List<String>
}