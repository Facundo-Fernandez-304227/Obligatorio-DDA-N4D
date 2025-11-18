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

        List<PuestoDTO> listaDTO = Fachada.getInstancia()
                .getPuestos()
                .stream()
                .map(PuestoDTO::new)
                .collect(Collectors.toList());

        return Respuesta.lista(new Respuesta("iniciarVista", listaDTO));
    }

    @GetMapping("/tarifas")
    public List<Respuesta> getTarifas(@RequestParam String nombrePuesto) {
        Puesto puesto = Fachada.getInstancia().buscarPuestoPorNombre(nombrePuesto);
        if (puesto == null) {
            return Respuesta.lista(new Respuesta("tarifas",
                    Map.of("tarifas", new ArrayList<>())));
        }

        // 🔹 Convertir tarifas a DTOs para evitar bucles
        List<TarifaPuestoDTO> tarifasDTO = puesto.getListaTarifaPuesto()
                .stream()
                .map(TarifaPuestoDTO::new)
                .collect(Collectors.toList());

        return Respuesta.lista(new Respuesta("tarifas",
                Map.of("tarifas", tarifasDTO)));
    }

    @PostMapping("/emularTransito")
    public List<Respuesta> emularTransito(@RequestParam String puesto, @RequestParam String matricula,
            @RequestParam String fechaHora) {

        try {

            Puesto puestoSel = Fachada.getInstancia().buscarPuestoPorNombre(puesto);
            if (puestoSel == null) {
                return Respuesta.lista(new Respuesta("error", "No se encontró el puesto seleccionado."));
            }

            Vehiculo vehiculo = Fachada.getInstancia().buscarVehiculoPorMatricula(matricula);
            if (vehiculo == null) {
                return Respuesta
                        .lista(new Respuesta("error", "No se encontró el vehículo con matrícula: " + matricula));
            }

            UsuarioPropietario propietario = vehiculo.getUsuarioPropietario();

            if (!propietario.getEstado().puedeRealizarTransitos()) {
                return Respuesta.lista(new Respuesta("error", "El propietario está en estado '"
                        + propietario.getEstado().getNombre() + "' y no puede realizar tránsitos."));
            }

            TarifaPuesto tarifa = puestoSel.getListaTarifaPuesto().stream()
                    .filter(t -> t.getCategoriaVehiculoPuesto().equals(vehiculo.getCategoriaVehiculo()))
                    .findFirst()
                    .orElse(null);

            if (tarifa == null) {
                return Respuesta.lista(new Respuesta("error", "No hay tarifa para la categoría del vehículo."));
            }

            // Parsear fecha
            LocalDate fecha = LocalDate.parse(fechaHora.substring(0, 10));
            LocalTime hora = LocalTime.parse(fechaHora.substring(11));

            // 6) Buscar bonificación asignada para ese puesto
            Bonificacion bonificacionAplicada = null;

            // Si NO está penalizado → puede aplicar bonificaciones
            if (!propietario.getEstado().getNombre().equals("Penalizado")) {

                bonificacionAplicada = propietario.getBonificacionAsignadas().stream()
                        .filter(b -> b.getPuesto().equals(puestoSel))
                        .map(b -> b.getBonificacion())
                        .findFirst()
                        .orElse(null);

            } else {
                // Está penalizado → ignorar bonificaciones
                bonificacionAplicada = null;
            }

            //Crear tránsito
            Transito transito = new Transito(fecha, hora, tarifa.getMontoPuesto(), vehiculo, puestoSel, bonificacionAplicada);

            //VALIDAR SALDO
            double costoFinal = transito.getMontoPagado();
            double saldoActual = propietario.getSaldoActual();

            if (saldoActual < costoFinal) {
                return Respuesta.lista(new Respuesta("error", "Saldo insuficiente. Su saldo es $" + saldoActual + " y el costo del tránsito es $" + costoFinal));
            }

            //agregar tránsito
            Fachada.getInstancia().agregarTransito(transito);

            //Cobrar tránsito
            double saldoFinal = saldoActual - costoFinal;
            propietario.setSaldoActual(saldoFinal);

            // 11) Respuesta al front
            Map<String, Object> info = Map.of(
                    "propietario", propietario.getNombreCompleto(),
                    "categoria", vehiculo.getCategoriaVehiculo().getNombreCategoria(),
                    "bonificacion",
                    bonificacionAplicada != null ? bonificacionAplicada.getNombre() : "Sin bonificación",
                    "costo", costoFinal,
                    "saldo", saldoFinal);

            return Respuesta.lista(new Respuesta("emularTransito", info));

        } catch (Exception e) {
            e.printStackTrace();
            return Respuesta.lista(new Respuesta("error", "Ocurrió un error: " + e.getMessage()));
        }
    }

}
