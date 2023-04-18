package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.MuonSachCT01;
import com.ebooks.model.MuonSachCT02;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MuonSachCT02DAO {

    private MuonSachCT02 readFromResultSet(ResultSet rs) throws SQLException {
        MuonSachCT02 model = new MuonSachCT02();
        model.setMaMuonSach(rs.getString("maMuonSach"));
        model.setMaMuonSachChiTiet(rs.getString("maMuonSachChiTiet"));
        model.setMaSach(rs.getString("maSach"));
        model.setTenSach(rs.getString("tenSach"));
        model.setTenDangNhap(rs.getString("taiKhoanMuon"));
        model.setThoiGianMuon(rs.getDate("ngayMuon"));
        return model;
    }

    private List<MuonSachCT02> select(String sql, Object... args) {
        List<MuonSachCT02> list = new ArrayList<MuonSachCT02>();
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

    public List<MuonSachCT02> findByNameBook(String tenSach) {
        String sql = "SELECT maMuonSachChiTiet,MuonSachChiTiet.maMuonSach maMuonSach,MuonSachChiTiet.maSach maSach,thoiGianMuon ngayMuon,MuonSach.tenDangNhap taiKhoanMuon,tenSach  FROM MuonSachChiTiet JOIN MuonSach \n"
                + "ON MuonSach.maMuonSach = MuonSachChiTiet.maMuonSach  JOIN Sach \n"
                + "ON MuonSachChiTiet.maSach = Sach.maSach\n"
                + "WHERE tenSach LIKE N?";
        List<MuonSachCT02> list = select(sql, "%" + tenSach + "%");
        return list;
    }
    
    public static void main(String[] args) {
        MuonSachCT02DAO DAOMS02 = new MuonSachCT02DAO();
        List<MuonSachCT02> list = DAOMS02.findByNameBook("");
        for (MuonSachCT02 muonSachCT02 : list) {
            System.out.println(muonSachCT02.getTenSach());
        }
    }
}
