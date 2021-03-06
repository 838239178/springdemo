package cn.shijh.utils;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapBuilder {
     public static class MapBuildAction {
         private final Map<String, Object> map;

         public MapBuildAction() {
             map = new LinkedHashMap<>();
         }

         public MapBuildAction add(String key, Object value) {
             map.put(key,value);
             return this;
         }

         public Map<String, Object> build() {
             return this.map;
         }

         public String json(){
             return JSON.toJSONString(this.map);
         }
     }

     public static MapBuildAction map() {
         return new MapBuildAction();
     }
}
