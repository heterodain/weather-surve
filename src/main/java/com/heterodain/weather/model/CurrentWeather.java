package com.heterodain.weather.model;

import lombok.Data;

/**
 * 現在の天気情報
 */
@Data
public class CurrentWeather {
    /** 天候 */
    private String weather;
    /** 温度(℃) */
    private Float temperature;
    /** 気圧(hPa) */
    private Integer pressure;
    /** 湿度(%) */
    private Integer humidity;
    /** 風速(meter/秒) */
    private Float windSpeed;
    /** 雲量(%) */
    private Integer cloudness;
    /** 1時間当たりの降水量(mm) */
    private Integer rain1h;
    /** 1時間当たりの積雪量(mm) */
    private Integer snow1h;
}
