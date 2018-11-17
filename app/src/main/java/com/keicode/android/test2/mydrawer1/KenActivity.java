package com.keicode.android.test2.mydrawer1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class KenActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private static final String[] scenes = {

            "北海道",
            "北海道",
            "東北",
            "青森県",
            "秋田県",
            "岩手県",
            "山形県",
            "福島県",
            "関東",
            "神奈川県",
            "埼玉県",
            "千葉県",
            "茨城県",
            "栃木県",
            "群馬県",
            "中部",
            "山梨県",
            "長野県",
            "新潟県",
            "愛知県",
            "静岡県",
            "岐阜県",
            "富山県",
            "石川県",
            "福井県",
            "近畿",
            "三重県",
            "大阪府",
            "京都府",
            "兵庫県",
            "滋賀県",
            "奈良県",
            "和歌山県",
            "中国",
            "岡山県",
            "広島県",
            "鳥取県",
            "島根県",
            "山口県",
            "四国",
            "徳島県",
            "香川県",
            "愛媛県",
            "高知県",
            "九州",
            "福岡県",
            "佐賀県",
            "長崎県",
            "熊本県",
            "大分県",
            "宮崎県",
            "鹿児島県",
            "沖縄県"
    };

    // ちょっと冗長的ですが分かり易くするために

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        // ListViewのインスタンスを生成
        ListView listView = findViewById(R.id.list_view);

        // BaseAdapter を継承したadapterのインスタンスを生成
        // レイアウトファイル list.xml を activity_main.xml に
        // inflate するためにadapterに引数として渡す
        BaseAdapter adapter = new ListViewAdapter(this.getApplicationContext(),
                R.layout.list, scenes);

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
                    getApplicationContext(), SubActivity.class);
            if(position==34) {


                // clickされたpositionのtextとphotoのID
                //String selectedText = scenes[position];
                //int selectedPhoto = photos[position];
                // インテントにセット30

                intent.putExtra("Ken", "岡山県");
                startActivity(intent);
            }
            else if(position==35) {
                    intent.putExtra("Ken", "広島県");
                startActivity(intent);
                }


                    // SubActivityへ遷移
                    //startActivity(intent);

        }

    }