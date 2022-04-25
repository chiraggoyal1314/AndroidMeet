package org.mediasoup.droid.demo;

import static android.content.ContentValues.TAG;
import static org.apache.http.conn.ssl.SSLSocketFactory.SSL;

import com.google.gson.GsonBuilder;

import org.mediasoup.droid.Logger;
import org.mediasoup.droid.MediasoupClient;
import org.mediasoup.droid.model.meetingModels.Body;
import org.mediasoup.droid.utils.MobileUtils;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Application extends android.app.Application {

    public static Retrofit retrofit;
    //  private static String BASE_URL = "https://s183.lsinternal.com:4443";
    private static String BASE_URL = "https://192.168.1.183:4443";
    public static Body body;

    @Override
    public void onCreate() {
        super.onCreate();
        body = new Body();
        Logger.setLogLevel(Logger.LogLevel.LOG_DEBUG);
        Logger.setDefaultHandler();
        MediasoupClient.initialize(getApplicationContext());
//                .addConverterFactory(GsonConverterFactory.create(gson))
        OkHttpClient okHttpClient = MobileUtils.getUnsafeOkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).build();
    }
}
