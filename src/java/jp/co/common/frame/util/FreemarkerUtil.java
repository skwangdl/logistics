package jp.co.common.frame.util;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtil {

	private Configuration cfg;
	
	public FreemarkerUtil () throws Exception {
		cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(this.getClass().getClassLoader().getResource("/").toURI().getPath() + "/template"));
	}
	
	public String getTemplateStr(Object rootMap, String templateName) throws Exception {
		return getTemplateStr(rootMap, templateName, "UTF-8");
	}
	public String getTemplateStr(Object rootMap, String templateName, String charsetName) throws Exception {
		Template t = cfg.getTemplate(templateName, charsetName);
		Writer out = new StringWriter();
		t.process(rootMap, out);
		return out.toString();
	}
	public String getTemplateStrNoCharset(Object rootMap, String templateName) throws Exception {
		Template t = cfg.getTemplate(templateName);
		Writer out = new StringWriter();
		t.process(rootMap, out);
		return out.toString();
	}	
}