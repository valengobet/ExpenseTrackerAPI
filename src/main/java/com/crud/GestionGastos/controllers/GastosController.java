package com.crud.GestionGastos.controllers;

import com.crud.GestionGastos.models.*;
import com.crud.GestionGastos.services.GastosService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gastos")
public class GastosController {
    @Autowired
    private GastosService gastosService;

    // Create
    @Operation(
            summary = "Crear un nuevo gasto",
            description = "Permite registrar un nuevo gasto en el sistema, incluyendo información como categoría, método de pago, destinatario y fecha."
    )
    @PostMapping
    public GastosModel createGasto(@Valid @RequestBody GastosModel gasto){
        return gastosService.createGasto(gasto);
    }

    // Create Metodo Pago
    @Operation(
            summary = "Crear un método de pago",
            description = "Agrega un nuevo método de pago al sistema. Si el método ya existe, no se duplicará."
    )
    @PostMapping(path="/metodo_pago")
    public String createMetodoPago(@RequestBody String metodo_pago){
        gastosService.getOrCreateMetodoPago(metodo_pago);
        return "El metodo de pago '" + metodo_pago + "' ha sido creado con exito.";
    }

    // Create Categoria
    @Operation(
            summary = "Crear una categoría",
            description = "Agrega una nueva categoría de gastos al sistema. Si la categoría ya existe, no se duplicará."
    )
    @PostMapping(path="/categoria")
    public String createCategoria(@RequestBody String categoria){
        gastosService.getOrCreateCategoria(categoria);
        return "La categoria '" + categoria + "' ha sido creada con exito.";
    }

    // Create Destinatario
    @Operation(
            summary = "Crear un destinatario",
            description = "Agrega un nuevo destinatario de gastos al sistema. Si el destinatario ya existe, no se duplicará."
    )
    @PostMapping(path="/destinatario")
    public String createDestinatario(@RequestBody String destinatario){
        gastosService.getOrCreateDestinatario(destinatario);
        return "El destinatario '" + destinatario + "' ha sido creado con exito.";
    }

    // Read all
    @Operation(
            summary = "Obtener todos los gastos",
            description = "Recupera una lista de todos los gastos registrados en el sistema."
    )
    @GetMapping
    public ArrayList<GastosModel> readGastos(){
        return gastosService.readGastos();
    }

    // Read by Id
    @Operation(
            summary = "Obtener un gasto por ID",
            description = "Devuelve la información de un gasto específico basado en el ID proporcionado."
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> readGastosById(@PathVariable Long id){
        Optional<GastosModel> result = gastosService.getById(id);

        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        } else{
            return ResponseEntity.status(404).body("El gasto con ID: " + id + " no fue encontrado.");
        }
    }

    // Read by Categoria
    @Operation(
            summary = "Obtener gastos por categoría",
            description = "Devuelve todos los gastos asociados con una categoría específica."
    )
    @GetMapping(path = "categoria/{categoria}")
    public ResponseEntity<?> getByCategoria(@PathVariable String categoria){
        ArrayList<GastosModel> result = gastosService.getByCategoria(categoria);

        if(result.size() > 0){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(404).body("No se encontraron gastos con la categoria '" + categoria + "'");
        }
    }

    // Read by Metodo Pago
    @Operation(
            summary = "Obtener gastos por método de pago",
            description = "Devuelve todos los gastos asociados con un método de pago específico."
    )
    @GetMapping(path = "metodo_pago/{metodo_pago}")
    public ResponseEntity<?> getByMetodoPago(@PathVariable String metodo_pago){
        ArrayList<GastosModel> result = gastosService.getByMetodoPago(metodo_pago);

        if(result.size() > 0){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(404).body("No se encontraron gastos con el metodo de pago '" + metodo_pago + "'");
        }
    }

    // Read by Destinatario
    @Operation(
            summary = "Obtener gastos por destinatario",
            description = "Devuelve todos los gastos asociados con un destinatario específico."
    )
    @GetMapping(path = "destinatario/{destinatario}")
    public ResponseEntity<?> getByDestinatario(@PathVariable String destinatario){
        ArrayList<GastosModel> result = gastosService.getByDestinatario(destinatario);

        if(result.size() > 0){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(404).body("No se encontraron gastos con el destinatario '" + destinatario + "'");
        }
    }

    // Read by fecha
    @Operation(
            summary = "Obtener gastos por fecha",
            description = "Devuelve todos los gastos registrados en una fecha específica."
    )
    @GetMapping(path = "fecha/{fecha}")
    public ResponseEntity<?> getByFecha(@PathVariable LocalDate fecha){
        ArrayList<GastosModel> result = gastosService.getByFecha(fecha);

        if(result.size() > 0){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(404).body("No se encontraron gastos en la fecha: '" + fecha + "'");
        }
    }

    // Read by mes
    @Operation(
            summary = "Obtener gastos por mes",
            description = "Devuelve todos los gastos registrados durante un mes específico. El mes se debe proporcionar como un número del 1 al 12."
    )
    @GetMapping(path = "mes/{mes}")
    public ResponseEntity<?> getByMes(@PathVariable int mes){
        ArrayList<GastosModel> result = gastosService.getByMes(mes);

        if(result.size() > 0){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(404).body("No se encontraron gastos en el mes: '" + mes + "'");
        }
    }

    // Read by año
    @Operation(
            summary = "Obtener gastos por año",
            description = "Devuelve todos los gastos registrados durante un año específico."
    )
    @GetMapping(path = "anio/{anio}")
    public ResponseEntity<?> getByAnio(@PathVariable int anio){
        ArrayList<GastosModel> result = gastosService.getByAnio(anio);

        if(result.size() > 0){
            return ResponseEntity.ok(result);
        } else{
            return ResponseEntity.status(404).body("No se encontraron gastos en el año: '" + anio + "'");
        }
    }

    // Read gasto total by Categoria
    @Operation(
            summary = "Obtener el gasto total por categoría",
            description = "Calcula y devuelve el monto total gastado en una categoría específica."
    )
    @GetMapping(path = "categoria/gasto_total/{categoria}")
    public String gastoTotalByCategoria(@PathVariable String categoria){
        int monto_total = gastosService.gastoTotalByCategoria(categoria);

        return "El gasto total para " + categoria + " es: " + monto_total;
    }

    // Read gasto total by Metodo Pago
    @Operation(
            summary = "Obtener el gasto total por método de pago",
            description = "Calcula y devuelve el monto total gastado utilizando un método de pago específico."
    )
    @GetMapping(path = "metodo_pago/gasto_total/{metodo_pago}")
    public String gastoTotalByMetodoPago(@PathVariable String metodo_pago){
        int monto_total = gastosService.gastoTotalByMetodoPago(metodo_pago);

        return "El gasto total para " + metodo_pago + " es: " + monto_total;
    }

    // Read gasto total by Destinatario
    @Operation(
            summary = "Obtener el gasto total por destinatario",
            description = "Calcula y devuelve el monto total gastado en un destinatario específico."
    )
    @GetMapping(path = "destinatario/gasto_total/{destinatario}")
    public String gastoTotalByDestinatario(@PathVariable String destinatario){
        int monto_total = gastosService.gastoTotalByDestinatario(destinatario);

        return "El gasto total para " + destinatario + " es: " + monto_total;
    }

    // Read gasto total by Fecha
    @Operation(
            summary = "Obtener el gasto total por fecha",
            description = "Calcula y devuelve el monto total gastado en una fecha específica."
    )
    @GetMapping(path = "fecha/gasto_total/{fecha}")
    public String gastoTotalByFecha(@PathVariable LocalDate fecha){
        int monto_total = gastosService.gastoTotalByFecha(fecha);

        return "El gasto total para " + fecha + " es: " + monto_total;
    }

    // Read gasto total by Mes
    @Operation(
            summary = "Obtener el gasto total por mes",
            description = "Calcula y devuelve el monto total gastado durante un mes específico."
    )
    @GetMapping(path = "mes/gasto_total/{mes}")
    public String gastoTotalByMes(@PathVariable int mes){
        int monto_total = gastosService.gastoTotalByMes(mes);

        return "El gasto total para " + mes + " es: " + monto_total;
    }

    // Read gasto total by Anio
    @Operation(
            summary = "Obtener el gasto total por año",
            description = "Calcula y devuelve el monto total gastado durante un año específico."
    )
    @GetMapping(path = "anio/gasto_total/{anio}")
    public String gastoTotalByAnio(@PathVariable int anio){
        int monto_total = gastosService.gastoTotalByAnio(anio);

        return "El gasto total para " + anio + " es: " + monto_total;
    }

    // Update
    @Operation(
            summary = "Actualizar un gasto",
            description = "Permite actualizar la información de un gasto existente. Se requiere el ID del gasto."
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateGasto(@PathVariable Long id, @Valid @RequestBody GastosModel gasto){
        Optional<GastosModel> result = gastosService.updateGasto(id, gasto);

        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        } else{
            return ResponseEntity.status(404).body("El gasto con ID: " + id + " no fue encontrado.");
        }
    }

    // Delete
    @Operation(
            summary = "Eliminar un gasto",
            description = "Elimina un gasto existente basado en el ID proporcionado."
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteGasto(@PathVariable Long id){
        Optional<GastosModel> result = gastosService.deleteGasto(id);

        if(result.isPresent()){
            return ResponseEntity.ok(result.get());
        } else{
            return ResponseEntity.status(404).body("El gasto con ID: " + id + " no fue encontrado.");
        }
    }
}
