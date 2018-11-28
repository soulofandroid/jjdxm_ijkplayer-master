package com.dou361.jjdxm_ijkplayer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnPlayerBackListener;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;


/**
 * ========================================
 * 描 述：点播全屏竖屏场景
 * ========================================
 */
public class PlayerActivity extends Activity {

    private PlayerView player;
    private Context mContext;
    private View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        rootView = getLayoutInflater().from(this).inflate(R.layout.simple_player_view_player, null);
        setContentView(rootView);
        String url = "http://v.stu.126.net/mooc-video/nos/mp4/2017/02/28/1005857376_97fc8a25c26548deb292e92e93b11de8_hd.mp4?ak=99ed7479ee303d1b1361b0ee5a4abceeb9b87e3891ded038a1fe95728b79762a9021ef41520b51aa76118833921ccac64af0bdd7a1c1a8142b45237805f97b2fdc73ca898c2e909ad081fb3bc930be9adae3070f62128a029f5d4c798f8df62e8d527559dfc298f46da8ad5f53c2548f0797ab0443b0ce0c1b07fa8415bc5e265f33a26f5b1c3ca6c5fb7b88ad4b8b179b7ad6f2c848f1a32a51c2df2fed3c1e9e78811ec85e3d19ea763a2749a2e3af";
        player = new PlayerView(this, rootView)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .hideMenu(true)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(mContext)
                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                .placeholder(R.color.cl_default)
                                .error(R.color.cl_error)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(url)
                .setPlayerBackListener(new OnPlayerBackListener() {
                    @Override
                    public void onPlayerBack() {
                        //这里可以简单播放器点击返回键
                        finish();
                    }
                })
                .startPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

}
