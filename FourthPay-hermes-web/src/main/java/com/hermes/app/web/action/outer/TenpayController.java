package com.hermes.app.web.action.outer;

import com.hermes.app.form.builder.RcvCardOrderFormBuilder;
import com.hermes.app.form.order.RcvCardOrderForm;
import com.hermes.app.service.order.OrderService;
import com.hermes.app.web.action.BaseController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于下载财付通订单，老业务
 *
 * @author of644
 */
@Controller
@RequestMapping("/tenpay")
public class TenpayController extends BaseController {

    // 处理中
    public static String INCHARGE_TENPAY = "-1";
    // 支付失败
    public static String FAIL_TENPAY = "0";
    // 支付成功
    public static String SUCCESS_TENPAY = "1";

    @Autowired
    private OrderService orderService;

    /**
     * 访问财付通订单
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public void index(@RequestParam String merId,
                        @RequestParam String dateStart,
                        @RequestParam String dateEnd,
                        @RequestParam String md5String, HttpServletResponse response) {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;

        try {
            // 获取商户的MD5key
            String md5key = orderService.queryUserMd5key(merId);
            String sign = DigestUtils.md5Hex(new StringBuilder().append(merId).append(dateStart).append(dateEnd).append(md5key).toString());
            // 验证签名
            if (!sign.equals(md5String)) {
                logger.error("用户编号：{}下载订单，签名错误", merId);
                return;
            }
            RcvCardOrderForm rcvCardOrderForm = RcvCardOrderFormBuilder.aRcvCardOrderForm()
                    .withUsercode("A772139")
                    .withStartTime(dateStart)
                    .withEndTime(dateEnd)
                    .build();
            // 财付通订单
            List<Map> tenpayOrders = orderService.queryOuterOrders(rcvCardOrderForm);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("rows=").append(tenpayOrders.size()).append("\r\n");
            for (Map map : tenpayOrders) {
                // 申明面值
                BigDecimal faceval = map.get("FACEVAL") == null ? new BigDecimal(0) : ((BigDecimal) map.get("FACEVAL"))
                        .multiply(new BigDecimal(100));
                // 实际面值
                BigDecimal retvalue = map.get("RETVALUE") == null ? new BigDecimal(0) : ((BigDecimal) map
                        .get("RETVALUE")).multiply(new BigDecimal(100));
                // 充值结果
                String retresult = errcode(map.get("RETRESULT").toString());

                stringBuffer.append(map.get("MERORDERNO") + "|");
                stringBuffer.append(map.get("CORPCODE") + "|");
                stringBuffer.append(map.get("CARDNO") + "|");
                stringBuffer.append(faceval + "|");
                stringBuffer.append(retvalue + "|");
                stringBuffer.append(map.get("ORDERTIME") + "|");
                stringBuffer.append(map.get("CHARGEENDTIME") + "|");
                stringBuffer.append(retresult + "|");
                stringBuffer.append(DigestUtils.md5Hex(map.get("MERORDERNO").toString() + map.get("CORPCODE")
                        + map.get("CARDNO") + faceval.toString() + retvalue.toString() + map.get("ORDERTIME")
                        + map.get("CHARGEENDTIME") + retresult + md5key));
                stringBuffer.append("\r\n");
            }

            String filename = dateStart + ".dat";
            response.setContentType("application/x-msdownload");
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            response.setContentLength(stringBuffer.toString().getBytes().length);

            inputStream = new ByteArrayInputStream(stringBuffer.toString().getBytes("UTF-8"));
            servletOutputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int i;
            while ((i = inputStream.read(buffer)) != -1){
                servletOutputStream.write(buffer, 0, i);
                servletOutputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) servletOutputStream.close();
                if (inputStream != null) inputStream.close();
            }
            catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }

    /**
     * 异常处理
     *
     * @param result
     * @return
     */
    public static String errcode(String result) {

        if ("2000".equals(result) || "2011".equals(result)) {
            return SUCCESS_TENPAY;

        }
        else if ("2001".equals(result)) {
            return INCHARGE_TENPAY;
        }
        else {
            return FAIL_TENPAY;
        }
    }

    public static void main(String[] args){
        try {
            String merId = "A772139";
            String dateStart = "2013-11-28";
            String dateEnd = "2013-11-28";

            String key = "6652F50E5D2370304AF326234306E96A22F7667B6F35EF05";
            String md5String = DigestUtils.md5Hex(merId + dateStart + dateEnd + key);
            System.out.println(md5String);
            Map<String, String> map = new HashMap<String, String>();
            map.put("merId", merId);
            map.put("dateStart", dateStart);
            map.put("dateEnd", dateEnd);
            map.put("md5String", md5String);
            HttpClient httpclient = new DefaultHttpClient();
            HttpParams params = httpclient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, 30000);
            HttpConnectionParams.setSoTimeout(params, 30000);
            HttpPost httppost = new HttpPost("http://localhost:8090/tenpay");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();// 用于存放请求参数
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            httppost.setEntity(new UrlEncodedFormEntity(nvps, "GBK"));
            HttpResponse httpResponse = httpclient.execute(httppost);
            HttpEntity httpEntity = httpResponse.getEntity();
            Header[] heads = httpResponse.getAllHeaders();
            System.out.println(heads.length);
            System.err.println(httpResponse.getAllHeaders());
            Header head2[] = httpResponse.getHeaders("Content-Disposition");
            String filename = head2[0].getValue().substring(20);
            System.out.println(filename);
            System.err.println();
            if (httpEntity != null)
            {

                File file = new File("c:/" + filename);
                FileOutputStream bos = new FileOutputStream(file);
                InputStream in = httpEntity.getContent();
                byte[] buf = new byte[4096];
                int k = 0;
                while ((k = in.read(buf)) != -1) {
                    bos.write(buf, 0, k);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
