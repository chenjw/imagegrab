package com.chenjw.imagegrab.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.chenjw.dynacomponent.spi.ComponentContainer;
import com.chenjw.imagegrab.spi.Parser;

public class ParserContainer implements ComponentContainer<Parser>,InitializingBean{
    private Map<String, Parser> htmlParserMap ;
    private static ParserContainer instance;
    
    
    public static Map<String,Object> parse(String id,String html){
        Map<String,Object> r=null;
        Parser parser=instance.htmlParserMap.get(id);
        if(parser!=null){
            r=parser.parse(html);
        }
        
        System.out.println(JSON.toJSONString(r, SerializerFeature.PrettyFormat));
        return r;
    }
    
    @Override
    public Class<Parser> componentType() {
        return Parser.class;
    }

    @Override
    public void onReload(List<Parser> args) {
        Map<String,Parser> parsers=new HashMap<String,Parser>();
        for(Parser parser:args){
            parsers.put(parser.id(), parser);
        }
        htmlParserMap=parsers;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        instance=this;
    }

}
