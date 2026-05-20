package com.techlab.articulo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.techlab.articulo.interfaces.Identificable;
import com.techlab.articulo.model.Categoria;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe ser GENÉRICA.
 *
 * Debe modelarse así:
 * Repositorio<T extends Identificable>
 *
 * ¿Qué significa eso?
 * Que podrá trabajar con distintos tipos de objetos, siempre que esos
 * objetos tengan código.
 *
 * EJEMPLOS DE USO ESPERADOS
 * ------------------------------------------------------------
 * - Repositorio<Categoria>
 * - Repositorio<Articulo>
 *
 * ESTA CLASE DEBE GUARDAR LOS DATOS EN MEMORIA
 * ------------------------------------------------------------
 * Usando:
 * - ArrayList<T>
 *
 * MÉTODOS MÍNIMOS ESPERADOS
 * ------------------------------------------------------------
 * - agregar(T objeto)
 * - listar()
 * - buscarPorCodigo(int codigo)
 * - eliminar(T objeto)
 * - estaVacio()
 *
 * OBJETIVO DIDÁCTICO
 * ------------------------------------------------------------
 * Esta clase prepara el terreno para entender luego estructuras como:
 * JpaRepository<T, ID> en Spring Boot.
 */
public class Repositorio<T extends Identificable> {

    private ArrayList<T> lista = new ArrayList<>();

    // TODO:
    // Implementar método agregar.
    public void agregar(T item) {
        lista.add(item);
    }

   

    // TODO:
    // Implementar método listar.
    public List<T> listar() {
        return lista;
    }

    // TODO:
    // Implementar método buscarPorCodigo.
        public T buscarPorCodigo(int codigo) {
        return lista.stream()
            .filter(item -> item.getCodigo() == codigo)
            .findFirst()
            .orElse(null);
    }


    // TODO:
    // Implementar método eliminar.
    public void eliminar(T item) {
        lista.remove(item);
    }

    // TODO:
    // Implementar método estaVacio.
    public boolean estaVacio() {
        return lista.isEmpty();
    }
}
