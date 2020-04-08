package io.github.talelin.merak.utils;

public class PathUtil {
    // 获取操作系统的分隔符
    private static String separator = System.getProperty("file.separator");

    /**
     * 获取存放图片路径
     */
    public static String getImgBasePath() {
        // 获取操作系统的信息
        String os = System.getProperty("os.name");
        String basePath = "";
        // 如果是window操作系统
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/eclipse/image"; // Windows系统
        } else {
            //最终写到配置文件或者上传到对象存储
            basePath = "/Users/bizzbee/image/"; // 除了Windows系统
        }

        // 更换分隔符
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    /**
     * 获取店铺照片路径
     */
    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/item/shop" + shopId + "/";
        return imagePath.replace("/", separator);
    }

}
