package com.nxc.components;

import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class MomoService {

    private static final String MomoEndpoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
    private static final String PartnerCode = "MOMO";
    private static final String AccessKey = "F8BBA842ECF85";
    private static final String SecretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";

    public String createSignature(String rawData) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(SecretKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Hex.encodeHexString(sha256_HMAC.doFinal(rawData.getBytes()));
    }


    public String createPaymentRequest(String orderId, float amount, String returnUrl) throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(MomoEndpoint);

        String orderInfo = "Thanh toán đơn hàng";
        String notifyUrl = "http://localhost:3000/payment";
        String requestType = "captureMoMoWallet";

        String rawSignature = "partnerCode=" + PartnerCode
                + "&accessKey=" + AccessKey
                + "&requestId=" + orderId
                + "&amount=" + String.valueOf((int) amount)
                + "&orderId=" + orderId
                + "&orderInfo=" + orderInfo
                + "&returnUrl=" + returnUrl
                + "&notifyUrl=" + notifyUrl
                + "&extraData=";  // Phải để extraData là rỗng như trong JSON

        String signature = createSignature(rawSignature);

        String json = "{"
                + "\"partnerCode\": \"" + PartnerCode + "\","
                + "\"accessKey\": \"" + AccessKey + "\","
                + "\"requestId\": \"" + orderId + "\","
                + "\"amount\": \"" + String.valueOf((int) amount) + "\","
                + "\"orderId\": \"" + orderId + "\","
                + "\"orderInfo\": \"" + orderInfo + "\","
                + "\"returnUrl\": \"" + returnUrl + "\","
                + "\"notifyUrl\": \"" + notifyUrl + "\","
                + "\"extraData\": \"\","
                + "\"requestType\": \"" + requestType + "\","
                + "\"signature\": \"" + signature + "\""
                + "}";

        post.setEntity(new StringEntity(json, "UTF-8"));
        post.setHeader("Content-Type", "application/json");

        HttpResponse response = client.execute(post);
        return EntityUtils.toString(response.getEntity());
    }

}