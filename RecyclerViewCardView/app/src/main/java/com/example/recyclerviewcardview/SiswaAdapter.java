package com.example.recyclerviewcardview; // Sesuaikan dengan package name proyek Anda

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem; // Impor untuk MenuItem
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu; // Impor untuk PopupMenu
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.SiswaViewHolder> {

    private Context context;
    private List<Siswa> siswaList;

    // Constructor
    public SiswaAdapter(Context context, List<Siswa> siswaList) {
        this.context = context;
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public SiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menginflasi layout item_siswa.xml untuk setiap item di RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.item_siswa, parent, false);
        return new SiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaViewHolder holder, int position) {
        // Mengambil objek Siswa pada posisi tertentu
        Siswa siswa = siswaList.get(position);

        // Mengatur teks untuk TextViews di layout item_siswa
        holder.tvNama.setText(siswa.getNama());
        holder.tvAlamat.setText(siswa.getAlamat());

        // --- OnClickListener untuk seluruh item (yang sudah ada di kode Anda) ---
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Siswa: " + siswa.getNama() + " Alamat: " + siswa.getAlamat(), Toast.LENGTH_SHORT).show();
            }
        });

        // --- OnClickListener untuk ikon menu (tv_menu) ---
        // Pastikan tv_menu ada di item_siswa.xml dan diinisialisasi di SiswaViewHolder
        holder.tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat instance PopupMenu
                PopupMenu popupMenu = new PopupMenu(context, holder.tvMenu);
                // Menginflasi menu_option.xml ke PopupMenu
                popupMenu.inflate(R.menu.menu_option); // Pastikan Anda memiliki file res/menu/menu_option.xml

                // Menambahkan OnMenuItemClickListener untuk menangani klik item menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.menu_simpan) { // Pastikan id ini ada di menu_option.xml
                            Toast.makeText(context, "Simpan Data " + siswa.getNama(), Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (id == R.id.menu_hapus) { // Pastikan id ini ada di menu_option.xml
                            // Dapatkan posisi item yang sedang dioperasikan
                            int currentPosition = holder.getAdapterPosition();
                            if (currentPosition != RecyclerView.NO_POSITION) { // Pastikan posisi valid
                                String namaSiswaDihapus = siswaList.get(currentPosition).getNama();
                                siswaList.remove(currentPosition); // Hapus data dari daftar
                                notifyItemRemoved(currentPosition); // Beri tahu adapter bahwa item dihapus
                                Toast.makeText(context, "Sudah di Hapus " + namaSiswaDihapus, Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                        return false;
                    }
                });
                // Menampilkan PopupMenu
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // Mengembalikan jumlah total item dalam daftar
        return siswaList.size();
    }

    // Kelas ViewHolder untuk menampung referensi View dari setiap item layout
    public static class SiswaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        TextView tvAlamat;
        TextView tvMenu; // Deklarasi TextView untuk ikon menu (tiga titik)

        public SiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            // Menghubungkan variabel dengan ID View dari item_siswa.xml
            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
            tvMenu = itemView.findViewById(R.id.tv_menu); // Inisialisasi tvMenu dari item_siswa.xml
        }
    }
}