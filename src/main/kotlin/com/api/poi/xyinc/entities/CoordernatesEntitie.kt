package com.api.poi.xyinc.entities

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@Entity
@Table(name = "coordenates")
data class CoordenatesEntitie(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_coordenate_sequence")
        @Column(name = "id_coordenate", unique = true)
        @SequenceGenerator(name = "id_coordenate_sequence", sequenceName = "sequence_id_coordenate", initialValue = 1, allocationSize = 1)
        @NotNull
        val idCoordenate : Long? = null,

        @Column(name = "name_coordenates")
        val nameCoordenate : String? = "",

        @Column(name="coordenate_x")
        @Min(value=0, message="Coordenada deve ser positiva!")
        val coordenateX : Long? = 0,

        @Column(name="coordenate_y")
        @Min(value=0, message="Coordenada deve ser positiva!")
        val coordenateY : Long? = 0
)