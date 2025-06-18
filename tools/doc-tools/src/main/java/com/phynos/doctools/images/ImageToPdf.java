package com.phynos.doctools.images;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 图片转pdf
 *
 * @author lupc
 * @date 2025/3/19 19:26
 */
public class ImageToPdf {

    public static final float pageWidth = 210f; // 页面宽度，单位毫米（A4宽度为210mm）
    public static final float pageHeight = 297f; // 页面高度，单位毫米（A4高度为297mm）
    public static final float margin = 10f; // 页面边距，单位毫米

    public static void main(String[] args) {
        String inputImagePath = "C:\\Users\\Administrator\\Downloads\\pdf转换\\2222.png"; // 输入长图片路径
        String outputPdfPath = "C:\\Users\\Administrator\\Downloads\\pdf转换\\2222.pdf"; // 输出PDF路径
        try (InputStream is = new FileInputStream(inputImagePath);
             OutputStream os = new FileOutputStream(outputPdfPath)) {
            imageToPdf(is, os);
            System.out.println("PDF转换完成，保存路径：" + outputPdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imageToPdf(InputStream is, OutputStream os) throws IOException {
        // 将毫米转换为点（1英寸=25.4毫米，1英寸=72点）
        float pageWidthPoints = pageWidth * 72 / 25.4f;
        float pageHeightPoints = pageHeight * 72 / 25.4f;
        float marginPoints = margin * 72 / 25.4f;
        // 创建PDF文档
        try (PDDocument document = new PDDocument()) {
            // 加载图片
            BufferedImage originalImage = ImageIO.read(is);
            int imageWidth = originalImage.getWidth();
            int imageHeight = originalImage.getHeight();

            // 计算每页可用的图片高度（减去上下边距）
            float usablePageHeightPoints = pageHeightPoints - 2 * marginPoints;
            // 计算每页可用的图片宽度（减去左右边距）
            float usablePageWidthPoints = pageWidthPoints - 2 * marginPoints;

            // 计算缩放比例，使图片宽度适应页面宽度，高度按比例缩放
            //float scale = pageWidthPoints / imageWidth;
            float scale = usablePageWidthPoints / imageWidth;
            int scaledHeight = (int) (imageHeight * scale);

            // 计算需要的总页数
            int totalPages = (int) Math.ceil((float) scaledHeight / usablePageHeightPoints);
            for (int page = 0; page < totalPages; page++) {
                // 创建新的PDF页面
                PDPage pdPage = new PDPage(new PDRectangle(pageWidthPoints, pageHeightPoints));
                document.addPage(pdPage);

                // 计算当前页需要绘制的图片区域
                int startY = (int) (page * usablePageHeightPoints / scale);
                int endY = (int) Math.min((page + 1) * usablePageHeightPoints / scale, imageHeight);

                if (startY >= imageHeight) {
                    // 如果起始Y超过图片高度，跳过空白页
                    continue;
                }

                // 裁剪图片的相应部分
                BufferedImage subImage = originalImage.getSubimage(0, startY, imageWidth, endY - startY);

                // 将裁剪后的图片转换为PDImageXObject
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, toByteArray(subImage), "subimage");

                // 绘制图片到PDF页面
                try (PDPageContentStream contentStream = new PDPageContentStream(document, pdPage)) {
                    // 计算绘制区域，保持图片宽度与页面宽度一致，高度自动缩放
                    //float imageScale = pageWidthPoints / (float) subImage.getWidth();
                    //float drawWidth = pageWidthPoints;
                    float imageScale = usablePageWidthPoints / (float) subImage.getWidth();
                    float drawWidth = usablePageWidthPoints;

                    float drawHeight = subImage.getHeight() * imageScale;

                    // 如果绘制高度超过可用页面高度，重新计算缩放比例
                    if (drawHeight > usablePageHeightPoints) {
                        imageScale = usablePageHeightPoints / (float) subImage.getHeight();
                        drawWidth = subImage.getWidth() * imageScale;
                        drawHeight = usablePageHeightPoints;
                    }

                    // 计算居中位置
                    float x = (pageWidthPoints - drawWidth) / 2;
                    float y = pageHeightPoints - marginPoints - drawHeight;

                    contentStream.drawImage(pdImage, x, y, drawWidth, drawHeight);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 保存PDF文档
            document.save(os);
        }
    }

    /**
     * 将BufferedImage转换为byte数组
     *
     * @param image BufferedImage对象
     * @return byte数组
     * @throws IOException
     */
    private static byte[] toByteArray(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos); // 可以根据需要更改格式，如 "jpeg"
            return baos.toByteArray();
        }
    }


}
