public class Mahasiswa {
    private String nim;
    private String nama;
    private String jenisKelamin;
    private String sarapan;

    public Mahasiswa(String nim, String nama, String jenisKelamin, String sarapan) {
        this.nim = nim;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.sarapan = sarapan;
    }

    public void setNim(String nim) {

        this.nim = nim;
    }

    public void setNama(String nama) {

        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {

        this.jenisKelamin = jenisKelamin;
    }

    public void setSarapan(String sarapan) {
        this.sarapan = sarapan;
    }

    public String getNim() {

        return this.nim;
    }

    public String getNama() {

        return this.nama;
    }

    public String getJenisKelamin() {

        return this.jenisKelamin;
    }

    public String getSarapan() {
        return sarapan;
    }
}
