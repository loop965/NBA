package com.yf.producer.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.yf.producer.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author: yf
 * @date: 2019/12/26  10:56
 * @desc:
 */
@Component
@Slf4j
public class NBAScoreTask {

    private static int maxSId = 0;
    private static String matchId = null;
    private static final String MATCH_TYPE = "basketball";

//   @Scheduled(fixedRate = 6000)
    public void getRealTimeScore(){

    }

    @Scheduled(fixedRate = 6000)
    public void getMaxId() throws Exception{
        if (matchId == null){
            return;
        }
        String maxIdUrl = "http://dingshi4pc.qiumibao.com/livetext/data/cache/max_sid/"+matchId+"/0.htm";
        //
        maxSId = Integer.parseInt(HttpClientUtil.sendGet(maxIdUrl));
    }


    public static Map<String,JSONObject> getLiveList(){
        Map<String,JSONObject> matchList = new HashMap<>();
        String url = "http://bifen4m.qiumibao.com/json/list.htm";
        String result = null;
        try {
            result = HttpClientUtil.sendGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == null){
            return new HashMap<>(16);
        }
        JSONObject resultObject = JSONObject.parseObject(result);
        JSONArray jsonArray = resultObject.getJSONArray("list");
        log.info("==================================================================");
        log.info("id       主队   比分       客队      时间          更新时间");
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
            matchList.put(id,jsonObject);
        });
        log.info("==================================================================");
        return matchList;
    }

    @Scheduled(initialDelay = 1000 * 3, fixedDelay=Long.MAX_VALUE)
    public void watchMatch() throws Exception{
        int initMaxSid = 0;
        Map<String,JSONObject> matchMap = getLiveList();
        if (matchMap.size() == 0){
            log.info("暂时没有比赛");
            return;
        }
        log.info("请输入比赛id 以enter键结束");
        Scanner scanner = new Scanner(System.in);
        matchId = scanner.next();
        if(!matchMap.containsKey(matchId)){
            log.error("比赛id错误");
            return;
        }
        String maxIdUrl = "http://dingshi4pc.qiumibao.com/livetext/data/cache/max_sid/"+matchId+"/0.htm";
        //
        maxSId = Integer.parseInt(HttpClientUtil.sendGet(maxIdUrl));
        while (initMaxSid < maxSId){
            initMaxSid = maxSId;
            // 比赛内容
            String contentUrl = "http://dingshi4pc.qiumibao.com/livetext/data/cache/livetext/"+matchId+"/0/lit_page_2/"+maxSId+".htm";
            String contentResult = HttpClientUtil.sendGet(contentUrl);
            JSONArray jsonArray = JSONArray.parseArray(contentResult);
            jsonArray.forEach(o -> {
                JSONObject jsonObject = (JSONObject) o;
                String  liveSid = jsonObject.getString("live_sid");
                if (Integer.parseInt(liveSid) < maxSId){
                    return;
                }
                String homeScore = jsonObject.getString("home_score");
                String visitScore = jsonObject.getString("visit_score");
                String liveText = jsonObject.getString("live_text");
                String pidText = jsonObject.getString("pid_text").replace("\n"," ");
                log.info("比分【{}:{}】 {} {}",homeScore,visitScore,liveText,pidText);
            });
        }
    }

    public static void main(String[] args) throws Exception {

    }



}
