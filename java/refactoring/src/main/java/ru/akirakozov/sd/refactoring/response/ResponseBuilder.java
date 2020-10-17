package ru.akirakozov.sd.refactoring.response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseBuilder {

    private final StringBuilder responseTextBuilder = new StringBuilder();
    private final HttpServletResponse response;

    public ResponseBuilder(HttpServletResponse response) {
        this.response = response;
    }

    public void addBlock(String block) {
        responseTextBuilder.append(block).append('\n');
    }

    public void build() throws IOException {
        String text = "<html><body>\n" +  responseTextBuilder.toString() + "</body></html>";

        response.getWriter().println(text);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
