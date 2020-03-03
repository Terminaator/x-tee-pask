package ee.tieto;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@RestController
@EnableAutoConfiguration
@EntityScan(basePackages = "ee.mkm.ehr.domain")
public class XteeStubApplication {
	static transient final Logger logger = LoggerFactory.getLogger(XteeStubApplication.class);

	static final long serialVersionUID = 1L;
	protected HashSet _DontProxyHeaders = new HashSet();
	@Autowired
	EntityManagerFactory entityManagerFactory;

	{
		_DontProxyHeaders.add("proxy-connection");
		_DontProxyHeaders.add("connection");
		_DontProxyHeaders.add("keep-alive");
		_DontProxyHeaders.add("transfer-encoding");
		_DontProxyHeaders.add("te");
		_DontProxyHeaders.add("trailer");
		_DontProxyHeaders.add("proxy-authorization");
		_DontProxyHeaders.add("proxy-authenticate");
		_DontProxyHeaders.add("upgrade");
	}

	public static void main(String[] args) {
		SpringApplication.run(XteeStubApplication.class, args);
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


	@RequestMapping(value="/cgi-bin/consumer_proxy")
	public String home(HttpServletRequest req, HttpServletResponse res) throws IOException, URISyntaxException, SQLException {
		System.out.println(getRequestBody(req));
		res.setHeader("Content-Type", "application/xop+xml;charset=utf-8;type=\"text/xml\"");
		res.setHeader("Content-ID", "<http://tempuri.org/0>");
		res.setHeader("Content-Transfer-Encoding", "2bit");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(res.getOutputStream(), "UTF-8"));
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "    <SOAP-ENV:Header xmlns:xrd=\"http://x-road.eu/xsd/xroad.xsd\" xmlns:id=\"http://x-road.eu/xsd/identifiers\">\n"
				+ "        <xrd:client id:objectType=\"SUBSYSTEM\">\n" + "            <id:xRoadInstance>ee-test</id:xRoadInstance>\n"
				+ "            <id:memberClass>GOV</id:memberClass>\n" + "            <id:memberCode>70003158</id:memberCode>\n"
				+ "            <id:subsystemCode>ehr</id:subsystemCode>\n" + "        </xrd:client>\n"
				+ "        <xrd:service id:objectType=\"SERVICE\">\n" + "            <id:xRoadInstance>ee-test</id:xRoadInstance>\n"
				+ "            <id:memberClass>GOV</id:memberClass>\n" + "            <id:memberCode>70003098</id:memberCode>\n"
				+ "            <id:subsystemCode>ads</id:subsystemCode>\n" + "            <id:serviceCode>ADSmenadresit</id:serviceCode>\n"
				+ "            <id:serviceVersion>v1</id:serviceVersion>\n" + "        </xrd:service>\n"
				+ "        <xrd:id>ehrb68d1924c9b14e87b01978a37c1277ff</xrd:id>\n"
				+ "        <xrd:requestHash algorithmId=\"http://www.w3.org/2001/04/xmlenc#sha512\">\n"
				+ "            No177voz2PkiunMs0+pApa4Mz7sJZ+JhiNuaf06JfLRwM2s+Q0R0Hp7VOKx3wGCuaj4j+GB1nUZ7RICn6NerYw==\n"
				+ "        </xrd:requestHash>\n" + "        <xrd:userId>EE37105210216</xrd:userId>\n" + "        <xrd:protocolVersion>4.0\n"
				+ "        </xrd:protocolVersion><!-- <requestInfo xmlns=\"\"><timestamp>2020-03-03T17:31:06.811</timestamp><logged>R-646973</logged></requestInfo> -->\n"
				+ "    </SOAP-ENV:Header>\n" + "    <SOAP-ENV:Body>\n"
				+ "        <tns:ADSmenadresitResponse xmlns:tns=\"http://www.maaamet.ee\">\n"
				+ "            <joustatud>2020-03-02T18:49:05+02:00</joustatud>\n" + "            <menetlusNr>11922271</menetlusNr>\n"
				+ "            <objektid>\n" + "                <menobjekt>\n" + "                    <adsOid>EE03516069</adsOid>\n"
				+ "                    <objektiLiik>EE</objektiLiik>\n" + "                    <origTunnus>120781715</origTunnus>\n"
				+ "                    <olek>K</olek>\n"
				+ "                    <taisAadress>Harju maakond, Tallinn, Põhja-Tallinna linnaosa, Staapli tn 12</taisAadress>\n"
				+ "                    <lahiAadress>Staapli tn 12</lahiAadress>\n"
				+ "                    <oiguslikAlus>Kasutusloa taotlus nr. 2011371/00324</oiguslikAlus>\n"
				+ "                    <aluseKuupaev>2020-02-26</aluseKuupaev>\n"
				+ "                    <ruumiKuju>&lt;gml:Polygon xmlns:gml=\"http://www.opengis.net/gml\" srsName=\"EPSG:3301\"&gt;&lt;gml:exterior&gt;&lt;gml:LinearRing&gt;&lt;gml:posList&gt;541233.74\n"
				+ "                        6590621.04 541252.92 6590609.1 541260.28 6590620.9 541241.09 6590632.84 541233.75 6590621.04 541233.74 6590621.04&lt;/gml:posList&gt;&lt;/gml:LinearRing&gt;&lt;/gml:exterior&gt;&lt;/gml:Polygon&gt;\n"
				+ "                    </ruumiKuju>\n" + "                    <kujuMoodustusviis>D</kujuMoodustusviis>\n"
				+ "                    <aadressid>\n" + "                        <aadress>\n"
				+ "                            <taisAadress>Harju maakond, Tallinn, Põhja-Tallinna linnaosa, Staapli tn 12</taisAadress>\n"
				+ "                            <lahiAadress>Staapli tn 12</lahiAadress>\n"
				+ "                            <adrId>3490452</adrId>\n" + "                            <adsTase1>\n"
				+ "                                <kood>37</kood>\n" + "                                <nimetus>Harju maakond</nimetus>\n"
				+ "                                <nimetus_liigiga>Harju maakond</nimetus_liigiga>\n"
				+ "                            </adsTase1>\n" + "                            <adsTase2>\n"
				+ "                                <kood>784</kood>\n" + "                                <nimetus>Tallinn</nimetus>\n"
				+ "                                <nimetus_liigiga>Tallinn</nimetus_liigiga>\n"
				+ "                            </adsTase2>\n" + "                            <adsTase3>\n"
				+ "                                <kood>0614</kood>\n"
				+ "                                <nimetus>Põhja-Tallinna linnaosa</nimetus>\n"
				+ "                                <nimetus_liigiga>Põhja-Tallinna linnaosa</nimetus_liigiga>\n"
				+ "                            </adsTase3>\n" + "                            <adsTase5>\n"
				+ "                                <kood>0Q2V</kood>\n" + "                                <nimetus>Staapli tn</nimetus>\n"
				+ "                                <nimetus_liigiga>Staapli tänav</nimetus_liigiga>\n"
				+ "                            </adsTase5>\n" + "                            <adsTase7>\n"
				+ "                                <kood>9SGZ</kood>\n" + "                                <nimetus>12</nimetus>\n"
				+ "                                <nimetus_liigiga>12</nimetus_liigiga>\n" + "                            </adsTase7>\n"
				+ "                        </aadress>\n" + "                    </aadressid>\n" + "                </menobjekt>\n"
				+ "            </objektid>\n"
				+ "            <teade>[EE03516069] ADS teade: Objekt muudeti. Komponent 12/4 taastati tasemel 7. Komponent C9UV tasemel 7 tühistati.&#13;\n"
				+ "                [MR03722774] ADS teade: Objekt muudeti. Komponent 13 taastati tasemel 8. Komponent K7K2 tasemel 8 tühistati.&#13;\n"
				+ "                [ER03749385] ADS teade: Objekt muudeti. Komponent 2 taastati tasemel 8. Komponent K7K3 tasemel 8 tühistati.&#13;\n"
				+ "                [MR03756130] ADS teade: Objekt muudeti. Komponent 12 taastati tasemel 8. Komponent K7K1 tasemel 8 tühistati.&#13;\n"
				+ "                [ER03749386] ADS teade: Objekt muudeti. Komponent 6 taastati tasemel 8. Komponent K7K4 tasemel 8 tühistati.&#13;\n"
				+ "                [MR03749387] ADS teade: Objekt muudeti. Komponent 5 taastati tasemel 8. Komponent K7K5 tasemel 8 tühistati.&#13;\n"
				+ "                [ER03749388] ADS teade: Objekt muudeti. Komponent 11 taastati tasemel 8. Komponent K7K6 tasemel 8 tühistati.&#13;\n"
				+ "                [MR03749389] ADS teade: Objekt muudeti. Komponent 10 taastati tasemel 8. Komponent K7K7 tasemel 8 tühistati.&#13;\n"
				+ "                [ER03516072] ADS teade: Objekt muudeti. Komponent 7 taastati tasemel 8. Komponent K7K8 tasemel 8 tühistati.&#13;\n"
				+ "                [ER03516073] ADS teade: Objekt muudeti. Komponent 8 taastati tasemel 8. Komponent K7K9 tasemel 8 tühistati.&#13;\n"
				+ "                [ER03516075] ADS teade: Objekt muudeti. Komponent 3 taastati tasemel 8. Komponent K7KA tasemel 8 tühistati.&#13;\n"
				+ "                [ER03516076] ADS teade: Objekt muudeti. Komponent 4 taastati tasemel 8. Komponent K7KB tasemel 8 tühistati.&#13;\n"
				+ "                [ER03516078] ADS teade: Objekt muudeti. Komponent 9 taastati tasemel 8. Komponent K7KC tasemel 8 tühistati.&#13;\n"
				+ "                [ER03516081] ADS teade: Objekt muudeti. Komponent 1 taastati tasemel 8. Komponent K7KD tasemel 8 tühistati.</teade>\n"
				+ "            <tulem>1</tulem>\n" + "        </tns:ADSmenadresitResponse>\n" + "    </SOAP-ENV:Body>\n"
				+ "</SOAP-ENV:Envelope>\n");
		writer.flush();
		writer.close();
		res.getOutputStream().flush();
		res.getOutputStream().close();
		return "";
	}
	private List<Rule> getRules() throws IOException, URISyntaxException, SQLException {
		List<Rule> rules = Lists.newArrayList();

		for (File f : new File(/*this.getClass().getClassLoader().getResource(".").toURI().getPath() + "rules/replace"*/ "rules/replace")
				.listFiles()) {
			addRule(rules, f, false);
		}

		for (File f : new File(/*
		 * this.getClass().getClassLoader().getResource(
		 * ".").toURI().getPath() + "rules/replace"
		 */ "rules6/replace").listFiles()) {
			addRule(rules, f, true);
		}
		return rules;
	}

	public void addRule(List<Rule> rules, File f, boolean isXtee6) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(f.getAbsolutePath()), Charset.forName("UTF-8"));
			String y = lines.get(1).replace("<!--", "").trim();/** SPECIAL LINE */
			lines.remove(1);
			String k = lines.get(1).replace("-->", "").trim();/** SPECIAL LINE */
			lines.remove(1);

			addReplaceRule(rules, y, k, StringUtils.normalizeSpace(StringUtils.join(lines, "")), isXtee6);
		} catch (Exception e) {
			logger.error("RULE VIGANE:" + f.getName(), e);
		}
	}

	protected void addReplaceRule(List<Rule> rules, String teenus, String condition, String parameter, boolean isXtee6) {
		Rule r = new Rule();
		r.type = "R"; /** replace */
		r.teenus = teenus;
		r.condition = condition;
		r.parameter = parameter;
		r.isXtee6 = isXtee6;
		rules.add(r);
	}

	private Rule needsOverwriting(String xml, List<Rule> rules) {
		for (Rule r : rules) {
			if ("R".equals(r.type) && xml.contains(r.teenus) && xml.contains(r.condition) && (isXtee6Message(xml) == r.isXtee6)) {
				return r;
			}
		}
		return null;
	}

	public boolean isXtee6Message(String xml) {
		return xml.contains("xRoadInstance");
	}

	private void copyHeaders(HttpServletRequest request, HttpServletResponse res, HttpURLConnection connection) throws ProtocolException {
		// Set method
		HttpURLConnection http = null;
		if (connection instanceof HttpURLConnection) {
			http = (HttpURLConnection) connection;
			http.setRequestMethod(request.getMethod());
			http.setInstanceFollowRedirects(false);
		}

		// check connection header
		String connectionHdr = request.getHeader("Connection");
		if (connectionHdr != null) {
			connectionHdr = connectionHdr.toLowerCase();
			if (connectionHdr.equals("keep-alive") || connectionHdr.equals("close"))
				connectionHdr = null;
		}

		// copy headers
		boolean xForwardedFor = false;
		boolean hasContent = false;
		Enumeration enm = request.getHeaderNames();
		while (enm.hasMoreElements()) {
			// TODO could be better than this!
			String hdr = (String) enm.nextElement();
			String lhdr = hdr.toLowerCase();

			if (_DontProxyHeaders.contains(lhdr))
				continue;
			if (connectionHdr != null && connectionHdr.indexOf(lhdr) >= 0)
				continue;

			if ("content-type".equals(lhdr))
				hasContent = true;

			Enumeration vals = request.getHeaders(hdr);
			while (vals.hasMoreElements()) {
				String val = (String) vals.nextElement();
				if (val != null) {
					connection.addRequestProperty(hdr, val);
					//                    context.log("req "+hdr+": "+val);
					xForwardedFor |= "X-Forwarded-For".equalsIgnoreCase(hdr);
				}
			}
		}

		// Proxy headers
		connection.setRequestProperty("Via", "1.1 (jetty)");
		if (!xForwardedFor)
			connection.addRequestProperty("X-Forwarded-For", request.getRemoteAddr());

		// a little bit of cache control
		String cache_control = request.getHeader("Cache-Control");
		if (cache_control != null && (cache_control.indexOf("no-cache") >= 0 || cache_control.indexOf("no-store") >= 0))
			connection.setUseCaches(false);
	}

	class Rule {
		public String type;
		public String teenus;
		public String condition;
		public String parameter;
		public boolean isXtee6;
	}
}
