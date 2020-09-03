package com.yf.producer.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yf.producer.entity.MatchVo;
import com.yf.producer.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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

    @RequestMapping("match/watch/{id}")
    public JSONObject  matchWatch(@PathVariable String id, int lastMaxSid) throws InterruptedException {
        JSONObject response = new JSONObject();
        List<String> msgList = new ArrayList<>();
        String maxIdUrl = "http://dingshi4pc.qiumibao.com/livetext/data/cache/max_sid/"+ id +"/0.htm";
        log.info(id);
        String maxId = HttpClientUtil.sendGet(maxIdUrl);
        if (StringUtils.isBlank(maxId)){
            return response;
        }
        int maxSId = Integer.parseInt(maxId);
        if (lastMaxSid == maxSId){
            return response;
        }
        String contentUrl = "http://dingshi4pc.qiumibao.com/livetext/data/cache/livetext/"+id+"/0/lit_page_2/"+maxSId+".htm";
        String contentResult = HttpClientUtil.sendGet(contentUrl);
        if (StringUtils.isBlank(contentResult)){
            return response;
        }
        JSONArray jsonArray = JSONArray.parseArray(contentResult);
        String today =  DateFormatUtils.format(new Date(),"yyyy-MM-dd");
        for (Object o : jsonArray) {
            String scoreUrl = "http://bifen4pc2.qiumibao.com/json/" + today + "/" + id + ".htm";
            String scoreResult = HttpClientUtil.sendGet(scoreUrl);
            String periodCn = "";
            if (StringUtils.isNotBlank(scoreResult)) {
                JSONObject detail = JSONObject.parseObject(scoreResult);
                periodCn = detail.getString("period_cn").replace("\n", " ");
            }
            JSONObject jsonObject = (JSONObject) o;
            String liveSid = jsonObject.getString("live_sid");
            lastMaxSid = Integer.parseInt(liveSid);
            String homeScore = jsonObject.getString("home_score");
            String visitScore = jsonObject.getString("visit_score");
            String liveText = jsonObject.getString("live_text");
            String pidText = jsonObject.getString("pid_text").replace("\n", " ");
            StringBuilder sb = new StringBuilder();
            sb.append(liveSid).append("【").append(homeScore).append(":").append(visitScore).append("】")
                    .append(" ").append(liveText).append(" ").append(periodCn);
            log.info("sid{} 【{}:{}】{} {}", liveSid,  homeScore, visitScore, liveText, periodCn);
            msgList.add(sb.toString());
        }
        response.put("msgList",msgList);
        response.put("lastMaxSid",lastMaxSid);
        return response;
    }


}
