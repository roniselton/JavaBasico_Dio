import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import roniselton.Contador;
import roniselton.ParametroInvalidoException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestContador {

    @AfterAll
    static void metodoAfterAll()
    {
        System.out.println( "Depois Tudo After All" );
    }

    @BeforeAll
    static void metodoBeforeAll()
    {
        System.out.println( "Preparando Tudo After All" );
    }

    @AfterEach
    public void metodoAfter()
    {
        System.out.println( "Depois Tudo De novo" );
    }

    @BeforeEach
    public void metodoBefore()
    {
        System.out.println( "Preparando Denovo" );
    }

    @Test
    @Order(5)
    public void testContador(){
        System.out.println("Executando testContador");
        try {
            boolean teste = true;
            Contador.contar( 2 ,4);
            Assertions.assertTrue(teste);
        } catch (ParametroInvalidoException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void testContadorThrow(){
        System.out.println("Executando testContadorThrow");
        Assertions.assertThrows( ParametroInvalidoException.class , () ->
            Contador.contar( 4 , 2)
         );

    }

    @Test
    @Order(3)
    public void testContadorNotThrow(){
        System.out.println("Executando testContadorNotThrow");
        Assertions.assertDoesNotThrow(  () ->
                Contador.contar( 2 , 8)
        );


    }

}
