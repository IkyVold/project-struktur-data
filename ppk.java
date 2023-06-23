import java.util.Scanner; //mengambil input dari pengguna

public class ppk {
    public static void main(String[] args) { //menggunakan metode main sebagai titik awal eksekusi
        Scanner input = new Scanner(System.in); // menggunakan objek scanner baru dengan menggunakan system.ini sebagai argumen konsstruktor
        DaftarJoki daftarJoki = new DaftarJoki(10); // objek daftar joki dibuat dengan instance dari kelas Daftarjoki, di panggil dengan 10 yg menentuksn kapasitas daftar

        boolean selesai = false; //digunakan untuk mengontrol apakah user telah selesai, false yg di artikan sebagai loop

        while (!selesai) { //awal dalam loop yg akan berjalan selama selesai bernilai false, loop ini mengulang tampilan menu dan mnerima input
            System.out.println("Aplikasi Order Jasa Joki Game Online");
            System.out.println("------------------------------------");
            System.out.println("1. Tambah Joki");
            System.out.println("2. Tampilkan Daftar Joki");
            System.out.println("3. Pesan Joki");
            System.out.println("4. Cari Joki");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = input.nextInt(); // pengguna diminta untuk memasukan pilihan melalui input.next, nilai yg di masukan disimpan dalam pilihan

            switch (pilihan) { //digunakan untuk mengevaluasi pilihan
                case 1:
                    System.out.print("Masukkan nama joki: ");
                    input.nextLine(); // Membersihkan newline di buffer
                    String nama = input.nextLine();
                    System.out.print("Masukkan game yang dikuasai: ");
                    String game = input.nextLine();
                    System.out.print("Masukkan harga per jam: ");
                    double harga = input.nextDouble();

                    Joki jokiBaru = new Joki(nama, game, harga);
                    daftarJoki.tambahJoki(jokiBaru);
                    break;
                case 2:
                    daftarJoki.tampilkanDaftarJoki();
                    break;
                case 3:
                    if (daftarJoki.getPesananJoki(1) != null) {
                        System.out.print("Masukkan nomor joki yang ingin dipesan: ");
                        int nomorJoki = input.nextInt();

                        Joki jokiDipesan = daftarJoki.getPesananJoki(nomorJoki);
                        if (jokiDipesan != null) {
                            System.out.print("Masukkan jumlah jam yang dipesan untuk joki " + jokiDipesan.getNama() + ": ");
                            int jumlahJam = input.nextInt();

                            double totalHarga = jokiDipesan.getHarga() * jumlahJam;
                            System.out.println("Joki " + jokiDipesan.getNama() + " telah dipesan untuk " + jumlahJam + " jam.");
                            System.out.println("Total harga: " + totalHarga);
                        } else {
                            System.out.println("Nomor joki tidak valid.");
                        }
                    } else {
                        System.out.println("Belum ada joki yang tersedia.");
                    }
                    break;
                case 4: 
                    input.nextLine();
                    System.out.println("Masukkan nama joki");
                    String cariNama = input.nextLine();
                    daftarJoki.searchJoki(cariNama);
                    break;
                case 5: //variabel selesai akan menjadi true sehingga loop utama akan berakhir
                    selesai = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }

            System.out.println();
        }

        System.out.println("Terima kasih telah menggunakan aplikasi Order Jasa Joki Game Online!");
    }
}


class Joki {                    //memiliki 3 atribut privat
    private String nama;
    private String game;
    private double harga;

    public Joki(String nama, String game, double harga) {
        this.nama = nama;
        this.game = game;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public String getGame() {
        return game;
    }

    public double getHarga() {  // metode get untuk mengakses nilai variabel tersebut
        return harga;
    }
}

class DaftarJoki {  // memiliki 2 variabel instance 
    private Joki[] daftarJoki; //yg merupakan array dari joki
    private int size; // yg menunjukan jumlah joki yg di daftar

    public DaftarJoki(int capacity) {
        daftarJoki = new Joki[capacity]; //kapasitas maksimum daftar joki akan sesuai dengan nilai capacity yg di atas 
        size = 0; // menunjukan bahwa saat objek class pertama kali dibuat dan blm ada yg terdaftar
    }

    public void tambahJoki(Joki joki) { //digunakan menambahkan objek ke joki ke dalam daftarjoki
        if (size < daftarJoki.length) { // kondisi pertama yg memeriksa apakah size (jumlah joki yg sdh ada) kurang panjang dri array dan daftar joki jika benar brrti msh ada ruang kosong untuk menambah daftarjoki
            daftarJoki[size] = joki; //jika kondisi pertama terpenuhi, size digunakan untuk memntukan posisi baru untuk joki yg ditambahkan
            size++; //setelah joki ditambahkan, nilai size ditingkatkan
            System.out.println("Joki berhasil ditambahkan!");
        } else { //Jika kondisi pertama tidak terpenuhi, yaitu jika size sama dengan panjang array daftarJoki, maka daftar joki sudah penuh dan tidak ada ruang kosong lagi.
            System.out.println("Daftar joki sudah penuh.");
        }
    }

    public void tampilkanDaftarJoki() {
        if (size > 0) {
            System.out.println("Daftar Joki:");
            for (int i = 0; i < size; i++) { //Ini adalah loop for dimulai dri index 0 sampai -1
                Joki joki = daftarJoki[i]; //Dalam setiap iterasi, objek Joki pada indeks i dalam array daftarJoki disimpan dalam variabel joki.
                System.out.println((i + 1) + ". Nama: " + joki.getNama() + ", Game: " + joki.getGame() + ", Harga: " + joki.getHarga() + " per jam"); //dicetak ke layar dengan format yang ditentukan. Nomor urut joki ditambahkan dengan menggunakan (i + 1).
            }
        } else {
            System.out.println("Belum ada joki yang terdaftar.");
        }
    }

    public void searchJoki (String nama) {
         if (size > 0) {
            boolean ketemu = false; 
            System.out.println("Daftar Joki:");
            for (int i = 0; i < size; i++) {
                Joki joki = daftarJoki[i];
                if(joki.getNama().equalsIgnoreCase(nama)){
                    System.out.println("Nama: " + joki.getNama() + ", Game: " + joki.getGame() + ", Harga: " + joki.getHarga() + " per jam");
                    ketemu = true;
                }
            }

            if(!ketemu){
                System.out.println("Tidak ditemukan");
            }
        } else {
            System.out.println("Belum ada joki yang terdaftar.");
        }
    }

    public Joki getPesananJoki(int nomorJoki) {
        if (nomorJoki >= 1 && nomorJoki <= size) {
            Joki jokiDipesan = daftarJoki[nomorJoki - 1];
            return jokiDipesan;
        } else {
            return null;
        }
    }
}



