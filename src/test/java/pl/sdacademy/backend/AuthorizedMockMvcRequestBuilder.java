package pl.sdacademy.backend;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Base64;

public class AuthorizedMockMvcRequestBuilder {

    public static String defaultAuthorizationHeader() {
        return "Basic " + Base64.getEncoder().encodeToString("user3:Password3".getBytes());
    }

    public static MockHttpServletRequestBuilder authorizeGet(String path) {
        return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(path)
                .header("Authorization", defaultAuthorizationHeader());
    }
}
