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

/**
 * Guarda la información del fabricante
 *
 * @param makerDTO Objeto DTO que contiene la información del fabricante
 * @return ResponseEntity<?> Entidad de respuesta
 *
 * Este método recibe una solicitud POST y guarda la información del fabricante.
 * Si el nombre del fabricante está vacío, se devuelve una respuesta de solicitud incorrecta;
 * de lo contrario, se guarda la información del fabricante en la base de datos y se devuelve
 * una entidad de respuesta creada. El método utiliza el anotación @RequestBody para
 * vincular los datos del cuerpo de la solicitud HTTP con el parámetro makerDTO y
 * construye un objeto Maker mediante Maker.builder() para guardar la información.
 */
@PostMapping("/save")
public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) throws URISyntaxException {

    // Verifica si el nombre del fabricante está vacío, en cuyo caso se devuelve una solicitud incorrecta
    if(makerDTO.getName().isBlank()){
        return ResponseEntity.badRequest().build();
    }

    // Guarda la información del fabricante en la base de datos
    makerServices.save(Maker.builder()
            .name(makerDTO.getName())
            .build());

    // Devuelve una entidad de respuesta creada, indicando que el recurso se ha creado correctamente
    return ResponseEntity.created(new URI("/api/maker/save")).build();
}


/**
 * Método del controlador para actualizar la información de un Maker específico
 *
 * @param id Identificador único del Maker que se desea actualizar
 * @param makerDTO Objeto MakerDTO que contiene la información actualizada, enviado a través del cuerpo de la solicitud
 * @return Retorna un objeto ResponseEntity que contiene el resultado del proceso
 */
@PutMapping("/update/{id}")
public ResponseEntity<?> updateMaker(@PathVariable Long id, @RequestBody MakerDTO makerDTO){
    // Intenta encontrar el Maker con el ID especificado a través del método findById del servicio
    Optional<Maker> makerOptional = makerServices.findById(id);

    // Verifica si se encontró el Maker con el ID especificado
    if(makerOptional.isPresent()){
        // Si se encontró, obtiene el objeto Maker
        Maker maker = makerOptional.get();
        // Actualiza el nombre del Maker
        maker.setName(makerDTO.getName());
        // Guarda el Maker actualizado
        makerServices.save(maker);
        // Retorna un ResponseEntity que indica éxito y contiene un mensaje de confirmación de actualización
        return ResponseEntity.ok("Registro Actualizado");
    }

    // Si no se encontró el Maker con el ID especificado, retorna un ResponseEntity que indica que no se encontró
    return ResponseEntity.notFound().build();
}

/**
 * Maneja una solicitud de eliminación para eliminar un registro de Maker por su ID
 *
 * @param id El ID del registro de Maker a eliminar
 * @return Devuelve un estado HTTP 200 con el mensaje "Registro eliminado" si la eliminación es exitosa, o un estado 400 si la solicitud es incorrecta
 */
@DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteMaker(@PathVariable Long id){
    // Verifica si el ID no es nulo, en ese caso procede con la eliminación
    if(id != null){
        makerServices.deleteById(id);
        return ResponseEntity.ok("Registro eliminado");
    }

    // Si el ID es nulo, devuelve una solicitud incorrecta
    return ResponseEntity.badRequest().build();
}

}
