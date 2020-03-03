package ee.tieto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.SQLException;

@RestController
@EnableAutoConfiguration
public class XteeStubApplication {
	static transient final Logger logger = LoggerFactory.getLogger(XteeStubApplication.class);

	static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SpringApplication.run(XteeStubApplication.class, args);
	}

	private static String xmlFile2String(String fileName) {

		String st = null;

		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			InputSource is = new InputSource(fileName);
			Document document = docBuilderFactory.newDocumentBuilder().parse(is);
			StringWriter sw = new StringWriter();
			Transformer serializer = TransformerFactory.newInstance().newTransformer();
			serializer.transform(new DOMSource(document), new StreamResult(sw));
			st = sw.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;

	}

	private String getRequestBody(final HttpServletRequest request) {
		final StringBuilder builder = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			if (reader == null) {
				logger.debug("Request body could not be read because it's empty.");
				return null;
			}
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} catch (final Exception e) {
			logger.trace("Could not obtain the saml request body from the http request", e);
			return null;
		}
	}

	@RequestMapping(value = "/cgi-bin/consumer_proxy")
	public String home(HttpServletRequest req, HttpServletResponse res) throws IOException, URISyntaxException, SQLException {
		res.setHeader("Content-Type", "application/xop+xml;charset=utf-8;type=\"text/xml\"");
		res.setHeader("Content-ID", "<http://tempuri.org/0>");
		res.setHeader("Content-Transfer-Encoding", "2bit");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8"));
		if (getRequestBody(req).contains("<ainultKontroll>true</ainultKontroll>")) {
			writer.write(xmlFile2String("src/main/resources/kontrolli.xml"));
		} else {
			writer.write(xmlFile2String("src/main/resources/jousta.xml"));
		}
		writer.flush();
		writer.close();
		res.getOutputStream().flush();
		res.getOutputStream().close();
		return "";
	}
}
