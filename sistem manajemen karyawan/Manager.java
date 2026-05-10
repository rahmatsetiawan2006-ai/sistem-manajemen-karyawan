import java.util.ArrayList;
import java.util.List;

public class Manager extends Karyawan {
    private String divisi;
    private double bonus;

    // Agregasi: Manager memiliki daftar jadwal (list of String)
    private List<String> jadwalList;

    // Constructor
    public Manager(int id_karyawan, String nama_karyawan, String alamat, String no_telp,
            String divisi, double bonus) {
        super(id_karyawan, nama_karyawan, alamat, no_telp);
        this.divisi = divisi;
        this.bonus = bonus;
        this.jadwalList = new ArrayList<>();
    }

    // Getter & Setter
    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    // Menambahkan jadwal ke dalam list
    public void atur_jadwal() {
        jadwalList.add("Jadwal-" + (jadwalList.size() + 1) + " untuk divisi " + divisi);
        System.out.println("Jadwal berhasil diatur: " + jadwalList.get(jadwalList.size() - 1));
    }

    // Menampilkan laporan semua jadwal
    public String lihat_laporan() {
        if (jadwalList.isEmpty()) {
            return "Belum ada laporan jadwal untuk Manager: " + get_nama();
        }
        StringBuilder sb = new StringBuilder("Laporan Jadwal Manager " + get_nama() + ":\n");
        for (int i = 0; i < jadwalList.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(jadwalList.get(i)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String tampil_info() {
        return super.tampil_info() +
                " | Divisi: " + divisi +
                " | Bonus: " + bonus;
    }
}
