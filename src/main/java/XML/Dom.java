package XML;


import Person.Person;
import Repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Dom {
    private final static Logger logger = LogManager.getLogger(Dom.class);

    public static void toXML(Repository rep) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element repository = document.createElement(rep.getClass().getSimpleName());
            document.appendChild(repository);


            for (int i = 0; i < rep.size; i++) {
                Element person = document.createElement(rep.getMasPersons()[i].getClass().getSimpleName());
                repository.appendChild(person);
                person.setAttribute("id", rep.getMasPersons()[i].getId().toString());

                Element fullName = document.createElement("fullName");
                fullName.appendChild(document.createTextNode(rep.getMasPersons()[i].getFullName()));
                person.appendChild(fullName);

                Element dateOfBirth = document.createElement("dateOfBirth");
                dateOfBirth.appendChild(document.createTextNode(rep.getMasPersons()[i].getDateOfBirth().toString()));
                person.appendChild(dateOfBirth);

                Element gender = document.createElement("gender");
                gender.appendChild(document.createTextNode(rep.getMasPersons()[i].getGender()));
                person.appendChild(gender);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("file.xml"));
            transformer.transform(domSource, streamResult);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public static Repository fromXML(String filePath) {
        try {
            Repository rep = new Repository();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));

            NodeList personElements = document.getElementsByTagName(Person.class.getSimpleName());

            for (int i = 0; i < personElements.getLength(); i++) {
                rep.add(Dom.getPerson(personElements.item(i)));
            }
            return rep;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    private static Person getPerson(Node node) {
        Person person = new Person();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            person.setId(Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue()));
            person.setFullName(getTagValue("fullName", element));
            person.setGender(getTagValue("gender", element));
            person.setDateOfBirth(new LocalDate(getTagValue("dateOfBirth", element)));
        }

        return person;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
