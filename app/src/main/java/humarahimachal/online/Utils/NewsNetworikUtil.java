package humarahimachal.online.Utils;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewsNetworikUtil {
    private static final String API_BASE_URL = "https://newsapi.org/v2/top-headlines?";
    private static final String API_KEY = "249857e0d6544cdc8994d1eb3d2222f3";
    private static final String COUNTRY_PLACE = "country";
    private static final String COUNTRY_VAL = "in";
    private static final String CATEGORY_PLACE = "category";
    private static final String CATEGORY_VAL = "general";
    private static final String API_KEY_PLACE = "apikey";
    private static final String PAGE_SIZE_PLACE = "pageSize";
    private static final String PAGE_SIZE_VAL = "100";
    private static final String REQ_METHOD = "GET";
    private static final int REQ_TIME = 10000;

    public static URL getUrl() throws MalformedURLException {
        Uri uri = Uri.parse(API_BASE_URL);
        Uri.Builder uriBuilder = uri.buildUpon();
        uriBuilder.appendQueryParameter(COUNTRY_PLACE, COUNTRY_VAL);
        uriBuilder.appendQueryParameter(COUNTRY_PLACE, COUNTRY_VAL);
        uriBuilder.appendQueryParameter(PAGE_SIZE_PLACE, PAGE_SIZE_VAL);
        uriBuilder.appendQueryParameter(API_KEY_PLACE, API_KEY);
        URL url = new URL(uriBuilder.toString());
        return url;
    }

    public static String sendRequestToServer(URL url) {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        if (url != null) {

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod(REQ_METHOD);
                urlConnection.setReadTimeout(REQ_TIME);
                urlConnection.setConnectTimeout(REQ_TIME);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = convertInputIntoString(inputStream);

            } catch (Exception e) {

            }
        }
        return jsonResponse;
    }

    private static String convertInputIntoString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
        return stringBuilder.toString();
    }
}
