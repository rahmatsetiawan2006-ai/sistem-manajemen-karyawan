import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Staff extends Karyawan {
    private String bagian;
    private String shift;

    // Agregasi: Staff memiliki banyak Absensi
    private List<Absensi> daftarAbsensi;

    // Constructor
    public Staff(int id_karyawan, String nama_karyawan, String alamat, String no_telp,
            String bagian, String shift) {
        super(id_karyawan, nama_karyawan, alamat, no_telp);
        this.bagian = bagian;
        this.shift = shift;
        this.daftarAbsensi = new ArrayList<>();
    }

    // Getter & Setter
    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    // Memasukkan absensi baru (dengan validasi)
    public void input_absen(Date tanggal, int jam_masuk, int jam_keluar, String status) {
        Absensi absensi = new Absensi(jam_masuk, jam_keluar, status);
        if (absensi.validasi_absen()) {
            daftarAbsensi.add(absensi);
            System.out.println("[VALID] Absensi berhasil diinput: " + absensi);
        } else {
            System.out.println("[INVALID] Absensi gagal diinput. Data tidak valid: " + absensi);
        }
    }

    // Menampilkan semua jadwal absensi
    public String lihat_jadwal() {
        if (daftarAbsensi.isEmpty()) {
            return "Belum ada jadwal absensi untuk Staff: " + get_nama();
        }
        StringBuilder sb = new StringBuilder("Jadwal Absensi Staff " + get_nama() + ":\n");
        for (int i = 0; i < daftarAbsensi.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(daftarAbsensi.get(i)).append("\n");
        }
        return sb.toString();
    }

    public List<Absensi> getDaftarAbsensi() {
        return daftarAbsensi;
    }

    @Override
    public String tampil_info() {
        return super.tampil_info() +
                " | Bagian: " + bagian +
                " | Shift: " + shift;
    }
}
