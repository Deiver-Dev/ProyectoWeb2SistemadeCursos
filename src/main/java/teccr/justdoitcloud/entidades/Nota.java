package teccr.justdoitcloud.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.time.LocalDate;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "inscripcion_id", nullable = false, unique = true)
    private Inscripcion inscripcion;

    @DecimalMin(value = "0.0", message = "La nota minima es 0")
    @DecimalMax(value = "100.0", message = "La nota maxima es 100")
    @Column(nullable = false)
    private Double valor;

    private String observacion;

    @Column(nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();

    public Nota() {}

    public Nota(Inscripcion inscripcion, Double valor, String observacion) {
        this.inscripcion = inscripcion;
        this.valor = valor;
        this.observacion = observacion;
        this.fechaRegistro = LocalDate.now();
    }

    public Long getId() { return id; }
    public Inscripcion getInscripcion() { return inscripcion; }
    public void setInscripcion(Inscripcion inscripcion) { this.inscripcion = inscripcion; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}
