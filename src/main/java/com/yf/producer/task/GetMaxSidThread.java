package com.yf.producer.task;

import com.yf.producer.util.HttpClientUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: yf
 * @date: 2019/12/31  09:12
 * @desc:
 */
@Slf4j
public class GetMaxSidThread implements Runnable {

    private String matchId;

    public GetMaxSidThread(String matchId) {
        this.matchId = matchId;
    }

    @SneakyThrows
    @Override
    public void run() {

        while (true){
            if (matchId != null){
                String maxIdUrl = "http://dingshi4pc.qiumibao.com/livetext/data/cache/max_sid/"+matchId+"/0.htm";
                //
                NBAScoreTask.maxSId = Integer.parseInt(HttpClientUtil.sendGet(maxIdUrl));
                log.info("getMaxSId:{}",NBAScoreTask.maxSId);
                Thread.sleep(6000);
            }
        }


    }


    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
