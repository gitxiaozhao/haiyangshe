package com.rainier.util;

public class Util {
    /**
    * @描述 把逗号拼接的多个id转换成 'xxx','xxx' 格式
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/9/10
    */
    public static String mapperIn(String str,String fuhao){
        String[] split = str.split(fuhao);
        String ids = "";
        if (!str.equals("") && str != null){
            for (int i=0;i<split.length;i++){
                if (ids.equals("")){
                    ids += "'"+split[i]+"'";
                }else {
                    ids += ",'"+split[i]+"'";
                }
            }
        }

        return ids;
    }
}
