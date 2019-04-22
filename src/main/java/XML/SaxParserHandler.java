package XML;

import Person.Person;
import Repository.Repository;
import org.joda.time.LocalDate;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaxParserHandler extends DefaultHandler {
    private Repository repository = null;
    private Person person = null;
    private StringBuilder data = null;


    private boolean bdateOfBirth = false;
    private boolean bfullName = false;
    private boolean bgender = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("person")) {
            String id = attributes.getValue("id");
            person = new Person();
            person.setId(Integer.parseInt(id));
            if (repository == null)
                repository = new Repository();
        } else if (qName.equalsIgnoreCase("dateOfBirth"))
            bdateOfBirth = true;
        else if (qName.equalsIgnoreCase("fullName"))
            bfullName = true;
        else if (qName.equalsIgnoreCase("gender"))
            bgender = true;

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (bdateOfBirth) {
            person.setDateOfBirth(new LocalDate(data.toString()));
            bdateOfBirth = false;
        } else if (bfullName) {
            person.setFullName(data.toString());
            bfullName = false;
        } else if (bgender) {
            person.setGender(data.toString());
            bgender = false;
        }

        if (qName.equalsIgnoreCase("person"))
            repository.add(person);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }

    public static Repository fromXML(String filePath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SaxParserHandler handler = new SaxParserHandler();
            saxParser.parse(new File(filePath), handler);
            return handler.repository;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
