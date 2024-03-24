import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private Database database;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JRadioButton yaRadioButton;
    private JRadioButton tidakRadioButton;
    private JLabel rotiField;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        //objek database
        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // Menambahkan radio button
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(yaRadioButton);
        buttonGroup.add(tidakRadioButton);

        // Menambahkan listener untuk radio button
        yaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (yaRadioButton.isSelected()) {
                    // Ketika yaRadioButton dipilih, atur nilai sarapan menjadi "Ya"
                    if (selectedIndex >= 0) {
                        listMahasiswa.get(selectedIndex).setSarapan("Yes");
                    }
                }
            }
        });

        tidakRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tidakRadioButton.isSelected()) {
                    // Ketika tidakRadioButton dipilih, atur nilai sarapan menjadi "Tidak"
                    if (selectedIndex >= 0) {
                        listMahasiswa.get(selectedIndex).setSarapan("No");
                    }
                }
            }
        });

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    String nim = "";
                            deleteData();
                }
            }
        });

        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener (new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedSarapan = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // Atur radio button berdasarkan nilai sarapan yang ada di data
                if (selectedSarapan.equals("Yes")) {
                    yaRadioButton.setSelected(true);
                } else if (selectedSarapan.equals("No")) {
                    tidakRadioButton.setSelected(true);
                }

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                rotiField.setText(selectedSarapan);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Sarapan"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");

            int i =0;
            while (resultSet.next()){
                Object[] row = new Object[5];

                row[0] = i + 1;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("sarapan");

                temp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temp;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String sarapan = "";

        if (yaRadioButton.isSelected()) {
            sarapan = "Ya";
        } else if (tidakRadioButton.isSelected()) {
            sarapan = "Tidak";
        }

        //tambahkan data ke dalam database
        try {
            String sql = "INSERT INTO mahasiswa VALUES (null, '" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + sarapan + "')";
            database.insertUpdateDeleteQuery(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");;
    }

    public void updateData() {
        // Ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String sarapan = "";

        if (yaRadioButton.isSelected()) {
            sarapan = "Ya";
        } else if (tidakRadioButton.isSelected()) {
            sarapan = "Tidak";
        }

        // Ubah data mahasiswa di dalam database
        try {
            String sql = "UPDATE mahasiswa SET nama = '" + nama + "', jenis_kelamin = '" + jenisKelamin + "', sarapan = '" + sarapan + "' WHERE nim = '" + nim + "'";
            database.insertUpdateDeleteQuery(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Update tabel
        mahasiswaTable.setModel(setTable());

        // Bersihkan form
        clearForm();

        // Feedback
        System.out.println("Update berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
    }


    public void deleteData() {
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin ingin menghapus data?","Delete",JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            // Ambil NIM dari data yang dipilih
            String nim = listMahasiswa.get(selectedIndex).getNim();

            // Hapus data dari database
            try {
                String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "'";
                database.insertUpdateDeleteQuery(sql);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Update tabel
            mahasiswaTable.setModel(setTable());

            // Feedback
            System.out.println("Delete berhasil!");
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        } else {
            System.out.println("Lain kali pikirin dulu!");
            JOptionPane.showMessageDialog(null, "Data ngga di apa-apa in");
        }

        // Bersihkan form
        clearForm();
    }




    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        rotiField.setText("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

}
