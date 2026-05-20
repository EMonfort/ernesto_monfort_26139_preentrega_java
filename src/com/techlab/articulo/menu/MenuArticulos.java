package com.techlab.articulo.menu;

import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.repository.Repositorio;
import com.techlab.articulo.utils.Secuencias;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Menu y encargarse del CRUD de artículos.
 *
 * Debe trabajar con:
 * - Repositorio<Articulo>
 * - Repositorio<Categoria>
 *
 * ¿Por qué necesita también categorías?
 * Porque un artículo debe asociarse a una categoría ya existente.
 *
 * FUNCIONALIDADES ESPERADAS
 * ------------------------------------------------------------
 * 1) Ingresar artículo
 * 2) Listar artículos
 * 3) Consultar un artículo por código
 * 4) Modificar un artículo
 * 5) Eliminar un artículo
 * 0) Volver
 *
 * REQUISITOS IMPORTANTES
 * ------------------------------------------------------------
 * - Antes de crear un artículo, debe verificarse que existan categorías.
 * - Debe preguntarse qué tipo de artículo se quiere crear:
 *   - electrónico
 *   - alimenticio
 * - Debe pedirse:
 *   - nombre
 *   - precio
 *   - categoría por código
 * - Si es electrónico:
 *   - garantía en meses
 * - Si es alimenticio:
 *   - días para vencimiento
 *
 * VALIDACIONES
 * ------------------------------------------------------------
 * - nombre no vacío
 * - precio no negativo
 * - categoría existente
 * - garantía no negativa
 * - días para vencimiento no negativos
 *
 * SUGERENCIA DE MÉTODOS
 * ------------------------------------------------------------
 * - ingresarArticulo()
 * - listarArticulos()
 * - consultarArticulo()
 * - modificarArticulo()
 * - eliminarArticulo()
 * - pedirCategoriaExistente()
 * - pedirNombreArticulo()
 * - pedirPrecioArticulo()
 * - pedirGarantia()
 * - pedirDiasParaVencimiento()
 */
public class MenuArticulos extends Menu {

    private Repositorio<Articulo> repositorioArticulos;
    private Repositorio<Categoria> repositorioCategorias;

    public MenuArticulos(Scanner scanner, Repositorio<Articulo> repositorioArticulos, Repositorio<Categoria> repositorioCategorias) {
        super(scanner);
        this.repositorioArticulos = repositorioArticulos;
        this. repositorioCategorias = repositorioCategorias;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ ARTÍCULOS ---");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar artículo");
        System.out.println("4 - Modificar artículo");
        System.out.println("5 - Eliminar artículo");
        System.out.println("0 - Volver");
    }

    @Override
    public void ejecutar() {
        // TODO:
        // Implementar el loop del menú y llamar a los métodos correspondientes.
        String opcion;
        do {
            mostrarMenu();

            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":          
                    ingresarArticulo();          
                    break;
                case "2":              
                    listarArticulos();      
                    break;
                case "3":             
                    consultarArticuloPorCodigo();       
                    break;
                case "4":                    
                    modificarArticulo();
                    break;
                case "5":               
                    eliminarArticulo();     
                    break;
                case "0":
                    System.out.println("Volviendo al menú principal");
                    return;
                default:
                    System.out.println("Opción incorrecta, intentar nuevamente.");
                    break;
            }

        } while (opcion != "0");
    }

    // TODO:
    // Implementar todos los métodos del CRUD de artículos.
    private void ingresarArticulo() {
        System.out.println("\n--- INGRESAR ARTÍCULO ---");

        if (repositorioCategorias.estaVacio()) {
            System.out.println("No se puede crear un articulo, porque aún no hay categorías ingresadas");
            return;
        }

        System.out.println("1 - Artículo electrónico");
        System.out.println("2 - Artículo alimenticio");

        String tipoArticulo;
        do {
            System.out.print("Ingrese el tipo de articulo (1 -2): ");
            tipoArticulo = scanner.nextLine();
            if (!tipoArticulo.equals("1") && !tipoArticulo.equals("2")) {
                System.out.println("Elija una opción válida (1 - 2)");
            }
        } while (!tipoArticulo.equals("1") && !tipoArticulo.equals("2"));

        int codigo = Secuencias.generarCodigoArticulo();

        System.out.print("Ingrese el nombre del artículo: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el precio del artículo: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        listarCategoriasInterno();
        Categoria categoria = pedirCategoriaExistente();

        Articulo articulo;

        if (tipoArticulo == "1") {
            System.out.print("Ingresar la garantía en meses: ");
            int garantiaMeses = scanner.nextInt();
            scanner.nextLine();

            articulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
        } else {
            System.out.print("Ingresar los días para el vencimiento: ");
            int diasParaVencimiento = scanner.nextInt();
            scanner.nextLine();

            articulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasParaVencimiento);
        }

        repositorioArticulos.agregar(articulo);

        System.out.println("Artículo ingresado correctamente.");
        System.out.println(articulo);
    }

    public void listarArticulos() {
        System.out.println("Lista de Artículos:");

        if (repositorioArticulos.estaVacio()) {
            System.out.println("No hay artículos para mostrar.");
            return;
        }

        for (Articulo articulo : repositorioArticulos.listar()) {
            System.out.println(articulo);
        }
    }

    private void consultarArticuloPorCodigo() {
        System.out.println("Consultar Articulo");

        System.out.print("Ingresar el código del artículo a consultar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }

        System.out.println("Artículo encontrada:");
        System.out.println(articulo);
    }

    private void modificarArticulo() {
        System.out.println("Modificar Artículo:");


        System.out.print("Ingresar el código del artículo a consultar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("Artículo no encontrado.");
            return;
        }

        System.out.print("Ingresar el nuevo nombre del artículo: ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Ingresar el nuevo precio del artículo: ");
        double nuevoPrecio = scanner.nextDouble();
        scanner.nextLine();

        listarCategoriasInterno();
        Categoria nuevaCategoria = pedirCategoriaExistente();

        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;

            System.out.println("Ingresar la nueva garantía en meses: ");
            int nuevaGarantia = scanner.nextInt();
            scanner.nextLine();

            electronico.setGarantiaMeses(nuevaGarantia);
        }

        if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;

            System.out.println("Ingresar los nuevos días para el vencimiento: ");
            int nuevosDias = scanner.nextInt();

            alimenticio.setDiasParaVencimiento(nuevosDias);
        }

        System.out.println("Artículo modificado.");
        System.out.println(articulo);
    }

    public void eliminarArticulo() {
        System.out.println("Eliminar Artículo:");

        System.out.print("Ingresar el código del artículo a eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }    
        
        repositorioArticulos.eliminar(articulo);
        System.out.println("Categoría eliminada.");
    }

    private void listarCategoriasInterno() {
        System.out.println("Categorías:");

        for (Categoria categoria : repositorioCategorias.listar()) {
            System.out.println(categoria);
        }
    }

    private Categoria pedirCategoriaExistente() {
        while (true) {
            System.out.print("Ingrese el código de la categoría: ");
            int codigoCategoria = scanner.nextInt();
            scanner.nextLine();
            Categoria categoria = repositorioCategorias.buscarPorCodigo(codigoCategoria);
            if (categoria != null) {
                return categoria;
            }
            System.out.println("Categoría incorrecta. Inténtelo nuevamente.");
        }   
    }
}
