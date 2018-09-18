package com.github.pampas.storage.entity;

import com.github.pampas.core.server.ServerState;
import lombok.Data;
import org.mongodb.morphia.annotations.Entity;

import java.util.Date;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-18
 */
@Entity(value = DBGatewayServer.ENTITY)
@Data
public class DBGatewayServer extends MongoData {
    public static final String ENTITY = "gateway_server";


    private String serverName;

    private String serverId;

    private Integer port;

    private String address;

    private Date start;

    private ServerState state;

}
