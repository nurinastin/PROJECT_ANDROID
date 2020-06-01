package com.project.sinisa.config;

import java.text.DecimalFormat;

public class ServerAccess {
    public static final String BASE_URL = "https://pakar.karnakitabisa.com/";
    public static final String ROOT_API = BASE_URL+"api/";
    public static final String auth = ROOT_API+"auth/";
    public static final String DAFTAR_TOKO = ROOT_API+"toko/daftar";
    public static final String LOGIN = auth+"login";
    public static final String LOGIN_EMAIL = auth+"loginEmail";
    public static final String LOGIN_PHONE = auth+"loginPhone";

    public static final String REGISTER = auth+"register";
    public static final String REGISTER_PHONE = auth+"registerByPhone";
    public static final String REGISTER_EMAIL = auth+"registerByEmail";

    public static final String UPDATE_PROFIL = auth+"updateProfile";
    public static final String UPDATE_PASSWORD = auth+"updatePassword";
    public static final String INFO_PROFIL = auth+"infoProfile/";
    public static final String PRODUK =ROOT_API+"produk/";
    public static final String BARANG =ROOT_API+"barang/";
    public static final String SIMPAN_BARANG =ROOT_API+"barang/store";
    public static final String USAHA =ROOT_API+"usaha/";
    public static final String SIMPAN_USAHA =ROOT_API+"usaha/store";
    public static final String HEWAN =ROOT_API+"hewan/";
    public static final String SIMPAN_HEWAN =ROOT_API+"hewan/store";
    public static final String LOKER =ROOT_API+"loker/";
    public static final String SIMPAN_LOKER =ROOT_API+"loker/store";
    public static final String KATEGORI_BARANG =ROOT_API+"barang/kategori";
    public static final String KATEGORI_USAHA =ROOT_API+"usaha/kategori";
    public static final String KATEGORI_HEWAN =ROOT_API+"hewan/kategori";
    public static final String KATEGORI_LOKER =ROOT_API+"loker/kategori";
    public static final String EVENT =ROOT_API+"event/";
    public static final String COVER =BASE_URL+"public/assets/img/";
    public static String numberConvert(String val){
        double v = Double.parseDouble(val);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String format = formatter.format(v);
        return "Rp "+format;
    }
    public static String coverDirectory(String foto, String id, String type){
        if (type.equals("barang")){
            return COVER+"/"+type+"/"+id +"/"+foto;
        }else{
            return COVER+"/"+type+"/"+foto;
        }

    }

}
