package cl.ucn.interfaz;

import cl.ucn.modelo.Customer;
import cl.ucn.modelo.LoyaltyDiscountEngine;
import cl.ucn.modelo.DiscountLog;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que contiene la interfaz de línea de comandos para gestionar clientes,
 * aplicar descuentos y visualizar logs. Usa JPA para interactuar con la base de datos.
 */
public class Main {

    /**
     * Scanner para lectura de entrada desde consola.
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Fábrica de EntityManager para la unidad de persistencia "shopping".
     */
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("shopping");

    /**
     * EntityManager para operaciones sobre entidades.
     */
    private static EntityManager em = emf.createEntityManager();

    /**
     * Motor de cálculo de descuentos para clientes.
     */
    private static LoyaltyDiscountEngine discountEngine = new LoyaltyDiscountEngine(em);

    /**
     * Punto de entrada del programa. Muestra un menú iterativo para gestionar clientes,
     * aplicar descuentos, actualizar datos y visualizar logs.
     *
     * @param args argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CLIENTES Y DESCUENTOS ===");
        int opcion;
        do {
            mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine().trim());
            switch (opcion) {
                case 1 -> crearCliente();
                case 2 -> listarClientes();
                case 3 -> aplicarDescuento();
                case 4 -> verLogs();
                case 5 -> actualizarCliente();
                case 6 -> eliminarCliente();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);

        em.close();
        emf.close();
    }

    /**
     * Muestra el menú principal de opciones al usuario.
     */
    private static void mostrarMenu() {
        System.out.println("""
                
                --- Menú ---
                1. Crear cliente
                2. Ver todos los clientes
                3. Calcular descuento para cliente
                4. Ver logs de descuento
                5. Actualizar datos del cliente
                6. Eliminar cliente
                0. Salir
                Seleccione una opción:""");
    }

    /**
     * Crea un nuevo cliente a partir de los datos ingresados por el usuario y lo guarda en la base de datos.
     */
    private static void crearCliente() {
        System.out.print("ID del cliente: ");
        String id = scanner.nextLine();
        System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
        LocalDate joinDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Total de órdenes: ");
        int orders = Integer.parseInt(scanner.nextLine());
        System.out.print("Nivel (BASIC, SILVER, GOLD, PLATINUM): ");
        Customer.LoyaltyLevel level = Customer.LoyaltyLevel.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("¿Tiene promoción activa? (true/false): ");
        boolean promo = Boolean.parseBoolean(scanner.nextLine());

        Customer c = new Customer(id, joinDate, orders, level, promo);

        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        System.out.println("✅ Cliente creado.");
    }

    /**
     * Muestra en pantalla todos los clientes almacenados en la base de datos.
     */
    private static void listarClientes() {
        List<Customer> clientes = em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        System.out.println("\n--- Lista de Clientes ---");
        for (Customer c : clientes) {
            System.out.printf("• ID: %s | Ingreso: %s | Órdenes: %d | Nivel: %s | Promo: %s\n",
                    c.getId(), c.getJoinDate(), c.getTotalOrders(), c.getLevel(), c.isHasActivePromo());
        }
    }

    /**
     * Aplica el motor de cálculo de descuentos a un cliente existente e imprime el resultado.
     */
    private static void aplicarDescuento() {
        System.out.print("ID del cliente: ");
        String id = scanner.nextLine();

        try {
            double descuento = discountEngine.computeDiscountById(id);
            System.out.printf("✅ Descuento aplicado: %.2f%%\n", descuento * 100);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Cliente no encontrado.");
        }
    }

    /**
     * Lista en consola los logs de descuentos aplicados, ordenados del más reciente al más antiguo.
     */
    private static void verLogs() {
        List<DiscountLog> logs = em.createQuery("SELECT l FROM DiscountLog l ORDER BY timestamp DESC", DiscountLog.class).getResultList();
        System.out.println("\n--- Logs de Descuentos ---");
        for (DiscountLog l : logs) {
            System.out.printf("• Cliente: %s | Fecha: %s | Descuento: %.2f%%\n",
                    l.getCustomerId(), l.getTimestamp(), l.getDiscountApplied() * 100);
        }
    }

    /**
     * Permite modificar el número de órdenes, el nivel de fidelidad o la promoción activa de un cliente.
     */
    private static void actualizarCliente() {
        System.out.print("ID del cliente a actualizar: ");
        String id = scanner.nextLine();
        Customer c = em.find(Customer.class, id);
        if (c == null) {
            System.out.println("❌ Cliente no encontrado.");
            return;
        }

        System.out.print("Nuevo total de órdenes: ");
        int orders = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuevo nivel (BASIC, SILVER, GOLD, PLATINUM): ");
        Customer.LoyaltyLevel level = Customer.LoyaltyLevel.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("¿Tiene promoción activa? (true/false): ");
        boolean promo = Boolean.parseBoolean(scanner.nextLine());

        em.getTransaction().begin();
        c.setTotalOrders(orders);
        c.setLevel(level);
        c.setHasActivePromo(promo);
        em.getTransaction().commit();
        System.out.println("✅ Cliente actualizado.");
    }

    /**
     * Elimina un cliente existente de la base de datos según su ID.
     */
    private static void eliminarCliente() {
        System.out.print("ID del cliente a eliminar: ");
        String id = scanner.nextLine();
        Customer c = em.find(Customer.class, id);
        if (c == null) {
            System.out.println("❌ Cliente no encontrado.");
            return;
        }

        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
        System.out.println("🗑️ Cliente eliminado.");
    }
}
