package br.com.fiap.infrastructure;

//package br.com.fiap.infrastructure;
//
//import jakarta.ws.rs.container.ContainerRequestContext;
//import jakarta.ws.rs.container.ContainerResponseContext;
//import jakarta.ws.rs.container.ContainerResponseFilter;
//import jakarta.ws.rs.ext.Provider;
//
//import java.io.IOException;
//
//@Provider
//public class CorsFilter implements ContainerResponseFilter {
//    @Override
//    public void filter(ContainerRequestContext requestContext,
//                       ContainerResponseContext responseContext) throws IOException {
//        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
//        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
//        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
//        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//
//
//    }
//}
//
//


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        headers.put("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        headers.put("Access-Control-Allow-Credentials", "true");
        headers.put("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.put("Access-Control-Max-Age", "1209600");

        headers.forEach((key, value) -> {
            if (!responseContext.getHeaders().containsKey(key)) {
                responseContext.getHeaders().add(key, value);
            }
        });

        // Handle preflight requests
        if (requestContext.getMethod().equalsIgnoreCase("OPTIONS")) {
            responseContext.setStatusInfo(Response.Status.OK);
        }
    }
}
