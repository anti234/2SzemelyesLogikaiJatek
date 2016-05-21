package xmlFeldolgozas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Feldolgozza a VegEredmenyek.xml-t, továbbá új elemeket is ad hozzá.
 */
public class EredmenyKezelo {

    private final Logger logger = LoggerFactory.getLogger(EredmenyKezelo.class);
    
    private final File xml;

    public EredmenyKezelo(String xml) {
        this.xml = new File(getClass().getResource(xml).getFile());
    }
    
    /**
     * Visszaad egy eredmény listát a Vegeredmenyek.xml-ből.
     * 
     * @return eredmény lista
     */
    public List<Eredmeny> eredmenyLista() {
        List<Eredmeny> eredmenyek = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);
            Element Jatekok = doc.getDocumentElement();
            NodeList nodeList = Jatekok.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Eredmeny eredmeny = new Eredmeny(
                            elem.getElementsByTagName("Kek").item(0).getTextContent(),
                            elem.getElementsByTagName("Sarga").item(0).getTextContent(),
                            elem.getElementsByTagName("Nyert").item(0).getTextContent(),
                            Integer.valueOf(elem.getElementsByTagName("LepesSzam").item(0).getTextContent())
                    );
                    eredmenyek.add(eredmeny);
                    logger.info("Eredmények lekérdezése az xml-ből.");
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.error(ex.getMessage());
        }
        return eredmenyek;
    }

    /**
     * Hozzáad egy eredményt a Vegeredmenyek.xml-hez.
     * 
     * @param kekJatekosNeve a kék játékos neve
     * @param sargaJatekosNeve a sárga játékos neve
     * @param nyert a nyerő játékos neve
     * @param lepesSzam a megtett lépésszám
     */
    public void eredmenyHozzaadasa(String kekJatekosNeve, String sargaJatekosNeve, String nyert, int lepesSzam) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);
            Element Jatekok = doc.getDocumentElement();
            Element Jatek = doc.createElement("Eredmeny");

            Element Kek = doc.createElement("Kek");
            Kek.appendChild(doc.createTextNode(kekJatekosNeve));

            Element Sarga = doc.createElement("Sarga");
            Sarga.appendChild(doc.createTextNode(sargaJatekosNeve));

            Element Nyert = doc.createElement("Nyert");
            Nyert.appendChild(doc.createTextNode(nyert));

            Element LepesSzam = doc.createElement("LepesSzam");
            LepesSzam.appendChild(doc.createTextNode(String.valueOf(lepesSzam)));

            Jatekok.appendChild(Jatek);
            Jatek.appendChild(Kek);
            Jatek.appendChild(Sarga);
            Jatek.appendChild(Nyert);
            Jatek.appendChild(LepesSzam);

            DOMSource source = new DOMSource(doc);
            TransformerFactory tfact = TransformerFactory.newInstance();
            Transformer trans = tfact.newTransformer();
            StreamResult result = new StreamResult(xml);
            trans.transform(source, result);
            logger.info("Új eredmény beírása az xml-be.");

        } catch (TransformerConfigurationException ex) {
            logger.error(ex.getMessage());
        } catch (SAXException | IOException | ParserConfigurationException | TransformerException ex) {
            logger.error(ex.getMessage());
        }
    }
}
