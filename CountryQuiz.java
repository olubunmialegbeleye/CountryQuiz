/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice;

import java.util.Scanner;
import java.io.IOException;
import java.util.Random;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author BUNMI
 */
public class CountryQuiz {

    public static void main(String[] args) {
        int score = 0, rand = 0;
        String countryName, capital, user_in;
        String xmlFilePath = "C:\\Users\\BUNMI\\Desktop\\countries.xml";
        Scanner in = new Scanner(System.in);
        Random randomNumbers = new Random();
        NodeList nList = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFilePath);
            nList = document.getElementsByTagName("country");
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(CountryQuiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        int life = 3;
        do {
            rand = randomNumbers.nextInt(nList.getLength());
            Node nNode = nList.item(rand);
            Element eElement = (Element) nNode;
            countryName = eElement.getAttribute("name").split(",")[0];
            capital = eElement.getAttribute("capital").split(",")[0];
            System.out.println(countryName + ":");
            user_in = in.next();
            if (!user_in.equals(capital)) {
                life--;
                System.out.printf("WRONG. The capital of %s is %s. You have %d lives left.%n", countryName, capital, life);

            } else {
                score++;
            }

        } while (life > 0);
        System.out.printf("GAME OVER. Your score is: %d%n", score);
    }
}