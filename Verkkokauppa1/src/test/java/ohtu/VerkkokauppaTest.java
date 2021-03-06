package ohtu;
import ohtu.verkkokauppa.*;
import org.junit.*;
import static org.mockito.Mockito.*;
public class VerkkokauppaTest {

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void asioitiTesti1() {


        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);
        //aloitetaan asiointi, koriin lisätään tuote, jota varastossa on ja suoritetaan ostos,eli kutsutaan metodia kaupan tilimaksu().
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        //  varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla,tilinumerolla ja summalla
        // mutta verify:ssä on tarkastettava että parametreilla on oikeat arvot

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }

    @Test
    public void asioitiTesti2() {


        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Fink Bräu I", 5));
        when(varasto.saldo(2)).thenReturn(10);
        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        //  varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla,tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));
    }

    @Test
    public void asioitiTesti3() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        //  varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla,tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(10));
    }

    @Test
    public void asioitiTesti4() {
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Fink Bräu I", 5));
        when(varasto.saldo(2)).thenReturn(0);
        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        //  varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla,tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }
    @Test
    public void asioitiTesti5(){
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Fink Bräu I", 2));
        when(varasto.saldo(2)).thenReturn(1);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "Porter", 11));
        when(varasto.saldo(3)).thenReturn(1);
        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();

        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa

        k.tilimaksu("pekka", "12345");
        //  varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla,tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"),eq(42),eq("12345"),anyString(),eq(5));

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("mas", "12345");
        verify(pankki).tilisiirto(eq("mas"),eq(42),eq("12345"),anyString(),eq(2));
        k.aloitaAsiointi();
        k.lisaaKoriin(3);
        k.tilimaksu("massi", "123465");
        verify(pankki).tilisiirto(eq("massi"),eq(42),eq("123465"),anyString(),eq(11));
    }
    @Test
    public void asioitiTesti6(){
        // luodaan ensin mock-oliot
        Pankki pankki = mock(Pankki.class);

        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).
                thenReturn(1).
                thenReturn(2).
                thenReturn(3);
        Varasto varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Fink Bräu I", 10));
        when(varasto.saldo(2)).thenReturn(1);
        // sitten testattava kauppa
        Kauppa k = new Kauppa(varasto, pankki, viite);
        // tehdään ostokset
        k.aloitaAsiointi();

        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa

        k.tilimaksu("pekka", "12345");
        //  varmistettava että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla,tilinumerolla ja summalla
        verify(pankki).tilisiirto(eq("pekka"),eq(1),eq("12345"),anyString(),eq(5));

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("mas", "12345");
        verify(pankki).tilisiirto(eq("mas"),eq(2),eq("12345"),anyString(),eq(10));
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("mas", "12345");
        verify(pankki).tilisiirto(eq("mas"),eq(3),eq("12345"),anyString(),eq(15));
    }
}

