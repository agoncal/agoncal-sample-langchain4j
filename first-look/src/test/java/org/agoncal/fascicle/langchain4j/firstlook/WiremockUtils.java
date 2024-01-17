package org.agoncal.fascicle.langchain4j.firstlook;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class WiremockUtils {

    public static final String DEFAULT_TOKEN = "whatever";
    private static final String DEFAULT_CHAT_RESPONSE_BODY;
    private static final ResponseDefinitionBuilder DEFAULT_CHAT_RESPONSE;

    static {
            DEFAULT_CHAT_RESPONSE_BODY = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
            DEFAULT_CHAT_RESPONSE = aResponse().withBody(DEFAULT_CHAT_RESPONSE_BODY);
    }

    private static ResponseDefinitionBuilder biographyResponse() {
        return DEFAULT_CHAT_RESPONSE;
    }

    public static MappingBuilder biographyMapping(String token) {
        return get(urlEqualTo("/authors"));
//                .withHeader("Authorization", equalTo("Bearer " + token));
    }

    public static MappingBuilder defaultBiographyStub() {
        return defaultBiographyStub(DEFAULT_TOKEN);
    }

    public static MappingBuilder defaultBiographyStub(String token) {
        return biographyMapping(token).willReturn(biographyResponse());
    }
}
