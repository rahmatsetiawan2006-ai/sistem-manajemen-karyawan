import java.util.*;

// ===================== KELAS ABSENSI =====================
class Absensi {
    Date   tanggal;
    int    jam_masuk;
    int    jam_keluar;
    String status;

    Absensi(int jam_masuk, int jam_keluar, String status) {
        this.tanggal   = new Date();
        this.jam_masuk  = jam_masuk;
        this.jam_keluar = jam_keluar;
        this.status     = status;
    }

    // Hitung durasi dalam jam
    int hitung_durasi() {
        return jam_keluar - jam_masuk;
    }

    // Validasi: jam valid (0-23), durasi tepat 8 jam, status tidak kosong
    boolean validasi_absen() {
        if (jam_masuk  < 0 || jam_masuk  > 23) return false;
        if (jam_keluar < 0 || jam_keluar > 23) return false;
        if (jam_masuk >= jam_keluar)            return false;
        if (hitung_durasi() != 8)               return false;
        if (status == null || status.trim().isEmpty()) return false;
        return true;
    }

    public String toString() {
        return "Jam Masuk: " + jam_masuk
             + " | Jam Keluar: " + jam_keluar
             + " | Durasi: "    + hitung_durasi() + " jam"
             + " | Status: "    + status;
    }
}

// ===================== KELAS KARYAWAN =====================
abstract class Karyawan {
    int    id;
    String nama, alamat, no_telp;

    Karyawan(int id, String nama, String alamat, String no_telp) {
        this.id      = id;
        this.nama    = nama;
        this.alamat  = alamat;
        this.no_telp = no_telp;
    }

    int    get_id()              { return id; }
    void   set_id(int id)        { this.id = id; }
    String get_nama()            { return nama; }
    void   set_nama(String nama) { this.nama = nama; }

    String tampil_info() {
        return "ID: " + id
             + " | Nama: "    + nama
             + " | Alamat: "  + alamat
             + " | No.Telp: " + no_telp;
    }
}

// ===================== KELAS MANAGER =====================
class Manager extends Karyawan {
    String       divisi;
    double       bonus;
    List<String> jadwalList = new ArrayList<>();

    Manager(int id, String nama, String alamat, String no_telp,
            String divisi, double bonus) {
        super(id, nama, alamat, no_telp);
        this.divisi = divisi;
        this.bonus  = bonus;
    }

    void atur_jadwal() {
        String j = "Jadwal-" + (jadwalList.size() + 1) + " [Divisi: " + divisi + "]";
        jadwalList.add(j);
        System.out.println("  [OK] " + j + " berhasil ditambahkan.");
    }

    String lihat_laporan() {
        if (jadwalList.isEmpty()) return "    (belum ada jadwal)\n";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jadwalList.size(); i++)
            sb.append("    ").append(i + 1).append(". ").append(jadwalList.get(i)).append("\n");
        return sb.toString();
    }

    String tampil_info() {
        return super.tampil_info()
             + " | Divisi: " + divisi
             + " | Bonus: Rp" + (long) bonus;
    }
}

// ===================== KELAS STAFF =====================
class Staff extends Karyawan {
    String        bagian, shift;
    List<Absensi> daftarAbsensi = new ArrayList<>();

    Staff(int id, String nama, String alamat, String no_telp,
          String bagian, String shift) {
        super(id, nama, alamat, no_telp);
        this.bagian = bagian;
        this.shift  = shift;
    }

    void input_absen(int jamMasuk, int jamKeluar, String status) {
        Absensi a = new Absensi(jamMasuk, jamKeluar, status);
        if (a.validasi_absen()) {
            daftarAbsensi.add(a);
            System.out.println("  [VALID]   Absensi berhasil disimpan.");
            System.out.println("  Detail  : " + a);
        } else {
            System.out.println("  [INVALID] Absensi ditolak!");
            System.out.println("  Alasan  : Pastikan jam valid (0-23), durasi tepat 8 jam, dan status tidak kosong.");
            System.out.println("  Detail  : " + a);
        }
    }

    String lihat_jadwal() {
        if (daftarAbsensi.isEmpty()) return "    (belum ada absensi)\n";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < daftarAbsensi.size(); i++)
            sb.append("    ").append(i + 1).append(". ").append(daftarAbsensi.get(i)).append("\n");
        return sb.toString();
    }

    String tampil_info() {
        return super.tampil_info()
             + " | Bagian: " + bagian
             + " | Shift: "  + shift;
    }
}

// ===================== MAIN =====================
public class Main {
    static Scanner       sc       = new Scanner(System.in);
    static List<Manager> managers = new ArrayList<>();
    static List<Staff>   staffs   = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("    SISTEM MANAJEMEN KARYAWAN ");
            System.out.println("  1. Tambah Manager           ");
            System.out.println("  2. Tambah Staff             ");
            System.out.println("  3. Input Absensi Staff      ");
            System.out.println("  4. Lihat Semua Data         ");
            System.out.println("  0. Keluar                   ");
            System.out.print("Pilih menu: ");
            pilihan = bacaInt();

            switch (pilihan) {
                case 1  -> tambahManager();
                case 2  -> tambahStaff();
                case 3  -> inputAbsensi();
                case 4  -> lihatData();
                case 0  -> System.out.println("Program selesai. Sampai jumpa!");
                default -> System.out.println("[!] Pilihan tidak ada. Coba lagi.");
            }
        } while (pilihan != 0);
    }

    // ──────────────────────────────────────────────────────────────
    // MENU 1 - TAMBAH MANAGER
    // ──────────────────────────────────────────────────────────────
    static void tambahManager() {
        System.out.println("\n--- TAMBAH MANAGER ---");
        System.out.println("Masukkan data manager baru.\n");

        System.out.print("ID Karyawan  : "); int id     = bacaInt();
        System.out.print("Nama Lengkap : "); String nama  = bacaString();
        System.out.print("Alamat       : "); String alamat = bacaString();
        System.out.print("No. Telepon  : "); String telp   = bacaString();
        System.out.print("Divisi       : "); String divisi  = bacaString();
        System.out.print("Bonus (Rp)   : "); double bonus   = bacaDouble();

        managers.add(new Manager(id, nama, alamat, telp, divisi, bonus));
        System.out.println("\n  [OK] Manager \"" + nama + "\" berhasil ditambahkan!");
    }

    // ──────────────────────────────────────────────────────────────
    // MENU 2 - TAMBAH STAFF
    // ──────────────────────────────────────────────────────────────
    static void tambahStaff() {
        System.out.println("\n--- TAMBAH STAFF ---");
        System.out.println("Masukkan data staff baru.\n");

        System.out.print("ID Karyawan  : "); int id     = bacaInt();
        System.out.print("Nama Lengkap : "); String nama   = bacaString();
        System.out.print("Alamat       : "); String alamat = bacaString();
        System.out.print("No. Telepon  : "); String telp   = bacaString();
        System.out.print("Bagian       : "); String bagian  = bacaString();
        System.out.print("Shift        : "); String shift   = bacaString();

        staffs.add(new Staff(id, nama, alamat, telp, bagian, shift));
        System.out.println("\n  [OK] Staff \"" + nama + "\" berhasil ditambahkan!");
    }

    // ──────────────────────────────────────────────────────────────
    // MENU 3 - INPUT ABSENSI STAFF
    // ──────────────────────────────────────────────────────────────
    static void inputAbsensi() {
        System.out.println("\n--- INPUT ABSENSI STAFF ---");

        if (staffs.isEmpty()) {
            System.out.println("  [!] Belum ada data staff. Tambah staff terlebih dahulu.");
            return;
        }

        System.out.println("Pilih staff yang ingin diinput absensinya:");
        for (int i = 0; i < staffs.size(); i++)
            System.out.println("  " + (i + 1) + ". " + staffs.get(i).nama
                             + " (Bagian: " + staffs.get(i).bagian + ")");

        System.out.print("Nomor staff: ");
        int idx = bacaInt() - 1;
        if (idx < 0 || idx >= staffs.size()) {
            System.out.println("  [!] Nomor tidak valid.");
            return;
        }

        System.out.println("\nAturan absensi: Jam masuk dan keluar antara 0-23, durasi HARUS tepat 8 jam.");

        System.out.print("Jam masuk  : "); int masuk  = bacaInt();
        System.out.print("Jam keluar : "); int keluar = bacaInt();
        System.out.print("Status     (Hadir / Izin / Sakit)   : "); String status = bacaString();

        System.out.println();
        staffs.get(idx).input_absen(masuk, keluar, status);
    }

    // ──────────────────────────────────────────────────────────────
    // MENU 4 - LIHAT SEMUA DATA
    // ──────────────────────────────────────────────────────────────
    static void lihatData() {
        System.out.println("\n========== DATA MANAGER ==========");
        if (managers.isEmpty()) {
            System.out.println("  (Belum ada data manager)");
        } else {
            for (int i = 0; i < managers.size(); i++) {
                Manager m = managers.get(i);
                System.out.println("  " + (i + 1) + ". " + m.tampil_info());
                System.out.println("     Jadwal:\n" + m.lihat_laporan());
            }
        }

        System.out.println("========== DATA STAFF ==========");
        if (staffs.isEmpty()) {
            System.out.println("  (Belum ada data staff)");
        } else {
            for (int i = 0; i < staffs.size(); i++) {
                Staff s = staffs.get(i);
                System.out.println("  " + (i + 1) + ". " + s.tampil_info());
                System.out.println("     Riwayat Absensi:\n" + s.lihat_jadwal());
            }
        }
    }

    // ──────────────────────────────────────────────────────────────
    // HELPER: baca input aman
    // ──────────────────────────────────────────────────────────────
    static int bacaInt() {
        while (true) {
            try   { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) {
                System.out.print("  [!] Masukkan angka yang valid: ");
            }
        }
    }

    static double bacaDouble() {
        while (true) {
            try   { return Double.parseDouble(sc.nextLine().trim()); }
            catch (NumberFormatException e) {
                System.out.print("  [!] Masukkan angka yang valid: ");
            }
        }
    }

    static String bacaString() {
        String s = sc.nextLine().trim();
        while (s.isEmpty()) {
            System.out.print("  [!] Tidak boleh kosong, coba lagi: ");
            s = sc.nextLine().trim();
        }
        return s;
    }
}