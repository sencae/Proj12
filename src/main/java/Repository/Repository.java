package Repository;


import Annotations.SorterField;
import Comparators.*;
import Injector.Injector;
import Person.Person;
import RepositorySearcher.*;
import RepositorySorters.*;
import XML.Dom;
import XML.SaxParserHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;


@XmlRootElement(name = "repository")
public class Repository {
    private final static Logger logger = LogManager.getLogger(Repository.class);
    @SorterField
    private Isorter sorter;

    final private int DEFAULT_SIZE = 10;
    public int size;
    @XmlElement(name = "person")
    private Person[] masPersons = new Person[DEFAULT_SIZE];

    public Repository() {
    }

    public Repository(Isorter s) {
        this.sorter = s;
    }

    /**
     * @param index index of person to remove
     */
    public void removePerson(int index) {
        try {
            size--;
            if (index == 0) {
                Person[] newMasPersons = new Person[masPersons.length - 1];
                System.arraycopy(masPersons, 1, newMasPersons, 0, newMasPersons.length);
                masPersons = newMasPersons;
            } else if (index == masPersons.length - 1) {
                Person[] newMasPersons = new Person[masPersons.length - 1];
                if (newMasPersons.length >= 0)
                    System.arraycopy(masPersons, 0, newMasPersons, 0, newMasPersons.length);
                masPersons = newMasPersons;
            } else {
                Person[] newMasPersons = new Person[masPersons.length - 1];
                for (int i = 0; i < masPersons.length; i++) {
                    if (i < index)
                        newMasPersons[i] = masPersons[i];
                    else if (i == index) {
                        continue;
                    } else
                        newMasPersons[i - 1] = masPersons[i];

                }
                masPersons = newMasPersons;
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error(e.toString());
            for (StackTraceElement s : e.getStackTrace())
                logger.error(s.toString());
        }
    }

    /**
     * @param p Person object
     */
    public void add(Person p) {
        if (masPersons.length == size) {
            masPersons = resize();
        }
        masPersons[size] = p;
        logger.info("add new " + p.toString());
        this.size++;
    }

    /**
     * @return array of persons in repository
     */
    public Person[] getMasPersons() {
        Person[] result = new Person[size];
        System.arraycopy(masPersons, 0, result, 0, result.length);
        return result;
    }

    /**
     * @return array with new size
     */
    private Person[] resize() {
        Person[] newMasPerson = new Person[size + 1];
        System.arraycopy(masPersons, 0, newMasPerson, 0, masPersons.length);
        return newMasPerson;
    }


    /**
     * @param index index of person to take
     */
    public Integer getAgeOfPerson(int index) {
        return masPersons[index].findAge();
    }

    /**
     * @param comparator comparator to sort
     */
    public void sortBy(iPersonComparator comparator) {
        sorter.sort(masPersons, comparator);
    }

    private Repository search(iPersonChecker p, Object value) {
        Repository result = new Repository();
        for (int i = 0; i < size; i++) {
            if (p.check(masPersons[i], value))
                result.add(masPersons[i]);
        }
        return result;
    }

    public Repository searchByFio(String fio) {
        return search(new FioPersonChecker(), fio);
    }

    public Repository searchByGender(Character gender) {
        return search(new GenderPersonChecker(), gender);
    }

    public Repository searchByDateOfBirth(LocalDate dateOfBirth) {
        return search(new DateOfBirthChecker(), dateOfBirth);
    }

    public static void main(String[] args) {
        Repository rep = Injector.inject(new Repository());
        //rep.add(new Person(0, new LocalDate(2000, 12, 12), "fullName", 'm'));
        //rep.add(new Person(1, new LocalDate(2001, 12, 12), "fullName", 'f'));
        /*JAXB*/
//        try {
//            File file = new File("file.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Repository.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//            // output pretty printed
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//            jaxbMarshaller.marshal(rep, file);
//            jaxbMarshaller.marshal(rep, System.out);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }

//        try {
//
//            File file = new File("file.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Repository.class);
//
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            Repository repository = (Repository) jaxbUnmarshaller.unmarshal(file);
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }

        /*SAX*/

        //  rep =  SaxParserHandler.fromXML("file.xml");
//        try {
//            File file = new File("file.xml");
//            TransformerFactory tf = TransformerFactory.newInstance();
//            if (!tf.getFeature(SAXTransformerFactory.FEATURE)) {
//                throw new RuntimeException(
//                        "Did not find a SAX-compatible TransformerFactory.");
//            }
//            SAXTransformerFactory stf = (SAXTransformerFactory) tf;
//            TransformerHandler th = stf.newTransformerHandler();
//            th.setResult(new StreamResult(file));
//
//            th.startDocument();
//            AttributesImpl fieldAttrs = new AttributesImpl();
//
//            th.startElement("", "", "repository", null);
//            for(int i =0; i<rep.size;i++){
//                fieldAttrs.addAttribute("", "", "id", "", rep.masPersons[i].getId().toString());
//                th.startElement("", "", "person", fieldAttrs);
//                th.startElement("","","dateOfBirth",null);
//                th.characters(rep.masPersons[i].getDateOfBirth().toString().toCharArray(),
//                        0,rep.masPersons[i].getDateOfBirth().toString().length());
//                th.endElement("","","dateOfBirth");
//                th.startElement("","","fullName",null);
//                th.characters(rep.masPersons[i].getFullName().toCharArray(),
//                        0,rep.masPersons[i].getFullName().length());
//                th.endElement("","","fullName");
//                th.startElement("","","gender",null);
//                th.characters(rep.masPersons[i].getGender().toCharArray(),
//                        0,rep.masPersons[i].getGender().length());
//                th.endElement("","","gender");
//                th.endElement("","","person");
//            }
//            th.endElement("", "", "repository");
//            th.endDocument();
//        }catch (Exception ex)
//        {
//            logger.error(ex.getMessage());
//        }

        /*DOM*/
        //rep = Dom.fromXML("file.xml");
        // Dom.toXML(rep);

    }
}



