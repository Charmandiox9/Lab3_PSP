package cl.ucn.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Representa un registro (log) de la aplicación de descuentos a un cliente.
 * Esta entidad es persistida en la base de datos con el nombre de tabla "logs".
 */
@Entity
@Table(name = "logs")
public class DiscountLog {

    /**
     * Identificador único del log (clave primaria autogenerada).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID del cliente al que se le aplicó el descuento.
     */
    @Column(name = "customer_id")
    private String customerId;

    /**
     * Fecha y hora en que se registró la aplicación del descuento.
     */
    private LocalDateTime timestamp;

    /**
     * Valor del descuento aplicado (expresado como decimal entre 0 y 1).
     */
    @Column(name = "discount_applied")
    private double discountApplied;

    /**
     * Constructor vacío requerido por JPA.
     */
    public DiscountLog() {}

    /**
     * Constructor que inicializa un log de descuento con cliente y valor aplicado.
     * El timestamp se establece al momento de creación del objeto.
     *
     * @param customerId ID del cliente.
     * @param discountApplied Descuento aplicado al cliente.
     */
    public DiscountLog(String customerId, double discountApplied) {
        this.customerId = customerId;
        this.discountApplied = discountApplied;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Obtiene el ID del log.
     * @return ID del log.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del log.
     * @param id nuevo ID del log.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del cliente asociado a este log.
     * @return ID del cliente.
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Establece el ID del cliente.
     * @param customerId nuevo ID de cliente.
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Obtiene el timestamp de cuando se registró el log.
     * @return Fecha y hora del log.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece el timestamp del log.
     * @param timestamp nuevo valor de fecha y hora.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Obtiene el valor del descuento aplicado.
     * @return descuento aplicado (valor entre 0 y 1).
     */
    public double getDiscountApplied() {
        return discountApplied;
    }

    /**
     * Establece el valor del descuento aplicado.
     * @param discountApplied nuevo valor de descuento.
     */
    public void setDiscountApplied(double discountApplied) {
        this.discountApplied = discountApplied;
    }
}
