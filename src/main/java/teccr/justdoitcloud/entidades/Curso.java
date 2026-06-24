package teccr.justdoitcloud.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del curso es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Min(value = 1, message = "Los creditos deben ser al menos 1")
    @Column(nullable = false)
    private int creditos;

    @Column(nullable = false)
    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Usuario profesor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull(message = "La categoria es obligatoria")
    private Categoria categoria;

    public Curso() {}

    public Curso(String nombre, String descripcion, int creditos, Usuario profesor, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.profesor = profesor;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public Usuario getProfesor() { return profesor; }
    public void setProfesor(Usuario profesor) { this.profesor = profesor; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
