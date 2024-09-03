package com.phynos.solar;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 注释内容
 *
 * @author lupc
 * @date 2024/5/17 12:00
 */
@Slf4j
//@Component
public class Excel {

    @PostConstruct
    public void test() throws IOException {
        if (filePath.length() == 0) {
            System.out.println("请输生成的字典位置:");
            filePath = new Scanner(System.in).nextLine();
        }
        generate(scanner());
    }


    private static BigDecimal counter = BigDecimal.ZERO;

    //for example:D://密码字典.txt
    private static String filePath = "F:\\tmp\\密码字典.txt";

    //密码可能会包含的字符集合
//    private static char[] fullCharSource = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
//            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
//            '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '{', '}', '|', ':', '"', '<', '>', '?', ';', '\'', ',', '.', '/', '-', '=', '`'};
    private static char[] fullCharSource = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    //将可能的密码集合长度
    private static int fullCharLength = fullCharSource.length;
    private static BigDecimal fullDecimal = new BigDecimal(fullCharLength);


    //maxLength：生成的字符串的最大长度
    public static String generate(int maxLength) throws IOException {
        Long startTime = System.currentTimeMillis();

        //计数器，多线程时可以对其加锁，当然得先转换成Integer类型。
        StringBuilder buider = new StringBuilder();

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"));

        while (buider.toString().length() <= maxLength) {
            buider = new StringBuilder(maxLength * 2);
            BigDecimal _counter = counter;
            //10进制转换成26进制
            while (_counter.compareTo(fullDecimal) >= 0) {
                //获得低位
                BigDecimal[] divde = _counter.divideAndRemainder(fullDecimal);
                buider.insert(0, fullCharSource[divde[1].intValue()]);
                _counter = divde[0];
                //处理进制体系中只有10没有01的问题，在穷举里面是可以存在01的
                _counter = _counter.subtract(BigDecimal.ONE);
            }
            //最高位
            buider.insert(0, fullCharSource[_counter.intValue()]);
            counter = counter.add(BigDecimal.ONE);

            boolean flag = excel(buider.toString());
            if (flag) {
                log.info("正确密码：{}", buider.toString());
                pw.write("第" + counter + "尝试成功密码为:" + buider.toString() + " 消耗时长:" + (System.currentTimeMillis() - startTime) / 1000 + "秒\n");
                return buider.toString();
            } else {
                System.out.println("第" + counter + "次尝试 错误密码:" + buider.toString() + "  消耗时长:" + (System.currentTimeMillis() - startTime) / 1000 + "秒");
            }
        }
        return "错误!";
    }

    @PreDestroy
    public void destroy() {

    }

    public static File excelFile = null;

    public static boolean excel(String password) throws IOException {
        String excelPath = "F:\\tmp\\202004月处方.xls";
        //String excelPath = "F:\\tmp\\test.xls";
        try {
            if (excelFile == null) {
                excelFile = new File(excelPath);
            }
            Workbook wb = WorkbookFactory.create(excelFile, password, true);
            return true;
//            InputStream is = new FileInputStream(excelPath);
//            //解密8
//            POIFSFileSystem pfs = new POIFSFileSystem(is);
//            EncryptionInfo info = new EncryptionInfo(pfs);
//            Decryptor decryptor = Decryptor.getInstance(info);
//            Biff8EncryptionKey.setCurrentUserPassword(password); // 设置你的密码
//            boolean verified = decryptor.verifyPassword(Biff8EncryptionKey.getCurrentUserPassword());
//            XSSFWorkbook workbook = new XSSFWorkbook(decryptor.getDataStream(pfs));
//            return verified;
        } catch (Exception e) {
            return false;
        }
    }

    public static Integer scanner() {
        System.out.print("生成的字典位置：" + filePath + "\n" + "请输入你需要生成的字典位数：");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        return x;
    }

}
