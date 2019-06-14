import dyrehage.Hunnindivid;
import dyrehage.RovdyrFabrikk;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;

public class JUnit{
    private static RovdyrFabrikk instance;

    @BeforeClass
    public static void setUpClass() throws Exception {
// kjøres en gang  - før alle testmetoder kjøres
    }
    @AfterClass
    public static void tearDownClass() throws Exception {
// kjøres en gang - etter alle testmetoder er ferdige
    }
    @Before
    public void setUp()throws Exception {
// metoden kjøres før hver test
// initier objekter – legg inn kjente  verdier- ol..
        instance = new RovdyrFabrikk();
    }
    @After
    public void tearDown() {
// metoden kjøres etter hver test
// frigjør objekter
    }
    // Test av metoden getNavn()
    @Test
    public void getNavn() throws Exception{
        Hunnindivid binne = (Hunnindivid) instance.nyBinne(20191002, "juni", "20120902", "Bur 10", 10);
        System.out.println("getNavn");
        String expResult = "juni";
        String result = binne.getNavn();
        assertEquals(expResult, result);
    }

    // Test av metoden getFdato()
    @Test
    public void getFdato() throws Exception{
        Hunnindivid binne = (Hunnindivid) instance.nyBinne(20191002, "juni", "20120902", "Bur 10", 10);
        System.out.println("getFdato");
        int expResult = 20120902;
        int result = binne.getFdato();
        assertEquals(expResult, result);
    }

    // Test av metoden getAlder()
    @Test
    public void getAlder() throws Exception{
        Hunnindivid binne = (Hunnindivid) instance.nyBinne(20191002, "juni", "20120902", "Bur 10", 10);
        System.out.println("getAlder");
        int expResult = 7;
        int result = binne.getAlder();
        assertEquals(expResult, result);
    }

    // Test av metoden getAdresse()
    @Test
    public void getAdresse() throws Exception{
        Hunnindivid binne = (Hunnindivid) instance.nyBinne(20191002, "juni", "20120902", "Bur 10", 10);
        System.out.println("getAdresse");
        String expResult = "Bur 10";
        String result = binne.getAdresse();
        assertEquals(expResult, result);
    }

    // Test av metoden getAntallKull()
    @Test
    public void getAntallKull() throws Exception{
        Hunnindivid binne = (Hunnindivid) instance.nyBinne(20191002, "juni", "20120902", "Bur 10", 10);
        System.out.println("getAntallKull");
        int expResult = 10;
        int result = binne.getAntKull();
        assertEquals(expResult, result);
    }

    // Test av metoden leggTilKull()
    @Test
    public void leggTilKull() throws Exception{
        Hunnindivid binne = (Hunnindivid) instance.nyBinne(20191002, "juni", "20120902", "Bur 10", 10);
        System.out.println("leggTilKull");
        int expResult = 20;
        binne.leggTilKull(10);
        int result = binne.getAntKull();
        assertEquals(expResult, result);
    }

    @Test
    public void leggTilNyttKull() throws Exception{
        Hunnindivid binne = (Hunnindivid) instance.nyBinne(20191002, "juni", "20120902", "Bur 10", 10);
        System.out.println("leggTilNyttKull");
        int expResult = 11;
        binne.leggTilNyttKull();
        int result = binne.getAntKull();
        assertEquals(expResult, result);
    }

    // Test av metoden getAlder()
    /*@Test
    public void getAntallInnbyggere() throws Exception{
        int expResult = 6000;
        int result = instance.getAntallInnbyggere();
        assertEquals(expResult, result);
    }

    @Test
    public void getByerMedPlass() throws Exception{
        assertArrayEquals(byer, instance.getByerMedPlass(500));
    }*/

}