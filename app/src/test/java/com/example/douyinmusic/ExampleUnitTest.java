package com.example.douyinmusic;

import com.example.douyinmusic.client.Client;
import com.example.douyinmusic.client.Lyric;
import com.example.douyinmusic.client.TaskCompleteCallback;
import com.example.douyinmusic.model.lyric.JSONLyric;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    String lyricDemo = "[00:00.000] 作曲 : 陈耀川 [00:01.000] 作词 : 李安修 [00:12.880]编曲:余佳运 [00:16.970] [00:18.970]我有花一朵种在我心中 [00:26.520]含苞待放意幽幽 [00:33.890]朝朝与暮暮我切切地等候 [00:41.020]有心的人来入梦 [00:46.620] [00:52.010]我有花一朵长在我心中 [00:59.250]真情真爱无人懂 [01:06.909]遍地的野草已占满了山坡 [01:13.549]孤芳自赏最心痛 [01:19.420] [01:20.450]女人花摇曳在红尘中 [01:27.830]女人花随风轻轻摆动 [01:34.830]只盼望有一双温柔手 [01:42.180]能抚慰她内心的寂寞 [01:48.300] [01:50.239]我有花一朵花香满枝头 [01:57.319]谁来真心寻芳踪 [02:04.799]花开不多时啊堪折直须折 [02:11.879]女人如花花似梦 [02:17.439] [02:47.919]女人花摇曳在红尘中 [02:54.800]女人花随风轻轻摆动 [03:02.039]若是你闻过了花香浓 [03:09.539]别问她花儿是为谁红 [03:16.430] [03:21.539]爱过知情重醉过知酒浓 [03:28.789]花开花谢终是空 [03:35.639]缘份不停留像春风来又走 [03:42.819]女人如花花似梦 [03:50.240]女人如花花似梦 [03:57.509] [03:58.669]吉他：孙宇辰 [03:59.560]Bass：彭宏立(Simon) [04:00.360]键盘：余佳运 [04:01.000]和声编写：余佳运 [04:01.479]混音：余佳运 [04:02.310]母带：沈会斌@NEWBAND STUDIO [04:03.139]封面设计：何谐@HasimStudio [04:03.800]制作人：余佳运 [04:04.620]企划：陈尚禔/潘俊/黄果璇 [04:05.199]策划：陈莹 [04:06.030]监制：唐晶晶 [04:06.800]特别企划：网易云音乐「复刻」 [04:07.800]OP：Universal Music Publishing Ltd. Taiwan/Great Grey Bear Ent. Ltd. [04:08.699]SP：美希亚音乐版权代理（北京）有限公司 [04:09.699] ";

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void parseLyric(){

    }

    @Test
    public void testLyric(){
        Client.getLyric(new TaskCompleteCallback<JSONLyric>() {
            @Override
            public void completed(JSONLyric res) {
                System.out.println(res.getLrc().getLyric());
            }
        });
    }
}

/**
 * 根据当前进度查找歌词
 */
