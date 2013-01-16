/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Fekih
 */
public class ReadXML {
    	public static void main(String argv[]) {
            System.out.println("Welcome to Github");
            List<Book> books=new ArrayList<Book>();
	  try {
 
		File fXmlFile = new File("./bookstore-jaxb.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
 
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("book");
                System.out.println("Total of elements : " + nList.getLength());
		System.out.println("-----------------------");
                
		for (int i = 0; i < nList.getLength(); i++) {
                        
		   Node nNode = nList.item(i);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
		      Element eElement = (Element) nNode;
                      Book book=new Book();
                      book.setAuthor(getTagValue("author", eElement));
                      book.setIsbn(getTagValue("isbn", eElement));
                      book.setName(getTagValue("title", eElement));
                      book.setPublisher(getTagValue("publisher", eElement));
                      books.add(book);
		   }
		}
	  } catch (Exception e) {
		e.printStackTrace();
	  }
          System.out.println(books);
  }
 
  private static String getTagValue(String sTag, Element eElement) {
	Node node = eElement.getElementsByTagName(sTag).item(0).getChildNodes().item(0);
	return node.getNodeValue();
  }
}
