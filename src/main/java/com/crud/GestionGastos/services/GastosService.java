package com.crud.GestionGastos.services;

import com.crud.GestionGastos.models.*;
import com.crud.GestionGastos.repositories.CategoriaRepository;
import com.crud.GestionGastos.repositories.DestinatarioRepository;
import com.crud.GestionGastos.repositories.FactGastosRepository;
import com.crud.GestionGastos.repositories.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GastosService {
    @Autowired
    private FactGastosRepository factGastosRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private DestinatarioRepository destinatarioRepository;
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    // Create gasto.
    public GastosModel createGasto(GastosModel gasto){
        CategoriaModel categoria = getOrCreateCategoria(gasto.getCategoria());
        MetodoPagoModel metodo_pago = getOrCreateMetodoPago(gasto.getMetodopago());
        DestinatarioModel destinatario = getOrCreateDestinatario(gasto.getDestinatario());

        FactGastosModel newFactGastos = new FactGastosModel(gasto.getMonto(), gasto.getFecha(), gasto.getDescripcion(), categoria, metodo_pago, destinatario);
        factGastosRepository.save(newFactGastos);
        GastosModel result = toGastosModel(newFactGastos);
        return result;
    }

    // Create categoria.

    // Funcion para buscar en la tabla de categorias si existe una con el nombre pasado como parametro y devolver su id.
    // En caso de que no exista lo crea y devuelve el id.
    public CategoriaModel getOrCreateCategoria(String categoria){
        CategoriaModel searched_categoria = categoriaRepository.findByNombre(categoria);

        if(categoria != null){
            return searched_categoria;
        } else{
            CategoriaModel new_categoria = new CategoriaModel(categoria);
            categoriaRepository.save(new_categoria);
            return new_categoria;
        }
    }

    // Funcion para buscar en la tabla de metodos de pago si existe una con el nombre pasado como parametro y devolver su id.
    // En caso de que no exista lo crea y devuelve el id.
    public MetodoPagoModel getOrCreateMetodoPago(String metodo_pago){
        MetodoPagoModel searched_metodo_pago = metodoPagoRepository.findByNombre(metodo_pago);

        if(metodo_pago != null){
            return searched_metodo_pago;
        } else{
            MetodoPagoModel new_metodo_pago = new MetodoPagoModel(metodo_pago);
            metodoPagoRepository.save(new_metodo_pago);
            return new_metodo_pago;
        }
    }

    // Funcion para buscar en la tabla de destinatarios si existe una con el nombre pasado como parametro y devolver su id.
    // En caso de que no exista lo crea y devuelve el id.
    public DestinatarioModel getOrCreateDestinatario(String destinatario){
        DestinatarioModel searched_destinatario = destinatarioRepository.findByNombre(destinatario);

        if(destinatario != null){
            return searched_destinatario;
        } else{
            DestinatarioModel new_destinatario = new DestinatarioModel(destinatario);
            destinatarioRepository.save(new_destinatario);
            return new_destinatario;
        }
    }

    // Read gasto.
    public ArrayList<GastosModel> readGastos(){
        List<FactGastosModel> factGastos = factGastosRepository.findAllWithJoins();

        ArrayList<GastosModel> result = new ArrayList<GastosModel>();

        for (FactGastosModel factGasto : factGastos) {
            GastosModel gasto = toGastosModel(factGasto);
            result.add(gasto);
        }

        return result;
    }

    public Optional<GastosModel> getById(Long id){
        Optional<FactGastosModel> result = factGastosRepository.findById(id);

        if(result.isPresent()){
            return Optional.of(toGastosModel(result.get()));
        } else{
            return Optional.empty();
        }

    }

    // Read by Categoria
    public ArrayList<GastosModel> getByCategoria(String categoria_name){
        CategoriaModel categoria = categoriaRepository.findByNombre(categoria_name);
        List<FactGastosModel> factGastos = factGastosRepository.findByCategoria(categoria);
        ArrayList<GastosModel> result = new ArrayList<GastosModel>();

        for(FactGastosModel factGasto : factGastos){
            GastosModel gasto = toGastosModel(factGasto);
            result.add(gasto);
        }
        return  result;
    }

    // Read by Metodo Pago
    public ArrayList<GastosModel> getByMetodoPago(String metodo_pago_name){
        MetodoPagoModel metodoPago = metodoPagoRepository.findByNombre(metodo_pago_name);
        List<FactGastosModel> factGastos = factGastosRepository.findByMetodoPago(metodoPago);
        ArrayList<GastosModel> result = new ArrayList<GastosModel>();

        for(FactGastosModel factGasto : factGastos){
            GastosModel gasto = toGastosModel(factGasto);
            result.add(gasto);
        }
        return  result;
    }

    // Read by Destinatario
    public ArrayList<GastosModel> getByDestinatario(String destinatario_name){
        DestinatarioModel destinatario = destinatarioRepository.findByNombre(destinatario_name);
        List<FactGastosModel> factGastos = factGastosRepository.findByDestinatario(destinatario);
        ArrayList<GastosModel> result = new ArrayList<GastosModel>();

        for(FactGastosModel factGasto : factGastos){
            GastosModel gasto = toGastosModel(factGasto);
            result.add(gasto);
        }
        return  result;
    }

    // Read by Fecha
    public ArrayList<GastosModel> getByFecha(LocalDate fecha){
        List<FactGastosModel> factGastos = factGastosRepository.findByFecha(fecha);
        ArrayList<GastosModel> result = new ArrayList<GastosModel>();

        for(FactGastosModel factGasto : factGastos){
            GastosModel gasto = toGastosModel(factGasto);
            result.add(gasto);
        }
        return  result;
    }

    // Read by Mes
    public ArrayList<GastosModel> getByMes(int mes){
        List<FactGastosModel> factGastos = factGastosRepository.findByMes(mes);
        ArrayList<GastosModel> result = new ArrayList<GastosModel>();

        for(FactGastosModel factGasto : factGastos){
            GastosModel gasto = toGastosModel(factGasto);
            result.add(gasto);
        }
        return  result;
    }

    // Read by Año
    public ArrayList<GastosModel> getByAnio(int anio){
        List<FactGastosModel> factGastos = factGastosRepository.findByAnio(anio);
        ArrayList<GastosModel> result = new ArrayList<GastosModel>();

        for(FactGastosModel factGasto : factGastos){
            GastosModel gasto = toGastosModel(factGasto);
            result.add(gasto);
        }
        return  result;
    }

    // Read gasto total by Categoria
    public int gastoTotalByCategoria (String categoria_name){
        ArrayList<GastosModel> gastosByCategoria = getByCategoria(categoria_name);

        int monto_total = 0;
        for(GastosModel gasto : gastosByCategoria){
            monto_total += gasto.getMonto();
        }

        return monto_total;
    }

    // Read gasto total by Metodo Pago
    public int gastoTotalByMetodoPago (String metodo_pago_name){
        ArrayList<GastosModel> gastosByMetodoPago = getByMetodoPago(metodo_pago_name);

        int monto_total = 0;
        for(GastosModel gasto : gastosByMetodoPago){
            monto_total += gasto.getMonto();
        }

        return monto_total;
    }

    // Read gasto total by Categoria
    public int gastoTotalByDestinatario (String destinatario_name){
        ArrayList<GastosModel> gastosByDestinatario = getByDestinatario(destinatario_name);

        int monto_total = 0;
        for(GastosModel gasto : gastosByDestinatario){
            monto_total += gasto.getMonto();
        }

        return monto_total;
    }

    // Read gasto total by Fecha
    public int gastoTotalByFecha (LocalDate fecha){
        ArrayList<GastosModel> gastosByFecha = getByFecha(fecha);

        int monto_total = 0;
        for(GastosModel gasto : gastosByFecha){
            monto_total += gasto.getMonto();
        }

        return monto_total;
    }

    // Read gasto total by Mes
    public int gastoTotalByMes (int mes){
        ArrayList<GastosModel> gastosByMes = getByMes(mes);

        int monto_total = 0;
        for(GastosModel gasto : gastosByMes){
            monto_total += gasto.getMonto();
        }

        return monto_total;
    }

    // Read gasto total by Año
    public int gastoTotalByAnio (int anio){
        ArrayList<GastosModel> gastosByAnio = getByAnio(anio);

        int monto_total = 0;
        for(GastosModel gasto : gastosByAnio){
            monto_total += gasto.getMonto();
        }

        return monto_total;
    }

    // Update gasto.
    public Optional<GastosModel> updateGasto (Long id, GastosModel gasto){
        CategoriaModel categoria = getOrCreateCategoria(gasto.getCategoria());
        MetodoPagoModel metodo_pago = getOrCreateMetodoPago(gasto.getMetodopago());
        DestinatarioModel destinatario = getOrCreateDestinatario(gasto.getDestinatario());

        Optional<FactGastosModel> result = factGastosRepository.findById(id);

        if(result.isPresent()){
            FactGastosModel toUpdate = result.get();

            toUpdate.setCategoria(categoria);
            toUpdate.setDestinatario(destinatario);
            toUpdate.setMetodoPago(metodo_pago);
            toUpdate.setDescripcion(gasto.getDescripcion());
            toUpdate.setFecha(gasto.getFecha());
            toUpdate.setMonto(gasto.getMonto());

            factGastosRepository.save(toUpdate);
            return Optional.of(toGastosModel(toUpdate));
        } else{
            return Optional.empty();
        }
    }

    // Funcion para convertir un gasto de FactGastosModel a GastosModel. (Reemplaza los ids de las dimensiones por sus nombres)
    public GastosModel toGastosModel(FactGastosModel gasto){

        FactGastosModel gastoFactGastosModel= factGastosRepository.findByIdWithJoins(gasto.getId());

        return new GastosModel(gastoFactGastosModel.getId(), gastoFactGastosModel.getMonto(), gastoFactGastosModel.getFecha(), gastoFactGastosModel.getDescripcion(), gastoFactGastosModel.getCategoria().getNombre(), gastoFactGastosModel.getMetodoPago().getNombre(), gastoFactGastosModel.getDestinatario().getNombre());
    }

    // Delete gasto.
    public Optional<GastosModel> deleteGasto(Long id){
        // Se busca el gasto por id, si existe retorna un Optional de GastosModel si no existe Optional.empty().
        Optional<GastosModel> gastoToDelete = getById(id);

        if(gastoToDelete.isPresent()){
            // Como se encontro un gasto con el id se elimina y se retorna el gasto eliminado
            factGastosRepository.deleteById(id);
            return gastoToDelete;
        } else{
            return Optional.empty();
        }

    }
}
