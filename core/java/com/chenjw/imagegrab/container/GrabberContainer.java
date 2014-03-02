package com.chenjw.imagegrab.container;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.InitializingBean;

import com.chenjw.dynacomponent.spi.ComponentContainer;
import com.chenjw.imagegrab.spi.Grabber;

public class GrabberContainer implements ComponentContainer<Grabber>,InitializingBean{
    private Map<String, Grabber> grabberMap ;
    private static GrabberContainer instance;
    
    public static Map<String,String> getIds(){
        Map<String,String> r=new HashMap<String,String>();
        for(Entry<String, Grabber> entry:instance.grabberMap.entrySet()){
            r.put(entry.getKey(), entry.getValue().name());
        }
        return r;
    }
    
    
    public Grabber getById(String id){
        return grabberMap.get(id);
    }
    
    
    @Override
    public Class<Grabber> componentType() {
        return Grabber.class;
    }

    @Override
    public void onReload(List<Grabber> args) {
        Map<String,Grabber> grabbers=new HashMap<String,Grabber>();
        for(Grabber grabber:args){
            grabbers.put(grabber.id(), grabber);
        }
        grabberMap=grabbers;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        instance=this;
    }



}
