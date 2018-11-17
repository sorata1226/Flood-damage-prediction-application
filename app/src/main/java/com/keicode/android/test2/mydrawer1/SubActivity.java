package com.keicode.android.test2.mydrawer1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class SubActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

        private static final String[] scenes = {
                "岡山市",
                "倉敷市",
                "津山市",
                "玉野市",
                "笠岡市",
                "井原市",
                "総社市",
                "高梁市",
                "新見市",
                "備前市",
                "瀬戸内市",
                "赤磐市",
                "真庭市",
                "美作市",
                "浅口市",
                "和気町",
                "早島町",
                "里定町",
                "矢掛町",
                "新庄村",
                "鏡野町",
                "勝央町",
                "奈義町",
                "西粟倉村",
                "久米南町",
                "美咲町",
                "吉備中央町",
        };

        private static final String[] scenes2 = {
                "広島市",
                "呉市",
                "竹原市",
                "三原市",
                "尾道市",
                "福山市",
                "府中市",
                "三次市",
                "庄原市",
                "大竹市",
                "東広島市",
                "廿日市市",
                "安芸高田市",
                "江田島市",
                "府中町",
                "海田町",
                "熊野町",
                "坂町",
                "安芸太田町",
                "北広島町",
                "大崎上島町",
                "大崎上島町",
                "世羅町",
                "神石高原町"
        };

        // ちょっと冗長的ですが分かり易くするために




        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_sub);

                String[] city =null;

                if(getIntent().getStringExtra("Ken").equals("岡山県")) {
                        city=scenes;

                }
                else if(getIntent().getStringExtra("Ken").equals("広島県")) {
                        city=scenes2;

                }

                // ListViewのインスタンスを生成
                ListView listView = findViewById(R.id.list_view);

                // BaseAdapter を継承したadapterのインスタンスを生成
                // レイアウトファイル list.xml を activity_main.xml に
                // inflate するためにadapterに引数として渡す
                BaseAdapter adapter = new ListViewAdapter2(this.getApplicationContext(),
                        R.layout.list, city);

                // ListViewにadapterをセット
                listView.setAdapter(adapter);

                // クリックリスナーをセット
                listView.setOnItemClickListener(this);

                //クリック後の処理



        }

        @Override
        public void onItemClick(AdapterView<?> parent, View v,
                                int position, long id) {

                Intent intent = new Intent(
                        getApplicationContext(), MapActivity.class);

                 //clickされたpositionのtextとphotoのID
                String Ken = getIntent().getStringExtra("Ken");
                if(Ken.equals("岡山県")) {
                        if (position == 1) {
                                String selectedText = scenes[1];
                                intent.putExtra("city", "倉敷市");
                                startActivity(intent);
                                //int selectedPhoto = photos[position];
                                // インテントにセット30
                        } else {
                                String selectedText = null;
                        }
                }
                else if(Ken.equals("広島県")){
                        if(position==0){
                                intent.putExtra("city", "広島市");
                                startActivity(intent);
                        }
                        else if(position==1){
                                intent.putExtra("city", "呉市");
                                startActivity(intent);
                        }
                        else{

                        }
                }

                //intent.putExtra("Text", selectedText);
                //intent.putExtra("Photo", selectedPhoto);


                // SubActivityへ遷移
                //startActivity(intent);
        }

}