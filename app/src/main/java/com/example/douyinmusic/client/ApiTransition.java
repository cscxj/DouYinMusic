package com.example.douyinmusic.client;

/**
 * 因为后端项目提供的访问接口和 数据返回的的接口不对应，在这里配置转换
 */

public class ApiTransition {
    public static String transition(String id) {
        String idx = "";
        switch (id) {
            // 飙升榜
            case "19723756":
                idx = "3";
                break;
            // 新歌榜
            case "3779629":
                idx = "0";
                break;
            // 原创榜
            case "2884035":
                idx = "2";
                break;
            // 网易云热歌榜
            case "3778678":
                idx = "1";
                break;
            // 网易云说唱榜
            case "991319590":
                idx = "23";
                break;
            // 古典音乐榜
            case "71384707":
                idx = "24";
                break;
            // 云音乐电音榜
            case "1978921795":
                idx = "25";
                break;
            // 抖音排行榜
            case "2250011882":
                idx = "26";
                break;
            // 新声榜
            case "2617766278":
                idx = "27";
                break;
            // ACG
            case "71385702":
                idx = "22";
                break;
            // 韩语榜
            case "745956260":
                idx = "28";
                break;
            // 国电榜
            case "10520166":
                idx = "30";
                break;
            // 英国Q杂志
            case "2023401535":
                idx = "29";
                break;
            // 电竞
            case "2006508653":
                idx = "30";
                break;
            // UK
            case "180106":
                idx = "5";
                break;
            // 美国Blli
            case "60198":
                idx = "6";
                break;
            // Beatport全球电子舞曲榜
            case "3812895":
                idx = "21";
                break;
            //KTV唛榜
            case "21845217":
                idx = "7";
                break;
            // iTunes榜
            case "11641012":
                idx = "8";
                break;
            //日本Oricon周榜
            case "60131":
                idx = "10";
                break;
            //Hit FM Top榜
            case "120001":
                idx = "9";
                break;
            // 台湾Hito排行榜
            case "112463":
                idx = "20";
                break;
            // 云音乐欧美热歌榜
            case "2809513713":
                idx = "31";
                break;
            // 云音乐欧美新歌榜
            case "2809577409":
                idx = "32";
                break;
            //法国 NRJ Vos Hits 周榜
            case "27135204":
                idx = "19";
                break;
            //中国新乡村音乐排行榜
            // 接口中没有这个榜，随便搞个换上
            case "3112516681":
                idx = "36";
                break;
        }
        return idx;
    }
}
