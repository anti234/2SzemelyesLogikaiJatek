package Tests;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import xmlFeldolgozas.Eredmeny;
import xmlFeldolgozas.EredmenyKezelo;

public class EredmenyKezeloTest {

    @Before
    public void setUp() {

        try {
            File xml = new File(getClass().getResource("/xml/VegEredmenyekTest.xml").getFile());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element element = doc.createElement("Eredmenyek");
            doc.appendChild(element);
            DOMSource source = new DOMSource(doc);
            TransformerFactory tfact = TransformerFactory.newInstance();
            Transformer trans = tfact.newTransformer();
            StreamResult result = new StreamResult(xml);
            trans.transform(source, result);
        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(EredmenyKezeloTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void eredmenyListaTest_eredmenyHozzaadasaTest() {
        EredmenyKezelo eredmenykezelo = new EredmenyKezelo("/xml/VegEredmenyekTest.xml");
        eredmenykezelo.eredmenyHozzaadasa("kekj", "sargaj", "sargaj", 32);
        Eredmeny eredmeny = eredmenykezelo.eredmenyLista().get(0);
        assertEquals(eredmeny.getKekJatekosNeve(), "kekj");
        assertEquals(eredmeny.getSargaJatekosNeve(), "sargaj");
        assertEquals(eredmeny.getNyeroJatekosNeve(), "sargaj");
        assertEquals(eredmeny.getLepesszam(), "32");
        
        eredmenykezelo.eredmenyHozzaadasa("kekj2", "sargaj2", "sargaj2", 322);
        eredmeny = eredmenykezelo.eredmenyLista().get(1);
        assertEquals(eredmeny.getKekJatekosNeve(), "kekj2");
        assertEquals(eredmeny.getSargaJatekosNeve(), "sargaj2");
        assertEquals(eredmeny.getNyeroJatekosNeve(), "sargaj2");
        assertEquals(eredmeny.getLepesszam(), "322");
    }
}
