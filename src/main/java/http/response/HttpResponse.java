package http.response;

import http.HttpHeaders;

import java.io.IOException;
import java.io.OutputStream;

import static http.constant.HttpStatus.*;

public class HttpResponse {

    private OutputStream outputStream;
    private HttpResponseStartLine startLine;
    private HttpHeaders headers;
    private byte[] body;

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.headers = new HttpHeaders();
        this.startLine = new HttpResponseStartLine();
        this.body = new byte[0];
    }

    // 200 OK
    public void forward(String path) throws IOException {
        startLine.setStatus(OK);
        writeResponse();
    }

    // 302 FOUND
    public void redirect(String path) throws IOException {
        startLine.setStatus(REDIRECT);
        writeResponse();
    }


    private void writeResponse() throws IOException {
        outputStream.write(startLine.toString().getBytes());
        outputStream.write(headers.toString().getBytes());
        outputStream.write(body);
        outputStream.flush();
        outputStream.close();
    }


    /* SET */
    public void setHeaders(HttpHeaders headers){
        this.headers = headers;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

//    public void setStartLineStatus(Status status){
//        startLine.setStatus(status);
//    }

}
