import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLDocToString
{
    public static void main(String[] args)
    {
        final String strXML =   "<BookStore>" +

                "	<book id=\"b101\">" +
                "		<name>Java Tutorial</name>" +
                "		<price>$5.00</price>" +
                "	</book>" +

                "	<book id=\"b102\">" +
                "		<name>PHP Tutorial</name>" +
                "		<price>$4.75</price>" +
                "	</book>" +

                "	<book id=\"b103\">" +
                "		<name>Visual Bsic Tutorial</name>" +
                "		<price>$3.50</price>" +
                "	</book>" +

                "</BookStore>";

        //Call method to convert XML string content to XML Document object.
        //Now you can perform required operations on this XML doc
       // Document xmlDoc = convertStringToXMLDoc( strXML );

        //Get the first node of XML Document to validate whether XML document is build correctly
        //System.out.println("XML Doc First Node Value is : "+xmlDoc.getFirstChild().getNodeName());

        //Now converting XML Doc to String
        //String xmlOutPut=convertXMLDocumentToString(xmlDoc);
        //System.out.println("XML Doc to String: \n"+ xmlOutPut);
        String xmlOutPut = readFromFile("bookstore.xml");
        System.out.println(xmlOutPut);
        /*----------------------------------------------------------------------------------------------------------------*/
        List<Book> bookstore = ReadFile(xmlOutPut);

        bookstore.forEach(i-> System.out.println(i));
        bookstore.add(new Book("b404","It","15.03$" ));

        System.out.println("\n\n"+writeDown(bookstore));
    }

    //Following method will  to convert String to XML Document
    private static Document convertStringToXMLDoc(String strXMLValue)
    {

        try
        {
            //Create a new object of DocumentBuilderFactory
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();

            //Create an object DocumentBuilder to parse the specified XML Data
            DocumentBuilder builder = dbfactory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(strXMLValue)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static String convertXMLDocumentToString(Document xmlDoc)
    {
        //Create a new object of TransformerFactory
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer;

        try {
            transformer = transformerFactory.newTransformer();

            //Creating object of the Source document that is xml doc
            DOMSource source = new DOMSource(xmlDoc);

            StringWriter strWriter = new StringWriter();

            StreamResult stResult = new StreamResult(strWriter);

            transformer.transform(source, stResult);

            String xmlString = strWriter.getBuffer().toString();

            return xmlString;

        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<Book> ReadFile(String inputFile){
        List<Book> result = new ArrayList<>();
        String[] lines = inputFile.split("book");
        for (String line: lines) {
            if (line.contains("id")) {
                HashMap <String,String> filler = new HashMap<>();
                filler.put("price",line.substring(line.indexOf("ce>")+3, line.indexOf("</p")));
                filler.put("name", line.substring(line.indexOf("me>")+3, line.indexOf("</n")));
                filler.put("id",line.substring(line.indexOf('\"')+1,line.indexOf("\">")));
                result.add(new Book(filler));
            }
        }
        return result;
    }

    private static String readFromFile(String filename){
        String result = null;
        try {
            FileReader fr  = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            try {
                String line = reader.readLine();
                while (line!=null) {
                    result += line;
                    line = reader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static String writeDown(List<Book> inpList){
        StringBuilder protoFile = new StringBuilder();
        inpList.forEach((i)->{
            HashMap<String, String> temp = BookToHashMap(i);
            protoFile.append(String.format("<book id = \"%s\">\n", i.getId()));
            temp.forEach((k,v)-> {
                if (!(k.equals("id")))
                    protoFile.append(String.format("<%s> %s </%s>\n", k, v, k));
            });
            protoFile.append("</book>\n");
        });
        return protoFile.toString();
    }

    private static HashMap<String, String> BookToHashMap (Book b){
        HashMap<String, String> result = new HashMap<>();
        result.put("id", b.getId());
        result.put("name", b.getName());
        result.put("price", b.getPrice());
        return result;
    }

}