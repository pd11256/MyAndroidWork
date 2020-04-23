package com.lec.android.a015_web;
 /*
 * XML , JSON 파싱 연습
 *
 * ■서울시 지하철 역사 정보
      http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

      샘플url


      XML 버젼
      http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/서울


      JSON 버젼
      http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/서울


 *      statnNm(STring), subwayId(int), subwayNm(String)
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Main3Activity extends AppCompatActivity {

    Button btnXML;
    Button btnJSOn;
    Button btnParse;
    EditText et;
    static TextView tv;
    StringBuffer sb;
    static String str= "";
    int ex ;
    static Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    btnXML =findViewById(R.id.btnXML);
    btnJSOn =findViewById(R.id.btnJSON);
    btnParse =findViewById(R.id.btnParse);
    et =findViewById(R.id.editText);
    tv =findViewById(R.id.tvResult);

//    wv.getSettings().setJavaScriptEnabled(true);
//    sb = readFromUrl(url_address);


        btnXML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {final String urlStr;
                    ex =1;
                     urlStr = buildUrlAddress("xml",et.getText().toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            request(urlStr);

                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        btnJSOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {final String urlStr;
                    ex=2;
                    urlStr = buildUrlAddress("json",et.getText().toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            request(urlStr);

                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

       btnParse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              // if(ex==1) parseXML(tv.getText().toString());
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try{
                           if(ex==1) {
                               parseXML(tv.getText().toString());
                           }
                           else {
                               parseJSON(tv.getText().toString());
                           }

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               });
           }
       });





    }//end onCreate

    public static String buildUrlAddress(String reqType,String search  ) throws IOException {

        String url_address = String.format(
                "http://swopenapi.seoul.go.kr/api/subway/75417a574a7064313436764c44596c/%s/stationInfo/1/5/%s",

                reqType,URLEncoder.encode(search, "utf-8"));
        return url_address;

    }



    public void request(String urlStr){
        final StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection();

            if(conn != null){
                conn.setConnectTimeout(5000); //timeout 시간 설정. 경과하면 SocketTimeoutException 발생
                conn.setUseCaches(false); //캐시 사용 안함.
                conn.setRequestMethod("GET"); //GET 방식 request

                conn.setDoInput(true); //URLConnection 을 입력으로사용 true , false -> 출력용

                int responseCode = conn.getResponseCode(); //response code 값. 성공하면 200

                if(responseCode == HttpURLConnection.HTTP_OK){// 200 HTTP_OK

                    reader =new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line =null;
                    while(true){
                        line = reader.readLine();
                        if(line ==null) break;
                        sb.append(line + "\n");
                    } //end while
                }//end if
            }//end if

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) reader.close();
                if(conn !=null) conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(sb.toString());
            }
        });



    }




    //4. XML 파싱
    public void parseXML(String xmlText) {
        // XML 파싱
        // 여기서 import 는 w3c.dom 으로 다하기!
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;


        try {
            // DOM parser 객체 생성
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();

            // String -> InputStream 변환
            InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));

            // InputStream -> DOM 객체 생성
            Document dom = dBuilder.parse(in);

            // DOM 최상위 document element 추출
            Element docElement = dom.getDocumentElement(); // DOM의 최상위 element
            // 파싱하기전 normalize()
            docElement.normalize(); // 흩어진 text node들을 정렬하는 등의 절차,
            // XML파싱하기전에 꼭 normalize()부터 해주자

            // DOM 내의 데이터 파싱
            NodeList nList = docElement.getElementsByTagName("row"); // <row>..</row> element 들로 구성된 NodeList 리턴 nList에는
            // 5개가저장된다
            String str ="";
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i); // for문 하나하나 <>row>...</row> 출력 이노드안에서 호선 ,역 인원 등 4개 찾아내야함

                // System.out.println("node type : " + node.getNodeType()); //상수값으로 정해져있음 1번
                // 노트타입

                // element node 인 경우만 파싱 진행
                if (node.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element rowElement = (Element) node;// 부모가 왼쪽이면 (Elemnet)필요없다 ! 여기선 부모가 오른쪽이기때문에 (Element)변환해준것이다.! 다형성

                // rowElement.getTextContent(); 도있음

                String statnId = rowElement.getElementsByTagName("statnNm").item(0).getChildNodes().item(0)
                        .getNodeValue().trim(); // 자바에선 elements 밖에안쓴다 element ㄴㄴ
                String subwayId = rowElement.getElementsByTagName("subwayId").item(0).getChildNodes().item(0)
                        .getNodeValue().trim(); // 자바에선 elements 밖에안쓴다 element ㄴㄴ
                String subwayNm = rowElement.getElementsByTagName("subwayNm").item(0).getChildNodes().item(0)
                        .getNodeValue().trim(); // 자바에선 elements 밖에안쓴다 element ㄴㄴ

                  str =  str + String.format("%d: %5s역  %6s %6s\n", i + 1, statnId, subwayId, subwayNm);


            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }handler.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(str);
            }
        });


    }

    public static void parseJSON(String jsonText) throws JSONException {

        JSONObject jObj = new JSONObject(jsonText); // JSON파싱 : JSONObject <- String

        JSONArray row = jObj.getJSONArray("stationList");// jason 배열을가져온것
        // 오브젝트에서 오브젝트뽑을떄
        System.out.println("row 의 개수: " + row.length());

        System.out.println();

        for (int i = 0; i < row.length(); i++) {
            JSONObject station = row.getJSONObject(i); // 배열에서 매개변수뽀ㅃㅂ을때

            String statnNm = station.getString("statnNm");
            String subwayId = station.getString("subwayId");
            String subwayNm = station.getString("subwayNm");

            str = str+String.format("%d: %5s역  %6s %6s\n", i + 1, statnNm, subwayId, subwayNm);

        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(str);
            }
        });

    }



}//end on// class
