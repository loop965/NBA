package com.yf.producer.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yf.producer.entity.MatchVo;
import com.yf.producer.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yf 2020/8/31 10:39 PM
 */
@RestController
@RequestMapping(value = "nba")
@Slf4j
public class NbaController {

    private static final String MATCH_TYPE = "basketball";


    @RequestMapping(value = "match/list")
    public List<MatchVo> matchVoList(){
        List<MatchVo> matchVoList = new ArrayList<>();
        String url = "http://bifen4m.qiumibao.com/json/list.htm";
        String result = null;
        try {
            result = HttpClientUtil.sendGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == null){
            return matchVoList;
        }
        JSONObject resultObject = JSONObject.parseObject(result);
        JSONArray jsonArray = resultObject.getJSONArray("list");
        jsonArray.forEach(object ->{
            JSONObject jsonObject = (JSONObject) object;
            String type = jsonObject.getString("type");
            if (!MATCH_TYPE.equals(type)){
                return;
            }
            String id = jsonObject.getString("id");
            String hostTeam = jsonObject.getString("home_team");
            String visitTeam = jsonObject.getString("visit_team");
            String homeScore = jsonObject.getString("home_score");
            String visitScore = jsonObject.getString("visit_score");
            String updateTime = jsonObject.getString("update");
            String periodCn = jsonObject.getString("period_cn").replace("\n"," ");
            log.info("{}   {}   {}：{}   {}    {}     {}",id,hostTeam,homeScore,visitScore,visitTeam,periodCn,updateTime);
            MatchVo matchVo = new MatchVo(id,hostTeam,homeScore,visitTeam,visitScore,updateTime,periodCn);
            matchVoList.add(matchVo);
        });
        return matchVoList;

    }


}
