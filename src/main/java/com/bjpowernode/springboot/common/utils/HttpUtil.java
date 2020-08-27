package com.bjpowernode.springboot.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class HttpUtil {

    private static CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager httpPool = new PoolingHttpClientConnectionManager();
        httpPool.setMaxTotal(200);
        httpClient = HttpClients.custom().setConnectionManager(httpPool).build();
    }

    public static String sendGet(Map<String, Object> param, String url) throws ParseException, ClientProtocolException, IOException {
        Iterator<Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            url = url + entry.getKey() + "=" + entry.getValue() + "&";
        }
        url = url.substring(0, url.length() - 1);
        System.err.println("url = " + url);
        HttpGet get = new HttpGet(url);
        get.addHeader("Charset", "UTF-8");
        String rspText = EntityUtils.toString(httpClient.execute(get).getEntity(), "utf-8");
        return rspText;
    }

    public static void sendPOST(Map<String, Object> param, String url) throws ParseException, ClientProtocolException, IOException {
        HttpPost post = new HttpPost(url);
        String requestJson = JSON.toJSONString(param);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Charset", "UTF-8");
        StringEntity paramsEntity = new StringEntity(requestJson, "UTF-8");
        post.setEntity(paramsEntity);
        String rspText = EntityUtils.toString(httpClient.execute(post).getEntity(), "utf-8");
        System.out.println("rspText = " + rspText);
    }

   /* public static String sendNewsPOST(Map<String, Object> param, String url, String token) throws ParseException, ClientProtocolException, IOException {
        HttpPost post = new HttpPost(url);
        String requestJson = JSON.toJSONString(param);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Charset", "UTF-8");
        post.addHeader("token", token);
        StringEntity paramsEntity = new StringEntity(requestJson, "UTF-8");
        post.setEntity(paramsEntity);
        String rspText = EntityUtils.toString(httpClient.execute(post).getEntity(), "utf-8");// 发送http请求并获得响应结果
        return rspText;
    }

    public static void sendGetUrl(String url) throws ParseException, ClientProtocolException, IOException {
        HttpGet get = new HttpGet(url);
        EntityUtils.toString(httpClient.execute(get).getEntity(), "utf-8");// 发送http请求并获得响应结果
    }*/
   /*
    public static CloseableHttpResponse postXml(String url, String outputEntity, boolean isLoadCert,String mchId) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(new StringEntity(outputEntity, "UTF-8"));
        
        if (isLoadCert) {
            // 加载含有证书的http请求
            return HttpClients.custom().setSSLSocketFactory(CertUtil.initCert(mchId)).build().execute(httpPost);
        } else {
            return HttpClients.custom().build().execute(httpPost);
        }
        
    }
    */
   /* public static String sendPOST1(Map<String, Object> param,String url) throws ParseException, ClientProtocolException, IOException{
    	HttpPost post = new HttpPost(url);
    	 String requestJson = JSON.toJSONString(param);
         post.addHeader("Content-Type", "application/json");
         post.addHeader("Charset", "UTF-8");
         StringEntity  paramsEntity = new StringEntity(requestJson,"UTF-8");
         post.setEntity(paramsEntity);
         String rspText = EntityUtils.toString(httpClient.execute(post).getEntity(), "utf-8"); 
         return rspText;
    }

    public static String postMultipartFile(File file,String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        //每个post参数之间的分隔。随意设定，只要不会和其他的字符串重复即可。
        String boundary ="--------------4585696313564699";
        try {
            //文件名
            String fileName = file.getName();
            HttpPost httpPost = new HttpPost(url);
            //设置请求头
            httpPost.setHeader("Content-Type","multipart/form-data; boundary="+boundary);

            //HttpEntity builder
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            //字符编码
            builder.setCharset(Charset.forName("UTF-8"));
            //模拟浏览器
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //boundary
            builder.setBoundary(boundary);
            //multipart/form-data
            builder.addPart("file",new FileBody(file));
            // binary
//            builder.addBinaryBody("name=\"multipartFile\"; filename=\"test.docx\"", new FileInputStream(file), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            //其他参数
            builder.addTextBody("filename", fileName,  ContentType.create("text/plain", Consts.UTF_8));
            //HttpEntity
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            // 执行提交
            HttpResponse response = httpClient.execute(httpPost);
            //响应
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.err.println("result"+result);
        return result;
*/

}
    
   /* public static void main(String[] args) throws ParseException, ClientProtocolException, IOException {
		
    	String url = "https://h5.51xianwan.com/adwall/api/quickEarnList?";
    	Map<String, Object> param = new HashMap<>();
    	String ptype = "2";
    	String appsign = "userId";
    	String deviceid = "imei";
    	String appid = "3580";
    	String appsecret = "z58k7rohzpjf99kmv";
    	String ketStr = appid+deviceid+ptype+appsign+appsecret;
    	System.out.println("ketStr = "+ketStr);
    	String keycode = MD5Util.getMd5(ketStr);
    	System.out.println("keycode = "+keycode);
    	param.put("ptype",ptype );
    	param.put("deviceid","imei" );
    	param.put("appid",appid );
    	param.put("appsign",appsign);
    	param.put("keycode",keycode );
    	param.put("page",1 );
    	param.put("pagesize",200);
    	
    	String resp = sendGet(param, url);
    	System.out.println("resp = "+resp);
	}*/
