package com.github.pampas.dubbo;

import cn.hutool.setting.dialect.Props;
import com.alibaba.dubbo.common.URL;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 高建华
 * @date 2018/6/19 下午11:01
 */
public enum DubboServiceClient {
    INSTANCE;

    private ZkClient client;
    private Props props;

    DubboServiceClient() {
        props = new Props("dubbo.properties");
        client = new ZkClient(props.getProperty("dubbo.registry.address"));
    }

    public Props getProps(){
        return props;
    }

    public List<String> getService() {
        if (!client.exists("/dubbo")) {
            return Collections.emptyList();
        }
        return client.getChildren("/dubbo");
    }

    public List<String> getProvider(String service) {
        if (!client.exists("/dubbo/" + service + "/providers")) {
            return Collections.emptyList();
        }
        List<String> list = client.getChildren("/dubbo/" + service + "/providers");
        if (!list.isEmpty()) {
            return list.stream().map(URL::decode).collect(Collectors.toList());
        }
        return list;
    }

    public List<String> getNoEmptyService() {
        if (!client.exists("/dubbo")) {
            return Collections.emptyList();
        }
        return client.getChildren("/dubbo").stream().filter(s -> client.exists("/dubbo/" + s + "/providers") && client.getChildren("/dubbo/" + s + "/providers").size() > 0).collect(Collectors.toList());
    }

    public List<String> getConsumer(String service) {
        if (!client.exists("/dubbo/" + service + "/consumers")) {
            return Collections.emptyList();
        }
        List<String> list = client.getChildren("/dubbo/" + service + "/consumers");
        if (!list.isEmpty()) {
            return list.stream().map(URL::decode).collect(Collectors.toList());
        }
        return list;
    }

    public List<String> getApplications() {

        List<String> services = getService();
        List<String> consumers = new ArrayList<>();
        List<String> providers = new ArrayList<>();

        for (String service : services) {
            consumers.addAll(getConsumer(service));
            providers.addAll(getProvider(service));
        }

        List<String> applications = consumers.stream().map(s ->
                Arrays.stream(StringUtils.substringAfter(s, "?").split("&")).filter(s1 -> StringUtils.contains(s1, "application=")).map(s1 -> StringUtils.replace(s1, "application=", "")).findFirst().get()
        ).collect(Collectors.toList());

        applications.addAll(providers.stream().map(s ->
                Arrays.stream(StringUtils.substringAfter(s, "?").split("&")).filter(s1 -> StringUtils.contains(s1, "application=")).map(s1 -> StringUtils.replace(s1, "application=", "")).findFirst().get()
        ).collect(Collectors.toList()));
        return applications.stream().distinct().collect(Collectors.toList());
    }


    public String getApplicationName(String service){
        String provider = getProvider(service).get(0);
        if(StringUtils.isBlank(provider)){
            return null;
        }else{
            return Arrays.stream(StringUtils.substringAfter(provider, "?").split("&")).filter(s1 -> StringUtils.contains(s1, "application=")).map(s1 -> StringUtils.replace(s1, "application=", "")).findFirst().get();
        }
    }
}
