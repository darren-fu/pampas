package com.github.pampas.storage.dao;

import com.github.pampas.storage.entity.DBGatewayServer;

import java.util.List;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-09-18
 */
public class GatewayServerDao extends BaseDao<DBGatewayServer> {

    /**
     * 更新Gateway Server的状态
     *
     * @param dbGatewayServer
     */
    public void updateGatewayServerStat(DBGatewayServer dbGatewayServer) {

        DBGatewayServer condition = new DBGatewayServer();
        condition.setServerId(dbGatewayServer.getServerId());
        condition.setServerName(dbGatewayServer.getServerName());
        condition.setPort(dbGatewayServer.getPort());

        List<DBGatewayServer> servers = queryByExample(condition);
        if (servers.size() == 0) {
            save(dbGatewayServer);
        } else {
            for (DBGatewayServer server : servers) {
                server.setState(dbGatewayServer.getState());
                server.setAddress(dbGatewayServer.getAddress());
                server.setStart(dbGatewayServer.getStart());
                save(server);
            }
        }
    }

    @Override
    public Class<DBGatewayServer> entityClz() {
        return DBGatewayServer.class;
    }
}
