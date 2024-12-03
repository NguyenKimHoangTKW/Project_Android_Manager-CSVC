package com.example.quanlycsvc.AdapterModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quanlycsvc.Models.GetFullThietBiMuon;
import com.example.quanlycsvc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DanhSachFullThietBiMuonAdapter extends BaseAdapter {
    Context context;
    ArrayList<GetFullThietBiMuon> list;

    public DanhSachFullThietBiMuonAdapter(Context context, ArrayList<GetFullThietBiMuon> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvrowTennguoidungmuon,tvShowtenthietbimuon, tvShowtenphonghocmuon, tvSoluongmuon, tvYeuCauMuon, tvLydohuymuonuser, tvngaydangkymuon, tvNgayhuyuser,tvNgaymuonuser, tvNgaytrauser, tvTrangthaimuon;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.danh_sach_full_thiet_bi_muon_row, null);
        tvrowTennguoidungmuon = row.findViewById(R.id.tvrowTennguoidungmuon);
        tvShowtenthietbimuon = row.findViewById(R.id.tvShowtenthietbimuon);
        tvShowtenphonghocmuon = row.findViewById(R.id.tvShowtenphonghocmuon);
        tvSoluongmuon = row.findViewById(R.id.tvSoluongmuon);
        tvYeuCauMuon = row.findViewById(R.id.tvYeuCauMuon);
        tvLydohuymuonuser = row.findViewById(R.id.tvLydohuymuonuser);
        tvngaydangkymuon = row.findViewById(R.id.tvngaydangkymuon);
        tvNgaymuonuser = row.findViewById(R.id.tvNgaymuonuser);
        tvNgaytrauser = row.findViewById(R.id.tvNgaytrauser);
        tvTrangthaimuon = row.findViewById(R.id.tvTrangthaimuon);
        tvNgayhuyuser = row.findViewById(R.id.tvNgayhuyuser);

        GetFullThietBiMuon getFullThietBiMuon = list.get(position);
        tvrowTennguoidungmuon.setText("Tên CBVC/GV mượn: "+ getFullThietBiMuon.name_CBVC);
        tvShowtenthietbimuon.setText("Tên thiết bị mượn: " + getFullThietBiMuon.ten_thiet_bi);
        tvShowtenphonghocmuon.setText("Phòng học mượn: " + getFullThietBiMuon.ten_phong_hoc);
        tvSoluongmuon.setText("Số lượng mượn: " + getFullThietBiMuon.so_luong_muon);
        tvYeuCauMuon.setText("Yêu cầu của người mượn: " + getFullThietBiMuon.yeu_cau);
        tvLydohuymuonuser.setText(getFullThietBiMuon.ly_do_huy != null ? "Lý do hủy: " + getFullThietBiMuon.ly_do_huy : "");

        tvngaydangkymuon.setText("Ngày đăng ký mượn: " + formatTimestamp(getFullThietBiMuon.ngay_dang_ky_muon));
        tvNgaymuonuser.setText(getFullThietBiMuon.ngay_muon != null ? "Ngày duyệt mượn: " + formatTimestamp(getFullThietBiMuon.ngay_muon) : "");
        tvNgaytrauser.setText(getFullThietBiMuon.ngay_tra != null ? "Ngày trả: " + formatTimestamp(getFullThietBiMuon.ngay_tra) : "");
        tvNgayhuyuser.setText(getFullThietBiMuon.ngay_huy != null ? "Ngày hủy: "+ formatTimestamp(getFullThietBiMuon.ngay_huy) : "");
        tvTrangthaimuon.setText(getFullThietBiMuon.ten_trang_thaii);
        return row;
    }
    private String formatTimestamp(Integer timestamp) {
        if (timestamp == null) return "";
        long timeInMillis = timestamp * 1000L;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMM yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timeInMillis));
    }
}
