package com.yf.producer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yf 2020/8/31 10:42 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchVo {

    private String matchId;
    private String homeTeam;
    private String homeScore;
    private String visitTeam;
    private String visitScore;
    private String updateTime;
    private String periodCn;


}
