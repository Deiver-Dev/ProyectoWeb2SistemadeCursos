package teccr.justdoitcloud.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inscripciones", uniqueConstraints = @UniqueConstraint(columnNames = {"estudiante_id", "curso_id"}))
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private LocalDate fechaInscripcion = LocalDate.now();

    public enum Estado { ACTIVA, COMPLETADA, CANCELADA }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.ACTIVA;

    public Inscripcion() {}

    public Inscripcion(Usuario estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaInscripcion = LocalDate.now();
        this.estado = Estado.ACTIVA;
    }

    public Long getId() { return id; }
    public Usuario getEstudiante() { return estudiante; }
    public void setEstudiante(Usuario estudiante) { this.estudiante = estudiante; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public LocalDate getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDate fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
}
