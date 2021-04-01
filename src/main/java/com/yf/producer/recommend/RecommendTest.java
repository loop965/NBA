package com.yf.producer.recommend;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author: yf
 * @date: 2021/01/13  14:45
 * @desc:
 */
public class RecommendTest {
    public static void main(String[] args) throws TasteException, IOException {
        // 存储并计算提供计算所需的偏好，用户以及物品数据
        DataModel model = new FileDataModel(new File("E:\\test.csv"));
        // 比较两个用户之间的相似度
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        // 明确与给定用户最相似的一组用户
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
        // 合并上述所有组件为用户推荐物品
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(6, 20);
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }
    }
}
