package com.trendyol.webautomation.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

public class PageUtils {

    private static CloseableHttpClient httpClient;

    private PageUtils() {
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    // https://mkyong.com/java/apache-httpclient-examples/
    public static boolean existsImage(String url) {
        try {
            HttpGet request = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(request);
            int status = response.getStatusLine().getStatusCode();
            response.close();
            return status != 404;
        } catch (Exception exception) {
            if (exception.getMessage().contains("connect timed out") || exception.getMessage().contains("read timed out")) {
                return existsImage(url);
            }
            throw new RuntimeException(exception.getMessage());
        }
    }

    static {
        try {
            // 15 seconds timeout
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(15000)
                    .setConnectTimeout(15000)
                    .setSocketTimeout(15000)
                    .build();
            // https://stackoverflow.com/questions/30078081/unable-to-find-valid-certification-path-to-requested-target-java
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(new SSLConnectionSocketFactory(SSLContexts.custom()
                            .loadTrustMaterial(null,(chain, authType) -> true)
                            .build())
                    )
                    .setDefaultRequestConfig(requestConfig)
                    .build();
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
