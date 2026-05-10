import java.util.Date;

public class Absensi {
    private Date tanggal;
    private String jam_masuk;  // Format HH:mm
    private String jam_keluar; // Format HH:mm
    private String status;

    // Constructor
    public Absensi(Date tanggal, String jam_masuk, String jam_keluar, String status) {
        this.tanggal = tanggal;
        this.jam_masuk = jam_masuk;
        this.jam_keluar = jam_keluar;
        this.status = status;
    }

    // Getter & Setter
    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }

    public String getJam_masuk() { return jam_masuk; }
    public void setJam_masuk(String jam_masuk) { this.jam_masuk = jam_masuk; }

    public String getJam_keluar() { return jam_keluar; }
    public void setJam_keluar(String jam_keluar) { this.jam_keluar = jam_keluar; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Mengubah "HH:mm" menjadi total menit
    private int toMenit(String jam) {
        String[] parts = jam.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    // Menghitung durasi kerja dalam menit
    public int hitung_durasi() {
        return toMenit(jam_keluar) - toMenit(jam_masuk);
    }

    // Durasi dalam format jam dan menit
    public String hitung_durasi_format() {
        int total = hitung_durasi();
        return (total / 60) + " jam " + (total % 60) + " menit";
    }

    // Validasi format jam HH:mm
    private boolean isFormatValid(String jam) {
        if (jam == null) return false;
        if (!jam.matches("\\d{2}:\\d{2}")) return false;
        if (hitung_durasi() != 480) return false;
        String[] parts = jam.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h >= 0 && h <= 23 && m >= 0 && m <= 59;
    }

    // Validasi absensi
    public boolean validasi_absen() {
        if (!isFormatValid(jam_masuk) || !isFormatValid(jam_keluar))
            return false;
        if (toMenit(jam_masuk) >= toMenit(jam_keluar))
            return false;
        if (status == null || status.trim().isEmpty())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Absensi{tanggal=" + tanggal +
                ", jam_masuk=" + jam_masuk +
                ", jam_keluar=" + jam_keluar +
                ", status='" + status + "'" +
                ", durasi=" + hitung_durasi_format() + "}";
    }
}