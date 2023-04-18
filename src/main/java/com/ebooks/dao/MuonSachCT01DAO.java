package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.MuonSachCT01;
import com.ebooks.model.ThongTinNguoiDung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuonSachCT01DAO {

    private MuonSachCT01 readFromResultSet(ResultSet rs) throws SQLException {
        MuonSachCT01 model = new MuonSachCT01();
        model.setMaMuonSach(rs.getString("maMuonSach"));
        model.setMaMuonSachChiTiet(rs.getString("maMuonSachChiTiet"));
        model.setMaSach(rs.getString("maSach"));
        model.setMaTacGia(rs.getString("maTacGia"));
        model.setMaTheLoai(rs.getString("maTheLoai"));
        model.setTenDangNhap(rs.getString("taiKhoanMuon"));
        model.setThoiGianMuon(rs.getDate("ngayMuon"));
        return model;
    }

    private List<MuonSachCT01> select(String sql, Object... args) {
        List<MuonSachCT01> list = new ArrayList<MuonSachCT01>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }

    public List<MuonSachCT01> findByTLAndTG(String maTheLoai, String maTacGia) {
        String sql = "SELECT maMuonSachChiTiet,MuonSachChiTiet.maMuonSach maMuonSach,MuonSachChiTiet.maSach maSach,MuonSach.tenDangNhap taiKhoanMuon,TheLoai_Sach.maTheLoai maTheLoai,TacGia_Sach.maTacGia maTacGia,MuonSach.thoiGianMuon ngayMuon  FROM MuonSachChiTiet JOIN MuonSach \n"
                + "ON MuonSach.maMuonSach = MuonSachChiTiet.maMuonSach  JOIN  TheLoai_Sach\n"
                + "ON MuonSachChiTiet.maSach = TheLoai_Sach.maSach JOIN TacGia_Sach \n"
                + "ON MuonSachChiTiet.maSach = TacGia_Sach.maSach\n"
                + "WHERE TheLoai_Sach.maTheLoai LIKE ? AND TacGia_Sach.maTacGia LIKE ?";
        List<MuonSachCT01> list = select(sql, "%" + maTheLoai + "%", "%" + maTacGia + "%");
        return list;
    }
    
    
}
