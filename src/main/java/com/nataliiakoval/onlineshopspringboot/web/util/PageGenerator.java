package com.nataliiakoval.onlineshopspringboot.web.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;

public class PageGenerator {
    private static PageGenerator pageGenerator;
    private final Configuration CFG;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String fileName, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            CFG.setClassForTemplateLoading(PageGenerator.class, "/templates");
            CFG.setDefaultEncoding("UTF-8");

            Template template = CFG.getTemplate(fileName);

            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    public String getPage(String fileName) {
        return getPage(fileName, Collections.emptyMap());
    }

    private PageGenerator() {
        CFG = new Configuration(Configuration.VERSION_2_3_31);
    }
}
