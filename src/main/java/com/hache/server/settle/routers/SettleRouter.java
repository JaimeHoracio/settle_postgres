package com.hache.server.settle.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.hache.server.settle.handlers.HandlerSettle;

@Configuration
public class SettleRouter {


    /**
     *
     *
     *
     * FILTRO PARA CONTROLAR QUE EL USUARIO QUE HACE LA MODIFICACION SEA EL MISMO DEL TOKEN!!!!
     *
     *
     *
     * */



    @Bean
    public RouterFunction<ServerResponse> initSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().GET("/api/hache/settle/init", handlerSettle::initSettle).build();
    }
    @Bean
    public RouterFunction<ServerResponse> selectMeetSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().GET("/api/hache/settle/meet/select", handlerSettle::selectSettle).build();
    }


    // Meet
    @Bean
    public RouterFunction<ServerResponse> addMeetSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().POST("/api/hache/settle/meet", handlerSettle::addMeetSettle).build();
    }

    @Bean
    public RouterFunction<ServerResponse> updateMeetSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().PUT("/api/hache/settle/meet", handlerSettle::updateMeetSettle).build();
    }

    @Bean
    public RouterFunction<ServerResponse> closeMeetSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().POST("/api/hache/settle/meet/close", handlerSettle::closeMeetSettle).build();
    }

    @Bean
    public RouterFunction<ServerResponse> removeMeetSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().DELETE("/api/hache/settle/meet", handlerSettle::removeMeetSettle).build();
    }

    // Bill
    @Bean
    public RouterFunction<ServerResponse> addBillSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().POST("/api/hache/settle/bill", handlerSettle::addBillSettle).build();
    }

    @Bean
    public RouterFunction<ServerResponse> updateBillSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().PUT("/api/hache/settle/bill", handlerSettle::updateBillSettle).build();
    }

    @Bean
    public RouterFunction<ServerResponse> removeBillSettle(HandlerSettle handlerSettle) {
        return RouterFunctions.route().DELETE("/api/hache/settle/bill", handlerSettle::removeBillSettle).build();
    }

}
