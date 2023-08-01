package com.hache.server.settle.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.hache.server.settle.handlers.HandlerAuth;

@Configuration
public class AuthRouter {

   @Bean
   public RouterFunction<ServerResponse> registerSettle(HandlerAuth handlerAuth) {
      return RouterFunctions.route().POST("/api/hache/auth/signup", handlerAuth::signUpSettle).build();
   }

   @Bean
   public RouterFunction<ServerResponse> loginSettle(HandlerAuth handlerAuth) {
      return RouterFunctions.route().POST("/api/hache/auth/signin", handlerAuth::loginSettle).build();
   }

   @Bean
   public RouterFunction<ServerResponse> refreshLoginSettle(HandlerAuth handlerAuth) {
      return RouterFunctions.route().POST("/api/hache/settle/refresh", handlerAuth::refreshSettle).build();
   }

}
