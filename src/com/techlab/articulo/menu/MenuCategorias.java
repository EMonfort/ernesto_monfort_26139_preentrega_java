package com.techlab.articulo.menu;

import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;
import com.techlab.articulo.utils.Secuencias;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Menu y encargarse del CRUD de categorías.
 *
 * Debe trabajar con:
 * - Repositorio<Categoria>
 * - Repositorio<Articulo>
 *
 * ¿Por qué necesita también artículos?
 * Porque antes de eliminar una categoría debe verificarse si está
 * siendo utilizada por algún artículo.
 *
 * FUNCIONALIDADES ESPERADAS
 * ------------------------------------------------------------
 * 1) Ingresar categoría
 * 2) Listar categorías
 * 3) Consultar una categoría por código
 * 4) Modificar una categoría
 * 5) Eliminar una categoría
 * 0) Volver
 *
 * VALIDACIONES
 * ------------------------------------------------------------
 * - nombre no vacío
 * - descripción no vacía
 * - no permitir categorías repetidas por nombre
 *
 * REGLA DE NEGOCIO IMPORTANTE
 * ------------------------------------------------------------
 * No se puede eliminar una categoría si existe al menos un artículo
 * asociado a ella.
 *
 * SUGERENCIA DE MÉTODOS
 * ------------------------------------------------------------
 * - ingresarCategoria()
 * - listarCategorias()
 * - consultarCategoria()
 * - modificarCategoria()
 * - eliminarCategoria()
 * - categoriaTieneArticulosAsociados(...)
 */
public class MenuCategorias extends Menu {

    private Repositorio<Categoria> repositorioCategorias;
    private Repositorio<Articulo> repositorioArticulos;

    public MenuCategorias(Scanner scanner, Repositorio<Categoria> repositorioCategorias, Repositorio<Articulo> repositorioArticulos) {
        super(scanner);
        this.repositorioCategorias = repositorioCategorias;
        this.repositorioArticulos = repositorioArticulos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ CATEGORÍAS ---");
        System.out.println("1 - Ingresar categoría");
        System.out.println("2 - Listar categorías");
        System.out.println("3 - Consultar categoría");
        System.out.println("4 - Modificar categoría");
        System.out.println("5 - Eliminar categoría");
        System.out.println("0 - Volver");
    }

    @Override
    public void ejecutar() {
        // TODO:
        // Implementar el loop del menú y llamar a los métodos correspondientes.
        String opcion;
        do {
        mostrarMenu();
        System.out.print("Ingrese una opcion: ");

        opcion = scanner.nextLine();
        switch (opcion) {
                case "1":
                    ingresarCategoria();
                    break;
                case "2":
                    listarCategorias();
                    break;
                case "3":              
                    consultarCategoriaPorCodigo();      
                    break;
                case "4":
                    modificarCategoria();               
                    break;
                case "5":   
                    eliminarCategoria();                 
                    break;
                case "0":
                    System.out.println("Volviendo al menú principal.");
                    return;
                default:
                    System.out.println("Opción incorrecta, intentar nuevamente.");
                    break;
            }


        } while (opcion != "0");


    }

    // TODO:
    // Implementar todos los métodos del CRUD de categorías.
    public void ingresarCategoria() {
        System.out.println("Ingresar Categoría:");

        int codigo = Secuencias.generarCodigoCategoria();

        System.out.print("Ingresar el nombre de la categoría: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingresar la descripción de la categoría: ");
        String descripcion = scanner.nextLine();

        Categoria categoria = new Categoria(codigo, nombre, descripcion);

        repositorioCategorias.agregar(categoria);
    }

    public void listarCategorias() {
        System.out.println("Lista de Categorías:");

        if (repositorioCategorias.estaVacio()) {
            System.out.println("No hay categorías para mostrar.");
            return;
        }

        for (Categoria categoria : repositorioCategorias.listar()) {
            System.out.println(categoria);
        }
    }

    private void consultarCategoriaPorCodigo() {
        System.out.println("Consultar Categoría");

        System.out.print("Ingresar el código de la categoría a consultar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }

        System.out.println("Categoría encontrada:");
        System.out.println(categoria);
    }

    private void modificarCategoria() {
        System.out.println("Modificar Categoría:");


        System.out.print("Ingresar el código de la categoría a consultar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }

        System.out.println("Ingrese el nuevo nombre de la categoría:");
        String nuevoNombre = scanner.nextLine();

        System.out.println("Ingrese la nueva descripción de la categoría:");
        String nuevaDescripcion = scanner.nextLine();;

        categoria.setNombre(nuevoNombre);
        categoria.setDescripcion(nuevaDescripcion);

        System.out.println("Categoría modificada:");
        System.out.println(categoria);
    }

    public void eliminarCategoria() {
        System.out.println("Eliminar Categoría:");

        System.out.print("Ingresar el código de la categoría a eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }    
        
        repositorioCategorias.eliminar(categoria);
        System.out.println("Categoría eliminada.");
    }
}
