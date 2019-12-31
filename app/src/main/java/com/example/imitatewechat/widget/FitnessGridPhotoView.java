package com.example.imitatewechat.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @ProjectName: ImitateWeChat
 * @Package: com.example.imitatewechat.widget
 * @ClassName: FitnessGridPhotoView
 * @Description: java类作用描述 自定义网格图片展示控件
 * @Author: 作者名 itfitness
 * @CreateDate: 2019/12/27 10:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/12/27 10:13
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class FitnessGridPhotoView extends LinearLayout {
    private boolean mIsLayout = false;//是否布局过了
    private List<String> mDatas;
    private OnItemClickListener onItemClickListener;
    public FitnessGridPhotoView(Context context) {
        this(context,null);
    }
    public FitnessGridPhotoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public FitnessGridPhotoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayoutParams();
        initListener();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<String> getDatas() {
        return mDatas;
    }

    /**
     * 设置数据
     * @param mDatas
     */
    public void setDatas(List<String> mDatas) {
        this.mDatas = mDatas;
        //此控件是否已经添加到Window上了,如果没有则要等到添加到Window上之后再设置数据,否则布局会出问题
        if(mIsLayout){
            if(getChildCount()>0){
                removeAllViews();
            }
            if(mDatas != null && mDatas.size()>0){
                initDatas2View();
            }
        }
    }
    private void initListener() {
        post(new Runnable() {
            @Override
            public void run() {
                //当可以获取到控件的宽高时再填充数据
                mIsLayout = true;
                if(getChildCount()>0){
                    removeAllViews();
                }
                if(mDatas != null && mDatas.size()>0){
                    initDatas2View();
                }
            }
        });
    }

    /**
     * 初始化布局信息
     */
    private void initLayoutParams() {
        //将最外层的LinearLayout布局设置为竖向排版的
        ViewGroup.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setOrientation(VERTICAL);
    }

    private void initDatas2View(){
        //二级布局的配置信息
        LinearLayout.LayoutParams layoutParamsContainer = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsContainer.topMargin = 10;//顶边距为10
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(layoutParamsContainer);
        linearLayout.setOrientation(HORIZONTAL);//每一行的布局为横向

        //ImageView的布局信息
        LinearLayout.LayoutParams layoutParamsImg = new LinearLayout.LayoutParams(0, 0);
        layoutParamsImg.width = getWidth()/3;//宽度为整个控件的三分之一
        layoutParamsImg.height = getWidth()/3;//宽高一样
        addView(linearLayout);
        for (int i = 0; i < mDatas.size(); i++) {
            if(i % 3 == 0 && i!=0){
                linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(HORIZONTAL);
                linearLayout.setLayoutParams(layoutParamsContainer);
                addView(linearLayout);
            }
            ImageView imageView = new ImageView(getContext());
            imageView.setPadding(10,0,10,0);//图片的左右内边距为10
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//图片为填充XY
            imageView.setLayoutParams(layoutParamsImg);
            //给每个Item设置点击事件
            if(onItemClickListener!=null){
                final int finalI = i;
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(finalI);
                    }
                });
            }
            Glide.with(getContext()).load(mDatas.get(i)).into(imageView);
            linearLayout.addView(imageView);
        }
    }

    /**
     * 没个条目的点击事件
     */
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
