package com.nisbank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator nisbankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
						.route(p -> p
								.path("/nisbank/accounts/**")
								.filters( f -> f.rewritePath("/nisbank/accounts/(?<segment>.*)","/${segment}")
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://ACCOUNTS"))
					.route(p -> p
							.path("/nisbank/loans/**")
							.filters( f -> f.rewritePath("/nisbank/loans/(?<segment>.*)","/${segment}")
									.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
							.uri("lb://LOANS"))
					.route(p -> p
							.path("/nisbank/cards/**")
							.filters( f -> f.rewritePath("/nisbank/cards/(?<segment>.*)","/${segment}")
									.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
							.uri("lb://CARDS")).build();
	}

}
