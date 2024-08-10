package com.nisbank.gatewayserver.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.springframework.http.HttpHeaders;
import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "X-Correlation-ID";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(CORRELATION_ID) != null) {
            //TODO improve 2 lines to 1:
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

}
