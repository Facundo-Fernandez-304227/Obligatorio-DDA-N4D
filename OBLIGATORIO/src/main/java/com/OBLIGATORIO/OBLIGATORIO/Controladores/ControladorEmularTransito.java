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
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.PuestoException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
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
    public List<Respuesta> emularTransito( @RequestParam String puesto, @RequestParam String matricula, @RequestParam String fechaHora) {

        try {
            // 1️⃣ Buscar el puesto por nombre
            Puesto puestoSeleccionado = Fachada.getInstancia().buscarPuestoPorNombre(puesto);
            if (puestoSeleccionado == null) {
                return Respuesta.lista(new Respuesta("error", "No se encontró el puesto seleccionado."));
            }

            // 2️⃣ Buscar el vehículo por matrícula
            Vehiculo vehiculo = Fachada.getInstancia().buscarVehiculoPorMatricula(matricula);
            if (vehiculo == null) {
                return Respuesta
                        .lista(new Respuesta("error", "No se encontró el vehículo con matrícula: " + matricula));
            }

            // 2.1️⃣ Validar estado del propietario (State)
            UsuarioPropietario propietario = vehiculo.getUsuarioPropietario();

            try {
                if (!propietario.getEstado().puedeRealizarTransitos()) {
                    return Respuesta.lista(new Respuesta("error",
                            "El propietario está en estado '" + propietario.getEstado().getNombre()
                                    + "' y no puede realizar transitos."));
                }
            } catch (UsuarioException e) {
                return Respuesta.lista(new Respuesta("error", e.getMessage()));
            }

            // 3️⃣ Obtener la tarifa correspondiente
            TarifaPuesto tarifa = puestoSeleccionado.getListaTarifaPuesto().stream()
                    .filter(t -> t.getCategoriaVehiculoPuesto().equals(vehiculo.getCategoriaVehiculo()))
                    .findFirst()
                    .orElse(null);

            if (tarifa == null) {
                return Respuesta.lista(new Respuesta("error", "No hay tarifa para la categoría del vehículo."));
            }

            // 4️⃣ Calcular fecha y hora
            LocalDate fecha = LocalDate.parse(fechaHora.substring(0, 10));
            LocalTime hora = LocalTime.parse(fechaHora.substring(11));

            // 5️⃣ Buscar si tiene bonificación asignada
            Bonificacion bonificacionAplicada = vehiculo.getUsuarioPropietario().getBonificacionAsignadas().stream()
                    .filter(b -> b.getPuesto().equals(puestoSeleccionado))
                    .map(b -> b.getBonificacion())
                    .findFirst()
                    .orElse(null);

            // 6️⃣ Crear tránsito
            Transito transito = new Transito(fecha, hora, tarifa.getMontoPuesto(), vehiculo, puestoSeleccionado, bonificacionAplicada);

            // 7️⃣ Agregarlo
            Fachada.getInstancia().agregarTransito(transito);

            // 8️⃣ Calcular valores
            double costoFinal = transito.getMontoPagado();
            double saldoFinal = vehiculo.getUsuarioPropietario().getSaldoActual() - costoFinal;

            vehiculo.getUsuarioPropietario().setSaldoActual(saldoFinal);

            // 9️⃣ Armar respuesta al front
            Map<String, Object> info = Map.of(
                    "propietario", vehiculo.getUsuarioPropietario().getNombreCompleto(),
                    "categoria", vehiculo.getCategoriaVehiculo().getNombreCategoria(),
                    "bonificacion",
                    bonificacionAplicada != null ? bonificacionAplicada.getNombre() : "Sin bonificación",
                    "costo", costoFinal,
                    "saldo", saldoFinal);

            return Respuesta.lista(new Respuesta("emularTransito", info));

        } catch (Exception e) {
            // Captura cualquier excepción inesperada
            e.printStackTrace();
            return Respuesta.lista(new Respuesta("error", "Ocurrió un error: " + e.getMessage()));
        }
    }

}
