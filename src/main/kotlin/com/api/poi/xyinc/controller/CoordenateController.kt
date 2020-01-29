package com.api.poi.xyinc.controller
import com.api.poi.xyinc.entities.CoordenatesEntitie
import com.api.poi.xyinc.repositorie.CoordenateRepositorie
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*
import javax.servlet.http.HttpServletResponse

@Api(value="coordenates")
@RestController
@RequestMapping("/coordenates")
 class CoordenateController {

    @Autowired
    lateinit  var coordenateRepositorie : CoordenateRepositorie
    val LOGGER = logger()

    @ApiOperation(value="Buscar todos os pois", response=CoordenatesEntitie::class)
        @ApiResponses(value= [
                ApiResponse(code=200, message="Retorna Lista de todos os pois", response=ResponseEntity::class),
                ApiResponse(code=500, message="Caso tenhamos algum erro vamos retornar a Exception", response= ResponseEntityExceptionHandler::class)
                ]
    )
    @GetMapping(path = ["/findAll"])
    fun findAll() : ResponseEntity<List<CoordenatesEntitie>>? {
        var coordenates : List<CoordenatesEntitie>?
        try {
            coordenates  = coordenateRepositorie.findAll()
            if (!coordenates.isEmpty()) return ResponseEntity.ok(coordenates)
        } catch (e : Exception) {
            LOGGER.error(e.message!!)
        }
        return ResponseEntity.noContent().build()
    }

    @ApiOperation(value="Buscar poi por proximidade", response=CoordenatesEntitie::class)
            @ApiResponses(value= [
                ApiResponse(code=200, message="Retorna todos os pois de acordo com uma coordenada e uma distância maxima", response=ResponseEntity::class),
                ApiResponse(code=500, message="Caso tenhamos algum erro vamos retornar a Exception", response=ResponseEntityExceptionHandler::class)
                ]
    )
    @GetMapping(path = ["/findPoiByProximity/{dmx}"])
    fun findPoiByProximity(@RequestBody coordenatesPoi: Map<String, Long>, @PathVariable("dmx") dmx: Long): ResponseEntity<List<String>>? {
        try {
            val coord = coordenateRepositorie.searchPoiByProximity(coordenatesPoi["coordenateX"].toString().toLong(), coordenatesPoi["coordenateY"].toString().toLong(), dmx)
            if (coord.isNotEmpty()) {
                return ResponseEntity.ok(coord)
            }
        } catch (e: Exception) {
            LOGGER.error(e.message!!)
        }
        return ResponseEntity.noContent().build()
    }

    @ApiOperation(value="Cadastrar um poi", response=CoordenatesEntitie::class)
            @ApiResponses(value= [
                ApiResponse(code=200, message="Retorna o poi cadastrado", response=ResponseEntity::class),
                ApiResponse(code=500, message="Caso tenhamos algum erro vamos retornar a Exception", response=ResponseEntityExceptionHandler::class)
                ]
    )

    @PostMapping(path = ["/insert"])
    fun insertPoi(@RequestBody coordenatesPoi: CoordenatesEntitie, response: HttpServletResponse): ResponseEntity<CoordenatesEntitie>? {
        try {
            return coordenateRepositorie.save(coordenatesPoi).let {
                val uri: URI = ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/findById/{id}")
                        .buildAndExpand(it.idCoordenate)
                        .toUri()
                response.setHeader("Location", uri.toASCIIString())
                ResponseEntity.created(uri).body(it)
            }
        } catch (e: Exception) {
            LOGGER.error(e.message!!)
        }
        return null
    }

    @GetMapping(path=["/findById/{id}"])
    fun findById(@PathVariable("id") id : Long) : ResponseEntity<CoordenatesEntitie>? {
            var coordenatesEntitie : CoordenatesEntitie? = null
        try {
           val coordenates : Optional<CoordenatesEntitie> = coordenateRepositorie.findById(id)
            coordenatesEntitie = coordenates.get()
        } catch (e : Exception) {
            LOGGER.error(e.message!!)
        } finally {
            return if(coordenatesEntitie != null) ResponseEntity.ok(coordenatesEntitie) else ResponseEntity.noContent().build()
        }
    }
}