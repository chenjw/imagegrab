import sun.net.www.http.HttpClient

import com.chenjw.imagegrab.spi.Grabber
import com.chenjw.imagegrab.spi.impl.GrabberTemplate



public class Baidu extends GrabberTemplate implements Grabber {
    /**
     * 邮箱抓取任务接口
     */
    def HttpClient httpClient;
    
    public String id(){
        return 'baidu';
    }

  
}
