package com.applicantion.rest.Controller;

import com.applicantion.rest.Controller.DTO.MakerDTO;
import com.applicantion.rest.Entities.Maker;
import com.applicantion.rest.Services.IMakerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maker")
public class MakerController {

    @Autowired
    private IMakerServices makerServices;

    /**
 * Busca información de un Maker por su ID
 *
 * @param id El identificador único del Maker
 * @return Devuelve el objeto Maker encontrado, o un código de estado 404 si no se encuentra
 */
@GetMapping("/find/{id}")
public ResponseEntity<?> findById(@PathVariable Long id){
    // Intenta buscar el Maker por su ID
    Optional<Maker> makerOptional = makerServices.findById(id);

    // Si se encontró el Maker
    if(makerOptional.isPresent()){
        Maker maker = makerOptional.get();

        // Convierte la entidad Maker a MakerDTO
        MakerDTO makerDTO = MakerDTO.builder()
                .id(maker.getId())
                .name(maker.getName())
                .productList(maker.getProductList())
                .build();
        // Devuelve el MakerDTO encontrado
        return ResponseEntity.ok(makerDTO);
    }

    // Si no se encontró el Maker, devuelve un código de estado 404
    return ResponseEntity.notFound().build();
}

/**
 * Utiliza el método HTTP GET para obtener la lista de todos los fabricantes
 * Este método llama al método findAll de MakerService para obtener la lista de fabricantes,
 * y la convierte en una lista de MakerDTO para su procesamiento adicional o retorno
 *
 * @return Devuelve una entidad de respuesta que contiene la información de todos los fabricantes, utilizando el código de estado HTTP 200 (OK)
 */
@GetMapping("/findAll")
public ResponseEntity<?> findAll(){
    // Obtiene todos los fabricantes desde makerServices y los mapea a una lista de MakerDTO
    List<MakerDTO> makerList = makerServices.findAll()
            .stream()
            .map(maker -> MakerDTO.builder()
                    .id(maker.getId())
                    .name(maker.getName())
                    .productList(maker.getProductList())
                    .build())
            .toList();

    // Devuelve una entidad de respuesta que contiene todos los fabricantes
    return ResponseEntity.ok(makerServices.findAll());
}

@PostMapping("/save")
public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) throws URISyntaxException {

    if(makerDTO.getName().isBlank()){
        return ResponseEntity.badRequest().build();
    }

    makerServices.save(Maker.builder()
            .name(makerDTO.getName())
            .build());
    return ResponseEntity.created(new URI("/api/maker/save")).build();
}
}
