package com.pushtechnology.diffusion.wireshark;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class WiresharkOutputCaptor {

    private WiresharkOutputCaptor() {
    }

    public static Document captureOutput(String testFile) throws IOException, InterruptedException, ParserConfigurationException, SAXException {
        final Runtime rt = Runtime.getRuntime();
        final String cmd = "tshark -r " + testFile + " -T pdml";
        System.out.println("Executing: " + cmd);
        Process proc = rt.exec(cmd);
        final InputStream stdin = proc.getInputStream();
        proc.waitFor();

        final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.parse(stdin);
    }

    public static Element getChildElementByNameAttribute(Element parentNode, String nameValue) {
        final NodeList children = parentNode.getChildNodes();
        final int length = children.getLength();
        for (int i = 0; i < length; i++)
        {
            final Node node = children.item(i);
            if (node instanceof Element) {
                final Element element = (Element)children.item(i);
                if (nameValue.equals(element.getAttribute("name"))) {
                    return element;
                }
            }
        }
        throw new NoSuchElementException("No child element with the name " + nameValue);
    }

    public static List<Element> getChildElementsByNameAttribute(Element parentNode, String nameValue) {
        final List<Element> result = new ArrayList<Element>();
        final NodeList children = parentNode.getChildNodes();
        final int length = children.getLength();
        for (int i = 0; i < length; i++)
        {
            final Node node = children.item(i);
            if (node instanceof Element) {
                final Element element = (Element)children.item(i);
                if (nameValue.equals(element.getAttribute("name"))) {
                    result.add(element);
                }
            }
        }
        return result;
    }
}
