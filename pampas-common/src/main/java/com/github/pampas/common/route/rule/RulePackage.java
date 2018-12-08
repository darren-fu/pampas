package com.github.pampas.common.route.rule;

import com.github.pampas.common.tools.JsonTools;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * User: darrenfu
 * Date: 2018-12-04
 */
@Data
public class RulePackage {

    private Integer ruleId;

    private String name;

    private String mappingHost;

    private List<AbstractRule> ruleList;


    public void addRule(Map<String, String> ruleDetailMap) {
        if (ruleList == null) {
            ruleList = new ArrayList<>(32);
        }

        JsonTools jsonTools = JsonTools.NON_NULL;
        String json = jsonTools.toJson(ruleDetailMap);
        String type = ruleDetailMap.get("type");
        AbstractRule rule = null;
        if (RuleTypeEnum.HTTP.getValue().equals(type)) {
            rule = jsonTools.fromJson(json, HttpRule.class);
        } else if (RuleTypeEnum.DUBBO.getValue().equals(type)) {
            rule = jsonTools.fromJson(json, DubboRule.class);
        } else if (RuleTypeEnum.GRPC.getValue().equals(type)) {

        }
        rule.init();
        ruleList.add(rule);
    }

}
