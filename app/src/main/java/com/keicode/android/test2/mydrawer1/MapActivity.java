package com.keicode.android.test2.mydrawer1;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import com.google.android.gms.maps.model.LatLng;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback {
    MapFragment mf;
    private GoogleMap mMap = null;

    private double[][][] areaLocation = new double[][][]{
            {// ここから広島市
                    {34.535543, 132.526091},    // 安佐北
                    {34.396695, 132.589161}     // 安芸区
            },
            {// ここから呉市
                    {34.290556, 132.516490},    // 天応地区
                    {34.273131, 132.517015},    // 吉浦地区
                    {34.281819, 132.746108},    // 安浦地区
                    {34.251354, 132.567418},    // 中央地区
                    {34.244917, 132.589641},    // 阿賀地区
                    {34.188103, 132.534023},    // 音戸地区
                    {34.188723, 132.717386}     // 蒲刈地区
            },
            {// ここから倉敷市
                    {34.631008, 133.695578}    // 真備町
            }
    };

    // 各地区の名称
    private String[][] areaName = new String[][]{
            {// ここから広島市
                    "安佐北",
                    "安芸区"
            },
            {// ここから呉市
                    "天応地区",
                    "吉浦地区",
                    "安浦地区",
                    "中央地区",
                    "阿賀地区",
                    "音戸地区",
                    "蒲刈地区"
            },
            {// ここから倉敷市
                    "真備町"
            }
    };

    // 各地区の情報
    private String[][] areaInfo = new String[][]{
            {// ここから広島市
                    // 安佐北
                    "住宅被害：流出家屋6棟程度(安佐北区口田南三丁目)\n" +
                            "断水被害：安佐北区白木・狩留家・小河原地区 2,370 戸\n" +
                            "土砂災害情報：安佐北区口田南地区で発生",
                    // 安芸区
                    "住宅被害：\n" +
                            "　- 流出家屋8棟程度(安芸区上瀬野町)\n" +
                            "　- 流出家屋3棟程度(安芸区畑賀二丁目)\n" +
                            "断水被害：" +
                            "　- 安芸区矢野地区 900 戸\n" +
                            "　- 安芸区瀬野川地区 400 戸\n" +
                            "　- 安芸区阿戸地区 100 戸\n" +
                            "停電被害：安芸区瀬野川地区 400 戸"
            },
            {// ここから呉市
                    // 天応地区
                    "人的被害：死者 １１名 行方不明 １名\n" +
                            "　- 天応西条３丁目 土石流，５名死亡\n" +
                            "　- 天応西条４丁目 土石流，４名死亡\n" +
                            "　- 天応南町 土石流，１名死亡\n" +
                            "　- 天応西条３丁目 海上にて発見，１名死亡\n" +
                            "　- 大屋大川（天応西条４丁目） 避難所へ移動中，川へ転落１名行方不名\n" +
                            "断水被害：呉市内全域",
                    // 吉浦地区
                    "人的被害：死者 ３名 \n" +
                            "　- 吉浦新出町 土砂崩れ３名死亡\n" +
                            "断水被害：呉市内全域",
                    // 安浦地区
                    "人的被害：死者 ４名 \n" +
                            "　- 安浦町中畑 土砂に流され３名死亡\n" +
                            "　- 安浦町下垣内 土砂崩れ・搬送先で１名死亡確認\n" +
                            "断水被害：呉市内全域",
                    // 中央地区
                    "人的被害：死者 ２名 \n" +
                            "　- 西畑町 土砂崩れ１名死亡\n" +
                            "　- 上二河町 １名死亡\n" +
                            "断水被害：呉市内全域",
                    // 阿賀地区
                    "人的被害：死者 １名 \n" +
                            "　- 阿賀南９丁目 土砂崩れ１名死亡\n" +
                            "断水被害：呉市内全域",
                    // 音戸地区
                    "人的被害：死者 ２名\n" +
                            "　- 音戸町早瀬２丁目 土砂崩れ１名死亡 \n" +
                            "  - 搬送先で１名死亡確認\n" +
                            "断水被害：呉市内全域",
                    // 蒲刈地区
                    "人的被害：死者 １名\n" +
                            "　- 蒲刈町田戸 土砂崩れ１名死亡\n" +
                            "断水被害：呉市内全域\n"
            },
            {// ここから倉敷市
                    // 真備町
                    "人的被害：死者 49名\n"+
                            "　- 一時1,400名孤立\n"+
                            "建物被害：\n"+
                            "　- 建物浸水被害 約4,600名\n"+
                            "　- 全壊 約2,100棟"
            }
    };
    /* ここまで全データ */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mf = MapFragment.newInstance();
        mf.getMapAsync(this);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(android.R.id.content, mf);
        ft.commit();
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String city = getIntent().getStringExtra("city");

        // マーカー設置処理
        for (int a = 0; a < areaLocation.length; a++) {
            for (int i = 0; i < areaLocation[a].length; i++) {
                LatLng aLocation = new LatLng(areaLocation[a][i][0], areaLocation[a][i][1]);

                // マーカー設定
                MarkerOptions options = new MarkerOptions();
                options.position(aLocation);

                // アイコンの変更
                // (1) 色選択
                BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                // (2) リソースのアイコン画像
                // BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher);
                options.icon(icon);

                // マーカー情報（フキダシ）設定
                options.title(areaName[a][i]);
                options.snippet(areaInfo[a][i]);

                mMap.addMarker(options);    // マーカー設置
                Marker marker = mMap.addMarker(options);

                // マーカー情報(フキダシ)のカスタマイズ
                /*InfoWindowViewer iwv = new InfoWindowViewer(this);
                iwv.getInfoWindow(marker);
                iwv.getInfoContents(marker);
                */
            }

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker marker) {

                    // TODO Auto-generated method stub
                    //Toast.makeText(getApplicationContext(), "マーカータップ", Toast.LENGTH_LONG).show();

                    marker.showInfoWindow();    // マーカー情報（フキダシ）表示

                    return false;
                }
            });


            // 呉市の座標
            if (city.equals("倉敷市")) {
                double yLocation = 34.585013;
                double xLocation = 133.772084;
                LatLng location = new LatLng(yLocation, xLocation);
                // カメラ移動
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            } else if (city.equals("広島市")) {
                double yLocation = 34.385203;
                double xLocation = 132.455293;
                LatLng location = new LatLng(yLocation, xLocation);
                // カメラ移動
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
            } else if (city.equals("呉市")) {
                double yLocation = 34.248646;
                double xLocation = 132.565441;
                LatLng location = new LatLng(yLocation, xLocation);
                // カメラ移動
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13));
            }


            try {
                GeoJsonLayer layer = new GeoJsonLayer(mMap, R.raw.map, getApplicationContext());

                layer.getDefaultPolygonStyle().setFillColor(Color.argb(50, 0, 255, 213));
                //GeoJsonPolygonStyle polygonStyle = new GeoJsonPolygonStyle();
                layer.addLayerToMap();
                //layer.getDefaultPolygonStyle().setFillColor(Color.RED);GeoJsonPolygonStyle polygonStyle = new GeoJsonPolygonStyle();
                //polygonStyle.setPolygonFillColor(Color.BLUE);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }


}
