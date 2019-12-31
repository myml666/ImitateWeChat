package com.example.imitatewechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.imitatewechat.widget.FitnessGridPhotoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter;
    private ArrayList<String> mImgDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
    }

    private void initDatas() {
        ArrayList<String> datas = new ArrayList<>();
        for (int  i = 0 ; i < 10 ; i++){
            datas.add(i+"");
        }
        if(baseQuickAdapter == null){
            baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_photo,datas) {
                @Override
                protected void convert(BaseViewHolder helper, String item) {
                    FitnessGridPhotoView fitnessGridPhotoView = helper.getView(R.id.fgp);
                    fitnessGridPhotoView.setDatas(getImgDatas());
                }
            };
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(baseQuickAdapter);
        }
    }
    private List<String> getImgDatas(){
        if(mImgDatas == null){
            mImgDatas = new ArrayList<>();
        }
        if(mImgDatas.isEmpty()){
            mImgDatas.add("http://img2.imgtn.bdimg.com/it/u=3841233814,3418116998&fm=26&gp=0.jpg");
            mImgDatas.add("http://img0.imgtn.bdimg.com/it/u=206739227,3502333159&fm=26&gp=0.jpg");
            mImgDatas.add("http://img5.imgtn.bdimg.com/it/u=2557941754,291179557&fm=26&gp=0.jpg");
            mImgDatas.add("http://img1.imgtn.bdimg.com/it/u=1413069676,3048257011&fm=26&gp=0.jpg");
            mImgDatas.add("http://img1.imgtn.bdimg.com/it/u=2890345719,416085154&fm=26&gp=0.jpg");
            mImgDatas.add("http://img1.imgtn.bdimg.com/it/u=2701264944,342503977&fm=26&gp=0.jpg");
        }
        return mImgDatas;
    }
    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
    }


}
