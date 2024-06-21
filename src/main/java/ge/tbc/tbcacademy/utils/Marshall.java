package ge.tbc.tbcacademy.utils;

import jakarta.xml.bind.*;
import jakarta.xml.soap.*;
import jakarta.xml.ws.soap.SOAPFaultException;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Marshall {
    public static <T> String marshallSoapRequest(T object) {
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPPart soapPart = soapMessage.getSOAPPart();

            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(object, new DOMResult(soapPart.getEnvelope().getBody()));

            Transformer ts = TransformerFactory.newInstance().newTransformer();

            Properties properties = new Properties();
            properties.setProperty("indent", "yes");
            properties.setProperty("omit-xml-declaration", "yes");

            ts.setOutputProperties(properties);

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            ts.transform(soapMessage.getSOAPPart().getContent(), new StreamResult(output));

            return output.toString(StandardCharsets.UTF_8);
        } catch (JAXBException | SOAPException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T unmarshallResponse(String response, Class<T> object) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
            SOAPMessage message = MessageFactory.newInstance().createMessage(null, byteArrayInputStream);
            // throw SOAPFaultException
            if (message.getSOAPBody().hasFault()) {
                SOAPFault soapFault = message.getSOAPBody().getFault();
                throw new SOAPFaultException(soapFault);
            }
            Document document = message.getSOAPBody().extractContentAsDocument();
            Unmarshaller unmarshaller = JAXBContext.newInstance(object).createUnmarshaller();
            Object unmarshal = unmarshaller.unmarshal(document);
            return object.cast(unmarshal);
        }
        catch (SOAPException | JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
