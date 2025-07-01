package cl.ucn.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Representa un cliente del sistema con atributos relacionados a fidelización y promociones.
 * Esta clase es una entidad persistente en la base de datos.
 */
@Entity
@Table(name = "customers")
public class Customer {

    /**
     * Identificador único del cliente.
     */
    @Id
    private String id;

    /**
     * Fecha en la que el cliente se unió al sistema.
     */
    @Column(name = "join_date")
    private LocalDate joinDate;

    /**
     * Número total de órdenes realizadas por el cliente.
     */
    @Column(name = "total_orders")
    private int totalOrders;

    /**
     * Nivel de lealtad del cliente.
     */
    @Enumerated(EnumType.STRING)
    private LoyaltyLevel level;

    /**
     * Indica si el cliente tiene una promoción activa.
     */
    @Column(name = "has_active_promo")
    private boolean hasActivePromo;

    /**
     * Niveles posibles de lealtad del cliente.
     */
    public enum LoyaltyLevel {
        BASIC, SILVER, GOLD, PLATINUM
    }

    /**
     * Constructor vacío requerido por JPA.
     */
    public Customer() {}

    /**
     * Constructor completo del cliente.
     *
     * @param id              Identificador único.
     * @param joinDate        Fecha de incorporación.
     * @param totalOrders     Total de órdenes realizadas.
     * @param level           Nivel de lealtad.
     * @param hasActivePromo  Si posee promoción activa.
     */
    public Customer(String id, LocalDate joinDate, int totalOrders, LoyaltyLevel level, boolean hasActivePromo) {
        this.id = id;
        this.joinDate = joinDate;
        this.totalOrders = totalOrders;
        this.level = level;
        this.hasActivePromo = hasActivePromo;
    }

    /**
     * Obtiene el ID del cliente.
     * @return id del cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     * @param id nuevo ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de incorporación del cliente.
     * @return fecha de incorporación.
     */
    public LocalDate getJoinDate() {
        return joinDate;
    }

    /**
     * Establece la fecha de incorporación del cliente.
     * @param joinDate nueva fecha.
     */
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * Obtiene el número total de órdenes del cliente.
     * @return número de órdenes.
     */
    public int getTotalOrders() {
        return totalOrders;
    }

    /**
     * Establece el número total de órdenes del cliente.
     * @param totalOrders nuevo total.
     */
    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    /**
     * Obtiene el nivel de lealtad del cliente.
     * @return nivel de lealtad.
     */
    public LoyaltyLevel getLevel() {
        return level;
    }

    /**
     * Establece el nivel de lealtad del cliente.
     * @param level nuevo nivel.
     */
    public void setLevel(LoyaltyLevel level) {
        this.level = level;
    }

    /**
     * Indica si el cliente tiene una promoción activa.
     * @return true si tiene promoción activa, false en caso contrario.
     */
    public boolean isHasActivePromo() {
        return hasActivePromo;
    }

    /**
     * Establece si el cliente tiene una promoción activa.
     * @param hasActivePromo true si tiene promoción activa.
     */
    public void setHasActivePromo(boolean hasActivePromo) {
        this.hasActivePromo = hasActivePromo;
    }
}
