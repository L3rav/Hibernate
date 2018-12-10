package infoShareAcademy.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import infoShareAcademy.freemarker.TemplateProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/infoShareAcademy")
public class TeamServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(TeamServlet.class);

    private static final String TEMPLATE_NAME = "team";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        Map<String, Object> model = new HashMap<>();
        model.put("name", "Mateusz Odroniec");
        model.put("team", "JDD5A-TAILANDCZYCY");
        model.put("date", LocalDateTime.now());

        Template template = templateProvider.getTemplate(
                getServletContext(), TEMPLATE_NAME
        );

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            LOG.error("Error while processing template: ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        List<String> valuesList = Collections.list(req.getParameterNames());
        for (String parameter : valuesList) {
            String[] parameterValues = req.getParameterValues(parameter);
            for (String pValue : parameterValues) {
                out.println(parameter + ":" + pValue);
            }
        }

    }
}