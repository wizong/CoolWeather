package com.shenzhen.coolweather.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author wizong @Date 2017/2/16 18:30 @Description TODO
 */
public class Weather {

    @SerializedName("HeWeather")
    private List<HeWeatherBean> HeWeather;

    public List<HeWeatherBean> getHeWeather() {
        return HeWeather;
    }

    public void setHeWeather(List<HeWeatherBean> HeWeather) {
        this.HeWeather = HeWeather;
    }

    public static class HeWeatherBean {
        /**
         * aqi : {"city":{"aqi":"58","co":"1","no2":"59","o3":"70","pm10":"61","pm25":"40","qlty":"良","so2":"23"}}
         * basic : {"city":"苏州","cnty":"中国","id":"CN101190401","lat":"31.309000","lon":"120.612000","update":{"loc":"2017-02-16 14:51","utc":"2017-02-16 06:51"}}
         * daily_forecast : [{"astro":{"mr":"22:45","ms":"09:44","sr":"06:37","ss":"17:45"},"cond":{"code_d":"305","code_n":"101","txt_d":"小雨","txt_n":"多云"},"date":"2017-02-16","hum":"79","pcpn":"1.2","pop":"93","pres":"1019","tmp":{"max":"15","min":"9"},"uv":"2","vis":"9","wind":{"deg":"182","dir":"东南风","sc":"微风","spd":"6"}},{"astro":{"mr":"23:39","ms":"10:19","sr":"06:36","ss":"17:46"},"cond":{"code_d":"305","code_n":"305","txt_d":"小雨","txt_n":"小雨"},"date":"2017-02-17","hum":"80","pcpn":"0.3","pop":"82","pres":"1026","tmp":{"max":"12","min":"6"},"uv":"4","vis":"10","wind":{"deg":"32","dir":"东北风","sc":"3-4","spd":"13"}},{"astro":{"mr":"null","ms":"10:55","sr":"06:35","ss":"17:47"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2017-02-18","hum":"72","pcpn":"0.0","pop":"2","pres":"1027","tmp":{"max":"13","min":"5"},"uv":"5","vis":"10","wind":{"deg":"147","dir":"东南风","sc":"微风","spd":"10"}}]
         * hourly_forecast : [{"cond":{"code":"302","txt":"雷阵雨"},"date":"2017-02-16 16:00","hum":"72","pop":"5","pres":"1017","tmp":"15","wind":{"deg":"177","dir":"南风","sc":"微风","spd":"15"}},{"cond":{"code":"103","txt":"晴间多云"},"date":"2017-02-16 19:00","hum":"83","pop":"0","pres":"1018","tmp":"13","wind":{"deg":"161","dir":"东南风","sc":"微风","spd":"14"}},{"cond":{"code":"101","txt":"多云"},"date":"2017-02-16 22:00","hum":"86","pop":"0","pres":"1019","tmp":"9","wind":{"deg":"177","dir":"南风","sc":"微风","spd":"11"}}]
         * now : {"cond":{"code":"300","txt":"阵雨"},"fl":"10","hum":"48","pcpn":"0","pres":"1019","tmp":"18","vis":"8","wind":{"deg":"170","dir":"南风","sc":"5-6","spd":"30"}}
         * status : ok
         * suggestion : {"air":{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"},"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"},"sport":{"brf":"较不宜","txt":"有降水，推荐您在室内进行各种健身休闲运动，若坚持户外运动，须注意保暖并携带雨具。"},"trav":{"brf":"适宜","txt":"温度适宜，又有较弱降水和微风作伴，会给您的旅行带来意想不到的景象，适宜旅游，可不要错过机会呦！"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
         */

        @SerializedName("aqi")
        private AqiBean aqi;
        @SerializedName("basic")
        private BasicBean                basic;
        @SerializedName("now")
        private NowBean                  now;
        @SerializedName("status")
        private String                   status;
        @SerializedName("suggestion")
        private SuggestionBean           suggestion;
        @SerializedName("daily_forecast")
        private List<DailyForecastBean>  dailyForecast;
        @SerializedName("hourly_forecast")
        private List<HourlyForecastBean> hourlyForecast;

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDailyForecast() {
            return dailyForecast;
        }

        public void setDailyForecast(List<DailyForecastBean> dailyForecast) {
            this.dailyForecast = dailyForecast;
        }

        public List<HourlyForecastBean> getHourlyForecast() {
            return hourlyForecast;
        }

        public void setHourlyForecast(List<HourlyForecastBean> hourlyForecast) {
            this.hourlyForecast = hourlyForecast;
        }

        public static class AqiBean {
            /**
             * city : {"aqi":"58","co":"1","no2":"59","o3":"70","pm10":"61","pm25":"40","qlty":"良","so2":"23"}
             */

            @SerializedName("city")
            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean {
                /**
                 * aqi : 58
                 * co : 1
                 * no2 : 59
                 * o3 : 70
                 * pm10 : 61
                 * pm25 : 40
                 * qlty : 良
                 * so2 : 23
                 */

                @SerializedName("aqi")
                private String aqi;
                @SerializedName("co")
                private String co;
                @SerializedName("no2")
                private String no2;
                @SerializedName("o3")
                private String o3;
                @SerializedName("pm10")
                private String pm10;
                @SerializedName("pm25")
                private String pm25;
                @SerializedName("qlty")
                private String qlty;
                @SerializedName("so2")
                private String so2;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class BasicBean {
            /**
             * city : 苏州
             * cnty : 中国
             * id : CN101190401
             * lat : 31.309000
             * lon : 120.612000
             * update : {"loc":"2017-02-16 14:51","utc":"2017-02-16 06:51"}
             */

            @SerializedName("city")
            private String city;
            @SerializedName("cnty")
            private String     cnty;
            @SerializedName("id")
            private String     id;
            @SerializedName("lat")
            private String     lat;
            @SerializedName("lon")
            private String     lon;
            @SerializedName("update")
            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                /**
                 * loc : 2017-02-16 14:51
                 * utc : 2017-02-16 06:51
                 */

                @SerializedName("loc")
                private String loc;
                @SerializedName("utc")
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowBean {
            /**
             * cond : {"code":"300","txt":"阵雨"}
             * fl : 10
             * hum : 48
             * pcpn : 0
             * pres : 1019
             * tmp : 18
             * vis : 8
             * wind : {"deg":"170","dir":"南风","sc":"5-6","spd":"30"}
             */

            @SerializedName("cond")
            private CondBean cond;
            @SerializedName("fl")
            private String   fl;
            @SerializedName("hum")
            private String   hum;
            @SerializedName("pcpn")
            private String   pcpn;
            @SerializedName("pres")
            private String   pres;
            @SerializedName("tmp")
            private String   tmp;
            @SerializedName("vis")
            private String   vis;
            @SerializedName("wind")
            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                /**
                 * code : 300
                 * txt : 阵雨
                 */

                @SerializedName("code")
                private String code;
                @SerializedName("txt")
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                /**
                 * deg : 170
                 * dir : 南风
                 * sc : 5-6
                 * spd : 30
                 */

                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionBean {
            /**
             * air : {"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。"}
             * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
             * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
             * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
             * flu : {"brf":"较易发","txt":"天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。"}
             * sport : {"brf":"较不宜","txt":"有降水，推荐您在室内进行各种健身休闲运动，若坚持户外运动，须注意保暖并携带雨具。"}
             * trav : {"brf":"适宜","txt":"温度适宜，又有较弱降水和微风作伴，会给您的旅行带来意想不到的景象，适宜旅游，可不要错过机会呦！"}
             * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
             */

            @SerializedName("air")
            private AirBean air;
            @SerializedName("comf")
            private ComfBean  comf;
            @SerializedName("cw")
            private CwBean    cw;
            @SerializedName("drsg")
            private DrsgBean  drsg;
            @SerializedName("flu")
            private FluBean   flu;
            @SerializedName("sport")
            private SportBean sport;
            @SerializedName("trav")
            private TravBean  trav;
            @SerializedName("uv")
            private UvBean    uv;

            public AirBean getAir() {
                return air;
            }

            public void setAir(AirBean air) {
                this.air = air;
            }

            public ComfBean getComf() {
                return comf;
            }

            public void setComf(ComfBean comf) {
                this.comf = comf;
            }

            public CwBean getCw() {
                return cw;
            }

            public void setCw(CwBean cw) {
                this.cw = cw;
            }

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public static class AirBean {
                /**
                 * brf : 中
                 * txt : 气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。
                 */

                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class ComfBean {
                /**
                 * brf : 舒适
                 * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
                 */

                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwBean {
                /**
                 * brf : 不宜
                 * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
                 */

                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgBean {
                /**
                 * brf : 较冷
                 * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
                 */

                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluBean {
                /**
                 * brf : 较易发
                 * txt : 天气较凉，较易发生感冒，请适当增加衣服。体质较弱的朋友尤其应该注意防护。
                 */

                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportBean {
                /**
                 * brf : 较不宜
                 * txt : 有降水，推荐您在室内进行各种健身休闲运动，若坚持户外运动，须注意保暖并携带雨具。
                 */

                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravBean {
                /**
                 * brf : 适宜
                 * txt : 温度适宜，又有较弱降水和微风作伴，会给您的旅行带来意想不到的景象，适宜旅游，可不要错过机会呦！
                 */

                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvBean {
                /**
                 * brf : 最弱
                 * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
                 */

                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * astro : {"mr":"22:45","ms":"09:44","sr":"06:37","ss":"17:45"}
             * cond : {"code_d":"305","code_n":"101","txt_d":"小雨","txt_n":"多云"}
             * date : 2017-02-16
             * hum : 79
             * pcpn : 1.2
             * pop : 93
             * pres : 1019
             * tmp : {"max":"15","min":"9"}
             * uv : 2
             * vis : 9
             * wind : {"deg":"182","dir":"东南风","sc":"微风","spd":"6"}
             */

            @SerializedName("astro")
            private AstroBean astro;
            @SerializedName("cond")
            private CondBeanX cond;
            @SerializedName("date")
            private String    date;
            @SerializedName("hum")
            private String    hum;
            @SerializedName("pcpn")
            private String    pcpn;
            @SerializedName("pop")
            private String    pop;
            @SerializedName("pres")
            private String    pres;
            @SerializedName("tmp")
            private TmpBean   tmp;
            @SerializedName("uv")
            private String    uv;
            @SerializedName("vis")
            private String    vis;
            @SerializedName("wind")
            private WindBeanX wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBeanX getCond() {
                return cond;
            }

            public void setCond(CondBeanX cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBeanX getWind() {
                return wind;
            }

            public void setWind(WindBeanX wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                /**
                 * mr : 22:45
                 * ms : 09:44
                 * sr : 06:37
                 * ss : 17:45
                 */

                @SerializedName("mr")
                private String mr;
                @SerializedName("ms")
                private String ms;
                @SerializedName("sr")
                private String sr;
                @SerializedName("ss")
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBeanX {
                /**
                 * code_d : 305
                 * code_n : 101
                 * txt_d : 小雨
                 * txt_n : 多云
                 */

                @SerializedName("code_d")
                private String codeD;
                @SerializedName("code_n")
                private String codeN;
                @SerializedName("txt_d")
                private String txtD;
                @SerializedName("txt_n")
                private String txtN;

                public String getCodeD() {
                    return codeD;
                }

                public void setCodeD(String codeD) {
                    this.codeD = codeD;
                }

                public String getCodeN() {
                    return codeN;
                }

                public void setCodeN(String codeN) {
                    this.codeN = codeN;
                }

                public String getTxtD() {
                    return txtD;
                }

                public void setTxtD(String txtD) {
                    this.txtD = txtD;
                }

                public String getTxtN() {
                    return txtN;
                }

                public void setTxtN(String txtN) {
                    this.txtN = txtN;
                }
            }

            public static class TmpBean {
                /**
                 * max : 15
                 * min : 9
                 */

                @SerializedName("max")
                private String max;
                @SerializedName("min")
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBeanX {
                /**
                 * deg : 182
                 * dir : 东南风
                 * sc : 微风
                 * spd : 6
                 */

                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class HourlyForecastBean {
            /**
             * cond : {"code":"302","txt":"雷阵雨"}
             * date : 2017-02-16 16:00
             * hum : 72
             * pop : 5
             * pres : 1017
             * tmp : 15
             * wind : {"deg":"177","dir":"南风","sc":"微风","spd":"15"}
             */

            @SerializedName("cond")
            private CondBeanXX cond;
            @SerializedName("date")
            private String     date;
            @SerializedName("hum")
            private String     hum;
            @SerializedName("pop")
            private String     pop;
            @SerializedName("pres")
            private String     pres;
            @SerializedName("tmp")
            private String     tmp;
            @SerializedName("wind")
            private WindBeanXX wind;

            public CondBeanXX getCond() {
                return cond;
            }

            public void setCond(CondBeanXX cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindBeanXX getWind() {
                return wind;
            }

            public void setWind(WindBeanXX wind) {
                this.wind = wind;
            }

            public static class CondBeanXX {
                /**
                 * code : 302
                 * txt : 雷阵雨
                 */

                @SerializedName("code")
                private String code;
                @SerializedName("txt")
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBeanXX {
                /**
                 * deg : 177
                 * dir : 南风
                 * sc : 微风
                 * spd : 15
                 */

                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
