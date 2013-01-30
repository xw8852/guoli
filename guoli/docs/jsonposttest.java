package com.guoli.json;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

 

import com.google.gson.Gson;

public class jsonposttest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        String resp = null;
        
        try {        	 
        	  resp = getResponse("http://api.guoli.com/doaction.php?type=json");
        	  //   resp = getResponse("http://172.16.3.86:81/doaction.php?type=json");
              System.out.print("resp: \r\n" + resp);
        } catch (Exception e) {
             System.out.println(e.toString());
            return;
        }
	}
    public static String getResponse(String url) throws Exception {
 
        HttpPost post = new HttpPost(url);

        post.setHeader("content-type", "application/json");
        post.setHeader("Accept", "application/json");
        post.getParams().setParameter(HTTP.DEFAULT_CONTENT_CHARSET, "utf-8");

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("action", "hotel_qry");
        // 	"hotel"=>array('qry','room','recommen','hotelpic'),
     	//"order"=>array('ordprice','ordsumpri','ordsubmit'，details),
     	//	  	"system"=>array('loading','citylist','arealist','mobilecheck'),
     	//	 	"user"=>array('noreglg')
        
        HashMap<String, Object> childParams = new HashMap<String, Object>();
        childParams.put("version", "1.0");
        childParams.put("citycode", "310000");
        childParams.put("indate", "2013-01-25");
        childParams.put("outdate", "2013-02-03");
        childParams.put("orderkey", "guoli");//排序方式
        childParams.put("picpath", "/232/233223");
        childParams.put("price", 2);//价格区间
        childParams.put("starlevel", "02");//星级
//        childParams.put("areaflag", "3");//地区 
//        childParams.put("area", "310106");//静安区
        childParams.put("pageno", "1");//当前页码
        childParams.put("shopid", "79245");//酒店id
        childParams.put("pid", "50373");//酒店房型ID
        childParams.put("num", "2");//间
        childParams.put("mobile", "13681959090");
        childParams.put("linkman","谢国苗");
        childParams.put("meno","请安排大床");
    //  childParams.put("keyword", "希尔顿");
//订单发票信息
        childParams.put("isinvoice", "1");
        childParams.put("invoicetitle", "湖北襄阳市和驰房地产开发有限公司");
        childParams.put("writename", "谢国苗");
        childParams.put("address", "上海市闸北区江场三路76-78号聚能湾大厦608室");
        childParams.put("postcode", "200436");
        childParams.put("uid", 3);
        childParams.put("authcode", "498d309");
        childParams.put("orderno", "13538193755478810");
        
      
        params.put("param", childParams);
        params.put("platformType", "android");       
        Gson gson = new Gson();
        String request = gson.toJson(params);
        StringEntity entity = new StringEntity(request, HTTP.UTF_8);
        entity.setContentType("application/json");
        try{
        	post.setEntity(entity);
        }catch (Exception e) {
            System.out.print(e.toString());
        }

        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
        HttpResponse resp = client.execute(post);
        return EntityUtils.toString(resp.getEntity(), HTTP.UTF_8);
 
    }
}
