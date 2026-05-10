public abstract class Karyawan {
    private int id_karyawan;
    private String nama_karyawan;
    private String alamat;
    private String no_telp;

    // Constructor
    public Karyawan(int id_karyawan, String nama_karyawan, String alamat, String no_telp) {
        this.id_karyawan = id_karyawan;
        this.nama_karyawan = nama_karyawan;
        this.alamat = alamat;
        this.no_telp = no_telp;
    }

    // Getter & Setter
    public int get_id() {
        return id_karyawan;
    }

    public void set_id(int id) {
        this.id_karyawan = id;
    }

    public String get_nama() {
        return nama_karyawan;
    }

    public void set_nama(String nama) {
        this.nama_karyawan = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String tampil_info() {
        return "ID: " + id_karyawan +
                " | Nama: " + nama_karyawan +
                " | Alamat: " + alamat +
                " | No. Telp: " + no_telp;
    }
}
