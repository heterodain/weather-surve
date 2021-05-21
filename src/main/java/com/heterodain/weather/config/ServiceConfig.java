package com.heterodain.weather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * サービスの設定
 */
@Component
@ConfigurationProperties("service")
@Data
public class ServiceConfig {
    /** Open Weather Mapの設定 */
    private OpenWeatherMap openWeatherMap;

    /** Ambientの設定 */
    private Ambient ambientWeather;

    /**
     * Open Weather Mapの設定情報
     */
    @Data
    public static class OpenWeatherMap {
        /** 都市ID */
        private String cityId;
        /** APIアクセスキー */
        private String apiKey;
    }

    /**
     * Ambientの設定情報
     */
    @Data
    public static class Ambient {
        /** チャネルID */
        private Integer channelId;
        /** リードキー */
        private String readKey;
        /** ライトキー */
        private String writeKey;
    }
}