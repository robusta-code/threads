package tp;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class CalculTestTest {


    @Test
    public void testHalfSuite() throws Exception {

        Calcul calcul = new Calcul();
        int a = 5;//15
        int b =3;//6

        assertTrue(calcul.halfSuite(a, b)==10);

    }

    @Test
    public void testHalfSuiteWithWait() throws Exception {

        Calcul calcul = new Calcul();
        int a = 5;//15
        int b =3;//6

        long time = System.currentTimeMillis();

        int res = calcul.halfSuiteWithWait(a, b);
        System.out.println("res is : "+res);
        assertTrue(res==10);

        long duration = System.currentTimeMillis()-time;
        assertTrue(duration < 100);
    }

    @Test
    public void testWhatHappensThere() throws Exception {

        Calcul calcul = new Calcul();
        int a = 5;//15
        int b =3;//6

        assertTrue(calcul.whatHappensThere(a, b)==10);
    }

    @Test
    public void futureTest() throws ExecutionException, InterruptedException {


        Calcul calcul = new Calcul();
        int a = 5;//15
        int b =3;//6

        assertTrue(calcul.future(a, b)==10);
    }

}
