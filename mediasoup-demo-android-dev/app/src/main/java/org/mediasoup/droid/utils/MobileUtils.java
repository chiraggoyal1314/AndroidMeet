package org.mediasoup.droid.utils;

import static android.content.ContentValues.TAG;
import static org.apache.http.conn.ssl.SSLSocketFactory.SSL;

import android.content.Context;
import android.widget.Toast;

import org.mediasoup.droid.Logger;
import org.mediasoup.droid.demo.vm.LoginActivity;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.cert.CertificateException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MobileUtils {
    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts =
                    new TrustManager[] {
                            new X509TrustManager() {

                                @Override
                                public void checkClientTrusted(
                                        java.security.cert.X509Certificate[] chain, String authType)
                                        throws CertificateException {}

                                @Override
                                public void checkServerTrusted(
                                        java.security.cert.X509Certificate[] chain, String authType)
                                        throws CertificateException {}

                                // Called reflectively by X509TrustManagerExtensions.
                                public void checkServerTrusted(
                                        java.security.cert.X509Certificate[] chain, String authType, String host) {}

                                @Override
                                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                    return new java.security.cert.X509Certificate[] {};
                                }
                            }
                    };

            final SSLContext sslContext = SSLContext.getInstance(SSL);
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            HttpLoggingInterceptor httpLoggingInterceptor =
                    new HttpLoggingInterceptor(s -> Logger.d(TAG, s));
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient.Builder builder =
                    new OkHttpClient.Builder()
                            .addInterceptor(httpLoggingInterceptor)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true);
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);

            builder.hostnameVerifier((hostname, session) -> true);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void notifyUserShort(@NonNull Context context, String msg){
        Toast.makeText(context , msg, Toast.LENGTH_SHORT).show();
    }
    public static void notifyUserLong(@NonNull Context context, String msg){
        Toast.makeText(context , msg, Toast.LENGTH_LONG).show();
    }
}
