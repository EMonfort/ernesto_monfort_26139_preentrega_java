package com.techlab.articulo.model;

import com.techlab.articulo.interfaces.Calculable;
import com.techlab.articulo.interfaces.Identificable;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe ser ABSTRACTA.
 *
 * ¿Por qué?
 * Porque no queremos crear objetos "Articulo" genéricos, sino trabajar
 * con tipos concretos de artículos.
 *
 * Esta clase representa todo lo común a cualquier artículo.
 *
 * ATRIBUTOS OBLIGATORIOS
 * ------------------------------------------------------------
 * - codigo : int
 * - nombre : String
 * - precio : double
 * - categoria : Categoria
 *
 * IMPORTANTE
 * ------------------------------------------------------------
 * categoria NO debe ser String.
 * Debe ser un objeto Categoria.
 *
 * ESTA CLASE DEBE
 * ------------------------------------------------------------
 * - implementar Calculable
 * - implementar Identificable
 * - tener constructor
 * - tener getters y setters
 * - tener toString()
 * - declarar un método abstracto:
 *   String getTipoArticulo();
 *
 * OPCIONAL RECOMENDADO
 * ------------------------------------------------------------
 * Podés declarar también otro método abstracto para el detalle específico,
 * por ejemplo:
 * - String getDetalleEspecifico();
 */
public abstract class Articulo implements Identificable, Calculable {

    protected int codigo;
    protected String nombre;
    protected double precio;
    protected Categoria categoria;

    // TODO:
    // Crear constructor completo.
    public Articulo(int codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.categoria = categoria;
        this. precio = precio;
        this.nombre = nombre;
    }

    // TODO:
    // Crear getters y setters.
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (!nombre.isBlank()) {
            this.nombre = nombre.trim();
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        if (precio > 0) {
            this.precio = precio;
        }
    }

    public abstract String getTipoArticulo();

    public abstract String getDetalleEspecifico();

    @Override
    public String toString() {
        return "Artículo {" +
                "código=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio base=" + precio +
                ", categoría='" + categoria.getNombre() + '\'' +
                ", tipo='" + getTipoArticulo() + '\'' +
                ", detalle='" + getDetalleEspecifico() + '\'' +
                ", precio final=" + calcularPrecioFinal() +
                '}';
    }
}
