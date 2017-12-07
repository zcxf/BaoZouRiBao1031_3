package com.yz.baozouribao1031_2.util;

/**
 * Created by Administrator on 2016/10/31.
 */

public class URLConstants {
    // 首页
    public static final String URL_HOME = "http://dailyapi.ibaozou.com/api/v31/documents/latest";
    // 首页上拉加载下一页（%d为当前页的timestamp）
    public static final String URL_HOME_REFRESH = "http://dailyapi.ibaozou" + "" +
            ".com/api/v31/documents/latest?timestamp=%d&t";
    // 用户投稿
    public static final String URL_USER_CONTRIBUTE = "http://dailyapi.ibaozou" + "" +
            ".com/api/v31/documents/contributes/latest";
    // 用户投稿详情
    public static final String URL_USER_CONTRIBUTE_DETAIL = "http://baozouribao.com/documents/";
    // 视频
    public static final String URL_VIDEO = "http://dailyapi.ibaozou" + "" +
            ".com/api/v31/documents/videos/latest";
    // 排行榜(需要拼接 + 阅读、评论、赞 + 今天、)
    public static final String URL_RANK_READ = "http://dailyapi.ibaozou.com/api/v31/rank/";
    // 频道
    public static final String URL_CHANNEL = "http://dailyapi.ibaozou" + "" +
            ".com/api/v31/channels/index?page=1&per_page=10&d";
    // 频道
    public static final String URL_CHANNEL_FORMAT = "http://dailyapi.ibaozou" + "" +
            ".com/api/v31/channels/index?page=%d&per_page=10&d";
    // 频道二（后面补上频道item对应的id）
    public static final String URL_CHANNEL2 = "http://dailyapi.ibaozou.com/api/v31/channels/";
    // 搜索
    public static final String URL_SEARCH = "http://dailyapi.ibaozou.com/api/v31/documents/search";
}
