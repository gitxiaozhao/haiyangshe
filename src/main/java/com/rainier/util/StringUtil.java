package com.rainier.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Barcke on 2017/9/12.
 */
public final class StringUtil {

    /**
     * 判断字符串是否是空字符串或null  是返回false  否返回true
     * @param str
     * @return
     */
    public static final boolean equalsIsNullString(String str){
        return str==null?false:"".equals(str.trim())?false:true;
    }
    /**
     * 判断字符串集合是否为空 是返回false  否返回true
     * @param str
     * @return
     */
    public static final boolean equalsIsNullStrings(String... str){
        if (str==null)
            return false;

        for (int i=0,leg=str.length;i<leg;i++){
            if (str[i]==null||"".equals(str[i].trim()))
                return false;
        }
        return true;
    }

    /**
     * 判断字符串最后一个是不是“，”（逗号）
     * 若是返回截取了逗号的字符串
     * 否则返回原字符串
     * 若为空值或null返回空值
     * @param str
     * @return
     */
    public static final String judegLastComma(String str) throws CustomException {
        if (str==null||"".equals(str.trim()))
            throw new CustomException("方法judegLastComma(String str)str参数为空");

        return str.endsWith(",")?str.substring(0,str.length()-1):str;
    }

    /**
     * 返回的url中参数不能带有+、=、 、？、%、#、&、/特殊字符 所以对返回的参数值进行转义
     * @param str url中返回的参数
     * @return
     */
    public static final String urlParamEscape(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str,"UTF-8");
    }
    /**
     * 判断字符串是否不为空
     * @param temp
     * @return
     */
    public static Boolean judeStringIsNullAndVoid(String temp){
        return temp!=null&&!"".equals(temp)?true:false;
    }

    /**
     * 正则验证邮箱是否正确  邮箱前面不需要加www.  如果正确返回true 否则false
     * @param email 邮箱
     * @return
     */
    public static final boolean isEmail(String email){
        String str="^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.find();
    }
    /**
     * 正则表达式验证电话号码是否正确  正确返回true  否则返回false 通用电话号码正则验证
     * @param phone 电话号码
     * @return
     */
    public static final boolean isPhone(String phone){
        return Pattern.compile("^(?:\\+86)?1\\d{10}$").matcher(phone).find();
    }

    /**
     * 正则表达式验证用户名是否符合要求 正确返回true 否则返回false
     * 以下正则表达式表示二十六个英文字母忽略大小写加上数字和下划线必须大于4小于16
     * @param name
     * @return
     */
    public static final boolean isLoginName(String name){
        return Pattern.compile("^[A-Za-z0-9]\\w{4,16}$").matcher(name).find();
    }

    /**
     * 正则表达式验证密码是否符合要求 正确返回true 否则返回false
     * 以下正则表达式表示密码必须是含有小写字母、大写字母、数字 长度为大于6小于16
     * @param passWord
     * @return
     */
    public static final  boolean isPassWord(String passWord){
        return Pattern.compile("^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,16}$").matcher(passWord).find();
    }

    /**
     * 通过正则表达式去除html标签
     * @param str
     * @return
     */
    public static final String removeHtmlLabel(String str){
        if (!equalsIsNullString(str))
            return "";
        String htmlStr = str; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {System.err.println("Html2Text: " + e.getMessage()); }
        //剔除空格行
        textStr=textStr.replaceAll("[ ]+", " ");
        textStr=textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        return textStr.trim();// 返回文本字符串
        //版本一会保留style中的样式 先使用版本二已成功移除style中的样式
        /*return str.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "").replaceAll("[(/>)<]", "").replace(" ","").replace("/r","").replace("/n",""); */
    }


    /**
     * 将字符串年月日格式改为yyyy-mm-dd
     * @param str
     * @return
     */
    public static final String formatDate(String str){
        if (!equalsIsNullString(str))
            return "";
        return str.replace("年","-").replace("月","-").replace("日","");
    }
}
