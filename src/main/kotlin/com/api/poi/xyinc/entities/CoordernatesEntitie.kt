package com.api.poi.xyinc.entities

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Entity
@Table(name = "coordenates")
data class CoordenatesEntitie(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_coordenate", unique = true)
        @NotNull
        val idCoordenate : Long? = null,

        @Column(name = "name_coordenates")
        @JsonProperty("name_coordenate")
        val nameCoordenate : String? = "",

        @Column(name="coordenate_x")
        @Min(value=0, message="Coordenada deve ser positiva!")
        @JsonProperty("coordenate_x")
        val coordenateX : Long? = 0,

        @Column(name="coordenate_y")
        @Min(value=0, message="Coordenada deve ser positiva!")
        @JsonProperty("coordenate_y")
        val coordenateY : Long? = 0
)