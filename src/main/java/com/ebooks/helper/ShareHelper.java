/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebooks.helper;

import com.ebooks.dao.SachDAO;
import com.ebooks.dao.TacGiaDAO;
import com.ebooks.dao.TheLoaiDAO;
import com.ebooks.model.Sach;
import com.ebooks.model.TacGia;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import com.ebooks.model.TaiKhoan;
import com.ebooks.model.TheLoai;
import java.text.SimpleDateFormat;

/**
 *
 * @author Jason
 */
public class ShareHelper {

    /**
     * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
     */
    public static final Image APP_ICON;
    public static final ImageIcon APP_ICON_1;

    static {
        // Tải biểu tượng ứng dụng 
        //CÁCH TẢI ẢNH TỪ TRONG PROJECT
        //icon là thư mục con của src
        String file = "/com/ebooks/Icon/layers.png";      //icon là thư mục con của src
        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
        APP_ICON_1 = new ImageIcon(ShareHelper.class.getResource(file));
    }

    /**
     * Sao chép file logo chuyên đề vào thư mục logos (tạo nếu chưa có thư mục
     * logos)
     *
     * @param file là đối tượng file ảnh
     * @return chép được hay không
     */
    public static boolean saveLogo(File file) {
        File dir = new File("logos");  //khai báo thư mục logos ngang hàng với src
        // Tạo thư mục nếu chưa tồn tại
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            // Copy vào thư mục logos (đè nếu đã tồn tại)
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Đọc hình ảnh logo chuyên đề trong thư mục logos theo tenFile
     *
     * @param fileName là tên file logo
     * @return ImageIcon ảnh đọc được
     */
    public static ImageIcon readLogo(String fileName) {
        File path = new File("logos", fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
    }

    /**
     * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhnập
     */
    public static TaiKhoan USER = new TaiKhoan();

    /**
     * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
     */
    public static void clear() {
        ShareHelper.USER = null;
    }

    /**
     * Kiểm tra xem đăng nhập hay chưa
     *
     * @return đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return ShareHelper.USER != null;
    }
//  Kiểm tra xem đăng nhập hay chưa
// * @return đăng nhập hay chưa

    public static boolean isUser() {
        return ShareHelper.USER != null;
    }
    //  Kiểm tra xem Người dùng đăng nhập hay chưa

    //  Kiểm tra xem Quan Tri Viên đăng nhập hay chưa
    public static void logoff() {
        ShareHelper.USER = null;
    }

    /**
     * Kiểm tra xem đăng nhập hay chưa
     *
     * @return đăng nhập hay chưa
     */
    public static boolean authenticated() {
        return ShareHelper.USER != null;
    }

    public static SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");

    public static String convertTypeBook(String maTheLoai) {
        TheLoaiDAO DAOTL = new TheLoaiDAO();
        TheLoai theLoai = DAOTL.findById(maTheLoai);
        return theLoai.getTenTheLoai();
    }

    public static String convertAuthorBook(String maTacGia) {
        TacGiaDAO DAOTG = new TacGiaDAO();
        TacGia tacGia = DAOTG.findById(maTacGia);
        return tacGia.getHoTen();
    }

    public static String convertBook(String maSach) {
        SachDAO DAOS = new SachDAO();
        Sach sach = DAOS.findById(maSach);
        return sach.getTenSach();
    }

}
