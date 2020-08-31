package com.yf.producer.task;

import com.alibaba.fastjson.JSONObject;
import com.yf.producer.pojo.Found;
import com.yf.producer.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * @author: yf
 * @date: 2019/12/18  10:11
 * @desc:
 */
@Component
@Slf4j
public class FundTask {


    /**
     * corn 秒 分 小时 天 月 周(1-7)
     */
//    @Scheduled(cron = "0 0/2 9-14 * * 1-5")
    public void getFound(){
        String[] jjCode = new String[]{"260108","162605","160632","519727","320007","001178","001617","001071","161725","001595","002682"};
        log.info("=========================================================================");
        log.info("当日净值  估算净值  估算涨跌百分比  估值时间          基金名称 ");
        Arrays.asList(jjCode).forEach(code ->{
            String url = "http://fundgz.1234567.com.cn/js/" + code + ".js?rt=1463558676006";
            String result = null;
            try {
                result = HttpClientUtil.sendGet(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (result != null){
                String json = result.substring(8,result.length()-2);
                JSONObject jsonObject = JSONObject.parseObject(json);
                String gszzl = jsonObject.getString("gszzl");
                String name = jsonObject.getString("name");
                String dwjz = jsonObject.getString("dwjz");
                String gsz = jsonObject.getString("gsz");
                String gztime = jsonObject.getString("gztime");
                Found found = new Found();
                if (Float.parseFloat(gszzl) < 0){
                    log.warn("{}   {}    {}%     {}   {}", dwjz,gsz,gszzl,gztime,name);
                }else {
                    log.info("{}   {}     {}%     {}   {}", dwjz,gsz,gszzl,gztime,name);
                }
                found.setName(name).setDwjz(dwjz).setGsz(gsz).setGszzl(gszzl).setGztime(gztime);
                // service.insertFound(found);
            }
        });
        log.info("=========================================================================");
    }


}
