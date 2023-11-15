package com.microservices.apigateway.filter;

import com.microservices.apigateway.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component

public class AuthenticationFilter extends AbstractGatewayFilterFactory{

    @Autowired
    private RouteValidator validator;

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange,chain)->{

            // check any req matches with the routes present in route validator

           if(validator.isSecured.test((ServerHttpRequest) exchange.getRequest())){

               //header conatins token or not
               if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                   throw new RuntimeException("missing token in header");
               }

               String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
               if(authHeader!=null && authHeader.startsWith("Bearer ")){
                   authHeader= authHeader.substring(7);// removing "Bearer " from the token header
               }
               try{
                   JwtService.validateToken(authHeader);
               }
               catch (Exception e ){
                   System.out.println("invalid token");
                   throw new RuntimeException("un authorized token");
               }
           }
         return   chain.filter(exchange);
        });
    }


}
