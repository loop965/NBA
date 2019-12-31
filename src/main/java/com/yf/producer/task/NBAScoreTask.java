package com.yf.producer.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.yf.producer.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yf
 * @date: 2019/12/26  10:56
 * @desc:
 */
@Component
@Slf4j
public class NBAScoreTask {

    public static int maxSId = 0;
    private static String matchId = null;
    private static final String MATCH_TYPE = "basketball";
    public static  String  exitCode = "";

//   @Scheduled(fixedRate = 6000)
    public void getRealTimeScore(){

    }


    private Map<String,JSONObject> getLiveList(){
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
        int lastMaxSid = 0;
        Map<String,JSONObject> matchMap = getLiveList();
        if (matchMap.size() == 0){
            log.info("暂时没有比赛");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        while (true){
            // 获取要观看比赛id
            while (true){
                getLiveList();
                log.info("请输入比赛id 以enter键结束");
                matchId = scanner.next();
                if(matchMap.containsKey(matchId)){
                    break;
                }else {
                    log.error("比赛id错误");
                }
            }
            // 另外开启一个线程监视退出指令
            ExistThread existThread = new ExistThread();
            ExecutorService ex = Executors.newSingleThreadExecutor();
            ex.execute(existThread);
            // 比赛内容
            JSONObject object = matchMap.get(matchId);
            String hostTeam = object.getString("home_team");
            String visitTeam = object.getString("visit_team");
            String today =  DateFormatUtils.format(new Date(),"yyyy-MM-dd");
            String maxIdUrl = "http://dingshi4pc.qiumibao.com/livetext/data/cache/max_sid/"+matchId+"/0.htm";
            while (true){
                maxSId = Integer.parseInt(HttpClientUtil.sendGet(maxIdUrl));
                if (lastMaxSid == maxSId){
                    continue;
                }
                // 比赛内容
                String contentUrl = "http://dingshi4pc.qiumibao.com/livetext/data/cache/livetext/"+matchId+"/0/lit_page_2/"+maxSId+".htm";
                String contentResult = HttpClientUtil.sendGet(contentUrl);
                if (StringUtils.isBlank(contentResult)){
                    continue;
                }
                JSONArray jsonArray = JSONArray.parseArray(contentResult);
                for (int i = 0; i < jsonArray.size(); i++) {
                    String scoreUrl = "http://bifen4pc2.qiumibao.com/json/"+ today +"/"+matchId+".htm";
                    String scoreResult = HttpClientUtil.sendGet(scoreUrl);
                    String periodCn = "";
                    if (StringUtils.isNotBlank(scoreResult)){
                        JSONObject detail = JSONObject.parseObject(scoreResult);
                        periodCn = detail.getString("period_cn").replace("\n"," ");
                    }
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String liveSid = jsonObject.getString("live_sid");
                    lastMaxSid = Integer.parseInt(liveSid);
                    String homeScore = jsonObject.getString("home_score");
                    String visitScore = jsonObject.getString("visit_score");
                    String liveText = jsonObject.getString("live_text");
                    String pidText = jsonObject.getString("pid_text").replace("\n"," ");
                    log.info("sid{} {}【{}:{}】{} {} {}",liveSid,hostTeam,homeScore,visitScore,visitTeam,liveText,periodCn);
                    Thread.sleep(1000);
                }
                // 退出指令
                if (("q").equals(exitCode)){
                    break;
                }
                Thread.sleep(1000);
            }
        }

    }


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String matchId = null;
        Map<String,JSONObject> jsonObjectMap = new HashMap<>(16);
        jsonObjectMap.put("100",new JSONObject());
        jsonObjectMap.put("200",new JSONObject());
        jsonObjectMap.put("300",new JSONObject());
        while (true){
            while (true){
                System.out.println("请输入比赛id");
                matchId = scanner.next();
                if (jsonObjectMap.containsKey(matchId)){
                    break;
                }else {
                    log.error("比赛id错误");
                }
            }
            log.info("matchId:{}",matchId);
            ExistThread existThread = new ExistThread();
            Thread thread = new Thread(existThread);
            thread.start();
            while (true){
                log.info("view game match id:{}",matchId);
                Thread.sleep(2000);
                if (("q").equals(exitCode)){
                    break;
                }
            }
        }




    }

}
