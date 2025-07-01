import cl.ucn.modelo.Customer;
import cl.ucn.modelo.DiscountLog;
import cl.ucn.modelo.LoyaltyDiscountEngine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.*;
import org.mockito.*;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class LoyaltyDiscountEngineTest {
    private EntityManager em;
    private LoyaltyDiscountEngine engine;


    @Before
    public void setUp(){
        em = mock(EntityManager.class);
        EntityTransaction tx = mock(EntityTransaction.class);
        when(em.getTransaction()).thenReturn(tx);
        engine = new LoyaltyDiscountEngine(em);
    }

    @Test
    public void testDescuentoNulo(){
        Customer c =  new Customer("1", LocalDate.now(), 0, Customer.LoyaltyLevel.BASIC, false);
        assertEquals(0.0, engine.computeDiscount(c), 0.001);
    }

    @Test
    public void testClienteSilver() {
        Customer c = new Customer("2", LocalDate.now().minusYears(3), 50, Customer.LoyaltyLevel.SILVER, false);
        assertEquals(0.05, engine.computeDiscount(c), 0.001);
    }

    @Test
    public void testAntiguedadMayor5Anios() {
        Customer c = new Customer("3", LocalDate.now().minusYears(6), 10, Customer.LoyaltyLevel.GOLD, false);
        assertEquals(0.15, engine.computeDiscount(c), 0.001);
    }

    @Test
    public void testMasDe100Ordenes() {
        Customer c = new Customer("4", LocalDate.now(), 101, Customer.LoyaltyLevel.GOLD, false);
        assertEquals(0.15, engine.computeDiscount(c), 0.001);
    }

    @Test
    public void testPromocionActiva() {
        Customer c = new Customer("5", LocalDate.now(), 0, Customer.LoyaltyLevel.BASIC, true);
        assertEquals(0.10, engine.computeDiscount(c), 0.001);
    }

    @Test
    public void testDescuentoMaximo() {
        Customer c = new Customer("6", LocalDate.now().minusYears(10), 150, Customer.LoyaltyLevel.PLATINUM, true);
        assertEquals(0.30, engine.computeDiscount(c), 0.001); // No excede el tope
    }

    @Test
    public void testRegistroEnLogs() {
        Customer c = new Customer("7", LocalDate.now(), 0, Customer.LoyaltyLevel.BASIC, false);
        engine.computeDiscount(c);
        verify(em).persist(Mockito.any(DiscountLog.class));
    }

    @Test
    public void testBusquedaPorId() {
        Customer c = new Customer("8", LocalDate.now(), 0, Customer.LoyaltyLevel.SILVER, false);
        when(em.find(Customer.class, "8")).thenReturn(c);
        double d = engine.computeDiscountById("8");
        assertEquals(0.05, d, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcepcionPorClienteNulo() {
        engine.computeDiscount(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcepcionPorIdFaltante() {
        Customer c = new Customer(null, LocalDate.now(), 0, Customer.LoyaltyLevel.BASIC, false);
        engine.computeDiscount(c);
    }
}
