package com.leon.excel.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.leon.api.utils.CollectionUtils;
import com.leon.excel.vo.ExcelDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class ExcelDemoListener extends AnalysisEventListener<ExcelDemo> {

    /**
     * 每隔100条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 10000;
    List<ExcelDemo> list = new ArrayList<>();


    @Override
    public void invoke(ExcelDemo data, AnalysisContext analysisContext) {
        // log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            disposeData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        disposeData();
        log.info("所有数据解析完成！");
    }

    /*
    stickers	other	rolls
    stickers	other	other
    stickers	paper	rolls
    stickers	paper	wallpaper
    stickers	paper	other

    tapestry	polyester
    tapestry	silk
    tapestry	other
    canvas painting	polyester
    canvas painting	silk
    canvas painting	other
    poster	paper
    wooden plaque,wooden sign	wood
    sign	metal,tin

    DESC_CN	HSCODE_CN
    "塑料成卷贴纸","3919109900"
    "塑料贴纸","3919909090"












     */
    public static Map<String,List<List<String>>> map = new HashMap<>();
    public static List<List<String>> mList = new ArrayList();
    static{
        /*List<String> mmList = new ArrayList(Arrays.asList("stickers", "paper", "rolls","纸制成卷贴纸", "4811410000"));
        List<String> mmList2 = new ArrayList(Arrays.asList("stickers", "paper", "wallpaper","纸制墙纸","4814900090"));
        List<String> mmList3 = new ArrayList(Arrays.asList("stickers", "paper", "other","纸制贴纸","4911999090"));
        List<String> mmList4 = new ArrayList(Arrays.asList("stickers", "other", "rolls","塑料成卷贴纸","3919109900"));
        List<String> mmList5 = new ArrayList(Arrays.asList("stickers", "other", "other","塑料贴纸","3919909090"));
        List<String> mmList6 = new ArrayList(Arrays.asList("tapestry", "polyester","合纤挂毯","6304939000"));
        List<String> mmList7 = new ArrayList(Arrays.asList("tapestry", "silk","丝制挂毯","6304991090"));
        List<String> mmList8 = new ArrayList(Arrays.asList("tapestry", "other","挂毯","6304999000"));
        List<String> mmList9 = new ArrayList(Arrays.asList("canvas painting", "polyester","合纤帆布画","6304939000"));
        List<String> mmList10 = new ArrayList(Arrays.asList("canvas painting", "silk","丝制布画","6304991090"));
        List<String> mmList11 = new ArrayList(Arrays.asList("canvas painting", "other","帆布画","6304999000"));
        List<String> mmList12 = new ArrayList(Arrays.asList("poster", "paper","海报","4911910090"));
        List<String> mmList13 = new ArrayList(Arrays.asList("wooden plaque,wooden sign", "wood","装饰木牌","4420109090"));
        List<String> mmList14 = new ArrayList(Arrays.asList("sign", "metal,tin","金属标牌","8310000000"));
        mList.add(mmList);
        mList.add(mmList2);
        mList.add(mmList3);
        mList.add(mmList4);
        mList.add(mmList5);
        mList.add(mmList6);
        mList.add(mmList7);
        mList.add(mmList8);
        mList.add(mmList9);
        mList.add(mmList10);
        mList.add(mmList11);
        mList.add(mmList12);
        mList.add(mmList13);
        mList.add(mmList14);
        map.put("wall art", mList);*/
        /*List<String> mmList = new ArrayList(Arrays.asList("925|sterling silver","银项链", "7113119090"));
        List<String> mmList2 = new ArrayList(Arrays.asList("stainless steel|alloy|brass|aluminum|titanium steel|24K|18K|14K", "金属制项链","7117190000"));
        List<String> mmList3 = new ArrayList(Arrays.asList("plastic", "塑料制项链","7117900000"));
        List<String> mmList4 = new ArrayList(Arrays.asList("other", "项链","7117900000"));
        mList.add(mmList);
        mList.add(mmList2);
        mList.add(mmList3);
        mList.add(mmList4);
        map.put("necklaces", mList);*/

        /*List<String> mmList = new ArrayList(Arrays.asList("925|sterling silver","银手镯", "7113119090"));
        List<String> mmList2 = new ArrayList(Arrays.asList("stainless steel|alloy|brass|aluminum|titanium steel|24K|18K|14K", "金属制手镯","7117190000"));
        List<String> mmList3 = new ArrayList(Arrays.asList("plastic", "塑料制手镯","7117900000"));
        List<String> mmList4 = new ArrayList(Arrays.asList("other", "手镯","7117900000"));
        mList.add(mmList);
        mList.add(mmList2);
        mList.add(mmList3);
        mList.add(mmList4);
        map.put("bracelets", mList);*/

        /*List<String> mmList = new ArrayList(Arrays.asList("925|sterling silver","银耳环", "7113119090"));
        List<String> mmList2 = new ArrayList(Arrays.asList("stainless steel|alloy|brass|aluminum|titanium steel|24K|18K|14K", "金属制耳环","7117190000"));
        List<String> mmList3 = new ArrayList(Arrays.asList("plastic", "塑料制耳环","7117900000"));
        List<String> mmList4 = new ArrayList(Arrays.asList("other", "耳环","7117900000"));
        mList.add(mmList);
        mList.add(mmList2);
        mList.add(mmList3);
        mList.add(mmList4);
        map.put("dangle earrings", mList);*/
        /**
         * Other Decals	sticker|decal	paper	rolls
         * Other Decals	sticker|decal	paper	wallpaper
         * Other Decals	sticker|decal	paper	*other
         * Other Decals	sticker|decal	*other	rolls
         * Other Decals	sticker|decal	*other	*other
         * Other Decals	tapestry	polyester
         * Other Decals	tapestry	silk
         * Other Decals	tapestry	*other
         * Other Decals	canva	polyester
         * Other Decals	canva	silk
         * Other Decals	canva	other
         * Other Decals	poster	paper
         * Other Decals	 sign	wood
         * Other Decals	 sign	metal|tin
         */
        List<String> mmList = new ArrayList(Arrays.asList("sticker|decal", "paper", "rolls","纸制成卷贴纸", "4811410000"));
        List<String> mmList2 = new ArrayList(Arrays.asList("sticker|decal", "paper", "wallpaper","纸制墙纸","4814900090"));
        List<String> mmList3 = new ArrayList(Arrays.asList("sticker|decal", "paper", "other","纸制贴纸","4911999090"));
        List<String> mmList4 = new ArrayList(Arrays.asList("sticker|decal", "other", "rolls","塑料成卷贴纸","3919109900"));
        List<String> mmList5 = new ArrayList(Arrays.asList("sticker|decal", "other", "other","塑料贴纸","3919909090"));
        List<String> mmList6 = new ArrayList(Arrays.asList("tapestry", "polyester","合纤挂毯","6304939000"));
        List<String> mmList7 = new ArrayList(Arrays.asList("tapestry", "silk","丝制挂毯","6304991090"));
        List<String> mmList8 = new ArrayList(Arrays.asList("tapestry", "other","挂毯","6304999000"));
        List<String> mmList9 = new ArrayList(Arrays.asList("canva", "polyester","合纤帆布画","6304939000"));
        List<String> mmList10 = new ArrayList(Arrays.asList("canva", "silk","丝制布画","6304991090"));
        List<String> mmList11 = new ArrayList(Arrays.asList("canva", "other","帆布画","6304999000"));
        List<String> mmList12 = new ArrayList(Arrays.asList("poster", "paper","海报","4911910090"));
        List<String> mmList13 = new ArrayList(Arrays.asList("sign", "wood","装饰木牌","4420109090"));
        List<String> mmList14 = new ArrayList(Arrays.asList("sign", "metal|tin","金属标牌","8310000000"));
        mList.add(mmList);
        mList.add(mmList2);
        mList.add(mmList3);
        mList.add(mmList4);
        mList.add(mmList5);
        mList.add(mmList6);
        mList.add(mmList7);
        mList.add(mmList8);
        mList.add(mmList9);
        mList.add(mmList10);
        mList.add(mmList11);
        mList.add(mmList12);
        mList.add(mmList13);
        mList.add(mmList14);
        map.put("other decals", mList);

    }

    private void disposeData() {
        // 写法1
        String fileName = "C:\\Users\\Administrator\\Desktop\\工作表" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, ExcelDemo.class).sheet("模板").doWrite(data());
    }

    private List<ExcelDemo> data() {
        List<ExcelDemo> demos  = new ArrayList<>();
        if(list != null && list.size() > 0){
            for (ExcelDemo excelDemo : list) {
                if(excelDemo.getCategory() != null){
                    String[] categorys = excelDemo.getCategory().split("\\|");
                    List<List<String>> lists = null;
                    for (String category : categorys) {
                        lists = map.get(category.toLowerCase());
                        if(CollectionUtils.isNotEmpty(lists)){
                            break;
                        }
                    }
                    if(CollectionUtils.isNotEmpty(lists)){
                        // 前后加空格, 是为了头尾部包含关键字, 也可以查询到.
                        String join = StringUtils.join(" ",excelDemo.getItemDesc()," ",
                                excelDemo.getItemDescEn()," ").toLowerCase();
                        String replace = join.replaceAll("[\\W]"," ");
                        for (List<String> strings : lists) {
                            Integer num = strings.size() - 2;
                            Integer tempNum = 0;
                            StringBuffer remark = new StringBuffer();
                            Integer iterNum = 0;
                            for (String string : strings) {
                                ++iterNum;
                                string = string.toLowerCase();
                                if(Objects.equals(string,"other")){
                                    remark.append(string).append(",");
                                    ++tempNum;
                                    continue;
                                }
                                String[] split = string.split("\\|");
                                for (String m : split) {
                                    boolean contains = replace.contains(StringUtils.join(" ", m, " "));
                                    if(contains){
                                        remark.append(m).append(",");
                                        ++tempNum;
                                        break;
                                    }
                                }
                                if(Objects.equals(iterNum,num)){
                                    break;
                                }
                            }
                            if(Objects.equals(num,tempNum)){
                                excelDemo.setRemark(remark.toString());
                                excelDemo.setRemark2(replace);
                                excelDemo.setItemDescDeclear(strings.get(num));
                                excelDemo.setHscode(strings.get(num + 1));
                                break;
                            }
                        }
                        demos.add(excelDemo);
                    }
                }
            }
        }
        return demos;
    }

}
