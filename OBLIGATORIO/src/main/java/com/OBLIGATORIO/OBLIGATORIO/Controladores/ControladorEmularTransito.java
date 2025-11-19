package com.OBLIGATORIO.OBLIGATORIO.Controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OBLIGATORIO.OBLIGATORIO.Dtos.PuestoDTO;
import com.OBLIGATORIO.OBLIGATORIO.Dtos.TarifaPuestoDTO;
import com.OBLIGATORIO.OBLIGATORIO.Dtos.TransitosDTO;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.TarifaPuesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Transito;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;
import com.OBLIGATORIO.OBLIGATORIO.Utils.Respuesta;

@RestController
@RequestMapping("/admin")
public class ControladorEmularTransito {

    @GetMapping("/iniciarVista")
    public List<Respuesta> iniciarVista() {

        List<PuestoDTO> listaDTO = new ArrayList<>();

        for (Puesto p : Fachada.getInstancia().getPuestos()) {
            listaDTO.add(new PuestoDTO(p));
        }

        return Respuesta.lista(new Respuesta("iniciarVista", listaDTO));
    }

    @GetMapping("/tarifas")
    public List<Respuesta> getTarifas(@RequestParam String nombrePuesto) {
        Puesto puesto = Fachada.getInstancia().buscarPuestoPorNombre(nombrePuesto);
        if (puesto == null) {
            return Respuesta.lista(new Respuesta("tarifas", Map.of("tarifas", new ArrayList<>())));
        }

        List<TarifaPuestoDTO> tarifasDTO = puesto.getListaTarifaPuesto().stream().map(TarifaPuestoDTO::new)
                .collect(Collectors.toList());

        return Respuesta.lista(new Respuesta("tarifas", Map.of("tarifas", tarifasDTO)));
    }


    @PostMapping("/emularTransito")
    public List<Respuesta> emularTransito(@RequestParam String puesto, @RequestParam String matricula, @RequestParam String fechaHora) {

        try {
            TransitosDTO dto = Fachada.getInstancia().emularTransito(puesto, matricula, fechaHora);

            Vehiculo vehiculo = Fachada.getInstancia().buscarVehiculoPorMatricula(matricula);
            UsuarioPropietario propietario = vehiculo.getUsuarioPropietario();

            Map<String, Object> info = Map.of( "propietario", propietario.getNombreCompleto(), "categoria", dto.categoria,"bonificacion", dto.bonificacion,"costo", dto.montoPagado,"saldo", propietario.getSaldoActual());

            return Respuesta.lista(new Respuesta("emularTransito", info));

        } catch (UsuarioException e) {
            
            return Respuesta.lista(new Respuesta("error", e.getMessage()));

        } catch (Exception e) {
            return Respuesta.lista(new Respuesta("error", "Ocurrió un error inesperado."));
        }
    }

}
